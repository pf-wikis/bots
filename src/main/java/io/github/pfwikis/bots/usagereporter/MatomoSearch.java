package io.github.pfwikis.bots.usagereporter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatomoSearch {
	private String label;
	@JsonProperty("nb_visits")
	private int visits;
	@JsonProperty("avg_time_on_page")
	private int averageSecondsOnPage;
}
