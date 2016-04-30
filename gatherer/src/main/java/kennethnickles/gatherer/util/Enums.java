package kennethnickles.gatherer.util;

/**
 * @author kenneth.nickles
 * @since 2015-08-02.
 */
public class Enums {

    public static String sanitize(String name) {
        return name.toUpperCase()
                   .replace(" ", "_")
                   .replace("-", "_")
                   .replace("'", "")
                   .replace("{", "")
                   .replace("}", "");
    }
}
