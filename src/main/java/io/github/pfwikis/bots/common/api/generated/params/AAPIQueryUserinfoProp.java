package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Which pieces of information to include:
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryUserinfoProp {

	/**Echoes the <code>Accept-Language</code> header sent by the client in a structured format.*/
	ACCEPTLANG("acceptlang"),

	/**Tags if the current user is blocked, by whom, and for what reason.*/
	BLOCKINFO("blockinfo"),

	/**Indicates whether the user is allowed to create accounts. To check whether some specific account can be created, use <a href="/wiki/Special:ApiHelp/query%2Busers" title="Special:ApiHelp/query+users"><kbd>action=query&amp;list=users&amp;usprop=cancreate</kbd></a>.*/
	CANCREATEACCOUNT("cancreateaccount"),

	/**Adds the central IDs and attachment status for the user.*/
	CENTRALIDS("centralids"),

	/**Lists the groups the current user can add to and remove from.*/
	CHANGEABLEGROUPS("changeablegroups"),

	/**Adds the current user's edit count.*/
	EDITCOUNT("editcount"),

	/**Adds the user's email address and email authentication date.*/
	EMAIL("email"),

	/**Lists groups that the current user has been explicitly assigned to, including the expiry date of each group membership.*/
	GROUPMEMBERSHIPS("groupmemberships"),

	/**Lists all the groups the current user belongs to.*/
	GROUPS("groups"),

	/**Adds a tag <samp>messages</samp> if the current user has pending messages.*/
	HASMSG("hasmsg"),

	/**Lists all the groups the current user is automatically a member of.*/
	IMPLICITGROUPS("implicitgroups"),

	/**Adds the date of user's latest contribution.*/
	LATESTCONTRIB("latestcontrib"),

	/**Lists all preferences the current user has set.*/
	OPTIONS("options"),

	/**Lists all rate limits applying to the current user.*/
	RATELIMITS("ratelimits"),

	/**Adds the user's real name.*/
	REALNAME("realname"),

	/**Adds the user's registration date.*/
	REGISTRATIONDATE("registrationdate"),

	/**Lists all the rights the current user has.*/
	RIGHTS("rights"),

	/**Lists all rate limits that would apply to the current user if they were not exempt from all ratelimits based on user rights or ip*/
	THEORETICALRATELIMITS("theoreticalratelimits"),

	/**Adds the count of unread pages on the user's watchlist (maximum 999; returns <samp>1000+</samp> if more).*/
	UNREADCOUNT("unreadcount");

	private final String jsonValue;
}
