package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Show only items that meet these criteria.
 * For example, to see only indefinite blocks on IP addresses, set <kbd>bkshow=ip|!temp</kbd>.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryBlocksShow {
	NOT_ACCOUNT("!account"),

	NOT_IP("!ip"),

	NOT_RANGE("!range"),

	NOT_TEMP("!temp"),

	ACCOUNT("account"),

	IP("ip"),

	RANGE("range"),

	TEMP("temp");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryBlocksShow> set = EnumSet.noneOf(AAPIQueryBlocksShow.class);

		public AAPIQueryBlocksShow[] build() {
			return set.toArray(AAPIQueryBlocksShow[]::new);
		}

		public Builder NOT_ACCOUNT() {
			set.add(NOT_ACCOUNT);
			return this;
		}

		public Builder NOT_IP() {
			set.add(NOT_IP);
			return this;
		}

		public Builder NOT_RANGE() {
			set.add(NOT_RANGE);
			return this;
		}

		public Builder NOT_TEMP() {
			set.add(NOT_TEMP);
			return this;
		}

		public Builder ACCOUNT() {
			set.add(ACCOUNT);
			return this;
		}

		public Builder IP() {
			set.add(IP);
			return this;
		}

		public Builder RANGE() {
			set.add(RANGE);
			return this;
		}

		public Builder TEMP() {
			set.add(TEMP);
			return this;
		}
	}
}
