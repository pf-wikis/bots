package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
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
}
