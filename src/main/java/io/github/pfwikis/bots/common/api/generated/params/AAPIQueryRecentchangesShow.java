package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Show only items that meet these criteria. For example, to see only minor edits done by logged-in users, set rcshow=minor|!anon.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryRecentchangesShow {
	NOT_ANON("!anon"),

	NOT_AUTOPATROLLED("!autopatrolled"),

	NOT_BOT("!bot"),

	NOT_MINOR("!minor"),

	NOT_PATROLLED("!patrolled"),

	NOT_REDIRECT("!redirect"),

	ANON("anon"),

	AUTOPATROLLED("autopatrolled"),

	BOT("bot"),

	MINOR("minor"),

	PATROLLED("patrolled"),

	REDIRECT("redirect"),

	UNPATROLLED("unpatrolled");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryRecentchangesShow> set =
				EnumSet.noneOf(AAPIQueryRecentchangesShow.class);

		public AAPIQueryRecentchangesShow[] build() {
			return set.toArray(AAPIQueryRecentchangesShow[]::new);
		}

		public Builder NOT_ANON() {
			set.add(NOT_ANON);
			return this;
		}

		public Builder NOT_AUTOPATROLLED() {
			set.add(NOT_AUTOPATROLLED);
			return this;
		}

		public Builder NOT_BOT() {
			set.add(NOT_BOT);
			return this;
		}

		public Builder NOT_MINOR() {
			set.add(NOT_MINOR);
			return this;
		}

		public Builder NOT_PATROLLED() {
			set.add(NOT_PATROLLED);
			return this;
		}

		public Builder NOT_REDIRECT() {
			set.add(NOT_REDIRECT);
			return this;
		}

		public Builder ANON() {
			set.add(ANON);
			return this;
		}

		public Builder AUTOPATROLLED() {
			set.add(AUTOPATROLLED);
			return this;
		}

		public Builder BOT() {
			set.add(BOT);
			return this;
		}

		public Builder MINOR() {
			set.add(MINOR);
			return this;
		}

		public Builder PATROLLED() {
			set.add(PATROLLED);
			return this;
		}

		public Builder REDIRECT() {
			set.add(REDIRECT);
			return this;
		}

		public Builder UNPATROLLED() {
			set.add(UNPATROLLED);
			return this;
		}
	}
}
