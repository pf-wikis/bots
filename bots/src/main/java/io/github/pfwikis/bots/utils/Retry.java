package io.github.pfwikis.bots.utils;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;

import com.google.common.util.concurrent.Uninterruptibles;

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
	
	public static <T> T times(Callable<T> function, int n, int delaySeconds, Function<Exception, Boolean> onFailure) {
		int run = 0;
		RuntimeException exception = null;
		while(true) {
			try {
				return function.call();
			} catch(Exception e) {
				run++;
				if(exception == null) {
					exception = new RuntimeException("Failed after "+n+" retries", e);
				}
				else {
					exception.addSuppressed(e);
				}
				
				boolean wait = true;
				try {
					wait = Boolean.TRUE.equals(onFailure.apply(e));
				} catch (Exception e1) {
					exception.addSuppressed(e1);
				}
				if(run >= n) {
					throw exception;
				}
				
				log.info("Failed {} times, will retry in {}s", run, delaySeconds, e);
				if(delaySeconds > 0 && wait)
					Uninterruptibles.sleepUninterruptibly(delaySeconds, TimeUnit.SECONDS);
			}
		}
	}
}
