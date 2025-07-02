package io.github.pfwikis.bots.facts;

import static io.github.pfwikis.bots.facts.SFactsProperties.Accessory_type;
import static io.github.pfwikis.bots.facts.SFactsProperties.Artist;
import static io.github.pfwikis.bots.facts.SFactsProperties.Audio_type;
import static io.github.pfwikis.bots.facts.SFactsProperties.Author;
import static io.github.pfwikis.bots.facts.SFactsProperties.Awards;
import static io.github.pfwikis.bots.facts.SFactsProperties.Blurb_heading;
import static io.github.pfwikis.bots.facts.SFactsProperties.Blurb_quotee;
import static io.github.pfwikis.bots.facts.SFactsProperties.Blurb_text;
import static io.github.pfwikis.bots.facts.SFactsProperties.Blurb_text_extras;
import static io.github.pfwikis.bots.facts.SFactsProperties.Board_game_type;
import static io.github.pfwikis.bots.facts.SFactsProperties.Book_type;
import static io.github.pfwikis.bots.facts.SFactsProperties.Chapters;
import static io.github.pfwikis.bots.facts.SFactsProperties.Composer;
import static io.github.pfwikis.bots.facts.SFactsProperties.Deck_type;
import static io.github.pfwikis.bots.facts.SFactsProperties.Decksize;
import static io.github.pfwikis.bots.facts.SFactsProperties.Description;
import static io.github.pfwikis.bots.facts.SFactsProperties.Designer;
import static io.github.pfwikis.bots.facts.SFactsProperties.Developer;
import static io.github.pfwikis.bots.facts.SFactsProperties.Dimensions;
import static io.github.pfwikis.bots.facts.SFactsProperties.Director;
import static io.github.pfwikis.bots.facts.SFactsProperties.Discs;
import static io.github.pfwikis.bots.facts.SFactsProperties.Editor;
import static io.github.pfwikis.bots.facts.SFactsProperties.Engine;
import static io.github.pfwikis.bots.facts.SFactsProperties.Errata;
import static io.github.pfwikis.bots.facts.SFactsProperties.Follows;
import static io.github.pfwikis.bots.facts.SFactsProperties.Full_title;
import static io.github.pfwikis.bots.facts.SFactsProperties.Gallery;
import static io.github.pfwikis.bots.facts.SFactsProperties.Genre;
import static io.github.pfwikis.bots.facts.SFactsProperties.Golarion_date;
import static io.github.pfwikis.bots.facts.SFactsProperties.Golarion_end_date;
import static io.github.pfwikis.bots.facts.SFactsProperties.Grid;
import static io.github.pfwikis.bots.facts.SFactsProperties.Image;
import static io.github.pfwikis.bots.facts.SFactsProperties.Includes_work;
import static io.github.pfwikis.bots.facts.SFactsProperties.Is_subsection;
import static io.github.pfwikis.bots.facts.SFactsProperties.Isbn;
import static io.github.pfwikis.bots.facts.SFactsProperties.Level_range_end;
import static io.github.pfwikis.bots.facts.SFactsProperties.Level_range_start;
import static io.github.pfwikis.bots.facts.SFactsProperties.Location;
import static io.github.pfwikis.bots.facts.SFactsProperties.Main_book;
import static io.github.pfwikis.bots.facts.SFactsProperties.Map_type;
import static io.github.pfwikis.bots.facts.SFactsProperties.Material;
import static io.github.pfwikis.bots.facts.SFactsProperties.Member_category;
import static io.github.pfwikis.bots.facts.SFactsProperties.Miniatures_type;
import static io.github.pfwikis.bots.facts.SFactsProperties.Modes;
import static io.github.pfwikis.bots.facts.SFactsProperties.Name;
import static io.github.pfwikis.bots.facts.SFactsProperties.Narrator;
import static io.github.pfwikis.bots.facts.SFactsProperties.Number_of_players;
import static io.github.pfwikis.bots.facts.SFactsProperties.On_page;
import static io.github.pfwikis.bots.facts.SFactsProperties.Pages;
import static io.github.pfwikis.bots.facts.SFactsProperties.Performer;
import static io.github.pfwikis.bots.facts.SFactsProperties.Platforms;
import static io.github.pfwikis.bots.facts.SFactsProperties.Precedes;
import static io.github.pfwikis.bots.facts.SFactsProperties.Price;
import static io.github.pfwikis.bots.facts.SFactsProperties.Primary_author;
import static io.github.pfwikis.bots.facts.SFactsProperties.Producer;
import static io.github.pfwikis.bots.facts.SFactsProperties.Programmer;
import static io.github.pfwikis.bots.facts.SFactsProperties.Pubcode;
import static io.github.pfwikis.bots.facts.SFactsProperties.Publisher;
import static io.github.pfwikis.bots.facts.SFactsProperties.Quantity;
import static io.github.pfwikis.bots.facts.SFactsProperties.Region;
import static io.github.pfwikis.bots.facts.SFactsProperties.Release_date;
import static io.github.pfwikis.bots.facts.SFactsProperties.Release_note;
import static io.github.pfwikis.bots.facts.SFactsProperties.Release_type;
import static io.github.pfwikis.bots.facts.SFactsProperties.Represented_by_page;
import static io.github.pfwikis.bots.facts.SFactsProperties.Rule_system;
import static io.github.pfwikis.bots.facts.SFactsProperties.Runtime;
import static io.github.pfwikis.bots.facts.SFactsProperties.Serialized;
import static io.github.pfwikis.bots.facts.SFactsProperties.Series;
import static io.github.pfwikis.bots.facts.SFactsProperties.Sanctioned;
import static io.github.pfwikis.bots.facts.SFactsProperties.To_page;
import static io.github.pfwikis.bots.facts.SFactsProperties.Video_game_type;
import static io.github.pfwikis.bots.facts.SFactsProperties.Web_enhancement;
import static io.github.pfwikis.bots.facts.SFactsProperties.Website;
import static io.github.pfwikis.bots.facts.SFactsProperties.Website_name;
import static io.github.pfwikis.bots.facts.SFactsProperties.Writer;

import java.util.EnumMap;
import java.util.List;

import com.google.common.collect.Lists;

import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.facts.model.SConcept;
import io.github.pfwikis.bots.facts.model.SInfoboxProperty;
import io.github.pfwikis.bots.facts.model.SPropertyGroup;
import io.github.pfwikis.bots.facts.model.SPropertyGroup.SPropertyGroupBuilder;

public class SModel {
	
	private final static SPropertyGroupBuilder BLURB_FIELDS = SPropertyGroup.builder()
			.name("Blurb")
			.properties(		
				Blurb_heading,
				Blurb_text,
				Blurb_text_extras,
				Blurb_quotee
			);
	
	private static final SPropertyGroupBuilder BASIC_FIELDS = SPropertyGroup.builder()
			.name("Basic")
			.properties(
				Name,
				Full_title,
				Represented_by_page,
				Image
			);

	public static final SConcept BOOK = SConcept.builder()
		.name("Book")
		.pluralName("Books")
		.properties(
			BASIC_FIELDS,
			SPropertyGroup.builder()
				.name("Contributors")
				.properties(
					Editor,
					Primary_author,
					Author,
					Artist,
					Narrator,
					Publisher
				),
			SPropertyGroup.builder()
				.name("Book")
				.properties(
					Book_type,
					Rule_system,
					Series,
					Follows,
					Precedes,
					Website,
					Pubcode,
					Serialized,
					Pages,
					Runtime,
					Chapters,
					Includes_work,
					Gallery,
					Awards,
					Errata,
					Web_enhancement
				),
			SPropertyGroup.builder()
				.name("Adventure")
				.properties(
					Level_range_start,
					Level_range_end,
					Location,
					Sanctioned
				),
			SPropertyGroup.builder()
				.name("Fiction")
				.properties(
					Golarion_date,
					Golarion_end_date
				),
			BLURB_FIELDS
		)
		.infoboxProperties(
			Editor,
			SInfoboxProperty.from(Primary_author)
				.label("Author")
				.fallback(Author)
				.build(),
			Publisher,
			Serialized,
			Pages,
			Narrator,
			Runtime,
			SInfoboxProperty.from(Rule_system)
				.label("Rule set")
				.build(),
			Chapters,
			SInfoboxProperty.from(Includes_work)
				.label("Includes")
				.build(),
			Series,
			Follows,
			Precedes,
			Awards,
			Errata,
			Web_enhancement
		)
		.subConcepts(
			SConcept.builder()
				.name("Section")
				.pluralName("Sections")
				.properties(
					SPropertyGroup.builder()
						.name("")
						.properties(
							Name,
							Is_subsection,
							Author,
							On_page,
							To_page,
							Description
						)
				),
			SConcept.builder()
				.name("Release")
				.pluralName("Releases")
				.properties(
					SPropertyGroup.builder()
						.name("")
						.properties(
							Release_type,
							Release_note,
							Release_date,
							Isbn,
							Pages,
							Rule_system,
							Price
						)
				)
				.infoboxProperties(
					SInfoboxProperty.from(Release_date)
						.label("Date")
						.build(),
					SInfoboxProperty.from(Isbn)
						.label("ISBN")
						.build(),
					Pages,
					SInfoboxProperty.from(Rule_system)
						.label("Rule set")
						.build(),
					Price
				)
		)
		.build();
	public static final SConcept ACCESSORY = SConcept.builder()
			.name("Accessory")
			.pluralName("Accessories")
			.properties(
				BASIC_FIELDS,
				SPropertyGroup.builder()
					.name("Contributors")
					.properties(
						Author,
						Artist,
						Publisher
					),
				SPropertyGroup.builder()
					.name("Accessory")
					.properties(
						Accessory_type,
						Release_date,
						Website,
						Pubcode,
						Isbn,
						Price,
						Quantity,
						Material,
						Rule_system,
						Series,
						Follows,
						Precedes,
						Awards,
						Errata,
						Web_enhancement,
						Gallery
					),
				BLURB_FIELDS
			)
			.infoboxProperties(
				Author,
				Artist,
				Publisher,
				Price,
				Release_date,
				Quantity,
				Material,
				SInfoboxProperty.from(Isbn)
					.label("ISBN")
					.build(),
				SInfoboxProperty.from(Rule_system)
					.label("Rule set")
					.build(),
				Series,
				Follows,
				Precedes,
				Awards,
				Errata,
				Web_enhancement
			)
		.build();
	public static final SConcept MAP_SF;
	public static final SConcept MAP_PF;
	static {
		var contributors = SPropertyGroup.builder()
			.name("Contributors")
			.properties(
				Artist,
				Publisher
			);
		var base = SConcept.builder()
			.name("Map")
			.pluralName("Maps");
		MAP_PF = base.properties(
				BASIC_FIELDS,
				contributors,
				SPropertyGroup.builder()
					.name("Map")
					.properties(
						Map_type,
						Website,
						Pubcode,
						Gallery,
						Price,
						Release_date,
						Region,
						Dimensions,
						Quantity,
						Isbn,
						Pages,
						Rule_system,
						Series,
						Follows,
						Precedes,
						Awards
					),
				BLURB_FIELDS
			)
			.infoboxProperties(
				Artist,
				Publisher,
				Price,
				Release_date,
				Region,
				Dimensions,
				Quantity,
				SInfoboxProperty.from(Isbn)
					.label("ISBN")
					.build(),
				Pages,
				SInfoboxProperty.from(Rule_system)
					.label("Rule set")
					.build(),
				Series,
				Follows,
				Precedes,
				Awards
			)
			.build();
		MAP_SF = base.properties(
				BASIC_FIELDS,
				contributors,
				SPropertyGroup.builder()
					.name("Map")
					.properties(
						Map_type,
						Website,
						Pubcode,
						Gallery,
						Price,
						Release_date,
						Region,
						Dimensions,
						Grid,
						Quantity,
						Isbn,
						Pages,
						Rule_system,
						Series,
						Follows,
						Precedes,
						Awards
					),
				BLURB_FIELDS
			)
			.infoboxProperties(
				Artist,
				Publisher,
				Price,
				Release_date,
				Region,
				Dimensions,
				Grid,
				Quantity,
				SInfoboxProperty.from(Isbn)
					.label("ISBN")
					.build(),
				Pages,
				SInfoboxProperty.from(Rule_system)
					.label("Rule set")
					.build(),
				Series,
				Follows,
				Precedes,
				Awards
			)
			.build();
	}
	public static final SConcept MINIATURES = SConcept.builder()
			.name("Miniatures")
			.pluralName("Miniatures")
			.properties(
				BASIC_FIELDS,
				SPropertyGroup.builder()
					.name("Contributors")
					.properties(
						Artist,
						Author,
						Publisher
					),
				SPropertyGroup.builder()
					.name("Miniatures")
					.properties(
						Miniatures_type,
						Website,
						Pubcode,
						Gallery,
						Price,
						Release_date,
						Isbn,
						Quantity,
						Series,
						Follows,
						Precedes,
						Awards
					),
				BLURB_FIELDS
			)
			.infoboxProperties(
				Author,
				Publisher,
				Price,
				Release_date,
				SInfoboxProperty.from(Isbn)
					.label("ISBN")
					.build(),
				Quantity,
				Series,
				Follows,
				Precedes,
				Awards
			)
		.build();
	public static final SConcept AUDIO = SConcept.builder()
			.name("Audio")
			.pluralName("Audio")
			.properties(
				BASIC_FIELDS,
				SPropertyGroup.builder()
					.name("Contributors")
					.properties(
						Author,
						Director,
						Performer,
						Publisher
					),
				SPropertyGroup.builder()
					.name("Audio")
					.properties(
						Audio_type,
						Website,
						Pubcode,
						Price,
						Release_date,
						Discs,
						Runtime,
						Isbn,
						Series,
						Follows,
						Precedes,
						Location
					),
				BLURB_FIELDS
			)
			.infoboxProperties(
				Author,
				Director,
				Performer,
				Publisher,
				Price,
				Release_date,
				Discs,
				Runtime,
				SInfoboxProperty.from(Isbn)
					.label("ISBN")
					.build(),
				Series,
				Follows,
				Precedes
			)
		.build();
	public static final SConcept VIDEO_GAME = SConcept.builder()
			.name("Video game")
			.pluralName("Video games")
			.properties(
				BASIC_FIELDS,
				SPropertyGroup.builder()
					.name("Contributors")
					.properties(
						Developer,
						Director,
						Producer,
						Designer,
						Artist,
						Writer,
						Composer,
						Programmer,
						Publisher
					),
				SPropertyGroup.builder()
					.name("Video game")
					.properties(
						Video_game_type,
						Genre,
						Modes,
						
						Engine,
						Platforms,
						Price,
						Release_date,
						Follows,
						Precedes,
						Location
					),
				BLURB_FIELDS
			)
			.infoboxProperties(
				Genre,
				Modes,
				Developer,
				Publisher,
				Director,
				Producer,
				Designer,
				Artist,
				Writer,
				Composer,
				Programmer,
				Engine,
				Platforms,
				Price,
				Release_date,
				Follows,
				Precedes
			)
		.build();
	public static final SConcept DECK = SConcept.builder()
			.name("Deck")
			.pluralName("Decks")
			.properties(
				BASIC_FIELDS,
				SPropertyGroup.builder()
				.name("Contributors")
				.properties(
					Author,
					Artist,
					Publisher
				),
				SPropertyGroup.builder()
				.name("Deck")
				.properties(
					Deck_type,
					Website,
					Pubcode,
					Gallery,
					Price,
					Release_date,
					Decksize,
					Isbn,
					Rule_system,
					Series,
					Follows,
					Precedes,
					Awards,
					Errata,
					Web_enhancement
				),
				BLURB_FIELDS
			)
			.infoboxProperties(
				Author,
				Artist,
				Publisher,
				Price,
				Release_date,
				Decksize,
				SInfoboxProperty.from(Isbn)
					.label("ISBN")
					.build(),
				SInfoboxProperty.from(Rule_system)
					.label("Rule set")
					.build(),
				Series,
				Follows,
				Precedes,
				Awards,
				Errata,
				Web_enhancement
			)
		.build();
	
	public static final SConcept BOARD_GAME = SConcept.builder()
		.name("Board game")
		.pluralName("Board games")
		.properties(
			BASIC_FIELDS,
			SPropertyGroup.builder()
			.name("Contributors")
			.properties(
				Designer,
				Artist,
				Publisher
			),
			SPropertyGroup.builder()
			.name("Boardgame")
			.properties(
				Board_game_type,
				Number_of_players,
				Website,
				Pubcode,
				Gallery,
				Price,
				Release_date,
				Isbn,
				Series,
				Follows,
				Precedes,
				Awards,
				Errata,
				Web_enhancement
			),
			BLURB_FIELDS
		)
		.infoboxProperties(
			Designer,
			Artist,
			Publisher,
			Number_of_players,
			Price,
			Release_date,
			SInfoboxProperty.from(Isbn)
				.label("ISBN")
				.build(),
			Series,
			Follows,
			Precedes,
			Awards,
			Errata,
			Web_enhancement
		)
		.build();
	
	private static final SConcept WEB_CITATION = SConcept.builder()
		.name("Web citation")
		.pluralName("Web citation")
		.properties(
			SPropertyGroup.builder()
				.name("")
				.properties(
					Name,
					Full_title,
					Author,
					Website,
					Website_name,
					Release_date
				)
		)
		.build();
	
	private static final SConcept ADVENTURE_PATH = SConcept.builder()
		.name("Adventure path")
		.pluralName("Adventure paths")
		.properties(
			BASIC_FIELDS,
			SPropertyGroup.builder()
				.name("Adventure Path")
				.properties(
					Main_book,
					Sanctioned
				),
			BLURB_FIELDS
		)
		.build();

	private static final SConcept SERIES = SConcept.builder()
		.name("Series")
		.pluralName("Series")
		.properties(
			BASIC_FIELDS,
			SPropertyGroup.builder()
				.name("Series")
				.properties(
					Member_category
				)
		)
		.build();
	
	private static final EnumMap<Wiki, List<SConcept>> CONCEPTS;
	static {
		CONCEPTS = new EnumMap<>(Wiki.class);
		CONCEPTS.put(Wiki.PF, Lists.newArrayList(
			BOOK,
			ACCESSORY,
			MAP_PF,
			MINIATURES,
			AUDIO,
			VIDEO_GAME,
			DECK,
			WEB_CITATION,
			ADVENTURE_PATH,
			SERIES,
			BOARD_GAME
		));
		CONCEPTS.put(Wiki.SF, Lists.newArrayList(
			BOOK,
			ACCESSORY,
			MAP_SF,
			MINIATURES,
			AUDIO,
			VIDEO_GAME,
			DECK,
			WEB_CITATION,
			ADVENTURE_PATH,
			SERIES,
			BOARD_GAME
		));
	}
	
	public static List<SConcept> getConcepts(Wiki server) {
		return CONCEPTS.get(server);
	}
}
