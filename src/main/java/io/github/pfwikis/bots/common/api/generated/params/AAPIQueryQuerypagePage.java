package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**The name of the special page. Note, this is case-sensitive.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryQuerypagePage {
	ANCIENTPAGES("Ancientpages"),

	BROKENREDIRECTS("BrokenRedirects"),

	DEADENDPAGES("Deadendpages"),

	DOUBLEREDIRECTS("DoubleRedirects"),

	FEWESTREVISIONS("Fewestrevisions"),

	GADGETUSAGE("GadgetUsage"),

	LISTDUPLICATEDFILES("ListDuplicatedFiles"),

	LISTREDIRECTS("Listredirects"),

	LONELYPAGES("Lonelypages"),

	LONGPAGES("Longpages"),

	MEDIASTATISTICS("MediaStatistics"),

	MOSTCATEGORIES("Mostcategories"),

	MOSTIMAGES("Mostimages"),

	MOSTINTERWIKIS("Mostinterwikis"),

	MOSTLINKED("Mostlinked"),

	MOSTLINKEDCATEGORIES("Mostlinkedcategories"),

	MOSTLINKEDTEMPLATES("Mostlinkedtemplates"),

	MOSTREVISIONS("Mostrevisions"),

	SHORTPAGES("Shortpages"),

	UNCATEGORIZEDCATEGORIES("Uncategorizedcategories"),

	UNCATEGORIZEDIMAGES("Uncategorizedimages"),

	UNCATEGORIZEDPAGES("Uncategorizedpages"),

	UNCATEGORIZEDTEMPLATES("Uncategorizedtemplates"),

	UNUSEDCATEGORIES("Unusedcategories"),

	UNUSEDIMAGES("Unusedimages"),

	UNUSEDTEMPLATES("Unusedtemplates"),

	UNWATCHEDPAGES("Unwatchedpages"),

	WANTEDCATEGORIES("Wantedcategories"),

	WANTEDFILES("Wantedfiles"),

	WANTEDPAGES("Wantedpages"),

	WANTEDTEMPLATES("Wantedtemplates"),

	WITHOUTINTERWIKI("Withoutinterwiki");

	private final String jsonValue;
}
