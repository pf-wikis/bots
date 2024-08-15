package io.github.pfwikis.bots.facts;
import io.github.pfwikis.bots.facts.model.SDIFactType;
import io.github.pfwikis.bots.facts.model.SDIProperty;
public class SFactsProperties {
	public static final SDIProperty<String> Accessory_type = SDIProperty.builder()
		.name("Accessory type")
		.factType(SDIFactType.PLAIN)
		.description("The type of accessory.")
		.build();
	public static final SDIProperty<String> Artist = SDIProperty.builder()
		.name("Artist")
		.factType("Page list")
		.suggestValuesFrom("Category:Artists")
		.description("An artist that worked on this book or product.")
		.build();
	public static final SDIProperty<String> Audio_type = SDIProperty.builder()
		.name("Audio type")
		.factType("Plain")
		.description("The type of audio product.")
		.build();
	public static final SDIProperty<String> Author = SDIProperty.builder()
		.name("Author")
		.hasFactDisplayFormat("_ordered.Author")
		.factType("Author")
		.suggestValuesFrom("Category:Authors")
		.description("This entity was written by the given Person. This property can appear multiple times to indicate multiple authors.")
		.build();
	public static final SDIProperty<String> Author_all = SDIProperty.builder()
		.name("Author all")
		.factType("Author")
		.description("This entity is automatically filled with all authors, section authors and primary authors.")
		.build();
	public static final SDIProperty<String> Author_ordered = SDIProperty.builder()
		.name("Author ordered")
		.List("Author;Order")
		.description("An ordered version of the Author property stored as records.")
		.build();
	public static final SDIProperty<String> Awards = SDIProperty.builder()
		.name("Awards")
		.disableAutocomplete(true)
		.factType("Plain")
		.description("Any awards this product has won.")
		.build();
	public static final SDIProperty<String> Blurb = SDIProperty.builder()
		.name("Blurb")
		.disableAutocomplete(true)
		.factType("Multiline wikitext")
		.description("A short description of this adventure, book or other product.")
		.build();
	public static final SDIProperty<String> Blurb_heading = SDIProperty.builder()
		.name("Blurb heading")
		.disableAutocomplete(true)
		.factType("Plain")
		.description("A heading for a text.")
		.build();
	public static final SDIProperty<String> Blurb_quotee = SDIProperty.builder()
		.name("Blurb quotee")
		.disableAutocomplete(true)
		.factType("Plain")
		.description("The source of a quote.")
		.build();
	public static final SDIProperty<String> Blurb_text = SDIProperty.builder()
		.name("Blurb text")
		.disableAutocomplete(true)
		.hasFactNote("This value might be used in multiple locations and should be concise. Consider <code>Blurb text extras</code> for additional content.")
		.factType("Multiline wikitext")
		.description("A text describing the entity in detail.")
		.build();
	public static final SDIProperty<String> Blurb_text_extras = SDIProperty.builder()
		.name("Blurb text extras")
		.disableAutocomplete(true)
		.hasFactNote("This value might be shown after <code>Blurb Text</code>, depending on the way the page uses these fields.")
		.factType("Multiline wikitext")
		.description("More details that can be shown together with <code>Blurb text</code> if there is enough space.")
		.build();
	public static final SDIProperty<String> Book_type = SDIProperty.builder()
		.name("Book type")
		.factType("Plain")
		.description("The type of book.")
		.build();
	public static final SDIProperty<String> Chapters = SDIProperty.builder()
		.name("Chapters")
		.factType("Plain")
		.description("The number of chapters.")
		.build();
	public static final SDIProperty<String> Composer = SDIProperty.builder()
		.name("Composer")
		.factType("Page list")
		.suggestValuesFrom("Category:Real-world people")
		.description("The composer who worked on this product.")
		.build();
	public static final SDIProperty<String> Created_from = SDIProperty.builder()
		.name("Created from")
		.description("Marks a relation between a generated page and its source. This implies, that if the source is deleted this page should be deleted as well.")
		.build();
	public static final SDIProperty<String> Debug = SDIProperty.builder()
		.name("Debug")
		.description("This property is meant to be used as a debugging helper and should only store valures temporarily.")
		.build();
	public static final SDIProperty<String> Deck_type = SDIProperty.builder()
		.name("Deck type")
		.factType("Plain")
		.description("The type of deck in this product.")
		.build();
	public static final SDIProperty<String> Decksize = SDIProperty.builder()
		.name("Decksize")
		.factType("Plain")
		.description("The number of cards in this deck.")
		.build();
	public static final SDIProperty<String> Description = SDIProperty.builder()
		.name("Description")
		.factType("Plain")
		.description("A wikitext description of the entity.")
		.build();
	public static final SDIProperty<String> Designer = SDIProperty.builder()
		.name("Designer")
		.factType("Page list")
		.suggestValuesFrom("Category:Real-world people")
		.description("The designer working on this product.")
		.build();
	public static final SDIProperty<String> Developer = SDIProperty.builder()
		.name("Developer")
		.factType("Page list")
		.suggestValuesFrom("Category:Real-world people")
		.description("The developer who worked on this product.")
		.build();
	public static final SDIProperty<String> Dimensions = SDIProperty.builder()
		.name("Dimensions")
		.factType("Plain")
		.description("How big the product is.")
		.build();
	public static final SDIProperty<String> Director = SDIProperty.builder()
		.name("Director")
		.factType("Page list")
		.suggestValuesFrom("Category:Real-world people")
		.description("The directory working on this product.")
		.build();
	public static final SDIProperty<String> Disable_autocomplete = SDIProperty.builder()
		.name("Disable autocomplete")
		.description("Yes, if autocomplete makes no sense for this property in forms.")
		.build();
	public static final SDIProperty<String> Discs = SDIProperty.builder()
		.name("Discs")
		.factType("Plain")
		.description("The number discs in this audio product.")
		.build();
	public static final SDIProperty<String> Engine = SDIProperty.builder()
		.name("Engine")
		.factType("Plain")
		.description("The engine used in this video game.")
		.build();
	public static final SDIProperty<String> Errata = SDIProperty.builder()
		.name("Errata")
		.disableAutocomplete(true)
		.factType("Plain")
		.description("A link to all the erratas related to this book.")
		.build();
	public static final SDIProperty<String> Fact_type = SDIProperty.builder()
		.name("Fact type")
		.factType("Plain")
		.suggestValuesFrom("Category:Facts templates")
		.description("The type of fact represented by this entity. This should be a reference to the template that created it.")
		.build();
	public static final SDIProperty<String> Follows = SDIProperty.builder()
		.name("Follows")
		.factType("Page list")
		.suggestValuesFrom("Namespace:Main")
		.description("The previous release.")
		.build();
	public static final SDIProperty<String> Full_title = SDIProperty.builder()
		.name("Full title")
		.disableAutocomplete(true)
		.hasFactNote("Fill this only, if the commonly used title of this product is a shorter form of the full title.")
		.factType("Plain")
		.description("The full book title. This should only be used in addition to name if the book has a long name that is not typically used in its full form.")
		.build();
	public static final SDIProperty<String> Gallery = SDIProperty.builder()
		.name("Gallery")
		.disableAutocomplete(true)
		.factType("Plain")
		.description("If the gallery has a different category name than "Artwork from PAGENAME".")
		.build();
	public static final SDIProperty<String> Gallery_page = SDIProperty.builder()
		.name("Gallery page")
		.description("The category page of the gallery of images belonging to this entity.")
		.build();
	public static final SDIProperty<String> Genre = SDIProperty.builder()
		.name("Genre")
		.factType("Plain")
		.description("The genre of this product.")
		.build();
	public static final SDIProperty<String> Has_2e_trait = SDIProperty.builder()
		.name("Has 2e trait")
		.description("A Pathfinder 2e trait.")
		.build();
	public static final SDIProperty<String> Has_coordinates = SDIProperty.builder()
		.name("Has coordinates")
		.description("The geo coordinates of the entity.")
		.build();
	public static final SDIProperty<String> Has_fact_display_format = SDIProperty.builder()
		.name("Has fact display format")
		.description("A SMW display format suffix.")
		.build();
	public static final SDIProperty<String> Has_fact_note = SDIProperty.builder()
		.name("Has fact note")
		.description("A note to be shown in forms with this field.")
		.build();
	public static final SDIProperty<String> Has_fact_type = SDIProperty.builder()
		.name("Has fact type")
		.factType("Plain")
		.description("This property is used to determine how a facts page is created from this.")
		.build();
	public static final SDIProperty<String> Has_infobox_label = SDIProperty.builder()
		.name("Has infobox label")
		.description("The label to use for this property in an infobox.")
		.build();
	public static final SDIProperty<String> Has_infobox_name = SDIProperty.builder()
		.name("Has infobox name")
		.description("The infobox name associated with this entity.")
		.build();
	public static final SDIProperty<String> Has_infobox_type = SDIProperty.builder()
		.name("Has infobox type")
		.factType("Plain")
		.description("The infobox type associated with this entity.")
		.build();
	public static final SDIProperty<String> Has_location_type = SDIProperty.builder()
		.name("Has location type")
		.factType("Plain")
		.description("The type of the location of interest.")
		.build();
	public static final SDIProperty<String> Has_meta_type = SDIProperty.builder()
		.name("Has meta type")
		.factType("Plain")
		.description("This property should be used to describe what kind of entity is described by the article.")
		.build();
	public static final SDIProperty<String> Has_name = SDIProperty.builder()
		.name("Has name")
		.description("Holds the name of an entity if it potentially is different from the page name.")
		.build();
	public static final SDIProperty<String> Has_name_for_sorting = SDIProperty.builder()
		.name("Has name for sorting")
		.description("An alternative version of the name to be used when sorting by name.")
		.build();
	public static final SDIProperty<String> Has_population = SDIProperty.builder()
		.name("Has population")
		.description("The population size of the city.")
		.build();
	public static final SDIProperty<String> Has_pubcode = SDIProperty.builder()
		.name("Has pubcode")
		.description("The official paizo product ID.")
		.build();
	public static final SDIProperty<String> Has_reference_to = SDIProperty.builder()
		.name("Has reference to")
		.description("This book contains a reference to the given character or thing.")
		.build();
	public static final SDIProperty<String> Has_release_date = SDIProperty.builder()
		.name("Has release date")
		.description("The point in time when the product reperesented by the entity is/was released.")
		.build();
	public static final SDIProperty<String> Has_unknown_release_date = SDIProperty.builder()
		.name("Has unknown release date")
		.description("Set instead of Property:has_release_date if it is unknown.")
		.build();
	public static final SDIProperty<String> Has_website = SDIProperty.builder()
		.name("Has website")
		.description("An official URL that represens the described entity.")
		.build();
	public static final SDIProperty<String> Image = SDIProperty.builder()
		.name("Image")
		.factType("Image")
		.description("The page of an image representing this entity.")
		.build();
	public static final SDIProperty<String> Is_capital = SDIProperty.builder()
		.name("Is capital")
		.description("Yes, if the entity is the capital city of its nation.")
		.build();
	public static final SDIProperty<String> Is_in_nation = SDIProperty.builder()
		.name("Is in nation")
		.description("The nation this city is part of.")
		.build();
	public static final SDIProperty<String> Is_represented_by_page = SDIProperty.builder()
		.name("Is represented by page")
		.description("The wikipage best representing the entity.")
		.build();
	public static final SDIProperty<String> Is_size = SDIProperty.builder()
		.name("Is size")
		.description("The size category of the city.")
		.build();
	public static final SDIProperty<String> Is_subsection = SDIProperty.builder()
		.name("Is subsection")
		.factType("Plain")
		.description("Marks this section as a subsection of the previous section.")
		.build();
	public static final SDIProperty<String> Isbn = SDIProperty.builder()
		.name("Isbn")
		.factType("Plain")
		.hasInfoboxLabel("ISBN")
		.description("The ISBN.")
		.Pvap("ISBN")
		.build();
	public static final SDIProperty<String> Level_range_end = SDIProperty.builder()
		.name("Level range end")
		.factType("Plain")
		.description("This adventure should end with the characters at this level '''or''' this society adventure can be played in a range ending with this level.")
		.build();
	public static final SDIProperty<String> Level_range_start = SDIProperty.builder()
		.name("Level range start")
		.factType("Plain")
		.description("This adventure is supposed to be started at this level '''or''' this society adventure can be played in a range starting with this level.")
		.build();
	public static final SDIProperty<String> Location = SDIProperty.builder()
		.name("Location")
		.factType("Page list")
		.description("This adventures features the location in a meaningful way.")
		.build();
	public static final SDIProperty<String> Map_type = SDIProperty.builder()
		.name("Map type")
		.factType("Plain")
		.description("The type of map.")
		.build();
	public static final SDIProperty<String> Material = SDIProperty.builder()
		.name("Material")
		.factType("Plain")
		.description("The material this product is made out of.")
		.build();
	public static final SDIProperty<String> Miniatures_type = SDIProperty.builder()
		.name("Miniatures type")
		.factType("Plain")
		.description("The type of miniatures in this product.")
		.build();
	public static final SDIProperty<String> Modes = SDIProperty.builder()
		.name("Modes")
		.factType("Plain")
		.description("The video game modes.")
		.build();
	public static final SDIProperty<String> Name = SDIProperty.builder()
		.name("Name")
		.disableAutocomplete(true)
		.factType("Plain")
		.description("The general name of an entity. This is used for many different types.")
		.build();
	public static final SDIProperty<String> Narrator = SDIProperty.builder()
		.name("Narrator")
		.factType("Page list")
		.suggestValuesFrom("Category:Real-world people")
		.description("A narrator that worked on this book or product.")
		.build();
	public static final SDIProperty<String> On_page = SDIProperty.builder()
		.name("On page")
		.factType("Plain")
		.description("The page this entity can be found on in the respective book. If the entitity is on multiple pages, this should indicate the starting page. Other allowed values are "Inside Back Cover", "Inside Front Cover", and "Inside Covers".")
		.Pvap("page number")
		.build();
	public static final SDIProperty<String> Order = SDIProperty.builder()
		.name("Order")
		.description("Stores a number that is used to establish some kind of order between multiple entries.")
		.build();
	public static final SDIProperty<String> Owl:differentFrom = SDIProperty.builder()
		.name("Owl:differentFrom")
		.Impo("owl differentFrom https://www.w3.org/TR/owl2-syntax/  Type:Page")
		.description("The property that determines that two given individuals are different.")
		.build();
	public static final SDIProperty<String> Pages = SDIProperty.builder()
		.name("Pages")
		.factType("Plain")
		.description("The number of pages in this book.")
		.build();
	public static final SDIProperty<String> Performer = SDIProperty.builder()
		.name("Performer")
		.factType("Page list")
		.suggestValuesFrom("Category:Actors (real world)")
		.description("The performer who worked on this product.")
		.build();
	public static final SDIProperty<String> Platforms = SDIProperty.builder()
		.name("Platforms")
		.factType("Plain")
		.description("The platforms that are supported for this product.")
		.build();
	public static final SDIProperty<String> Precedes = SDIProperty.builder()
		.name("Precedes")
		.factType("Page list")
		.suggestValuesFrom("Namespace:Main")
		.description("The next release.")
		.build();
	public static final SDIProperty<String> Price = SDIProperty.builder()
		.name("Price")
		.hasFactNote("The purchase price of the product in the form of "$4.99".")
		.factType("Plain")
		.description("The price of this release.")
		.Pvap("price")
		.build();
	public static final SDIProperty<String> Primary_author = SDIProperty.builder()
		.name("Primary author")
		.hasFactDisplayFormat("_ordered.Primary_author")
		.factType("Author")
		.hasInfoboxLabel("Author")
		.suggestValuesFrom("Category:Authors")
		.description("This entity was written by the given Person. This property can appear multiple times to indicate multiple authors. Compared to the Author property this should be credited on the cover page.")
		.build();
	public static final SDIProperty<String> Primary_author_ordered = SDIProperty.builder()
		.name("Primary author ordered")
		.List("Primary_author;Order")
		.description("An ordered version of the Primary author property stored as records.")
		.build();
	public static final SDIProperty<String> Producer = SDIProperty.builder()
		.name("Producer")
		.factType("Page list")
		.suggestValuesFrom("Category:Real-world people")
		.description("The producer working on this product.")
		.build();
	public static final SDIProperty<String> Programmer = SDIProperty.builder()
		.name("Programmer")
		.factType("Page list")
		.suggestValuesFrom("Category:Real-world people")
		.description("The programmer working on this product.")
		.build();
	public static final SDIProperty<String> Pubcode = SDIProperty.builder()
		.name("Pubcode")
		.disableAutocomplete(true)
		.hasFactNote("The publisher's product code for this release (e.g. PZO9500, etc.).")
		.factType("Plain")
		.description("Typically a Paizo pubcode.")
		.build();
	public static final SDIProperty<String> Publisher = SDIProperty.builder()
		.name("Publisher")
		.hasFactNote("Typically Paizo Inc.")
		.factType("Page list")
		.suggestValuesFrom("Category:Publishers")
		.description("The publisher of this book or product.")
		.build();
	public static final SDIProperty<String> Quantity = SDIProperty.builder()
		.name("Quantity")
		.factType("Plain")
		.description("The number of elements included in the product.")
		.build();
	public static final SDIProperty<String> Region = SDIProperty.builder()
		.name("Region")
		.factType("Page list")
		.suggestValuesFrom("Category:Locations")
		.description("The region shown on this map.")
		.build();
	public static final SDIProperty<String> Release_date = SDIProperty.builder()
		.name("Release date")
		.hasFactDisplayFormat("#LOCL")
		.factType("Date")
		.description("The release date or a partial release date.")
		.build();
	public static final SDIProperty<String> Release_date_precision = SDIProperty.builder()
		.name("Release date precision")
		.description("Automatically generated property that says how precise a given date was.")
		.build();
	public static final SDIProperty<String> Release_note = SDIProperty.builder()
		.name("Release note")
		.factType("Plain")
		.description("A short note to differentiate this release from a similar one. Currently used for Society versions.")
		.build();
	public static final SDIProperty<String> Release_type = SDIProperty.builder()
		.name("Release type")
		.factType("Plain")
		.description("What differentiates this release from others. E.g. PDF, Hardover or Special Edition.")
		.build();
	public static final SDIProperty<String> Release_year = SDIProperty.builder()
		.name("Release year")
		.description("The year in which this product was released. This is typically automatically calculated.")
		.build();
	public static final SDIProperty<String> Represented_by_page = SDIProperty.builder()
		.name("Represented by page")
		.hasFactNote("Only fill this if it is different from the Main page of the same name.")
		.factType("Represented by page")
		.suggestValuesFrom("Namespace:Main")
		.description("This entity is best represented by this wikipage.")
		.build();
	public static final SDIProperty<String> Rule_system = SDIProperty.builder()
		.name("Rule system")
		.factType("Plain")
		.hasInfoboxLabel("Rule set")
		.suggestValuesFrom("Property:Rule system")
		.description("Which RPG system is used in this product.")
		.build();
	public static final SDIProperty<String> Runtime = SDIProperty.builder()
		.name("Runtime")
		.factType("Plain")
		.description("The runtime given as a number and a unit.")
		.build();
	public static final SDIProperty<String> Serialized = SDIProperty.builder()
		.name("Serialized")
		.factType("Plain")
		.description("If the contents were serialized, note the month and year of both the start and end of serialization.")
		.build();
	public static final SDIProperty<String> Series = SDIProperty.builder()
		.name("Series")
		.factType("Page list")
		.suggestValuesFrom("Namespace:Main")
		.description("The series this book belongs to.")
		.build();
	public static final SDIProperty<String> Suggest_values_from = SDIProperty.builder()
		.name("Suggest values from")
		.description("Suggests pages from the given category in form fields for this property.")
		.build();
	public static final SDIProperty<String> Text = SDIProperty.builder()
		.name("Text")
		.disableAutocomplete(true)
		.factType("Multiline wikitext")
		.description("A text describing the entity in detail.")
		.build();
	public static final SDIProperty<String> To_page = SDIProperty.builder()
		.name("To page")
		.factType("Plain")
		.description("Can only be used together with On page to give a full page range for the entity.")
		.build();
	public static final SDIProperty<String> Video_game_type = SDIProperty.builder()
		.name("Video game type")
		.factType("Plain")
		.description("The type of video game.")
		.build();
	public static final SDIProperty<String> Web_enhancement = SDIProperty.builder()
		.name("Web enhancement")
		.disableAutocomplete(true)
		.factType("Plain")
		.description("Links to all the web enhancements belonging to this product.")
		.build();
	public static final SDIProperty<String> Website = SDIProperty.builder()
		.name("Website")
		.hasFactNote("The URL of the product's official page on the publisher's website.")
		.factType("Plain")
		.description("An URL that is strongly linked to this entity. E.g. the Paizo page for a book.")
		.build();
	public static final SDIProperty<String> Writer = SDIProperty.builder()
		.name("Writer")
		.factType("Page list")
		.suggestValuesFrom("Category:Real-world people")
		.description("The writer working on this non-book product.")
		.build();
}
