package io.github.pfwikis.bots.factshelper;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jsoup.Jsoup;

import com.beust.jcommander.Parameters;
import com.fizzed.rocker.RockerModel;

import io.github.pfwikis.bots.common.bots.SimpleBot;
import io.github.pfwikis.bots.common.model.SemanticAsk.Result;
import lombok.extern.slf4j.Slf4j;

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
		var props = run.getWiki().semanticAsk("[[Has type::+]]|?Has type|?Has fact type|?Has fact display format")
			.stream()
			.map(this::createDefinition)
			.collect(Collectors.toMap(d->d.getName(), d->d));
		
		for(var formDef:formDefs) {
			try {
				formDef.setRProperties(formDef.getProperties().stream()
					.map(n->
						Optional.ofNullable(props.get(n))
						.orElseThrow(()->new RuntimeException("Could not find definition for Property:"+n))
					)
					.toList());
				make("Template:Facts/"+formDef.getName(), MakeTemplate.template(formDef));
				make("Template:Facts/"+formDef.getName()+"/Input", MakeTemplateInput.template(formDef));
				make("Template:Facts/"+formDef.getName()+"/Ask", MakeTemplateAsk.template(formDef));
				make("Template:Facts/"+formDef.getName()+"/Show", MakeTemplateShow.template(formDef));
				make("Form:"+formDef.getName(), MakeForm.template(formDef));
				make("Category:Facts about "+formDef.getPluralName(), MakeCategory.template(formDef));
			} catch(Exception e) {
				this.reportException(new RuntimeException("Failed to create facts utilities for "+formDef.getName(), e));
			}
		}
	}
	
	private void make(String page, RockerModel template) {
		var txt = template
				.render().toString();
		run.getWiki().editIfChange(page, txt, "Automatic regeneration of template");
	}
	
	private PropertyDefinition createDefinition(Result rawProp) {
		var res = new PropertyDefinition(
			rawProp.getFulltext().substring(9),
			rawProp.getPrintouts().getHasType()[0],
			assumeNoneOrOne(rawProp.getPrintouts().getHasFactType()),
			assumeNoneOrOne(rawProp.getPrintouts().getHasFactDisplayFormat()),
			assumeNoneOrOne(rawProp.getPrintouts().getHasFactNote())
		);
		return res;
	}
	
	private <T> T assumeNoneOrOne(T[] values) {
		if(values == null || values.length == 0)
			return null;
		if(values.length == 1)
			return values[0];
		else
			throw new IllegalStateException("More than one value unexpectedly");
	}

	@Override
	protected String getDescription() {
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
