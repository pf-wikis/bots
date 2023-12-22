package io.github.pfwikis.bots.factshelper;

import java.io.IOException;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.bots.SimpleBot;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters
public class FactsHelper extends SimpleBot {

	public FactsHelper() {
		super("facts-helper", "Bot Facts Helper");
	}

	@Override
	public void run() throws IOException {
		var properties = run.getWiki().semanticAsk("[[Property:+]][[Has type::+]]")
				.stream()
				.map(p->StringUtils.removeStart(p.getFulltext(), "Property:"))
				.sorted()
				.toList();
		var allProperties = properties.stream()
				.map(p->toSMWProp(p))
				.collect(Collectors.joining());
		var token = run.getWiki().requestToken("csrf");
		
		for(var page:run.getWiki().getPagesInCategory("Category:Facts templates")) {
			var text = """
				<includeonly>{{%s
				 |userparam=Facts:{{{page|{{FULLPAGENAME}}}}}
				 |link=none
				 |format=plainlist
				 |template={{{template|}}}
				 |named args=yes
				 |valuesep=;
				 |searchlabel=
				 |default={{{default|}}}
				}}</includeonly><noinclude>
				<div class="banner">
                This page is automatically created by [[User:Bot Facts Helper]].
                Do not edit it manually. Ask for a change of the bot in the discord instead.
                </div>
				This template allows you to call another template with all the fact properties of a page.
				It then provides all the properties as named parameters and the page it was called on as.
				<code><nowiki>{{{#userparam}}}</nowiki></code>.
				
				<templatedata>
				{
					"params": {
						"template": {
							"description": "The template that should be called with the facts.",
							"type": "wiki-template-name",
							"suggested": true
						}
						,"default": {
							"description": "what to show if there are no results matching the query",
							"type": "string"
						}%s
					}
				}
				</templatedata>
				
				</noinclude>""";

			run.getWiki().editIfChange(
				page.getTitle()+"/Show",
				text.formatted(
					"#show: Facts:{{{page|}}}\n" + allProperties,
					"""
					,"page": {
						"description": "the page that the facts come from without the 'Facts:' namespace",
						"type": "wiki-page-name",
						"required": true
					}
					"""
				),
				"Update show template"
			);
			run.getWiki().protect(page.getTitle(), "edit=sysop|move=sysop", "Automatically created page", token);
			
			run.getWiki().editIfChange(
				page.getTitle()+"/Ask",
				text.formatted(
					"""
					#ask: [[Fact type::%s]]{{{query|}}}\n
					 |limit=1000
					 |introtemplate={{{introtemplate|}}}
					 |outrotemplate={{{outrotemplate|}}}
					 |sort={{{sort|}}}
					 |order=asc
					""".formatted(page.getTitle()) + allProperties,
					"""
					,"query": {
						"description": "The semantic wiki query to select pages with. Filtering for the right Fact type is already done.",
						"type": "string",
						"suggested": true
					}
					,"introtemplate": {
						"description": "The template called before the first queried entity.",
						"type": "wiki-template-name"
					}
					,"outrotemplate": {
						"description": "The template called after the first queried entity.",
						"type": "wiki-template-name"
					}
					,"sort": {
						"description": "the property, or properties, to sort the results by",
						"type": "string"
					}
					"""
				),
				"Update ask template"
			);
		}
	}

	@Override
	protected String getDescription() {
		return
			"""
			This bot automatically creates the Show and Ask helper templates to each template in
			[[:Category:Facts_templates]].
			""";
	}
		
	private String toSMWProp(String prop) {
		prop = prop.strip();
		var style = switch(prop) {
			case "Release date" -> "#LOCL";
			default -> "";
		};
		return " |?"+prop+style+"="+prop+"\n";
	}

}
