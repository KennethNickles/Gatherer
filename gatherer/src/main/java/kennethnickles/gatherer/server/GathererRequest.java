package kennethnickles.gatherer.server;

import android.support.annotation.NonNull;

import kennethnickles.gatherer.card.Color;
import kennethnickles.gatherer.card.Type;
import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * @author kenneth.nickles
 * @since 2015-06-28.
 * <p/>
 * TODO: Parameter differences need to be tested such as "Odyssey" and "odyssey"
 */
public final class GathererRequest {

    private static final String PARAM_ADD = "+";
    private static final String PARAM_OR = "|";
    private static final String PARAM_ASSIGN = "=";
    private static final String PARAM_AND = "&";
    private static final String PARAM_FORMAT = "[%s]";
    private static final String PARAM_EXCLUSIVE = "@(%s)";

    private static final String NAME_KEY = "name";
    private static final String RULES_KEY = "rulesText";
    private static final String EXPANSION_KEY = "expansion";
    private static final String FORMAT_KEY = "format";
    private static final String COLOR_KEY = "color";
    private static final String TYPE_KEY = "type";
    private static final String SUPERTYPE_KEY = "supertype";
    private static final String SUBTYPE_KEY = "subtype";

    private final Builder mState;

    private GathererRequest(Builder builder) {
        this.mState = builder;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Map<String, String> getParams() {
        final Map<String, String> map = new HashMap<>();
        for (String key : mState.mParams.keySet()) {
            if (NAME_KEY.equals(key)) {
                map.put(key, buildNameParams(mState.mParams.get(key)));
            } else if (TYPE_KEY.equals(key)) {
                map.put(key, buildTypeParams(mState.mAreTypesExclusive, mState.mParams.get(key)));
            } else if (SUPERTYPE_KEY.equals(key)) {
                map.put(key, buildSupertypeParams(mState.mAreSupertypesExclusive, mState.mParams.get(key)));
            } else if (SUBTYPE_KEY.equals(key)) {
                map.put(key, buildSubtypeParams(mState.mAreSubtypesExclusive, mState.mParams.get(key)));
            } else if (COLOR_KEY.equals(key)) {
                map.put(key, buildColorParams(mState.mAreColorsExclusive, mState.mParams.get(key)));
            }
        }
        return map;
    }

    private static String buildNameParams(Set<String> names) {
        final StringBuilder builder = new StringBuilder();
        builder.append(getNameParams(names));
        return builder.toString();
    }

    private static String getNameParams(Set<String> names) {
        final StringBuilder builder = new StringBuilder();
        for (String name : names) {
            builder.append(String.format(Locale.US, PARAM_FORMAT, name)).append(PARAM_ADD);
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    private static String buildTypeParams(boolean isParamExclusive, Set<String> types) {
        final StringBuilder builder = new StringBuilder();
        if (isParamExclusive) {
            builder.append(String.format(Locale.US, PARAM_EXCLUSIVE, getColorParams(types)));
        } else {
            builder.append(getTypeParams(types));
        }
        return builder.toString();
    }

    private static String getTypeParams(Set<String> types) {
        final StringBuilder builder = new StringBuilder();
        for (String name : types) {
            builder.append(String.format(Locale.US, PARAM_FORMAT, name)).append(PARAM_ADD);
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    private static String buildSupertypeParams(boolean isParamExclusive, Set<String> supertypes) {
        final StringBuilder builder = new StringBuilder();
        if (isParamExclusive) {
            builder.append(String.format(Locale.US, PARAM_EXCLUSIVE, getColorParams(supertypes)));
        } else {
            builder.append(getSupertypeParams(supertypes));
        }
        return builder.toString();
    }

    private static String getSupertypeParams(Set<String> supertypes) {
        final StringBuilder builder = new StringBuilder();
        for (String name : supertypes) {
            builder.append(String.format(Locale.US, PARAM_FORMAT, name)).append(PARAM_ADD);
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    private static String buildSubtypeParams(boolean isParamExclusive, Set<String> subtypes) {
        final StringBuilder builder = new StringBuilder();
        if (isParamExclusive) {
            builder.append(String.format(Locale.US, PARAM_EXCLUSIVE, getColorParams(subtypes)));
        } else {
            builder.append(getSubtypeParams(subtypes));
        }
        return builder.toString();
    }

    private static String getSubtypeParams(Set<String> subtypes) {
        final StringBuilder builder = new StringBuilder();
        for (String name : subtypes) {
            builder.append(String.format(Locale.US, PARAM_FORMAT, name)).append(PARAM_ADD);
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    private static String buildColorParams(boolean isParamExclusive, Set<String> colors) {
        final StringBuilder builder = new StringBuilder();
        if (isParamExclusive) {
            builder.append(String.format(Locale.US, PARAM_EXCLUSIVE, getColorParams(colors)));
        } else {
            builder.append(getColorParams(colors));
        }
        return builder.toString();
    }

    private static String getColorParams(Set<String> colors) {
        final StringBuilder builder = new StringBuilder();
        for (String color : colors) {
            builder.append(String.format(Locale.US, PARAM_FORMAT, color)).append(PARAM_ADD);
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    public static class Builder {

        private final Map<String, Set<String>> mParams = new HashMap<>();

        final Request.Builder requestBuilder = new Request.Builder();

        private boolean mAreTypesExclusive;
        private boolean mAreSupertypesExclusive;
        private boolean mAreSubtypesExclusive;
        private boolean mAreColorsExclusive;

        private Builder() {
        }

        public Builder withName(@NonNull String name) {
            if (!mParams.containsKey(NAME_KEY)) {
                mParams.put(NAME_KEY, new HashSet<String>());
            }
            this.mParams.get(NAME_KEY).add(name);
            return this;
        }

        public Builder withRule(@NonNull String rule) {
            if (!mParams.containsKey(RULES_KEY)) {
                mParams.put(RULES_KEY, new HashSet<String>());
            }
            this.mParams.get(RULES_KEY).add(rule);
            return this;
        }

        public Builder withExpansion(@NonNull String expansion) {
            if (!mParams.containsKey(EXPANSION_KEY)) {
                mParams.put(EXPANSION_KEY, new HashSet<String>());
            }
            this.mParams.get(EXPANSION_KEY).add(expansion);
            return this;
        }

        public Builder withFormat(@NonNull String format) {
            if (!mParams.containsKey(FORMAT_KEY)) {
                mParams.put(FORMAT_KEY, new HashSet<String>());
            }
            this.mParams.get(FORMAT_KEY).add(format);
            return this;
        }

        public Builder withColor(@NonNull String color) {
            if (!mParams.containsKey(COLOR_KEY)) {
                mParams.put(COLOR_KEY, new HashSet<String>());
            }
            this.mParams.get(COLOR_KEY).add(color);
            return this;
        }

        public Builder withColor(@NonNull Color color) {
            if (!mParams.containsKey(COLOR_KEY)) {
                mParams.put(COLOR_KEY, new HashSet<String>());
            }
            this.mParams.get(COLOR_KEY).add(color.getName());
            return this;
        }

        public Builder withColors(@NonNull Set<Color> colors) {
            if (!mParams.containsKey(COLOR_KEY)) {
                mParams.put(COLOR_KEY, new HashSet<String>());
            }
            for (Color color : colors) {
                this.mParams.get(COLOR_KEY).add(color.getName());
            }
            return this;
        }

        public Builder areColorsExclusive(boolean areColorsExclusive) {
            this.mAreColorsExclusive = areColorsExclusive;
            return this;
        }

        public Builder withType(@NonNull String type) {
            if (!mParams.containsKey(TYPE_KEY)) {
                mParams.put(TYPE_KEY, new HashSet<String>());
            }
            this.mParams.get(TYPE_KEY).add(type);
            return this;
        }

        public Builder withType(@NonNull Type type) {
            if (!mParams.containsKey(TYPE_KEY)) {
                mParams.put(TYPE_KEY, new HashSet<String>());
            }
            this.mParams.get(TYPE_KEY).add(type.name());
            return this;
        }

        public Builder withSubtype(@NonNull String subtype) {
            if (!mParams.containsKey(SUBTYPE_KEY)) {
                mParams.put(SUBTYPE_KEY, new HashSet<String>());
            }
            this.mParams.get(SUBTYPE_KEY).add(subtype);
            return this;
        }

        public Request build() {
            final StringBuilder urlBuilder = new StringBuilder();
            for (String key : mParams.keySet()) {
                urlBuilder.append(key).append(PARAM_ASSIGN);
                urlBuilder.append(PARAM_ADD);
                if (NAME_KEY.equals(key)) {
                    urlBuilder.append(buildNameParams(mParams.get(key)));
                } else if (TYPE_KEY.equals(key)) {
                    urlBuilder.append(buildTypeParams(mAreTypesExclusive, mParams.get(key)));
                } else if (SUPERTYPE_KEY.equals(key)) {
                    urlBuilder.append(buildSupertypeParams(mAreSupertypesExclusive, mParams.get(key)));
                } else if (SUBTYPE_KEY.equals(key)) {
                    urlBuilder.append(buildSubtypeParams(mAreSubtypesExclusive, mParams.get(key)));
                } else if (COLOR_KEY.equals(key)) {
                    urlBuilder.append(buildColorParams(mAreColorsExclusive, mParams.get(key)));
                }
            }
            // http://gatherer.wizards.com/Pages/Search/Default.aspx?color=+@([U])&type=+[Legendary]
            return this.requestBuilder.url(urlBuilder.substring(0, urlBuilder.length() - 1)).build();
        }
    }
}
