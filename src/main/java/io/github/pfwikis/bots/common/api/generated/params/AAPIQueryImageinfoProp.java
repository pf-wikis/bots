package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Which file information to get:
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryImageinfoProp {

	/**Adds the filename of the archive version for non-latest versions. If the file has been revision deleted, a <samp>filehidden</samp> property will be returned.*/
	ARCHIVENAME("archivename"),

	/**Adds whether the file is on the <a href="/wiki/MediaWiki:Bad_image_list" title="MediaWiki:Bad image list">MediaWiki:Bad image list</a>*/
	BADFILE("badfile"),

	/**Adds the bit depth of the version. If the file has been revision deleted, a <samp>filehidden</samp> property will be returned.*/
	BITDEPTH("bitdepth"),

	/**Adds the canonical title of the file. If the file has been revision deleted, a <samp>filehidden</samp> property will be returned.*/
	CANONICALTITLE("canonicaltitle"),

	/**Comment on the version. If the comment has been revision deleted, a <samp>commenthidden</samp> property will be returned.*/
	COMMENT("comment"),

	/**Lists file format generic metadata for the version of the file. If the file has been revision deleted, a <samp>filehidden</samp> property will be returned.*/
	COMMONMETADATA("commonmetadata"),

	/**Alias for size.*/
	DIMENSIONS("dimensions"),

	/**Lists formatted metadata combined from multiple sources. Results are HTML formatted. If the file has been revision deleted, a <samp>filehidden</samp> property will be returned.*/
	EXTMETADATA("extmetadata"),

	/**Adds the media type of the file. If the file has been revision deleted, a <samp>filehidden</samp> property will be returned.*/
	MEDIATYPE("mediatype"),

	/**Lists Exif metadata for the version of the file. If the file has been revision deleted, a <samp>filehidden</samp> property will be returned.*/
	METADATA("metadata"),

	/**Adds MIME type of the file. If the file has been revision deleted, a <samp>filehidden</samp> property will be returned.*/
	MIME("mime"),

	/**Parse the comment on the version. If the comment has been revision deleted, a <samp>commenthidden</samp> property will be returned.*/
	PARSEDCOMMENT("parsedcomment"),

	/**Adds SHA-1 hash for the file. If the file has been revision deleted, a <samp>filehidden</samp> property will be returned.*/
	SHA1("sha1"),

	/**Adds the size of the file in bytes and the height, width and page count (if applicable).*/
	SIZE("size"),

	/**Adds MIME type of the image thumbnail (requires url and param iiurlwidth). If the file has been revision deleted, a <samp>filehidden</samp> property will be returned.*/
	THUMBMIME("thumbmime"),

	/**Adds timestamp for the uploaded version.*/
	TIMESTAMP("timestamp"),

	/**Used by the Special:Upload page to get information about an existing file. Not intended for use outside MediaWiki core.*/
	UPLOADWARNING("uploadwarning"),

	/**Gives URL to the file and the description page. If the file has been revision deleted, a <samp>filehidden</samp> property will be returned.*/
	URL("url"),

	/**Adds the user who uploaded each file version. If the user has been revision deleted, a <samp>userhidden</samp> property will be returned.*/
	USER("user"),

	/**Add the ID of the user that uploaded each file version. If the user has been revision deleted, a <samp>userhidden</samp> property will be returned.*/
	USERID("userid");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryImageinfoProp> set = EnumSet.noneOf(AAPIQueryImageinfoProp.class);

		public AAPIQueryImageinfoProp[] build() {
			return set.toArray(AAPIQueryImageinfoProp[]::new);
		}

		/**Adds the filename of the archive version for non-latest versions. If the file has been revision deleted, a <samp>filehidden</samp> property will be returned.*/
		public Builder ARCHIVENAME() {
			set.add(ARCHIVENAME);
			return this;
		}

		/**Adds whether the file is on the <a href="/wiki/MediaWiki:Bad_image_list" title="MediaWiki:Bad image list">MediaWiki:Bad image list</a>*/
		public Builder BADFILE() {
			set.add(BADFILE);
			return this;
		}

		/**Adds the bit depth of the version. If the file has been revision deleted, a <samp>filehidden</samp> property will be returned.*/
		public Builder BITDEPTH() {
			set.add(BITDEPTH);
			return this;
		}

		/**Adds the canonical title of the file. If the file has been revision deleted, a <samp>filehidden</samp> property will be returned.*/
		public Builder CANONICALTITLE() {
			set.add(CANONICALTITLE);
			return this;
		}

		/**Comment on the version. If the comment has been revision deleted, a <samp>commenthidden</samp> property will be returned.*/
		public Builder COMMENT() {
			set.add(COMMENT);
			return this;
		}

		/**Lists file format generic metadata for the version of the file. If the file has been revision deleted, a <samp>filehidden</samp> property will be returned.*/
		public Builder COMMONMETADATA() {
			set.add(COMMONMETADATA);
			return this;
		}

		/**Alias for size.*/
		public Builder DIMENSIONS() {
			set.add(DIMENSIONS);
			return this;
		}

		/**Lists formatted metadata combined from multiple sources. Results are HTML formatted. If the file has been revision deleted, a <samp>filehidden</samp> property will be returned.*/
		public Builder EXTMETADATA() {
			set.add(EXTMETADATA);
			return this;
		}

		/**Adds the media type of the file. If the file has been revision deleted, a <samp>filehidden</samp> property will be returned.*/
		public Builder MEDIATYPE() {
			set.add(MEDIATYPE);
			return this;
		}

		/**Lists Exif metadata for the version of the file. If the file has been revision deleted, a <samp>filehidden</samp> property will be returned.*/
		public Builder METADATA() {
			set.add(METADATA);
			return this;
		}

		/**Adds MIME type of the file. If the file has been revision deleted, a <samp>filehidden</samp> property will be returned.*/
		public Builder MIME() {
			set.add(MIME);
			return this;
		}

		/**Parse the comment on the version. If the comment has been revision deleted, a <samp>commenthidden</samp> property will be returned.*/
		public Builder PARSEDCOMMENT() {
			set.add(PARSEDCOMMENT);
			return this;
		}

		/**Adds SHA-1 hash for the file. If the file has been revision deleted, a <samp>filehidden</samp> property will be returned.*/
		public Builder SHA1() {
			set.add(SHA1);
			return this;
		}

		/**Adds the size of the file in bytes and the height, width and page count (if applicable).*/
		public Builder SIZE() {
			set.add(SIZE);
			return this;
		}

		/**Adds MIME type of the image thumbnail (requires url and param iiurlwidth). If the file has been revision deleted, a <samp>filehidden</samp> property will be returned.*/
		public Builder THUMBMIME() {
			set.add(THUMBMIME);
			return this;
		}

		/**Adds timestamp for the uploaded version.*/
		public Builder TIMESTAMP() {
			set.add(TIMESTAMP);
			return this;
		}

		/**Used by the Special:Upload page to get information about an existing file. Not intended for use outside MediaWiki core.*/
		public Builder UPLOADWARNING() {
			set.add(UPLOADWARNING);
			return this;
		}

		/**Gives URL to the file and the description page. If the file has been revision deleted, a <samp>filehidden</samp> property will be returned.*/
		public Builder URL() {
			set.add(URL);
			return this;
		}

		/**Adds the user who uploaded each file version. If the user has been revision deleted, a <samp>userhidden</samp> property will be returned.*/
		public Builder USER() {
			set.add(USER);
			return this;
		}

		/**Add the ID of the user that uploaded each file version. If the user has been revision deleted, a <samp>userhidden</samp> property will be returned.*/
		public Builder USERID() {
			set.add(USERID);
			return this;
		}
	}
}
