package com.github.kennethnickles.gatherer.server;

import android.support.annotation.NonNull;
import com.github.kennethnickles.gatherer.card.Color;
import com.github.kennethnickles.gatherer.card.Type;
import com.squareup.okhttp.Request;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author kenneth.nickles
 * @since 2015-06-28.
 * <p/>
 * TODO: Parameter differences need to be tested such as "Odyssey" and "odyssey"
 */
public final class GathererRequest {

    private final Builder mState;

    private GathererRequest(Builder builder) {
        this.mState = builder;
    }

    public boolean areNamesExclusive() {
        return mState.mAreNamesExclusive;
    }

    public boolean areTypesExclusive() {
        return mState.mAreTypesExclusive;
    }

    public boolean areSupertypesExclusive() {
        return mState.mAreSupertypesExclusive;
    }

    public boolean areSubtypesExclusive() {
        return mState.mAreSubtypesExclusive;
    }

    public boolean areColorsExclusive() {
        return mState.mAreColorsExclusive;
    }

    @NonNull
    public Map<String, Set<String>> getParams() {
        return mState.mParams;
    }

    @NonNull
    public Map<String, String> getDeckBrewParams() {
        return new DeckBrewParamBuilder().getParams(this);
    }

    @NonNull
    public Map<String, String> getGathererParams() {
        return new GathererParamBuilder().getParams(this);
    }

    @NonNull
    public Request toDeckBrewRequest() {
        final DeckBrewParamBuilder deckBrewParamBuilder = new DeckBrewParamBuilder();
        final StringBuilder urlBuilder = new StringBuilder();
        final Map<String, String> params = deckBrewParamBuilder.getParams(this);
        for (String key : params.keySet()) {
            urlBuilder.append(key).append(ParamConstants.PARAM_ASSIGN);
            urlBuilder.append(ParamConstants.PARAM_ADD);
            if (ParamConstants.NAME_KEY.equals(key)) {
                urlBuilder.append(params.get(key));
            } else if (ParamConstants.TYPE_KEY.equals(key)) {
                urlBuilder.append(params.get(key));
            } else if (ParamConstants.SUPERTYPE_KEY.equals(key)) {
                urlBuilder.append(params.get(key));
            } else if (ParamConstants.SUBTYPE_KEY.equals(key)) {
                urlBuilder.append(params.get(key));
            } else if (ParamConstants.COLOR_KEY.equals(key)) {
                urlBuilder.append(params.get(key));
            }
        }
        // http://gatherer.wizards.com/Pages/Search/Default.aspx?color=+@([U])&type=+[Legendary]
        return new Request.Builder().url(urlBuilder.substring(0, urlBuilder.length() - 1)).build();
    }

    @NonNull
    public Request toGathererRequest() {
        final GathererParamBuilder gathererParamBuilder = new GathererParamBuilder();
        final StringBuilder urlBuilder = new StringBuilder();
        final Map<String, String> params = gathererParamBuilder.getParams(this);
        for (String key : params.keySet()) {
            urlBuilder.append(key).append(ParamConstants.PARAM_ASSIGN);
            urlBuilder.append(ParamConstants.PARAM_ADD);
            if (ParamConstants.NAME_KEY.equals(key)) {
                urlBuilder.append(params.get(key));
            } else if (ParamConstants.TYPE_KEY.equals(key)) {
                urlBuilder.append(params.get(key));
            } else if (ParamConstants.SUPERTYPE_KEY.equals(key)) {
                urlBuilder.append(params.get(key));
            } else if (ParamConstants.SUBTYPE_KEY.equals(key)) {
                urlBuilder.append(params.get(key));
            } else if (ParamConstants.COLOR_KEY.equals(key)) {
                urlBuilder.append(params.get(key));
            }
        }
        // http://gatherer.wizards.com/Pages/Search/Default.aspx?color=+@([U])&type=+[Legendary]
        return new Request.Builder().url(urlBuilder.substring(0, urlBuilder.length() - 1)).build();
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private final Map<String, Set<String>> mParams = new HashMap<>();

        final Request.Builder requestBuilder = new Request.Builder();

        private boolean mAreNamesExclusive;
        private boolean mAreTypesExclusive;
        private boolean mAreSupertypesExclusive;
        private boolean mAreSubtypesExclusive;
        private boolean mAreColorsExclusive;

        private Builder() {
        }

        public Builder withName(@NonNull String name) {
            if (!mParams.containsKey(ParamConstants.NAME_KEY)) {
                mParams.put(ParamConstants.NAME_KEY, new HashSet<String>());
            }
            this.mParams.get(ParamConstants.NAME_KEY).add(name);
            return this;
        }

        public Builder withRule(@NonNull String rule) {
            if (!mParams.containsKey(ParamConstants.RULES_KEY)) {
                mParams.put(ParamConstants.RULES_KEY, new HashSet<String>());
            }
            this.mParams.get(ParamConstants.RULES_KEY).add(rule);
            return this;
        }

        public Builder withExpansion(@NonNull String expansion) {
            if (!mParams.containsKey(ParamConstants.EXPANSION_KEY)) {
                mParams.put(ParamConstants.EXPANSION_KEY, new HashSet<String>());
            }
            this.mParams.get(ParamConstants.EXPANSION_KEY).add(expansion);
            return this;
        }

        public Builder withFormat(@NonNull String format) {
            if (!mParams.containsKey(ParamConstants.FORMAT_KEY)) {
                mParams.put(ParamConstants.FORMAT_KEY, new HashSet<String>());
            }
            this.mParams.get(ParamConstants.FORMAT_KEY).add(format);
            return this;
        }

        public Builder withColor(@NonNull String color) {
            if (!mParams.containsKey(ParamConstants.COLOR_KEY)) {
                mParams.put(ParamConstants.COLOR_KEY, new HashSet<String>());
            }
            this.mParams.get(ParamConstants.COLOR_KEY).add(color);
            return this;
        }

        public Builder withColor(@NonNull Color color) {
            if (!mParams.containsKey(ParamConstants.COLOR_KEY)) {
                mParams.put(ParamConstants.COLOR_KEY, new HashSet<String>());
            }
            this.mParams.get(ParamConstants.COLOR_KEY).add(color.getName());
            return this;
        }

        public Builder withColors(@NonNull Set<Color> colors) {
            if (!mParams.containsKey(ParamConstants.COLOR_KEY)) {
                mParams.put(ParamConstants.COLOR_KEY, new HashSet<String>());
            }
            for (Color color : colors) {
                this.mParams.get(ParamConstants.COLOR_KEY).add(color.getName());
            }
            return this;
        }

        public Builder areColorsExclusive(boolean areColorsExclusive) {
            this.mAreColorsExclusive = areColorsExclusive;
            return this;
        }

        public Builder withType(@NonNull String type) {
            if (!mParams.containsKey(ParamConstants.TYPE_KEY)) {
                mParams.put(ParamConstants.TYPE_KEY, new HashSet<String>());
            }
            this.mParams.get(ParamConstants.TYPE_KEY).add(type);
            return this;
        }

        public Builder withType(@NonNull Type type) {
            if (!mParams.containsKey(ParamConstants.TYPE_KEY)) {
                mParams.put(ParamConstants.TYPE_KEY, new HashSet<String>());
            }
            this.mParams.get(ParamConstants.TYPE_KEY).add(type.name());
            return this;
        }

        public Builder withSubtype(@NonNull String subtype) {
            if (!mParams.containsKey(ParamConstants.SUBTYPE_KEY)) {
                mParams.put(ParamConstants.SUBTYPE_KEY, new HashSet<String>());
            }
            this.mParams.get(ParamConstants.SUBTYPE_KEY).add(subtype);
            return this;
        }

        @NonNull
        public GathererRequest build() {
            return new GathererRequest(this);
        }
    }
}
