package io.github.pfwikis.bots.propertystatistics;

import java.io.IOException;
import java.util.Comparator;

import org.apache.commons.lang3.StringUtils;

import com.beust.jcommander.Parameters;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.NumericNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset.Entry;

import io.github.pfwikis.bots.common.bots.SimpleBot;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters
public class PropertyStatistics extends SimpleBot {

	public PropertyStatistics() {
		super("property-statistics", "Bot Property Statistics");
	}

	@Override
	public void run() throws IOException, InterruptedException {
		var props = run.getWiki().getPagesInNamespace("Property");
		for(var prop:props) {
			String name = StringUtils.removeStart(prop.getTitle(), "Property:");
			var results = run.getWiki().semanticAsk("[["+name+"::+]]|?"+name+"=value|format=valuerank|maxtags=10000");
			var counts = HashMultiset.<String>create();
			results.forEach(r->
				r.getPrintouts().getValue().forEach(v->counts.add(switch(v) {
					case ObjectNode n -> {
						if(n.has("fulltext"))
							yield n.get("fulltext").textValue();
						if(n.has("Author"))
							yield n.at("/Author/item/0/fulltext").textValue();
						if(n.has("Primary author"))
							yield n.at("/Primary author/item/0/fulltext").textValue();
						if(n.has("timestamp"))
							yield n.get("raw").textValue().substring(2);
						yield n.toString();
					}
					case TextNode str -> str.textValue();
					case NumericNode n -> n.textValue();
					default -> throw new IllegalStateException("unhandles type "+v.getClass());
				}))
			);
			
			var sb = new StringBuilder();
			sb.append("<noinclude>{{Bot created|Bot Property Statistics|This template is automatically created from [[Created from::Property:"+name+"]].}}</noinclude>");
			if(counts.entrySet().size()>50) {
				sb.append("This property has "+counts.entrySet().size()+" different values. The following are the 50 most common ones.\n");
			}
			else {
				sb.append("A count of the unique properties:\n");
			}
			var entries = counts.entrySet().stream()
				.sorted(Comparator.<Entry<String>,Integer>comparing(Entry::getCount).reversed().thenComparing(e->e.getElement()))
				.limit(50)
				.toList();
			sb.append("<table class=\"wikitable\"><tr><th>Value</th><th>Count</th></tr>");
			for(var e:entries) {
				sb.append("\n\t<tr><td>").append(e.getElement()).append("</td><td>").append(e.getCount()).append("</td></tr>");
			}
			sb.append("\n</table>");
			run.getWiki().editIfChange("User:Bot Property Statistics/"+name, sb.toString(), "Update property statistics");
		}
	}
	
	@Override
	public String getDescription() {
		return """
		This bot regularly updates the statistics of properties on their pages
		""";
	}

}
