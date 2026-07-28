package io.github.pfwikis.bots.common.api.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class AAPISubmodule<KEY extends Enum<?>, MODULE extends AAPIModule> {
	
	private final KEY key;
	private final MODULE value;
}
