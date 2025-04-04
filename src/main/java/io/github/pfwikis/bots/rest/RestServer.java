package io.github.pfwikis.bots.rest;

import static spark.Spark.post;

import java.util.List;

import io.github.classgraph.ClassGraph;
import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.scheduler.Scheduler;
import lombok.extern.slf4j.Slf4j;
import spark.Spark;

@Slf4j
public class RestServer {
	public static void start(Scheduler scheduler) {
		Spark.port(8765);
		Spark.threadPool(5); //limit number of threads as to bot bombard the wiki
		for(var wiki:Wiki.values()) {
			for(var endpoint : findAllEndpoints()) {
				endpoint.setWiki(wiki);
				endpoint.setScheduler(scheduler);
				log.info("RPEndpoint POST /{}/{}", wiki.getCode(), endpoint.getEndpoint());
				post("/"+wiki.getCode()+"/"+endpoint.getEndpoint(), endpoint);
			}
		}
	}
	
	public static void stop() {
		Spark.awaitStop();
	}
	
	private final static List<RPEndpoint<?>> findAllEndpoints() {
		return new ClassGraph()
			.acceptPackages(RestServer.class.getPackageName())
			.enableClassInfo()
			.scan()
			.getSubclasses(RPEndpoint.class)
			.stream()
			.filter(bc->!bc.isAbstract())
			.<RPEndpoint<?>>map(bc-> {
				try {
					return bc.loadClass(RPEndpoint.class)
						.getConstructor()
						.newInstance();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			})
			.toList();
	}
}
