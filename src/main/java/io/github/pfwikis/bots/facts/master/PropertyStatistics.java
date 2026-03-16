package io.github.pfwikis.bots.facts.master;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import com.beust.jcommander.Parameters;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset.Entry;

import io.github.pfwikis.bots.common.api.generated.params.NS;
import io.github.pfwikis.bots.common.api.model.PageRef;
import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.bots.ScatteredRunnableBot;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.node.NumericNode;
import tools.jackson.databind.node.ObjectNode;
import tools.jackson.databind.node.StringNode;
import tools.jackson.databind.node.ValueNode;

@Slf4j
@Parameters
public class PropertyStatistics extends SimpleBot implements ScatteredRunnableBot<PropertyStatistics.Shard> {
	
	public PropertyStatistics() {
		super("property-statistics", "Facts Master");
	}

	@Override
	public void run(RunContext ctx) throws IOException, InterruptedException {
		StringNode sn = null;
		var props = run.getWiki().getPagesInNamespace(NS.PROPERTY);
		for(var prop:props) {
			if(ctx.getScatterShard() instanceof Shard shard && prop.getTitle().hashCode()%10 != shard.hashModulo) {
				continue;
			}
			String name = prop.getTitle().getName();
			var results = run.getWiki().semanticAsk(Printout.class, "[["+name+"::+]]|?"+name+"=value|format=valuerank|maxtags=10000|limit=10000");
			var counts = HashMultiset.<String>create();
			
					
			Consumer<JsonNode> consumer = v->counts.add(switch(v) {
				case ObjectNode n -> {
					if(n.has("fulltext"))
						yield n.get("fulltext").stringValue();
					if(n.has("Author"))
						yield n.at("/Author/item/0/fulltext").stringValue();
					if(n.has("Primary author"))
						yield n.at("/Primary author/item/0/fulltext").stringValue();
					if(n.has("timestamp"))
						yield n.get("raw").stringValue().substring(2);
					yield n.toString();
				}
				case StringNode str -> str.stringValue();
				case ValueNode n -> n.asString();
				default -> throw new IllegalStateException("unhandles type "+v.getClass());
			});
			results.forEach(r-> {
				if(r.getPrintouts().value().isContainer())
					r.getPrintouts().value().forEach(consumer);
				else
					consumer.accept(r.getPrintouts().value);
			});
			
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
				sb
					.append("\n\t<tr><td>")
					.append(e.getElement())
					.append("</td><td>")
					.append(countToRange(e.getCount()))
					.append("</td></tr>");
			}
			sb.append("\n</table>");
			run.getWiki().editIfChange(PageRef.of("User:Bot Facts Master/Statistics/"+name), sb.toString(), "Update property statistics");
		}
	}
	
	private String countToRange(int count) {
		if(count <= 1)
			return Integer.toString(count);
		if(count <=5) {
			return "2-5";
		}
		if(count <=50) {
			return "6-50";
		}
		if(count <=500) {
			return "51-500";
		}
		if(count <=5000) {
			return "501-5000";
		}
		return "&gt;5000";
	}

	@Override
	public String getDescription() {
		return null;
	}

	
	public static record Shard(int hashModulo) {}
	private static record Printout(
		JsonNode value
	) {}


	@Override
	public List<Shard> createScatterShards() {
		return IntStream.range(0, 10).mapToObj(Shard::new).toList();
	}
}
