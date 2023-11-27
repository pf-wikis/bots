package io.github.pfwikis.bots.usagereporter;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import io.github.pfwikis.bots.common.WikiAPI;
import io.github.pfwikis.bots.common.model.Page;

public class CategoryResolver {
	
	public static Multiset<String> resolve(WikiAPI wiki, List<MatomoEvent> categories) {
		var parentCache = new HashMap<String, List<String>>();
		Multiset<String> categoryVisits = HashMultiset.create();
		
		for(var cat:categories) {
			var closed = new HashSet<String>();
			var open = new ArrayDeque<String>();
			open.add("Category:"+cat.getLabel());
			while(!open.isEmpty()) {
				var current = open.pop();
				if(!closed.add(current)) continue;
				
				categoryVisits.add(current, cat.getVisits());
				open.addAll(getParents(wiki, parentCache, current));
			}
		}
		
		return categoryVisits;
	}

	private static final Set<String> SKIP = Set.of(
		"Category:PathfinderWiki",
		"Category:Meta",
		"Category:Categories",
		"Category:PathfinderWiki maintenance",
		"Hidden categories"
	);
	private static Collection<String> getParents(WikiAPI wiki, Map<String, List<String>> parentCache, String cat) {
		if(SKIP.contains(cat))
			return Collections.emptyList();
		var parents = parentCache.get(cat);
		if(parents == null) {
			parents = wiki.getCategories(cat).stream().map(Page::getTitle).filter(p->!SKIP.contains(p)).toList();
			parentCache.put(cat, parents);
		}
		return parents;
	}
}
