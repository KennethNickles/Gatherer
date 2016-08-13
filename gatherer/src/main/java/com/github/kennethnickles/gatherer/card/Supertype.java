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
 * @since 2016-04-04.
 */
public enum Supertype {

	BASIC,
	LEGENDARY,
	ONGOING,
	SNOW,
	WORLD;

	@NonNull
	public String getName() {
		return name().toLowerCase();
	}

	@Nullable
	public static Supertype from(String lookup) {
		if (Strings.isNullOrEmpty(lookup)) {
			return null;
		}
		for (Supertype supertype : values()) {
			if (Enums.sanitize(supertype.getName()).equals(Enums.sanitize(lookup))) {
				return supertype;
			}
		}
		Log.e(Supertype.class.getSimpleName(), String.format("Missing Supertype: %s", lookup));
		return null;
	}

	@Nullable
	public static List<Supertype> from(List<String> lookups) {
		if (lookups == null || lookups.isEmpty()) {
			return Lists.newArrayList();
		}
		final List<Supertype> types = Lists.newArrayList();
		for (String lookup : lookups) {
			types.add(from(lookup));
		}
		return types;
	}
}
