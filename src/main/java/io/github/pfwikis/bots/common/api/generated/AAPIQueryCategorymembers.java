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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryCategorymembersProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryCategorymembersType;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryCategorymembersSort;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryCategorymembersDir;

import io.github.pfwikis.bots.common.api.generated.params.AAPIImagerotateGenerator.AAPIImagerotateGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIPurgeGenerator.AAPIPurgeGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryList.AAPIQueryListModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryGenerator.AAPIQueryGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPISetnotificationtimestampGenerator.AAPISetnotificationtimestampGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPITemplatedataGenerator.AAPITemplatedataGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIWatchGenerator.AAPIWatchGeneratorModule;

/** List all pages in a given category.
 */
public class AAPIQueryCategorymembers
		implements AAPIModule,
				AAPIImagerotateGeneratorModule,
				AAPIPurgeGeneratorModule,
				AAPIQueryListModule,
				AAPIQueryGeneratorModule,
				AAPISetnotificationtimestampGeneratorModule,
				AAPITemplatedataGeneratorModule,
				AAPIWatchGeneratorModule {

	public static AAPIQueryCategorymembers create(@NonNull ContainsPageRef title) {

		AAPIQueryCategorymembers v = new AAPIQueryCategorymembers();

		v.title = title;

		return v;
	}

	private AAPIQueryCategorymembers() {}

	private List<AAPIQueryCategorymembersProp> prop;

	private List<NS> namespace;

	private List<AAPIQueryCategorymembersType> type;

	private Integer limit = 5000;

	private AAPIQueryCategorymembersSort sort;

	private AAPIQueryCategorymembersDir dir;

	private java.time.Instant start;

	private java.time.Instant end;

	private String starthexsortkey;

	private String endhexsortkey;

	private String startsortkeyprefix;

	private String endsortkeyprefix;

	private ContainsPageRef title;

	/**<p>Which pieces of information to include:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryCategorymembers prop(AAPIQueryCategorymembersProp... prop) {

		this.prop = List.of(prop);

		return this;
	}

	/**<p>Which pieces of information to include:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryCategorymembersProp> getProp() {
		return this.prop;
	}

	/**Only include pages in these namespaces. Note that <kbd>cmtype=subcat</kbd> or <kbd>cmtype=file</kbd> may be used instead of <kbd>cmnamespace=14</kbd> or <kbd>6</kbd>.
	 */
	public AAPIQueryCategorymembers namespace(NS... namespace) {

		this.namespace = List.of(namespace);

		return this;
	}

	/**Only include pages in these namespaces. Note that <kbd>cmtype=subcat</kbd> or <kbd>cmtype=file</kbd> may be used instead of <kbd>cmnamespace=14</kbd> or <kbd>6</kbd>.
	 */
	public List<NS> getNamespace() {
		return this.namespace;
	}

	/**Which type of category members to include. Ignored when <kbd>cmsort=timestamp</kbd> is set.
	 */
	public AAPIQueryCategorymembers type(AAPIQueryCategorymembersType... type) {

		this.type = List.of(type);

		return this;
	}

	/**Which type of category members to include. Ignored when <kbd>cmsort=timestamp</kbd> is set.
	 */
	public List<AAPIQueryCategorymembersType> getType() {
		return this.type;
	}

	/**The maximum number of pages to return.
	 */
	public AAPIQueryCategorymembers limit(Integer limit) {

		this.limit = limit;

		return this;
	}

	/**The maximum number of pages to return.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	/**Property to sort by.
	 */
	public AAPIQueryCategorymembers sort(AAPIQueryCategorymembersSort sort) {

		this.sort = sort;

		return this;
	}

	/**Property to sort by.
	 */
	public AAPIQueryCategorymembersSort getSort() {
		return this.sort;
	}

	/**In which direction to sort.
	 */
	public AAPIQueryCategorymembers dir(AAPIQueryCategorymembersDir dir) {

		this.dir = dir;

		return this;
	}

	/**In which direction to sort.
	 */
	public AAPIQueryCategorymembersDir getDir() {
		return this.dir;
	}

	/**Timestamp to start listing from. Can only be used with <kbd>cmsort=timestamp</kbd>.
	 */
	public AAPIQueryCategorymembers start(java.time.Instant start) {

		this.start = start;

		return this;
	}

	/**Timestamp to start listing from. Can only be used with <kbd>cmsort=timestamp</kbd>.
	 */
	public java.time.Instant getStart() {
		return this.start;
	}

	/**Timestamp to end listing at. Can only be used with <kbd>cmsort=timestamp</kbd>.
	 */
	public AAPIQueryCategorymembers end(java.time.Instant end) {

		this.end = end;

		return this;
	}

	/**Timestamp to end listing at. Can only be used with <kbd>cmsort=timestamp</kbd>.
	 */
	public java.time.Instant getEnd() {
		return this.end;
	}

	/**Sortkey to start listing from, as returned by <kbd>cmprop=sortkey</kbd>. Can only be used with <kbd>cmsort=sortkey</kbd>.
	 */
	public AAPIQueryCategorymembers starthexsortkey(String starthexsortkey) {

		this.starthexsortkey = starthexsortkey;

		return this;
	}

	/**Sortkey to start listing from, as returned by <kbd>cmprop=sortkey</kbd>. Can only be used with <kbd>cmsort=sortkey</kbd>.
	 */
	public String getStarthexsortkey() {
		return this.starthexsortkey;
	}

	/**Sortkey to end listing at, as returned by <kbd>cmprop=sortkey</kbd>. Can only be used with <kbd>cmsort=sortkey</kbd>.
	 */
	public AAPIQueryCategorymembers endhexsortkey(String endhexsortkey) {

		this.endhexsortkey = endhexsortkey;

		return this;
	}

	/**Sortkey to end listing at, as returned by <kbd>cmprop=sortkey</kbd>. Can only be used with <kbd>cmsort=sortkey</kbd>.
	 */
	public String getEndhexsortkey() {
		return this.endhexsortkey;
	}

	/**Sortkey prefix to start listing from. Can only be used with <kbd>cmsort=sortkey</kbd>. Overrides <var>cmstarthexsortkey</var>.
	 */
	public AAPIQueryCategorymembers startsortkeyprefix(String startsortkeyprefix) {

		this.startsortkeyprefix = startsortkeyprefix;

		return this;
	}

	/**Sortkey prefix to start listing from. Can only be used with <kbd>cmsort=sortkey</kbd>. Overrides <var>cmstarthexsortkey</var>.
	 */
	public String getStartsortkeyprefix() {
		return this.startsortkeyprefix;
	}

	/**Sortkey prefix to end listing <strong>before</strong> (not <strong>at</strong>; if this value occurs it will not be included!). Can only be used with cmsort=sortkey. Overrides cmendhexsortkey.
	 */
	public AAPIQueryCategorymembers endsortkeyprefix(String endsortkeyprefix) {

		this.endsortkeyprefix = endsortkeyprefix;

		return this;
	}

	/**Sortkey prefix to end listing <strong>before</strong> (not <strong>at</strong>; if this value occurs it will not be included!). Can only be used with cmsort=sortkey. Overrides cmendhexsortkey.
	 */
	public String getEndsortkeyprefix() {
		return this.endsortkeyprefix;
	}

	public ContainsPageRef getTitle() {
		return this.title;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryCategorymembers(");

		if (title != null) {
			sb.append("title=").append(title).append(", ");
		}

		if (prop != null) {

			sb.append("cmprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (namespace != null) {

			sb.append("cmnamespace")
					.append("=")
					.append(
							namespace.stream()
									.map(v -> Integer.toString(v.getId()))
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (type != null) {

			sb.append("cmtype")
					.append("=")
					.append(
							type.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("cmlimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		if (sort != null) {

			sb.append("cmsort").append("=").append(sort.getJsonValue());

			sb.append(", ");
		}

		if (dir != null) {

			sb.append("cmdir").append("=").append(dir.getJsonValue());

			sb.append(", ");
		}

		if (start != null) {

			sb.append("cmstart")
					.append("=")
					.append(start.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());

			sb.append(", ");
		}

		if (end != null) {

			sb.append("cmend")
					.append("=")
					.append(end.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());

			sb.append(", ");
		}

		if (starthexsortkey != null) {

			sb.append("cmstarthexsortkey").append("=").append(starthexsortkey);

			sb.append(", ");
		}

		if (endhexsortkey != null) {

			sb.append("cmendhexsortkey").append("=").append(endhexsortkey);

			sb.append(", ");
		}

		if (startsortkeyprefix != null) {

			sb.append("cmstartsortkeyprefix").append("=").append(startsortkeyprefix);

			sb.append(", ");
		}

		if (endsortkeyprefix != null) {

			sb.append("cmendsortkeyprefix").append("=").append(endsortkeyprefix);

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (title != null) {

			if (title.toPageRef().hasId()) {
				req.addParameter(
						paramPrefix + "cmpageid", Integer.toString(title.toPageRef().getId()));
			} else {
				req.addParameter(
						paramPrefix + "cmtitle", title.toPageRef().getTitle().toFullTitle());
			}
		}

		if (prop != null) {

			req.addParameter(
					paramPrefix + "cmprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (namespace != null) {

			req.addParameter(
					paramPrefix + "cmnamespace",
					namespace.stream()
							.map(v -> Integer.toString(v.getId()))
							.collect(Collectors.joining("|")));
		}

		if (type != null) {

			req.addParameter(
					paramPrefix + "cmtype",
					type.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "cmlimit", limit.toString());
		}

		if (sort != null) {

			req.addParameter(paramPrefix + "cmsort", sort.getJsonValue());
		}

		if (dir != null) {

			req.addParameter(paramPrefix + "cmdir", dir.getJsonValue());
		}

		if (start != null) {

			req.addParameter(
					paramPrefix + "cmstart",
					start.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());
		}

		if (end != null) {

			req.addParameter(
					paramPrefix + "cmend",
					end.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());
		}

		if (starthexsortkey != null) {

			req.addParameter(paramPrefix + "cmstarthexsortkey", starthexsortkey);
		}

		if (endhexsortkey != null) {

			req.addParameter(paramPrefix + "cmendhexsortkey", endhexsortkey);
		}

		if (startsortkeyprefix != null) {

			req.addParameter(paramPrefix + "cmstartsortkeyprefix", startsortkeyprefix);
		}

		if (endsortkeyprefix != null) {

			req.addParameter(paramPrefix + "cmendsortkeyprefix", endsortkeyprefix);
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
			c.accept(AAPIQueryCategorymembers.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryCategorymembers.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return false;
		}
	}
}
