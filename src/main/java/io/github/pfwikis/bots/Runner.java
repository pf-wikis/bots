package io.github.pfwikis.bots;

import java.lang.reflect.InvocationTargetException;

import com.beust.jcommander.JCommander;

import io.github.classgraph.ClassGraph;
import io.github.pfwikis.bots.common.bots.Bot;

public class Runner {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		var commands = JCommander.newBuilder();
		var scan = new ClassGraph()
			.acceptPackages(Runner.class.getPackageName())
			.enableClassInfo()
			.scan();
		for(var botClass : scan.getSubclasses(Bot.class)) {
			if(botClass.isAbstract()) continue;
			
			var bot = botClass.loadClass(Bot.class).getConstructor().newInstance();
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
	}
}
