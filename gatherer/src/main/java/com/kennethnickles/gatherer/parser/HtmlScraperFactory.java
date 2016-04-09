package com.kennethnickles.gatherer.parser;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @author kenneth.nickles
 * @since 2016-04-08.
 */
public class HtmlScraperFactory extends Converter.Factory {

    public static HtmlScraperFactory create() {
        return new HtmlScraperFactory();
    }

    private HtmlScraperFactory() {
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type,
                                                            Annotation[] annotations,
                                                            Retrofit retrofit) {
        return super.responseBodyConverter(type, annotations, retrofit);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations,
                                                          Annotation[] methodAnnotations,
                                                          Retrofit retrofit) {
        return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
    }

    @Override
    public Converter<?, String> stringConverter(Type type,
                                                Annotation[] annotations,
                                                Retrofit retrofit) {
        return super.stringConverter(type, annotations, retrofit);
    }
}
