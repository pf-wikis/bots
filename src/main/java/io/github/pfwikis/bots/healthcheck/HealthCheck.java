package io.github.pfwikis.bots.healthcheck;

import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.security.cert.X509Certificate;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.DurationFormatUtils;

import com.beust.jcommander.Parameters;
import com.google.common.util.concurrent.Uninterruptibles;

import io.github.pfwikis.bots.common.Discord;
import io.github.pfwikis.bots.scheduler.Schedulable;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Slf4j
@Parameters
public class HealthCheck extends Schedulable {

	private final Discord discord;

	public HealthCheck(Discord discord) {
		super("health-check");
		this.discord = discord;
	}

	public static void main(String...args) {
		new HealthCheck(new Discord(args[0])).execute();
	}
	
	@Override
	public void execute() {
		test("https://pathfinderwiki.com", "pathfinderwiki.com");
		test("https://matomo.pathfinderwiki.com", "pathfinderwiki.com");
		test("https://map.pathfinderwiki.com", "pathfinderwiki.com");
		test("https://starfinderwiki.com", "starfinderwiki.com");
	}
	
	private static Duration WARNING_PERIOD = Duration.ofDays(7);
	
	private void test(String url, String domain) {
		int tryCount = 1;
		try {
			boolean retry  = true;
			while(retry) {
				try {
					retry = false;
					tryTest(url, domain);
				} catch (ConnectException e) {
					if(tryCount++>6) {
						throw e;
					}
					else {
						log.warn("Failed with {} on {} . Retrying in 10s.", e.getMessage(), url, e);
						retry = true;
						Uninterruptibles.sleepUninterruptibly(10, TimeUnit.SECONDS);
					}
				}
			}
		} catch (IOException e) {
			discord.report("**Unexpected exception during health check:**\n"
					+e.getClass()+" "
					+e.getMessage());
		}
	}

	private void tryTest(String url, String domain) throws MalformedURLException, IOException {
		var cert = getCertificate(url, domain);
		var notAfter = cert.getNotAfter().toInstant();
		var inOneWeek = Instant.now().plus(WARNING_PERIOD);
		
		if(inOneWeek.isBefore(notAfter)) {
			log.info("{} valid until {}", url, cert.getNotAfter());
		}
		else {
			log.error("{} only valid until {}", url, cert.getNotAfter());
			discord.report(
				"**Certificate Warning: **the certificate for "
				+ url
				+ " will expire in "
				+ DurationFormatUtils.formatDurationWords(
					Duration.between(Instant.now(), notAfter).truncatedTo(ChronoUnit.MINUTES).toMillis(),
					true,
					true
				)
				+ "! Something is wrong with the renewal system.");
		}
	}
	
	private final OkHttpClient client = new OkHttpClient.Builder()
			.connectTimeout(30, TimeUnit.SECONDS)
			.callTimeout(30, TimeUnit.SECONDS)
			.readTimeout(30, TimeUnit.SECONDS)
			.build();

	private X509Certificate getCertificate(String url, String domain) throws MalformedURLException, IOException {
		Request request = new Request.Builder()
			.url(url)
			.build();

		try (Response response = client.newCall(request).execute()) {
			var certs = response.handshake().peerCertificates();
			for(var cert:certs) {
				if(cert instanceof X509Certificate xCert) {
					if(("CN="+domain).equals(xCert.getSubjectX500Principal().getName()))
						return xCert;
				}
			}
			throw new IllegalStateException("Could not find Certificate for url "+url);
		}
	}

}
