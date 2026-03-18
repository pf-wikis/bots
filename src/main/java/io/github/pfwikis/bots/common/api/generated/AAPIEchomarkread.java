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

import io.github.pfwikis.bots.common.api.generated.params.AAPIEchomarkreadSections;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** Mark notifications as read for the current user.
 */
public class AAPIEchomarkread implements AAPIModule, AAPITokenModule, AAPIMainActionModule {

	public static AAPIEchomarkread create() {

		AAPIEchomarkread v = new AAPIEchomarkread();

		return v;
	}

	private AAPIEchomarkread() {}

	private List<String> list;

	private List<String> unreadlist;

	private Boolean all;

	private List<AAPIEchomarkreadSections> sections;

	private String token;

	/**A list of notification IDs to mark as read.
	 */
	public AAPIEchomarkread list(String... list) {

		this.list = List.of(list);

		return this;
	}

	/**A list of notification IDs to mark as read.
	 */
	public List<String> getList() {
		return this.list;
	}

	/**A list of notification IDs to mark as unread.
	 */
	public AAPIEchomarkread unreadlist(String... unreadlist) {

		this.unreadlist = List.of(unreadlist);

		return this;
	}

	/**A list of notification IDs to mark as unread.
	 */
	public List<String> getUnreadlist() {
		return this.unreadlist;
	}

	/**If set, marks all of a user's notifications as read.
	 */
	public AAPIEchomarkread all(Boolean all) {

		this.all = all;

		return this;
	}

	/**If set, marks all of a user's notifications as read.
	 */
	public Boolean getAll() {
		return this.all;
	}

	/**A list of sections to mark as read.
	 */
	public AAPIEchomarkread sections(AAPIEchomarkreadSections... sections) {

		this.sections = List.of(sections);

		return this;
	}

	/**A list of sections to mark as read.
	 */
	public List<AAPIEchomarkreadSections> getSections() {
		return this.sections;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public AAPIEchomarkread token(String token) {

		this.token = token;

		return this;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public String getToken() {
		return this.token;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIEchomarkread(");

		if (list != null) {

			sb.append("list")
					.append("=")
					.append(list.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (unreadlist != null) {

			sb.append("unreadlist")
					.append("=")
					.append(unreadlist.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (all != null) {

			sb.append("all").append("=").append(all.toString());

			sb.append(", ");
		}

		if (sections != null) {

			sb.append("sections")
					.append("=")
					.append(
							sections.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (token != null) {

			sb.append("token").append("=").append(token);

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (list != null) {

			req.addParameter(
					paramPrefix + "list",
					list.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (unreadlist != null) {

			req.addParameter(
					paramPrefix + "unreadlist",
					unreadlist.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (all != null) {

			req.addParameter(paramPrefix + "all", all.toString());
		}

		if (sections != null) {

			req.addParameter(
					paramPrefix + "sections",
					sections.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		token = api.requestToken(AAPIQueryTokensType.CSRF);

		if (token != null) {

			req.addParameter(paramPrefix + "token", token);
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
			c.accept(AAPIEchomarkread.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIEchomarkread.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return true;
		}

		@Override
		protected boolean internalRequiresPagination() {
			return true;
		}
	}
}
