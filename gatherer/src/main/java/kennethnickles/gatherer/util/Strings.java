package kennethnickles.gatherer.util;

import android.support.annotation.Nullable;

/**
 * @author kenneth.nickles
 * @since 2016-03-29.
 */
public class Strings {

    private Strings() {
    }

    public static boolean isNullOrEmpty(@Nullable String string) {
        return string == null || string.isEmpty();
    }
}
