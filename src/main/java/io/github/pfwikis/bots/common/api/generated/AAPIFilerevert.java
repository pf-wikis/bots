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

/** Revert a file to an old version.
 */
public class AAPIFilerevert implements AAPIModule, AAPITokenModule, AAPIMainActionModule {

	public static AAPIFilerevert create(@NonNull String filename, @NonNull String archivename) {

		AAPIFilerevert v = new AAPIFilerevert();

		v.filename = filename;

		v.archivename = archivename;

		return v;
	}

	private AAPIFilerevert() {}

	private String filename;

	private String comment;

	private String archivename;

	private String token;

	/**Target filename, without the File: prefix.
	 */
	public String getFilename() {
		return this.filename;
	}

	/**Upload comment.
	 */
	public AAPIFilerevert comment(String comment) {

		this.comment = comment;

		return this;
	}

	/**Upload comment.
	 */
	public String getComment() {
		return this.comment;
	}

	/**Archive name of the revision to revert to.
	 */
	public String getArchivename() {
		return this.archivename;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public AAPIFilerevert token(String token) {

		this.token = token;

		return this;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public String getToken() {
		return this.token;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIFilerevert(");

		if (filename != null) {

			sb.append("filename").append("=").append(filename);

			sb.append(", ");
		}

		if (comment != null) {

			sb.append("comment").append("=").append(comment);

			sb.append(", ");
		}

		if (archivename != null) {

			sb.append("archivename").append("=").append(archivename);

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

		if (filename != null) {

			req.addParameter(paramPrefix + "filename", filename);
		}

		if (comment != null) {

			req.addParameter(paramPrefix + "comment", comment);
		}

		if (archivename != null) {

			req.addParameter(paramPrefix + "archivename", archivename);
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
			c.accept(AAPIFilerevert.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIFilerevert.this);

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
