package io.github.pfwikis.bots.usagereporter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatomoValue {
	private long value;
}
