package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Which information to get for each language.
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryLanguageinfoProp {

	/**The autonym of the language, that is, the name in that language.*/
	AUTONYM("autonym"),

	/**The BCP-47 language code.*/
	BCP47("bcp47"),

	/**The language code. (This code is MediaWiki-specific, though there are overlaps with other standards.)*/
	CODE("code"),

	/**The writing direction of the language (either <code>ltr</code> or <code>rtl</code>).*/
	DIR("dir"),

	/**The language codes of the fallback languages configured for this language. The implicit final fallback to 'en' is not included (but some languages may fall back to 'en' explicitly).*/
	FALLBACKS("fallbacks"),

	/**The name of the language in the language specified by the <var>uselang</var> parameter, with language fallbacks applied if necessary.*/
	NAME("name"),

	/**The short names for language variants used for language conversion links.*/
	VARIANTNAMES("variantnames"),

	/**The language codes of the variants supported by this language.*/
	VARIANTS("variants");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryLanguageinfoProp> set =
				EnumSet.noneOf(AAPIQueryLanguageinfoProp.class);

		public AAPIQueryLanguageinfoProp[] build() {
			return set.toArray(AAPIQueryLanguageinfoProp[]::new);
		}

		/**The autonym of the language, that is, the name in that language.*/
		public Builder AUTONYM() {
			set.add(AUTONYM);
			return this;
		}

		/**The BCP-47 language code.*/
		public Builder BCP47() {
			set.add(BCP47);
			return this;
		}

		/**The language code. (This code is MediaWiki-specific, though there are overlaps with other standards.)*/
		public Builder CODE() {
			set.add(CODE);
			return this;
		}

		/**The writing direction of the language (either <code>ltr</code> or <code>rtl</code>).*/
		public Builder DIR() {
			set.add(DIR);
			return this;
		}

		/**The language codes of the fallback languages configured for this language. The implicit final fallback to 'en' is not included (but some languages may fall back to 'en' explicitly).*/
		public Builder FALLBACKS() {
			set.add(FALLBACKS);
			return this;
		}

		/**The name of the language in the language specified by the <var>uselang</var> parameter, with language fallbacks applied if necessary.*/
		public Builder NAME() {
			set.add(NAME);
			return this;
		}

		/**The short names for language variants used for language conversion links.*/
		public Builder VARIANTNAMES() {
			set.add(VARIANTNAMES);
			return this;
		}

		/**The language codes of the variants supported by this language.*/
		public Builder VARIANTS() {
			set.add(VARIANTS);
			return this;
		}
	}
}
