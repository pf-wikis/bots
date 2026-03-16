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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryFilearchiveDir;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryFilearchiveProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryList.AAPIQueryListModule;

/** Enumerate all deleted files sequentially.
 */
public class AAPIQueryFilearchive implements AAPIModule, AAPIQueryListModule {

	public static AAPIQueryFilearchive create() {

		AAPIQueryFilearchive v = new AAPIQueryFilearchive();

		return v;
	}

	private AAPIQueryFilearchive() {}

	private String from;

	private String to;

	private String prefix;

	private AAPIQueryFilearchiveDir dir;

	private String sha1;

	private String sha1base36;

	private List<AAPIQueryFilearchiveProp> prop;

	private Integer limit = 5000;

	/**The image title to start enumerating from.
	 */
	public AAPIQueryFilearchive from(String from) {

		this.from = from;

		return this;
	}

	/**The image title to start enumerating from.
	 */
	public String getFrom() {
		return this.from;
	}

	/**The image title to stop enumerating at.
	 */
	public AAPIQueryFilearchive to(String to) {

		this.to = to;

		return this;
	}

	/**The image title to stop enumerating at.
	 */
	public String getTo() {
		return this.to;
	}

	/**Search for all image titles that begin with this value.
	 */
	public AAPIQueryFilearchive prefix(String prefix) {

		this.prefix = prefix;

		return this;
	}

	/**Search for all image titles that begin with this value.
	 */
	public String getPrefix() {
		return this.prefix;
	}

	/**The direction in which to list.
	 */
	public AAPIQueryFilearchive dir(AAPIQueryFilearchiveDir dir) {

		this.dir = dir;

		return this;
	}

	/**The direction in which to list.
	 */
	public AAPIQueryFilearchiveDir getDir() {
		return this.dir;
	}

	/**SHA1 hash of image. Overrides fasha1base36.
	 */
	public AAPIQueryFilearchive sha1(String sha1) {

		this.sha1 = sha1;

		return this;
	}

	/**SHA1 hash of image. Overrides fasha1base36.
	 */
	public String getSha1() {
		return this.sha1;
	}

	/**SHA1 hash of image in base 36 (used in MediaWiki).
	 */
	public AAPIQueryFilearchive sha1base36(String sha1base36) {

		this.sha1base36 = sha1base36;

		return this;
	}

	/**SHA1 hash of image in base 36 (used in MediaWiki).
	 */
	public String getSha1base36() {
		return this.sha1base36;
	}

	/**<p>Which image information to get:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryFilearchive prop(AAPIQueryFilearchiveProp... prop) {

		this.prop = List.of(prop);

		return this;
	}

	/**<p>Which image information to get:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryFilearchiveProp> getProp() {
		return this.prop;
	}

	/**How many images to return in total.
	 */
	public AAPIQueryFilearchive limit(Integer limit) {

		this.limit = limit;

		return this;
	}

	/**How many images to return in total.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryFilearchive(");

		if (from != null) {

			sb.append("fafrom").append("=").append(from);

			sb.append(", ");
		}

		if (to != null) {

			sb.append("fato").append("=").append(to);

			sb.append(", ");
		}

		if (prefix != null) {

			sb.append("faprefix").append("=").append(prefix);

			sb.append(", ");
		}

		if (dir != null) {

			sb.append("fadir").append("=").append(dir.getJsonValue());

			sb.append(", ");
		}

		if (sha1 != null) {

			sb.append("fasha1").append("=").append(sha1);

			sb.append(", ");
		}

		if (sha1base36 != null) {

			sb.append("fasha1base36").append("=").append(sha1base36);

			sb.append(", ");
		}

		if (prop != null) {

			sb.append("faprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("falimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (from != null) {

			req.addParameter(paramPrefix + "fafrom", from);
		}

		if (to != null) {

			req.addParameter(paramPrefix + "fato", to);
		}

		if (prefix != null) {

			req.addParameter(paramPrefix + "faprefix", prefix);
		}

		if (dir != null) {

			req.addParameter(paramPrefix + "fadir", dir.getJsonValue());
		}

		if (sha1 != null) {

			req.addParameter(paramPrefix + "fasha1", sha1);
		}

		if (sha1base36 != null) {

			req.addParameter(paramPrefix + "fasha1base36", sha1base36);
		}

		if (prop != null) {

			req.addParameter(
					paramPrefix + "faprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "falimit", limit.toString());
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
			c.accept(AAPIQueryFilearchive.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryFilearchive.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return false;
		}
	}
}
