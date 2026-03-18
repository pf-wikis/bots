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

import io.github.pfwikis.bots.common.api.generated.params.AAPICompareFromslots;

import io.github.pfwikis.bots.common.api.generated.params.AAPICompareTorelative;

import io.github.pfwikis.bots.common.api.generated.params.AAPICompareToslots;

import io.github.pfwikis.bots.common.api.generated.params.AAPICompareProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPICompareSlots;

import io.github.pfwikis.bots.common.api.generated.params.AAPICompareDifftype;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** <p>Get the difference between two pages.
 * </p>
 * <p>A revision number, a page title, a page ID, text, or a relative reference for both "from" and "to" must be passed.
 * </p>
 */
public class AAPICompare implements AAPIModule, AAPIMainActionModule {

	public static AAPICompare create() {

		AAPICompare v = new AAPICompare();

		return v;
	}

	private AAPICompare() {}

	private String fromtitle;

	private Long fromid;

	private Long fromrev;

	private List<AAPICompareFromslots> fromslots;

	private Boolean frompst;

	private String totitle;

	private Long toid;

	private Long torev;

	private AAPICompareTorelative torelative;

	private List<AAPICompareToslots> toslots;

	private Boolean topst;

	private List<AAPICompareProp> prop;

	private List<AAPICompareSlots> slots;

	private AAPICompareDifftype difftype;

	/**First title to compare.
	 */
	public AAPICompare fromtitle(String fromtitle) {

		this.fromtitle = fromtitle;

		return this;
	}

	/**First title to compare.
	 */
	public String getFromtitle() {
		return this.fromtitle;
	}

	/**First page ID to compare.
	 */
	public AAPICompare fromid(Long fromid) {

		this.fromid = fromid;

		return this;
	}

	/**First page ID to compare.
	 */
	public Long getFromid() {
		return this.fromid;
	}

	/**First revision to compare.
	 */
	public AAPICompare fromrev(Long fromrev) {

		this.fromrev = fromrev;

		return this;
	}

	/**First revision to compare.
	 */
	public Long getFromrev() {
		return this.fromrev;
	}

	/**<p>Override content of the revision specified by <var>fromtitle</var>, <var>fromid</var> or <var>fromrev</var>.
	 * </p><p>This parameter specifies the slots that are to be modified. Use <var>fromtext-&#x7b;slot}</var>, <var>fromcontentmodel-&#x7b;slot}</var>, and <var>fromcontentformat-&#x7b;slot}</var> to specify content for each slot.
	 * </p>
	 */
	public AAPICompare fromslots(AAPICompareFromslots... fromslots) {

		this.fromslots = List.of(fromslots);

		return this;
	}

	/**<p>Override content of the revision specified by <var>fromtitle</var>, <var>fromid</var> or <var>fromrev</var>.
	 * </p><p>This parameter specifies the slots that are to be modified. Use <var>fromtext-&#x7b;slot}</var>, <var>fromcontentmodel-&#x7b;slot}</var>, and <var>fromcontentformat-&#x7b;slot}</var> to specify content for each slot.
	 * </p>
	 */
	public List<AAPICompareFromslots> getFromslots() {
		return this.fromslots;
	}

	/**Do a pre-save transform on <var>fromtext-&#x7b;slot}</var>.
	 */
	public AAPICompare frompst(Boolean frompst) {

		this.frompst = frompst;

		return this;
	}

	/**Do a pre-save transform on <var>fromtext-&#x7b;slot}</var>.
	 */
	public Boolean getFrompst() {
		return this.frompst;
	}

	/**Second title to compare.
	 */
	public AAPICompare totitle(String totitle) {

		this.totitle = totitle;

		return this;
	}

	/**Second title to compare.
	 */
	public String getTotitle() {
		return this.totitle;
	}

	/**Second page ID to compare.
	 */
	public AAPICompare toid(Long toid) {

		this.toid = toid;

		return this;
	}

	/**Second page ID to compare.
	 */
	public Long getToid() {
		return this.toid;
	}

	/**Second revision to compare.
	 */
	public AAPICompare torev(Long torev) {

		this.torev = torev;

		return this;
	}

	/**Second revision to compare.
	 */
	public Long getTorev() {
		return this.torev;
	}

	/**Use a revision relative to the revision determined from <var>fromtitle</var>, <var>fromid</var> or <var>fromrev</var>. All of the other 'to' options will be ignored.
	 */
	public AAPICompare torelative(AAPICompareTorelative torelative) {

		this.torelative = torelative;

		return this;
	}

	/**Use a revision relative to the revision determined from <var>fromtitle</var>, <var>fromid</var> or <var>fromrev</var>. All of the other 'to' options will be ignored.
	 */
	public AAPICompareTorelative getTorelative() {
		return this.torelative;
	}

	/**<p>Override content of the revision specified by <var>totitle</var>, <var>toid</var> or <var>torev</var>.
	 * </p><p>This parameter specifies the slots that are to be modified. Use <var>totext-&#x7b;slot}</var>, <var>tocontentmodel-&#x7b;slot}</var>, and <var>tocontentformat-&#x7b;slot}</var> to specify content for each slot.
	 * </p>
	 */
	public AAPICompare toslots(AAPICompareToslots... toslots) {

		this.toslots = List.of(toslots);

		return this;
	}

	/**<p>Override content of the revision specified by <var>totitle</var>, <var>toid</var> or <var>torev</var>.
	 * </p><p>This parameter specifies the slots that are to be modified. Use <var>totext-&#x7b;slot}</var>, <var>tocontentmodel-&#x7b;slot}</var>, and <var>tocontentformat-&#x7b;slot}</var> to specify content for each slot.
	 * </p>
	 */
	public List<AAPICompareToslots> getToslots() {
		return this.toslots;
	}

	/**Do a pre-save transform on <var>totext</var>.
	 */
	public AAPICompare topst(Boolean topst) {

		this.topst = topst;

		return this;
	}

	/**Do a pre-save transform on <var>totext</var>.
	 */
	public Boolean getTopst() {
		return this.topst;
	}

	/**<p>Which pieces of information to get.
	 * </p>
	 * <dl></dl>
	 */
	public AAPICompare prop(AAPICompareProp... prop) {

		this.prop = List.of(prop);

		return this;
	}

	/**<p>Which pieces of information to get.
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPICompareProp> getProp() {
		return this.prop;
	}

	/**Return individual diffs for these slots, rather than one combined diff for all slots.
	 */
	public AAPICompare slots(AAPICompareSlots... slots) {

		this.slots = List.of(slots);

		return this;
	}

	/**Return individual diffs for these slots, rather than one combined diff for all slots.
	 */
	public List<AAPICompareSlots> getSlots() {
		return this.slots;
	}

	/**Return the comparison formatted as inline HTML.
	 */
	public AAPICompare difftype(AAPICompareDifftype difftype) {

		this.difftype = difftype;

		return this;
	}

	/**Return the comparison formatted as inline HTML.
	 */
	public AAPICompareDifftype getDifftype() {
		return this.difftype;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPICompare(");

		if (fromtitle != null) {

			sb.append("fromtitle").append("=").append(fromtitle);

			sb.append(", ");
		}

		if (fromid != null) {

			sb.append("fromid").append("=").append(fromid.toString());

			sb.append(", ");
		}

		if (fromrev != null) {

			sb.append("fromrev").append("=").append(fromrev.toString());

			sb.append(", ");
		}

		if (fromslots != null) {

			sb.append("fromslots")
					.append("=")
					.append(
							fromslots.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (frompst != null) {

			sb.append("frompst").append("=").append(frompst.toString());

			sb.append(", ");
		}

		if (totitle != null) {

			sb.append("totitle").append("=").append(totitle);

			sb.append(", ");
		}

		if (toid != null) {

			sb.append("toid").append("=").append(toid.toString());

			sb.append(", ");
		}

		if (torev != null) {

			sb.append("torev").append("=").append(torev.toString());

			sb.append(", ");
		}

		if (torelative != null) {

			sb.append("torelative").append("=").append(torelative.getJsonValue());

			sb.append(", ");
		}

		if (toslots != null) {

			sb.append("toslots")
					.append("=")
					.append(
							toslots.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (topst != null) {

			sb.append("topst").append("=").append(topst.toString());

			sb.append(", ");
		}

		if (prop != null) {

			sb.append("prop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (slots != null) {

			sb.append("slots")
					.append("=")
					.append(
							slots.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (difftype != null) {

			sb.append("difftype").append("=").append(difftype.getJsonValue());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (fromtitle != null) {

			req.addParameter(paramPrefix + "fromtitle", fromtitle);
		}

		if (fromid != null) {

			req.addParameter(paramPrefix + "fromid", fromid.toString());
		}

		if (fromrev != null) {

			req.addParameter(paramPrefix + "fromrev", fromrev.toString());
		}

		if (fromslots != null) {

			req.addParameter(
					paramPrefix + "fromslots",
					fromslots.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (frompst != null) {

			req.addParameter(paramPrefix + "frompst", frompst.toString());
		}

		if (totitle != null) {

			req.addParameter(paramPrefix + "totitle", totitle);
		}

		if (toid != null) {

			req.addParameter(paramPrefix + "toid", toid.toString());
		}

		if (torev != null) {

			req.addParameter(paramPrefix + "torev", torev.toString());
		}

		if (torelative != null) {

			req.addParameter(paramPrefix + "torelative", torelative.getJsonValue());
		}

		if (toslots != null) {

			req.addParameter(
					paramPrefix + "toslots",
					toslots.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (topst != null) {

			req.addParameter(paramPrefix + "topst", topst.toString());
		}

		if (prop != null) {

			req.addParameter(
					paramPrefix + "prop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (slots != null) {

			req.addParameter(
					paramPrefix + "slots",
					slots.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (difftype != null) {

			req.addParameter(paramPrefix + "difftype", difftype.getJsonValue());
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
			c.accept(AAPICompare.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPICompare.this);

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
