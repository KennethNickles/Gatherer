package com.kennethnickles.gatherer.card;

import android.support.annotation.Nullable;

import com.kennethnickles.gatherer.util.Enums;
import com.kennethnickles.gatherer.util.Strings;

/**
 * @author kenneth.nickles
 * @since 2015-06-28.
 */
public enum Mark {

    NONE("None");

    private final String mName;

    Mark(String name) {
        this.mName = name;
    }

    @Nullable
    public static Mark from(String lookup) {
        if (Strings.isNullOrEmpty(lookup)) {
            return null;
        }
        for (Mark mark : values()) {
            if (Enums.sanitize(mark.name()).equals(Enums.sanitize(lookup))) {
                return mark;
            }
        }
        return null;
    }

}
