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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryLogeventsProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryLogeventsType;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryLogeventsAction;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryLogeventsDir;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryList.AAPIQueryListModule;

/** Get events from logs.
 */
public class AAPIQueryLogevents implements AAPIModule, AAPIQueryListModule {

	public static AAPIQueryLogevents create() {

		AAPIQueryLogevents v = new AAPIQueryLogevents();

		return v;
	}

	private AAPIQueryLogevents() {}

	private List<AAPIQueryLogeventsProp> prop;

	private AAPIQueryLogeventsType type;

	private AAPIQueryLogeventsAction action;

	private java.time.Instant start;

	private java.time.Instant end;

	private AAPIQueryLogeventsDir dir;

	private String user;

	private String title;

	private NS namespace;

	private String prefix;

	private String tag;

	private Integer limit;

	/**<p>Which properties to get:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryLogevents prop(AAPIQueryLogeventsProp prop) {
		this.prop = List.of(prop);

		return this;
	}

	/**<p>Which properties to get:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryLogevents prop(AAPIQueryLogeventsProp... prop) {
		this.prop = List.of(prop);
		return this;
	}

	/**<p>Which properties to get:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryLogeventsProp> getProp() {
		return this.prop;
	}

	/**Filter log entries to only this type.
	 */
	public AAPIQueryLogevents type(AAPIQueryLogeventsType type) {
		this.type = type;

		return this;
	}

	/**Filter log entries to only this type.
	 */
	public AAPIQueryLogeventsType getType() {
		return this.type;
	}

	/**Filter log actions to only this action. Overrides <var>letype</var>. In the list of possible values, values with the asterisk wildcard such as <kbd>action/*</kbd> can have different strings after the slash (/).
	 */
	public AAPIQueryLogevents action(AAPIQueryLogeventsAction action) {
		this.action = action;

		return this;
	}

	/**Filter log actions to only this action. Overrides <var>letype</var>. In the list of possible values, values with the asterisk wildcard such as <kbd>action/*</kbd> can have different strings after the slash (/).
	 */
	public AAPIQueryLogeventsAction getAction() {
		return this.action;
	}

	/**The timestamp to start enumerating from.
	 */
	public AAPIQueryLogevents start(java.time.Instant start) {
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
	public AAPIQueryLogevents end(java.time.Instant end) {
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
	public AAPIQueryLogevents dir(AAPIQueryLogeventsDir dir) {
		this.dir = dir;

		return this;
	}

	/**<p>In which direction to enumerate:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryLogeventsDir getDir() {
		return this.dir;
	}

	/**Filter entries to those made by the given user.
	 */
	public AAPIQueryLogevents user(String user) {
		this.user = user;

		return this;
	}

	/**Filter entries to those made by the given user.
	 */
	public String getUser() {
		return this.user;
	}

	/**Filter entries to those related to a page.
	 */
	public AAPIQueryLogevents title(String title) {
		this.title = title;

		return this;
	}

	/**Filter entries to those related to a page.
	 */
	public String getTitle() {
		return this.title;
	}

	/**Filter entries to those in the given namespace.
	 */
	public AAPIQueryLogevents namespace(NS namespace) {
		this.namespace = namespace;

		return this;
	}

	/**Filter entries to those in the given namespace.
	 */
	public NS getNamespace() {
		return this.namespace;
	}

	/**Filter entries that start with this prefix.
	 */
	public AAPIQueryLogevents prefix(String prefix) {
		this.prefix = prefix;

		return this;
	}

	/**Filter entries that start with this prefix.
	 */
	public String getPrefix() {
		return this.prefix;
	}

	/**Only list event entries tagged with this tag.
	 */
	public AAPIQueryLogevents tag(String tag) {
		this.tag = tag;

		return this;
	}

	/**Only list event entries tagged with this tag.
	 */
	public String getTag() {
		return this.tag;
	}

	/**How many total event entries to return.
	 */
	public AAPIQueryLogevents limit(Integer limit) {
		this.limit = limit;

		return this;
	}

	/**How many total event entries to return.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryLogevents(");

		if (prop != null) {

			sb.append("leprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (type != null) {

			sb.append("letype").append("=").append(type.getJsonValue());

			sb.append(", ");
		}

		if (action != null) {

			sb.append("leaction").append("=").append(action.getJsonValue());

			sb.append(", ");
		}

		if (start != null) {

			sb.append("lestart")
					.append("=")
					.append(start.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());

			sb.append(", ");
		}

		if (end != null) {

			sb.append("leend")
					.append("=")
					.append(end.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());

			sb.append(", ");
		}

		if (dir != null) {

			sb.append("ledir").append("=").append(dir.getJsonValue());

			sb.append(", ");
		}

		if (user != null) {

			sb.append("leuser").append("=").append(user);

			sb.append(", ");
		}

		if (title != null) {

			sb.append("letitle").append("=").append(title);

			sb.append(", ");
		}

		if (namespace != null) {

			sb.append("lenamespace").append("=").append(Integer.toString(namespace.getId()));

			sb.append(", ");
		}

		if (prefix != null) {

			sb.append("leprefix").append("=").append(prefix);

			sb.append(", ");
		}

		if (tag != null) {

			sb.append("letag").append("=").append(tag);

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("lelimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (prop != null) {

			req.addParameter(
					paramPrefix + "leprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (type != null) {

			req.addParameter(paramPrefix + "letype", type.getJsonValue());
		}

		if (action != null) {

			req.addParameter(paramPrefix + "leaction", action.getJsonValue());
		}

		if (start != null) {

			req.addParameter(
					paramPrefix + "lestart",
					start.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());
		}

		if (end != null) {

			req.addParameter(
					paramPrefix + "leend",
					end.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());
		}

		if (dir != null) {

			req.addParameter(paramPrefix + "ledir", dir.getJsonValue());
		}

		if (user != null) {

			req.addParameter(paramPrefix + "leuser", user);
		}

		if (title != null) {

			req.addParameter(paramPrefix + "letitle", title);
		}

		if (namespace != null) {

			req.addParameter(paramPrefix + "lenamespace", Integer.toString(namespace.getId()));
		}

		if (prefix != null) {

			req.addParameter(paramPrefix + "leprefix", prefix);
		}

		if (tag != null) {

			req.addParameter(paramPrefix + "letag", tag);
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "lelimit", limit.toString());

		} else {
			req.addParameter(paramPrefix + "lelimit", "5000");
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
			c.accept(AAPIQueryLogevents.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryLogevents.this);

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
