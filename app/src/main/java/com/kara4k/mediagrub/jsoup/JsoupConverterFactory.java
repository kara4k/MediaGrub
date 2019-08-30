package com.kara4k.mediagrub.jsoup;

import com.kara4k.mediagrub.jsoup.converter.ResponseBodyConverter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class JsoupConverterFactory extends Converter.Factory {

    public static JsoupConverterFactory create(){
        return new JsoupConverterFactory();
    }

    public JsoupConverterFactory() {
        super();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(final Type type, final Annotation[] annotations, final Retrofit retrofit) {
        return new ResponseBodyConverter();
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(final Type type, final Annotation[] parameterAnnotations, final Annotation[] methodAnnotations, final Retrofit retrofit) {
        return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
    }

    @Override
    public Converter<?, String> stringConverter(final Type type, final Annotation[] annotations, final Retrofit retrofit) {
        return super.stringConverter(type, annotations, retrofit);
    }
}
