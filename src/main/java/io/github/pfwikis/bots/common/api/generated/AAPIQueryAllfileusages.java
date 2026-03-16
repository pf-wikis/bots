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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryAllfileusagesProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryAllfileusagesDir;

import io.github.pfwikis.bots.common.api.generated.params.AAPIImagerotateGenerator.AAPIImagerotateGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIPurgeGenerator.AAPIPurgeGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryList.AAPIQueryListModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryGenerator.AAPIQueryGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPISetnotificationtimestampGenerator.AAPISetnotificationtimestampGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPITemplatedataGenerator.AAPITemplatedataGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIWatchGenerator.AAPIWatchGeneratorModule;

/** List all file usages, including non-existing.
 */
public class AAPIQueryAllfileusages
		implements AAPIModule,
				AAPIImagerotateGeneratorModule,
				AAPIPurgeGeneratorModule,
				AAPIQueryListModule,
				AAPIQueryGeneratorModule,
				AAPISetnotificationtimestampGeneratorModule,
				AAPITemplatedataGeneratorModule,
				AAPIWatchGeneratorModule {

	public static AAPIQueryAllfileusages create() {

		AAPIQueryAllfileusages v = new AAPIQueryAllfileusages();

		return v;
	}

	private AAPIQueryAllfileusages() {}

	private String from;

	private String to;

	private String prefix;

	private Boolean unique;

	private List<AAPIQueryAllfileusagesProp> prop;

	private Integer limit = 5000;

	private AAPIQueryAllfileusagesDir dir;

	/**The title of the file to start enumerating from.
	 */
	public AAPIQueryAllfileusages from(String from) {

		this.from = from;

		return this;
	}

	/**The title of the file to start enumerating from.
	 */
	public String getFrom() {
		return this.from;
	}

	/**The title of the file to stop enumerating at.
	 */
	public AAPIQueryAllfileusages to(String to) {

		this.to = to;

		return this;
	}

	/**The title of the file to stop enumerating at.
	 */
	public String getTo() {
		return this.to;
	}

	/**Search for all file titles that begin with this value.
	 */
	public AAPIQueryAllfileusages prefix(String prefix) {

		this.prefix = prefix;

		return this;
	}

	/**Search for all file titles that begin with this value.
	 */
	public String getPrefix() {
		return this.prefix;
	}

	/**Only show distinct file titles. Cannot be used with afprop=ids.
	 * When used as a generator, yields target pages instead of source pages.
	 */
	public AAPIQueryAllfileusages unique(Boolean unique) {

		this.unique = unique;

		return this;
	}

	/**Only show distinct file titles. Cannot be used with afprop=ids.
	 * When used as a generator, yields target pages instead of source pages.
	 */
	public Boolean getUnique() {
		return this.unique;
	}

	/**<p>Which pieces of information to include:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryAllfileusages prop(AAPIQueryAllfileusagesProp... prop) {

		this.prop = List.of(prop);

		return this;
	}

	/**<p>Which pieces of information to include:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryAllfileusagesProp> getProp() {
		return this.prop;
	}

	/**How many total items to return.
	 */
	public AAPIQueryAllfileusages limit(Integer limit) {

		this.limit = limit;

		return this;
	}

	/**How many total items to return.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	/**The direction in which to list.
	 */
	public AAPIQueryAllfileusages dir(AAPIQueryAllfileusagesDir dir) {

		this.dir = dir;

		return this;
	}

	/**The direction in which to list.
	 */
	public AAPIQueryAllfileusagesDir getDir() {
		return this.dir;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryAllfileusages(");

		if (from != null) {

			sb.append("affrom").append("=").append(from);

			sb.append(", ");
		}

		if (to != null) {

			sb.append("afto").append("=").append(to);

			sb.append(", ");
		}

		if (prefix != null) {

			sb.append("afprefix").append("=").append(prefix);

			sb.append(", ");
		}

		if (unique != null) {

			sb.append("afunique").append("=").append(unique.toString());

			sb.append(", ");
		}

		if (prop != null) {

			sb.append("afprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("aflimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		if (dir != null) {

			sb.append("afdir").append("=").append(dir.getJsonValue());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (from != null) {

			req.addParameter(paramPrefix + "affrom", from);
		}

		if (to != null) {

			req.addParameter(paramPrefix + "afto", to);
		}

		if (prefix != null) {

			req.addParameter(paramPrefix + "afprefix", prefix);
		}

		if (unique != null) {

			req.addParameter(paramPrefix + "afunique", unique.toString());
		}

		if (prop != null) {

			req.addParameter(
					paramPrefix + "afprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "aflimit", limit.toString());
		}

		if (dir != null) {

			req.addParameter(paramPrefix + "afdir", dir.getJsonValue());
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
			c.accept(AAPIQueryAllfileusages.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryAllfileusages.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return false;
		}
	}
}
