package io.github.pfwikis.bots.facts.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.temporal.Temporal;
import java.util.Arrays;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.NumericNode;
import com.fasterxml.jackson.databind.node.TextNode;

import io.github.pfwikis.bots.common.model.SemanticAsk.WikiDate;
import io.github.pfwikis.bots.common.model.subject.PageRef;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SMWPropertyType {
	ANNOTATION_URI(
		null,
		"http://semantic-mediawiki.org/swivt/1.0#_anu",
		"Annotation URI"
	),
    BOOLEAN(
		4,
		"http://semantic-mediawiki.org/swivt/1.0#_boo",
		"Boolean"
	) {
		@Override
		public Boolean convertToJava(JsonNode v) {
			return "t".equals(v.textValue());
		}
	},
    CODE(
		null,
		"http://semantic-mediawiki.org/swivt/1.0#_cod",
		"Code"
	),
    DATE(
		6,
		"http://semantic-mediawiki.org/swivt/1.0#_dat",
		"Date"
	) {
		@Override
		public Temporal convertToJava(JsonNode v) {
			return WikiDate.parseRaw(v.textValue());
		}
	},
    EMAIL(
		null,
		"http://semantic-mediawiki.org/swivt/1.0#_ema",
		"Email"
	),
    EXTERNAL_IDENTIFIER(
		null,
		"http://semantic-mediawiki.org/swivt/1.0#_eid",
		"External identifier"
	),
    GEOGRAPHIC_COORDINATES(
		null,
		"http://semantic-mediawiki.org/swivt/1.0#_geo",
		"Geographic coordinates"
	),
    KEYWORD(
		null,
		"http://semantic-mediawiki.org/swivt/1.0#_keyw",
		"Keyword"
	),
    MONOLINGUAL_TEXT(
		null,
		"http://semantic-mediawiki.org/swivt/1.0#_mlt_rec",
		"Monolingual text"
	),
    NUMBER(
		1,
		"http://semantic-mediawiki.org/swivt/1.0#_num",
    	"Number"
	) {
		@Override
		public Number convertToJava(JsonNode v) {
			if(v instanceof TextNode tn) {
				var dec = new BigDecimal(tn.textValue());
				if(dec.stripTrailingZeros().scale()<=0) {
					try {
						return dec.intValueExact();
					} catch(Exception e) {}
				}
				return dec;
			}
			var n = (NumericNode)v;
			if(n.canConvertToInt())
				return n.intValue();
			else
				return n.decimalValue();
		}
	},
    PAGE(
		9,
		"http://semantic-mediawiki.org/swivt/1.0#_wpg",
		"Page"
	) {
		@Override
		public PageRef convertToJava(JsonNode v) {
			return PageRef.of(v);
		}
	},
    QUANTITY(
		null,
		"http://semantic-mediawiki.org/swivt/1.0#_qty",
		"Quantity"
	),
    RECORD(
		null,
		"http://semantic-mediawiki.org/swivt/1.0#_rec",
		"Record"
	),
    REFERENCE(
		null,
		"http://semantic-mediawiki.org/swivt/1.0#_ref_rec",
		"Reference"
	),
    TELEPHONE_NUMBER(
		null,
		"http://semantic-mediawiki.org/swivt/1.0#_tel",
		"Telephone number"
	),
    TEMPERATURE(
		null,
		"http://semantic-mediawiki.org/swivt/1.0#_tem",
		"Temperature"
	),
    TEXT(
		2,
		"http://semantic-mediawiki.org/swivt/1.0#_txt",
		"Text"
	),
    URL(
		5,
		"http://semantic-mediawiki.org/swivt/1.0#_uri",
		"URL"
	);

	private final static Map<String, SMWPropertyType> MAP = Arrays.stream(SMWPropertyType.values())
			.flatMap(p->Stream.concat(
				Stream.of(Pair.of(p.id, p)),
				p.intId!=null?Stream.of(Pair.of(p.intId.toString(), p)):Stream.empty()
			))
			.collect(Collectors.toMap(f->f.getKey(), f->f.getValue()));
	private final static Map<Integer, SMWPropertyType> INT_MAP = Arrays.stream(SMWPropertyType.values()).filter(f->f.intId!=null).collect(Collectors.toMap(f->f.intId, f->f));
	
	private final Integer intId;
	private final String id;
	@Getter
	private final String wikiName;

	@JsonCreator
	public static SMWPropertyType of(String id) {
		var res = MAP.get(id);
		if(res == null) {
			throw new NoSuchElementException("Unknown fact type '"+id+"'");
		}
		return res;
	}
	
	@JsonCreator
	public static SMWPropertyType of(int id) {
		var res = INT_MAP.get(id);
		if(res == null) {
			throw new NoSuchElementException("Unknown fact type '"+id+"'");
		}
		return res;
	}

	public Object convertToJava(JsonNode v) {
		return v.textValue();
	}
}
