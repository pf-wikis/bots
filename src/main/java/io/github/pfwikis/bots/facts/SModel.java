package io.github.pfwikis.bots.facts;

import static io.github.pfwikis.bots.facts.SFactsProperties.Accessory_type;
import static io.github.pfwikis.bots.facts.SFactsProperties.Artist;
import static io.github.pfwikis.bots.facts.SFactsProperties.Audio_type;
import static io.github.pfwikis.bots.facts.SFactsProperties.Author;
import static io.github.pfwikis.bots.facts.SFactsProperties.Author_all;
import static io.github.pfwikis.bots.facts.SFactsProperties.Awards;
import static io.github.pfwikis.bots.facts.SFactsProperties.Blurb_heading;
import static io.github.pfwikis.bots.facts.SFactsProperties.Blurb_quotee;
import static io.github.pfwikis.bots.facts.SFactsProperties.Blurb_text;
import static io.github.pfwikis.bots.facts.SFactsProperties.Blurb_text_extras;
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
import static io.github.pfwikis.bots.facts.SFactsProperties.Engine;
import static io.github.pfwikis.bots.facts.SFactsProperties.Errata;
import static io.github.pfwikis.bots.facts.SFactsProperties.Follows;
import static io.github.pfwikis.bots.facts.SFactsProperties.Full_title;
import static io.github.pfwikis.bots.facts.SFactsProperties.Gallery;
import static io.github.pfwikis.bots.facts.SFactsProperties.Genre;
import static io.github.pfwikis.bots.facts.SFactsProperties.Image;
import static io.github.pfwikis.bots.facts.SFactsProperties.Is_subsection;
import static io.github.pfwikis.bots.facts.SFactsProperties.Isbn;
import static io.github.pfwikis.bots.facts.SFactsProperties.Level_range_end;
import static io.github.pfwikis.bots.facts.SFactsProperties.Level_range_start;
import static io.github.pfwikis.bots.facts.SFactsProperties.Location;
import static io.github.pfwikis.bots.facts.SFactsProperties.Map_type;
import static io.github.pfwikis.bots.facts.SFactsProperties.Material;
import static io.github.pfwikis.bots.facts.SFactsProperties.Miniatures_type;
import static io.github.pfwikis.bots.facts.SFactsProperties.Modes;
import static io.github.pfwikis.bots.facts.SFactsProperties.Name;
import static io.github.pfwikis.bots.facts.SFactsProperties.Narrator;
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
import static io.github.pfwikis.bots.facts.SFactsProperties.Release_year;
import static io.github.pfwikis.bots.facts.SFactsProperties.Represented_by_page;
import static io.github.pfwikis.bots.facts.SFactsProperties.Rule_system;
import static io.github.pfwikis.bots.facts.SFactsProperties.Runtime;
import static io.github.pfwikis.bots.facts.SFactsProperties.Serialized;
import static io.github.pfwikis.bots.facts.SFactsProperties.Series;
import static io.github.pfwikis.bots.facts.SFactsProperties.To_page;
import static io.github.pfwikis.bots.facts.SFactsProperties.Video_game_type;
import static io.github.pfwikis.bots.facts.SFactsProperties.Web_enhancement;
import static io.github.pfwikis.bots.facts.SFactsProperties.*;
import static io.github.pfwikis.bots.facts.SFactsProperties.Writer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;

import org.apache.commons.lang3.StringUtils;

import com.beust.jcommander.Strings;

import io.github.pfwikis.bots.common.bots.Run.SingleRun;
import io.github.pfwikis.bots.common.model.subject.PageRef;
import io.github.pfwikis.bots.common.model.subject.SemanticSubject;
import io.github.pfwikis.bots.facts.model.SConcept;
import io.github.pfwikis.bots.facts.model.SInfoboxProperty;
import io.github.pfwikis.bots.facts.model.SPropertyGroup;
import io.github.pfwikis.bots.facts.model.SPropertyGroup.SPropertyGroupBuilder;
import lombok.RequiredArgsConstructor;

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
					Location
				),
			BLURB_FIELDS
		)
		.infoboxProperties(
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
		.conceptSpecificCategoriesFunction(new Helper() {

			@Override
			protected void generateCategories() {
				var bookType = page.getOr(Book_type, "");
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
				addCats(switch(page.getOr(Rule_system, "")) {
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
						page.get(Author_all).stream().map(a->run.getWiki().getDisplayTitle(a.toFullTitle())).map(a->"Comics by "+a).toList()
					);
					case "Short Fiction" -> List.of("Fiction", ifYear("{} fiction"));
					default -> List.of();
				});
				
				
				
				if((game+" Society scenario").equals(bookType)) {
					addCats(game+" Society scenarios");
					series.forEach(s-> {
						if(s.getTitle().startsWith("Season ")) {
							addCats(StringUtils.appendIfMissing(s.getTitle(), " scenarios"));
						}
					});
				}
				if((game+" Society (2E) scenario").equals(bookType)) {
					addCats(game+" Society (2E) scenarios");
					series.forEach(s-> {
						if(s.getTitle().startsWith("Season ")) {
							addCats(StringUtils.appendIfMissing(s.getTitle()," (2E) scenarios"));
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
					if(page.has(Serialized) && !page.get(Serialized).isBlank()) {
						addCats("Serial fiction");
					}
				}
			}
		})
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
	public static final SConcept MAP = SConcept.builder()
			.name("Map")
			.pluralName("Maps")
			.properties(
				BASIC_FIELDS,
				SPropertyGroup.builder()
					.name("Contributors")
					.properties(
						Artist,
						Publisher
					),
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
		.conceptSpecificCategoriesFunction(new Helper() {
			@Override
			protected void generateCategories() {
				addCats(ifYear("{} accessories"));
				addCats("Accessories");
				
				addCats(switch(page.getOr(Map_type, "") ) {
					case "Poster Map Folio" -> "Poster Map Folios"; 
					case "Flip-Mat" -> game+" Flip-Mats"; 
					case "Map tiles" -> game+" Flip-Tiles"; 
					case "Map Pack" -> game+" Map Packs"; 
					default -> List.of();
				});
				
				ifMatchingSeries(game+" Campaign Setting", game+" Campaign Setting");
				page.getOr(Region, Collections.emptyList()).forEach(r->addCats("Images of "+run.getWiki().getDisplayTitle(r.toFullTitle())));
			}
		})
		.build();
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
			.conceptSpecificCategoriesFunction(new Helper() {
				@Override
				protected void generateCategories() {
					addCats(switch(page.getOr(Audio_type, "") ) {
						case "Full-cast Audio Drama" -> List.of("Audio dramas", ifYear("{} audio dramas"));
						case "Soundscape" -> List.of("Soundscapes", ifYear("{} soundscapes"));
						case "Game score", "Soundtrack" -> List.of("Soundtracks", ifYear("{} soundtracks"));
						default -> List.of();
					});
				}
			})
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
			.conceptSpecificCategoriesFunction(new Helper() {
				@Override
				protected void generateCategories() {
					addCats(switch(page.getOr(Video_game_type, "") ) {
						case "Computer RPG" -> "RPG video games";
						default -> "video games";
					});
				}
			})
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
			.conceptSpecificCategoriesFunction(new Helper() {
				@Override
				protected void generateCategories() {
					addCats(switch(page.getOr(Deck_type, "") ) {
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
					
					if("PACG".equals(page.getOr(Rule_system, ""))) {
						 addCats("Pathfinder Adventure Card Game");
					}
					ifMatchingSeries(game+" Cards", game+" Cards");
					ifMatchingSeries(game+" Adventure Card Game", game+" Adventure Card Game");
					ifMatchingSeries(game+" Accessories", game+" Accessories");
				}
			})
		.build();
	
	public static final SConcept WEB_CITATION = SConcept.builder()
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
	public static final SConcept[] CONCEPTS = {
		BOOK,
		ACCESSORY,
		MAP,
		MINIATURES,
		AUDIO,
		VIDEO_GAME,
		DECK,
		WEB_CITATION
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
			if(page.has(Release_year))
				return pattern.replace("{}", page.get(Release_year).toString());
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
			this.series = page.getOr(Series, Collections.emptyList());
			
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
