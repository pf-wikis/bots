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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryDuplicatefilesDir;

import io.github.pfwikis.bots.common.api.generated.params.AAPIImagerotateGenerator.AAPIImagerotateGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIPurgeGenerator.AAPIPurgeGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryProp.AAPIQueryPropModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryGenerator.AAPIQueryGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPISetnotificationtimestampGenerator.AAPISetnotificationtimestampGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPITemplatedataGenerator.AAPITemplatedataGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIWatchGenerator.AAPIWatchGeneratorModule;

/** List all files that are duplicates of the given files based on hash values.
 */
public class AAPIQueryDuplicatefiles
		implements AAPIModule,
				AAPIImagerotateGeneratorModule,
				AAPIPurgeGeneratorModule,
				AAPIQueryPropModule,
				AAPIQueryGeneratorModule,
				AAPISetnotificationtimestampGeneratorModule,
				AAPITemplatedataGeneratorModule,
				AAPIWatchGeneratorModule {

	public static AAPIQueryDuplicatefiles create() {

		AAPIQueryDuplicatefiles v = new AAPIQueryDuplicatefiles();

		return v;
	}

	private AAPIQueryDuplicatefiles() {}

	private Integer limit;

	private AAPIQueryDuplicatefilesDir dir;

	private Boolean localonly;

	/**How many duplicate files to return.
	 */
	public AAPIQueryDuplicatefiles limit(Integer limit) {
		this.limit = limit;

		return this;
	}

	/**How many duplicate files to return.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	/**The direction in which to list.
	 */
	public AAPIQueryDuplicatefiles dir(AAPIQueryDuplicatefilesDir dir) {
		this.dir = dir;

		return this;
	}

	/**The direction in which to list.
	 */
	public AAPIQueryDuplicatefilesDir getDir() {
		return this.dir;
	}

	/**Look only for files in the local repository.
	 */
	public AAPIQueryDuplicatefiles localonly(Boolean localonly) {
		this.localonly = localonly;

		return this;
	}

	/**Look only for files in the local repository.
	 */
	public Boolean getLocalonly() {
		return this.localonly;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryDuplicatefiles(");

		if (limit != null) {

			sb.append("dflimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		if (dir != null) {

			sb.append("dfdir").append("=").append(dir.getJsonValue());

			sb.append(", ");
		}

		if (localonly != null) {

			sb.append("dflocalonly").append("=").append(localonly.toString());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (limit != null) {

			req.addParameter(paramPrefix + "dflimit", limit.toString());

		} else {
			req.addParameter(paramPrefix + "dflimit", "5000");
		}

		if (dir != null) {

			req.addParameter(paramPrefix + "dfdir", dir.getJsonValue());
		}

		if (localonly != null) {

			req.addParameter(paramPrefix + "dflocalonly", localonly.toString());
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
			c.accept(AAPIQueryDuplicatefiles.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryDuplicatefiles.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return false;
		}

		@Override
		protected boolean internalRequiresPagination() {
			return limit == null;
		}
	}
}
