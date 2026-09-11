package io.github.pfwikis.bots.utils;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.fizzed.rocker.RockerContent;

public class WikitextHelper {

	public static String ifElse(String cond, String ifTrue, String ifFalse) {
		cond = Objects.requireNonNull(StringUtils.trimToNull(cond));
		ifTrue = Objects.requireNonNull(StringUtils.trimToNull(ifTrue));
		ifFalse = StringUtils.trimToNull(ifFalse);
		
		
		var sb = new StringBuilder();
		if(cond.equals(ifTrue))
			sb.append("{{#coalesce:");
		else
			sb.append("{{#if:").append(cond).append("|");
		sb.append(ifTrue);
		if(ifFalse != null) {
			sb.append("|").append(ifFalse);
		}
		return sb.append("}}").toString();
	}

	public static String ifElse(String cond, String ifTrue, String prefix, String suffix, String ifFalse) {
		cond = Objects.requireNonNull(StringUtils.trimToNull(cond));
		ifTrue = Objects.requireNonNull(StringUtils.trimToNull(ifTrue));
		ifFalse = StringUtils.trimToNull(ifFalse);
		prefix = StringUtils.trimToNull(prefix);
		suffix = StringUtils.trimToNull(suffix);
		
		boolean s = cond.equals(ifTrue);
		
		if(s) {
			return "{{#ifContent:%s|%s|%s|%s}}".formatted(
		        ifTrue,
		        Objects.requireNonNullElse(prefix, ""),
		        Objects.requireNonNullElse(suffix, ""),
		        Objects.requireNonNullElse(ifFalse, "")
		    ).replaceAll("\\|+\\}\\}$", "}}");
		}
		else {
			return "{{#if:%s|%s%s%s|%s}}".formatted(
		        cond,
		        Objects.requireNonNullElse(prefix, ""),
		        ifTrue,
		        Objects.requireNonNullElse(suffix, ""),
		        Objects.requireNonNullElse(ifFalse, "")
		    ).replaceAll("\\|+\\}\\}$", "}}");
		}
	}
}
