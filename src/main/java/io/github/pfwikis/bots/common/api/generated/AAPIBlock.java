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

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** Block a user.
 */
public class AAPIBlock implements AAPIModule, AAPITokenModule, AAPIMainActionModule {

	public static AAPIBlock create() {

		AAPIBlock v = new AAPIBlock();

		return v;
	}

	private AAPIBlock() {}

	private String user;

	private String expiry;

	private String reason;

	private Boolean anononly;

	private Boolean nocreate;

	private Boolean autoblock;

	private Boolean noemail;

	private Boolean hidename;

	private Boolean allowusertalk;

	private Boolean reblock;

	private Boolean watchuser;

	private List<String> tags;

	private Boolean partial;

	private List<String> pagerestrictions;

	private List<NS> namespacerestrictions;

	private String token;

	/**User to block.
	 */
	public AAPIBlock user(String user) {

		this.user = user;

		return this;
	}

	/**User to block.
	 */
	public String getUser() {
		return this.user;
	}

	/**Expiry time. May be relative (e.g. <kbd>5 months</kbd> or <kbd>2 weeks</kbd>) or absolute (e.g. <kbd>2014-09-18T12:34:56Z</kbd>). If set to <kbd>infinite</kbd>, <kbd>indefinite</kbd>, or <kbd>never</kbd>, the block will never expire.
	 */
	public AAPIBlock expiry(String expiry) {

		this.expiry = expiry;

		return this;
	}

	/**Expiry time. May be relative (e.g. <kbd>5 months</kbd> or <kbd>2 weeks</kbd>) or absolute (e.g. <kbd>2014-09-18T12:34:56Z</kbd>). If set to <kbd>infinite</kbd>, <kbd>indefinite</kbd>, or <kbd>never</kbd>, the block will never expire.
	 */
	public String getExpiry() {
		return this.expiry;
	}

	/**Reason for block.
	 */
	public AAPIBlock reason(String reason) {

		this.reason = reason;

		return this;
	}

	/**Reason for block.
	 */
	public String getReason() {
		return this.reason;
	}

	/**Block anonymous users only (i.e. disable anonymous edits for this IP address, including temporary account edits).
	 */
	public AAPIBlock anononly(Boolean anononly) {

		this.anononly = anononly;

		return this;
	}

	/**Block anonymous users only (i.e. disable anonymous edits for this IP address, including temporary account edits).
	 */
	public Boolean getAnononly() {
		return this.anononly;
	}

	/**Prevent account creation.
	 */
	public AAPIBlock nocreate(Boolean nocreate) {

		this.nocreate = nocreate;

		return this;
	}

	/**Prevent account creation.
	 */
	public Boolean getNocreate() {
		return this.nocreate;
	}

	/**Automatically block the last used IP address, and any subsequent IP addresses they try to login from.
	 */
	public AAPIBlock autoblock(Boolean autoblock) {

		this.autoblock = autoblock;

		return this;
	}

	/**Automatically block the last used IP address, and any subsequent IP addresses they try to login from.
	 */
	public Boolean getAutoblock() {
		return this.autoblock;
	}

	/**Prevent user from sending email through the wiki. (Requires the <code>blockemail</code> right).
	 */
	public AAPIBlock noemail(Boolean noemail) {

		this.noemail = noemail;

		return this;
	}

	/**Prevent user from sending email through the wiki. (Requires the <code>blockemail</code> right).
	 */
	public Boolean getNoemail() {
		return this.noemail;
	}

	/**Hide the username from the block log. (Requires the <code>hideuser</code> right).
	 */
	public AAPIBlock hidename(Boolean hidename) {

		this.hidename = hidename;

		return this;
	}

	/**Hide the username from the block log. (Requires the <code>hideuser</code> right).
	 */
	public Boolean getHidename() {
		return this.hidename;
	}

	/**Allow the user to edit their own talk page (depends on <var><a href="/w/index.php?title=Mw:Special:MyLanguage/Manual:$wgBlockAllowsUTEdit&amp;action=edit&amp;redlink=1" class="new" title="Mw:Special:MyLanguage/Manual:$wgBlockAllowsUTEdit (page does not exist)">$wgBlockAllowsUTEdit</a></var>).
	 */
	public AAPIBlock allowusertalk(Boolean allowusertalk) {

		this.allowusertalk = allowusertalk;

		return this;
	}

	/**Allow the user to edit their own talk page (depends on <var><a href="/w/index.php?title=Mw:Special:MyLanguage/Manual:$wgBlockAllowsUTEdit&amp;action=edit&amp;redlink=1" class="new" title="Mw:Special:MyLanguage/Manual:$wgBlockAllowsUTEdit (page does not exist)">$wgBlockAllowsUTEdit</a></var>).
	 */
	public Boolean getAllowusertalk() {
		return this.allowusertalk;
	}

	/**If the user is already blocked, overwrite the existing block.
	 */
	public AAPIBlock reblock(Boolean reblock) {

		this.reblock = reblock;

		return this;
	}

	/**If the user is already blocked, overwrite the existing block.
	 */
	public Boolean getReblock() {
		return this.reblock;
	}

	/**Watch the user's or IP address's user and talk pages.
	 */
	public AAPIBlock watchuser(Boolean watchuser) {

		this.watchuser = watchuser;

		return this;
	}

	/**Watch the user's or IP address's user and talk pages.
	 */
	public Boolean getWatchuser() {
		return this.watchuser;
	}

	/**Change tags to apply to the entry in the block log.
	 */
	public AAPIBlock tags(String... tags) {

		this.tags = List.of(tags);

		return this;
	}

	/**Change tags to apply to the entry in the block log.
	 */
	public List<String> getTags() {
		return this.tags;
	}

	/**Block user from specific pages or namespaces rather than the entire site.
	 */
	public AAPIBlock partial(Boolean partial) {

		this.partial = partial;

		return this;
	}

	/**Block user from specific pages or namespaces rather than the entire site.
	 */
	public Boolean getPartial() {
		return this.partial;
	}

	/**List of titles to block the user from editing. Only applies when <var>partial</var> is set to true.
	 */
	public AAPIBlock pagerestrictions(String... pagerestrictions) {

		this.pagerestrictions = List.of(pagerestrictions);

		return this;
	}

	/**List of titles to block the user from editing. Only applies when <var>partial</var> is set to true.
	 */
	public List<String> getPagerestrictions() {
		return this.pagerestrictions;
	}

	/**List of namespace IDs to block the user from editing. Only applies when <var>partial</var> is set to true.
	 */
	public AAPIBlock namespacerestrictions(NS... namespacerestrictions) {

		this.namespacerestrictions = List.of(namespacerestrictions);

		return this;
	}

	/**List of namespace IDs to block the user from editing. Only applies when <var>partial</var> is set to true.
	 */
	public List<NS> getNamespacerestrictions() {
		return this.namespacerestrictions;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public AAPIBlock token(String token) {

		this.token = token;

		return this;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public String getToken() {
		return this.token;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIBlock(");

		if (user != null) {

			sb.append("user").append("=").append(user);

			sb.append(", ");
		}

		if (expiry != null) {

			sb.append("expiry").append("=").append(expiry);

			sb.append(", ");
		}

		if (reason != null) {

			sb.append("reason").append("=").append(reason);

			sb.append(", ");
		}

		if (anononly != null) {

			sb.append("anononly").append("=").append(anononly.toString());

			sb.append(", ");
		}

		if (nocreate != null) {

			sb.append("nocreate").append("=").append(nocreate.toString());

			sb.append(", ");
		}

		if (autoblock != null) {

			sb.append("autoblock").append("=").append(autoblock.toString());

			sb.append(", ");
		}

		if (noemail != null) {

			sb.append("noemail").append("=").append(noemail.toString());

			sb.append(", ");
		}

		if (hidename != null) {

			sb.append("hidename").append("=").append(hidename.toString());

			sb.append(", ");
		}

		if (allowusertalk != null) {

			sb.append("allowusertalk").append("=").append(allowusertalk.toString());

			sb.append(", ");
		}

		if (reblock != null) {

			sb.append("reblock").append("=").append(reblock.toString());

			sb.append(", ");
		}

		if (watchuser != null) {

			sb.append("watchuser").append("=").append(watchuser.toString());

			sb.append(", ");
		}

		if (tags != null) {

			sb.append("tags")
					.append("=")
					.append(tags.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (partial != null) {

			sb.append("partial").append("=").append(partial.toString());

			sb.append(", ");
		}

		if (pagerestrictions != null) {

			sb.append("pagerestrictions")
					.append("=")
					.append(pagerestrictions.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (namespacerestrictions != null) {

			sb.append("namespacerestrictions")
					.append("=")
					.append(
							namespacerestrictions.stream()
									.map(v -> Integer.toString(v.getId()))
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

		if (user != null) {

			req.addParameter(paramPrefix + "user", user);
		}

		if (expiry != null) {

			req.addParameter(paramPrefix + "expiry", expiry);
		}

		if (reason != null) {

			req.addParameter(paramPrefix + "reason", reason);
		}

		if (anononly != null) {

			req.addParameter(paramPrefix + "anononly", anononly.toString());
		}

		if (nocreate != null) {

			req.addParameter(paramPrefix + "nocreate", nocreate.toString());
		}

		if (autoblock != null) {

			req.addParameter(paramPrefix + "autoblock", autoblock.toString());
		}

		if (noemail != null) {

			req.addParameter(paramPrefix + "noemail", noemail.toString());
		}

		if (hidename != null) {

			req.addParameter(paramPrefix + "hidename", hidename.toString());
		}

		if (allowusertalk != null) {

			req.addParameter(paramPrefix + "allowusertalk", allowusertalk.toString());
		}

		if (reblock != null) {

			req.addParameter(paramPrefix + "reblock", reblock.toString());
		}

		if (watchuser != null) {

			req.addParameter(paramPrefix + "watchuser", watchuser.toString());
		}

		if (tags != null) {

			req.addParameter(
					paramPrefix + "tags",
					tags.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (partial != null) {

			req.addParameter(paramPrefix + "partial", partial.toString());
		}

		if (pagerestrictions != null) {

			req.addParameter(
					paramPrefix + "pagerestrictions",
					pagerestrictions.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (namespacerestrictions != null) {

			req.addParameter(
					paramPrefix + "namespacerestrictions",
					namespacerestrictions.stream()
							.map(v -> Integer.toString(v.getId()))
							.collect(Collectors.joining("|")));
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
			c.accept(AAPIBlock.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIBlock.this);

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
