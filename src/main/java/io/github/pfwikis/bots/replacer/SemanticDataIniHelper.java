package io.github.pfwikis.bots.replacer;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import io.github.pfwikis.bots.common.bots.Run.SingleRun;
import io.github.pfwikis.bots.common.model.Page;
import io.github.pfwikis.bots.replacer.PseudoParser.OC;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SemanticDataIniHelper {

	private final SingleRun run;
	private Page page;

	public void start() {
		var pages = run.getWiki().getPagesTranscluding("Template:Accessory");
		
		for(var p:pages) {
			page = p;
			try {
				extractPage();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void extractPage() {
		var txt = run.getWiki().getPageText(page.getTitle());
		//remove comments
		txt = txt.replaceAll("(?s)<!--.*?-->", "");
		var pp = new PseudoParser(txt);
		var texts = search(pp);

		if(texts.isEmpty())
			return;
		
		String text;
		
		if(texts.size() == 1) {
			text = texts.get(0);
		}
		else {
			text = String.join("\n\n\n", texts)
					+ "\n<!--Split those into multiple facts pages and link to them here-->[[Category:Pages with errors]]";
		}
		run.getWiki().editIfChange("Facts:"+page.getTitle(), text, "Automatic facts extraction");
	}

	private List<String> search(PseudoParser pp) {
		if(pp.goTo("\\{\\{\\s*Accessory\\s*\\|\\s*") == null)
			return Collections.emptyList();
		var sub = pp.subParserTo(2, new OC('{', '}'));
		var text = fields(sub);
		
		var res = new ArrayList<>(search(pp));
		res.addFirst(text);
		return res;
	}

	private String fields(PseudoParser pp) {
		var fields = new ArrayList<Field>();
		pp.trim();
		while (true) {
			var key = pp.goTo("\\s*=\\s*");
			if(key == null) {
				return finishFacts(fields);
			}
			var value = pp.goToFirstOutside('|', new OC('[',']'), new OC('{','}')).trim();
			fields.add(new Field(key.strip(), value));
		}
	}
	
	private StringBuilder templateField(StringBuilder sb, String field, String value) {
		if(StringUtils.isAllBlank(value)) {
			return sb;
		}
		return sb.append("| ").append(field).append(" = ").append(value).append("\n");
	}
	
	private String finishFacts(ArrayList<Field> fields) {
		fields.removeIf(f->StringUtils.isAllBlank(f.value));
		collectNumbered(fields);
		var name = page.getTitle().replace('_', ' ').replaceAll(" *\\(.*", "");
		var sb = new StringBuilder()
			.append("{{Facts/Accessory\n");
		templateField(sb, "Name", name);
		
		//set default values
		if(fields.stream().noneMatch(f->"type".equals(f.key)))
			fields.add(new Field("type", "Accessory"));
		
		for(int i=0;i<fields.size();i++) {
			var field = fields.get(i);
			switch(field.key) {
				default -> {
					System.err.println("Unhandled field key "+field.key);
					continue;
				}
				case "author", "artist" -> {
					templateField(sb, StringUtils.capitalize(field.key), removeLinks(field.value));
				}
				case "follows",
						"precedes",
						"publisher",
						"series" -> {
					templateField(sb, StringUtils.capitalize(field.key), removeLinks(noBold(field.value)));
				}
				
				case "title" -> {
					var title = noBold(field.value);
					if(!name.equals(title)) {
						templateField(sb, "Full title", title);
					}
				}
				case "type"    -> templateField(sb, "Accessory type", field.value);
				case "enhance" -> templateField(sb, "Web enhancement", field.value);
				case "rules"   -> templateField(sb, "Rule system", removeLinks(field.value));
				case "image"   -> templateField(sb, "Image", StringUtils.removeStart(removeLinks(field.value), "File:"));
				case "awards",
						"binding", 
						"gallery" -> {
					templateField(sb, StringUtils.capitalize(field.key), field.value);
				}
				case "released", "expected" -> templateField(sb, "Release date", parseDate(field.value));
				case "pubcode",
						"quantity",
						"material",
						"isbn",
						"price",
						"website" ->
					templateField(sb, StringUtils.capitalize(field.key), field.value);
			};
			fields.remove(i);
			i--;
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

	
	private static final List<DateTimeFormatter> FORMATS = List.of(
		"MMMM dd, yyyy",
		"MMMM d, yyyy",
		"MMMM yyyy"
	).stream().map(v->DateTimeFormatter.ofPattern(v, Locale.US)).toList();
	private static final DateTimeFormatter TARGET_FORMAT = DateTimeFormatter.ofPattern("uuuu[-MM[-dd]]", Locale.US);
	private String parseDate(String value) {
		for(var format:FORMATS) {
			try {
				var info = format.parse(value);
				return TARGET_FORMAT.format(info);
			} catch(DateTimeParseException e) {}
		}
		System.err.println("Unparseable date: "+value);
		return value;
	}

	private String noBold(String value) {
		return value.replace("'''", "").replace("''", "");
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
