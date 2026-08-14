package io.github.pfwikis.bots.orga;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Objects;

import com.beust.jcommander.Parameters;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.github.pfwikis.bots.common.api.generated.params.UserGroup;
import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters
public class UserPromoter extends SimpleBot {
	
	public UserPromoter() {
		super("user-promoter", "User Promoter");
	}

	@Override
	protected void run(RunContext ctx) throws Exception {
		
		var contacts = Zeffy.request(
			Contact.class,
			zeffyToken,
			"https://api.zeffy.com/api/v1/contacts?limit=100"
		);

		var newPatrons = new HashSet<>(contacts.stream()
				.filter(c->c.last_donation_date.isBefore(Instant.now().minus(365, ChronoUnit.DAYS)))
				.map(c->run.getWiki().getUserByEmail(c.email))
				.filter(Objects::nonNull)
				.toList());
		
		var oldPatrons = run.getWiki().getUsersInGroup(UserGroup.PATRON);
		
		for(var old : oldPatrons) {
			if(!newPatrons.removeIf(np->np.getUserid()==old.getUserid())) {
				log.info("No longer a patron: {}", old.getName());
				run.getWiki().removeGroupFromUser(old, UserGroup.PATRON);
			}
		}
		
		for(var n : newPatrons) {
			log.info("New patron: {}", n.getName());
			run.getWiki().addGroupToUser(n, UserGroup.PATRON);
		}
	}
	@JsonIgnoreProperties(ignoreUnknown = true)
	private record Contact(String email, String donor_type, Instant last_donation_date, long total_contribution) {}

	@Override
	public String getDescription() {
		return """
			This bot adds users that are donating via Zeffy to the patron group.
			""";
	}
}
