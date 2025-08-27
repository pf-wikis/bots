package io.github.pfwikis.bots.utils;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

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
	
	public static <T> T times(Callable<T> function, int n, int delaySeconds) {
		int run = 0;
		while(true) {
			try {
				return function.call();
			} catch(Exception e) {
				run++;
				if(run == n)
					throw new RuntimeException("Still failed after "+n+" retries", e);
				
				log.info("Failed {} times, will retry in {}s", run, delaySeconds, e);
				Uninterruptibles.sleepUninterruptibly(delaySeconds, TimeUnit.SECONDS);
			}
		}
	}
}
