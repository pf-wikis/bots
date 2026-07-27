package io.github.pfwikis.bots.common.api.generated.params;

// import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.RequiredArgsConstructor;
import lombok.Getter;
import io.github.pfwikis.bots.common.Wiki;

@Getter
@RequiredArgsConstructor
public enum UserGroup {
	STAR("*"),

	BOT("bot"),

	PATRON("patron"),

	SYSOP("sysop"),

	TECHADMIN("techadmin"),

	USER("user");

	@Getter(onMethod_ = @JsonValue)
	private final String name;
}
