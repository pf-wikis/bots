package io.github.pfwikis.bots.common.api.model;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;

import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;

import io.github.pfwikis.bots.common.api.AAPI;
import io.github.pfwikis.bots.common.api.generated.params.AAPIImagerotateGenerator.AAPIImagerotateGeneratorModule;
import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryTokensType;

public interface AAPIModule {
	
	public void buildRequest(RequestContext ctx);
	
	public Builder builder();
	
	public static record RequestContext(AAPI api, ClassicRequestBuilder req, String paramPrefix, boolean forceNewToken) {

		public void addParameter(String key, Object val) {
			if(val != null)
				req.addParameter(paramPrefix+key, val.toString());
		}

		public String requestToken(AAPIQueryTokensType type) {
			return api.requestToken(type, forceNewToken);
		}

		public RequestContext appendParamPrefix(String app) {
			return new RequestContext(api, req, paramPrefix+app, forceNewToken);
		}
	}
	
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
