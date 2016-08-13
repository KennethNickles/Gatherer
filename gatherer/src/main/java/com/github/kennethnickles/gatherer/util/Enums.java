package com.github.kennethnickles.gatherer.util;

import java.util.Map;

/**
 * @author kenneth.nickles
 * @since 2015-08-02.
 */
public class Enums {

	public static String sanitize(String name) {
		return name.toLowerCase()
				   .replace(" ", "_")
				   .replace("-", "_")
				   .replace("'", "")
				   .replace("{", "")
				   .replace("}", "");
	}

	public static <E extends Enum<E>> void assertFullMappingKeys(Class<E> clazz, Map<E, ?> map) {
		for (Enum key : clazz.getEnumConstants()) {
			if (!map.containsKey(key)) {
				throw new IllegalArgumentException("Map does not contain a full mapping. missing: " + key.name());
			}
		}
	}

	public static <E extends Enum<E>> void assertFullMappingValues(Class<E> clazz, Map<?, E> map) {
		for (Enum value : clazz.getEnumConstants()) {
			if (!map.containsValue(value)) {
				throw new IllegalArgumentException("Map does not contain a full mapping. missing: " + value.name());
			}
		}
	}
}
