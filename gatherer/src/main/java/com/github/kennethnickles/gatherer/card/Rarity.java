package com.github.kennethnickles.gatherer.card;

import android.support.annotation.Nullable;
import android.util.Log;
import com.github.kennethnickles.gatherer.util.Enums;
import com.github.kennethnickles.gatherer.util.Lists;
import com.github.kennethnickles.gatherer.util.Strings;
import java.util.List;

/**
 * @author kenneth.nickles
 * @since 2015-06-28.
 */
public enum Rarity {

	BASIC,
	COMMON,
	UNCOMMON,
	MYTHIC,
	RARE,
	SPECIAL;

	@Nullable
	public static Rarity from(String lookup) {
		if (Strings.isNullOrEmpty(lookup)) {
			return null;
		}
		for (Rarity rarity : values()) {
			if (rarity.name().equals(Enums.sanitize(lookup))) {
				return rarity;
			}
		}
		Log.e(Rarity.class.getSimpleName(), String.format("Missing Rarity: %s", lookup));
		return null;
	}

	@Nullable
	public static List<Rarity> from(List<String> lookups) {
		if (lookups == null || lookups.isEmpty()) {
			return Lists.newArrayList();
		}
		final List<Rarity> types = Lists.newArrayList();
		for (String lookup : lookups) {
			types.add(from(lookup));
		}
		return types;
	}
}
