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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryAlldeletedrevisionsProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryAlldeletedrevisionsSlots;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryAlldeletedrevisionsDir;

import io.github.pfwikis.bots.common.api.generated.params.AAPIImagerotateGenerator.AAPIImagerotateGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIPurgeGenerator.AAPIPurgeGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryList.AAPIQueryListModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryGenerator.AAPIQueryGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPISetnotificationtimestampGenerator.AAPISetnotificationtimestampGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPITemplatedataGenerator.AAPITemplatedataGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIWatchGenerator.AAPIWatchGeneratorModule;

/** List all deleted revisions by a user or in a namespace.
 */
public class AAPIQueryAlldeletedrevisions
		implements AAPIModule,
				AAPIImagerotateGeneratorModule,
				AAPIPurgeGeneratorModule,
				AAPIQueryListModule,
				AAPIQueryGeneratorModule,
				AAPISetnotificationtimestampGeneratorModule,
				AAPITemplatedataGeneratorModule,
				AAPIWatchGeneratorModule {

	public static AAPIQueryAlldeletedrevisions create() {

		AAPIQueryAlldeletedrevisions v = new AAPIQueryAlldeletedrevisions();

		return v;
	}

	private AAPIQueryAlldeletedrevisions() {}

	private List<AAPIQueryAlldeletedrevisionsProp> prop;

	private List<AAPIQueryAlldeletedrevisionsSlots> slots;

	private Integer limit;

	private String section;

	private String user;

	private List<NS> namespace;

	private java.time.Instant start;

	private java.time.Instant end;

	private AAPIQueryAlldeletedrevisionsDir dir;

	private String from;

	private String to;

	private String prefix;

	private String excludeuser;

	private String tag;

	private Boolean generatetitles;

	/**<p>Which properties to get for each revision:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryAlldeletedrevisions prop(AAPIQueryAlldeletedrevisionsProp prop) {
		this.prop = List.of(prop);

		return this;
	}

	/**<p>Which properties to get for each revision:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryAlldeletedrevisions prop(AAPIQueryAlldeletedrevisionsProp... prop) {
		this.prop = List.of(prop);
		return this;
	}

	/**<p>Which properties to get for each revision:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryAlldeletedrevisionsProp> getProp() {
		return this.prop;
	}

	/**Which revision slots to return data for, when slot-related properties are included in <var>adrprops</var>. If omitted, data from the <kbd>main</kbd> slot will be returned in a backwards-compatible format.
	 */
	public AAPIQueryAlldeletedrevisions slots(AAPIQueryAlldeletedrevisionsSlots slots) {
		this.slots = List.of(slots);

		return this;
	}

	/**Which revision slots to return data for, when slot-related properties are included in <var>adrprops</var>. If omitted, data from the <kbd>main</kbd> slot will be returned in a backwards-compatible format.
	 */
	public AAPIQueryAlldeletedrevisions slots(AAPIQueryAlldeletedrevisionsSlots... slots) {
		this.slots = List.of(slots);
		return this;
	}

	/**Which revision slots to return data for, when slot-related properties are included in <var>adrprops</var>. If omitted, data from the <kbd>main</kbd> slot will be returned in a backwards-compatible format.
	 */
	public List<AAPIQueryAlldeletedrevisionsSlots> getSlots() {
		return this.slots;
	}

	/**Limit how many revisions will be returned. If <var>adrprop=content</var>, <var>adrprop=parsetree</var>, <var>adrdiffto</var> or <var>adrdifftotext</var> is used, the limit is 50. If <var>adrparse</var> is used, the limit is 1.
	 */
	public AAPIQueryAlldeletedrevisions limit(Integer limit) {
		this.limit = limit;

		return this;
	}

	/**Limit how many revisions will be returned. If <var>adrprop=content</var>, <var>adrprop=parsetree</var>, <var>adrdiffto</var> or <var>adrdifftotext</var> is used, the limit is 50. If <var>adrparse</var> is used, the limit is 1.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	/**Only retrieve the content of the section with this identifier.
	 */
	public AAPIQueryAlldeletedrevisions section(String section) {
		this.section = section;

		return this;
	}

	/**Only retrieve the content of the section with this identifier.
	 */
	public String getSection() {
		return this.section;
	}

	/**Only list revisions by this user.
	 */
	public AAPIQueryAlldeletedrevisions user(String user) {
		this.user = user;

		return this;
	}

	/**Only list revisions by this user.
	 */
	public String getUser() {
		return this.user;
	}

	/**Only list pages in this namespace.
	 */
	public AAPIQueryAlldeletedrevisions namespace(NS namespace) {
		this.namespace = List.of(namespace);

		return this;
	}

	/**Only list pages in this namespace.
	 */
	public AAPIQueryAlldeletedrevisions namespace(NS... namespace) {
		this.namespace = List.of(namespace);
		return this;
	}

	/**Only list pages in this namespace.
	 */
	public List<NS> getNamespace() {
		return this.namespace;
	}

	/**The timestamp to start enumerating from.
	 */
	public AAPIQueryAlldeletedrevisions start(java.time.Instant start) {
		this.start = start;

		return this;
	}

	/**The timestamp to start enumerating from.
	 */
	public java.time.Instant getStart() {
		return this.start;
	}

	/**The timestamp to stop enumerating at.
	 */
	public AAPIQueryAlldeletedrevisions end(java.time.Instant end) {
		this.end = end;

		return this;
	}

	/**The timestamp to stop enumerating at.
	 */
	public java.time.Instant getEnd() {
		return this.end;
	}

	/**<p>In which direction to enumerate:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryAlldeletedrevisions dir(AAPIQueryAlldeletedrevisionsDir dir) {
		this.dir = dir;

		return this;
	}

	/**<p>In which direction to enumerate:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryAlldeletedrevisionsDir getDir() {
		return this.dir;
	}

	/**Start listing at this title.
	 */
	public AAPIQueryAlldeletedrevisions from(String from) {
		this.from = from;

		return this;
	}

	/**Start listing at this title.
	 */
	public String getFrom() {
		return this.from;
	}

	/**Stop listing at this title.
	 */
	public AAPIQueryAlldeletedrevisions to(String to) {
		this.to = to;

		return this;
	}

	/**Stop listing at this title.
	 */
	public String getTo() {
		return this.to;
	}

	/**Search for all page titles that begin with this value.
	 */
	public AAPIQueryAlldeletedrevisions prefix(String prefix) {
		this.prefix = prefix;

		return this;
	}

	/**Search for all page titles that begin with this value.
	 */
	public String getPrefix() {
		return this.prefix;
	}

	/**Don't list revisions by this user.
	 */
	public AAPIQueryAlldeletedrevisions excludeuser(String excludeuser) {
		this.excludeuser = excludeuser;

		return this;
	}

	/**Don't list revisions by this user.
	 */
	public String getExcludeuser() {
		return this.excludeuser;
	}

	/**Only list revisions tagged with this tag.
	 */
	public AAPIQueryAlldeletedrevisions tag(String tag) {
		this.tag = tag;

		return this;
	}

	/**Only list revisions tagged with this tag.
	 */
	public String getTag() {
		return this.tag;
	}

	/**When being used as a generator, generate titles rather than revision IDs.
	 */
	public AAPIQueryAlldeletedrevisions generatetitles(Boolean generatetitles) {
		this.generatetitles = generatetitles;

		return this;
	}

	/**When being used as a generator, generate titles rather than revision IDs.
	 */
	public Boolean getGeneratetitles() {
		return this.generatetitles;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryAlldeletedrevisions(");

		if (prop != null) {

			sb.append("adrprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (slots != null) {

			sb.append("adrslots")
					.append("=")
					.append(
							slots.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("adrlimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		if (section != null) {

			sb.append("adrsection").append("=").append(section);

			sb.append(", ");
		}

		if (user != null) {

			sb.append("adruser").append("=").append(user);

			sb.append(", ");
		}

		if (namespace != null) {

			sb.append("adrnamespace")
					.append("=")
					.append(
							namespace.stream()
									.map(v -> Integer.toString(v.getId()))
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (start != null) {

			sb.append("adrstart")
					.append("=")
					.append(start.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());

			sb.append(", ");
		}

		if (end != null) {

			sb.append("adrend")
					.append("=")
					.append(end.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());

			sb.append(", ");
		}

		if (dir != null) {

			sb.append("adrdir").append("=").append(dir.getJsonValue());

			sb.append(", ");
		}

		if (from != null) {

			sb.append("adrfrom").append("=").append(from);

			sb.append(", ");
		}

		if (to != null) {

			sb.append("adrto").append("=").append(to);

			sb.append(", ");
		}

		if (prefix != null) {

			sb.append("adrprefix").append("=").append(prefix);

			sb.append(", ");
		}

		if (excludeuser != null) {

			sb.append("adrexcludeuser").append("=").append(excludeuser);

			sb.append(", ");
		}

		if (tag != null) {

			sb.append("adrtag").append("=").append(tag);

			sb.append(", ");
		}

		if (generatetitles != null) {

			sb.append("adrgeneratetitles").append("=").append(generatetitles.toString());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (prop != null) {

			req.addParameter(
					paramPrefix + "adrprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (slots != null) {

			req.addParameter(
					paramPrefix + "adrslots",
					slots.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "adrlimit", limit.toString());

		} else {
			req.addParameter(paramPrefix + "adrlimit", "5000");
		}

		if (section != null) {

			req.addParameter(paramPrefix + "adrsection", section);
		}

		if (user != null) {

			req.addParameter(paramPrefix + "adruser", user);
		}

		if (namespace != null) {

			req.addParameter(
					paramPrefix + "adrnamespace",
					namespace.stream()
							.map(v -> Integer.toString(v.getId()))
							.collect(Collectors.joining("|")));
		}

		if (start != null) {

			req.addParameter(
					paramPrefix + "adrstart",
					start.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());
		}

		if (end != null) {

			req.addParameter(
					paramPrefix + "adrend",
					end.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());
		}

		if (dir != null) {

			req.addParameter(paramPrefix + "adrdir", dir.getJsonValue());
		}

		if (from != null) {

			req.addParameter(paramPrefix + "adrfrom", from);
		}

		if (to != null) {

			req.addParameter(paramPrefix + "adrto", to);
		}

		if (prefix != null) {

			req.addParameter(paramPrefix + "adrprefix", prefix);
		}

		if (excludeuser != null) {

			req.addParameter(paramPrefix + "adrexcludeuser", excludeuser);
		}

		if (tag != null) {

			req.addParameter(paramPrefix + "adrtag", tag);
		}

		if (generatetitles != null) {

			req.addParameter(paramPrefix + "adrgeneratetitles", generatetitles.toString());
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
			c.accept(AAPIQueryAlldeletedrevisions.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryAlldeletedrevisions.this);

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
