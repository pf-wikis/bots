package io.github.pfwikis.bots.facts;
import java.lang.reflect.Modifier;
import java.time.temporal.Temporal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import io.github.pfwikis.bots.common.model.subject.PageRef;
import io.github.pfwikis.bots.facts.model.SConcept;
import io.github.pfwikis.bots.facts.model.SFactTypes;
import io.github.pfwikis.bots.facts.model.SProperty;

public class SUtilProperties {
	public static final SProperty<PageRef> Ordered_value = new SProperty<>(
			"Ordered value",
			SFactTypes.PAGE)
			.setDescription("The value of an ordered list entry.");
	public static final SProperty<Integer> Order = new SProperty<>(
			"Order",
			SFactTypes.INTEGER)
			.setDescription("The order value of an ordered list entry.");

	private static final Map<String, SProperty<?>> ALL_PROPERTIES;
	static {
		ALL_PROPERTIES = new HashMap<>();
		for(var f:SUtilProperties.class.getFields()) {
			if(Modifier.isStatic(f.getModifiers()) && SProperty.class.isAssignableFrom(f.getType())) {
				try {
					var p = (SProperty<?>) f.get(null);
					ALL_PROPERTIES.put(p.getName(), p);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new IllegalStateException("Could not collection property "+f, e);
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> SProperty<T> get(String name) {
		return (SProperty<T>) ALL_PROPERTIES.get(name);
	}
	
	public static List<SProperty<?>> getAll() {
		return ALL_PROPERTIES.values()
			.stream()
			.sorted(Comparator.comparing(p->p.getName()))
			.toList();
	}
}
