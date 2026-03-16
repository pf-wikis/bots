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

	/**Whether files can be uploaded to this repository, e.g. via CORS and shared authentication.*/
	CANUPLOAD("canUpload"),

	/**The human-readable name of the repository wiki.*/
	DISPLAYNAME("displayname"),

	/**Repository wiki's favicon URL, from <var><a href="/w/index.php?title=Mw:Special:MyLanguage/Manual:$wgFavicon&amp;action=edit&amp;redlink=1" class="new" title="Mw:Special:MyLanguage/Manual:$wgFavicon (page does not exist)">$wgFavicon</a></var>.*/
	FAVICON("favicon"),

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

		/**Whether files can be uploaded to this repository, e.g. via CORS and shared authentication.*/
		public Builder CANUPLOAD() {
			set.add(CANUPLOAD);
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
