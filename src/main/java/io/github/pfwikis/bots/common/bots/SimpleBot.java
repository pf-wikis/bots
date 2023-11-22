package io.github.pfwikis.bots.common.bots;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

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
	protected List<SingleRun> createRuns() {
		var runs = new ArrayList<SingleRun>(2);
		for(boolean starfinder : new boolean[] {false, true}) {
			var run = new SingleRun(starfinder);
			try {
				run.setMasterWiki(new WikiAPI(starfinder, "VirenerusBot", rootPassword));
			} catch(Exception e) {
				log.error("Failed to log in as {}", botName, e);
				System.exit(-1);
			}
			
			checkAccount(run);
			
			try {
				run.setWiki(new WikiAPI(starfinder, botName, getBotPassword()));
			} catch(Exception e) {
				log.error("Failed to log in as {}", botName, e);
				System.exit(-1);
			}
			runs.add(run);
		}
		return runs;
	}
	
	protected String getBotPassword() {
		return rootPassword+botName;
	}

	protected <T> T loadConfig(Class<T> type) {
		return loadConfig(type, "Config");
	}
	
	protected <T> T loadConfig(Class<T> type, String name) {
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
	
	protected <T> void saveConfig(T config, String reason) {
		saveConfig(config, "Config", reason);
	}
	
	protected <T> void saveConfig(T config, String name, String reason) {
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
