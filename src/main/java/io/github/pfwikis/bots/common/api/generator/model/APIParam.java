package io.github.pfwikis.bots.common.api.generator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.fizzed.rocker.RockerContent;
import com.google.common.base.CaseFormat;

import io.github.pfwikis.bots.common.api.generator.APIGenerator;
import io.github.pfwikis.bots.common.api.generator.api.GenAPIParameter;
import io.github.pfwikis.bots.common.api.generator.api.GenAPIParameter.GenAPIParameterType;
import io.github.pfwikis.bots.common.api.generator.api.GenAPIParameter.GenAPITokenType;
import lombok.Getter;

@Getter
public class APIParam {

	private APIModule module;
	private GenAPIParameter parameter;
	private List<APIEnumValue> enumValues;
	private String description;
	private String javaType;
	private String javaValueType;
	private String javaParamValueType;

	public static APIParam create(APIGenerator gen, APIModule m, GenAPIParameter param) {
		var r = new APIParam();
		r.module = m;
		r.parameter = param;
		r.description = param.getDescription();
		
		//define types
		if(param.getType()==GenAPIParameterType.ENUM) {
			r.javaType = m.getJavaName()
				+StringUtils.capitalize(param.getName());
		}
		else {
			r.javaType = param.getType().getJavaType();
		}
		
		r.javaValueType=r.javaType;
		if(r.isSubmodule())
			r.javaValueType=r.getJavaSubmoduleType();
		if(param.isMulti())
			r.javaValueType = "List<"+r.javaValueType+">";
		
		r.javaParamValueType=r.javaType;
		if(r.isSubmodule())
			r.javaParamValueType=r.getJavaModuleType();
		if(param.isMulti())
			r.javaParamValueType = r.javaParamValueType+"...";
		
		/*
		r.javaEnumType=r.javaType;
		if(param.isMulti())
			r.javaEnumType = "List<"+r.javaEnumType+">";
		*/
		
		if(param.getEnumValues() != null && !param.getEnumValues().isEmpty()) {
			r.enumValues = new ArrayList<>();
			param.getEnumValues().forEach(v -> r.enumValues.add(APIEnumValue.create(v)));
			
			
			//create safe names
			List<String> safeEnumValues = new ArrayList<>();
			r.enumValues.forEach(v -> {
				if(v.getValue().equals("")) {
					v.setSafeName("EMPTY");
					return;
				}
				
				String name = CaseFormat.LOWER_HYPHEN
						.converterTo(CaseFormat.UPPER_UNDERSCORE)
						.convert(v.getValue());
				name = name.replace("!", "NOT_").replaceAll("[^A-Z_0-9]+", "_");
				if(Character.isDigit(name.charAt(0))) {
					name="V_"+name;
				}
				while(safeEnumValues.contains(name)) {
					name+="_ALT";
				}
				safeEnumValues.add(name);
				v.setSafeName(name);
			});
			
			//Improve description
			r.enumValues.forEach(v -> {
				var descr = r.getDescription();
				var mat = Pattern.compile("<dt><span[^>]*>(<a [^>]*>)?"+Pattern.quote(v.getValue())+"(</a>)?</span></dt>\n<dd>(?<cnt>.*?)</dd>\n?").matcher(descr);
				if(mat.find()) {
					v.setDescription(mat.group("cnt"));
					descr = descr.substring(0, mat.start())+descr.substring(mat.end(), descr.length());
				}
				r.description = StringUtils.trimToNull(descr);
			});
		}
		
		if(param.getSubmodules() != null) {
			for(var subE : param.getSubmodules().entrySet()) {
				var v = r.enumValues.stream().filter(ev->ev.getValue().equals(subE.getKey())).findAny().get();
				v.setReferencedModule(gen.getInfo(m.getModule(), subE.getValue()));
				if(v.getReferencedModule() != null) {
					v.getReferencedModule().getReferencedBy().add(r);
				}
			}
			
			//remove values that can't be referenced (because they are deprecated)
			r.enumValues.removeIf(ev->ev.getReferencedModule() == null);
		}
		
		return r;
	}

	public boolean requiresEnum() {
		return enumValues != null;
	}
	
	public String getRawName() {
		return parameter.getName();
	}
	
	public String getPrefixedRawName() {
		return module.getModule().getPrefix()+parameter.getName();
	}
	
	public String getSafeName() {
		return switch(parameter.getName()) {
			case "abstract","continue","for","new","switch",
			"assert","default","goto","package","synchronized",
			"boolean","do","if","private","this",
			"break","double","implements","protected","throw",
			"byte","else","import","public","throws",
			"case","enum","instanceof","return","transient",
			"catch","extends","int","short","try",
			"char","final","interface","static","void",
			"class","finally","long","strictfp","volatile",
			"const","float","native","super","while" -> "_"+parameter.getName();
			default -> parameter.getName();
		};
	}

	public boolean isSubmodule() {
		return parameter.getSubmodules() != null;
	}
	
	public boolean isRequired() {
		return parameter.isRequired() && !isToken();
	}
	
	public boolean isToken() {
		return parameter.getTokentype() != null;
	}
	
	public GenAPITokenType getTokenType() {
		return parameter.getTokentype();
	}

	public String getJavaModuleType() {
		if(!isSubmodule()) throw new IllegalStateException();
		return getJavaType()+"Module";
	}
	
	public String getJavaSubmoduleType() {
		if(!isSubmodule()) throw new IllegalStateException();
		return getJavaType()+"Submodule";
	}
	
	public String getJsonValue(String name) {
		String submod = (isSubmodule()?".getKey()":"");
		if(isMulti())
			return name+".stream().map(v->"
				+ parameter.getType().codeToGetJsonValue("v"+submod)
				+ ").collect(Collectors.joining(\"|\"))";
		return parameter.getType().codeToGetJsonValue(name+submod);
	}

	public boolean isMulti() {
		return parameter.isMulti();
	}

	public Integer getMaxValue() {
		return parameter.getHighmax();
	}

	public String getSubmoduleParamPrefix() {
		return parameter.getSubmoduleparamprefix();
	}
}
