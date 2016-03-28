package com.kennethnickles.gatherer.server;

import android.support.annotation.NonNull;
import com.kennethnickles.gatherer.Action;
import com.kennethnickles.gatherer.card.Card;
import com.kennethnickles.gatherer.card.Color;
import com.kennethnickles.gatherer.card.Type;
import com.kennethnickles.gatherer.parser.HtmlScraper;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
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
public final class GathererRequestClient {

    private final OkHttpClient okHttpClient = new OkHttpClient();

    public final ArrayList<Card> request(Builder builder) throws IOException {
        final Response response = okHttpClient.newCall(builder.build()).execute();
        return HtmlScraper.parse(response.body().string());
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private final Map<String, Set<String>> params = new HashMap<>();

        private static final String SEARCH_URL = "http://gatherer.wizards.com/Pages/Search/Default.aspx?";
        private static final String PARAM_ADD = "+";
        private static final String PARAM_OR = "|";
        private static final String PARAM_ASSIGN = "=";
        private static final String PARAM_AND = "&";
        private static final String PARAM_FORMAT = "[%s]";
        private static final String PARAM_EXCLUSIVE_START = "@(";
        private static final String PARAM_EXCLUSIVE_END = ")";

        private static final String ACTION_KEY = "action";
        private static final String NAME_KEY = "name";
        private static final String RULES_KEY = "rulesText";
        private static final String EXPANSION_KEY = "expansion";
        private static final String FORMAT_KEY = "format";
        private static final String COLOR_KEY = "color";
        private static final String TYPE_KEY = "type";
        private static final String SUB_TYPE_KEY = "subtype";

        final Request.Builder requestBuilder = new Request.Builder();

        private String mPath;
        private Action mAction;
        private boolean mAreColorsExclusive;

        private Builder() {
        }

        public Builder withAction(@NonNull String action) {
            this.mAction = Action.from(action);
            return this;
        }

        public Builder withAction(@NonNull Action action) {
            this.mAction = action;
            return this;
        }

        public Builder withName(@NonNull String name) {
            if (!params.containsKey(NAME_KEY)) {
                params.put(NAME_KEY, new HashSet<String>());
            }
            this.params.get(NAME_KEY).add(name);
            return this;
        }

        public Builder withRuleText(@NonNull String rule) {
            if (!params.containsKey(RULES_KEY)) {
                params.put(RULES_KEY, new HashSet<String>());
            }
            this.params.get(RULES_KEY).add(rule);
            return this;
        }

        public Builder withExpansion(@NonNull String expansion) {
            if (!params.containsKey(EXPANSION_KEY)) {
                params.put(EXPANSION_KEY, new HashSet<String>());
            }
            this.params.get(EXPANSION_KEY).add(expansion);
            return this;
        }

        public Builder withFormat(@NonNull String format) {
            if (!params.containsKey(FORMAT_KEY)) {
                params.put(FORMAT_KEY, new HashSet<String>());
            }
            this.params.get(FORMAT_KEY).add(format);
            return this;
        }

        public Builder withColor(@NonNull String color) {
            if (!params.containsKey(COLOR_KEY)) {
                params.put(COLOR_KEY, new HashSet<String>());
            }
            this.params.get(COLOR_KEY).add(color);
            return this;
        }

        public Builder withColor(@NonNull Color color) {
            if (!params.containsKey(COLOR_KEY)) {
                params.put(COLOR_KEY, new HashSet<String>());
            }
            this.params.get(COLOR_KEY).add(color.getName());
            return this;
        }

        public Builder withColors(@NonNull Set<Color> colors) {
            if (!params.containsKey(COLOR_KEY)) {
                params.put(COLOR_KEY, new HashSet<String>());
            }
            for (Color color : colors) {
                this.params.get(COLOR_KEY).add(color.getName());
            }
            return this;
        }

        public Builder areColorsExclusive(boolean areColorsExclusive) {
            this.mAreColorsExclusive = areColorsExclusive;
            return this;
        }

        public Builder withType(@NonNull String type) {
            if (!params.containsKey(TYPE_KEY)) {
                params.put(TYPE_KEY, new HashSet<String>());
            }
            this.params.get(TYPE_KEY).add(type);
            return this;
        }

        public Builder withType(@NonNull Type type) {
            if (!params.containsKey(TYPE_KEY)) {
                params.put(TYPE_KEY, new HashSet<String>());
            }
            this.params.get(TYPE_KEY).add(type.getName());
            return this;
        }

        public Builder withSubtype(@NonNull String subtype) {
            if (!params.containsKey(SUB_TYPE_KEY)) {
                params.put(SUB_TYPE_KEY, new HashSet<String>());
            }
            this.params.get(SUB_TYPE_KEY).add(subtype);
            return this;
        }

        public Builder toSearch() {
            this.mPath = SEARCH_URL;
            return this;
        }

        Request build() {
            final StringBuilder urlBuilder = new StringBuilder();
            urlBuilder.append(mPath);
            if (mAction != null) {
                urlBuilder.append(ACTION_KEY).append(PARAM_ASSIGN).append(mAction.getName()).append(PARAM_AND);
            }
            for (String paramKey : params.keySet()) {
                urlBuilder.append(paramKey).append(PARAM_ASSIGN);
                urlBuilder.append(PARAM_ADD);
                if (COLOR_KEY.equals(paramKey) && mAreColorsExclusive) {
                    urlBuilder.append(PARAM_EXCLUSIVE_START);
                }
                for (String param : params.get(paramKey)) {
                    urlBuilder.append(String.format(Locale.US, PARAM_FORMAT, param)).append(PARAM_ADD);
                }
                urlBuilder.deleteCharAt(urlBuilder.length() - 1);
                if (COLOR_KEY.equals(paramKey) && mAreColorsExclusive) {
                    urlBuilder.append(PARAM_EXCLUSIVE_END);
                }
                urlBuilder.append(PARAM_AND);
            }
            // http://gatherer.wizards.com/Pages/Search/Default.aspx?color=+@([U])&type=+[Legendary]
            return this.requestBuilder.url(urlBuilder.substring(0, urlBuilder.length() - 1)).build();
        }
    }
}
