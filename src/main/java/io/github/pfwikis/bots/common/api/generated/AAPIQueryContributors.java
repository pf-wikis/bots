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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryContributorsGroup;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryContributorsExcludegroup;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryContributorsRights;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryContributorsExcluderights;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryProp.AAPIQueryPropModule;

/** Get the list of logged-in contributors and the count of logged-out contributors to a page.
 */
public class AAPIQueryContributors implements AAPIModule, AAPIQueryPropModule {

	public static AAPIQueryContributors create() {

		AAPIQueryContributors v = new AAPIQueryContributors();

		return v;
	}

	private AAPIQueryContributors() {}

	private List<AAPIQueryContributorsGroup> group;

	private List<AAPIQueryContributorsExcludegroup> excludegroup;

	private List<AAPIQueryContributorsRights> rights;

	private List<AAPIQueryContributorsExcluderights> excluderights;

	private Integer limit;

	/**Only include users in the given groups. Does not include implicit or auto-promoted groups like *, user, or autoconfirmed.
	 */
	public AAPIQueryContributors group(AAPIQueryContributorsGroup group) {
		this.group = List.of(group);

		return this;
	}

	/**Only include users in the given groups. Does not include implicit or auto-promoted groups like *, user, or autoconfirmed.
	 */
	public AAPIQueryContributors group(AAPIQueryContributorsGroup... group) {
		this.group = List.of(group);
		return this;
	}

	/**Only include users in the given groups. Does not include implicit or auto-promoted groups like *, user, or autoconfirmed.
	 */
	public List<AAPIQueryContributorsGroup> getGroup() {
		return this.group;
	}

	/**Exclude users in the given groups. Does not include implicit or auto-promoted groups like *, user, or autoconfirmed.
	 */
	public AAPIQueryContributors excludegroup(AAPIQueryContributorsExcludegroup excludegroup) {
		this.excludegroup = List.of(excludegroup);

		return this;
	}

	/**Exclude users in the given groups. Does not include implicit or auto-promoted groups like *, user, or autoconfirmed.
	 */
	public AAPIQueryContributors excludegroup(AAPIQueryContributorsExcludegroup... excludegroup) {
		this.excludegroup = List.of(excludegroup);
		return this;
	}

	/**Exclude users in the given groups. Does not include implicit or auto-promoted groups like *, user, or autoconfirmed.
	 */
	public List<AAPIQueryContributorsExcludegroup> getExcludegroup() {
		return this.excludegroup;
	}

	/**Only include users having the given rights. Does not include rights granted by implicit or auto-promoted groups like *, user, or autoconfirmed.
	 */
	public AAPIQueryContributors rights(AAPIQueryContributorsRights rights) {
		this.rights = List.of(rights);

		return this;
	}

	/**Only include users having the given rights. Does not include rights granted by implicit or auto-promoted groups like *, user, or autoconfirmed.
	 */
	public AAPIQueryContributors rights(AAPIQueryContributorsRights... rights) {
		this.rights = List.of(rights);
		return this;
	}

	/**Only include users having the given rights. Does not include rights granted by implicit or auto-promoted groups like *, user, or autoconfirmed.
	 */
	public List<AAPIQueryContributorsRights> getRights() {
		return this.rights;
	}

	/**Exclude users having the given rights. Does not include rights granted by implicit or auto-promoted groups like *, user, or autoconfirmed.
	 */
	public AAPIQueryContributors excluderights(AAPIQueryContributorsExcluderights excluderights) {
		this.excluderights = List.of(excluderights);

		return this;
	}

	/**Exclude users having the given rights. Does not include rights granted by implicit or auto-promoted groups like *, user, or autoconfirmed.
	 */
	public AAPIQueryContributors excluderights(
			AAPIQueryContributorsExcluderights... excluderights) {
		this.excluderights = List.of(excluderights);
		return this;
	}

	/**Exclude users having the given rights. Does not include rights granted by implicit or auto-promoted groups like *, user, or autoconfirmed.
	 */
	public List<AAPIQueryContributorsExcluderights> getExcluderights() {
		return this.excluderights;
	}

	/**How many contributors to return.
	 */
	public AAPIQueryContributors limit(Integer limit) {
		this.limit = limit;

		return this;
	}

	/**How many contributors to return.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryContributors(");

		if (group != null) {

			sb.append("pcgroup")
					.append("=")
					.append(
							group.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (excludegroup != null) {

			sb.append("pcexcludegroup")
					.append("=")
					.append(
							excludegroup.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (rights != null) {

			sb.append("pcrights")
					.append("=")
					.append(
							rights.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (excluderights != null) {

			sb.append("pcexcluderights")
					.append("=")
					.append(
							excluderights.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("pclimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (group != null) {

			req.addParameter(
					paramPrefix + "pcgroup",
					group.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (excludegroup != null) {

			req.addParameter(
					paramPrefix + "pcexcludegroup",
					excludegroup.stream()
							.map(v -> v.getJsonValue())
							.collect(Collectors.joining("|")));
		}

		if (rights != null) {

			req.addParameter(
					paramPrefix + "pcrights",
					rights.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (excluderights != null) {

			req.addParameter(
					paramPrefix + "pcexcluderights",
					excluderights.stream()
							.map(v -> v.getJsonValue())
							.collect(Collectors.joining("|")));
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "pclimit", limit.toString());

		} else {
			req.addParameter(paramPrefix + "pclimit", "5000");
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
			c.accept(AAPIQueryContributors.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryContributors.this);

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
