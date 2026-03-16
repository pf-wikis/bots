package io.github.pfwikis.bots.common.api.generated.params;

// import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.RequiredArgsConstructor;
import lombok.Getter;

@Getter
@RequiredArgsConstructor
public enum NS {
	MEDIA(-2, "Media:", "Media"),

	SPECIAL(-1, "Special:", "Special"),

	MAIN(0, "", "Main"),

	TALK(1, "Talk:", "Talk"),

	USER(2, "User:", "User"),

	USER_TALK(3, "User talk:", "User talk"),

	PROJECT(4, "Project:", "Project"),

	PROJECT_TALK(5, "Project talk:", "Project talk"),

	FILE(6, "File:", "File"),

	FILE_TALK(7, "File talk:", "File talk"),

	MEDIAWIKI(8, "MediaWiki:", "MediaWiki"),

	MEDIAWIKI_TALK(9, "MediaWiki talk:", "MediaWiki talk"),

	TEMPLATE(10, "Template:", "Template"),

	TEMPLATE_TALK(11, "Template talk:", "Template talk"),

	HELP(12, "Help:", "Help"),

	HELP_TALK(13, "Help talk:", "Help talk"),

	CATEGORY(14, "Category:", "Category"),

	CATEGORY_TALK(15, "Category talk:", "Category talk"),

	PROPERTY(102, "Property:", "Property"),

	PROPERTY_TALK(103, "Property talk:", "Property talk"),

	FORM(106, "Form:", "Form"),

	FORM_TALK(107, "Form talk:", "Form talk"),

	CONCEPT(108, "Concept:", "Concept"),

	CONCEPT_TALK(109, "Concept talk:", "Concept talk"),

	SMW_SCHEMA(112, "smw/schema:", "smw/schema"),

	SMW_SCHEMA_TALK(113, "smw/schema talk:", "smw/schema talk"),

	META(126, "Meta:", "Meta"),

	META_TALK(127, "Meta talk:", "Meta talk"),

	FACTS(128, "Facts:", "Facts"),

	FACTS_TALK(129, "Facts talk:", "Facts talk"),

	STYLE(130, "Style:", "Style"),

	STYLE_TALK(131, "Style talk:", "Style talk"),

	WIDGET(274, "Widget:", "Widget"),

	WIDGET_TALK(275, "Widget talk:", "Widget talk"),

	GEOJSON(420, "GeoJson:", "GeoJson"),

	GEOJSON_TALK(421, "GeoJson talk:", "GeoJson talk");

	@Getter(onMethod_ = @JsonValue)
	private final int id;

	private final String prefix;
	private final String label;

	public static NS fromId(int id) {
		return switch (id) {
			case -2 -> MEDIA;

			case -1 -> SPECIAL;

			case 0 -> MAIN;

			case 1 -> TALK;

			case 2 -> USER;

			case 3 -> USER_TALK;

			case 4 -> PROJECT;

			case 5 -> PROJECT_TALK;

			case 6 -> FILE;

			case 7 -> FILE_TALK;

			case 8 -> MEDIAWIKI;

			case 9 -> MEDIAWIKI_TALK;

			case 10 -> TEMPLATE;

			case 11 -> TEMPLATE_TALK;

			case 12 -> HELP;

			case 13 -> HELP_TALK;

			case 14 -> CATEGORY;

			case 15 -> CATEGORY_TALK;

			case 102 -> PROPERTY;

			case 103 -> PROPERTY_TALK;

			case 106 -> FORM;

			case 107 -> FORM_TALK;

			case 108 -> CONCEPT;

			case 109 -> CONCEPT_TALK;

			case 112 -> SMW_SCHEMA;

			case 113 -> SMW_SCHEMA_TALK;

			case 126 -> META;

			case 127 -> META_TALK;

			case 128 -> FACTS;

			case 129 -> FACTS_TALK;

			case 130 -> STYLE;

			case 131 -> STYLE_TALK;

			case 274 -> WIDGET;

			case 275 -> WIDGET_TALK;

			case 420 -> GEOJSON;

			case 421 -> GEOJSON_TALK;

			default -> throw new IllegalArgumentException("Unknown namespace id " + id);
		};
	}

	public static NS fromName(String name) {
		return switch (name) {
			case "Media" -> MEDIA;

			case "Special" -> SPECIAL;

			case "Main" -> MAIN;

			case "Talk" -> TALK;

			case "User" -> USER;

			case "User talk" -> USER_TALK;

			case "Project" -> PROJECT;

			case "Project talk" -> PROJECT_TALK;

			case "File" -> FILE;

			case "File talk" -> FILE_TALK;

			case "MediaWiki" -> MEDIAWIKI;

			case "MediaWiki talk" -> MEDIAWIKI_TALK;

			case "Template" -> TEMPLATE;

			case "Template talk" -> TEMPLATE_TALK;

			case "Help" -> HELP;

			case "Help talk" -> HELP_TALK;

			case "Category" -> CATEGORY;

			case "Category talk" -> CATEGORY_TALK;

			case "Property" -> PROPERTY;

			case "Property talk" -> PROPERTY_TALK;

			case "Form" -> FORM;

			case "Form talk" -> FORM_TALK;

			case "Concept" -> CONCEPT;

			case "Concept talk" -> CONCEPT_TALK;

			case "smw/schema" -> SMW_SCHEMA;

			case "smw/schema talk" -> SMW_SCHEMA_TALK;

			case "Meta" -> META;

			case "Meta talk" -> META_TALK;

			case "Facts" -> FACTS;

			case "Facts talk" -> FACTS_TALK;

			case "Style" -> STYLE;

			case "Style talk" -> STYLE_TALK;

			case "Widget" -> WIDGET;

			case "Widget talk" -> WIDGET_TALK;

			case "GeoJson" -> GEOJSON;

			case "GeoJson talk" -> GEOJSON_TALK;

			default -> throw new IllegalArgumentException("Unknown namespace name " + name);
		};
	}
}
