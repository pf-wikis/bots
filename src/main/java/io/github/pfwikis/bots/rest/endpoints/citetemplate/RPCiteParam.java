package io.github.pfwikis.bots.rest.endpoints.citetemplate;

import org.apache.commons.lang3.StringUtils;

import com.google.common.primitives.Ints;

import io.github.pfwikis.bots.common.model.subject.SemanticSubject;
import lombok.Data;

@Data
public class RPCiteParam {

	public static final RPCiteParam EMPTY = new RPCiteParam();
	
	private String factsPage;
	private Integer intLocation;
	private String location;
	private String endPage;
	private String showAs;
	private Mode mode;
	private SemanticSubject.Container semanticSubject;
	
	
	public static enum Mode {
		FULL_REF;
	}

	public boolean validate() {
		if(location!=null)
			intLocation=Ints.tryParse(location);
		return factsPage != null && factsPage.startsWith("Facts:") && semanticSubject != null;
	}

	public boolean hasLocation() {
		return !StringUtils.isBlank(location);
	}

	public boolean hasShowAs() {
		return !StringUtils.isBlank(showAs);
	}

	public boolean hasEndPage() {
		return !StringUtils.isBlank(endPage);
	}
}
