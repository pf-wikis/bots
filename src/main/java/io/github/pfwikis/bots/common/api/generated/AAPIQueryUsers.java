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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryUsersProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryList.AAPIQueryListModule;

/** Get information about a list of users.
 */
public class AAPIQueryUsers implements AAPIModule, AAPIQueryListModule {

	public static AAPIQueryUsers create() {

		AAPIQueryUsers v = new AAPIQueryUsers();

		return v;
	}

	private AAPIQueryUsers() {}

	private List<AAPIQueryUsersProp> prop;

	private String attachedwiki;

	private List<String> users;

	private List<Long> userids;

	/**<p>Which pieces of information to include:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryUsers prop(AAPIQueryUsersProp... prop) {

		this.prop = List.of(prop);

		return this;
	}

	/**<p>Which pieces of information to include:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryUsersProp> getProp() {
		return this.prop;
	}

	/**With <kbd>usprop=centralids</kbd>, indicate whether the user is attached with the wiki identified by this ID.
	 */
	public AAPIQueryUsers attachedwiki(String attachedwiki) {

		this.attachedwiki = attachedwiki;

		return this;
	}

	/**With <kbd>usprop=centralids</kbd>, indicate whether the user is attached with the wiki identified by this ID.
	 */
	public String getAttachedwiki() {
		return this.attachedwiki;
	}

	/**A list of users to obtain information for.
	 */
	public AAPIQueryUsers users(String... users) {

		this.users = List.of(users);

		return this;
	}

	/**A list of users to obtain information for.
	 */
	public List<String> getUsers() {
		return this.users;
	}

	/**A list of user IDs to obtain information for.
	 */
	public AAPIQueryUsers userids(Long... userids) {

		this.userids = List.of(userids);

		return this;
	}

	/**A list of user IDs to obtain information for.
	 */
	public List<Long> getUserids() {
		return this.userids;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryUsers(");

		if (prop != null) {

			sb.append("usprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (attachedwiki != null) {

			sb.append("usattachedwiki").append("=").append(attachedwiki);

			sb.append(", ");
		}

		if (users != null) {

			sb.append("ususers")
					.append("=")
					.append(users.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (userids != null) {

			sb.append("ususerids")
					.append("=")
					.append(
							userids.stream()
									.map(v -> v.toString())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (prop != null) {

			req.addParameter(
					paramPrefix + "usprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (attachedwiki != null) {

			req.addParameter(paramPrefix + "usattachedwiki", attachedwiki);
		}

		if (users != null) {

			req.addParameter(
					paramPrefix + "ususers",
					users.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (userids != null) {

			req.addParameter(
					paramPrefix + "ususerids",
					userids.stream().map(v -> v.toString()).collect(Collectors.joining("|")));
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
			c.accept(AAPIQueryUsers.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryUsers.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return false;
		}

		@Override
		protected boolean internalRequiresPagination() {
			return true;
		}
	}
}
