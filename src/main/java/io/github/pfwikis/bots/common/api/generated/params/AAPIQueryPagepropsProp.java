package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
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

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryPagepropsProp> set = EnumSet.noneOf(AAPIQueryPagepropsProp.class);

		public AAPIQueryPagepropsProp[] build() {
			return set.toArray(AAPIQueryPagepropsProp[]::new);
		}

		public Builder PFDEFAULTFORM() {
			set.add(PFDEFAULTFORM);
			return this;
		}

		public Builder PAGEFORMSTEMPLATEPARAMS() {
			set.add(PAGEFORMSTEMPLATEPARAMS);
			return this;
		}

		public Builder DEFAULTSORT() {
			set.add(DEFAULTSORT);
			return this;
		}

		public Builder DISPLAYTITLE() {
			set.add(DISPLAYTITLE);
			return this;
		}

		public Builder EXPECTUNUSEDCATEGORY() {
			set.add(EXPECTUNUSEDCATEGORY);
			return this;
		}

		public Builder HIDDENCAT() {
			set.add(HIDDENCAT);
			return this;
		}

		public Builder NEWSECTIONLINK() {
			set.add(NEWSECTIONLINK);
			return this;
		}

		public Builder NOEDITSECTION() {
			set.add(NOEDITSECTION);
			return this;
		}

		public Builder NOINDEX() {
			set.add(NOINDEX);
			return this;
		}

		public Builder NOTOC() {
			set.add(NOTOC);
			return this;
		}

		public Builder PAGE_IMAGE_FREE() {
			set.add(PAGE_IMAGE_FREE);
			return this;
		}

		public Builder TEMPLATEDATA() {
			set.add(TEMPLATEDATA);
			return this;
		}

		public Builder TOC() {
			set.add(TOC);
			return this;
		}
	}
}
