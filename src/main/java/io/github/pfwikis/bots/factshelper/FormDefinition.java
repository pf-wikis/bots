package io.github.pfwikis.bots.factshelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

import io.github.pfwikis.bots.facts.model.SDIProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Getter
@Setter
public class FormDefinition {
	private String name;
	private String pluralName;
	private List<String> properties = new ArrayList<>();
	private List<FormDefinition> subForms = new ArrayList<>();
	private List<String> infoboxProperties = new ArrayList<>();
	
	@Value
	public static class Resolved {
		
		String name;
		String pluralName;
		List<SDIProperty> properties;
		List<FormDefinition.Resolved> subForms;
		List<SDIProperty> infoboxProperties;
		List<SDIProperty.Generated> generatedProperties;
		
		public String toCSSName() {
			return name.toLowerCase().replaceAll("[^a-z0-9]+", "-");
		}

		public boolean containsProperty(String name) {
			return properties.stream().anyMatch(p->p.getName().equals(name));
		}
	}

	public Resolved resolve(Map<String, SDIProperty> props) {
		return new Resolved(
			name,
			pluralName,
			resolve(properties, props),
			subForms.stream().map(f->f.resolve(props)).toList(),
			resolve(infoboxProperties, props),
			generated(props)
		);
	}
	
	private List<SDIProperty.Generated> generated(Map<String, SDIProperty> props) {
		var result = new ArrayList<SDIProperty.Generated>();
		//add special autoproperties
		result.add(props.get("Fact type").withGenerateCode("Template:Facts/"+name));
		//Add a release year if if there is a release date
		String releaseYearDefault = "|default=unknown";
		if(properties.contains("Serialized")) {
			releaseYearDefault = "|default={{#rmatch:{{{Serialized|}}}|.*?([0-9]{4}).*|${1}|unknown}}";
		}
		if(properties.contains("Release date")) {
			result.add(props.get("Release year").withGenerateCode("{{#show:{{FULLPAGENAME}}|?Release date#-F[Y]=|sort=Release date|order=ASC|limit=1|mainlabel=-|searchlabel="+releaseYearDefault+"}}"));
		}
		else if(subForms.stream().anyMatch(s->s.getProperties().contains("Release date"))){
			var sub=subForms.stream().filter(s->s.getProperties().contains("Release date")).findAny().get();
			result.add(props.get("Release year").withGenerateCode("{{#ask:[[-Has subobject::{{FULLPAGENAME}}]][[Fact type::Template:Facts/"+name+"/"+sub.name+"]][[Release date::+]]|?Release date#-F[Y]=|sort=Release date|order=ASC|limit=1|mainlabel=-|searchlabel="+releaseYearDefault+"}}"));
			result.add(props.get("Release date").withGenerateCode("{{#ask:[[-Has subobject::{{FULLPAGENAME}}]][[Fact type::Template:Facts/"+name+"/"+sub.name+"]][[Release date::+]]|?Release date#ISO-P=|sort=Release date|order=ASC|limit=1|mainlabel=-|searchlabel=}}"));
			result.add(props.get("Release date precision").withGenerateCode("{{#ask:[[-Has subobject::{{FULLPAGENAME}}]][[Fact type::Template:Facts/"+name+"/"+sub.name+"]][[Release date::+]]|?Release date precision=|sort=Release date|order=ASC|limit=1|mainlabel=-|searchlabel=|default=empty}}"));
		}
		//add gallery page
		if(properties.contains("Gallery")) {
			result.add(props.get("Gallery page").withGenerateCode("{{#if:{{{Gallery|}}}|Category:Artwork from {{{Gallery}}}|Category:Artwork from {{PAGENAME}}}}"));
		}
		
		return result;
	}

	private static List<SDIProperty> resolve(List<String> properties, Map<String, SDIProperty> props) {
		var result = new ArrayList<>(properties.stream()
			.map(p->Pair.of(p, props.get(p)))
			.map(p->Optional.ofNullable(p.getValue()).orElseThrow(()->new RuntimeException("Could not find definition for Property:"+p.getKey())))
			.toList());
		
		for(var p:result) {
			if(p.getFactType() == null) {
				throw new IllegalStateException("Fact type is null for "+p.getName());
			}
		}
		
		return result;
	}
}
