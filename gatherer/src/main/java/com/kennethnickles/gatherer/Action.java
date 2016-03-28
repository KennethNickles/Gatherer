package com.kennethnickles.gatherer;

import java.util.HashSet;
import java.util.Set;

/**
 * @author kenneth.nickles
 * @since 2016-03-08.
 */
public enum Action {
    ADVANCED("advanced");

    private final String mName;

    Action(String name) {
        this.mName = name;
    }

    public String getName() {
        return mName;
    }

    public static Action from(String lookup) {
        for (Action color : values()) {
            if (color.toString().equals(lookup)) {
                return color;
            }
        }
        return null;
    }

    public static Set<Action> from(Set<String> lookups) {
        final Set<Action> colors = new HashSet<>();
        for (String lookup : lookups) {
            final Action color = Action.from(lookup);
            if (color != null) {
                colors.add(color);
            }
        }
        return colors;
    }
}
