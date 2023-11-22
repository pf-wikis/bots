package io.github.pfwikis.bots.common.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.Data;

@Datapublic class LogEventsQuery {
	private List<JsonNode> logevents = new ArrayList<>();
}