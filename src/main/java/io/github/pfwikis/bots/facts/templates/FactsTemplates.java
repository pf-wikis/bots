package io.github.pfwikis.bots.facts.templates;

import static io.github.pfwikis.bots.utils.RockerHelper.make;

import java.io.IOException;
import java.util.stream.Collectors;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import io.github.pfwikis.bots.facts.SFactsProperties;
import io.github.pfwikis.bots.facts.SModel;
import io.github.pfwikis.bots.facts.model.SConcept;
import io.github.pfwikis.bots.facts.model.SProperty;
import io.github.pfwikis.bots.rest.endpoints.infobox.autocategorization.AutoCategorizer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters
public class FactsTemplates extends SimpleBot {

	public FactsTemplates() {
		super("facts-templates", "Bot Facts Master");
	}

	@Override
	public void run(RunContext ctx) throws IOException {
		var concepts = SModel.getConcepts(run.getServer());
		
		run.getWiki().editIfChange(
			run.getServer().getWikiNamespace()+":Automatic categorization",
			"{{Bot created|"+botName+"}}"
			+"{{tl|Infobox}} adds automatic categories based on the facts page. This page explains what categories are added.\n\n"
			+AutoCategorizer.generateDocs(run.getServer(), run.getWiki().getSeries2Category()),
			"Update auto categorization documentation"
		);
		
		if(run.getServer()==Wiki.PF) {
			make(run.getWiki(), "Template:Facts/Helper/Create page buttons", MakeTemplateCreatePageButtons.template(concepts));
		}
		
		handlePropertyDefinitions();
		
		for(var concept:concepts) {
			handleConcept(concept);
		}
	}
	
	private void handlePropertyDefinitions() {
		var patterns = "This page contains the regex patterns of the facts system:\n"
			+ SFactsProperties.getAll()
			.stream()
			.filter(p->p.getAllowsPattern() != null)
			.map(p->" "+p.toSafeName()+"|"+p.getAllowsPattern())
			.collect(Collectors.joining("\n"));
		run.getWiki().editIfChange("MediaWiki:Smw_allows_pattern", patterns, "Update facts patterns");
		
		for(var p:SProperty.getAll()) {
			make(run.getWiki(), "Property:"+p.getName(), MakeProperty.template(p));
		}
	}

	private void handleConcept(SConcept c) {
		try {
			make(run.getWiki(), "Template:Facts/"+c.getName(), MakeTemplate.template(c));
			make(run.getWiki(), "Template:Facts/"+c.getName()+"/Input", MakeTemplateInput.template(c));
			make(run.getWiki(), "Template:Facts/"+c.getName()+"/Ask", MakeTemplateAsk.template(c.getName(), c));
			make(run.getWiki(), "Template:Facts/"+c.getName()+"/Show", MakeTemplateShow.template(c.getName(), c));
			make(run.getWiki(), "Form:"+c.getName(), MakeForm.template(c.getName(), c.getPluralName(), c, false));
			make(run.getWiki(), "Category:Facts about "+c.getPluralName(), MakeCategory.template(c.getName(), c.getPluralName(), c));
			
			for(var subForm:c.getSubConcepts()) {
				handleSubForm(c, subForm);
			}
		} catch(Exception e) {
			this.reportException(new RuntimeException("Failed to create facts utilities for "+c.getName(), e));
		}
	}
	
	private void handleSubForm(SConcept parent, SConcept subForm) {
		try {
			var slashName = parent.getName()+"/"+subForm.getName();
			var spaceName = parent.getName()+" "+subForm.getPluralName();
			make(run.getWiki(), "Template:Facts/"+slashName, MakeSubTemplate.template(parent, subForm));
			make(run.getWiki(), "Template:Facts/"+slashName+"/Ask", MakeTemplateAsk.template(slashName, subForm));
			make(run.getWiki(), "Template:Facts/"+slashName+"/Show", MakeTemplateShow.template(slashName, subForm));
			make(run.getWiki(), "Form:"+slashName, MakeForm.template(slashName, spaceName, subForm, true));
			make(run.getWiki(), "Category:Facts about "+spaceName, MakeCategory.template(slashName, spaceName, subForm));
		} catch(Exception e) {
			this.reportException(new RuntimeException("Failed to create facts utilities for "+subForm.getName(), e));
		}
	}

	@Override
	public String getDescription() {
		var sb = new StringBuilder();
		sb.append("This bot is the ruling automaton in charge of the facts system.")
			.append("It creates most pages in the Property namespace:");
		for(var p:SProperty.getAll()) {
			sb.append("\n* [[Property:$p]]".replace("$p", p.getName()));
		}
		sb.append("\nIt creates the following facts related pages:");
		for(var c:SModel.getConcepts(run.getServer())) {
			sb.append("\n")
			.append(
				"""
				* for $cs
				** '''[[:Template:Facts/$c]]:''' to specify facts of the given kind.
				** '''[[:Template:Facts/$c/Input]]:''' a template showing an input to easily create a new entity
				** '''[[:Template:Facts/$c/Ask]]:''' to easily call a template for each entity of the type.
				** '''[[:Template:Facts/$c/Show]]:''' to easily call a template with the properties of a single entity the type.
				** '''[[:Form:$c]]:''' a form to edit entities of this type in an approachable way.
				** '''[[:Category:Facts about $cs]]:''' the category that organizes the facts.
				""".replace("$cs", c.getPluralName()).replace("$c", c.getName())
			);
		}
		return sb.toString();
	}
		
}
