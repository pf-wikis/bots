package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Which pieces of information to get:
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIParseProp {

	/**Gives the categories in the parsed wikitext.*/
	CATEGORIES("categories"),

	/**Gives the HTML version of the categories.*/
	CATEGORIESHTML("categorieshtml"),

	/**Adds the title of the parsed wikitext.*/
	DISPLAYTITLE("displaytitle"),

	/**Gives the JavaScript configuration variables specific to the page as a JSON string.*/
	ENCODEDJSCONFIGVARS("encodedjsconfigvars"),

	/**Gives the external links in the parsed wikitext.*/
	EXTERNALLINKS("externallinks"),

	/**Gives parsed doctype, opening <code>&lt;html&gt;</code>, <code>&lt;head&gt;</code> element and opening <code>&lt;body&gt;</code> of the page.*/
	HEADHTML("headhtml"),

	/**Gives the images in the parsed wikitext.*/
	IMAGES("images"),

	/**Gives the HTML of page status indicators used on the page.*/
	INDICATORS("indicators"),

	/**Gives interwiki links in the parsed wikitext.*/
	IWLINKS("iwlinks"),

	/**Gives the JavaScript configuration variables specific to the page. To apply, use <code>mw.config.set()</code>.*/
	JSCONFIGVARS("jsconfigvars"),

	/**Gives the language links in the parsed wikitext.*/
	LANGLINKS("langlinks"),

	/**Gives the limit report in a structured way. Gives no data, when <var>disablelimitreport</var> is set.*/
	LIMITREPORTDATA("limitreportdata"),

	/**Gives the HTML version of the limit report. Gives no data, when <var>disablelimitreport</var> is set.*/
	LIMITREPORTHTML("limitreporthtml"),

	/**Gives the internal links in the parsed wikitext.*/
	LINKS("links"),

	/**Gives the ResourceLoader modules used on the page. To load, use <code>mw.loader.using()</code>. Either <kbd>jsconfigvars</kbd> or <kbd>encodedjsconfigvars</kbd> must be requested jointly with <kbd>modules</kbd>.*/
	MODULES("modules"),

	/**The XML parse tree of revision content (requires content model <code>wikitext</code>)*/
	PARSETREE("parsetree"),

	/**Gives the warnings that occurred while parsing content (as wikitext).*/
	PARSEWARNINGS("parsewarnings"),

	/**Gives the warnings that occurred while parsing content (as HTML).*/
	PARSEWARNINGSHTML("parsewarningshtml"),

	/**Gives various properties defined in the parsed wikitext.*/
	PROPERTIES("properties"),

	/**Adds the revision ID of the parsed page.*/
	REVID("revid"),

	/**Gives the sections in the parsed wikitext.*/
	SECTIONS("sections"),

	/**Adds the page subtitle for the parsed page.*/
	SUBTITLE("subtitle"),

	/**Gives the templates in the parsed wikitext.*/
	TEMPLATES("templates"),

	/**Gives the parsed text of the wikitext.*/
	TEXT("text"),

	/**Gives the original wikitext that was parsed.*/
	WIKITEXT("wikitext"),

	/**<span class="apihelp-deprecated">Deprecated.</span> Gives items to put in the <code>&lt;head&gt;</code> of the page.*/
	HEADITEMS("headitems");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIParseProp> set = EnumSet.noneOf(AAPIParseProp.class);

		public AAPIParseProp[] build() {
			return set.toArray(AAPIParseProp[]::new);
		}

		/**Gives the categories in the parsed wikitext.*/
		public Builder CATEGORIES() {
			set.add(CATEGORIES);
			return this;
		}

		/**Gives the HTML version of the categories.*/
		public Builder CATEGORIESHTML() {
			set.add(CATEGORIESHTML);
			return this;
		}

		/**Adds the title of the parsed wikitext.*/
		public Builder DISPLAYTITLE() {
			set.add(DISPLAYTITLE);
			return this;
		}

		/**Gives the JavaScript configuration variables specific to the page as a JSON string.*/
		public Builder ENCODEDJSCONFIGVARS() {
			set.add(ENCODEDJSCONFIGVARS);
			return this;
		}

		/**Gives the external links in the parsed wikitext.*/
		public Builder EXTERNALLINKS() {
			set.add(EXTERNALLINKS);
			return this;
		}

		/**Gives parsed doctype, opening <code>&lt;html&gt;</code>, <code>&lt;head&gt;</code> element and opening <code>&lt;body&gt;</code> of the page.*/
		public Builder HEADHTML() {
			set.add(HEADHTML);
			return this;
		}

		/**Gives the images in the parsed wikitext.*/
		public Builder IMAGES() {
			set.add(IMAGES);
			return this;
		}

		/**Gives the HTML of page status indicators used on the page.*/
		public Builder INDICATORS() {
			set.add(INDICATORS);
			return this;
		}

		/**Gives interwiki links in the parsed wikitext.*/
		public Builder IWLINKS() {
			set.add(IWLINKS);
			return this;
		}

		/**Gives the JavaScript configuration variables specific to the page. To apply, use <code>mw.config.set()</code>.*/
		public Builder JSCONFIGVARS() {
			set.add(JSCONFIGVARS);
			return this;
		}

		/**Gives the language links in the parsed wikitext.*/
		public Builder LANGLINKS() {
			set.add(LANGLINKS);
			return this;
		}

		/**Gives the limit report in a structured way. Gives no data, when <var>disablelimitreport</var> is set.*/
		public Builder LIMITREPORTDATA() {
			set.add(LIMITREPORTDATA);
			return this;
		}

		/**Gives the HTML version of the limit report. Gives no data, when <var>disablelimitreport</var> is set.*/
		public Builder LIMITREPORTHTML() {
			set.add(LIMITREPORTHTML);
			return this;
		}

		/**Gives the internal links in the parsed wikitext.*/
		public Builder LINKS() {
			set.add(LINKS);
			return this;
		}

		/**Gives the ResourceLoader modules used on the page. To load, use <code>mw.loader.using()</code>. Either <kbd>jsconfigvars</kbd> or <kbd>encodedjsconfigvars</kbd> must be requested jointly with <kbd>modules</kbd>.*/
		public Builder MODULES() {
			set.add(MODULES);
			return this;
		}

		/**The XML parse tree of revision content (requires content model <code>wikitext</code>)*/
		public Builder PARSETREE() {
			set.add(PARSETREE);
			return this;
		}

		/**Gives the warnings that occurred while parsing content (as wikitext).*/
		public Builder PARSEWARNINGS() {
			set.add(PARSEWARNINGS);
			return this;
		}

		/**Gives the warnings that occurred while parsing content (as HTML).*/
		public Builder PARSEWARNINGSHTML() {
			set.add(PARSEWARNINGSHTML);
			return this;
		}

		/**Gives various properties defined in the parsed wikitext.*/
		public Builder PROPERTIES() {
			set.add(PROPERTIES);
			return this;
		}

		/**Adds the revision ID of the parsed page.*/
		public Builder REVID() {
			set.add(REVID);
			return this;
		}

		/**Gives the sections in the parsed wikitext.*/
		public Builder SECTIONS() {
			set.add(SECTIONS);
			return this;
		}

		/**Adds the page subtitle for the parsed page.*/
		public Builder SUBTITLE() {
			set.add(SUBTITLE);
			return this;
		}

		/**Gives the templates in the parsed wikitext.*/
		public Builder TEMPLATES() {
			set.add(TEMPLATES);
			return this;
		}

		/**Gives the parsed text of the wikitext.*/
		public Builder TEXT() {
			set.add(TEXT);
			return this;
		}

		/**Gives the original wikitext that was parsed.*/
		public Builder WIKITEXT() {
			set.add(WIKITEXT);
			return this;
		}

		/**<span class="apihelp-deprecated">Deprecated.</span> Gives items to put in the <code>&lt;head&gt;</code> of the page.*/
		public Builder HEADITEMS() {
			set.add(HEADITEMS);
			return this;
		}
	}
}
