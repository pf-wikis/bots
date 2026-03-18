package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Automatically subscribe the user to the talk page thread?*/
@Getter
@RequiredArgsConstructor
public enum AAPIDiscussiontoolseditAutosubscribe {
	DEFAULT("default"),

	NO("no"),

	YES("yes");

	private final String jsonValue;
}
