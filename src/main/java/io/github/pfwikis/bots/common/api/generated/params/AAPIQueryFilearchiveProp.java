package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
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

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryFilearchiveProp> set =
				EnumSet.noneOf(AAPIQueryFilearchiveProp.class);

		public AAPIQueryFilearchiveProp[] build() {
			return set.toArray(AAPIQueryFilearchiveProp[]::new);
		}

		/**Adds the filename of the archive version for non-latest versions.*/
		public Builder ARCHIVENAME() {
			set.add(ARCHIVENAME);
			return this;
		}

		/**Adds the bit depth of the version.*/
		public Builder BITDEPTH() {
			set.add(BITDEPTH);
			return this;
		}

		/**Adds description of the image version.*/
		public Builder DESCRIPTION() {
			set.add(DESCRIPTION);
			return this;
		}

		/**Alias for size.*/
		public Builder DIMENSIONS() {
			set.add(DIMENSIONS);
			return this;
		}

		/**Adds the media type of the image.*/
		public Builder MEDIATYPE() {
			set.add(MEDIATYPE);
			return this;
		}

		/**Lists Exif metadata for the version of the image.*/
		public Builder METADATA() {
			set.add(METADATA);
			return this;
		}

		/**Adds MIME of the image.*/
		public Builder MIME() {
			set.add(MIME);
			return this;
		}

		/**Parse the description of the version.*/
		public Builder PARSEDDESCRIPTION() {
			set.add(PARSEDDESCRIPTION);
			return this;
		}

		/**Adds SHA-1 hash for the image.*/
		public Builder SHA1() {
			set.add(SHA1);
			return this;
		}

		/**Adds the size of the image in bytes and the height, width and page count (if applicable).*/
		public Builder SIZE() {
			set.add(SIZE);
			return this;
		}

		/**Adds timestamp for the uploaded version.*/
		public Builder TIMESTAMP() {
			set.add(TIMESTAMP);
			return this;
		}

		/**Adds user who uploaded the image version.*/
		public Builder USER() {
			set.add(USER);
			return this;
		}
	}
}
