package io.github.pfwikis.bots.common.api.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import io.github.pfwikis.bots.common.api.generated.params.Interwiki;
import io.github.pfwikis.bots.common.api.generated.params.NS;

@ExtendWith(SoftAssertionsExtension.class)
class PageTitleTest {
	
	@InjectSoftAssertions
	private SoftAssertions softly;

	@ParameterizedTest
	@CsvSource(delimiter = ';', textBlock = """
		Simple;;MAIN;Simple;
		Project:Name;;PROJECT;Name;
		Name1:Name2;;MAIN;Name1:Name2;
		pfw:Simple;PFW;MAIN;Simple;
		Facts:Book#hash;;FACTS;Book;hash
		""")
	
	void test(String in, Interwiki expectedInterwiki, NS expectedNS, String expectedName, String expectedHash) {
		var p = PageTitle.of(in);
		softly.assertThat(p.getInterwiki()).isEqualTo(expectedInterwiki);
		softly.assertThat(p.getNs()).isEqualTo(expectedNS);
		softly.assertThat(p.getName()).isEqualTo(expectedName);
		softly.assertThat(p.getHash()).isEqualTo(expectedHash);
		softly.assertThat(p.toString()).isEqualTo(in);
	}

}
