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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryUsercontribsDir;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryUsercontribsProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryUsercontribsShow;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryList.AAPIQueryListModule;

/** Get all edits by a user.
 */
public class AAPIQueryUsercontribs implements AAPIModule, AAPIQueryListModule {

	public static AAPIQueryUsercontribs create() {

		AAPIQueryUsercontribs v = new AAPIQueryUsercontribs();

		return v;
	}

	private AAPIQueryUsercontribs() {}

	private Integer limit;

	private java.time.Instant start;

	private java.time.Instant end;

	private List<String> user;

	private List<Long> userids;

	private String userprefix;

	private String iprange;

	private AAPIQueryUsercontribsDir dir;

	private List<NS> namespace;

	private List<AAPIQueryUsercontribsProp> prop;

	private List<AAPIQueryUsercontribsShow> show;

	private String tag;

	/**The maximum number of contributions to return.
	 */
	public AAPIQueryUsercontribs limit(Integer limit) {
		this.limit = limit;

		return this;
	}

	/**The maximum number of contributions to return.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	/**The start timestamp to return from, i.e. revisions before this timestamp.
	 */
	public AAPIQueryUsercontribs start(java.time.Instant start) {
		this.start = start;

		return this;
	}

	/**The start timestamp to return from, i.e. revisions before this timestamp.
	 */
	public java.time.Instant getStart() {
		return this.start;
	}

	/**The end timestamp to return to, i.e. revisions after this timestamp.
	 */
	public AAPIQueryUsercontribs end(java.time.Instant end) {
		this.end = end;

		return this;
	}

	/**The end timestamp to return to, i.e. revisions after this timestamp.
	 */
	public java.time.Instant getEnd() {
		return this.end;
	}

	/**The users to retrieve contributions for. Cannot be used with <var>ucuserids</var>, <var>ucuserprefix</var>, or <var>uciprange</var>.
	 */
	public AAPIQueryUsercontribs user(String user) {
		this.user = List.of(user);

		return this;
	}

	/**The users to retrieve contributions for. Cannot be used with <var>ucuserids</var>, <var>ucuserprefix</var>, or <var>uciprange</var>.
	 */
	public AAPIQueryUsercontribs user(String... user) {
		this.user = List.of(user);
		return this;
	}

	/**The users to retrieve contributions for. Cannot be used with <var>ucuserids</var>, <var>ucuserprefix</var>, or <var>uciprange</var>.
	 */
	public List<String> getUser() {
		return this.user;
	}

	/**The user IDs to retrieve contributions for. Cannot be used with <var>ucuser</var>, <var>ucuserprefix</var>, or <var>uciprange</var>.
	 */
	public AAPIQueryUsercontribs userids(Long userids) {
		this.userids = List.of(userids);

		return this;
	}

	/**The user IDs to retrieve contributions for. Cannot be used with <var>ucuser</var>, <var>ucuserprefix</var>, or <var>uciprange</var>.
	 */
	public AAPIQueryUsercontribs userids(Long... userids) {
		this.userids = List.of(userids);
		return this;
	}

	/**The user IDs to retrieve contributions for. Cannot be used with <var>ucuser</var>, <var>ucuserprefix</var>, or <var>uciprange</var>.
	 */
	public List<Long> getUserids() {
		return this.userids;
	}

	/**Retrieve contributions for all users whose names begin with this value. Cannot be used with <var>ucuser</var>, <var>ucuserids</var>, or <var>uciprange</var>.
	 */
	public AAPIQueryUsercontribs userprefix(String userprefix) {
		this.userprefix = userprefix;

		return this;
	}

	/**Retrieve contributions for all users whose names begin with this value. Cannot be used with <var>ucuser</var>, <var>ucuserids</var>, or <var>uciprange</var>.
	 */
	public String getUserprefix() {
		return this.userprefix;
	}

	/**The CIDR range to retrieve contributions for. Cannot be used with <var>ucuser</var>, <var>ucuserprefix</var>, or <var>ucuserids</var>.
	 */
	public AAPIQueryUsercontribs iprange(String iprange) {
		this.iprange = iprange;

		return this;
	}

	/**The CIDR range to retrieve contributions for. Cannot be used with <var>ucuser</var>, <var>ucuserprefix</var>, or <var>ucuserids</var>.
	 */
	public String getIprange() {
		return this.iprange;
	}

	/**<p>In which direction to enumerate:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryUsercontribs dir(AAPIQueryUsercontribsDir dir) {
		this.dir = dir;

		return this;
	}

	/**<p>In which direction to enumerate:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryUsercontribsDir getDir() {
		return this.dir;
	}

	/**Only list contributions in these namespaces.
	 */
	public AAPIQueryUsercontribs namespace(NS namespace) {
		this.namespace = List.of(namespace);

		return this;
	}

	/**Only list contributions in these namespaces.
	 */
	public AAPIQueryUsercontribs namespace(NS... namespace) {
		this.namespace = List.of(namespace);
		return this;
	}

	/**Only list contributions in these namespaces.
	 */
	public List<NS> getNamespace() {
		return this.namespace;
	}

	/**<p>Include additional pieces of information:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryUsercontribs prop(AAPIQueryUsercontribsProp prop) {
		this.prop = List.of(prop);

		return this;
	}

	/**<p>Include additional pieces of information:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryUsercontribs prop(AAPIQueryUsercontribsProp... prop) {
		this.prop = List.of(prop);
		return this;
	}

	/**<p>Include additional pieces of information:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryUsercontribsProp> getProp() {
		return this.prop;
	}

	/**<p>Show only items that meet these criteria, e.g. non minor edits only: <kbd>ucshow=!minor</kbd>.
	 * </p><p>If <kbd>ucshow=patrolled</kbd> or <kbd>ucshow=!patrolled</kbd> is set, revisions older than <var><a href="/w/index.php?title=Mw:Special:MyLanguage/Manual:$wgRCMaxAge&amp;action=edit&amp;redlink=1" class="new" title="Mw:Special:MyLanguage/Manual:$wgRCMaxAge (page does not exist)">$wgRCMaxAge</a></var> (7776000 seconds) won't be shown.
	 * </p>
	 */
	public AAPIQueryUsercontribs show(AAPIQueryUsercontribsShow show) {
		this.show = List.of(show);

		return this;
	}

	/**<p>Show only items that meet these criteria, e.g. non minor edits only: <kbd>ucshow=!minor</kbd>.
	 * </p><p>If <kbd>ucshow=patrolled</kbd> or <kbd>ucshow=!patrolled</kbd> is set, revisions older than <var><a href="/w/index.php?title=Mw:Special:MyLanguage/Manual:$wgRCMaxAge&amp;action=edit&amp;redlink=1" class="new" title="Mw:Special:MyLanguage/Manual:$wgRCMaxAge (page does not exist)">$wgRCMaxAge</a></var> (7776000 seconds) won't be shown.
	 * </p>
	 */
	public AAPIQueryUsercontribs show(AAPIQueryUsercontribsShow... show) {
		this.show = List.of(show);
		return this;
	}

	/**<p>Show only items that meet these criteria, e.g. non minor edits only: <kbd>ucshow=!minor</kbd>.
	 * </p><p>If <kbd>ucshow=patrolled</kbd> or <kbd>ucshow=!patrolled</kbd> is set, revisions older than <var><a href="/w/index.php?title=Mw:Special:MyLanguage/Manual:$wgRCMaxAge&amp;action=edit&amp;redlink=1" class="new" title="Mw:Special:MyLanguage/Manual:$wgRCMaxAge (page does not exist)">$wgRCMaxAge</a></var> (7776000 seconds) won't be shown.
	 * </p>
	 */
	public List<AAPIQueryUsercontribsShow> getShow() {
		return this.show;
	}

	/**Only list revisions tagged with this tag.
	 */
	public AAPIQueryUsercontribs tag(String tag) {
		this.tag = tag;

		return this;
	}

	/**Only list revisions tagged with this tag.
	 */
	public String getTag() {
		return this.tag;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryUsercontribs(");

		if (limit != null) {

			sb.append("uclimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		if (start != null) {

			sb.append("ucstart")
					.append("=")
					.append(start.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());

			sb.append(", ");
		}

		if (end != null) {

			sb.append("ucend")
					.append("=")
					.append(end.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());

			sb.append(", ");
		}

		if (user != null) {

			sb.append("ucuser")
					.append("=")
					.append(user.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (userids != null) {

			sb.append("ucuserids")
					.append("=")
					.append(
							userids.stream()
									.map(v -> v.toString())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (userprefix != null) {

			sb.append("ucuserprefix").append("=").append(userprefix);

			sb.append(", ");
		}

		if (iprange != null) {

			sb.append("uciprange").append("=").append(iprange);

			sb.append(", ");
		}

		if (dir != null) {

			sb.append("ucdir").append("=").append(dir.getJsonValue());

			sb.append(", ");
		}

		if (namespace != null) {

			sb.append("ucnamespace")
					.append("=")
					.append(
							namespace.stream()
									.map(v -> Integer.toString(v.getId()))
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (prop != null) {

			sb.append("ucprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (show != null) {

			sb.append("ucshow")
					.append("=")
					.append(
							show.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (tag != null) {

			sb.append("uctag").append("=").append(tag);

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (limit != null) {

			req.addParameter(paramPrefix + "uclimit", limit.toString());

		} else {
			req.addParameter(paramPrefix + "uclimit", "5000");
		}

		if (start != null) {

			req.addParameter(
					paramPrefix + "ucstart",
					start.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());
		}

		if (end != null) {

			req.addParameter(
					paramPrefix + "ucend",
					end.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());
		}

		if (user != null) {

			req.addParameter(
					paramPrefix + "ucuser",
					user.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (userids != null) {

			req.addParameter(
					paramPrefix + "ucuserids",
					userids.stream().map(v -> v.toString()).collect(Collectors.joining("|")));
		}

		if (userprefix != null) {

			req.addParameter(paramPrefix + "ucuserprefix", userprefix);
		}

		if (iprange != null) {

			req.addParameter(paramPrefix + "uciprange", iprange);
		}

		if (dir != null) {

			req.addParameter(paramPrefix + "ucdir", dir.getJsonValue());
		}

		if (namespace != null) {

			req.addParameter(
					paramPrefix + "ucnamespace",
					namespace.stream()
							.map(v -> Integer.toString(v.getId()))
							.collect(Collectors.joining("|")));
		}

		if (prop != null) {

			req.addParameter(
					paramPrefix + "ucprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (show != null) {

			req.addParameter(
					paramPrefix + "ucshow",
					show.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (tag != null) {

			req.addParameter(paramPrefix + "uctag", tag);
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
			c.accept(AAPIQueryUsercontribs.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryUsercontribs.this);

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
