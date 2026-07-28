package io.github.pfwikis.bots.common.api.generator.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class APIEnumValue {

	public static APIEnumValue create(String value) {
		var r = new APIEnumValue();
		r.value = value;
		return r;
	}
	
	private String value;
	@Setter
	private String safeName;
	@Setter
	private APIModule referencedModule;
	@Setter
	private String description;

}
