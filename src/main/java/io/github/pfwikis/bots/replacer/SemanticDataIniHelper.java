package io.github.pfwikis.bots.replacer;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multiset.Entry;

import io.github.pfwikis.bots.common.bots.Run.SingleRun;
import io.github.pfwikis.bots.common.model.Page;
import io.github.pfwikis.bots.replacer.PseudoParser.OC;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class SemanticDataIniHelper {

	private final SingleRun run;
	private Page page;

	public void start() {
		start("Map");
		start("Miniatures");
		start("Audio");
		start("Video game");
		start("Deck");
	}

	public record TemplateDef(String template, Multiset<String> fields) {
		public String toString() {
			return template;
		}
	}
	private void start(String template) {
		if(StringUtils.isAllBlank(run.getWiki().getPageText("Template:"+template)))
			return;
		
		var pages = run.getWiki().getPagesTranscluding("Template:"+template);
		var tDef = new TemplateDef(template, HashMultiset.create());
		
		for(var p:pages) {
			page = p;
			try {
				extractPage(tDef);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		log.info("Template {} fields:", template);
		tDef.fields.entrySet().stream()
			.sorted(Comparator.comparing(Entry::getElement))
			.forEach(f->log.info("\t{}", f));
	}

	private void extractPage(TemplateDef template) {
		var txt = run.getWiki().getPageText(page.getTitle());
		//remove comments
		txt = txt.replaceAll("(?s)<!--.*?-->", "");
		var pp = new PseudoParser(txt);
		var texts = search(template, pp);

		if(texts.isEmpty())
			return;
		
		if(texts.size() == 1) {
			run.getWiki().editIfChange("Facts:"+page.getTitle(), texts.get(0), "Automatic facts extraction");
		}
		else {
			var text = "";
			for(int i=0;i<texts.size();i++) {
				var subPageText = texts.get(i);
				if(!subPageText.contains("\n| Gallery =") && run.getWiki().pageExists("Category:Artwork from "+page.getTitle())) {
					subPageText=subPageText.replaceFirst("(\\{\\{Facts/.*)", "$1\n| Gallery = "+page.getTitle());
				}
				var pageName = "Facts:"+page.getTitle()+"/"+template+" "+(i+1);
				run.getWiki().editIfChange(pageName, subPageText, "Automatic facts extraction");
				text+="* [["+pageName+"|]]\n";
			}
			run.getWiki().editIfChange("Facts:"+page.getTitle(), text+"[[Category:Fact pages listing multiple Facts]]", "Automatic facts extraction");
		}
		
	}
	
	private List<String> search(TemplateDef template, PseudoParser pp) {
		if(pp.goTo("\\{\\{\\s*"+template+"\\s*\\|\\s*") == null)
			return Collections.emptyList();
		var sub = pp.subParserTo(2, new OC('{', '}'));
		var text = fields(template, sub);
		
		var res = new ArrayList<>(search(template, pp));
		res.addFirst(text);
		return res;
	}

	private String fields(TemplateDef template, PseudoParser pp) {
		var fields = new ArrayList<Field>();
		pp.trim();
		while (true) {
			var key = pp.goTo("\\s*=\\s*");
			if(key == null) {
				return finishFacts(template, fields);
			}
			var value = pp.goToFirstOutside('|', new OC('[',']'), new OC('{','}')).trim();
			fields.add(new Field(key.strip(), value));
		}
	}
	
	private void templateField(StringBuilder sb, String field, String value) {
		if(!StringUtils.isAllBlank(value)) {
			sb.append("| ").append(field).append(" = ").append(value).append("\n");
		}
	}
	
	private String finishFacts(TemplateDef template, ArrayList<Field> fields) {
		final String templateType = template+" type";
		fields.removeIf(f->StringUtils.isAllBlank(f.value));
		rename(fields);
		collectNumbered(fields);
		var name = page.getTitle().replace('_', ' ').replaceAll(" *\\(.*", "");
		var sb = new StringBuilder()
			//.append("{{WIP}}")
			.append("{{Facts/"+template+"\n");
		templateField(sb, "Name", name);
		
		//set default values
		if(fields.stream().noneMatch(f->"type".equals(f.key)))
			fields.add(new Field("type", template.template));
		
		fields.replaceAll(f-> new Field(
			switch(f.key) {
				default -> StringUtils.capitalize(f.key);
				case "title" -> "Full title";
				case "type"    -> templateType;
				case "enhance" -> "Web enhancement";
				case "rules"   -> "Rule system";
				case "released", "expected" -> "Release date";
			},
			f.value
		));
		
		for(int i=0;i<fields.size();i++) {
			var field = fields.get(i);
			template.fields.add(field.key);
			
			var realValue = switch(field.key) {
				case "Pages" -> removeSuffix(field.value, "page");
				case "Discs" -> removeSuffix(field.value, "disc");
				case "Performer", "Director", "Rule system", "Author", "Artist", "Region" -> removeLinks(cleanList(field.value));
				case "Follows",
						"Precedes",
						"Publisher",
						"Series" -> removeLinks(noBold(field.value));
				
				case "Full title" -> {
					var title = noBold(field.value);
					if(!name.equals(title)) {
						yield title;
					}
					yield "";
				}
				case "Awards", "Binding",
					"Gallery", "Web enhancement" -> field.value;
				case "Image" -> StringUtils.removeStart(removeLinks(field.value), "File:");
				case "Release date" -> {
					var parsed = parseDate(field.value);
					if(parsed != null) {
						yield parsed;
					}
					else {
						yield null;
					}
				}
				case "Pubcode",
					 "Quantity",
					 "Material",
					 "Isbn",
					 "Price",
					 "Website",
					 "Dimensions",
					 "Decksize",
					 "Grid",
					 "Runtime" -> field.value;
				default -> {
					if(field.key.equals(templateType))
						yield field.value;
					yield null;
				}
			};
			if(realValue == null) {
				log.error("Unhandled field '{}' = '{}'", field.key, field.value);
			}
			else {
				templateField(sb, field.key, realValue);
				fields.remove(i);
				i--;
			}
		}
		
		sb.append("}}");
		if(!fields.isEmpty()) {
			sb.append("<!--\nUnhandled fields:\n");
			for(var f:fields) {
				templateField(sb, f.key, f.value);
			}
			sb.append("-->[[Category:Pages with errors]]");
		}
		return sb.toString();
	}

	
	private String removeSuffix(String value, String suffix) {
		if(value.matches("\\d+")) {
			return value;
		}
		else if(value.matches("\\d+ "+suffix+"s")) {
			return value.substring(0, value.length()-(suffix.length()+2));
		}
		else if(value.matches("\\d+ "+suffix)) {
			return value.substring(0, value.length()-(suffix.length()+1));
		}
		return null;
	}

	private void rename(ArrayList<Field> fields) {
		fields.replaceAll(f->new Field(switch(f.key) {
			case "regions" -> "region";
			default -> f.key;
		}, f.value));
	}


	private static final List<DateTimeFormatter> FORMATS = List.of(
		"MMMM dd, yyyy",
		"MMMM d, yyyy",
		"MMMM yyyy",
		"yyyy",
		"dd MMMM yyyy",
		"d MMMM yyyy"
	).stream().map(v->DateTimeFormatter.ofPattern(v, Locale.US)).toList();
	private static final DateTimeFormatter TARGET_FORMAT = DateTimeFormatter.ofPattern("uuuu[-MM[-dd]]", Locale.US);
	public static String parseDate(String value) {
		for(var format:FORMATS) {
			try {
				var info = format.parse(value);
				return TARGET_FORMAT.format(info);
			} catch(DateTimeParseException e) {}
		}
		
		return null;
	}

	private String noBold(String value) {
		return value.replace("'''", "").replace("''", "");
	}
	
	private String cleanList(String txt) {
		return txt.replaceAll(" *, *", "; ");
	}

	private String removeLinks(String txt) {
		return Arrays.stream(txt.replaceAll("\\[\\[(.*?)(\\|.*?)?\\]\\]", "$1")
				.split("\\s*;\\s*"))
				.map(String::strip)
				.distinct()
				.sorted()
				.collect(Collectors.joining("; "));
	}

	private void collectNumbered(ArrayList<Field> fields) {
		if("Player Character Folio".equals(page.getTitle())) {
			System.out.print("");
		}
		
		var map = new HashMap<>(fields.stream()
			.collect(Collectors.toMap(f->f.key, Function.identity())));
		for(int i=0;i<fields.size();i++) {
			var f = fields.get(i);
			if(f.key.matches(".*\\d+$")) {
				fields.remove(i);
				i=-1; //hacky
				var realKey = f.key.replaceAll("\\d+$", "");
				var value = f.value;
				if(map.containsKey(realKey)) {
					var r=map.get(realKey);
					fields.remove(r);
					value = r.value+"; "+value;
				}
				fields.add(new Field(realKey, value));
				map.put(realKey, fields.get(fields.size()-1));
			}
		}
	}

	private record Field(String key, String value) {}

}
