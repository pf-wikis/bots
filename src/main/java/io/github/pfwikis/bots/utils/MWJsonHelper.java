package io.github.pfwikis.bots.utils;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.google.common.primitives.Ints;

public class MWJsonHelper {
	public static <T> T assumeNoneOrOne(T[] values) {
		if (values == null || values.length == 0)
			return null;
		if (values.length == 1)
			return values[0];
		else
			throw new IllegalStateException("More than one value unexpectedly");
	}
	
	public static Integer tryParseInt(String value) {
		if(value == null || value.isEmpty()) return null;
		return Ints.tryParse(value);
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
