package io.github.pfwikis.bots.common.api.model;

import java.io.IOException;
import java.util.List;

import io.github.pfwikis.bots.common.api.responses.AAPIWrappedResponse.AAPIError;
import io.github.pfwikis.bots.common.api.responses.IResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.StandardException;

public class AAPIExceptions {

	@Getter
	@RequiredArgsConstructor
	public static class AAPIValidationException extends AAPIRuntimeException {
		private final IResponse<?> failed;
		
		public AAPIValidationException(String message, IResponse<?> failed) {
			super("Failed validation: "+message+" on:\n"+failed);
			this.failed = failed;
		}
	}

	@Getter
	@StandardException
	public static class AAPIException extends IOException {
		private AAPIError aapiError;
	}
	
	public static class AAPIMultipleExceptions extends AAPIException {
		public AAPIMultipleExceptions(List<AAPIException> list) {
			super(list.size()+" AAPI errors occurred");
			list.forEach(this::addSuppressed);
		}
	}
	
	@StandardException
	public static class AAPIRuntimeException extends RuntimeException {}

	public static AAPIException from(AAPIError error) {
		var e = new AAPIException(error.getCode()+": "+error.getText());
		e.aapiError = error;
		return e;
	}

}
