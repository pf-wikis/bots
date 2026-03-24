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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryBlocksDir;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryBlocksProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryBlocksShow;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryList.AAPIQueryListModule;

/** List all blocked users and IP addresses.
 */
public class AAPIQueryBlocks implements AAPIModule, AAPIQueryListModule {

	public static AAPIQueryBlocks create() {

		AAPIQueryBlocks v = new AAPIQueryBlocks();

		return v;
	}

	private AAPIQueryBlocks() {}

	private java.time.Instant start;

	private java.time.Instant end;

	private AAPIQueryBlocksDir dir;

	private List<Long> ids;

	private List<String> users;

	private String ip;

	private Integer limit;

	private List<AAPIQueryBlocksProp> prop;

	private List<AAPIQueryBlocksShow> show;

	/**The timestamp to start enumerating from.
	 */
	public AAPIQueryBlocks start(java.time.Instant start) {
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
	public AAPIQueryBlocks end(java.time.Instant end) {
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
	public AAPIQueryBlocks dir(AAPIQueryBlocksDir dir) {
		this.dir = dir;

		return this;
	}

	/**<p>In which direction to enumerate:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryBlocksDir getDir() {
		return this.dir;
	}

	/**List of block IDs to list (optional).
	 */
	public AAPIQueryBlocks ids(Long ids) {
		this.ids = List.of(ids);

		return this;
	}

	/**List of block IDs to list (optional).
	 */
	public AAPIQueryBlocks ids(Long... ids) {
		this.ids = List.of(ids);
		return this;
	}

	/**List of block IDs to list (optional).
	 */
	public List<Long> getIds() {
		return this.ids;
	}

	/**List of users to search for (optional).
	 */
	public AAPIQueryBlocks users(String users) {
		this.users = List.of(users);

		return this;
	}

	/**List of users to search for (optional).
	 */
	public AAPIQueryBlocks users(String... users) {
		this.users = List.of(users);
		return this;
	}

	/**List of users to search for (optional).
	 */
	public List<String> getUsers() {
		return this.users;
	}

	/**Get all blocks applying to this IP address or CIDR range, including range blocks.
	 * Cannot be used together with <var>bkusers</var>. CIDR ranges broader than IPv4/16 or IPv6/19 are not accepted.
	 */
	public AAPIQueryBlocks ip(String ip) {
		this.ip = ip;

		return this;
	}

	/**Get all blocks applying to this IP address or CIDR range, including range blocks.
	 * Cannot be used together with <var>bkusers</var>. CIDR ranges broader than IPv4/16 or IPv6/19 are not accepted.
	 */
	public String getIp() {
		return this.ip;
	}

	/**The maximum number of blocks to list.
	 */
	public AAPIQueryBlocks limit(Integer limit) {
		this.limit = limit;

		return this;
	}

	/**The maximum number of blocks to list.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	/**<p>Which properties to get:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryBlocks prop(AAPIQueryBlocksProp prop) {
		this.prop = List.of(prop);

		return this;
	}

	/**<p>Which properties to get:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryBlocks prop(AAPIQueryBlocksProp... prop) {
		this.prop = List.of(prop);
		return this;
	}

	/**<p>Which properties to get:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryBlocksProp> getProp() {
		return this.prop;
	}

	/**Show only items that meet these criteria.
	 * For example, to see only indefinite blocks on IP addresses, set <kbd>bkshow=ip|!temp</kbd>.
	 */
	public AAPIQueryBlocks show(AAPIQueryBlocksShow show) {
		this.show = List.of(show);

		return this;
	}

	/**Show only items that meet these criteria.
	 * For example, to see only indefinite blocks on IP addresses, set <kbd>bkshow=ip|!temp</kbd>.
	 */
	public AAPIQueryBlocks show(AAPIQueryBlocksShow... show) {
		this.show = List.of(show);
		return this;
	}

	/**Show only items that meet these criteria.
	 * For example, to see only indefinite blocks on IP addresses, set <kbd>bkshow=ip|!temp</kbd>.
	 */
	public List<AAPIQueryBlocksShow> getShow() {
		return this.show;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryBlocks(");

		if (start != null) {

			sb.append("bkstart")
					.append("=")
					.append(start.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());

			sb.append(", ");
		}

		if (end != null) {

			sb.append("bkend")
					.append("=")
					.append(end.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());

			sb.append(", ");
		}

		if (dir != null) {

			sb.append("bkdir").append("=").append(dir.getJsonValue());

			sb.append(", ");
		}

		if (ids != null) {

			sb.append("bkids")
					.append("=")
					.append(ids.stream().map(v -> v.toString()).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (users != null) {

			sb.append("bkusers")
					.append("=")
					.append(users.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (ip != null) {

			sb.append("bkip").append("=").append(ip);

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("bklimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		if (prop != null) {

			sb.append("bkprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (show != null) {

			sb.append("bkshow")
					.append("=")
					.append(
							show.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (start != null) {

			req.addParameter(
					paramPrefix + "bkstart",
					start.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());
		}

		if (end != null) {

			req.addParameter(
					paramPrefix + "bkend",
					end.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());
		}

		if (dir != null) {

			req.addParameter(paramPrefix + "bkdir", dir.getJsonValue());
		}

		if (ids != null) {

			req.addParameter(
					paramPrefix + "bkids",
					ids.stream().map(v -> v.toString()).collect(Collectors.joining("|")));
		}

		if (users != null) {

			req.addParameter(
					paramPrefix + "bkusers",
					users.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (ip != null) {

			req.addParameter(paramPrefix + "bkip", ip);
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "bklimit", limit.toString());

		} else {
			req.addParameter(paramPrefix + "bklimit", "5000");
		}

		if (prop != null) {

			req.addParameter(
					paramPrefix + "bkprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (show != null) {

			req.addParameter(
					paramPrefix + "bkshow",
					show.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
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
			c.accept(AAPIQueryBlocks.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryBlocks.this);

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
