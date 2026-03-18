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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryRecentchangesDir;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryRecentchangesProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryRecentchangesShow;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryRecentchangesType;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryRecentchangesSlot;

import io.github.pfwikis.bots.common.api.generated.params.AAPIImagerotateGenerator.AAPIImagerotateGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIPurgeGenerator.AAPIPurgeGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryList.AAPIQueryListModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryGenerator.AAPIQueryGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPISetnotificationtimestampGenerator.AAPISetnotificationtimestampGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPITemplatedataGenerator.AAPITemplatedataGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIWatchGenerator.AAPIWatchGeneratorModule;

/** Enumerate recent changes.
 */
public class AAPIQueryRecentchanges
		implements AAPIModule,
				AAPIImagerotateGeneratorModule,
				AAPIPurgeGeneratorModule,
				AAPIQueryListModule,
				AAPIQueryGeneratorModule,
				AAPISetnotificationtimestampGeneratorModule,
				AAPITemplatedataGeneratorModule,
				AAPIWatchGeneratorModule {

	public static AAPIQueryRecentchanges create() {

		AAPIQueryRecentchanges v = new AAPIQueryRecentchanges();

		return v;
	}

	private AAPIQueryRecentchanges() {}

	private java.time.Instant start;

	private java.time.Instant end;

	private AAPIQueryRecentchangesDir dir;

	private List<NS> namespace;

	private String user;

	private String excludeuser;

	private String tag;

	private List<AAPIQueryRecentchangesProp> prop;

	private List<AAPIQueryRecentchangesShow> show;

	private Integer limit;

	private List<AAPIQueryRecentchangesType> type;

	private Boolean toponly;

	private String title;

	private Boolean generaterevisions;

	private AAPIQueryRecentchangesSlot slot;

	/**The timestamp to start enumerating from.
	 */
	public AAPIQueryRecentchanges start(java.time.Instant start) {

		this.start = start;

		return this;
	}

	/**The timestamp to start enumerating from.
	 */
	public java.time.Instant getStart() {
		return this.start;
	}

	/**The timestamp to end enumerating.
	 */
	public AAPIQueryRecentchanges end(java.time.Instant end) {

		this.end = end;

		return this;
	}

	/**The timestamp to end enumerating.
	 */
	public java.time.Instant getEnd() {
		return this.end;
	}

	/**<p>In which direction to enumerate:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryRecentchanges dir(AAPIQueryRecentchangesDir dir) {

		this.dir = dir;

		return this;
	}

	/**<p>In which direction to enumerate:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryRecentchangesDir getDir() {
		return this.dir;
	}

	/**Filter changes to only these namespaces.
	 */
	public AAPIQueryRecentchanges namespace(NS... namespace) {

		this.namespace = List.of(namespace);

		return this;
	}

	/**Filter changes to only these namespaces.
	 */
	public List<NS> getNamespace() {
		return this.namespace;
	}

	/**Only list changes by this user.
	 */
	public AAPIQueryRecentchanges user(String user) {

		this.user = user;

		return this;
	}

	/**Only list changes by this user.
	 */
	public String getUser() {
		return this.user;
	}

	/**Don't list changes by this user.
	 */
	public AAPIQueryRecentchanges excludeuser(String excludeuser) {

		this.excludeuser = excludeuser;

		return this;
	}

	/**Don't list changes by this user.
	 */
	public String getExcludeuser() {
		return this.excludeuser;
	}

	/**Only list changes tagged with this tag.
	 */
	public AAPIQueryRecentchanges tag(String tag) {

		this.tag = tag;

		return this;
	}

	/**Only list changes tagged with this tag.
	 */
	public String getTag() {
		return this.tag;
	}

	/**<p>Include additional pieces of information:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryRecentchanges prop(AAPIQueryRecentchangesProp... prop) {

		this.prop = List.of(prop);

		return this;
	}

	/**<p>Include additional pieces of information:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryRecentchangesProp> getProp() {
		return this.prop;
	}

	/**Show only items that meet these criteria. For example, to see only minor edits done by logged-in users, set rcshow=minor|!anon.
	 */
	public AAPIQueryRecentchanges show(AAPIQueryRecentchangesShow... show) {

		this.show = List.of(show);

		return this;
	}

	/**Show only items that meet these criteria. For example, to see only minor edits done by logged-in users, set rcshow=minor|!anon.
	 */
	public List<AAPIQueryRecentchangesShow> getShow() {
		return this.show;
	}

	/**How many total changes to return.
	 */
	public AAPIQueryRecentchanges limit(Integer limit) {

		this.limit = limit;

		return this;
	}

	/**How many total changes to return.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	/**Which types of changes to show.
	 */
	public AAPIQueryRecentchanges type(AAPIQueryRecentchangesType... type) {

		this.type = List.of(type);

		return this;
	}

	/**Which types of changes to show.
	 */
	public List<AAPIQueryRecentchangesType> getType() {
		return this.type;
	}

	/**Only list changes which are the latest revision.
	 */
	public AAPIQueryRecentchanges toponly(Boolean toponly) {

		this.toponly = toponly;

		return this;
	}

	/**Only list changes which are the latest revision.
	 */
	public Boolean getToponly() {
		return this.toponly;
	}

	/**Filter entries to those related to a page.
	 */
	public AAPIQueryRecentchanges title(String title) {

		this.title = title;

		return this;
	}

	/**Filter entries to those related to a page.
	 */
	public String getTitle() {
		return this.title;
	}

	/**When being used as a generator, generate revision IDs rather than titles. Recent change entries without associated revision IDs (e.g. most log entries) will generate nothing.
	 */
	public AAPIQueryRecentchanges generaterevisions(Boolean generaterevisions) {

		this.generaterevisions = generaterevisions;

		return this;
	}

	/**When being used as a generator, generate revision IDs rather than titles. Recent change entries without associated revision IDs (e.g. most log entries) will generate nothing.
	 */
	public Boolean getGeneraterevisions() {
		return this.generaterevisions;
	}

	/**Only list changes that touch the named slot.
	 */
	public AAPIQueryRecentchanges slot(AAPIQueryRecentchangesSlot slot) {

		this.slot = slot;

		return this;
	}

	/**Only list changes that touch the named slot.
	 */
	public AAPIQueryRecentchangesSlot getSlot() {
		return this.slot;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryRecentchanges(");

		if (start != null) {

			sb.append("rcstart")
					.append("=")
					.append(start.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());

			sb.append(", ");
		}

		if (end != null) {

			sb.append("rcend")
					.append("=")
					.append(end.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());

			sb.append(", ");
		}

		if (dir != null) {

			sb.append("rcdir").append("=").append(dir.getJsonValue());

			sb.append(", ");
		}

		if (namespace != null) {

			sb.append("rcnamespace")
					.append("=")
					.append(
							namespace.stream()
									.map(v -> Integer.toString(v.getId()))
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (user != null) {

			sb.append("rcuser").append("=").append(user);

			sb.append(", ");
		}

		if (excludeuser != null) {

			sb.append("rcexcludeuser").append("=").append(excludeuser);

			sb.append(", ");
		}

		if (tag != null) {

			sb.append("rctag").append("=").append(tag);

			sb.append(", ");
		}

		if (prop != null) {

			sb.append("rcprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (show != null) {

			sb.append("rcshow")
					.append("=")
					.append(
							show.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("rclimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		if (type != null) {

			sb.append("rctype")
					.append("=")
					.append(
							type.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (toponly != null) {

			sb.append("rctoponly").append("=").append(toponly.toString());

			sb.append(", ");
		}

		if (title != null) {

			sb.append("rctitle").append("=").append(title);

			sb.append(", ");
		}

		if (generaterevisions != null) {

			sb.append("rcgeneraterevisions").append("=").append(generaterevisions.toString());

			sb.append(", ");
		}

		if (slot != null) {

			sb.append("rcslot").append("=").append(slot.getJsonValue());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (start != null) {

			req.addParameter(
					paramPrefix + "rcstart",
					start.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());
		}

		if (end != null) {

			req.addParameter(
					paramPrefix + "rcend",
					end.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());
		}

		if (dir != null) {

			req.addParameter(paramPrefix + "rcdir", dir.getJsonValue());
		}

		if (namespace != null) {

			req.addParameter(
					paramPrefix + "rcnamespace",
					namespace.stream()
							.map(v -> Integer.toString(v.getId()))
							.collect(Collectors.joining("|")));
		}

		if (user != null) {

			req.addParameter(paramPrefix + "rcuser", user);
		}

		if (excludeuser != null) {

			req.addParameter(paramPrefix + "rcexcludeuser", excludeuser);
		}

		if (tag != null) {

			req.addParameter(paramPrefix + "rctag", tag);
		}

		if (prop != null) {

			req.addParameter(
					paramPrefix + "rcprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (show != null) {

			req.addParameter(
					paramPrefix + "rcshow",
					show.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "rclimit", limit.toString());

		} else {
			req.addParameter(paramPrefix + "rclimit", "5000");
		}

		if (type != null) {

			req.addParameter(
					paramPrefix + "rctype",
					type.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (toponly != null) {

			req.addParameter(paramPrefix + "rctoponly", toponly.toString());
		}

		if (title != null) {

			req.addParameter(paramPrefix + "rctitle", title);
		}

		if (generaterevisions != null) {

			req.addParameter(paramPrefix + "rcgeneraterevisions", generaterevisions.toString());
		}

		if (slot != null) {

			req.addParameter(paramPrefix + "rcslot", slot.getJsonValue());
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
			c.accept(AAPIQueryRecentchanges.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryRecentchanges.this);

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
