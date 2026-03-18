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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryRevisionsProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryRevisionsSlots;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryRevisionsDir;

import io.github.pfwikis.bots.common.api.generated.params.AAPIImagerotateGenerator.AAPIImagerotateGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIPurgeGenerator.AAPIPurgeGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryProp.AAPIQueryPropModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryGenerator.AAPIQueryGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPISetnotificationtimestampGenerator.AAPISetnotificationtimestampGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPITemplatedataGenerator.AAPITemplatedataGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIWatchGenerator.AAPIWatchGeneratorModule;

/** <p>Get revision information.
 * </p>
 * <p>May be used in several ways:
 * </p>
 * <ol><li>Get data about a set of pages (last revision), by setting titles or pageids.</li>
 * <li>Get revisions for one given page, by using titles or pageids with start, end, or limit.</li>
 * <li>Get data about a set of revisions by setting their IDs with revids.</li></ol>
 */
public class AAPIQueryRevisions
		implements AAPIModule,
				AAPIImagerotateGeneratorModule,
				AAPIPurgeGeneratorModule,
				AAPIQueryPropModule,
				AAPIQueryGeneratorModule,
				AAPISetnotificationtimestampGeneratorModule,
				AAPITemplatedataGeneratorModule,
				AAPIWatchGeneratorModule {

	public static AAPIQueryRevisions create() {

		AAPIQueryRevisions v = new AAPIQueryRevisions();

		return v;
	}

	private AAPIQueryRevisions() {}

	private List<AAPIQueryRevisionsProp> prop;

	private List<AAPIQueryRevisionsSlots> slots;

	private Integer limit;

	private String section;

	private Long startid;

	private Long endid;

	private java.time.Instant start;

	private java.time.Instant end;

	private AAPIQueryRevisionsDir dir;

	private String user;

	private String excludeuser;

	private String tag;

	/**<p>Which properties to get for each revision:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryRevisions prop(AAPIQueryRevisionsProp... prop) {

		this.prop = List.of(prop);

		return this;
	}

	/**<p>Which properties to get for each revision:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryRevisionsProp> getProp() {
		return this.prop;
	}

	/**Which revision slots to return data for, when slot-related properties are included in <var>rvprops</var>. If omitted, data from the <kbd>main</kbd> slot will be returned in a backwards-compatible format.
	 */
	public AAPIQueryRevisions slots(AAPIQueryRevisionsSlots... slots) {

		this.slots = List.of(slots);

		return this;
	}

	/**Which revision slots to return data for, when slot-related properties are included in <var>rvprops</var>. If omitted, data from the <kbd>main</kbd> slot will be returned in a backwards-compatible format.
	 */
	public List<AAPIQueryRevisionsSlots> getSlots() {
		return this.slots;
	}

	/**Limit how many revisions will be returned. If <var>rvprop=content</var>, <var>rvprop=parsetree</var>, <var>rvdiffto</var> or <var>rvdifftotext</var> is used, the limit is 50. If <var>rvparse</var> is used, the limit is 1.
	 */
	public AAPIQueryRevisions limit(Integer limit) {

		this.limit = limit;

		return this;
	}

	/**Limit how many revisions will be returned. If <var>rvprop=content</var>, <var>rvprop=parsetree</var>, <var>rvdiffto</var> or <var>rvdifftotext</var> is used, the limit is 50. If <var>rvparse</var> is used, the limit is 1.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	/**Only retrieve the content of the section with this identifier.
	 */
	public AAPIQueryRevisions section(String section) {

		this.section = section;

		return this;
	}

	/**Only retrieve the content of the section with this identifier.
	 */
	public String getSection() {
		return this.section;
	}

	/**Start enumeration from this revision's timestamp. The revision must exist, but need not belong to this page.
	 */
	public AAPIQueryRevisions startid(Long startid) {

		this.startid = startid;

		return this;
	}

	/**Start enumeration from this revision's timestamp. The revision must exist, but need not belong to this page.
	 */
	public Long getStartid() {
		return this.startid;
	}

	/**Stop enumeration at this revision's timestamp. The revision must exist, but need not belong to this page.
	 */
	public AAPIQueryRevisions endid(Long endid) {

		this.endid = endid;

		return this;
	}

	/**Stop enumeration at this revision's timestamp. The revision must exist, but need not belong to this page.
	 */
	public Long getEndid() {
		return this.endid;
	}

	/**From which revision timestamp to start enumeration.
	 */
	public AAPIQueryRevisions start(java.time.Instant start) {

		this.start = start;

		return this;
	}

	/**From which revision timestamp to start enumeration.
	 */
	public java.time.Instant getStart() {
		return this.start;
	}

	/**Enumerate up to this timestamp.
	 */
	public AAPIQueryRevisions end(java.time.Instant end) {

		this.end = end;

		return this;
	}

	/**Enumerate up to this timestamp.
	 */
	public java.time.Instant getEnd() {
		return this.end;
	}

	/**<p>In which direction to enumerate:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryRevisions dir(AAPIQueryRevisionsDir dir) {

		this.dir = dir;

		return this;
	}

	/**<p>In which direction to enumerate:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryRevisionsDir getDir() {
		return this.dir;
	}

	/**Only include revisions made by user.
	 */
	public AAPIQueryRevisions user(String user) {

		this.user = user;

		return this;
	}

	/**Only include revisions made by user.
	 */
	public String getUser() {
		return this.user;
	}

	/**Exclude revisions made by user.
	 */
	public AAPIQueryRevisions excludeuser(String excludeuser) {

		this.excludeuser = excludeuser;

		return this;
	}

	/**Exclude revisions made by user.
	 */
	public String getExcludeuser() {
		return this.excludeuser;
	}

	/**Only list revisions tagged with this tag.
	 */
	public AAPIQueryRevisions tag(String tag) {

		this.tag = tag;

		return this;
	}

	/**Only list revisions tagged with this tag.
	 */
	public String getTag() {
		return this.tag;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryRevisions(");

		if (prop != null) {

			sb.append("rvprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (slots != null) {

			sb.append("rvslots")
					.append("=")
					.append(
							slots.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("rvlimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		if (section != null) {

			sb.append("rvsection").append("=").append(section);

			sb.append(", ");
		}

		if (startid != null) {

			sb.append("rvstartid").append("=").append(startid.toString());

			sb.append(", ");
		}

		if (endid != null) {

			sb.append("rvendid").append("=").append(endid.toString());

			sb.append(", ");
		}

		if (start != null) {

			sb.append("rvstart")
					.append("=")
					.append(start.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());

			sb.append(", ");
		}

		if (end != null) {

			sb.append("rvend")
					.append("=")
					.append(end.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());

			sb.append(", ");
		}

		if (dir != null) {

			sb.append("rvdir").append("=").append(dir.getJsonValue());

			sb.append(", ");
		}

		if (user != null) {

			sb.append("rvuser").append("=").append(user);

			sb.append(", ");
		}

		if (excludeuser != null) {

			sb.append("rvexcludeuser").append("=").append(excludeuser);

			sb.append(", ");
		}

		if (tag != null) {

			sb.append("rvtag").append("=").append(tag);

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (prop != null) {

			req.addParameter(
					paramPrefix + "rvprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (slots != null) {

			req.addParameter(
					paramPrefix + "rvslots",
					slots.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "rvlimit", limit.toString());

		} else {
			req.addParameter(paramPrefix + "rvlimit", "5000");
		}

		if (section != null) {

			req.addParameter(paramPrefix + "rvsection", section);
		}

		if (startid != null) {

			req.addParameter(paramPrefix + "rvstartid", startid.toString());
		}

		if (endid != null) {

			req.addParameter(paramPrefix + "rvendid", endid.toString());
		}

		if (start != null) {

			req.addParameter(
					paramPrefix + "rvstart",
					start.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());
		}

		if (end != null) {

			req.addParameter(
					paramPrefix + "rvend",
					end.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());
		}

		if (dir != null) {

			req.addParameter(paramPrefix + "rvdir", dir.getJsonValue());
		}

		if (user != null) {

			req.addParameter(paramPrefix + "rvuser", user);
		}

		if (excludeuser != null) {

			req.addParameter(paramPrefix + "rvexcludeuser", excludeuser);
		}

		if (tag != null) {

			req.addParameter(paramPrefix + "rvtag", tag);
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
			c.accept(AAPIQueryRevisions.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryRevisions.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return false;
		}

		@Override
		protected boolean internalRequiresPagination() {
			return limit != null;
		}
	}
}
