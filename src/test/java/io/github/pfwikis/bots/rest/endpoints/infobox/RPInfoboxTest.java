package io.github.pfwikis.bots.rest.endpoints.infobox;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.FieldSource;

import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.common.WikiAPI;
import io.github.pfwikis.bots.common.bots.Run.SingleRun;
import io.github.pfwikis.bots.rest.RestProviderBot;
import io.github.pfwikis.bots.utils.Jackson;

class RPInfoboxTest {
	
	static final Supplier<List<Arguments>> testInfoboxCreation = () -> {
		var l = new ArrayList<Arguments>();
		for(var f:new File("test-files/infobox").listFiles()) {
			if(f.getName().endsWith(".in.json")) {
				l.add(Arguments.of(
						f,
						new File(f.getParentFile(), f.getName().replace(".in.", ".expected."))
				));
			}
		}
		return l;
	};
		

	@ParameterizedTest
	@FieldSource
	void testInfoboxCreation(File input, File expected) throws Exception {
		var param = Jackson.JSON.readValue(input, RPInfoboxParam.class);
		assertThat(param.validate()).isTrue();
		var endpoint = new RPInfobox();
		
		var run = mock(SingleRun.class);
		var bot = mock(RestProviderBot.class);
		when(bot.getWiki()).thenReturn(Wiki.PF);
		when(bot.getRun()).thenReturn(run);
		when(run.getServer()).thenReturn(Wiki.PF);
		when(run.getWiki()).thenReturn(WikiAPI.create(Wiki.PF, null, null, null));
		
		var result = endpoint.generateResult(bot, param);
		assertThat(result).isEqualTo(Files.readString(expected.toPath()));
	}

}
