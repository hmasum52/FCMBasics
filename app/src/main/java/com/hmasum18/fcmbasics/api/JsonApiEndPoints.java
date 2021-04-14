package com.hmasum18.fcmbasics.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface JsonApiEndPoints {
    @POST
    Call<JsonElement> POST(@HeaderMap Map<String,String> headerMap,
                                      @Url String relativePath, @Body Object object);
    @POST
    Call<JsonElement> POST(@Url String relativePath, @Body Object object);

    @GET
    Call<JsonElement> GET(@HeaderMap Map<String,String> headerMap, @Url String relativePath);

    @GET
    Call<JsonElement> GET(@Url String relativePath);
}
