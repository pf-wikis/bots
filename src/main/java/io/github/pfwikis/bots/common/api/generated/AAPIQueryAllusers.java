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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryAllusersDir;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryAllusersGroup;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryAllusersExcludegroup;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryAllusersRights;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryAllusersProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryList.AAPIQueryListModule;

/** Enumerate all registered users.
 */
public class AAPIQueryAllusers implements AAPIModule, AAPIQueryListModule {

	public static AAPIQueryAllusers create() {

		AAPIQueryAllusers v = new AAPIQueryAllusers();

		return v;
	}

	private AAPIQueryAllusers() {}

	private String to;

	private String prefix;

	private AAPIQueryAllusersDir dir;

	private List<AAPIQueryAllusersGroup> group;

	private List<AAPIQueryAllusersExcludegroup> excludegroup;

	private List<AAPIQueryAllusersRights> rights;

	private List<AAPIQueryAllusersProp> prop;

	private Integer limit;

	private Boolean witheditsonly;

	private Boolean activeusers;

	private String attachedwiki;

	private Boolean excludenamed;

	private Boolean excludetemp;

	/**The username to stop enumerating at.
	 */
	public AAPIQueryAllusers to(String to) {

		this.to = to;

		return this;
	}

	/**The username to stop enumerating at.
	 */
	public String getTo() {
		return this.to;
	}

	/**Search for all users that begin with this value.
	 */
	public AAPIQueryAllusers prefix(String prefix) {

		this.prefix = prefix;

		return this;
	}

	/**Search for all users that begin with this value.
	 */
	public String getPrefix() {
		return this.prefix;
	}

	/**Direction to sort in.
	 */
	public AAPIQueryAllusers dir(AAPIQueryAllusersDir dir) {

		this.dir = dir;

		return this;
	}

	/**Direction to sort in.
	 */
	public AAPIQueryAllusersDir getDir() {
		return this.dir;
	}

	/**Only include users in the given groups. Does not include implicit or auto-promoted groups like *, user, or autoconfirmed.
	 */
	public AAPIQueryAllusers group(AAPIQueryAllusersGroup... group) {

		this.group = List.of(group);

		return this;
	}

	/**Only include users in the given groups. Does not include implicit or auto-promoted groups like *, user, or autoconfirmed.
	 */
	public List<AAPIQueryAllusersGroup> getGroup() {
		return this.group;
	}

	/**Exclude users in the given groups.
	 */
	public AAPIQueryAllusers excludegroup(AAPIQueryAllusersExcludegroup... excludegroup) {

		this.excludegroup = List.of(excludegroup);

		return this;
	}

	/**Exclude users in the given groups.
	 */
	public List<AAPIQueryAllusersExcludegroup> getExcludegroup() {
		return this.excludegroup;
	}

	/**Only include users with the given rights. Does not include rights granted by implicit or auto-promoted groups like *, user, or autoconfirmed.
	 */
	public AAPIQueryAllusers rights(AAPIQueryAllusersRights... rights) {

		this.rights = List.of(rights);

		return this;
	}

	/**Only include users with the given rights. Does not include rights granted by implicit or auto-promoted groups like *, user, or autoconfirmed.
	 */
	public List<AAPIQueryAllusersRights> getRights() {
		return this.rights;
	}

	/**<p>Which pieces of information to include:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryAllusers prop(AAPIQueryAllusersProp... prop) {

		this.prop = List.of(prop);

		return this;
	}

	/**<p>Which pieces of information to include:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryAllusersProp> getProp() {
		return this.prop;
	}

	/**How many total usernames to return.
	 */
	public AAPIQueryAllusers limit(Integer limit) {

		this.limit = limit;

		return this;
	}

	/**How many total usernames to return.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	/**Only list users who have made edits.
	 */
	public AAPIQueryAllusers witheditsonly(Boolean witheditsonly) {

		this.witheditsonly = witheditsonly;

		return this;
	}

	/**Only list users who have made edits.
	 */
	public Boolean getWitheditsonly() {
		return this.witheditsonly;
	}

	/**Only list users active in the last 30 days.
	 */
	public AAPIQueryAllusers activeusers(Boolean activeusers) {

		this.activeusers = activeusers;

		return this;
	}

	/**Only list users active in the last 30 days.
	 */
	public Boolean getActiveusers() {
		return this.activeusers;
	}

	/**With <kbd>auprop=centralids</kbd>, also indicate whether the user is attached with the wiki identified by this ID.
	 */
	public AAPIQueryAllusers attachedwiki(String attachedwiki) {

		this.attachedwiki = attachedwiki;

		return this;
	}

	/**With <kbd>auprop=centralids</kbd>, also indicate whether the user is attached with the wiki identified by this ID.
	 */
	public String getAttachedwiki() {
		return this.attachedwiki;
	}

	/**Exclude users of named accounts.
	 */
	public AAPIQueryAllusers excludenamed(Boolean excludenamed) {

		this.excludenamed = excludenamed;

		return this;
	}

	/**Exclude users of named accounts.
	 */
	public Boolean getExcludenamed() {
		return this.excludenamed;
	}

	/**Exclude users of temporary accounts.
	 */
	public AAPIQueryAllusers excludetemp(Boolean excludetemp) {

		this.excludetemp = excludetemp;

		return this;
	}

	/**Exclude users of temporary accounts.
	 */
	public Boolean getExcludetemp() {
		return this.excludetemp;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryAllusers(");

		if (to != null) {

			sb.append("auto").append("=").append(to);

			sb.append(", ");
		}

		if (prefix != null) {

			sb.append("auprefix").append("=").append(prefix);

			sb.append(", ");
		}

		if (dir != null) {

			sb.append("audir").append("=").append(dir.getJsonValue());

			sb.append(", ");
		}

		if (group != null) {

			sb.append("augroup")
					.append("=")
					.append(
							group.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (excludegroup != null) {

			sb.append("auexcludegroup")
					.append("=")
					.append(
							excludegroup.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (rights != null) {

			sb.append("aurights")
					.append("=")
					.append(
							rights.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (prop != null) {

			sb.append("auprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("aulimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		if (witheditsonly != null) {

			sb.append("auwitheditsonly").append("=").append(witheditsonly.toString());

			sb.append(", ");
		}

		if (activeusers != null) {

			sb.append("auactiveusers").append("=").append(activeusers.toString());

			sb.append(", ");
		}

		if (attachedwiki != null) {

			sb.append("auattachedwiki").append("=").append(attachedwiki);

			sb.append(", ");
		}

		if (excludenamed != null) {

			sb.append("auexcludenamed").append("=").append(excludenamed.toString());

			sb.append(", ");
		}

		if (excludetemp != null) {

			sb.append("auexcludetemp").append("=").append(excludetemp.toString());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (to != null) {

			req.addParameter(paramPrefix + "auto", to);
		}

		if (prefix != null) {

			req.addParameter(paramPrefix + "auprefix", prefix);
		}

		if (dir != null) {

			req.addParameter(paramPrefix + "audir", dir.getJsonValue());
		}

		if (group != null) {

			req.addParameter(
					paramPrefix + "augroup",
					group.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (excludegroup != null) {

			req.addParameter(
					paramPrefix + "auexcludegroup",
					excludegroup.stream()
							.map(v -> v.getJsonValue())
							.collect(Collectors.joining("|")));
		}

		if (rights != null) {

			req.addParameter(
					paramPrefix + "aurights",
					rights.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (prop != null) {

			req.addParameter(
					paramPrefix + "auprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "aulimit", limit.toString());

		} else {
			req.addParameter(paramPrefix + "aulimit", "5000");
		}

		if (witheditsonly != null) {

			req.addParameter(paramPrefix + "auwitheditsonly", witheditsonly.toString());
		}

		if (activeusers != null) {

			req.addParameter(paramPrefix + "auactiveusers", activeusers.toString());
		}

		if (attachedwiki != null) {

			req.addParameter(paramPrefix + "auattachedwiki", attachedwiki);
		}

		if (excludenamed != null) {

			req.addParameter(paramPrefix + "auexcludenamed", excludenamed.toString());
		}

		if (excludetemp != null) {

			req.addParameter(paramPrefix + "auexcludetemp", excludetemp.toString());
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
			c.accept(AAPIQueryAllusers.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryAllusers.this);

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
