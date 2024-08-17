package io.github.pfwikis.bots.facts;

import static io.github.pfwikis.bots.facts.SFactsProperties.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;

import com.beust.jcommander.Strings;

import io.github.pfwikis.bots.common.bots.Run.SingleRun;
import io.github.pfwikis.bots.common.model.SemanticSubject;
import io.github.pfwikis.bots.common.model.SemanticSubject.PageRef;
import io.github.pfwikis.bots.facts.model.SConcept;
import io.github.pfwikis.bots.facts.model.SInfoboxProperty;
import lombok.RequiredArgsConstructor;

public class SModel {

	public static final SConcept BOOK = SConcept.builder()
		.name("Book")
		.pluralName("Books")
		.properties(
			Name,
			Full_title,
			Represented_by_page,
			Book_type,
			Image,
			Rule_system,
			Primary_author,
			Author,
			Artist,
			Narrator,
			Publisher,
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
			Web_enhancement,
			Level_range_start,
			Level_range_end,
			Location,
			Blurb_heading,
			Blurb_text,
			Blurb_text_extras,
			Blurb_quotee
		)
		.infoboxProperties(
			SInfoboxProperty.Simple.builder()
				.prop(Primary_author)
				.label("Author")
				.fallback(Author)
				.build(),
			Publisher,
			Serialized,
			Pages,
			Narrator,
			Runtime,
			SInfoboxProperty.Simple.builder()
				.prop(Rule_system)
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
					Name,
					Is_subsection,
					Author,
					On_page,
					To_page,
					Description
				),
			SConcept.builder()
				.name("Release")
				.pluralName("Releases")
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
		/*.conceptSpecificCategoriesFunction(new Helper() {

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
						page.getAll(Author_all).stream().map(a->run.getWiki().getDisplayTitle(a.toFullTitle())).map(a->"Comics by "+a).toList()
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
					if(page.has(Serialized) && !page.get(Serialized).isBlank()) {
						addCats("Serial fiction");
					}
				}
			}
		})*/
		.build();
	public static final SConcept ACCESSORY = SConcept.builder()
			.name("Accessory")
			.pluralName("Accessories")
			.properties(
				Name,
				Full_title,
				Represented_by_page,
				Accessory_type,
				Image,
				Release_date,
				Website,
				Author,
				Artist,
				Publisher,
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
				Gallery,
				Blurb_heading,
				Blurb_text,
				Blurb_text_extras,
				Blurb_quotee
			)
			.infoboxProperties(
				Author,
				Artist,
				Publisher,
				Price,
				Release_date,
				Quantity,
				Material,
				Isbn,
				SInfoboxProperty.Simple.builder()
					.prop(Rule_system)
					.label("Rule set")
					.build(),
				Series,
				Follows,
				Precedes,
				Awards,
				Errata,
				Web_enhancement
			)
			/*.conceptSpecificCategoriesFunction(new Helper() {
				@Override
				protected void generateCategories() {
					addCats("Accessories");
					addCats(ifYear("{} accessories"));
					
					ifMatchingSeries(game+" Accessories", game+" Accessories");
					ifMatchingSeries(game+" Terrain", game+" Terrain");
					ifMatchingSeries(game+" Book Tabs", game+" Book Tabs");
					
				}
			})*/
		.build();
	public static final SConcept MAP = SConcept.builder()
			.name("Map")
			.pluralName("Maps")
			.properties(
				Name,
				Full_title,
				Represented_by_page,
				Map_type,
				Website,
				Pubcode,
				Image,
				Gallery,
				Artist,
				Publisher,
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
				Awards,
				Blurb_heading,
				Blurb_text,
				Blurb_text_extras,
				Blurb_quotee
			)
			.infoboxProperties(
				Artist,
				Publisher,
				Price,
				Release_date,
				Region,
				Dimensions,
				Quantity,
				Isbn,
				Pages,
				SInfoboxProperty.Simple.builder()
					.prop(Rule_system)
					.label("Rule set")
					.build(),
				Series,
				Follows,
				Precedes,
				Awards
			)
		/*.conceptSpecificCategoriesFunction(new Helper() {
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
				page.getAll(Region).forEach(r->addCats("Images of "+run.getWiki().getDisplayTitle(r.toFullTitle())));
			}
		})*/
		.build();
	public static final SConcept MINIATURES = SConcept.builder()
			.name("Miniatures")
			.pluralName("Miniatures")
			.properties(
				Name,
				Full_title,
				Represented_by_page,
				Miniatures_type,
				Website,
				Pubcode,
				Image,
				Gallery,
				Author,
				Publisher,
				Price,
				Release_date,
				Isbn,
				Quantity,
				Series,
				Follows,
				Precedes,
				Awards,
				Artist,
				Blurb_heading,
				Blurb_text,
				Blurb_text_extras,
				Blurb_quotee
			)
			.infoboxProperties(
				Author,
				Publisher,
				Price,
				Release_date,
				Isbn,
				Quantity,
				Series,
				Follows,
				Precedes,
				Awards
			)/*
			.conceptSpecificCategoriesFunction(new Helper() {
				@Override
				protected void generateCategories() {
					addCats("Miniatures");
					addCats(ifYear("{} miniatures"));
					ifMatchingSeries(game+" Pawns", game+" Pawns");
					ifMatchingSeries(game+" Battles", game+" Battles");
					ifMatchingSeries(game+" Paper Minis", game+" Paper Minis");
				}
			})*/
		.build();
	public static final SConcept AUDIO = SConcept.builder()
			.name("Audio")
			.pluralName("Audio")
			.properties(
				Name,
				Full_title,
				Represented_by_page,
				Audio_type,
				Website,
				Pubcode,
				Image,
				Author,
				Director,
				Performer,
				Publisher,
				Price,
				Release_date,
				Discs,
				Runtime,
				Isbn,
				Series,
				Follows,
				Precedes,
				Location,
				Blurb_heading,
				Blurb_text,
				Blurb_text_extras,
				Blurb_quotee
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
				Isbn,
				Series,
				Follows,
				Precedes
			)
			/*.conceptSpecificCategoriesFunction(new Helper() {
				@Override
				protected void generateCategories() {
					addCats(switch(page.getOr(Audio_type, "") ) {
						case "Full-cast Audio Drama" -> List.of("Audio dramas", ifYear("{} audio dramas"));
						case "Soundscape" -> List.of("Soundscapes", ifYear("{} soundscapes"));
						case "Game score", "Soundtrack" -> List.of("Soundtracks", ifYear("{} soundtracks"));
						default -> List.of();
					});
				}
			})*/
		.build();
	public static final SConcept VIDEO_GAME = SConcept.builder()
			.name("Video game")
			.pluralName("Video games")
			.properties(
				Name,
				Full_title,
				Represented_by_page,
				Video_game_type,
				Image,
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
				Precedes,
				Location,
				Blurb_heading,
				Blurb_text,
				Blurb_text_extras,
				Blurb_quotee
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
			)/*
			.conceptSpecificCategoriesFunction(new Helper() {
				@Override
				protected void generateCategories() {
					addCats(switch(page.getOr(Video_game_type, "") ) {
						case "Computer RPG" -> "RPG video games";
						default -> "video games";
					});
				}
			})*/
		.build();
	public static final SConcept DECK = SConcept.builder()
			.name("Deck")
			.pluralName("Decks")
			.properties(
				Name,
				Full_title,
				Represented_by_page,
				Deck_type,
				Website,
				Pubcode,
				Image,
				Gallery,
				Author,
				Artist,
				Publisher,
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
				Web_enhancement,
				Blurb_heading,
				Blurb_text,
				Blurb_text_extras,
				Blurb_quotee
			)
			.infoboxProperties(
				Author,
				Artist,
				Publisher,
				Price,
				Release_date,
				Decksize,
				Isbn,
				SInfoboxProperty.Simple.builder()
					.prop(Rule_system)
					.label("Rule set")
					.build(),
				Series,
				Follows,
				Precedes,
				Awards,
				Errata,
				Web_enhancement
			)/*
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
			})*/
		.build();
	
	public static final SConcept[] CONCEPTS = {
		BOOK,
		ACCESSORY,
		MAP,
		MINIATURES,
		AUDIO,
		VIDEO_GAME,
		DECK
	};
	/*
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
			this.series = page.getAll(Series);
			
			this.cats = new ArrayList<String>();
			generateCategories();
			cats = cats.stream().sorted().distinct().toList();
			if(cats.isEmpty()) return "";
			return
				"[[Category:"
				+ Strings.join("]][[Category:", cats)
				+ "]]";
		}
	}*/
}
