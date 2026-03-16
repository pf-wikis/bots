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

/** Find a comment by its ID or name.
 */
public class AAPIDiscussiontoolsfindcomment implements AAPIModule, AAPIMainActionModule {

	public static AAPIDiscussiontoolsfindcomment create() {

		AAPIDiscussiontoolsfindcomment v = new AAPIDiscussiontoolsfindcomment();

		return v;
	}

	private AAPIDiscussiontoolsfindcomment() {}

	private String idorname;

	private String heading;

	private String page;

	/**Comment ID or name
	 */
	public AAPIDiscussiontoolsfindcomment idorname(String idorname) {
		this.idorname = idorname;

		return this;
	}

	/**Comment ID or name
	 */
	public String getIdorname() {
		return this.idorname;
	}

	/**Heading hash fragment
	 */
	public AAPIDiscussiontoolsfindcomment heading(String heading) {
		this.heading = heading;

		return this;
	}

	/**Heading hash fragment
	 */
	public String getHeading() {
		return this.heading;
	}

	/**Page that the heading hash fragment once existed on
	 */
	public AAPIDiscussiontoolsfindcomment page(String page) {
		this.page = page;

		return this;
	}

	/**Page that the heading hash fragment once existed on
	 */
	public String getPage() {
		return this.page;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIDiscussiontoolsfindcomment(");

		if (idorname != null) {

			sb.append("idorname").append("=").append(idorname);

			sb.append(", ");
		}

		if (heading != null) {

			sb.append("heading").append("=").append(heading);

			sb.append(", ");
		}

		if (page != null) {

			sb.append("page").append("=").append(page);

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (idorname != null) {

			req.addParameter(paramPrefix + "idorname", idorname);
		}

		if (heading != null) {

			req.addParameter(paramPrefix + "heading", heading);
		}

		if (page != null) {

			req.addParameter(paramPrefix + "page", page);
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
			c.accept(AAPIDiscussiontoolsfindcomment.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIDiscussiontoolsfindcomment.this);

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
