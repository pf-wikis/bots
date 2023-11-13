package io.github.pfwikis.bots.usagereporter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatomoEvent {
	private String label;
	@JsonProperty("nb_visits")
	private int visits;
	@JsonProperty("sum_daily_nb_uniq_visitors")
	private int sumOfDailyUniqueVisitors;
	private int idsubdatatable;
}
