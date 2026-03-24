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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryLanglinksProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryLanglinksDir;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryProp.AAPIQueryPropModule;

/** Returns all interlanguage links from the given pages.
 */
public class AAPIQueryLanglinks implements AAPIModule, AAPIQueryPropModule {

	public static AAPIQueryLanglinks create() {

		AAPIQueryLanglinks v = new AAPIQueryLanglinks();

		return v;
	}

	private AAPIQueryLanglinks() {}

	private List<AAPIQueryLanglinksProp> prop;

	private String lang;

	private String title;

	private AAPIQueryLanglinksDir dir;

	private String inlanguagecode;

	private Integer limit;

	/**<p>Which additional properties to get for each interlanguage link:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryLanglinks prop(AAPIQueryLanglinksProp prop) {
		this.prop = List.of(prop);

		return this;
	}

	/**<p>Which additional properties to get for each interlanguage link:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryLanglinks prop(AAPIQueryLanglinksProp... prop) {
		this.prop = List.of(prop);
		return this;
	}

	/**<p>Which additional properties to get for each interlanguage link:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryLanglinksProp> getProp() {
		return this.prop;
	}

	/**Only return language links with this language code.
	 */
	public AAPIQueryLanglinks lang(String lang) {
		this.lang = lang;

		return this;
	}

	/**Only return language links with this language code.
	 */
	public String getLang() {
		return this.lang;
	}

	/**Link to search for. Must be used with <var>lllang</var>.
	 */
	public AAPIQueryLanglinks title(String title) {
		this.title = title;

		return this;
	}

	/**Link to search for. Must be used with <var>lllang</var>.
	 */
	public String getTitle() {
		return this.title;
	}

	/**The direction in which to list.
	 */
	public AAPIQueryLanglinks dir(AAPIQueryLanglinksDir dir) {
		this.dir = dir;

		return this;
	}

	/**The direction in which to list.
	 */
	public AAPIQueryLanglinksDir getDir() {
		return this.dir;
	}

	/**Language code for localised language names.
	 */
	public AAPIQueryLanglinks inlanguagecode(String inlanguagecode) {
		this.inlanguagecode = inlanguagecode;

		return this;
	}

	/**Language code for localised language names.
	 */
	public String getInlanguagecode() {
		return this.inlanguagecode;
	}

	/**How many langlinks to return.
	 */
	public AAPIQueryLanglinks limit(Integer limit) {
		this.limit = limit;

		return this;
	}

	/**How many langlinks to return.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryLanglinks(");

		if (prop != null) {

			sb.append("llprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (lang != null) {

			sb.append("lllang").append("=").append(lang);

			sb.append(", ");
		}

		if (title != null) {

			sb.append("lltitle").append("=").append(title);

			sb.append(", ");
		}

		if (dir != null) {

			sb.append("lldir").append("=").append(dir.getJsonValue());

			sb.append(", ");
		}

		if (inlanguagecode != null) {

			sb.append("llinlanguagecode").append("=").append(inlanguagecode);

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("lllimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (prop != null) {

			req.addParameter(
					paramPrefix + "llprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (lang != null) {

			req.addParameter(paramPrefix + "lllang", lang);
		}

		if (title != null) {

			req.addParameter(paramPrefix + "lltitle", title);
		}

		if (dir != null) {

			req.addParameter(paramPrefix + "lldir", dir.getJsonValue());
		}

		if (inlanguagecode != null) {

			req.addParameter(paramPrefix + "llinlanguagecode", inlanguagecode);
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "lllimit", limit.toString());

		} else {
			req.addParameter(paramPrefix + "lllimit", "5000");
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
			c.accept(AAPIQueryLanglinks.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryLanglinks.this);

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
