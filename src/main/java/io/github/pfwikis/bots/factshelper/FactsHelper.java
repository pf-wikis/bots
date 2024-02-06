package io.github.pfwikis.bots
.factshelper;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.bots.SimpleBot;
import io.github.pfwikis.bots.common.model.SemanticAsk.Result;
import lombok.extern.slf4j.Slf4j;
import static io.github.pfwikis.bots.utils.MWJsonHelper.*;
import static io.github.pfwikis.bots.utils.RockerHelper.*;

@Slf4j
@Parameters
public class FactsHelper extends SimpleBot {

	public FactsHelper() {
		super("facts-helper", "Bot Facts Helper");
	}

	@Override
	public void run() throws IOException {
		if(run.isStarfinder()) return;
		
		var formDefs = this.loadConfig(FormDefinition[].class);
		var props = run.getWiki().semanticAsk("[[Has type::+]]"
				+ "|?Has type"
				+ "|?Has fact type"
				+ "|?Has fact display format"
				+ "|?Has fact note"
				+ "|?Suggest values from"
				+ "|?Has infobox label"
			)
			.stream()
			.map(this::createDefinition)
			.collect(Collectors.toMap(d->d.getName(), d->d));
		
		for(var formDef:formDefs) {
			handleForm(props, formDef);
		}
	}
	
	private void handleForm(Map<String, PropertyDefinition> props, FormDefinition form) {
		try {
			var rForm = form.resolve(props);
			if(!form.getInfoboxProperties().isEmpty())
				make(run.getWiki(), "Template:Infobox/"+form.getName(), MakeInfobox.template(rForm, run.getWiki().getAllSubPages("Template", "Infobox/"+form.getName())));
			make(run.getWiki(), "Template:Facts/"+form.getName(), MakeTemplate.template(rForm));
			make(run.getWiki(), "Template:Facts/"+form.getName()+"/Input", MakeTemplateInput.template(rForm));
			make(run.getWiki(), "Template:Facts/"+form.getName()+"/Ask", MakeTemplateAsk.template(form.getName(), rForm));
			make(run.getWiki(), "Template:Facts/"+form.getName()+"/Show", MakeTemplateShow.template(form.getName(), rForm));
			make(run.getWiki(), "Form:"+form.getName(), MakeForm.template(form.getName(), form.getPluralName(), rForm, false));
			make(run.getWiki(), "Category:Facts about "+form.getPluralName(), MakeCategory.template(form.getName(), form.getPluralName(), rForm));
			
			for(var subForm:rForm.getSubForms()) {
				handleSubForm(rForm, subForm);
			}
		} catch(Exception e) {
			this.reportException(new RuntimeException("Failed to create facts utilities for "+form.getName(), e));
		}
	}
	
	private void handleSubForm(FormDefinition.Resolved parent, FormDefinition.Resolved subForm) {
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

	private PropertyDefinition createDefinition(Result rawProp) {
		var res = new PropertyDefinition(
			rawProp.getFulltext().substring(9),
			rawProp.getPrintouts().getHasType(),
			rawProp.getPrintouts().getHasFactType(),
			rawProp.getPrintouts().getHasFactDisplayFormat(),
			rawProp.getPrintouts().getHasFactNote(),
			rawProp.getPrintouts().getSuggestValuesFrom(),
			rawProp.getPrintouts().getHasInfoboxLabel()
		);
		return res;
	}
	
	@Override
	protected String getDescription() {
		return
			"""
			This bot automatically creates the following for each for defined in [[User:Bot Facts Helper/Config|here]]:
			* '''Template:Infobox/...:''' the infobox for these facts (if specified in config)
			* '''Template:Facts/...:''' to specify facts of the given kind.
			* '''Template:Facts/.../Input:''' a template showing an input to easily create a new entity
			* '''Template:Facts/.../Ask:''' to easily call a template for each entity of the type.
			* '''Template:Facts/.../Show:''' to easily call a template with the properties of a single entity the type.
			* '''Form:...:''' a form to edit entities of this type automatically.
			* '''Category:Facts about ...:''' the category that organizes the facts.
			""";
	}
		
}
