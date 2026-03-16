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

import io.github.pfwikis.bots.common.api.generated.params.AAPIEchomarkseenType;

import io.github.pfwikis.bots.common.api.generated.params.AAPIEchomarkseenTimestampFormat;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** Mark notifications as seen for the current user.
 */
public class AAPIEchomarkseen implements AAPIModule, AAPIMainActionModule {

	public static AAPIEchomarkseen create(@NonNull AAPIEchomarkseenType type) {

		AAPIEchomarkseen v = new AAPIEchomarkseen();

		v.type = type;

		return v;
	}

	private AAPIEchomarkseen() {}

	private AAPIEchomarkseenType type;

	private AAPIEchomarkseenTimestampFormat timestampFormat;

	/**Type of notifications to mark as seen: 'alert', 'message' or 'all'.
	 */
	public AAPIEchomarkseenType getType() {
		return this.type;
	}

	/**Timestamp format to use for output, 'ISO_8601' or 'MW'. 'MW' is deprecated here, so all clients should switch to 'ISO_8601'. This parameter will be removed, and 'ISO_8601' will become the only output format.
	 */
	public AAPIEchomarkseen timestampFormat(AAPIEchomarkseenTimestampFormat timestampFormat) {
		this.timestampFormat = timestampFormat;

		return this;
	}

	/**Timestamp format to use for output, 'ISO_8601' or 'MW'. 'MW' is deprecated here, so all clients should switch to 'ISO_8601'. This parameter will be removed, and 'ISO_8601' will become the only output format.
	 */
	public AAPIEchomarkseenTimestampFormat getTimestampFormat() {
		return this.timestampFormat;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIEchomarkseen(");

		if (type != null) {

			sb.append("type").append("=").append(type.getJsonValue());

			sb.append(", ");
		}

		if (timestampFormat != null) {

			sb.append("timestampFormat").append("=").append(timestampFormat.getJsonValue());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (type != null) {

			req.addParameter(paramPrefix + "type", type.getJsonValue());
		}

		if (timestampFormat != null) {

			req.addParameter(paramPrefix + "timestampFormat", timestampFormat.getJsonValue());
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
			c.accept(AAPIEchomarkseen.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIEchomarkseen.this);

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
