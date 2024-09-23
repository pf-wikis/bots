package io.github.pfwikis.bots.facts.citetemplates;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;

public class RangeHelper {

	public static RangeSet<Integer> toHasValueRanges(Map<String, List<Range<Integer>>> ranges) {
		var result = TreeRangeSet.<Integer>create();
		for(var e:ranges.entrySet()) {
			if(StringUtils.isNotBlank(e.getKey())) {
				result.addAll(e.getValue());
			}
		}
		return result;
	}

}
