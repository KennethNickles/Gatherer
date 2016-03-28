package com.kennethnickles.gatherer.card;

import android.support.annotation.Nullable;
import com.google.common.base.Strings;
import com.kennethnickles.gatherer.util.EnumUtils;

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
            if (EnumUtils.sanitize(mark.name()).equals(EnumUtils.sanitize(lookup))) {
                return mark;
            }
        }
        return null;
    }

}
