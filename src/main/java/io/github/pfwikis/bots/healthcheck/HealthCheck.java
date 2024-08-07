package io.github.pfwikis.bots.healthcheck;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.X509Certificate;
import java.time.Duration;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.lang3.time.DurationFormatUtils;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.Discord;
import io.github.pfwikis.bots.scheduler.Schedulable;
import lombok.extern.slf4j.Slf4j;

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
		try {
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private X509Certificate getCertificate(String url, String domain) throws MalformedURLException, IOException {
		var con = (HttpsURLConnection) URI.create(url).toURL().openConnection();
		try {
			con.connect();
			for(var cert:con.getServerCertificates()) {
				if(cert instanceof X509Certificate xCert) {
					if(("CN="+domain).equals(xCert.getSubjectX500Principal().getName()))
						return xCert;
				}
			}
			throw new IllegalStateException("Could not find Certificate for url "+url);
		} finally {
			con.disconnect();
		}
	}

}
