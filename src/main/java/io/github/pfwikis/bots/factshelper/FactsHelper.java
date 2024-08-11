package io.github.pfwikis.bots.factshelper;

import static io.github.pfwikis.bots.utils.RockerHelper.make;

import java.io.IOException;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import io.github.pfwikis.bots.facts.SDIModel;
import io.github.pfwikis.bots.facts.model.SDIConcept;
import io.github.pfwikis.bots.facts.model.SDIProperty;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters
public class FactsHelper extends SimpleBot {

	public FactsHelper() {
		super("facts-helper", "Bot Facts Helper");
	}

	@Override
	public void run() throws IOException {
		if(run.getServer()==Wiki.SF) return;
		
		var props = SDIProperty.load(run);
		var concepts = SDIModel.CONCEPTS;
		
		for(var concept:concepts) {
			handleForm(concept.resolve(props));
		}
	}
	
	private void handleForm(SDIConcept form) {
		try {
			make(run.getWiki(), "Template:Facts/"+form.getName(), MakeTemplate.template(form));
			make(run.getWiki(), "Template:Facts/"+form.getName()+"/Input", MakeTemplateInput.template(form));
			make(run.getWiki(), "Template:Facts/"+form.getName()+"/Ask", MakeTemplateAsk.template(form.getName(), form));
			make(run.getWiki(), "Template:Facts/"+form.getName()+"/Show", MakeTemplateShow.template(form.getName(), form));
			make(run.getWiki(), "Form:"+form.getName(), MakeForm.template(form.getName(), form.getPluralName(), form, false));
			make(run.getWiki(), "Category:Facts about "+form.getPluralName(), MakeCategory.template(form.getName(), form.getPluralName(), form));
			
			for(var subForm:form.getSubForms()) {
				handleSubForm(form, subForm);
			}
		} catch(Exception e) {
			this.reportException(new RuntimeException("Failed to create facts utilities for "+form.getName(), e));
		}
	}
	
	private void handleSubForm(SDIConcept parent, SDIConcept subForm) {
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
		return
			"""
			This bot automatically creates the following for each for defined in [[User:Bot Facts Helper/Config|here]]:
			* '''Template:Facts/...:''' to specify facts of the given kind.
			* '''Template:Facts/.../Input:''' a template showing an input to easily create a new entity
			* '''Template:Facts/.../Ask:''' to easily call a template for each entity of the type.
			* '''Template:Facts/.../Show:''' to easily call a template with the properties of a single entity the type.
			* '''Form:...:''' a form to edit entities of this type automatically.
			* '''Category:Facts about ...:''' the category that organizes the facts.
			""";
	}
		
}
