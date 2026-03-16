package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Categories of lint errors*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryLinterrorsCategories {
	BOGUS_IMAGE_OPTIONS("bogus-image-options"),

	DELETABLE_TABLE_TAG("deletable-table-tag"),

	DUPLICATE_IDS("duplicate-ids"),

	FOSTERED("fostered"),

	FOSTERED_TRANSPARENT("fostered-transparent"),

	HTML5_MISNESTING("html5-misnesting"),

	LARGE_TABLES("large-tables"),

	MISC_TIDY_REPLACEMENT_ISSUES("misc-tidy-replacement-issues"),

	MISNESTED_TAG("misnested-tag"),

	MISSING_END_TAG("missing-end-tag"),

	MISSING_END_TAG_IN_HEADING("missing-end-tag-in-heading"),

	MULTI_COLON_ESCAPE("multi-colon-escape"),

	MULTILINE_HTML_TABLE_IN_LIST("multiline-html-table-in-list"),

	MULTIPLE_UNCLOSED_FORMATTING_TAGS("multiple-unclosed-formatting-tags"),

	NIGHT_MODE_UNAWARE_BACKGROUND_COLOR("night-mode-unaware-background-color"),

	OBSOLETE_TAG("obsolete-tag"),

	PWRAP_BUG_WORKAROUND("pwrap-bug-workaround"),

	SELF_CLOSED_TAG("self-closed-tag"),

	STRIPPED_TAG("stripped-tag"),

	TIDY_FONT_BUG("tidy-font-bug"),

	TIDY_WHITESPACE_BUG("tidy-whitespace-bug"),

	UNCLOSED_QUOTES_IN_HEADING("unclosed-quotes-in-heading"),

	WIKILINK_IN_EXTLINK("wikilink-in-extlink");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryLinterrorsCategories> set =
				EnumSet.noneOf(AAPIQueryLinterrorsCategories.class);

		public AAPIQueryLinterrorsCategories[] build() {
			return set.toArray(AAPIQueryLinterrorsCategories[]::new);
		}

		public Builder BOGUS_IMAGE_OPTIONS() {
			set.add(BOGUS_IMAGE_OPTIONS);
			return this;
		}

		public Builder DELETABLE_TABLE_TAG() {
			set.add(DELETABLE_TABLE_TAG);
			return this;
		}

		public Builder DUPLICATE_IDS() {
			set.add(DUPLICATE_IDS);
			return this;
		}

		public Builder FOSTERED() {
			set.add(FOSTERED);
			return this;
		}

		public Builder FOSTERED_TRANSPARENT() {
			set.add(FOSTERED_TRANSPARENT);
			return this;
		}

		public Builder HTML5_MISNESTING() {
			set.add(HTML5_MISNESTING);
			return this;
		}

		public Builder LARGE_TABLES() {
			set.add(LARGE_TABLES);
			return this;
		}

		public Builder MISC_TIDY_REPLACEMENT_ISSUES() {
			set.add(MISC_TIDY_REPLACEMENT_ISSUES);
			return this;
		}

		public Builder MISNESTED_TAG() {
			set.add(MISNESTED_TAG);
			return this;
		}

		public Builder MISSING_END_TAG() {
			set.add(MISSING_END_TAG);
			return this;
		}

		public Builder MISSING_END_TAG_IN_HEADING() {
			set.add(MISSING_END_TAG_IN_HEADING);
			return this;
		}

		public Builder MULTI_COLON_ESCAPE() {
			set.add(MULTI_COLON_ESCAPE);
			return this;
		}

		public Builder MULTILINE_HTML_TABLE_IN_LIST() {
			set.add(MULTILINE_HTML_TABLE_IN_LIST);
			return this;
		}

		public Builder MULTIPLE_UNCLOSED_FORMATTING_TAGS() {
			set.add(MULTIPLE_UNCLOSED_FORMATTING_TAGS);
			return this;
		}

		public Builder NIGHT_MODE_UNAWARE_BACKGROUND_COLOR() {
			set.add(NIGHT_MODE_UNAWARE_BACKGROUND_COLOR);
			return this;
		}

		public Builder OBSOLETE_TAG() {
			set.add(OBSOLETE_TAG);
			return this;
		}

		public Builder PWRAP_BUG_WORKAROUND() {
			set.add(PWRAP_BUG_WORKAROUND);
			return this;
		}

		public Builder SELF_CLOSED_TAG() {
			set.add(SELF_CLOSED_TAG);
			return this;
		}

		public Builder STRIPPED_TAG() {
			set.add(STRIPPED_TAG);
			return this;
		}

		public Builder TIDY_FONT_BUG() {
			set.add(TIDY_FONT_BUG);
			return this;
		}

		public Builder TIDY_WHITESPACE_BUG() {
			set.add(TIDY_WHITESPACE_BUG);
			return this;
		}

		public Builder UNCLOSED_QUOTES_IN_HEADING() {
			set.add(UNCLOSED_QUOTES_IN_HEADING);
			return this;
		}

		public Builder WIKILINK_IN_EXTLINK() {
			set.add(WIKILINK_IN_EXTLINK);
			return this;
		}
	}
}
