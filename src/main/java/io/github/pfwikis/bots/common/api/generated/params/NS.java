package io.github.pfwikis.bots.common.api.generated.params;

// import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.RequiredArgsConstructor;
import lombok.Getter;

import java.util.EnumMap;
import java.util.Map;
import io.github.pfwikis.bots.common.Wiki;

@Getter
@RequiredArgsConstructor
public enum NS {
	MEDIA(-2, false, "Media:", "Media", null, null),

	SPECIAL(-1, false, "Special:", "Special", null, null),

	MAIN(0, false, "", "Main", null, null),

	TALK(1, true, "Talk:", "Talk", null, null),

	USER(2, false, "User:", "User", null, null),

	USER_TALK(3, true, "User talk:", "User talk", null, null),

	PROJECT(
			4,
			false,
			"Project:",
			"Project",
			new EnumMap<>(Map.of(Wiki.PF, "PathfinderWiki", Wiki.SF, "StarfinderWiki")),
			new EnumMap<>(Map.of(Wiki.PF, "PathfinderWiki:", Wiki.SF, "StarfinderWiki:"))),

	PROJECT_TALK(
			5,
			true,
			"Project talk:",
			"Project talk",
			new EnumMap<>(Map.of(Wiki.PF, "PathfinderWiki talk", Wiki.SF, "StarfinderWiki talk")),
			new EnumMap<>(
					Map.of(Wiki.PF, "PathfinderWiki talk:", Wiki.SF, "StarfinderWiki talk:"))),

	FILE(6, false, "File:", "File", null, null),

	FILE_TALK(7, true, "File talk:", "File talk", null, null),

	MEDIAWIKI(8, false, "MediaWiki:", "MediaWiki", null, null),

	MEDIAWIKI_TALK(9, true, "MediaWiki talk:", "MediaWiki talk", null, null),

	TEMPLATE(10, false, "Template:", "Template", null, null),

	TEMPLATE_TALK(11, true, "Template talk:", "Template talk", null, null),

	HELP(12, false, "Help:", "Help", null, null),

	HELP_TALK(13, true, "Help talk:", "Help talk", null, null),

	CATEGORY(14, false, "Category:", "Category", null, null),

	CATEGORY_TALK(15, true, "Category talk:", "Category talk", null, null),

	PROPERTY(102, false, "Property:", "Property", null, null),

	PROPERTY_TALK(103, true, "Property talk:", "Property talk", null, null),

	FORM(106, false, "Form:", "Form", null, null),

	FORM_TALK(107, true, "Form talk:", "Form talk", null, null),

	CONCEPT(108, false, "Concept:", "Concept", null, null),

	CONCEPT_TALK(109, true, "Concept talk:", "Concept talk", null, null),

	SMW_SCHEMA(112, false, "smw/schema:", "smw/schema", null, null),

	SMW_SCHEMA_TALK(113, true, "smw/schema talk:", "smw/schema talk", null, null),

	META(126, false, "Meta:", "Meta", null, null),

	META_TALK(127, true, "Meta talk:", "Meta talk", null, null),

	FACTS(128, false, "Facts:", "Facts", null, null),

	FACTS_TALK(129, true, "Facts talk:", "Facts talk", null, null),

	STYLE(130, false, "Style:", "Style", null, null),

	STYLE_TALK(131, true, "Style talk:", "Style talk", null, null),

	WIDGET(274, false, "Widget:", "Widget", null, null),

	WIDGET_TALK(275, true, "Widget talk:", "Widget talk", null, null),

	GEOJSON(420, false, "GeoJson:", "GeoJson", null, null),

	GEOJSON_TALK(421, true, "GeoJson talk:", "GeoJson talk", null, null);

	@Getter(onMethod_ = @JsonValue)
	private final int id;

	private final boolean talk;
	private final String prefix;
	private final String label;
	private final EnumMap<Wiki, String> alternativeLabels;
	private final EnumMap<Wiki, String> alternativePrefixes;

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
		NS result = fromNameOrNull(name);
		if (result == null) {
			throw new IllegalArgumentException("Unknown namespace name " + name);
		}
		return result;
	}

	public static NS fromNameOrNull(String name) {
		return switch (name) {
			case "Media" -> MEDIA;

			case "Special" -> SPECIAL;

			case "Main" -> MAIN;

			case "Talk" -> TALK;

			case "User" -> USER;

			case "User talk" -> USER_TALK;

			case "Project", "PathfinderWiki", "StarfinderWiki" -> PROJECT;

			case "Project talk", "PathfinderWiki talk", "StarfinderWiki talk" -> PROJECT_TALK;

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

			default -> null;
		};
	}
}
