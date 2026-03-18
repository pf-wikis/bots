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
	FACTS(128, "Facts:", "Facts", null, null),

	MAIN(0, "", "Main", null, null),

	SPECIAL(-1, "Special:", "Special", null, null),

	FACTS_TALK(129, "Facts talk:", "Facts talk", null, null),

	TALK(1, "Talk:", "Talk", null, null),

	MEDIA(-2, "Media:", "Media", null, null),

	STYLE(130, "Style:", "Style", null, null),

	USER(2, "User:", "User", null, null),

	STYLE_TALK(131, "Style talk:", "Style talk", null, null),

	USER_TALK(3, "User talk:", "User talk", null, null),

	PROJECT(
			4,
			"Project:",
			"Project",
			new EnumMap<>(Map.of(Wiki.PF, "PathfinderWiki", Wiki.SF, "StarfinderWiki")),
			new EnumMap<>(Map.of(Wiki.PF, "PathfinderWiki:", Wiki.SF, "StarfinderWiki:"))),

	PROJECT_TALK(
			5,
			"Project talk:",
			"Project talk",
			new EnumMap<>(Map.of(Wiki.PF, "PathfinderWiki talk", Wiki.SF, "StarfinderWiki talk")),
			new EnumMap<>(
					Map.of(Wiki.PF, "PathfinderWiki talk:", Wiki.SF, "StarfinderWiki talk:"))),

	FILE(6, "File:", "File", null, null),

	FILE_TALK(7, "File talk:", "File talk", null, null),

	MEDIAWIKI(8, "MediaWiki:", "MediaWiki", null, null),

	MEDIAWIKI_TALK(9, "MediaWiki talk:", "MediaWiki talk", null, null),

	TEMPLATE(10, "Template:", "Template", null, null),

	TEMPLATE_TALK(11, "Template talk:", "Template talk", null, null),

	HELP(12, "Help:", "Help", null, null),

	HELP_TALK(13, "Help talk:", "Help talk", null, null),

	CATEGORY(14, "Category:", "Category", null, null),

	CATEGORY_TALK(15, "Category talk:", "Category talk", null, null),

	WIDGET(274, "Widget:", "Widget", null, null),

	WIDGET_TALK(275, "Widget talk:", "Widget talk", null, null),

	GEOJSON(420, "GeoJson:", "GeoJson", null, null),

	GEOJSON_TALK(421, "GeoJson talk:", "GeoJson talk", null, null),

	PROPERTY(102, "Property:", "Property", null, null),

	PROPERTY_TALK(103, "Property talk:", "Property talk", null, null),

	FORM(106, "Form:", "Form", null, null),

	FORM_TALK(107, "Form talk:", "Form talk", null, null),

	CONCEPT(108, "Concept:", "Concept", null, null),

	CONCEPT_TALK(109, "Concept talk:", "Concept talk", null, null),

	SMW_SCHEMA(112, "smw/schema:", "smw/schema", null, null),

	SMW_SCHEMA_TALK(113, "smw/schema talk:", "smw/schema talk", null, null),

	META(126, "Meta:", "Meta", null, null),

	META_TALK(127, "Meta talk:", "Meta talk", null, null);

	@Getter(onMethod_ = @JsonValue)
	private final int id;

	private final String prefix;
	private final String label;
	private final EnumMap<Wiki, String> alternativeLabels;
	private final EnumMap<Wiki, String> alternativePrefixes;

	public static NS fromId(int id) {
		return switch (id) {
			case 128 -> FACTS;

			case 0 -> MAIN;

			case -1 -> SPECIAL;

			case 129 -> FACTS_TALK;

			case 1 -> TALK;

			case -2 -> MEDIA;

			case 130 -> STYLE;

			case 2 -> USER;

			case 131 -> STYLE_TALK;

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

			case 274 -> WIDGET;

			case 275 -> WIDGET_TALK;

			case 420 -> GEOJSON;

			case 421 -> GEOJSON_TALK;

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
			case "Facts" -> FACTS;

			case "Main" -> MAIN;

			case "Special" -> SPECIAL;

			case "Facts talk" -> FACTS_TALK;

			case "Talk" -> TALK;

			case "Media" -> MEDIA;

			case "Style" -> STYLE;

			case "User" -> USER;

			case "Style talk" -> STYLE_TALK;

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

			case "Widget" -> WIDGET;

			case "Widget talk" -> WIDGET_TALK;

			case "GeoJson" -> GEOJSON;

			case "GeoJson talk" -> GEOJSON_TALK;

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

			default -> null;
		};
	}
}
