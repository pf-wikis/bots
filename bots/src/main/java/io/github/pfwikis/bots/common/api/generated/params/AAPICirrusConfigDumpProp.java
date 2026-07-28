package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Type of configuration variables to dump*/
@Getter
@RequiredArgsConstructor
public enum AAPICirrusConfigDumpProp {
	GLOBALS("globals"),

	NAMESPACEMAP("namespacemap"),

	PROFILES("profiles"),

	REPLICAGROUP("replicagroup"),

	USERTESTING("usertesting");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPICirrusConfigDumpProp> set =
				EnumSet.noneOf(AAPICirrusConfigDumpProp.class);

		public AAPICirrusConfigDumpProp[] build() {
			return set.toArray(AAPICirrusConfigDumpProp[]::new);
		}

		public Builder GLOBALS() {
			set.add(GLOBALS);
			return this;
		}

		public Builder NAMESPACEMAP() {
			set.add(NAMESPACEMAP);
			return this;
		}

		public Builder PROFILES() {
			set.add(PROFILES);
			return this;
		}

		public Builder REPLICAGROUP() {
			set.add(REPLICAGROUP);
			return this;
		}

		public Builder USERTESTING() {
			set.add(USERTESTING);
			return this;
		}
	}
}
