package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Show only items that meet these criteria, e.g. non minor edits only: <kbd>ucshow=!minor</kbd>.
 * </p><p>If <kbd>ucshow=patrolled</kbd> or <kbd>ucshow=!patrolled</kbd> is set, revisions older than <var><a href="/w/index.php?title=Mw:Special:MyLanguage/Manual:$wgRCMaxAge&amp;action=edit&amp;redlink=1" class="new" title="Mw:Special:MyLanguage/Manual:$wgRCMaxAge (page does not exist)">$wgRCMaxAge</a></var> (7776000 seconds) won't be shown.
 * </p>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryUsercontribsShow {
	NOT_AUTOPATROLLED("!autopatrolled"),

	NOT_MINOR("!minor"),

	NOT_NEW("!new"),

	NOT_PATROLLED("!patrolled"),

	NOT_TOP("!top"),

	AUTOPATROLLED("autopatrolled"),

	MINOR("minor"),

	NEW("new"),

	PATROLLED("patrolled"),

	TOP("top");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryUsercontribsShow> set =
				EnumSet.noneOf(AAPIQueryUsercontribsShow.class);

		public AAPIQueryUsercontribsShow[] build() {
			return set.toArray(AAPIQueryUsercontribsShow[]::new);
		}

		public Builder NOT_AUTOPATROLLED() {
			set.add(NOT_AUTOPATROLLED);
			return this;
		}

		public Builder NOT_MINOR() {
			set.add(NOT_MINOR);
			return this;
		}

		public Builder NOT_NEW() {
			set.add(NOT_NEW);
			return this;
		}

		public Builder NOT_PATROLLED() {
			set.add(NOT_PATROLLED);
			return this;
		}

		public Builder NOT_TOP() {
			set.add(NOT_TOP);
			return this;
		}

		public Builder AUTOPATROLLED() {
			set.add(AUTOPATROLLED);
			return this;
		}

		public Builder MINOR() {
			set.add(MINOR);
			return this;
		}

		public Builder NEW() {
			set.add(NEW);
			return this;
		}

		public Builder PATROLLED() {
			set.add(PATROLLED);
			return this;
		}

		public Builder TOP() {
			set.add(TOP);
			return this;
		}
	}
}
