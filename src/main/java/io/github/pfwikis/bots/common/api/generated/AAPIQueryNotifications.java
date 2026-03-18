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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryNotificationsFilter;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryNotificationsProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryNotificationsSections;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryNotificationsFormat;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryNotificationsNotifiertypes;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryMeta.AAPIQueryMetaModule;

/** Get notifications waiting for the current user.
 */
public class AAPIQueryNotifications implements AAPIModule, AAPIQueryMetaModule {

	public static AAPIQueryNotifications create() {

		AAPIQueryNotifications v = new AAPIQueryNotifications();

		return v;
	}

	private AAPIQueryNotifications() {}

	private List<AAPIQueryNotificationsFilter> filter;

	private List<AAPIQueryNotificationsProp> prop;

	private List<AAPIQueryNotificationsSections> sections;

	private Boolean groupbysection;

	private AAPIQueryNotificationsFormat format;

	private Integer limit;

	private Boolean unreadfirst;

	private List<String> titles;

	private Boolean bundle;

	private List<AAPIQueryNotificationsNotifiertypes> notifiertypes;

	private String alertcontinue;

	private Boolean alertunreadfirst;

	private String messagecontinue;

	private Boolean messageunreadfirst;

	/**Filter notifications returned.
	 */
	public AAPIQueryNotifications filter(AAPIQueryNotificationsFilter... filter) {

		this.filter = List.of(filter);

		return this;
	}

	/**Filter notifications returned.
	 */
	public List<AAPIQueryNotificationsFilter> getFilter() {
		return this.filter;
	}

	/**Details to request.
	 */
	public AAPIQueryNotifications prop(AAPIQueryNotificationsProp... prop) {

		this.prop = List.of(prop);

		return this;
	}

	/**Details to request.
	 */
	public List<AAPIQueryNotificationsProp> getProp() {
		return this.prop;
	}

	/**The notification sections to query (i.e. some combination of 'alert' and 'message').
	 */
	public AAPIQueryNotifications sections(AAPIQueryNotificationsSections... sections) {

		this.sections = List.of(sections);

		return this;
	}

	/**The notification sections to query (i.e. some combination of 'alert' and 'message').
	 */
	public List<AAPIQueryNotificationsSections> getSections() {
		return this.sections;
	}

	/**Whether to group the result by section. Each section is fetched separately if set.
	 */
	public AAPIQueryNotifications groupbysection(Boolean groupbysection) {

		this.groupbysection = groupbysection;

		return this;
	}

	/**Whether to group the result by section. Each section is fetched separately if set.
	 */
	public Boolean getGroupbysection() {
		return this.groupbysection;
	}

	/**<p>If specified, notifications will be returned formatted this way.
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryNotifications format(AAPIQueryNotificationsFormat format) {

		this.format = format;

		return this;
	}

	/**<p>If specified, notifications will be returned formatted this way.
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryNotificationsFormat getFormat() {
		return this.format;
	}

	/**The maximum number of notifications to return.
	 */
	public AAPIQueryNotifications limit(Integer limit) {

		this.limit = limit;

		return this;
	}

	/**The maximum number of notifications to return.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	/**Whether to show unread notifications first (only used if groupbysection is not set).
	 */
	public AAPIQueryNotifications unreadfirst(Boolean unreadfirst) {

		this.unreadfirst = unreadfirst;

		return this;
	}

	/**Whether to show unread notifications first (only used if groupbysection is not set).
	 */
	public Boolean getUnreadfirst() {
		return this.unreadfirst;
	}

	/**Only return notifications for these pages. To get notifications not associated with any page, use [] as a title.
	 */
	public AAPIQueryNotifications titles(String... titles) {

		this.titles = List.of(titles);

		return this;
	}

	/**Only return notifications for these pages. To get notifications not associated with any page, use [] as a title.
	 */
	public List<String> getTitles() {
		return this.titles;
	}

	/**Whether to show bundle compatible unread notifications according to notification types bundling rules.
	 */
	public AAPIQueryNotifications bundle(Boolean bundle) {

		this.bundle = bundle;

		return this;
	}

	/**Whether to show bundle compatible unread notifications according to notification types bundling rules.
	 */
	public Boolean getBundle() {
		return this.bundle;
	}

	/**Notifier types for which to return notifications.
	 */
	public AAPIQueryNotifications notifiertypes(
			AAPIQueryNotificationsNotifiertypes... notifiertypes) {

		this.notifiertypes = List.of(notifiertypes);

		return this;
	}

	/**Notifier types for which to return notifications.
	 */
	public List<AAPIQueryNotificationsNotifiertypes> getNotifiertypes() {
		return this.notifiertypes;
	}

	/**When more alert results are available, use this to continue.
	 */
	public AAPIQueryNotifications alertcontinue(String alertcontinue) {

		this.alertcontinue = alertcontinue;

		return this;
	}

	/**When more alert results are available, use this to continue.
	 */
	public String getAlertcontinue() {
		return this.alertcontinue;
	}

	/**Whether to show unread message notifications first (only used if groupbysection is set).
	 */
	public AAPIQueryNotifications alertunreadfirst(Boolean alertunreadfirst) {

		this.alertunreadfirst = alertunreadfirst;

		return this;
	}

	/**Whether to show unread message notifications first (only used if groupbysection is set).
	 */
	public Boolean getAlertunreadfirst() {
		return this.alertunreadfirst;
	}

	/**When more message results are available, use this to continue.
	 */
	public AAPIQueryNotifications messagecontinue(String messagecontinue) {

		this.messagecontinue = messagecontinue;

		return this;
	}

	/**When more message results are available, use this to continue.
	 */
	public String getMessagecontinue() {
		return this.messagecontinue;
	}

	/**Whether to show unread alert notifications first (only used if groupbysection is set).
	 */
	public AAPIQueryNotifications messageunreadfirst(Boolean messageunreadfirst) {

		this.messageunreadfirst = messageunreadfirst;

		return this;
	}

	/**Whether to show unread alert notifications first (only used if groupbysection is set).
	 */
	public Boolean getMessageunreadfirst() {
		return this.messageunreadfirst;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryNotifications(");

		if (filter != null) {

			sb.append("notfilter")
					.append("=")
					.append(
							filter.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (prop != null) {

			sb.append("notprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (sections != null) {

			sb.append("notsections")
					.append("=")
					.append(
							sections.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (groupbysection != null) {

			sb.append("notgroupbysection").append("=").append(groupbysection.toString());

			sb.append(", ");
		}

		if (format != null) {

			sb.append("notformat").append("=").append(format.getJsonValue());

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("notlimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		if (unreadfirst != null) {

			sb.append("notunreadfirst").append("=").append(unreadfirst.toString());

			sb.append(", ");
		}

		if (titles != null) {

			sb.append("nottitles")
					.append("=")
					.append(titles.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (bundle != null) {

			sb.append("notbundle").append("=").append(bundle.toString());

			sb.append(", ");
		}

		if (notifiertypes != null) {

			sb.append("notnotifiertypes")
					.append("=")
					.append(
							notifiertypes.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (alertcontinue != null) {

			sb.append("notalertcontinue").append("=").append(alertcontinue);

			sb.append(", ");
		}

		if (alertunreadfirst != null) {

			sb.append("notalertunreadfirst").append("=").append(alertunreadfirst.toString());

			sb.append(", ");
		}

		if (messagecontinue != null) {

			sb.append("notmessagecontinue").append("=").append(messagecontinue);

			sb.append(", ");
		}

		if (messageunreadfirst != null) {

			sb.append("notmessageunreadfirst").append("=").append(messageunreadfirst.toString());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (filter != null) {

			req.addParameter(
					paramPrefix + "notfilter",
					filter.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (prop != null) {

			req.addParameter(
					paramPrefix + "notprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (sections != null) {

			req.addParameter(
					paramPrefix + "notsections",
					sections.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (groupbysection != null) {

			req.addParameter(paramPrefix + "notgroupbysection", groupbysection.toString());
		}

		if (format != null) {

			req.addParameter(paramPrefix + "notformat", format.getJsonValue());
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "notlimit", limit.toString());

		} else {
			req.addParameter(paramPrefix + "notlimit", "500");
		}

		if (unreadfirst != null) {

			req.addParameter(paramPrefix + "notunreadfirst", unreadfirst.toString());
		}

		if (titles != null) {

			req.addParameter(
					paramPrefix + "nottitles",
					titles.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (bundle != null) {

			req.addParameter(paramPrefix + "notbundle", bundle.toString());
		}

		if (notifiertypes != null) {

			req.addParameter(
					paramPrefix + "notnotifiertypes",
					notifiertypes.stream()
							.map(v -> v.getJsonValue())
							.collect(Collectors.joining("|")));
		}

		if (alertcontinue != null) {

			req.addParameter(paramPrefix + "notalertcontinue", alertcontinue);
		}

		if (alertunreadfirst != null) {

			req.addParameter(paramPrefix + "notalertunreadfirst", alertunreadfirst.toString());
		}

		if (messagecontinue != null) {

			req.addParameter(paramPrefix + "notmessagecontinue", messagecontinue);
		}

		if (messageunreadfirst != null) {

			req.addParameter(paramPrefix + "notmessageunreadfirst", messageunreadfirst.toString());
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
			c.accept(AAPIQueryNotifications.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryNotifications.this);

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
