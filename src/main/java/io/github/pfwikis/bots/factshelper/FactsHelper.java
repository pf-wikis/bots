package io.github.pfwikis.bots.factshelper;

import static io.github.pfwikis.bots.utils.RockerHelper.make;

import java.io.IOException;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import io.github.pfwikis.bots.facts.SModel;
import io.github.pfwikis.bots.facts.model.SConcept;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters
public class FactsHelper extends SimpleBot {

	public FactsHelper() {
		super("facts-helper", "Bot Facts Helper");
	}

	@Override
	public void run(RunContext ctx) throws IOException {
		var concepts = SModel.CONCEPTS;
		
		for(var concept:concepts) {
			handleConcept(concept);
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
