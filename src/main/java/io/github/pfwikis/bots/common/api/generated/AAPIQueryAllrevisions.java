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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryAllrevisionsProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryAllrevisionsSlots;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryAllrevisionsDir;

import io.github.pfwikis.bots.common.api.generated.params.AAPIImagerotateGenerator.AAPIImagerotateGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIPurgeGenerator.AAPIPurgeGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryList.AAPIQueryListModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryGenerator.AAPIQueryGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPISetnotificationtimestampGenerator.AAPISetnotificationtimestampGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPITemplatedataGenerator.AAPITemplatedataGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIWatchGenerator.AAPIWatchGeneratorModule;

/** List all revisions.
 */
public class AAPIQueryAllrevisions
		implements AAPIModule,
				AAPIImagerotateGeneratorModule,
				AAPIPurgeGeneratorModule,
				AAPIQueryListModule,
				AAPIQueryGeneratorModule,
				AAPISetnotificationtimestampGeneratorModule,
				AAPITemplatedataGeneratorModule,
				AAPIWatchGeneratorModule {

	public static AAPIQueryAllrevisions create() {

		AAPIQueryAllrevisions v = new AAPIQueryAllrevisions();

		return v;
	}

	private AAPIQueryAllrevisions() {}

	private List<AAPIQueryAllrevisionsProp> prop;

	private List<AAPIQueryAllrevisionsSlots> slots;

	private Integer limit;

	private String section;

	private String user;

	private List<NS> namespace;

	private java.time.Instant start;

	private java.time.Instant end;

	private AAPIQueryAllrevisionsDir dir;

	private String excludeuser;

	private Boolean generatetitles;

	/**<p>Which properties to get for each revision:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryAllrevisions prop(AAPIQueryAllrevisionsProp... prop) {

		this.prop = List.of(prop);

		return this;
	}

	/**<p>Which properties to get for each revision:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryAllrevisionsProp> getProp() {
		return this.prop;
	}

	/**Which revision slots to return data for, when slot-related properties are included in <var>arvprops</var>. If omitted, data from the <kbd>main</kbd> slot will be returned in a backwards-compatible format.
	 */
	public AAPIQueryAllrevisions slots(AAPIQueryAllrevisionsSlots... slots) {

		this.slots = List.of(slots);

		return this;
	}

	/**Which revision slots to return data for, when slot-related properties are included in <var>arvprops</var>. If omitted, data from the <kbd>main</kbd> slot will be returned in a backwards-compatible format.
	 */
	public List<AAPIQueryAllrevisionsSlots> getSlots() {
		return this.slots;
	}

	/**Limit how many revisions will be returned. If <var>arvprop=content</var>, <var>arvprop=parsetree</var>, <var>arvdiffto</var> or <var>arvdifftotext</var> is used, the limit is 50. If <var>arvparse</var> is used, the limit is 1.
	 */
	public AAPIQueryAllrevisions limit(Integer limit) {

		this.limit = limit;

		return this;
	}

	/**Limit how many revisions will be returned. If <var>arvprop=content</var>, <var>arvprop=parsetree</var>, <var>arvdiffto</var> or <var>arvdifftotext</var> is used, the limit is 50. If <var>arvparse</var> is used, the limit is 1.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	/**Only retrieve the content of the section with this identifier.
	 */
	public AAPIQueryAllrevisions section(String section) {

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
	public AAPIQueryAllrevisions user(String user) {

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
	public AAPIQueryAllrevisions namespace(NS... namespace) {

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
	public AAPIQueryAllrevisions start(java.time.Instant start) {

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
	public AAPIQueryAllrevisions end(java.time.Instant end) {

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
	public AAPIQueryAllrevisions dir(AAPIQueryAllrevisionsDir dir) {

		this.dir = dir;

		return this;
	}

	/**<p>In which direction to enumerate:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryAllrevisionsDir getDir() {
		return this.dir;
	}

	/**Don't list revisions by this user.
	 */
	public AAPIQueryAllrevisions excludeuser(String excludeuser) {

		this.excludeuser = excludeuser;

		return this;
	}

	/**Don't list revisions by this user.
	 */
	public String getExcludeuser() {
		return this.excludeuser;
	}

	/**When being used as a generator, generate titles rather than revision IDs.
	 */
	public AAPIQueryAllrevisions generatetitles(Boolean generatetitles) {

		this.generatetitles = generatetitles;

		return this;
	}

	/**When being used as a generator, generate titles rather than revision IDs.
	 */
	public Boolean getGeneratetitles() {
		return this.generatetitles;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryAllrevisions(");

		if (prop != null) {

			sb.append("arvprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (slots != null) {

			sb.append("arvslots")
					.append("=")
					.append(
							slots.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("arvlimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		if (section != null) {

			sb.append("arvsection").append("=").append(section);

			sb.append(", ");
		}

		if (user != null) {

			sb.append("arvuser").append("=").append(user);

			sb.append(", ");
		}

		if (namespace != null) {

			sb.append("arvnamespace")
					.append("=")
					.append(
							namespace.stream()
									.map(v -> Integer.toString(v.getId()))
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (start != null) {

			sb.append("arvstart")
					.append("=")
					.append(start.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());

			sb.append(", ");
		}

		if (end != null) {

			sb.append("arvend")
					.append("=")
					.append(end.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());

			sb.append(", ");
		}

		if (dir != null) {

			sb.append("arvdir").append("=").append(dir.getJsonValue());

			sb.append(", ");
		}

		if (excludeuser != null) {

			sb.append("arvexcludeuser").append("=").append(excludeuser);

			sb.append(", ");
		}

		if (generatetitles != null) {

			sb.append("arvgeneratetitles").append("=").append(generatetitles.toString());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (prop != null) {

			req.addParameter(
					paramPrefix + "arvprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (slots != null) {

			req.addParameter(
					paramPrefix + "arvslots",
					slots.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "arvlimit", limit.toString());

		} else {
			req.addParameter(paramPrefix + "arvlimit", "5000");
		}

		if (section != null) {

			req.addParameter(paramPrefix + "arvsection", section);
		}

		if (user != null) {

			req.addParameter(paramPrefix + "arvuser", user);
		}

		if (namespace != null) {

			req.addParameter(
					paramPrefix + "arvnamespace",
					namespace.stream()
							.map(v -> Integer.toString(v.getId()))
							.collect(Collectors.joining("|")));
		}

		if (start != null) {

			req.addParameter(
					paramPrefix + "arvstart",
					start.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());
		}

		if (end != null) {

			req.addParameter(
					paramPrefix + "arvend",
					end.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());
		}

		if (dir != null) {

			req.addParameter(paramPrefix + "arvdir", dir.getJsonValue());
		}

		if (excludeuser != null) {

			req.addParameter(paramPrefix + "arvexcludeuser", excludeuser);
		}

		if (generatetitles != null) {

			req.addParameter(paramPrefix + "arvgeneratetitles", generatetitles.toString());
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
			c.accept(AAPIQueryAllrevisions.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryAllrevisions.this);

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
