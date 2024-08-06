package io.github.pfwikis.bots.facts.model;

import java.util.Arrays;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SMWPropertyType {
	ANNOTATION_URI("http://semantic-mediawiki.org/swivt/1.0#_anu"),
    BOOLEAN("http://semantic-mediawiki.org/swivt/1.0#_boo"),
    CODE("http://semantic-mediawiki.org/swivt/1.0#_cod"),
    DATE("http://semantic-mediawiki.org/swivt/1.0#_dat"),
    EMAIL("http://semantic-mediawiki.org/swivt/1.0#_ema"),
    EXTERNAL_IDENTIFIER("http://semantic-mediawiki.org/swivt/1.0#_eid"),
    GEOGRAPHIC_COORDINATES("http://semantic-mediawiki.org/swivt/1.0#_geo"),
    KEYWORD("http://semantic-mediawiki.org/swivt/1.0#_keyw"),
    MONOLINGUAL_TEXT("http://semantic-mediawiki.org/swivt/1.0#_mlt_rec"),
    NUMBER("http://semantic-mediawiki.org/swivt/1.0#_num"),
    PAGE("http://semantic-mediawiki.org/swivt/1.0#_wpg"),
    QUANTITY("http://semantic-mediawiki.org/swivt/1.0#_qty"),
    RECORD("http://semantic-mediawiki.org/swivt/1.0#_rec"),
    REFERENCE("http://semantic-mediawiki.org/swivt/1.0#_ref_rec"),
    TELEPHONE_NUMBER("http://semantic-mediawiki.org/swivt/1.0#_tel"),
    TEMPERATURE("http://semantic-mediawiki.org/swivt/1.0#_tem"),
    TEXT("http://semantic-mediawiki.org/swivt/1.0#_txt"),
    URL("http://semantic-mediawiki.org/swivt/1.0#_uri");

	private final static Map<String, SMWPropertyType> MAP = Arrays.stream(SMWPropertyType.values()).collect(Collectors.toMap(f->f.id, f->f));
	
	private final String id;

	@JsonCreator
	public static SMWPropertyType of(String id) {
		var res = MAP.get(id);
		if(res == null) {
			throw new NoSuchElementException("Unknown fact type '"+id+"'");
		}
		return res;
	}
}
