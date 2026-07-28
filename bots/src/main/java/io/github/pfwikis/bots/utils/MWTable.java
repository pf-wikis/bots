package io.github.pfwikis.bots.utils;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class MWTable {

	@SafeVarargs
	public static <T> String makeTable(Collection<T> elements, List<String> header, Function<T, Object>... elementToCellMappers) {
		var table = new StringBuilder()
				.append("{| class=\"wikitable sortable\"\n")
				.append("|-\n")
				.append("! ").append(String.join(" !! ", header));
			
			for(var e:elements) {
				table.append("\n|-\n| ");
				for(int i=0;i<elementToCellMappers.length;i++) {
					if(i>0) table.append(" || ");
					table.append(elementToCellMappers[i].apply(e));
				}
			}
			table.append("\n|}");
			return table.toString();
	}
}
