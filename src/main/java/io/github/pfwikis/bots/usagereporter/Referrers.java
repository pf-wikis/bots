package io.github.pfwikis.bots.usagereporter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Referrers {
	
	@JsonProperty("Referrers_visitorsFromSearchEngines")
	private int visitorsFromSearchEngines;
	@JsonProperty("Referrers_visitorsFromSocialNetworks")
	private int visitorsFromSocialNetworks;
	@JsonProperty("Referrers_visitorsFromDirectEntry")
	private int visitorsFromDirectEntry;
	@JsonProperty("Referrers_visitorsFromWebsites")
	private int visitorsFromWebsites;
	@JsonProperty("Referrers_visitorsFromCampaigns")
	private int visitorsFromCampaigns;
	@JsonProperty("Referrers_distinctSearchEngines")
	private int distinctSearchEngines;
	@JsonProperty("Referrers_distinctSocialNetworks")
	private int distinctSocialNetworks;
	@JsonProperty("Referrers_distinctKeywords")
	private int distinctKeywords;
	@JsonProperty("Referrers_distinctWebsites")
	private int distinctWebsites;
	@JsonProperty("Referrers_distinctWebsitesUrls")
	private int distinctWebsitesUrls;
	@JsonProperty("Referrers_distinctCampaigns")
	private int distinctCampaigns;
}
