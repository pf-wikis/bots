package io.github.pfwikis.bots.common.bots;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.common.WikiAPI;
import io.github.pfwikis.bots.common.bots.Run.SingleRun;
import io.github.pfwikis.bots.utils.Jackson;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class SimpleBot extends Bot<SingleRun> {

	private String rawConfig;

	public SimpleBot(String id, String botName) {
		super(id, botName);
	}
	
	@Override
	public Wiki getWiki() {
		return run.getServer();
	}
	
	@Override
	protected List<SingleRun> createRuns() {
		var runs = new ArrayList<SingleRun>(2);
		for(var server:Wiki.values()) {
			var run = new SingleRun(server, "VirenerusBot", rootPassword, antiProtectionSecret);
			run.setWiki(WikiAPI.create(server, botName, getBotPassword(), antiProtectionSecret));
			runs.add(run);
		}
		return runs;
	}
	
	public <T> T loadConfig(Class<T> type) {
		return loadConfig(type, "Config");
	}
	
	public <T> T loadConfig(Class<T> type, String name) {
		var configPage = "User:"+botName+"/"+name;
		rawConfig = run.getWiki().getPageText(configPage);
		if(rawConfig.isEmpty()) {
			try {
				T result = type.getConstructor().newInstance();
				rawConfig = Jackson.JSON.writeValueAsString(result);
				run.getWiki().editIfChange(
						configPage,
						rawConfig,
						"Create default config."
				);
				run.getWiki().setContentModel(configPage, "json");
				log.info("Created default config at [["+configPage+"|]]\n\n");
				return result;
			} catch(Exception e) {
				throw new IllegalStateException("Could not create "+type, e);
			}
		}
		try {
			log.info("Using config at [["+configPage+"|]]\n\n");
			return Jackson.JSON.readValue(rawConfig, type);
		} catch (JsonProcessingException e) {
			throw new IllegalStateException("Could not load "+type, e);
		}
	}
	
	public <T> void saveConfig(T config, String reason) {
		saveConfig(config, "Config", reason);
	}
	
	public <T> void saveConfig(T config, String name, String reason) {
		var configPage = "User:"+botName+"/"+name;
		try {
			var newConfig = Jackson.JSON.writeValueAsString(config);
			if(newConfig.equals(config)) return;
			
			run.getWiki().editIfChange(configPage, newConfig, reason);
			log.info("Wrote config to [["+configPage+"|]]\n\n");
		} catch (JsonProcessingException e) {
			throw new IllegalStateException("Could not serialize "+config.getClass(), e);
		}
	}
}
