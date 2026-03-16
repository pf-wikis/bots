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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryLangbacklinksProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryLangbacklinksDir;

import io.github.pfwikis.bots.common.api.generated.params.AAPIImagerotateGenerator.AAPIImagerotateGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIPurgeGenerator.AAPIPurgeGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryList.AAPIQueryListModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryGenerator.AAPIQueryGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPISetnotificationtimestampGenerator.AAPISetnotificationtimestampGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPITemplatedataGenerator.AAPITemplatedataGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIWatchGenerator.AAPIWatchGeneratorModule;

/** <p>Find all pages that link to the given language link.
 * </p>
 * <p>Can be used to find all links with a language code, or all links to a title (with a given language). Using neither parameter is effectively "all language links".
 * </p><p>Note that this may not consider language links added by extensions.
 * </p>
 */
public class AAPIQueryLangbacklinks
		implements AAPIModule,
				AAPIImagerotateGeneratorModule,
				AAPIPurgeGeneratorModule,
				AAPIQueryListModule,
				AAPIQueryGeneratorModule,
				AAPISetnotificationtimestampGeneratorModule,
				AAPITemplatedataGeneratorModule,
				AAPIWatchGeneratorModule {

	public static AAPIQueryLangbacklinks create() {

		AAPIQueryLangbacklinks v = new AAPIQueryLangbacklinks();

		return v;
	}

	private AAPIQueryLangbacklinks() {}

	private String lang;

	private String title;

	private Integer limit = 5000;

	private List<AAPIQueryLangbacklinksProp> prop;

	private AAPIQueryLangbacklinksDir dir;

	/**Language for the language link.
	 */
	public AAPIQueryLangbacklinks lang(String lang) {

		this.lang = lang;

		return this;
	}

	/**Language for the language link.
	 */
	public String getLang() {
		return this.lang;
	}

	/**Language link to search for. Must be used with lbllang.
	 */
	public AAPIQueryLangbacklinks title(String title) {

		this.title = title;

		return this;
	}

	/**Language link to search for. Must be used with lbllang.
	 */
	public String getTitle() {
		return this.title;
	}

	/**How many total pages to return.
	 */
	public AAPIQueryLangbacklinks limit(Integer limit) {

		this.limit = limit;

		return this;
	}

	/**How many total pages to return.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	/**<p>Which properties to get:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryLangbacklinks prop(AAPIQueryLangbacklinksProp... prop) {

		this.prop = List.of(prop);

		return this;
	}

	/**<p>Which properties to get:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryLangbacklinksProp> getProp() {
		return this.prop;
	}

	/**The direction in which to list.
	 */
	public AAPIQueryLangbacklinks dir(AAPIQueryLangbacklinksDir dir) {

		this.dir = dir;

		return this;
	}

	/**The direction in which to list.
	 */
	public AAPIQueryLangbacklinksDir getDir() {
		return this.dir;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryLangbacklinks(");

		if (lang != null) {

			sb.append("lbllang").append("=").append(lang);

			sb.append(", ");
		}

		if (title != null) {

			sb.append("lbltitle").append("=").append(title);

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("lbllimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		if (prop != null) {

			sb.append("lblprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (dir != null) {

			sb.append("lbldir").append("=").append(dir.getJsonValue());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (lang != null) {

			req.addParameter(paramPrefix + "lbllang", lang);
		}

		if (title != null) {

			req.addParameter(paramPrefix + "lbltitle", title);
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "lbllimit", limit.toString());
		}

		if (prop != null) {

			req.addParameter(
					paramPrefix + "lblprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (dir != null) {

			req.addParameter(paramPrefix + "lbldir", dir.getJsonValue());
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
			c.accept(AAPIQueryLangbacklinks.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryLangbacklinks.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return false;
		}
	}
}
