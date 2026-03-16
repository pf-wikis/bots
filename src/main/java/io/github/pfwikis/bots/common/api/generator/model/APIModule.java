package io.github.pfwikis.bots.common.api.generator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.common.base.CaseFormat;

import io.github.pfwikis.bots.common.api.generator.APIGenerator;
import io.github.pfwikis.bots.common.api.generator.api.GenAPIModule;
import lombok.Getter;

@Getter
public class APIModule {
	private GenAPIModule module;
	private List<APIParam> parameters = new ArrayList<>();
	private List<APIParam> requiredParameters = new ArrayList<>();
	private List<APIParam> optionalParameters = new ArrayList<>();
	private List<APIParam> referencedBy = new ArrayList<>();
	private APIParam resultContinueParam;
	private APIParam resultLimitParam;
	private PageSetter pageSetter;

	public static APIModule create(APIGenerator gen, GenAPIModule m) {
		var r = new APIModule();
		r.module = m;
		
		for(var param : m.getParameters()) {
			var p = APIParam.create(gen, r, param);
			r.parameters.add(p);
		}
		
		extractContinues(r);
		extractPageTitle(r);
		r.parameters.forEach(p->{
			if(p.isRequired())
				r.requiredParameters.add(p);
			else
				r.optionalParameters.add(p);
		});
		
		return r;
	}
	
	public static record PageSetter(String idField, String pageField, boolean multi, boolean required) {}
	
	private static void extractPageTitle(APIModule mod) {
		mod.pageSetter = switch(mod.getJavaName()) {
			case "AAPIParse" -> new PageSetter("pageid", "page", false, false);
			case "AAPIQuery" -> new PageSetter("pageids", "titles", true, false);
			case "AAPIEdit",
				"AAPIQueryCategorymembers"
				-> new PageSetter("pageid", "title", false, true);
			default -> null;
		};
		
		if(mod.pageSetter != null) {
			mod.getParameters().removeIf(p->
				p.getRawName().equals(mod.pageSetter.idField)
				|| p.getRawName().equals(mod.pageSetter.pageField)
			);
		}
	}

	private static void extractContinues(APIModule mod) {
		var contCandidate = mod.parameters.stream().filter(p->p.getParameter().getName().equals("continue")).findAny();
		var limitCandidate = mod.parameters.stream().filter(p->p.getParameter().getName().equals("limit")).findAny();
		//if limit exists but no continue try "from" and "offset"
		if(limitCandidate.isPresent() && contCandidate.isEmpty()) {
			contCandidate = mod.parameters.stream().filter(p->p.getParameter().getName().equals("from")).findAny();
			if(contCandidate.isEmpty())
				contCandidate = mod.parameters.stream().filter(p->p.getParameter().getName().equals("offset")).findAny();
		}
		if(mod.getJavaName().equals("AAPIQueryAllcategories"))
			System.out.println("");
		if(contCandidate.isEmpty()) return;
		
		mod.parameters.remove(contCandidate.get());
		
		var cont = contCandidate.get();
		var limit = limitCandidate.orElse(null);
		
		mod.resultContinueParam=cont;
		mod.resultLimitParam=limit;
	}
	
	public String getJavaName() {
		return "AAPI"
			+CaseFormat.LOWER_HYPHEN.converterTo(CaseFormat.UPPER_CAMEL).convert(
					module.getPath().replace('+', '-').replace('.', '-')
			);
	}

	public String getDescription() {
		return module.getDescription();
	}
	
	public boolean requiresPost() {
		return module.isMustbeposted();
	}
}
