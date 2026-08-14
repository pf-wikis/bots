package io.github.pfwikis.bots.utils;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;

import com.google.common.util.concurrent.Uninterruptibles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.StandardException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Retry {

	public static <T> T forDuration(Callable<T> function, Duration failAfter, int delaySeconds) {
		int run = 0;
		var start = Instant.now();
		while(true) {
			try {
				return function.call();
			} catch(Exception e) {
				run++;
				if(Duration.between(start, Instant.now()).compareTo(failAfter)>0)
					throw new RuntimeException("Still failed after trying for "+failAfter, e);
				
				log.info("Failed {} times, will retry in {}s until I failed for {}", run, delaySeconds, failAfter, e);
				Uninterruptibles.sleepUninterruptibly(delaySeconds, TimeUnit.SECONDS);
			}
		}
	}
	
	public static <T> T times(Callable<T> function, int n, int delaySeconds, Consumer<RetryContext> onFailure) {
		int run = 0;
		RetryException exception = null;
		while(true) {
			try {
				return function.call();
			} catch(Exception e) {
				run++;
				if(exception == null) {
					exception = new RetryException("Failed after (at most) "+n+" retries", e);
				}
				else {
					exception.addSuppressed(e);
				}
				
				var ctx = new RetryContext(true, e, true);
				try {
					onFailure.accept(ctx);
				} catch (Exception e1) {
					exception.addSuppressed(e1);
				}
				if(run >= n || !ctx.isRetry()) {
					throw exception;
				}
				
				log.info("Failed {} times, will retry in {}s", run, delaySeconds, e);
				if(delaySeconds > 0 && ctx.isDoWait())
					Uninterruptibles.sleepUninterruptibly(delaySeconds, TimeUnit.SECONDS);
			}
		}
	}
	
	@StandardException
	public static class RetryException extends RuntimeException {}
	
	@Data
	@AllArgsConstructor
	public static class RetryContext {
		private boolean doWait;
		private Exception exception;
		private boolean retry;
	}
}
