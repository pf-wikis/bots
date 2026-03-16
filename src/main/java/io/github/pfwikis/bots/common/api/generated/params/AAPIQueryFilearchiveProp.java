package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Which image information to get:
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryFilearchiveProp {

	/**Adds the filename of the archive version for non-latest versions.*/
	ARCHIVENAME("archivename"),

	/**Adds the bit depth of the version.*/
	BITDEPTH("bitdepth"),

	/**Adds description of the image version.*/
	DESCRIPTION("description"),

	/**Alias for size.*/
	DIMENSIONS("dimensions"),

	/**Adds the media type of the image.*/
	MEDIATYPE("mediatype"),

	/**Lists Exif metadata for the version of the image.*/
	METADATA("metadata"),

	/**Adds MIME of the image.*/
	MIME("mime"),

	/**Parse the description of the version.*/
	PARSEDDESCRIPTION("parseddescription"),

	/**Adds SHA-1 hash for the image.*/
	SHA1("sha1"),

	/**Adds the size of the image in bytes and the height, width and page count (if applicable).*/
	SIZE("size"),

	/**Adds timestamp for the uploaded version.*/
	TIMESTAMP("timestamp"),

	/**Adds user who uploaded the image version.*/
	USER("user");

	private final String jsonValue;
}
