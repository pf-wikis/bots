package io.github.pfwikis.bots.factshelper;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@RequiredArgsConstructor
public class PropertyDefinition {
	private final String name;
	private final PropertyType type;
	private final FactType factType;
	private final String factDisplayFormat;
	private final String note;
	private final String suggestValuesFrom;
}