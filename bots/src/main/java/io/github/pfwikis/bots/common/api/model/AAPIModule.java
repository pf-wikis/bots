package io.github.pfwikis.bots.common.api.model;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;

import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;

import io.github.pfwikis.bots.common.api.AAPI;

public interface AAPIModule {
	
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix);
	
	public Builder builder();
	
	public abstract class Builder {
	
		public abstract void forEachModule(Consumer<AAPIModule> c);
		
		public abstract <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce);
		
		
		public boolean requiresPost() {
			return mapReduce(m->m.builder().internalRequiresPost(), (a,b)->a||b);
		}
		
		public boolean requiresPagination() {
			return mapReduce(m->m.builder().internalRequiresPagination(), (a,b)->a&&b);
		}
		
		protected abstract boolean internalRequiresPost();
		
		protected abstract boolean internalRequiresPagination();
	}
}
