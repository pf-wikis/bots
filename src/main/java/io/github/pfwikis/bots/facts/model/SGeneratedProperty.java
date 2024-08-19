package io.github.pfwikis.bots.facts.model;

public class SGeneratedProperty<JType> extends SProperty<JType> {

	private final String generateWikitext;
	
	public SGeneratedProperty(String name, SFactType<JType> factType, String generateWikitext) {
		super(name, factType);
		this.generateWikitext = generateWikitext;
	}
	
	@Override
	public String wikitextToStoreFact() {
		return getFactType().wikitextToStoreFact(this, generateWikitext);
	}
	
	//overrides to statisfy the type system
	@Override
	public SGeneratedProperty<JType> setDescription(String description) {
		return (SGeneratedProperty<JType>) super.setDescription(description);
	}
}
