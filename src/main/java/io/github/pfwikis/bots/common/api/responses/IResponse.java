package io.github.pfwikis.bots.common.api.responses;

public interface IResponse<T extends IResponse<T>> {

	public default void validate() {}

	public default void addContinuedResults(T more) {
		throw new UnsupportedOperationException();
	}
}
