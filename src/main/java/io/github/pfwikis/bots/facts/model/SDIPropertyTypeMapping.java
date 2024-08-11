package io.github.pfwikis.bots.facts.model;

import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;

import io.github.pfwikis.bots.common.model.SemanticSubject.PageRef;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SDIPropertyTypeMapping<TYPE> {

	public static SDIPropertyTypeMapping<String> ACCESSORY_TYPE = prop(String.class, "Accessory type");
	public static SDIPropertyTypeMapping<PageRef> ARTIST = prop(PageRef.class, "Artist");
	public static SDIPropertyTypeMapping<String> AUDIO_TYPE = prop(String.class, "Audio type");
	public static SDIPropertyTypeMapping<PageRef> AUTHOR = prop(PageRef.class, "Author");
	public static SDIPropertyTypeMapping<PageRef> AUTHOR_ALL = prop(PageRef.class, "Author all");
	public static SDIPropertyTypeMapping<String> AWARDS = prop(String.class, "Awards");
	public static SDIPropertyTypeMapping<String> BLURB = prop(String.class, "Blurb");
	public static SDIPropertyTypeMapping<String> BLURB_HEADING = prop(String.class, "Blurb heading");
	public static SDIPropertyTypeMapping<String> BLURB_QUOTEE = prop(String.class, "Blurb quotee");
	public static SDIPropertyTypeMapping<String> BLURB_TEXT = prop(String.class, "Blurb text");
	public static SDIPropertyTypeMapping<String> BLURB_TEXT_EXTRAS = prop(String.class, "Blurb text extras");
	public static SDIPropertyTypeMapping<String> BOOK_TYPE = prop(String.class, "Book type");
	public static SDIPropertyTypeMapping<Integer> CHAPTERS = prop(Integer.class, "Chapters");
	public static SDIPropertyTypeMapping<PageRef> COMPOSER = prop(PageRef.class, "Composer");
	public static SDIPropertyTypeMapping<String> DECK_TYPE = prop(String.class, "Deck type");
	public static SDIPropertyTypeMapping<String> DECKSIZE = prop(String.class, "Decksize");
	public static SDIPropertyTypeMapping<String> DESCRIPTION = prop(String.class, "Description");
	public static SDIPropertyTypeMapping<PageRef> DESIGNER = prop(PageRef.class, "Designer");
	public static SDIPropertyTypeMapping<PageRef> DEVELOPER = prop(PageRef.class, "Developer");
	public static SDIPropertyTypeMapping<String> DIMENSIONS = prop(String.class, "Dimensions");
	public static SDIPropertyTypeMapping<PageRef> DIRECTOR = prop(PageRef.class, "Director");
	public static SDIPropertyTypeMapping<Integer> DISCS = prop(Integer.class, "Discs");
	public static SDIPropertyTypeMapping<String> ENGINE = prop(String.class, "Engine");
	public static SDIPropertyTypeMapping<String> ERRATA = prop(String.class, "Errata");
	public static SDIPropertyTypeMapping<PageRef> FACT_TYPE = prop(PageRef.class, "Fact type");
	public static SDIPropertyTypeMapping<PageRef> FOLLOWS = prop(PageRef.class, "Follows");
	public static SDIPropertyTypeMapping<String> FULL_TITLE = prop(String.class, "Full title");
	public static SDIPropertyTypeMapping<String> GALLERY = prop(String.class, "Gallery");
	public static SDIPropertyTypeMapping<String> GENRE = prop(String.class, "Genre");
	public static SDIPropertyTypeMapping<String> HAS_FACT_TYPE = prop(String.class, "Has fact type");
	public static SDIPropertyTypeMapping<String> HAS_INFOBOX_TYPE = prop(String.class, "Has infobox type");
	public static SDIPropertyTypeMapping<String> HAS_LOCATION_TYPE = prop(String.class, "Has location type");
	public static SDIPropertyTypeMapping<String> HAS_META_TYPE = prop(String.class, "Has meta type");
	public static SDIPropertyTypeMapping<PageRef> IMAGE = prop(PageRef.class, "Image");
	public static SDIPropertyTypeMapping<Boolean> IS_SUBSECTION = prop(Boolean.class, "Is subsection");
	public static SDIPropertyTypeMapping<String> ISBN = prop(String.class, "Isbn");
	public static SDIPropertyTypeMapping<Integer> LEVEL_RANGE_END = prop(Integer.class, "Level range end");
	public static SDIPropertyTypeMapping<Integer> LEVEL_RANGE_START = prop(Integer.class, "Level range start");
	public static SDIPropertyTypeMapping<PageRef> LOCATION = prop(PageRef.class, "Location");
	public static SDIPropertyTypeMapping<String> MAP_TYPE = prop(String.class, "Map type");
	public static SDIPropertyTypeMapping<String> MATERIAL = prop(String.class, "Material");
	public static SDIPropertyTypeMapping<String> MINIATURES_TYPE = prop(String.class, "Miniatures type");
	public static SDIPropertyTypeMapping<String> MODES = prop(String.class, "Modes");
	public static SDIPropertyTypeMapping<String> NAME = prop(String.class, "Name");
	public static SDIPropertyTypeMapping<PageRef> NARRATOR = prop(PageRef.class, "Narrator");
	public static SDIPropertyTypeMapping<String> ON_PAGE = prop(String.class, "On page");
	public static SDIPropertyTypeMapping<Integer> PAGES = prop(Integer.class, "Pages");
	public static SDIPropertyTypeMapping<PageRef> PERFORMER = prop(PageRef.class, "Performer");
	public static SDIPropertyTypeMapping<String> PLATFORMS = prop(String.class, "Platforms");
	public static SDIPropertyTypeMapping<PageRef> PRECEDES = prop(PageRef.class, "Precedes");
	public static SDIPropertyTypeMapping<String> PRICE = prop(String.class, "Price");
	public static SDIPropertyTypeMapping<PageRef> PRIMARY_AUTHOR = prop(PageRef.class, "Primary author");
	public static SDIPropertyTypeMapping<PageRef> PRODUCER = prop(PageRef.class, "Producer");
	public static SDIPropertyTypeMapping<PageRef> PROGRAMMER = prop(PageRef.class, "Programmer");
	public static SDIPropertyTypeMapping<String> PUBCODE = prop(String.class, "Pubcode");
	public static SDIPropertyTypeMapping<PageRef> PUBLISHER = prop(PageRef.class, "Publisher");
	public static SDIPropertyTypeMapping<String> QUANTITY = prop(String.class, "Quantity");
	public static SDIPropertyTypeMapping<PageRef> REGION = prop(PageRef.class, "Region");
	public static SDIPropertyTypeMapping<Temporal> RELEASE_DATE = prop(Temporal.class, "Release date");
	public static SDIPropertyTypeMapping<String> RELEASE_YEAR = prop(String.class, "Release year");
	public static SDIPropertyTypeMapping<String> RELEASE_NOTE = prop(String.class, "Release note");
	public static SDIPropertyTypeMapping<String> RELEASE_TYPE = prop(String.class, "Release type");
	public static SDIPropertyTypeMapping<PageRef> REPRESENTED_BY_PAGE = prop(PageRef.class, "Represented by page");
	public static SDIPropertyTypeMapping<String> RULE_SYSTEM = prop(String.class, "Rule system");
	public static SDIPropertyTypeMapping<String> RUNTIME = prop(String.class, "Runtime");
	public static SDIPropertyTypeMapping<String> SERIALIZED = prop(String.class, "Serialized");
	public static SDIPropertyTypeMapping<PageRef> SERIES = prop(PageRef.class, "Series");
	public static SDIPropertyTypeMapping<String> TEXT = prop(String.class, "Text");
	public static SDIPropertyTypeMapping<Integer> TO_PAGE = prop(Integer.class, "To page");
	public static SDIPropertyTypeMapping<String> VIDEO_GAME_TYPE = prop(String.class, "Video game type");
	public static SDIPropertyTypeMapping<String> WEB_ENHANCEMENT = prop(String.class, "Web enhancement");
	public static SDIPropertyTypeMapping<String> WEBSITE = prop(String.class, "Website");
	public static SDIPropertyTypeMapping<PageRef> WRITER = prop(PageRef.class, "Writer");
	//generated
	public static SDIPropertyTypeMapping<PageRef> GALLERY_PAGE = prop(PageRef.class, "Gallery page");
			
			
	private final Class<TYPE> type;
	private final String property;
	
	public static <T> SDIPropertyTypeMapping<T> prop(Class<T> type, String property) {
		return new SDIPropertyTypeMapping<>(type, property);
	}
}
