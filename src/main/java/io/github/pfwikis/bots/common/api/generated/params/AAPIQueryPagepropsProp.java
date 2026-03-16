package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Only list these page properties (<kbd><a href="/wiki/Special:ApiHelp/query%2Bpagepropnames" title="Special:ApiHelp/query+pagepropnames">action=query&amp;list=pagepropnames</a></kbd> returns page property names in use). Useful for checking whether pages use a certain page property.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryPagepropsProp {
	PFDEFAULTFORM("PFDefaultForm"),

	PAGEFORMSTEMPLATEPARAMS("PageFormsTemplateParams"),

	DEFAULTSORT("defaultsort"),

	DISPLAYTITLE("displaytitle"),

	EXPECTUNUSEDCATEGORY("expectunusedcategory"),

	HIDDENCAT("hiddencat"),

	NEWSECTIONLINK("newsectionlink"),

	NOEDITSECTION("noeditsection"),

	NOINDEX("noindex"),

	NOTOC("notoc"),

	PAGE_IMAGE_FREE("page_image_free"),

	TEMPLATEDATA("templatedata"),

	TOC("toc");

	private final String jsonValue;
}
