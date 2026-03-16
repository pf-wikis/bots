package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryAllcategories;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryAlldeletedrevisions;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryAllfileusages;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryAllimages;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryAlllinks;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryAllpages;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryAllredirects;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryAllrevisions;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryAlltransclusions;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryAllusers;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryBacklinks;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryBlocks;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryCategorymembers;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryEditcount;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryEmbeddedin;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryExturlusage;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryFilearchive;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryGadgetcategories;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryGadgets;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryImageusage;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryIwbacklinks;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryLangbacklinks;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryLinterrors;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryLogevents;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryMystashedfiles;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryPagepropnames;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryPageswithprop;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryPrefixsearch;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryProtectedtitles;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryQuerypage;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryRandom;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryRecentchanges;

import io.github.pfwikis.bots.common.api.generated.AAPIQuerySearch;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryTags;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryUsercontribs;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryUsers;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryWatchlist;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryWatchlistraw;

/**<p>Which lists to get.
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryList {

	/**Enumerate all categories.*/
	ALLCATEGORIES("allcategories", AAPIQueryAllcategories.class),

	/**List all deleted revisions by a user or in a namespace.*/
	ALLDELETEDREVISIONS("alldeletedrevisions", AAPIQueryAlldeletedrevisions.class),

	/**List all file usages, including non-existing.*/
	ALLFILEUSAGES("allfileusages", AAPIQueryAllfileusages.class),

	/**Enumerate all images sequentially.*/
	ALLIMAGES("allimages", AAPIQueryAllimages.class),

	/**Enumerate all links that point to a given namespace.*/
	ALLLINKS("alllinks", AAPIQueryAlllinks.class),

	/**Enumerate all pages sequentially in a given namespace.*/
	ALLPAGES("allpages", AAPIQueryAllpages.class),

	/**List all redirects to a namespace.*/
	ALLREDIRECTS("allredirects", AAPIQueryAllredirects.class),

	/**List all revisions.*/
	ALLREVISIONS("allrevisions", AAPIQueryAllrevisions.class),

	/**List all transclusions (pages embedded using &#123;&#123;x&#125;&#125;), including non-existing.*/
	ALLTRANSCLUSIONS("alltransclusions", AAPIQueryAlltransclusions.class),

	/**Enumerate all registered users.*/
	ALLUSERS("allusers", AAPIQueryAllusers.class),

	/**Find all pages that link to the given page.*/
	BACKLINKS("backlinks", AAPIQueryBacklinks.class),

	/**List all blocked users and IP addresses.*/
	BLOCKS("blocks", AAPIQueryBlocks.class),

	/**List all pages in a given category.*/
	CATEGORYMEMBERS("categorymembers", AAPIQueryCategorymembers.class),

	/**List number of edits of given users.*/
	EDITCOUNT("editcount", AAPIQueryEditcount.class),

	/**Find all pages that embed (transclude) the given title.*/
	EMBEDDEDIN("embeddedin", AAPIQueryEmbeddedin.class),

	/**Enumerate pages that contain a given URL.*/
	EXTURLUSAGE("exturlusage", AAPIQueryExturlusage.class),

	/**Enumerate all deleted files sequentially.*/
	FILEARCHIVE("filearchive", AAPIQueryFilearchive.class),

	/**Returns a list of gadget categories.*/
	GADGETCATEGORIES("gadgetcategories", AAPIQueryGadgetcategories.class),

	/**Returns a list of gadgets used on this wiki.*/
	GADGETS("gadgets", AAPIQueryGadgets.class),

	/**Find all pages that use the given image title.*/
	IMAGEUSAGE("imageusage", AAPIQueryImageusage.class),

	/**Find all pages that link to the given interwiki link.*/
	IWBACKLINKS("iwbacklinks", AAPIQueryIwbacklinks.class),

	/**Find all pages that link to the given language link.*/
	LANGBACKLINKS("langbacklinks", AAPIQueryLangbacklinks.class),

	/**Get a list of lint errors*/
	LINTERRORS("linterrors", AAPIQueryLinterrors.class),

	/**Get events from logs.*/
	LOGEVENTS("logevents", AAPIQueryLogevents.class),

	/**Get a list of files in the current user's upload stash.*/
	MYSTASHEDFILES("mystashedfiles", AAPIQueryMystashedfiles.class),

	/**List all page property names in use on the wiki.*/
	PAGEPROPNAMES("pagepropnames", AAPIQueryPagepropnames.class),

	/**List all pages using a given page property.*/
	PAGESWITHPROP("pageswithprop", AAPIQueryPageswithprop.class),

	/**Perform a prefix search for page titles.*/
	PREFIXSEARCH("prefixsearch", AAPIQueryPrefixsearch.class),

	/**List all titles protected from creation.*/
	PROTECTEDTITLES("protectedtitles", AAPIQueryProtectedtitles.class),

	/**Get a list provided by a QueryPage-based special page.*/
	QUERYPAGE("querypage", AAPIQueryQuerypage.class),

	/**Get a set of random pages.*/
	RANDOM("random", AAPIQueryRandom.class),

	/**Enumerate recent changes.*/
	RECENTCHANGES("recentchanges", AAPIQueryRecentchanges.class),

	/**Perform a full text search.*/
	SEARCH("search", AAPIQuerySearch.class),

	/**List change tags.*/
	TAGS("tags", AAPIQueryTags.class),

	/**Get all edits by a user.*/
	USERCONTRIBS("usercontribs", AAPIQueryUsercontribs.class),

	/**Get information about a list of users.*/
	USERS("users", AAPIQueryUsers.class),

	/**Get recent changes to pages in the current user's watchlist.*/
	WATCHLIST("watchlist", AAPIQueryWatchlist.class),

	/**Get all pages on the current user's watchlist.*/
	WATCHLISTRAW("watchlistraw", AAPIQueryWatchlistraw.class);

	private final String jsonValue;

	private final Class<? extends AAPIQueryListModule> type;

	public static interface AAPIQueryListModule extends AAPIModule {}

	public static class AAPIQueryListSubmodule
			extends AAPISubmodule<AAPIQueryList, AAPIQueryListModule> {
		public AAPIQueryListSubmodule(AAPIQueryList k, AAPIQueryListModule v) {
			super(k, v);
		}
	}

	public static AAPIQueryListSubmodule createSubmodule(AAPIQueryListModule module) {
		return new AAPIQueryListSubmodule(
				switch (module) {
					case AAPIQueryAllcategories g -> ALLCATEGORIES;

					case AAPIQueryAlldeletedrevisions g -> ALLDELETEDREVISIONS;

					case AAPIQueryAllfileusages g -> ALLFILEUSAGES;

					case AAPIQueryAllimages g -> ALLIMAGES;

					case AAPIQueryAlllinks g -> ALLLINKS;

					case AAPIQueryAllpages g -> ALLPAGES;

					case AAPIQueryAllredirects g -> ALLREDIRECTS;

					case AAPIQueryAllrevisions g -> ALLREVISIONS;

					case AAPIQueryAlltransclusions g -> ALLTRANSCLUSIONS;

					case AAPIQueryAllusers g -> ALLUSERS;

					case AAPIQueryBacklinks g -> BACKLINKS;

					case AAPIQueryBlocks g -> BLOCKS;

					case AAPIQueryCategorymembers g -> CATEGORYMEMBERS;

					case AAPIQueryEditcount g -> EDITCOUNT;

					case AAPIQueryEmbeddedin g -> EMBEDDEDIN;

					case AAPIQueryExturlusage g -> EXTURLUSAGE;

					case AAPIQueryFilearchive g -> FILEARCHIVE;

					case AAPIQueryGadgetcategories g -> GADGETCATEGORIES;

					case AAPIQueryGadgets g -> GADGETS;

					case AAPIQueryImageusage g -> IMAGEUSAGE;

					case AAPIQueryIwbacklinks g -> IWBACKLINKS;

					case AAPIQueryLangbacklinks g -> LANGBACKLINKS;

					case AAPIQueryLinterrors g -> LINTERRORS;

					case AAPIQueryLogevents g -> LOGEVENTS;

					case AAPIQueryMystashedfiles g -> MYSTASHEDFILES;

					case AAPIQueryPagepropnames g -> PAGEPROPNAMES;

					case AAPIQueryPageswithprop g -> PAGESWITHPROP;

					case AAPIQueryPrefixsearch g -> PREFIXSEARCH;

					case AAPIQueryProtectedtitles g -> PROTECTEDTITLES;

					case AAPIQueryQuerypage g -> QUERYPAGE;

					case AAPIQueryRandom g -> RANDOM;

					case AAPIQueryRecentchanges g -> RECENTCHANGES;

					case AAPIQuerySearch g -> SEARCH;

					case AAPIQueryTags g -> TAGS;

					case AAPIQueryUsercontribs g -> USERCONTRIBS;

					case AAPIQueryUsers g -> USERS;

					case AAPIQueryWatchlist g -> WATCHLIST;

					case AAPIQueryWatchlistraw g -> WATCHLISTRAW;

					default -> throw new IllegalArgumentException();
				},
				module);
	}

	public static List<AAPIQueryListSubmodule> createSubmodule(AAPIQueryListModule... modules) {
		var result = new ArrayList<AAPIQueryListSubmodule>();
		for (var v : modules) {
			result.add(createSubmodule(v));
		}
		return result;
	}
}
