package io.github.pfwikis.bots.facts;
import java.time.temporal.Temporal;
import java.util.List;

import io.github.pfwikis.bots.common.model.SemanticSubject.PageRef;
import io.github.pfwikis.bots.facts.model.SConcept;
import io.github.pfwikis.bots.facts.model.SFactTypes;
import io.github.pfwikis.bots.facts.model.SProperty;

public class SFactsProperties {
	public static final SProperty<String> Accessory_type = new SProperty<>(
		"Accessory type",
		SFactTypes.STRING)
		.setDescription("The type of accessory.");
	public static final SProperty<List<PageRef>> Artist = new SProperty<>(
		"Artist",
		SFactTypes.PAGE_LIST)
		.setSuggestValuesFrom("Category:Artists")
		.setDescription("An artist that worked on this book or product.");
	public static final SProperty<String> Audio_type = new SProperty<>(
		"Audio type",
		SFactTypes.STRING)
		.setDescription("The type of audio product.");
	public static final SProperty<List<PageRef>> Author_all = new SProperty<>(
		"Author all",
		SFactTypes.PAGE_LIST)
		.setGenerateWikitext("{{{Author|}}};{{{Primary author|}}}")
		.setDescription("This entity is automatically filled with all authors, section authors and primary authors.");
	public static final SProperty<List<PageRef>> Author = new SProperty<>(
		"Author",
		SFactTypes.PAGE_LIST_ORDERED)
		.setSuggestValuesFrom("Category:Authors")
		.setDescription("This entity was written by the given Person. This property can appear multiple times to indicate multiple authors.");
	public static final SProperty<String> Awards = new SProperty<>(
		"Awards",
		SFactTypes.STRING)
		.setAutocompleteDisabled(true)
		.setDescription("Any awards this product has won.");
	public static final SProperty<String> Blurb = new SProperty<>(
		"Blurb",
		SFactTypes.MULTILINE_WIKITEXT)
		.setAutocompleteDisabled(true)
		.setDescription("A short description of this adventure, book or other product.");
	public static final SProperty<String> Blurb_heading = new SProperty<>(
		"Blurb heading",
		SFactTypes.STRING)
		.setAutocompleteDisabled(true)
		.setDescription("A heading for a text.");
	public static final SProperty<String> Blurb_quotee = new SProperty<>(
		"Blurb quotee",
		SFactTypes.STRING)
		.setAutocompleteDisabled(true)
		.setDescription("The source of a quote.");
	public static final SProperty<String> Blurb_text = new SProperty<>(
		"Blurb text",
		SFactTypes.MULTILINE_WIKITEXT)
		.setAutocompleteDisabled(true)
		.setFormNote("This value might be used in multiple locations and should be concise. Consider <code>Blurb text extras</code> for additional content.")
		.setDescription("A text describing the entity in detail.");
	public static final SProperty<String> Blurb_text_extras = new SProperty<>(
		"Blurb text extras",
		SFactTypes.MULTILINE_WIKITEXT)
		.setAutocompleteDisabled(true)
		.setFormNote("This value might be shown after <code>Blurb Text</code>, depending on the way the page uses these fields.")
		.setDescription("More details that can be shown together with <code>Blurb text</code> if there is enough space.");
	public static final SProperty<String> Book_type = new SProperty<>(
		"Book type",
		SFactTypes.STRING)
		.setDescription("The type of book.");
	public static final SProperty<String> Chapters = new SProperty<>(
		"Chapters",
		SFactTypes.STRING)
		.setDescription("The number of chapters.");
	public static final SProperty<List<PageRef>> Composer = new SProperty<>(
		"Composer",
		SFactTypes.PAGE_LIST)
		.setSuggestValuesFrom("Category:Real-world people")
		.setDescription("The composer who worked on this product.");
	public static final SProperty<PageRef> Created_from = new SProperty<>(
		"Created from",
		SFactTypes.PAGE)
		.setDescription("Marks a relation between a generated page and its source. This implies, that if the source is deleted this page should be deleted as well.");
	public static final SProperty<String> Debug = new SProperty<>(
		"Debug",
		SFactTypes.STRING)
		.setDescription("This property is meant to be used as a debugging helper and should only store valures temporarily.");
	public static final SProperty<String> Deck_type = new SProperty<>(
		"Deck type",
		SFactTypes.STRING)
		.setDescription("The type of deck in this product.");
	public static final SProperty<String> Decksize = new SProperty<>(
		"Decksize",
		SFactTypes.STRING)
		.setDescription("The number of cards in this deck.");
	public static final SProperty<String> Description = new SProperty<>(
		"Description",
		SFactTypes.STRING)
		.setDescription("A wikitext description of the entity.");
	public static final SProperty<List<PageRef>> Designer = new SProperty<>(
		"Designer",
		SFactTypes.PAGE_LIST)
		.setSuggestValuesFrom("Category:Real-world people")
		.setDescription("The designer working on this product.");
	public static final SProperty<List<PageRef>> Developer = new SProperty<>(
		"Developer",
		SFactTypes.PAGE_LIST)
		.setSuggestValuesFrom("Category:Real-world people")
		.setDescription("The developer who worked on this product.");
	public static final SProperty<String> Dimensions = new SProperty<>(
		"Dimensions",
		SFactTypes.STRING)
		.setDescription("How big the product is.");
	public static final SProperty<List<PageRef>> Director = new SProperty<>(
		"Director",
		SFactTypes.PAGE_LIST)
		.setSuggestValuesFrom("Category:Real-world people")
		.setDescription("The directory working on this product.");
	public static final SProperty<String> Discs = new SProperty<>(
		"Discs",
		SFactTypes.STRING)
		.setDescription("The number discs in this audio product.");
	public static final SProperty<String> Engine = new SProperty<>(
		"Engine",
		SFactTypes.STRING)
		.setDescription("The engine used in this video game.");
	public static final SProperty<String> Errata = new SProperty<>(
		"Errata",
		SFactTypes.STRING)
		.setAutocompleteDisabled(true)
		.setDescription("A link to all the erratas related to this book.");
	public static final SProperty<PageRef> Fact_type = new SProperty<>(
		"Fact type",
		SFactTypes.PAGE)
		.setGenerateWikitext("unknown")
		.setDescription("The type of fact represented by this entity. This should be a reference to the template that created it.");
	public static final SProperty<List<PageRef>> Follows = new SProperty<>(
		"Follows",
		SFactTypes.PAGE_LIST)
		.setSuggestValuesFrom("Namespace:Main")
		.setDescription("The previous release.");
	public static final SProperty<String> Full_title = new SProperty<>(
		"Full title",
		SFactTypes.STRING)
		.setAutocompleteDisabled(true)
		.setFormNote("Fill this only, if the commonly used title of this product is a shorter form of the full title.")
		.setDescription("The full book title. This should only be used in addition to name if the book has a long name that is not typically used in its full form.");
	public static final SProperty<PageRef> Gallery_page = new SProperty<>(
		"Gallery page",
		SFactTypes.PAGE)
		.setGenerateWikitext("{{#if:{{{Gallery|}}}|Category:Artwork from {{{Gallery}}}|Category:Artwork from {{PAGENAME}}}}")
		.setDescription("The category page of the gallery of images belonging to this entity.");
	public static final SProperty<String> Gallery = new SProperty<>(
		"Gallery",
		SFactTypes.STRING) {
			public List<SProperty<?>> generateProperties(SConcept c) {
				return List.of(Gallery_page);
			};
		}
		.setAutocompleteDisabled(true)
		.setDescription("If the gallery has a different category name than \"Artwork from PAGENAME\".");
	public static final SProperty<String> Genre = new SProperty<>(
		"Genre",
		SFactTypes.STRING)
		.setDescription("The genre of this product.");
	public static final SProperty<PageRef> Image = new SProperty<>(
		"Image",
		SFactTypes.IMAGE)
		.setDescription("The page of an image representing this entity.");
	public static final SProperty<String> Isbn = new SProperty<>(
		"Isbn",
		SFactTypes.STRING)
		.setDescription("The ISBN.")
		.setAllowsPattern("^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$");
	public static final SProperty<String> Level_range_end = new SProperty<>(
		"Level range end",
		SFactTypes.STRING)
		.setDescription("This adventure should end with the characters at this level '''or''' this society adventure can be played in a range ending with this level.");
	public static final SProperty<String> Level_range_start = new SProperty<>(
		"Level range start",
		SFactTypes.STRING)
		.setDescription("This adventure is supposed to be started at this level '''or''' this society adventure can be played in a range starting with this level.");
	public static final SProperty<List<PageRef>> Location = new SProperty<>(
		"Location",
		SFactTypes.PAGE_LIST)
		.setDescription("This adventures features the location in a meaningful way.");
	public static final SProperty<String> Map_type = new SProperty<>(
		"Map type",
		SFactTypes.STRING)
		.setDescription("The type of map.");
	public static final SProperty<String> Material = new SProperty<>(
		"Material",
		SFactTypes.STRING)
		.setDescription("The material this product is made out of.");
	public static final SProperty<String> Miniatures_type = new SProperty<>(
		"Miniatures type",
		SFactTypes.STRING)
		.setDescription("The type of miniatures in this product.");
	public static final SProperty<String> Modes = new SProperty<>(
		"Modes",
		SFactTypes.STRING)
		.setDescription("The video game modes.");
	public static final SProperty<String> Name = new SProperty<>(
		"Name",
		SFactTypes.STRING)
		.setAutocompleteDisabled(true)
		.setDescription("The general name of an entity. This is used for many different types.");
	public static final SProperty<List<PageRef>> Narrator = new SProperty<>(
		"Narrator",
		SFactTypes.PAGE_LIST)
		.setSuggestValuesFrom("Category:Real-world people")
		.setDescription("A narrator that worked on this book or product.");
	public static final SProperty<String> On_page = new SProperty<>(
		"On page",
		SFactTypes.STRING)
		.setDescription("The page this entity can be found on in the respective book. If the entitity is on multiple pages, this should indicate the starting page. Other allowed values are \"Inside Back Cover\", \"Inside Front Cover\", and \"Inside Covers\".")
		.setAllowsPattern("^(\\d+|Inside Back Cover|Inside Front Cover|Inside Covers)$");
	public static final SProperty<Integer> Order = new SProperty<>(
		"Order",
		SFactTypes.INTEGER)
		.setDescription("Stores a number that is used to establish some kind of order between multiple entries.");
	public static final SProperty<String> Pages = new SProperty<>(
		"Pages",
		SFactTypes.STRING)
		.setDescription("The number of pages in this book.");
	public static final SProperty<List<PageRef>> Performer = new SProperty<>(
		"Performer",
		SFactTypes.PAGE_LIST)
		.setSuggestValuesFrom("Category:Actors (real world)")
		.setDescription("The performer who worked on this product.");
	public static final SProperty<String> Platforms = new SProperty<>(
		"Platforms",
		SFactTypes.STRING)
		.setDescription("The platforms that are supported for this product.");
	public static final SProperty<List<PageRef>> Precedes = new SProperty<>(
		"Precedes",
		SFactTypes.PAGE_LIST)
		.setSuggestValuesFrom("Namespace:Main")
		.setDescription("The next release.");
	public static final SProperty<String> Price = new SProperty<>(
		"Price",
		SFactTypes.STRING)
		.setFormNote("The purchase price of the product in the form of \"$4.99\".")
		.setDescription("The price of this release.")
		.setAllowsPattern("^(Free|[$€]\\d+(.\\d+)?)$");
	public static final SProperty<List<PageRef>> Primary_author = new SProperty<>(
		"Primary author",
		SFactTypes.PAGE_LIST_ORDERED) {
			public List<SProperty<?>> generateProperties(SConcept c) {
				return List.of(Author_all);
			};
		}
		.setSuggestValuesFrom("Category:Authors")
		.setDescription("This entity was written by the given Person. This property can appear multiple times to indicate multiple authors. Compared to the Author property this should be credited on the cover page.");
	public static final SProperty<List<PageRef>> Producer = new SProperty<>(
		"Producer",
		SFactTypes.PAGE_LIST)
		.setSuggestValuesFrom("Category:Real-world people")
		.setDescription("The producer working on this product.");
	public static final SProperty<List<PageRef>> Programmer = new SProperty<>(
		"Programmer",
		SFactTypes.PAGE_LIST)
		.setSuggestValuesFrom("Category:Real-world people")
		.setDescription("The programmer working on this product.");
	public static final SProperty<String> Pubcode = new SProperty<>(
		"Pubcode",
		SFactTypes.STRING)
		.setAutocompleteDisabled(true)
		.setFormNote("The publisher's product code for this release (e.g. PZO9500, etc.).")
		.setDescription("Typically a Paizo pubcode.");
	public static final SProperty<List<PageRef>> Publisher = new SProperty<>(
		"Publisher",
		SFactTypes.PAGE_LIST)
		.setFormNote("Typically Paizo Inc.")
		.setSuggestValuesFrom("Category:Publishers")
		.setDescription("The publisher of this book or product.");
	public static final SProperty<String> Quantity = new SProperty<>(
		"Quantity",
		SFactTypes.STRING)
		.setDescription("The number of elements included in the product.");
	public static final SProperty<List<PageRef>> Region = new SProperty<>(
		"Region",
		SFactTypes.PAGE_LIST)
		.setSuggestValuesFrom("Category:Locations")
		.setDescription("The region shown on this map.");
	public static final SProperty<String> Release_date_precision = new SProperty<>(
		"Release date precision",
		SFactTypes.STRING)
		.setGenerateWikitext("{{#if:{{{Release year|}}}|"
			+"{{#rmatch:{{{Release year|}}}|^\\d{4}-\\d{1,2}-\\d{1,2}$|date|"
				+ "{{#rmatch:{{{Release year|}}}|^\\d{4}-\\d{1,2}$|month|"
					+ "{{#rmatch:{{{Release year|}}}|^\\d{4}$|year|unknown}}"
				+ "}}"
			+ "}}"
		+ "|empty}}")
		.setDescription("Automatically generated property that says how precise a given date was.");
	public static final SProperty<String> Release_year = new SProperty<>(
		"Release year",
		SFactTypes.STRING)
		.setGenerateWikitext("unknown")
		.setDefaultValue("unknown")
		.setDescription("The year in which this product was released. This is typically automatically calculated.");
	public static final SProperty<Temporal> Release_date = new SProperty<>(
		"Release date",
		SFactTypes.DATE) {
			public List<SProperty<?>> generateProperties(SConcept c) {
				return List.of(Release_date_precision, Release_year);
			};
		}
		.setDescription("The release date or a partial release date.");
	public static final SProperty<String> Release_note = new SProperty<>(
		"Release note",
		SFactTypes.STRING)
		.setDescription("A short note to differentiate this release from a similar one. Currently used for Society versions.");
	public static final SProperty<String> Release_type = new SProperty<>(
		"Release type",
		SFactTypes.STRING)
		.setDescription("What differentiates this release from others. E.g. PDF, Hardover or Special Edition.");
	public static final SProperty<PageRef> Represented_by_page = new SProperty<>(
		"Represented by page",
		SFactTypes.PAGE)
		.setFormNote("Only fill this if it is different from the Main page of the same name.")
		.setSuggestValuesFrom("Namespace:Main")
		.setDefaultValue("{{#ifexist:{{ROOTPAGENAME}}|{{ROOTPAGENAME}}}}")
		.setDescription("This entity is best represented by this wikipage.");
	public static final SProperty<String> Rule_system = new SProperty<>(
		"Rule system",
		SFactTypes.STRING)
		.setSuggestValuesFrom("Property:Rule system")
		.setDescription("Which RPG system is used in this product.");
	public static final SProperty<String> Runtime = new SProperty<>(
		"Runtime",
		SFactTypes.STRING)
		.setDescription("The runtime given as a number and a unit.");
	public static final SProperty<String> Serialized = new SProperty<>(
		"Serialized",
		SFactTypes.STRING)
		.setDescription("If the contents were serialized, note the month and year of both the start and end of serialization.");
	public static final SProperty<List<PageRef>> Series = new SProperty<>(
		"Series",
		SFactTypes.PAGE_LIST)
		.setSuggestValuesFrom("Namespace:Main")
		.setDescription("The series this book belongs to.");
	public static final SProperty<String> Text = new SProperty<>(
		"Text",
		SFactTypes.MULTILINE_WIKITEXT)
		.setAutocompleteDisabled(true)
		.setDescription("A text describing the entity in detail.");
	public static final SProperty<String> To_page = new SProperty<>(
		"To page",
		SFactTypes.STRING)
		.setDescription("Can only be used together with On page to give a full page range for the entity.");
	public static final SProperty<String> Video_game_type = new SProperty<>(
		"Video game type",
		SFactTypes.STRING)
		.setDescription("The type of video game.");
	public static final SProperty<String> Web_enhancement = new SProperty<>(
		"Web enhancement",
		SFactTypes.STRING)
		.setAutocompleteDisabled(true)
		.setDescription("Links to all the web enhancements belonging to this product.");
	public static final SProperty<String> Website = new SProperty<>(
		"Website",
		SFactTypes.STRING)
		.setFormNote("The URL of the product's official page on the publisher's website.")
		.setDescription("An URL that is strongly linked to this entity. E.g. the Paizo page for a book.");
	public static final SProperty<List<PageRef>> Writer = new SProperty<>(
		"Writer",
		SFactTypes.PAGE_LIST)
		.setSuggestValuesFrom("Category:Real-world people")
		.setDescription("The writer working on this non-book product.");
	public static final SProperty<Boolean> Is_subsection = new SProperty<>(
		"Is subsection",
		SFactTypes.BOOLEAN)
		.setDescription("Marks this section as a subsection of the previous section.")
		.setDefaultValue("f");
}
