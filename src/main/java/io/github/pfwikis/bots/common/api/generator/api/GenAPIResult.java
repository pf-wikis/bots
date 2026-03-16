package io.github.pfwikis.bots.common.api.generator.api;

import lombok.Data;

@Data
public class GenAPIResult {
	private GenAPIParamInfo paraminfo;
	private boolean batchcomplete;
	private GenAPIQuery query;
}
