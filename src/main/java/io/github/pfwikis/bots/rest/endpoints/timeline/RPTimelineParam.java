package io.github.pfwikis.bots.rest.endpoints.timeline;

import com.google.common.base.CharMatcher;

import static com.google.common.base.CharMatcher.inRange;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;

@Data
public class RPTimelineParam {
	private final static CharMatcher VALID = inRange('a', 'z')
			.or(inRange('A', 'Z'))
			.precomputed();
	
	private String tag;
	
	public boolean validate() {
		return StringUtils.isNotBlank(tag) && VALID.matchesAllOf(tag);
	}
}
