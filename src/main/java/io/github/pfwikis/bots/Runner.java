package io.github.pfwikis.bots;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.MissingCommandException;

import io.github.classgraph.ClassGraph;
import io.github.pfwikis.bots.common.bots.Bot;
import io.github.pfwikis.bots.scheduler.Scheduler;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Runner {

	public static void main(String[] args) throws Exception {
		var commands = JCommander.newBuilder();
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
		
		var bot = (Bot<?>)commander.getCommands().get(commander.getParsedCommand())
			.getObjects().get(0);
		bot.startSingleInstance();
		if(bot.isHadError()) {
			System.exit(-1);
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
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			})
			.toList();
}
