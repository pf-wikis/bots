package io.github.pfwikis.bots.facts;

import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.ACCESSORY_TYPE;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.ARTIST;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.AUDIO_TYPE;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.AUTHOR;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.AUTHOR_ALL;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.AWARDS;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.BLURB_HEADING;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.BLURB_QUOTEE;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.BLURB_TEXT;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.BLURB_TEXT_EXTRAS;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.BOOK_TYPE;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.CHAPTERS;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.COMPOSER;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.DECKSIZE;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.DECK_TYPE;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.DESCRIPTION;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.DESIGNER;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.DEVELOPER;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.DIMENSIONS;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.DIRECTOR;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.DISCS;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.ENGINE;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.ERRATA;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.FOLLOWS;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.FULL_TITLE;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.GALLERY;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.GENRE;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.IMAGE;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.ISBN;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.IS_SUBSECTION;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.LEVEL_RANGE_END;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.LEVEL_RANGE_START;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.LOCATION;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.MAP_TYPE;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.MATERIAL;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.MINIATURES_TYPE;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.MODES;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.NAME;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.NARRATOR;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.ON_PAGE;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.PAGES;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.PERFORMER;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.PLATFORMS;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.PRECEDES;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.PRICE;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.PRIMARY_AUTHOR;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.PRODUCER;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.PROGRAMMER;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.PUBCODE;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.PUBLISHER;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.QUANTITY;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.REGION;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.RELEASE_DATE;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.RELEASE_NOTE;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.RELEASE_TYPE;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.RELEASE_YEAR;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.REPRESENTED_BY_PAGE;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.RULE_SYSTEM;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.RUNTIME;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.SERIALIZED;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.SERIES;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.TO_PAGE;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.VIDEO_GAME_TYPE;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.WEBSITE;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.WEB_ENHANCEMENT;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.WRITER;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;

import com.beust.jcommander.Strings;

import io.github.pfwikis.bots.common.bots.Run.SingleRun;
import io.github.pfwikis.bots.common.model.SemanticSubject;
import io.github.pfwikis.bots.common.model.SemanticSubject.PageRef;
import io.github.pfwikis.bots.facts.model.SDIRawConcept;
import lombok.RequiredArgsConstructor;

public class SDIModel {

	public static final SDIRawConcept BOOK = SDIRawConcept.builder()
			.name("Book")
			.pluralName("Books")
			.properties(List.of(
				NAME,
				FULL_TITLE,
				REPRESENTED_BY_PAGE,
				BOOK_TYPE,
				IMAGE,
				RULE_SYSTEM,
				PRIMARY_AUTHOR,
				AUTHOR,
				ARTIST,
				NARRATOR,
				PUBLISHER,
				SERIES,
				FOLLOWS,
				PRECEDES,
				WEBSITE,
				PUBCODE,
				SERIALIZED,
				PAGES,
				RUNTIME,
				CHAPTERS,
				GALLERY,
				AWARDS,
				ERRATA,
				WEB_ENHANCEMENT,
				LEVEL_RANGE_START,
				LEVEL_RANGE_END,
				LOCATION,
				BLURB_HEADING,
				BLURB_TEXT,
				BLURB_TEXT_EXTRAS,
				BLURB_QUOTEE
			))
			.infoboxProperties(List.of(
				PRIMARY_AUTHOR,
				PUBLISHER,
				SERIALIZED,
				PAGES,
				NARRATOR,
				RUNTIME,
				RULE_SYSTEM,
				CHAPTERS,
				SERIES,
				FOLLOWS,
				PRECEDES,
				AWARDS,
				ERRATA,
				WEB_ENHANCEMENT
			))
			.subForms(List.of(
				SDIRawConcept.builder()
					.name("Section")
					.pluralName("Sections")
					.properties(List.of(
						NAME,
						IS_SUBSECTION,
						AUTHOR,
						ON_PAGE,
						TO_PAGE,
						DESCRIPTION
					))
				.build(),
				SDIRawConcept.builder()
					.name("Release")
					.pluralName("Releases")
					.properties(List.of(
						RELEASE_TYPE,
						RELEASE_NOTE,
						RELEASE_DATE,
						ISBN,
						PAGES,
						RULE_SYSTEM,
						PRICE
					))
				.build()
			))
		.conceptSpecificCategoriesFunction(new Helper() {

			@Override
			protected void generateCategories() {
				var bookType = page.getOr(BOOK_TYPE, "");
				var isAdventure = false;
				if(Set.of(
						"Adventure",
						"Adventure Path issue",
						game+" Society scenario",
						game+" Society (2E) scenario",
						game+" Bounty",
						game+" Quest",
						game+" Quest (2E)",
						game+" One-Shot",
						game+" Society Adventure Card Guild Adventure"
				).contains(bookType)) {
					addCats("Adventures");
					addCats(ifYear("{} adventures"));
					isAdventure = true;
				}
				
				String categoryWord = isAdventure?"adventures":"sourcebooks";
				addCats(switch(page.getOr(RULE_SYSTEM, "")) {
					case "PFRPG" -> "PFRPG "+categoryWord;
					case "PF2",
						"Pathfinder Playtest"
						->"PFRPG 2E "+categoryWord;
					case "D&D 3.5" -> "D&D 3.5 "+categoryWord;
					case "D&D 5E" -> "D&D 5 "+categoryWord;
					case "PACG" -> "PACG "+categoryWord;
					case "OGL" -> "OGL "+categoryWord;
					case "Savage Worlds" -> "Savage Worlds "+categoryWord;
					case "SFRPG" -> "SFRPG "+categoryWord;
					case "SF2 Playtest" -> "SFRPG 2E "+categoryWord;
					default->List.of();
				});
				
				addCats(switch(bookType) {
					case "Sourcebook" -> List.of("Sourcebooks", ifYear("{} sourcebooks"));
					case "Adventure Path issue" -> game+" Adventure Path";
					case "Adventure Path compilation" -> List.of(game+" Adventure Path", "Adventure compilations");
					case "Pathfinder Quest" -> "Pathfinder Quests";
					case "Starfinder Quest" -> "Starfinder Quests";
					case "Pathfinder Bounty" -> "Pathfinder Bounties";
					case "Starfinder Bounty" -> "Starfinder Bounties";
					case "Pathfinder Quest (2E)" -> "Pathfinder Quests (2E)";
					case "Starfinder Quest (2E)" -> "Starfinder Quests (2E)";
					case "Pathfinder One-Shot" -> "Pathfinder One-Shots";
					case "Starfinder One-Shot" -> "Starfinder One-Shots";
					case "Pathfinder Society Adventure Card Guild Adventure" -> "Pathfinder Society Adventure Card Guild scenarios";
					case "Novel" -> List.of("Novels", "Fiction", ifYear("{} fiction"));
					case "Novella" -> List.of("Novellas", "Fiction", ifYear("{} fiction"));
					case "Periodical" -> List.of("Periodicals", "Fiction", ifYear("{} fiction"), ifYear("{} periodicals"));
					case "Comic book" -> List.of(
						"Comics", ifYear("{} comics"),
						"Fiction", ifYear("{} fiction"),
						series.stream().map(s->s.getTitle()+" comics").toList(),
						page.getAll(AUTHOR_ALL).stream().map(a->run.getWiki().getDisplayTitle(a.toFullTitle())).map(a->"Comics by "+a).toList()
					);
					case "Short Fiction" -> List.of("Fiction", ifYear("{} fiction"));
					default -> List.of();
				});
				
				
				
				if((game+" Society scenario").equals(bookType)) {
					addCats(game+" Society scenarios");
					series.forEach(s-> {
						if(s.getTitle().startsWith("Season ")) {
							addCats(s.getTitle()+" scenarios");
						}
					});
				}
				if((game+" Society (2E) scenario").equals(bookType)) {
					addCats(game+" Society (2E) scenarios");
					series.forEach(s-> {
						if(s.getTitle().startsWith("Season ")) {
							addCats(s.getTitle()+" (2E) scenarios");
						}
					});
				}
				
				
				ifMatchingSeries(game+" Roleplaying Game", game+" RPG");
				ifMatchingSeries(game+" Player Companion", game+" Player Companion");
				ifMatchingSeries(game+" Campaign Setting", game+" Campaign Setting");
				ifMatchingSeries("Lost Omens", game+" Lost Omens");
				ifMatchingSeries(game+" Modules", game+" Modules");
				ifMatchingSeries(game+" Adventure", game+" Adventure");
				ifMatchingSeries(game+" Tales", game+" Tales");
				ifMatchingSeries(game+"'s Journal (series)", game+"'s Journal");
				
				
				if(Set.of("Novel", "Short Fiction", "Novella").contains(bookType)) {
					if(page.has(SERIALIZED) && !page.get(SERIALIZED).isBlank()) {
						addCats("Serial fiction");
					}
				}
			}
		})
		.build();
	public static final SDIRawConcept ACCESSORY = SDIRawConcept.builder()
			.name("Accessory")
			.pluralName("Accessories")
			.properties(List.of(
				NAME,
				FULL_TITLE,
				REPRESENTED_BY_PAGE,
				ACCESSORY_TYPE,
				IMAGE,
				RELEASE_DATE,
				WEBSITE,
				AUTHOR,
				ARTIST,
				PUBLISHER,
				PUBCODE,
				ISBN,
				PRICE,
				QUANTITY,
				MATERIAL,
				RULE_SYSTEM,
				SERIES,
				FOLLOWS,
				PRECEDES,
				AWARDS,
				ERRATA,
				WEB_ENHANCEMENT,
				GALLERY,
				BLURB_HEADING,
				BLURB_TEXT,
				BLURB_TEXT_EXTRAS,
				BLURB_QUOTEE
			))
			.infoboxProperties(List.of(
				AUTHOR,
				ARTIST,
				PUBLISHER,
				PRICE,
				RELEASE_DATE,
				QUANTITY,
				MATERIAL,
				ISBN,
				RULE_SYSTEM,
				SERIES,
				FOLLOWS,
				PRECEDES,
				AWARDS,
				ERRATA,
				WEB_ENHANCEMENT
			))
			.conceptSpecificCategoriesFunction(new Helper() {
				@Override
				protected void generateCategories() {
					addCats("Accessories");
					addCats(ifYear("{} accessories"));
					
					ifMatchingSeries(game+" Accessories", game+" Accessories");
					ifMatchingSeries(game+" Terrain", game+" Terrain");
					ifMatchingSeries(game+" Book Tabs", game+" Book Tabs");
					
				}
			})
		.build();
	public static final SDIRawConcept MAP = SDIRawConcept.builder()
			.name("Map")
			.pluralName("Maps")
			.properties(List.of(
				NAME,
				FULL_TITLE,
				REPRESENTED_BY_PAGE,
				MAP_TYPE,
				WEBSITE,
				PUBCODE,
				IMAGE,
				GALLERY,
				ARTIST,
				PUBLISHER,
				PRICE,
				RELEASE_DATE,
				REGION,
				DIMENSIONS,
				QUANTITY,
				ISBN,
				PAGES,
				RULE_SYSTEM,
				SERIES,
				FOLLOWS,
				PRECEDES,
				AWARDS,
				BLURB_HEADING,
				BLURB_TEXT,
				BLURB_TEXT_EXTRAS,
				BLURB_QUOTEE
			))
			.infoboxProperties(List.of(
				ARTIST,
				PUBLISHER,
				PRICE,
				RELEASE_DATE,
				REGION,
				DIMENSIONS,
				QUANTITY,
				ISBN,
				PAGES,
				RULE_SYSTEM,
				SERIES,
				FOLLOWS,
				PRECEDES,
				AWARDS
			))
		.conceptSpecificCategoriesFunction(new Helper() {
			@Override
			protected void generateCategories() {
				addCats(ifYear("{} accessories"));
				addCats("Accessories");
				
				addCats(switch(page.getOr(MAP_TYPE, "") ) {
					case "Poster Map Folio" -> "Poster Map Folios"; 
					case "Flip-Mat" -> game+" Flip-Mats"; 
					case "Map tiles" -> game+" Flip-Tiles"; 
					case "Map Pack" -> game+" Map Packs"; 
					default -> List.of();
				});
				
				ifMatchingSeries(game+" Campaign Setting", game+" Campaign Setting");
				page.getAll(REGION).forEach(r->addCats("Images of "+run.getWiki().getDisplayTitle(r.toFullTitle())));
			}
		})
		.build();
	public static final SDIRawConcept MINIATURES = SDIRawConcept.builder()
			.name("Miniatures")
			.pluralName("Miniatures")
			.properties(List.of(
				NAME,
				FULL_TITLE,
				REPRESENTED_BY_PAGE,
				MINIATURES_TYPE,
				WEBSITE,
				PUBCODE,
				IMAGE,
				GALLERY,
				AUTHOR,
				PUBLISHER,
				PRICE,
				RELEASE_DATE,
				ISBN,
				QUANTITY,
				SERIES,
				FOLLOWS,
				PRECEDES,
				AWARDS,
				ARTIST,
				BLURB_HEADING,
				BLURB_TEXT,
				BLURB_TEXT_EXTRAS,
				BLURB_QUOTEE
			))
			.infoboxProperties(List.of(
				AUTHOR,
				PUBLISHER,
				PRICE,
				RELEASE_DATE,
				ISBN,
				QUANTITY,
				SERIES,
				FOLLOWS,
				PRECEDES,
				AWARDS
			))
			.conceptSpecificCategoriesFunction(new Helper() {
				@Override
				protected void generateCategories() {
					addCats("Miniatures");
					addCats(ifYear("{} miniatures"));
					ifMatchingSeries(game+" Pawns", game+" Pawns");
					ifMatchingSeries(game+" Battles", game+" Battles");
					ifMatchingSeries(game+" Paper Minis", game+" Paper Minis");
				}
			})
		.build();
	public static final SDIRawConcept AUDIO = SDIRawConcept.builder()
			.name("Audio")
			.pluralName("Audio")
			.properties(List.of(
				NAME,
				FULL_TITLE,
				REPRESENTED_BY_PAGE,
				AUDIO_TYPE,
				WEBSITE,
				PUBCODE,
				IMAGE,
				AUTHOR,
				DIRECTOR,
				PERFORMER,
				PUBLISHER,
				PRICE,
				RELEASE_DATE,
				DISCS,
				RUNTIME,
				ISBN,
				SERIES,
				FOLLOWS,
				PRECEDES,
				LOCATION,
				BLURB_HEADING,
				BLURB_TEXT,
				BLURB_TEXT_EXTRAS,
				BLURB_QUOTEE
			))
			.infoboxProperties(List.of(
				AUTHOR,
				DIRECTOR,
				PERFORMER,
				PUBLISHER,
				PRICE,
				RELEASE_DATE,
				DISCS,
				RUNTIME,
				ISBN,
				SERIES,
				FOLLOWS,
				PRECEDES
			))
			.conceptSpecificCategoriesFunction(new Helper() {
				@Override
				protected void generateCategories() {
					addCats(switch(page.getOr(AUDIO_TYPE, "") ) {
						case "Full-cast Audio Drama" -> List.of("Audio dramas", ifYear("{} audio dramas"));
						case "Soundscape" -> List.of("Soundscapes", ifYear("{} soundscapes"));
						case "Game score", "Soundtrack" -> List.of("Soundtracks", ifYear("{} soundtracks"));
						default -> List.of();
					});
				}
			})
		.build();
	public static final SDIRawConcept VIDEO_GAME = SDIRawConcept.builder()
			.name("Video game")
			.pluralName("Video games")
			.properties(List.of(
				NAME,
				FULL_TITLE,
				REPRESENTED_BY_PAGE,
				VIDEO_GAME_TYPE,
				IMAGE,
				GENRE,
				MODES,
				DEVELOPER,
				PUBLISHER,
				DIRECTOR,
				PRODUCER,
				DESIGNER,
				ARTIST,
				WRITER,
				COMPOSER,
				PROGRAMMER,
				ENGINE,
				PLATFORMS,
				PRICE,
				RELEASE_DATE,
				FOLLOWS,
				PRECEDES,
				LOCATION,
				BLURB_HEADING,
				BLURB_TEXT,
				BLURB_TEXT_EXTRAS,
				BLURB_QUOTEE
			))
			.infoboxProperties(List.of(
				GENRE,
				MODES,
				DEVELOPER,
				PUBLISHER,
				DIRECTOR,
				PRODUCER,
				DESIGNER,
				ARTIST,
				WRITER,
				COMPOSER,
				PROGRAMMER,
				ENGINE,
				PLATFORMS,
				PRICE,
				RELEASE_DATE,
				FOLLOWS,
				PRECEDES
			))
			.conceptSpecificCategoriesFunction(new Helper() {
				@Override
				protected void generateCategories() {
					addCats(switch(page.getOr(VIDEO_GAME_TYPE, "") ) {
						case "Computer RPG" -> "RPG video games";
						default -> "video games";
					});
				}
			})
		.build();
	public static final SDIRawConcept DECK = SDIRawConcept.builder()
			.name("Deck")
			.pluralName("Decks")
			.properties(List.of(
				NAME,
				FULL_TITLE,
				REPRESENTED_BY_PAGE,
				DECK_TYPE,
				WEBSITE,
				PUBCODE,
				IMAGE,
				GALLERY,
				AUTHOR,
				ARTIST,
				PUBLISHER,
				PRICE,
				RELEASE_DATE,
				DECKSIZE,
				ISBN,
				RULE_SYSTEM,
				SERIES,
				FOLLOWS,
				PRECEDES,
				AWARDS,
				ERRATA,
				WEB_ENHANCEMENT,
				BLURB_HEADING,
				BLURB_TEXT,
				BLURB_TEXT_EXTRAS,
				BLURB_QUOTEE
			))
			.infoboxProperties(List.of(
				AUTHOR,
				ARTIST,
				PUBLISHER,
				PRICE,
				RELEASE_DATE,
				DECKSIZE,
				ISBN,
				RULE_SYSTEM,
				SERIES,
				FOLLOWS,
				PRECEDES,
				AWARDS,
				ERRATA,
				WEB_ENHANCEMENT
			))
			.conceptSpecificCategoriesFunction(new Helper() {
				@Override
				protected void generateCategories() {
					addCats(switch(page.getOr(DECK_TYPE, "") ) {
						//Adventure Card Game categories
						case "Base Set" -> List.of("Base sets", "Card games", ifYear("{} card games"));
						case "Adventure Deck" -> List.of("Adventure decks", "Card games", ifYear("{} card games"));
						case "Class Deck" -> List.of("Class decks", "Card games", ifYear("{} card games"));
						case "Character Add-On Deck" -> List.of("Character add-on decks", "Card games", ifYear("{} card games"));
						//cards categories
						case "Item Cards " -> List.of("Item cards", "Accessories", ifYear("{} accessories"));
						case "Face Cards " -> List.of("Face cards", "Accessories", ifYear("{} accessories"));
						case "Spell Cards " -> List.of("Spell cards", "Accessories", ifYear("{} accessories"));
						case "Campaign Cards " -> List.of("Campaign cards", "Accessories", ifYear("{} accessories"));
						case "GameMastery Cards " -> List.of("Accessories", ifYear("{} accessories"));
						case "Cards " -> List.of("Accessories", ifYear("{} accessories"));
						//else
						case "Card game" -> List.of("Card games", ifYear("{} card games"));
						default -> List.of("Decks", ifYear("{} decks"));
					});
					
					if("PACG".equals(page.getOr(RULE_SYSTEM, ""))) {
						 addCats("Pathfinder Adventure Card Game");
					}
					ifMatchingSeries(game+" Cards", game+" Cards");
					ifMatchingSeries(game+" Adventure Card Game", game+" Adventure Card Game");
					ifMatchingSeries(game+" Accessories", game+" Accessories");
				}
			})
		.build();
	
	public static final SDIRawConcept[] CONCEPTS = {
		BOOK,
		ACCESSORY,
		MAP,
		MINIATURES,
		AUDIO,
		VIDEO_GAME,
		DECK
	};
	
	@RequiredArgsConstructor
	private static abstract class Helper implements BiFunction<SingleRun, SemanticSubject, String> {
		
		protected SingleRun run;
		protected SemanticSubject page;
		private List<String> cats;
		protected String game;
		protected List<PageRef> series;
		
		protected abstract void generateCategories();
		
		protected Object ifYear(String pattern) {
			if(page.has(RELEASE_YEAR))
				return pattern.replace("{}", page.get(RELEASE_YEAR).toString());
			return List.of();
		}
		
		protected void ifMatchingSeries(String seriesKey, String cat) {
			if(series.stream().anyMatch(s->s.getTitle().equals(seriesKey))) {
				addCats(cat);
			}
		}
		
		public void addCats(Object obj) {
			if(obj instanceof String str) {
				cats.add(str);
			}
			else if(obj instanceof List<?> l) {
				l.forEach(this::addCats);
			}
			else
				throw new IllegalStateException("Not a cat "+obj);
		}
		
		@Override
		public String apply(SingleRun run, SemanticSubject page) {
			this.run = run;
			this.page = page;
			this.game = run.getServer().getName();
			this.series = page.getAll(SERIES);
			
			this.cats = new ArrayList<String>();
			generateCategories();
			cats = cats.stream().sorted().distinct().toList();
			if(cats.isEmpty()) return "";
			return
				"[[Category:"
				+ Strings.join("]][[Category:", cats)
				+ "]]";
		}
	}
}
