package io.github.pfwikis.bots.meta;

import java.util.stream.Collectors;

import com.beust.jcommander.Parameters;

import io.github.classgraph.ClassGraph;
import io.github.pfwikis.bots.Runner;
import io.github.pfwikis.bots.common.bots.Bot;
import io.github.pfwikis.bots.common.bots.SimpleBot;

@Parameters
public class Meta extends SimpleBot {

	public Meta() {
		super("meta", "VirenerusBot");
	}
	
	@Override
	protected String getBotPassword() {
		return rootPassword;
	}
	
	@Override
	protected String getDescription() {
		var scan = new ClassGraph()
			.acceptPackages(Runner.class.getPackageName())
			.enableClassInfo()
			.scan();
		var bots = scan.getSubclasses(Bot.class)
			.stream()
			.filter(bc->!bc.isAbstract())
			.map(bc-> {
				try {
					return bc.loadClass(Bot.class)
						.getConstructor()
						.newInstance();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			})
			.map(b->"{{User:"+b.getBotName()+"/Status}}")
			.sorted()
			.collect(Collectors.joining("\n"));
				
		return """
		This bot is owned by [[User:Virenerus]]. It is a manager bot that is responsible for managing multiple thematic sub bots, you might know.

		==Sub bots==
		{| class="wikitable" style="margin:auto"
		|-
		! Bot Name !! Last run !! Status
		%s
		|}
		""".formatted(bots);
	}

	@Override
	public void run() throws Exception {}
}
