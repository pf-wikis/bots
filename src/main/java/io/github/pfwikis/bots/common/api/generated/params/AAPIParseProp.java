package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
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
}
