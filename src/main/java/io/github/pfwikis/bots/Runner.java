package io.github.pfwikis.bots;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import com.beust.jcommander.IDefaultProvider;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.MissingCommandException;
import com.google.common.base.CaseFormat;

import io.github.classgraph.ClassGraph;
import io.github.pfwikis.bots.common.bots.Bot;
import io.github.pfwikis.bots.scheduler.Scheduler;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Runner {
	
	public static final String MDC_KEY = "wiki";
	public static final String MDC_VALUE_NONE = "none";

	public static void main(String[] args) throws Exception {
		MDC.put(MDC_KEY, MDC_VALUE_NONE);
		Locale.setDefault(Locale.ROOT);
		var commands = JCommander.newBuilder();
		commands.defaultProvider(new IDefaultProvider() {
			@Override
			public String getDefaultValueFor(String optionName) {
				var name = StringUtils.removeStart(optionName, "--");
				name = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_UNDERSCORE).convert(name);
				var def = System.getenv("BOT_"+name);
				log.info("Using '{}' from environment: {}", StringUtils.removeStart(optionName, "--"), def);
				return def;
			}
		});
		commands.addCommand("scheduler", new Scheduler());
		for(var bot : Runner.getAllBots()) {
			commands.addCommand(bot.getId(), bot);
		}
		
		var commander = commands.build();
		try {
			commander.parse(args);
			if(commander.getParsedCommand() == null) {
				throw new MissingCommandException("Unknown command: "+Arrays.toString(args));
			}
		} catch(MissingCommandException e) {
			log.error(e.getMessage());
			commander.usage();
			System.exit(-1);
		}
		
		if("scheduler".equals(commander.getParsedCommand())) {
			((Scheduler)commander.getCommands().get(commander.getParsedCommand())
			.getObjects().get(0)).start();
		}
		else {
			var bot = (Bot<?>)commander.getCommands().get(commander.getParsedCommand())
				.getObjects().get(0);
			bot.startSingleInstance();
			if(bot.isHadError()) {
				System.exit(-1);
			}
		}
	}

	@Getter(lazy = true)
	private final static List<Bot<?>> allBots = new ClassGraph()
			.acceptPackages(Runner.class.getPackageName())
			.enableClassInfo()
			.scan()
			.getSubclasses(Bot.class)
			.stream()
			.filter(bc->!bc.isAbstract())
			.<Bot<?>>map(bc-> {
				try {
					return bc.loadClass(Bot.class)
						.getConstructor()
						.newInstance();
				} catch (Throwable t) {
					log.error("Failed to instantiate bot {}", bc.getName(), t);
					return null;
				}
			})
			.filter(Objects::nonNull)
			.toList();
}
