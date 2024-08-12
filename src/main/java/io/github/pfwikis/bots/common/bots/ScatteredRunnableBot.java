package io.github.pfwikis.bots.common.bots;

import java.util.List;

public interface ScatteredRunnableBot<T> {

	public List<T> createScatterShards();
}
