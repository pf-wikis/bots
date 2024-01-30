package io.github.pfwikis.bots.utils;

import com.fasterxml.jackson.databind.util.StdConverter;

public class MWJsonHelper {
	public static <T> T assumeNoneOrOne(T[] values) {
		if (values == null || values.length == 0)
			return null;
		if (values.length == 1)
			return values[0];
		else
			throw new IllegalStateException("More than one value unexpectedly");
	}

	public static class BooleanConverter extends StdConverter<String, Boolean> {

		@Override
		public Boolean convert(String value) {
			return switch (value) {
				case "t" -> Boolean.TRUE;
				case "f" -> Boolean.FALSE;
				default -> null;
			};
		}

	}
}
