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
public enum AAPIQueryAllusersProp {

	/**Adds the information about a current block on the user.*/
	BLOCKINFO("blockinfo"),

	/**Adds the central IDs and attachment status for the user.*/
	CENTRALIDS("centralids"),

	/**Adds the edit count of the user.*/
	EDITCOUNT("editcount"),

	/**Lists groups that the user is in. This uses more server resources and may return fewer results than the limit.*/
	GROUPS("groups"),

	/**Lists all the groups the user is automatically in.*/
	IMPLICITGROUPS("implicitgroups"),

	/**Adds the timestamp of when the user registered if available (may be blank).*/
	REGISTRATION("registration"),

	/**Lists rights that the user has.*/
	RIGHTS("rights");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryAllusersProp> set = EnumSet.noneOf(AAPIQueryAllusersProp.class);

		public AAPIQueryAllusersProp[] build() {
			return set.toArray(AAPIQueryAllusersProp[]::new);
		}

		/**Adds the information about a current block on the user.*/
		public Builder BLOCKINFO() {
			set.add(BLOCKINFO);
			return this;
		}

		/**Adds the central IDs and attachment status for the user.*/
		public Builder CENTRALIDS() {
			set.add(CENTRALIDS);
			return this;
		}

		/**Adds the edit count of the user.*/
		public Builder EDITCOUNT() {
			set.add(EDITCOUNT);
			return this;
		}

		/**Lists groups that the user is in. This uses more server resources and may return fewer results than the limit.*/
		public Builder GROUPS() {
			set.add(GROUPS);
			return this;
		}

		/**Lists all the groups the user is automatically in.*/
		public Builder IMPLICITGROUPS() {
			set.add(IMPLICITGROUPS);
			return this;
		}

		/**Adds the timestamp of when the user registered if available (may be blank).*/
		public Builder REGISTRATION() {
			set.add(REGISTRATION);
			return this;
		}

		/**Lists rights that the user has.*/
		public Builder RIGHTS() {
			set.add(RIGHTS);
			return this;
		}
	}
}
