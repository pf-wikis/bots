package io.github.pfwikis.bots.usagereporter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatomoPage {
	private String label;
	@JsonProperty("sum_daily_nb_uniq_visitors")
	private int sumOfDailyUniqueVisitors;
	@JsonProperty("avg_time_on_page")
	private int averageSecondsOnPage;
	@JsonProperty("nb_visits")
	private int visits;
}
