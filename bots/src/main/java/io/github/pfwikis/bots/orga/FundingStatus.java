package io.github.pfwikis.bots.orga;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import com.beust.jcommander.Parameters;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.common.api.generated.params.NS;
import io.github.pfwikis.bots.common.api.generated.params.UserGroup;
import io.github.pfwikis.bots.common.api.model.PageRef;
import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import io.github.pfwikis.bots.utils.Jackson;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.entities.Widget;
import okhttp3.OkHttpClient;
import okhttp3.Request;

@Slf4j
@Parameters
public class FundingStatus extends SimpleBot {
	
	public FundingStatus() {
		super("funding-status", "Funding Status");
	}

	@Override
	protected void run(RunContext ctx) throws Exception {
		if(run.getServer() != Wiki.PF) return;
		
		var lastYear = LocalDateTime.now().minusYears(1).toInstant(ZoneOffset.UTC).toEpochMilli()
				/1000L;
		var payments = Zeffy.request(
			Payment.class,
			zeffyToken,
			"https://api.zeffy.com/api/v1/payments?limit=100&status=succeeded&created[gt]="+lastYear
		);
				
		var sum = payments.stream().mapToLong(p->p.eligible_amount).sum();
		var dollar = new BigDecimal(sum).scaleByPowerOfTen(-2);
		var goal = new BigDecimal(960);

		run.getWiki().editIfChange(
			PageRef.of(NS.TEMPLATE, "Funding status"),
			"""
			<noinclude>
				{{Bot created|VirenerusBot#%3$s}}
				[[Category:Synced to starfinderwiki]]
				{{Documentation
				|content={{Funding status}}<templatedata>
					{
						"params": {},
						"format": "inline",
						"description": "Shows the current funding status"
					}
					</templatedata>
				}}
			</noinclude><includeonly><div class="funding-status">
				<div class="pfw-funding-header">
					<div class="pfw-funding-title">[[Tabletop RPG Historical Society|Historical Society]] Funding</div>
					<div class="pfw-funding-amount">
						<strong>$%1$,d</strong>
						of $960
					</div>
				</div>
			
				<div class="pfw-funding-bar" role="progressbar"
						 aria-valuenow="%2$d" aria-valuemin="0" aria-valuemax="100"
						 aria-label="%2$d%% of funding goal reached">
					<div class="pfw-funding-progress" style="width: %2$d%%;"></div>
					<div class="pfw-funding-percent">%2$d%% funded</div>
				</div>
				
				{{#widget:Funding status}}
			</div></includeonly>
			""".replaceAll("\t", "").formatted(
					dollar.intValue(),
					dollar.scaleByPowerOfTen(2).divide(goal, RoundingMode.HALF_UP).intValue(),
					this.getBotName()),
			"Automatic update"
		);
		
		run.getWiki().editIfChange(
			PageRef.of(NS.WIDGET, "Funding status"),
			"""
			<noinclude>
				{{Bot created|VirenerusBot#%s}}
				This is meant to be used with {{tl|Funding status}}.
				{{#widget:Funding status}}
				[[Category:Synced to starfinderwiki]]
			</noinclude><includeonly><button
					type="button"
					class="pfw-funding-donate-button"
					id="pfw-funding-donate"
				>Donate via Zeffy</button>
			
				<div class="pfw-funding-donation" id="pfw-funding-donation"></div>
			
				<script>
				(() => {
					const button = document.getElementById("pfw-funding-donate");
					const container = document.getElementById("pfw-funding-donation");
				
					button.addEventListener("click", () => {
						const iframe = document.createElement("iframe");
				
						iframe.title = "Donation form powered by Zeffy";
						iframe.src = "https://www.zeffy.com/embed/donation-form/new-org-same-great-wikis";
						iframe.setAttribute("allowpaymentrequest", "");
						iframe.setAttribute("allowTransparency", "true");
				
						container.appendChild(iframe);
						button.remove();
					});
				})();
			</script></includeonly>
			""".formatted(this.getBotName()),
			"Automatic update"
		);
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	private record Payment(String id, long eligible_amount) {}

	@Override
	public String getDescription() {
		return """
			This bot keeps the current status of the funding transparent.
			""";
	}
}
