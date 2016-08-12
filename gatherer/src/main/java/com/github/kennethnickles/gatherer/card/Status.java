package com.github.kennethnickles.gatherer.card;

import android.support.annotation.NonNull;

/**
 * @author kenneth.nickles
 * @since 2016-08-11.
 */
public enum Status {
	LEGAL,
	BANNED,
	RESTRICTED;

	@NonNull
	public String getName() {
		return name().toLowerCase();
	}
}
