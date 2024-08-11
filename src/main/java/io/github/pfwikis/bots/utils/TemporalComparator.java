package io.github.pfwikis.bots.utils;

import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.util.Comparator;

public enum TemporalComparator implements Comparator<Temporal> {
	
	INSTANCE;

	private static final ChronoField[] FIELDS = {
		ChronoField.YEAR,
		ChronoField.MONTH_OF_YEAR,
		ChronoField.DAY_OF_MONTH,
		ChronoField.HOUR_OF_DAY,
		ChronoField.MINUTE_OF_HOUR,
		ChronoField.SECOND_OF_MINUTE
	};
	
	@Override
	public int compare(Temporal a, Temporal b) {
		for(var field: FIELDS) {
			if(a.isSupported(field)) {
				if(b.isSupported(field)) {
					var result = a.get(field) - b.get(field);
					if(result != 0)
						return result;
				}
				else {
					return -1;
				}
			}
			else {
				if(b.isSupported(field)) {
					return 1;
				}
				else {
					return 0;
				}
			}
		}
		return 0;
	}

}
