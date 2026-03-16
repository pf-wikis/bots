package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import io.github.pfwikis.bots.common.api.generated.AAPIJson;

import io.github.pfwikis.bots.common.api.generated.AAPIJsonfm;

import io.github.pfwikis.bots.common.api.generated.AAPINone;

import io.github.pfwikis.bots.common.api.generated.AAPIPhp;

import io.github.pfwikis.bots.common.api.generated.AAPIPhpfm;

import io.github.pfwikis.bots.common.api.generated.AAPIRawfm;

import io.github.pfwikis.bots.common.api.generated.AAPIXml;

import io.github.pfwikis.bots.common.api.generated.AAPIXmlfm;

/**<p>The format of the output.
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIMainFormat {

	/**Output data in JSON format.*/
	JSON("json", AAPIJson.class),

	/**Output data in JSON format (pretty-print in HTML).*/
	JSONFM("jsonfm", AAPIJsonfm.class),

	/**Output nothing.*/
	NONE("none", AAPINone.class),

	/**Output data in serialized PHP format.*/
	PHP("php", AAPIPhp.class),

	/**Output data in serialized PHP format (pretty-print in HTML).*/
	PHPFM("phpfm", AAPIPhpfm.class),

	/**Output data, including debugging elements, in JSON format (pretty-print in HTML).*/
	RAWFM("rawfm", AAPIRawfm.class),

	/**Output data in XML format.*/
	XML("xml", AAPIXml.class),

	/**Output data in XML format (pretty-print in HTML).*/
	XMLFM("xmlfm", AAPIXmlfm.class);

	private final String jsonValue;

	private final Class<? extends AAPIMainFormatModule> type;

	public static interface AAPIMainFormatModule extends AAPIModule {}

	public static class AAPIMainFormatSubmodule
			extends AAPISubmodule<AAPIMainFormat, AAPIMainFormatModule> {
		public AAPIMainFormatSubmodule(AAPIMainFormat k, AAPIMainFormatModule v) {
			super(k, v);
		}
	}

	public static AAPIMainFormatSubmodule createSubmodule(AAPIMainFormatModule module) {
		return new AAPIMainFormatSubmodule(
				switch (module) {
					case AAPIJson g -> JSON;

					case AAPIJsonfm g -> JSONFM;

					case AAPINone g -> NONE;

					case AAPIPhp g -> PHP;

					case AAPIPhpfm g -> PHPFM;

					case AAPIRawfm g -> RAWFM;

					case AAPIXml g -> XML;

					case AAPIXmlfm g -> XMLFM;

					default -> throw new IllegalArgumentException();
				},
				module);
	}

	public static List<AAPIMainFormatSubmodule> createSubmodule(AAPIMainFormatModule... modules) {
		var result = new ArrayList<AAPIMainFormatSubmodule>();
		for (var v : modules) {
			result.add(createSubmodule(v));
		}
		return result;
	}
}
