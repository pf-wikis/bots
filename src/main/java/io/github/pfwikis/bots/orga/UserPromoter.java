package io.github.pfwikis.bots.orga;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import com.beust.jcommander.Parameters;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.github.pfwikis.bots.common.api.generated.params.UserGroup;
import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import io.github.pfwikis.bots.utils.Jackson;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;

@Slf4j
@Parameters
public class UserPromoter extends SimpleBot {

	public UserPromoter() {
		super("user-promoter", "User Promoter");
	}

	@Override
	protected void run(RunContext ctx) throws Exception {
		OkHttpClient client = new OkHttpClient();
		
		var contacts = new ArrayList<Contact>();
		boolean cont = true;
		String next = null;
		while(cont) {
			Request request = new Request.Builder()
				  .url("https://api.zeffy.com/api/v1/contacts?limit=100"+(next==null?"":("&starting_after="+next)))
				  .get()
				  .addHeader("Accept", "application/json")
				  .addHeader("Authorization", "Bearer b71e2d13-d05f-411a-b060-b74ca48f44b5")
				  .build();
			var resp = Jackson.JSON.readValue(client.newCall(request)
					.execute()
					.body()
					.bytes(), Response.class);
			contacts.addAll(resp.data);
			cont = resp.has_more;
			next = resp.next_cursor;
		}
		
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
	private record Response(boolean has_more, String next_cursor, List<Contact> data) {}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	private record Contact(String email, String donor_type, Instant last_donation_date, long total_contribution) {}

	@Override
	public String getDescription() {
		return """
			This bot adds users that are donating via Zeffy to the patron group.
			""";
	}
}
