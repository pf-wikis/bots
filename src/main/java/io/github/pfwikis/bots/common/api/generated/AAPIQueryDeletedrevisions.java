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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryDeletedrevisionsProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryDeletedrevisionsSlots;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryDeletedrevisionsDir;

import io.github.pfwikis.bots.common.api.generated.params.AAPIImagerotateGenerator.AAPIImagerotateGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIPurgeGenerator.AAPIPurgeGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryProp.AAPIQueryPropModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryGenerator.AAPIQueryGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPISetnotificationtimestampGenerator.AAPISetnotificationtimestampGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPITemplatedataGenerator.AAPITemplatedataGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIWatchGenerator.AAPIWatchGeneratorModule;

/** <p>Get deleted revision information.
 * </p>
 * <p>May be used in several ways:
 * </p>
 * <ol><li>Get deleted revisions for a set of pages, by setting titles or pageids. Ordered by title and timestamp.</li>
 * <li>Get data about a set of deleted revisions by setting their IDs with revids. Ordered by revision ID.</li></ol>
 */
public class AAPIQueryDeletedrevisions
		implements AAPIModule,
				AAPIImagerotateGeneratorModule,
				AAPIPurgeGeneratorModule,
				AAPIQueryPropModule,
				AAPIQueryGeneratorModule,
				AAPISetnotificationtimestampGeneratorModule,
				AAPITemplatedataGeneratorModule,
				AAPIWatchGeneratorModule {

	public static AAPIQueryDeletedrevisions create() {

		AAPIQueryDeletedrevisions v = new AAPIQueryDeletedrevisions();

		return v;
	}

	private AAPIQueryDeletedrevisions() {}

	private List<AAPIQueryDeletedrevisionsProp> prop;

	private List<AAPIQueryDeletedrevisionsSlots> slots;

	private Integer limit;

	private String section;

	private java.time.Instant start;

	private java.time.Instant end;

	private AAPIQueryDeletedrevisionsDir dir;

	private String tag;

	private String user;

	private String excludeuser;

	/**<p>Which properties to get for each revision:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryDeletedrevisions prop(AAPIQueryDeletedrevisionsProp... prop) {

		this.prop = List.of(prop);

		return this;
	}

	/**<p>Which properties to get for each revision:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryDeletedrevisionsProp> getProp() {
		return this.prop;
	}

	/**Which revision slots to return data for, when slot-related properties are included in <var>drvprops</var>. If omitted, data from the <kbd>main</kbd> slot will be returned in a backwards-compatible format.
	 */
	public AAPIQueryDeletedrevisions slots(AAPIQueryDeletedrevisionsSlots... slots) {

		this.slots = List.of(slots);

		return this;
	}

	/**Which revision slots to return data for, when slot-related properties are included in <var>drvprops</var>. If omitted, data from the <kbd>main</kbd> slot will be returned in a backwards-compatible format.
	 */
	public List<AAPIQueryDeletedrevisionsSlots> getSlots() {
		return this.slots;
	}

	/**Limit how many revisions will be returned. If <var>drvprop=content</var>, <var>drvprop=parsetree</var>, <var>drvdiffto</var> or <var>drvdifftotext</var> is used, the limit is 50. If <var>drvparse</var> is used, the limit is 1.
	 */
	public AAPIQueryDeletedrevisions limit(Integer limit) {

		this.limit = limit;

		return this;
	}

	/**Limit how many revisions will be returned. If <var>drvprop=content</var>, <var>drvprop=parsetree</var>, <var>drvdiffto</var> or <var>drvdifftotext</var> is used, the limit is 50. If <var>drvparse</var> is used, the limit is 1.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	/**Only retrieve the content of the section with this identifier.
	 */
	public AAPIQueryDeletedrevisions section(String section) {

		this.section = section;

		return this;
	}

	/**Only retrieve the content of the section with this identifier.
	 */
	public String getSection() {
		return this.section;
	}

	/**The timestamp to start enumerating from. Ignored when processing a list of revision IDs.
	 */
	public AAPIQueryDeletedrevisions start(java.time.Instant start) {

		this.start = start;

		return this;
	}

	/**The timestamp to start enumerating from. Ignored when processing a list of revision IDs.
	 */
	public java.time.Instant getStart() {
		return this.start;
	}

	/**The timestamp to stop enumerating at. Ignored when processing a list of revision IDs.
	 */
	public AAPIQueryDeletedrevisions end(java.time.Instant end) {

		this.end = end;

		return this;
	}

	/**The timestamp to stop enumerating at. Ignored when processing a list of revision IDs.
	 */
	public java.time.Instant getEnd() {
		return this.end;
	}

	/**<p>In which direction to enumerate:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryDeletedrevisions dir(AAPIQueryDeletedrevisionsDir dir) {

		this.dir = dir;

		return this;
	}

	/**<p>In which direction to enumerate:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryDeletedrevisionsDir getDir() {
		return this.dir;
	}

	/**Only list revisions tagged with this tag.
	 */
	public AAPIQueryDeletedrevisions tag(String tag) {

		this.tag = tag;

		return this;
	}

	/**Only list revisions tagged with this tag.
	 */
	public String getTag() {
		return this.tag;
	}

	/**Only list revisions by this user.
	 */
	public AAPIQueryDeletedrevisions user(String user) {

		this.user = user;

		return this;
	}

	/**Only list revisions by this user.
	 */
	public String getUser() {
		return this.user;
	}

	/**Don't list revisions by this user.
	 */
	public AAPIQueryDeletedrevisions excludeuser(String excludeuser) {

		this.excludeuser = excludeuser;

		return this;
	}

	/**Don't list revisions by this user.
	 */
	public String getExcludeuser() {
		return this.excludeuser;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryDeletedrevisions(");

		if (prop != null) {

			sb.append("drvprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (slots != null) {

			sb.append("drvslots")
					.append("=")
					.append(
							slots.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("drvlimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		if (section != null) {

			sb.append("drvsection").append("=").append(section);

			sb.append(", ");
		}

		if (start != null) {

			sb.append("drvstart")
					.append("=")
					.append(start.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());

			sb.append(", ");
		}

		if (end != null) {

			sb.append("drvend")
					.append("=")
					.append(end.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());

			sb.append(", ");
		}

		if (dir != null) {

			sb.append("drvdir").append("=").append(dir.getJsonValue());

			sb.append(", ");
		}

		if (tag != null) {

			sb.append("drvtag").append("=").append(tag);

			sb.append(", ");
		}

		if (user != null) {

			sb.append("drvuser").append("=").append(user);

			sb.append(", ");
		}

		if (excludeuser != null) {

			sb.append("drvexcludeuser").append("=").append(excludeuser);

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (prop != null) {

			req.addParameter(
					paramPrefix + "drvprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (slots != null) {

			req.addParameter(
					paramPrefix + "drvslots",
					slots.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "drvlimit", limit.toString());

		} else {
			req.addParameter(paramPrefix + "drvlimit", "5000");
		}

		if (section != null) {

			req.addParameter(paramPrefix + "drvsection", section);
		}

		if (start != null) {

			req.addParameter(
					paramPrefix + "drvstart",
					start.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());
		}

		if (end != null) {

			req.addParameter(
					paramPrefix + "drvend",
					end.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());
		}

		if (dir != null) {

			req.addParameter(paramPrefix + "drvdir", dir.getJsonValue());
		}

		if (tag != null) {

			req.addParameter(paramPrefix + "drvtag", tag);
		}

		if (user != null) {

			req.addParameter(paramPrefix + "drvuser", user);
		}

		if (excludeuser != null) {

			req.addParameter(paramPrefix + "drvexcludeuser", excludeuser);
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
			c.accept(AAPIQueryDeletedrevisions.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryDeletedrevisions.this);

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
