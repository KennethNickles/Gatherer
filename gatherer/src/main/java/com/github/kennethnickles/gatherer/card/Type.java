package com.github.kennethnickles.gatherer.card;

import android.support.annotation.NonNull;
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
public enum Type {

	ARTIFACT,
	CONSPIRACY,
	CREATURE,
	ENCHANTMENT,
	INSTANT,
	LAND,
	PHENOMENON,
	PLANE,
	PLANESWALKER,
	SCHEME,
	SORCERY,
	TRIBAL,
	VANGUARD,
	// UNHIGNED TYPES
	EATURECRAY,
	ENCHANT,
	PLAYER;

	@NonNull
	public String getName() {
		return name().toLowerCase();
	}

	@Nullable
	public static Type from(@Nullable String lookup) {
		if (Strings.isNullOrEmpty(lookup)) {
			return null;
		}
		for (Type type : values()) {
			if (type.getName().equals(Enums.sanitize(lookup))) {
				return type;
			}
		}
		Log.e(Type.class.getSimpleName(), String.format("Missing Type: %s", lookup));
		return null;
	}

	@Nullable
	public static List<Type> from(List<String> lookups) {
		if (lookups == null || lookups.isEmpty()) {
			return Lists.newArrayList();
		}
		final List<Type> types = Lists.newArrayList();
		for (String lookup : lookups) {
			types.add(from(lookup));
		}
		return types;
	}
}
