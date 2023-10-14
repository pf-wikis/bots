package io.github.pfwikis.bots.common.model;

public record QueryResponse<T>(
		boolean batchcomplete,
		T query
) {
}
