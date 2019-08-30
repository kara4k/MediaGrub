package com.kara4k.mediagrub.jsoup.converter;

import android.support.annotation.NonNull;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class ResponseBodyConverter implements Converter<ResponseBody, Document> {

    @Override
    public Document convert(@NonNull final ResponseBody responseBody) throws IOException {
        return Jsoup.parse(responseBody.string());
    }
}
