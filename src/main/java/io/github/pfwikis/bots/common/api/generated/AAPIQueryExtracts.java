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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryExtractsSectionformat;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryProp.AAPIQueryPropModule;

/** Returns plain-text or limited HTML extracts of the given pages.
 */
public class AAPIQueryExtracts implements AAPIModule, AAPIQueryPropModule {

	public static AAPIQueryExtracts create() {

		AAPIQueryExtracts v = new AAPIQueryExtracts();

		return v;
	}

	private AAPIQueryExtracts() {}

	private Long chars;

	private Long sentences;

	private Integer limit;

	private Boolean intro;

	private Boolean plaintext;

	private AAPIQueryExtractsSectionformat sectionformat;

	/**How many characters to return. Actual text returned might be slightly longer.
	 */
	public AAPIQueryExtracts chars(Long chars) {

		this.chars = chars;

		return this;
	}

	/**How many characters to return. Actual text returned might be slightly longer.
	 */
	public Long getChars() {
		return this.chars;
	}

	/**How many sentences to return.
	 */
	public AAPIQueryExtracts sentences(Long sentences) {

		this.sentences = sentences;

		return this;
	}

	/**How many sentences to return.
	 */
	public Long getSentences() {
		return this.sentences;
	}

	/**How many extracts to return. (Multiple extracts can only be returned if exintro is set to true.)
	 */
	public AAPIQueryExtracts limit(Integer limit) {

		this.limit = limit;

		return this;
	}

	/**How many extracts to return. (Multiple extracts can only be returned if exintro is set to true.)
	 */
	public Integer getLimit() {
		return this.limit;
	}

	/**Return only content before the first section.
	 */
	public AAPIQueryExtracts intro(Boolean intro) {

		this.intro = intro;

		return this;
	}

	/**Return only content before the first section.
	 */
	public Boolean getIntro() {
		return this.intro;
	}

	/**Return extracts as plain text instead of limited HTML.
	 */
	public AAPIQueryExtracts plaintext(Boolean plaintext) {

		this.plaintext = plaintext;

		return this;
	}

	/**Return extracts as plain text instead of limited HTML.
	 */
	public Boolean getPlaintext() {
		return this.plaintext;
	}

	/**<p>How to format sections in plaintext mode:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryExtracts sectionformat(AAPIQueryExtractsSectionformat sectionformat) {

		this.sectionformat = sectionformat;

		return this;
	}

	/**<p>How to format sections in plaintext mode:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryExtractsSectionformat getSectionformat() {
		return this.sectionformat;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryExtracts(");

		if (chars != null) {

			sb.append("exchars").append("=").append(chars.toString());

			sb.append(", ");
		}

		if (sentences != null) {

			sb.append("exsentences").append("=").append(sentences.toString());

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("exlimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		if (intro != null) {

			sb.append("exintro").append("=").append(intro.toString());

			sb.append(", ");
		}

		if (plaintext != null) {

			sb.append("explaintext").append("=").append(plaintext.toString());

			sb.append(", ");
		}

		if (sectionformat != null) {

			sb.append("exsectionformat").append("=").append(sectionformat.getJsonValue());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (chars != null) {

			req.addParameter(paramPrefix + "exchars", chars.toString());
		}

		if (sentences != null) {

			req.addParameter(paramPrefix + "exsentences", sentences.toString());
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "exlimit", limit.toString());
		}

		if (intro != null) {

			req.addParameter(paramPrefix + "exintro", intro.toString());
		}

		if (plaintext != null) {

			req.addParameter(paramPrefix + "explaintext", plaintext.toString());
		}

		if (sectionformat != null) {

			req.addParameter(paramPrefix + "exsectionformat", sectionformat.getJsonValue());
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
			c.accept(AAPIQueryExtracts.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryExtracts.this);

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
