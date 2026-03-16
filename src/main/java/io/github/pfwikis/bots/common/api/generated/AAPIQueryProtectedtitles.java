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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryProtectedtitlesLevel;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryProtectedtitlesDir;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryProtectedtitlesProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIImagerotateGenerator.AAPIImagerotateGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIPurgeGenerator.AAPIPurgeGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryList.AAPIQueryListModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryGenerator.AAPIQueryGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPISetnotificationtimestampGenerator.AAPISetnotificationtimestampGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPITemplatedataGenerator.AAPITemplatedataGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIWatchGenerator.AAPIWatchGeneratorModule;

/** List all titles protected from creation.
 */
public class AAPIQueryProtectedtitles
		implements AAPIModule,
				AAPIImagerotateGeneratorModule,
				AAPIPurgeGeneratorModule,
				AAPIQueryListModule,
				AAPIQueryGeneratorModule,
				AAPISetnotificationtimestampGeneratorModule,
				AAPITemplatedataGeneratorModule,
				AAPIWatchGeneratorModule {

	public static AAPIQueryProtectedtitles create() {

		AAPIQueryProtectedtitles v = new AAPIQueryProtectedtitles();

		return v;
	}

	private AAPIQueryProtectedtitles() {}

	private List<NS> namespace;

	private List<AAPIQueryProtectedtitlesLevel> level;

	private Integer limit = 5000;

	private AAPIQueryProtectedtitlesDir dir;

	private java.time.Instant start;

	private java.time.Instant end;

	private List<AAPIQueryProtectedtitlesProp> prop;

	/**Only list titles in these namespaces.
	 */
	public AAPIQueryProtectedtitles namespace(NS... namespace) {

		this.namespace = List.of(namespace);

		return this;
	}

	/**Only list titles in these namespaces.
	 */
	public List<NS> getNamespace() {
		return this.namespace;
	}

	/**Only list titles with these protection levels.
	 */
	public AAPIQueryProtectedtitles level(AAPIQueryProtectedtitlesLevel... level) {

		this.level = List.of(level);

		return this;
	}

	/**Only list titles with these protection levels.
	 */
	public List<AAPIQueryProtectedtitlesLevel> getLevel() {
		return this.level;
	}

	/**How many total pages to return.
	 */
	public AAPIQueryProtectedtitles limit(Integer limit) {

		this.limit = limit;

		return this;
	}

	/**How many total pages to return.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	/**<p>In which direction to enumerate:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryProtectedtitles dir(AAPIQueryProtectedtitlesDir dir) {

		this.dir = dir;

		return this;
	}

	/**<p>In which direction to enumerate:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryProtectedtitlesDir getDir() {
		return this.dir;
	}

	/**Start listing at this protection timestamp.
	 */
	public AAPIQueryProtectedtitles start(java.time.Instant start) {

		this.start = start;

		return this;
	}

	/**Start listing at this protection timestamp.
	 */
	public java.time.Instant getStart() {
		return this.start;
	}

	/**Stop listing at this protection timestamp.
	 */
	public AAPIQueryProtectedtitles end(java.time.Instant end) {

		this.end = end;

		return this;
	}

	/**Stop listing at this protection timestamp.
	 */
	public java.time.Instant getEnd() {
		return this.end;
	}

	/**<p>Which properties to get:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryProtectedtitles prop(AAPIQueryProtectedtitlesProp... prop) {

		this.prop = List.of(prop);

		return this;
	}

	/**<p>Which properties to get:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryProtectedtitlesProp> getProp() {
		return this.prop;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryProtectedtitles(");

		if (namespace != null) {

			sb.append("ptnamespace")
					.append("=")
					.append(
							namespace.stream()
									.map(v -> Integer.toString(v.getId()))
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (level != null) {

			sb.append("ptlevel")
					.append("=")
					.append(
							level.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("ptlimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		if (dir != null) {

			sb.append("ptdir").append("=").append(dir.getJsonValue());

			sb.append(", ");
		}

		if (start != null) {

			sb.append("ptstart")
					.append("=")
					.append(start.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());

			sb.append(", ");
		}

		if (end != null) {

			sb.append("ptend")
					.append("=")
					.append(end.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());

			sb.append(", ");
		}

		if (prop != null) {

			sb.append("ptprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (namespace != null) {

			req.addParameter(
					paramPrefix + "ptnamespace",
					namespace.stream()
							.map(v -> Integer.toString(v.getId()))
							.collect(Collectors.joining("|")));
		}

		if (level != null) {

			req.addParameter(
					paramPrefix + "ptlevel",
					level.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "ptlimit", limit.toString());
		}

		if (dir != null) {

			req.addParameter(paramPrefix + "ptdir", dir.getJsonValue());
		}

		if (start != null) {

			req.addParameter(
					paramPrefix + "ptstart",
					start.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());
		}

		if (end != null) {

			req.addParameter(
					paramPrefix + "ptend",
					end.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());
		}

		if (prop != null) {

			req.addParameter(
					paramPrefix + "ptprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
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
			c.accept(AAPIQueryProtectedtitles.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryProtectedtitles.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return false;
		}
	}
}
