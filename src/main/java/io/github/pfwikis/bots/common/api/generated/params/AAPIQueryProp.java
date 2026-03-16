package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryCategories;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryCategoryinfo;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryCirrusbuilddoc;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryCirruscompsuggestbuilddoc;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryCirrusdoc;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryContributors;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryDeletedrevisions;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryDuplicatefiles;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryExtlinks;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryExtracts;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryFileusage;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryImageinfo;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryImages;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryInfo;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryIwlinks;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryLanglinks;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryLinks;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryLinkshere;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryPageimages;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryPageprops;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryRedirects;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryRevisions;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryStashimageinfo;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryTemplates;

import io.github.pfwikis.bots.common.api.generated.AAPIQueryTranscludedin;

/**<p>Which properties to get for the queried pages.
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryProp {

	/**List all categories the pages belong to.*/
	CATEGORIES("categories", AAPIQueryCategories.class),

	/**Returns information about the given categories.*/
	CATEGORYINFO("categoryinfo", AAPIQueryCategoryinfo.class),

	/**Dump of a CirrusSearch article document from the database servers*/
	CIRRUSBUILDDOC("cirrusbuilddoc", AAPIQueryCirrusbuilddoc.class),

	/**Dump of the document used by the completion suggester*/
	CIRRUSCOMPSUGGESTBUILDDOC(
			"cirruscompsuggestbuilddoc", AAPIQueryCirruscompsuggestbuilddoc.class),

	/**Dump of a CirrusSearch article document from the search servers*/
	CIRRUSDOC("cirrusdoc", AAPIQueryCirrusdoc.class),

	/**Get the list of logged-in contributors and the count of logged-out contributors to a page.*/
	CONTRIBUTORS("contributors", AAPIQueryContributors.class),

	/**Get deleted revision information.*/
	DELETEDREVISIONS("deletedrevisions", AAPIQueryDeletedrevisions.class),

	/**List all files that are duplicates of the given files based on hash values.*/
	DUPLICATEFILES("duplicatefiles", AAPIQueryDuplicatefiles.class),

	/**Returns all external URLs (not interwikis) from the given pages.*/
	EXTLINKS("extlinks", AAPIQueryExtlinks.class),

	/**Returns plain-text or limited HTML extracts of the given pages.*/
	EXTRACTS("extracts", AAPIQueryExtracts.class),

	/**Find all pages that use the given files.*/
	FILEUSAGE("fileusage", AAPIQueryFileusage.class),

	/**Returns file information and upload history.*/
	IMAGEINFO("imageinfo", AAPIQueryImageinfo.class),

	/**Returns all files contained on the given pages.*/
	IMAGES("images", AAPIQueryImages.class),

	/**Get basic page information.*/
	INFO("info", AAPIQueryInfo.class),

	/**Returns all interwiki links from the given pages.*/
	IWLINKS("iwlinks", AAPIQueryIwlinks.class),

	/**Returns all interlanguage links from the given pages.*/
	LANGLINKS("langlinks", AAPIQueryLanglinks.class),

	/**Returns all links from the given pages.*/
	LINKS("links", AAPIQueryLinks.class),

	/**Find all pages that link to the given pages.*/
	LINKSHERE("linkshere", AAPIQueryLinkshere.class),

	/**Returns information about images on the page, such as thumbnail and presence of photos.*/
	PAGEIMAGES("pageimages", AAPIQueryPageimages.class),

	/**Get various page properties defined in the page content.*/
	PAGEPROPS("pageprops", AAPIQueryPageprops.class),

	/**Returns all redirects to the given pages.*/
	REDIRECTS("redirects", AAPIQueryRedirects.class),

	/**Get revision information.*/
	REVISIONS("revisions", AAPIQueryRevisions.class),

	/**Returns file information for stashed files.*/
	STASHIMAGEINFO("stashimageinfo", AAPIQueryStashimageinfo.class),

	/**Returns all pages transcluded on the given pages.*/
	TEMPLATES("templates", AAPIQueryTemplates.class),

	/**Find all pages that transclude the given pages.*/
	TRANSCLUDEDIN("transcludedin", AAPIQueryTranscludedin.class);

	private final String jsonValue;

	private final Class<? extends AAPIQueryPropModule> type;

	public static interface AAPIQueryPropModule extends AAPIModule {}

	public static class AAPIQueryPropSubmodule
			extends AAPISubmodule<AAPIQueryProp, AAPIQueryPropModule> {
		public AAPIQueryPropSubmodule(AAPIQueryProp k, AAPIQueryPropModule v) {
			super(k, v);
		}
	}

	public static AAPIQueryPropSubmodule createSubmodule(AAPIQueryPropModule module) {
		return new AAPIQueryPropSubmodule(
				switch (module) {
					case AAPIQueryCategories g -> CATEGORIES;

					case AAPIQueryCategoryinfo g -> CATEGORYINFO;

					case AAPIQueryCirrusbuilddoc g -> CIRRUSBUILDDOC;

					case AAPIQueryCirruscompsuggestbuilddoc g -> CIRRUSCOMPSUGGESTBUILDDOC;

					case AAPIQueryCirrusdoc g -> CIRRUSDOC;

					case AAPIQueryContributors g -> CONTRIBUTORS;

					case AAPIQueryDeletedrevisions g -> DELETEDREVISIONS;

					case AAPIQueryDuplicatefiles g -> DUPLICATEFILES;

					case AAPIQueryExtlinks g -> EXTLINKS;

					case AAPIQueryExtracts g -> EXTRACTS;

					case AAPIQueryFileusage g -> FILEUSAGE;

					case AAPIQueryImageinfo g -> IMAGEINFO;

					case AAPIQueryImages g -> IMAGES;

					case AAPIQueryInfo g -> INFO;

					case AAPIQueryIwlinks g -> IWLINKS;

					case AAPIQueryLanglinks g -> LANGLINKS;

					case AAPIQueryLinks g -> LINKS;

					case AAPIQueryLinkshere g -> LINKSHERE;

					case AAPIQueryPageimages g -> PAGEIMAGES;

					case AAPIQueryPageprops g -> PAGEPROPS;

					case AAPIQueryRedirects g -> REDIRECTS;

					case AAPIQueryRevisions g -> REVISIONS;

					case AAPIQueryStashimageinfo g -> STASHIMAGEINFO;

					case AAPIQueryTemplates g -> TEMPLATES;

					case AAPIQueryTranscludedin g -> TRANSCLUDEDIN;

					default -> throw new IllegalArgumentException();
				},
				module);
	}

	public static List<AAPIQueryPropSubmodule> createSubmodule(AAPIQueryPropModule... modules) {
		var result = new ArrayList<AAPIQueryPropSubmodule>();
		for (var v : modules) {
			result.add(createSubmodule(v));
		}
		return result;
	}
}
