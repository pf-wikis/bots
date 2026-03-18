package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<span class="apihelp-empty">(no description)</span>*/
@Getter
@RequiredArgsConstructor
public enum AAPISmwbrowseBrowse {
	CATEGORY("category"),

	CONCEPT("concept"),

	PAGE("page"),

	PROPERTY("property"),

	PSUBJECT("psubject"),

	PVALUE("pvalue"),

	SUBJECT("subject");

	private final String jsonValue;
}
