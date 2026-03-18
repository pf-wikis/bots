package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Exclude users in the given groups. Does not include implicit or auto-promoted groups like *, user, or autoconfirmed.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryContributorsExcludegroup {
	BOT("bot"),

	PUSH_SUBSCRIPTION_MANAGER("push-subscription-manager"),

	SMWADMINISTRATOR("smwadministrator"),

	SMWCURATOR("smwcurator"),

	SMWEDITOR("smweditor"),

	SYSOP("sysop"),

	TECHADMIN("techadmin");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryContributorsExcludegroup> set =
				EnumSet.noneOf(AAPIQueryContributorsExcludegroup.class);

		public AAPIQueryContributorsExcludegroup[] build() {
			return set.toArray(AAPIQueryContributorsExcludegroup[]::new);
		}

		public Builder BOT() {
			set.add(BOT);
			return this;
		}

		public Builder PUSH_SUBSCRIPTION_MANAGER() {
			set.add(PUSH_SUBSCRIPTION_MANAGER);
			return this;
		}

		public Builder SMWADMINISTRATOR() {
			set.add(SMWADMINISTRATOR);
			return this;
		}

		public Builder SMWCURATOR() {
			set.add(SMWCURATOR);
			return this;
		}

		public Builder SMWEDITOR() {
			set.add(SMWEDITOR);
			return this;
		}

		public Builder SYSOP() {
			set.add(SYSOP);
			return this;
		}

		public Builder TECHADMIN() {
			set.add(TECHADMIN);
			return this;
		}
	}
}
