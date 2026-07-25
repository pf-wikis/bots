package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Which repository properties to get (properties available may vary on other wikis).
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryFilerepoinfoProp {

	/**URL to the repository API - helpful for getting image info from the host.*/
	APIURL("apiurl"),

	/**Repository wiki's <var><a href="/w/index.php?title=Mw:Special:MyLanguage/Manual:$wgArticlePath&amp;action=edit&amp;redlink=1" class="new" title="Mw:Special:MyLanguage/Manual:$wgArticlePath (page does not exist)">$wgArticlePath</a></var> or equivalent.*/
	ARTICLEPATH("articlepath"),

	/**Whether files can be uploaded to this repository, e.g. via CORS and shared authentication.*/
	CANUPLOAD("canUpload"),

	/**<span class="apihelp-empty">(no description)</span>*/
	DESCRIPTIONCACHEEXPIRY("descriptionCacheExpiry"),

	/**The human-readable name of the repository wiki.*/
	DISPLAYNAME("displayname"),

	/**Repository wiki's favicon URL, from <var><a href="/w/index.php?title=Mw:Special:MyLanguage/Manual:$wgFavicon&amp;action=edit&amp;redlink=1" class="new" title="Mw:Special:MyLanguage/Manual:$wgFavicon (page does not exist)">$wgFavicon</a></var>.*/
	FAVICON("favicon"),

	/**Whether file description pages are fetched from this repository when viewing local file description pages.*/
	FETCHDESCRIPTION("fetchDescription"),

	/**Whether file names implicitly start with a capital letter.*/
	INITIALCAPITAL("initialCapital"),

	/**Whether that repository is the local one or not.*/
	LOCAL("local"),

	/**The key of the repository - used in e.g. <var><a href="/w/index.php?title=Mw:Special:MyLanguage/Manual:$wgForeignFileRepos&amp;action=edit&amp;redlink=1" class="new" title="Mw:Special:MyLanguage/Manual:$wgForeignFileRepos (page does not exist)">$wgForeignFileRepos</a></var> and <a href="/wiki/Special:ApiHelp/query%2Bimageinfo" title="Special:ApiHelp/query+imageinfo">imageinfo</a> return values.*/
	NAME("name"),

	/**Root URL path for image paths.*/
	ROOTURL("rootUrl"),

	/**Root URL path for the repository wiki's MediaWiki installation.*/
	SCRIPTDIRURL("scriptDirUrl"),

	/**Repository wiki's <var><a href="/w/index.php?title=Mw:Special:MyLanguage/Manual:$wgServer&amp;action=edit&amp;redlink=1" class="new" title="Mw:Special:MyLanguage/Manual:$wgServer (page does not exist)">$wgServer</a></var> or equivalent.*/
	SERVER("server"),

	/**Root URL path for thumbnail paths.*/
	THUMBURL("thumbUrl"),

	/**Public zone URL path.*/
	URL("url");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryFilerepoinfoProp> set =
				EnumSet.noneOf(AAPIQueryFilerepoinfoProp.class);

		public AAPIQueryFilerepoinfoProp[] build() {
			return set.toArray(AAPIQueryFilerepoinfoProp[]::new);
		}

		/**URL to the repository API - helpful for getting image info from the host.*/
		public Builder APIURL() {
			set.add(APIURL);
			return this;
		}

		/**Repository wiki's <var><a href="/w/index.php?title=Mw:Special:MyLanguage/Manual:$wgArticlePath&amp;action=edit&amp;redlink=1" class="new" title="Mw:Special:MyLanguage/Manual:$wgArticlePath (page does not exist)">$wgArticlePath</a></var> or equivalent.*/
		public Builder ARTICLEPATH() {
			set.add(ARTICLEPATH);
			return this;
		}

		/**Whether files can be uploaded to this repository, e.g. via CORS and shared authentication.*/
		public Builder CANUPLOAD() {
			set.add(CANUPLOAD);
			return this;
		}

		/**<span class="apihelp-empty">(no description)</span>*/
		public Builder DESCRIPTIONCACHEEXPIRY() {
			set.add(DESCRIPTIONCACHEEXPIRY);
			return this;
		}

		/**The human-readable name of the repository wiki.*/
		public Builder DISPLAYNAME() {
			set.add(DISPLAYNAME);
			return this;
		}

		/**Repository wiki's favicon URL, from <var><a href="/w/index.php?title=Mw:Special:MyLanguage/Manual:$wgFavicon&amp;action=edit&amp;redlink=1" class="new" title="Mw:Special:MyLanguage/Manual:$wgFavicon (page does not exist)">$wgFavicon</a></var>.*/
		public Builder FAVICON() {
			set.add(FAVICON);
			return this;
		}

		/**Whether file description pages are fetched from this repository when viewing local file description pages.*/
		public Builder FETCHDESCRIPTION() {
			set.add(FETCHDESCRIPTION);
			return this;
		}

		/**Whether file names implicitly start with a capital letter.*/
		public Builder INITIALCAPITAL() {
			set.add(INITIALCAPITAL);
			return this;
		}

		/**Whether that repository is the local one or not.*/
		public Builder LOCAL() {
			set.add(LOCAL);
			return this;
		}

		/**The key of the repository - used in e.g. <var><a href="/w/index.php?title=Mw:Special:MyLanguage/Manual:$wgForeignFileRepos&amp;action=edit&amp;redlink=1" class="new" title="Mw:Special:MyLanguage/Manual:$wgForeignFileRepos (page does not exist)">$wgForeignFileRepos</a></var> and <a href="/wiki/Special:ApiHelp/query%2Bimageinfo" title="Special:ApiHelp/query+imageinfo">imageinfo</a> return values.*/
		public Builder NAME() {
			set.add(NAME);
			return this;
		}

		/**Root URL path for image paths.*/
		public Builder ROOTURL() {
			set.add(ROOTURL);
			return this;
		}

		/**Root URL path for the repository wiki's MediaWiki installation.*/
		public Builder SCRIPTDIRURL() {
			set.add(SCRIPTDIRURL);
			return this;
		}

		/**Repository wiki's <var><a href="/w/index.php?title=Mw:Special:MyLanguage/Manual:$wgServer&amp;action=edit&amp;redlink=1" class="new" title="Mw:Special:MyLanguage/Manual:$wgServer (page does not exist)">$wgServer</a></var> or equivalent.*/
		public Builder SERVER() {
			set.add(SERVER);
			return this;
		}

		/**Root URL path for thumbnail paths.*/
		public Builder THUMBURL() {
			set.add(THUMBURL);
			return this;
		}

		/**Public zone URL path.*/
		public Builder URL() {
			set.add(URL);
			return this;
		}
	}
}
