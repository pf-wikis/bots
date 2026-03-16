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

import io.github.pfwikis.bots.common.api.generated.AAPIQueryBacklinks;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryCategories;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryCategorymembers;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryDeletedrevisions;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryDuplicatefiles;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryEmbeddedin;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryExturlusage;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryFileusage;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryImages;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryImageusage;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryIwbacklinks;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryLangbacklinks;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryLinks;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryLinkshere;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryPageswithprop;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryPrefixsearch;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryProtectedtitles;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryQuerypage;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryRandom;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryRecentchanges;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryRedirects;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryRevisions;

import io.github.pfwikis.bots.common.api.generated.AAPIQuerySearch;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryTemplates;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryTranscludedin;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryWatchlist;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryWatchlistraw;

/**<p>Get the list of pages to work on by executing the specified query module.
 * </p><p><strong>Note:</strong> Generator parameter names must be prefixed with a "g", see examples.
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryGenerator {

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

	/**Find all pages that link to the given page.*/
	BACKLINKS("backlinks", AAPIQueryBacklinks.class),

	/**List all categories the pages belong to.*/
	CATEGORIES("categories", AAPIQueryCategories.class),

	/**List all pages in a given category.*/
	CATEGORYMEMBERS("categorymembers", AAPIQueryCategorymembers.class),

	/**Get deleted revision information.*/
	DELETEDREVISIONS("deletedrevisions", AAPIQueryDeletedrevisions.class),

	/**List all files that are duplicates of the given files based on hash values.*/
	DUPLICATEFILES("duplicatefiles", AAPIQueryDuplicatefiles.class),

	/**Find all pages that embed (transclude) the given title.*/
	EMBEDDEDIN("embeddedin", AAPIQueryEmbeddedin.class),

	/**Enumerate pages that contain a given URL.*/
	EXTURLUSAGE("exturlusage", AAPIQueryExturlusage.class),

	/**Find all pages that use the given files.*/
	FILEUSAGE("fileusage", AAPIQueryFileusage.class),

	/**Returns all files contained on the given pages.*/
	IMAGES("images", AAPIQueryImages.class),

	/**Find all pages that use the given image title.*/
	IMAGEUSAGE("imageusage", AAPIQueryImageusage.class),

	/**Find all pages that link to the given interwiki link.*/
	IWBACKLINKS("iwbacklinks", AAPIQueryIwbacklinks.class),

	/**Find all pages that link to the given language link.*/
	LANGBACKLINKS("langbacklinks", AAPIQueryLangbacklinks.class),

	/**Returns all links from the given pages.*/
	LINKS("links", AAPIQueryLinks.class),

	/**Find all pages that link to the given pages.*/
	LINKSHERE("linkshere", AAPIQueryLinkshere.class),

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

	/**Returns all redirects to the given pages.*/
	REDIRECTS("redirects", AAPIQueryRedirects.class),

	/**Get revision information.*/
	REVISIONS("revisions", AAPIQueryRevisions.class),

	/**Perform a full text search.*/
	SEARCH("search", AAPIQuerySearch.class),

	/**Returns all pages transcluded on the given pages.*/
	TEMPLATES("templates", AAPIQueryTemplates.class),

	/**Find all pages that transclude the given pages.*/
	TRANSCLUDEDIN("transcludedin", AAPIQueryTranscludedin.class),

	/**Get recent changes to pages in the current user's watchlist.*/
	WATCHLIST("watchlist", AAPIQueryWatchlist.class),

	/**Get all pages on the current user's watchlist.*/
	WATCHLISTRAW("watchlistraw", AAPIQueryWatchlistraw.class);

	private final String jsonValue;

	private final Class<? extends AAPIQueryGeneratorModule> type;

	public static interface AAPIQueryGeneratorModule extends AAPIModule {}

	public static class AAPIQueryGeneratorSubmodule
			extends AAPISubmodule<AAPIQueryGenerator, AAPIQueryGeneratorModule> {
		public AAPIQueryGeneratorSubmodule(AAPIQueryGenerator k, AAPIQueryGeneratorModule v) {
			super(k, v);
		}
	}

	public static AAPIQueryGeneratorSubmodule createSubmodule(AAPIQueryGeneratorModule module) {
		return new AAPIQueryGeneratorSubmodule(
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

					case AAPIQueryBacklinks g -> BACKLINKS;

					case AAPIQueryCategories g -> CATEGORIES;

					case AAPIQueryCategorymembers g -> CATEGORYMEMBERS;

					case AAPIQueryDeletedrevisions g -> DELETEDREVISIONS;

					case AAPIQueryDuplicatefiles g -> DUPLICATEFILES;

					case AAPIQueryEmbeddedin g -> EMBEDDEDIN;

					case AAPIQueryExturlusage g -> EXTURLUSAGE;

					case AAPIQueryFileusage g -> FILEUSAGE;

					case AAPIQueryImages g -> IMAGES;

					case AAPIQueryImageusage g -> IMAGEUSAGE;

					case AAPIQueryIwbacklinks g -> IWBACKLINKS;

					case AAPIQueryLangbacklinks g -> LANGBACKLINKS;

					case AAPIQueryLinks g -> LINKS;

					case AAPIQueryLinkshere g -> LINKSHERE;

					case AAPIQueryPageswithprop g -> PAGESWITHPROP;

					case AAPIQueryPrefixsearch g -> PREFIXSEARCH;

					case AAPIQueryProtectedtitles g -> PROTECTEDTITLES;

					case AAPIQueryQuerypage g -> QUERYPAGE;

					case AAPIQueryRandom g -> RANDOM;

					case AAPIQueryRecentchanges g -> RECENTCHANGES;

					case AAPIQueryRedirects g -> REDIRECTS;

					case AAPIQueryRevisions g -> REVISIONS;

					case AAPIQuerySearch g -> SEARCH;

					case AAPIQueryTemplates g -> TEMPLATES;

					case AAPIQueryTranscludedin g -> TRANSCLUDEDIN;

					case AAPIQueryWatchlist g -> WATCHLIST;

					case AAPIQueryWatchlistraw g -> WATCHLISTRAW;

					default -> throw new IllegalArgumentException();
				},
				module);
	}

	public static List<AAPIQueryGeneratorSubmodule> createSubmodule(
			AAPIQueryGeneratorModule... modules) {
		var result = new ArrayList<AAPIQueryGeneratorSubmodule>();
		for (var v : modules) {
			result.add(createSubmodule(v));
		}
		return result;
	}
}
