package io.github.pfwikis.bots.facts;

import java.util.List;

import io.github.pfwikis.bots.facts.model.SFactType;

public class Test {
	public static void main(String[] args) {
		new SFactType<List<String>>(null, null, null, null) {}
			.getJavaType();
	}
}
