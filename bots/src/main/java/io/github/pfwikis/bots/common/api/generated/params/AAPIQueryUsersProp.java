package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Which pieces of information to include:
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryUsersProp {

	/**Tags if the user is blocked, by whom, and for what reason.*/
	BLOCKINFO("blockinfo"),

	/**Indicates whether an account for valid but unregistered usernames can be created. To check whether the current user can perform the account creation, use <a href="/wiki/Special:ApiHelp/query%2Buserinfo" title="Special:ApiHelp/query+userinfo"><kbd>action=query&amp;meta=userinfo&amp;uiprop=cancreateaccount</kbd></a>.*/
	CANCREATE("cancreate"),

	/**Adds the central IDs and attachment status for the user.*/
	CENTRALIDS("centralids"),

	/**Adds the user's edit count.*/
	EDITCOUNT("editcount"),

	/**Tags if the user can and wants to receive email through <a href="/wiki/Special:EmailUser" title="Special:EmailUser">Special:Emailuser</a>.*/
	EMAILABLE("emailable"),

	/**Tags the gender of the user. Returns "male", "female", or "unknown".*/
	GENDER("gender"),

	/**Lists groups that each user has been explicitly assigned to, including the expiry date of each group membership.*/
	GROUPMEMBERSHIPS("groupmemberships"),

	/**Lists all the groups each user belongs to.*/
	GROUPS("groups"),

	/**Lists all the groups a user is automatically a member of.*/
	IMPLICITGROUPS("implicitgroups"),

	/**Adds the user's registration timestamp.*/
	REGISTRATION("registration"),

	/**Lists all the rights each user has.*/
	RIGHTS("rights");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryUsersProp> set = EnumSet.noneOf(AAPIQueryUsersProp.class);

		public AAPIQueryUsersProp[] build() {
			return set.toArray(AAPIQueryUsersProp[]::new);
		}

		/**Tags if the user is blocked, by whom, and for what reason.*/
		public Builder BLOCKINFO() {
			set.add(BLOCKINFO);
			return this;
		}

		/**Indicates whether an account for valid but unregistered usernames can be created. To check whether the current user can perform the account creation, use <a href="/wiki/Special:ApiHelp/query%2Buserinfo" title="Special:ApiHelp/query+userinfo"><kbd>action=query&amp;meta=userinfo&amp;uiprop=cancreateaccount</kbd></a>.*/
		public Builder CANCREATE() {
			set.add(CANCREATE);
			return this;
		}

		/**Adds the central IDs and attachment status for the user.*/
		public Builder CENTRALIDS() {
			set.add(CENTRALIDS);
			return this;
		}

		/**Adds the user's edit count.*/
		public Builder EDITCOUNT() {
			set.add(EDITCOUNT);
			return this;
		}

		/**Tags if the user can and wants to receive email through <a href="/wiki/Special:EmailUser" title="Special:EmailUser">Special:Emailuser</a>.*/
		public Builder EMAILABLE() {
			set.add(EMAILABLE);
			return this;
		}

		/**Tags the gender of the user. Returns "male", "female", or "unknown".*/
		public Builder GENDER() {
			set.add(GENDER);
			return this;
		}

		/**Lists groups that each user has been explicitly assigned to, including the expiry date of each group membership.*/
		public Builder GROUPMEMBERSHIPS() {
			set.add(GROUPMEMBERSHIPS);
			return this;
		}

		/**Lists all the groups each user belongs to.*/
		public Builder GROUPS() {
			set.add(GROUPS);
			return this;
		}

		/**Lists all the groups a user is automatically a member of.*/
		public Builder IMPLICITGROUPS() {
			set.add(IMPLICITGROUPS);
			return this;
		}

		/**Adds the user's registration timestamp.*/
		public Builder REGISTRATION() {
			set.add(REGISTRATION);
			return this;
		}

		/**Lists all the rights each user has.*/
		public Builder RIGHTS() {
			set.add(RIGHTS);
			return this;
		}
	}
}
