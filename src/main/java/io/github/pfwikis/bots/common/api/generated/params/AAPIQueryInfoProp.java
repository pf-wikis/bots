package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Which additional properties to get:
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryInfoProp {

	/**The prefixed title of the <a href="/w/index.php?title=Mw:Special:MyLanguage/Help:Associated_pages&amp;action=edit&amp;redlink=1" class="new" title="Mw:Special:MyLanguage/Help:Associated pages (page does not exist)">associated subject or talk page</a>.*/
	ASSOCIATEDPAGE("associatedpage"),

	/**Gives the manner in which the page title is actually displayed.*/
	DISPLAYTITLE("displaytitle"),

	/**Gives the intro messages that should be shown to the user while editing this page or revision, as HTML.*/
	EDITINTRO("editintro"),

	/**Gives the additional CSS classes (e.g. link colors) used for links to this page if they were to appear on the page named by <var>inlinkcontext</var>.*/
	LINKCLASSES("linkclasses"),

	/**The watchlist notification timestamp of each page.*/
	NOTIFICATIONTIMESTAMP("notificationtimestamp"),

	/**Gives the content to be shown in the editor when the page does not exist or while adding a new section.*/
	PRELOADCONTENT("preloadcontent"),

	/**List the protection level of each page.*/
	PROTECTION("protection"),

	/**The page ID of the parent page for each talk page.*/
	SUBJECTID("subjectid"),

	/**The page ID of the talk page for each non-talk page.*/
	TALKID("talkid"),

	/**Gives a full URL, an edit URL, and the canonical URL for each page.*/
	URL("url"),

	/**Gives the display title in all variants of the site content language.*/
	VARIANTTITLES("varianttitles"),

	/**The number of watchers of each page who have visited recent edits to that page, if allowed.*/
	VISITINGWATCHERS("visitingwatchers"),

	/**List the watched status of each page.*/
	WATCHED("watched"),

	/**The number of watchers, if allowed.*/
	WATCHERS("watchers"),

	/**<span class="apihelp-deprecated">Deprecated.</span> Gives the text returned by EditFormPreloadText. Use <kbd>preloadcontent</kbd> instead, which supports other kinds of preloaded text too.*/
	PRELOAD("preload"),

	/**<span class="apihelp-deprecated">Deprecated.</span> Whether the user can read this page. Use <kbd>intestactions=read</kbd> instead.*/
	READABLE("readable");

	private final String jsonValue;
}
