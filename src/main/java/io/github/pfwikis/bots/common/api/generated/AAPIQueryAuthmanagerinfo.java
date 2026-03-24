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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryAuthmanagerinfoRequestsfor;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryAuthmanagerinfoMessageformat;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryMeta.AAPIQueryMetaModule;

/** Retrieve information about the current authentication status.
 */
public class AAPIQueryAuthmanagerinfo implements AAPIModule, AAPIQueryMetaModule {

	public static AAPIQueryAuthmanagerinfo create() {

		AAPIQueryAuthmanagerinfo v = new AAPIQueryAuthmanagerinfo();

		return v;
	}

	private AAPIQueryAuthmanagerinfo() {}

	private String securitysensitiveoperation;

	private AAPIQueryAuthmanagerinfoRequestsfor requestsfor;

	private Boolean mergerequestfields;

	private AAPIQueryAuthmanagerinfoMessageformat messageformat;

	/**Test whether the user's current authentication status is sufficient for the specified security-sensitive operation.
	 */
	public AAPIQueryAuthmanagerinfo securitysensitiveoperation(String securitysensitiveoperation) {
		this.securitysensitiveoperation = securitysensitiveoperation;

		return this;
	}

	/**Test whether the user's current authentication status is sufficient for the specified security-sensitive operation.
	 */
	public String getSecuritysensitiveoperation() {
		return this.securitysensitiveoperation;
	}

	/**Fetch information about the authentication requests needed for the specified authentication action.
	 */
	public AAPIQueryAuthmanagerinfo requestsfor(AAPIQueryAuthmanagerinfoRequestsfor requestsfor) {
		this.requestsfor = requestsfor;

		return this;
	}

	/**Fetch information about the authentication requests needed for the specified authentication action.
	 */
	public AAPIQueryAuthmanagerinfoRequestsfor getRequestsfor() {
		return this.requestsfor;
	}

	/**Merge field information for all authentication requests into one array.
	 */
	public AAPIQueryAuthmanagerinfo mergerequestfields(Boolean mergerequestfields) {
		this.mergerequestfields = mergerequestfields;

		return this;
	}

	/**Merge field information for all authentication requests into one array.
	 */
	public Boolean getMergerequestfields() {
		return this.mergerequestfields;
	}

	/**Format to use for returning messages.
	 */
	public AAPIQueryAuthmanagerinfo messageformat(
			AAPIQueryAuthmanagerinfoMessageformat messageformat) {
		this.messageformat = messageformat;

		return this;
	}

	/**Format to use for returning messages.
	 */
	public AAPIQueryAuthmanagerinfoMessageformat getMessageformat() {
		return this.messageformat;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryAuthmanagerinfo(");

		if (securitysensitiveoperation != null) {

			sb.append("amisecuritysensitiveoperation")
					.append("=")
					.append(securitysensitiveoperation);

			sb.append(", ");
		}

		if (requestsfor != null) {

			sb.append("amirequestsfor").append("=").append(requestsfor.getJsonValue());

			sb.append(", ");
		}

		if (mergerequestfields != null) {

			sb.append("amimergerequestfields").append("=").append(mergerequestfields.toString());

			sb.append(", ");
		}

		if (messageformat != null) {

			sb.append("amimessageformat").append("=").append(messageformat.getJsonValue());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (securitysensitiveoperation != null) {

			req.addParameter(
					paramPrefix + "amisecuritysensitiveoperation", securitysensitiveoperation);
		}

		if (requestsfor != null) {

			req.addParameter(paramPrefix + "amirequestsfor", requestsfor.getJsonValue());
		}

		if (mergerequestfields != null) {

			req.addParameter(paramPrefix + "amimergerequestfields", mergerequestfields.toString());
		}

		if (messageformat != null) {

			req.addParameter(paramPrefix + "amimessageformat", messageformat.getJsonValue());
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
			c.accept(AAPIQueryAuthmanagerinfo.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryAuthmanagerinfo.this);

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
