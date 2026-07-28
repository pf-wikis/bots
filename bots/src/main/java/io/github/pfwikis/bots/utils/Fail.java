package io.github.pfwikis.bots.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Fail {
	public static String error(String msg, Object... params) {
		log.error(msg, params);
		return "";
	}
}
