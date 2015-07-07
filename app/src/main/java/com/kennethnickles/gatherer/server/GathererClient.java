package com.kennethnickles.gatherer.server;

import android.net.Uri;
import com.kennethnickles.gatherer.card.Color;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.URL;

/**
 * @author kenneth.nickles
 * @since 2015-06-28.
 */
public final class GathererClient {

    private static final String GATHERER_URL = "http://gatherer.wizards.com/Pages/Search/Default.aspx";

    private final OkHttpClient okHttpClient = new OkHttpClient();

    public final String run(Builder builder) throws IOException {
        final Request request = new Request.Builder()
                .url(builder.toUrl())
                .get()
                .build();

        final Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }

    public static Builder builder() {
        return new Builder();
    }

    private static class Builder {
        private static final String NAME_KEY = "name";
        private static final String RULES_KEY = "rulesText";
        private static final String EXPANSION_KEY = "expansion";
        private static final String FORMAT_KEY = "format";
        private static final String COLOR_KEY = "color";

        final HttpUrl.Builder builder = new HttpUrl.Builder();

        private String name;
        private String rules;
        private String expansion;
        private String format;
        private Color color;

        private Builder() {
        }

        public Builder withName(String name) {
            this.builder.addQueryParameter(NAME_KEY, name);
            return this;
        }

        public Builder withRuleText(String rule) {
            this.builder.addQueryParameter(RULES_KEY, rule);
            return this;
        }

        public Builder withExpansion(String expansion) {
            this.builder.addQueryParameter(EXPANSION_KEY, expansion);
            return this;
        }

        public Builder withFormat(String format) {
            this.builder.addQueryParameter(FORMAT_KEY, format);
            return this;
        }

        public Builder withColor(String color) {
            this.builder.addQueryParameter(COLOR_KEY, color);
            return this;
        }

        public HttpUrl toUrl() {
            return this.builder.build();
        }
    }
}
