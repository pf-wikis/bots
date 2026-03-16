package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Which file information to get:
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryStashimageinfoProp {

	/**Adds whether the file is on the <a href="/wiki/MediaWiki:Bad_image_list" title="MediaWiki:Bad image list">MediaWiki:Bad image list</a>*/
	BADFILE("badfile"),

	/**Adds the bit depth of the version. If the file has been revision deleted, a <samp>filehidden</samp> property will be returned.*/
	BITDEPTH("bitdepth"),

	/**Adds the canonical title of the file. If the file has been revision deleted, a <samp>filehidden</samp> property will be returned.*/
	CANONICALTITLE("canonicaltitle"),

	/**Lists file format generic metadata for the version of the file. If the file has been revision deleted, a <samp>filehidden</samp> property will be returned.*/
	COMMONMETADATA("commonmetadata"),

	/**Alias for size.*/
	DIMENSIONS("dimensions"),

	/**Lists formatted metadata combined from multiple sources. Results are HTML formatted. If the file has been revision deleted, a <samp>filehidden</samp> property will be returned.*/
	EXTMETADATA("extmetadata"),

	/**Lists Exif metadata for the version of the file. If the file has been revision deleted, a <samp>filehidden</samp> property will be returned.*/
	METADATA("metadata"),

	/**Adds MIME type of the file. If the file has been revision deleted, a <samp>filehidden</samp> property will be returned.*/
	MIME("mime"),

	/**Adds SHA-1 hash for the file. If the file has been revision deleted, a <samp>filehidden</samp> property will be returned.*/
	SHA1("sha1"),

	/**Adds the size of the file in bytes and the height, width and page count (if applicable).*/
	SIZE("size"),

	/**Adds MIME type of the image thumbnail (requires url and param siiurlwidth). If the file has been revision deleted, a <samp>filehidden</samp> property will be returned.*/
	THUMBMIME("thumbmime"),

	/**Adds timestamp for the uploaded version.*/
	TIMESTAMP("timestamp"),

	/**Gives URL to the file and the description page. If the file has been revision deleted, a <samp>filehidden</samp> property will be returned.*/
	URL("url");

	private final String jsonValue;
}
