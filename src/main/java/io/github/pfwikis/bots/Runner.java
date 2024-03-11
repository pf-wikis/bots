package io.github.pfwikis.bots;

import java.util.List;

import com.beust.jcommander.JCommander;

import io.github.classgraph.ClassGraph;
import io.github.pfwikis.bots.common.bots.Bot;
import lombok.Getter;

public class Runner {

	public static void main(String[] args) throws Exception {
		var commands = JCommander.newBuilder();
		for(var bot : Runner.getAllBots()) {
			commands.addCommand(bot.getId(), bot);
		}
		
		var commander = commands.build();
		commander.parse(args);
		
		if(commander.getParsedCommand() == null) {
			commander.usage();
			System.exit(-1);
		}
		
		var bot = (Bot<?>)commander.getCommands().get(commander.getParsedCommand())
			.getObjects().get(0);
		bot.start();
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
