package io.github.pfwikis.bots.common.api.generated;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.NonNull;

import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;

import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import io.github.pfwikis.bots.common.api.model.AAPITokenModule;
import io.github.pfwikis.bots.common.api.model.ContainsPageRef;
import io.github.pfwikis.bots.common.api.AAPI;
import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryTokensType;
import io.github.pfwikis.bots.common.api.generated.params.NS;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryImagesDir;

import io.github.pfwikis.bots.common.api.generated.params.AAPIImagerotateGenerator.AAPIImagerotateGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIPurgeGenerator.AAPIPurgeGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryProp.AAPIQueryPropModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryGenerator.AAPIQueryGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPISetnotificationtimestampGenerator.AAPISetnotificationtimestampGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPITemplatedataGenerator.AAPITemplatedataGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIWatchGenerator.AAPIWatchGeneratorModule;

/** Returns all files contained on the given pages.
 */
public class AAPIQueryImages
		implements AAPIModule,
				AAPIImagerotateGeneratorModule,
				AAPIPurgeGeneratorModule,
				AAPIQueryPropModule,
				AAPIQueryGeneratorModule,
				AAPISetnotificationtimestampGeneratorModule,
				AAPITemplatedataGeneratorModule,
				AAPIWatchGeneratorModule {

	public static AAPIQueryImages create() {

		AAPIQueryImages v = new AAPIQueryImages();

		return v;
	}

	private AAPIQueryImages() {}

	private Integer limit = 5000;

	private List<String> images;

	private AAPIQueryImagesDir dir;

	/**How many files to return.
	 */
	public AAPIQueryImages limit(Integer limit) {

		this.limit = limit;

		return this;
	}

	/**How many files to return.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	/**Only list these files. Useful for checking whether a certain page has a certain file.
	 */
	public AAPIQueryImages images(String... images) {

		this.images = List.of(images);

		return this;
	}

	/**Only list these files. Useful for checking whether a certain page has a certain file.
	 */
	public List<String> getImages() {
		return this.images;
	}

	/**The direction in which to list.
	 */
	public AAPIQueryImages dir(AAPIQueryImagesDir dir) {

		this.dir = dir;

		return this;
	}

	/**The direction in which to list.
	 */
	public AAPIQueryImagesDir getDir() {
		return this.dir;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryImages(");

		if (limit != null) {

			sb.append("imlimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		if (images != null) {

			sb.append("imimages")
					.append("=")
					.append(images.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (dir != null) {

			sb.append("imdir").append("=").append(dir.getJsonValue());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (limit != null) {

			req.addParameter(paramPrefix + "imlimit", limit.toString());
		}

		if (images != null) {

			req.addParameter(
					paramPrefix + "imimages",
					images.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (dir != null) {

			req.addParameter(paramPrefix + "imdir", dir.getJsonValue());
		}
	}

	private final Builder builder = new Builder();

	@Override
	public Builder builder() {
		return builder;
	}

	private class Builder extends AAPIModule.Builder {
		@Override
		public void forEachModule(Consumer<AAPIModule> c) {
			c.accept(AAPIQueryImages.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryImages.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return false;
		}
	}
}
