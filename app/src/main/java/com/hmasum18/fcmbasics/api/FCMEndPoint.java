package com.hmasum18.fcmbasics.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface FCMEndPoint {
    /*@POST("{path}")
    Call<JsonObject> postJsonObject(@Header("authorization")String token
            , @Path(value = "path",encoded = true) String path,@Body  JsonObject jsonObject);

    @GET("{path}")
    Call<JsonObject> callForJsonObject(@Header("authorization")String token
            , @Path(value = "path", encoded = true) String path);
    @GET("{path}")
    Call<JsonArray> callForJsonArray(@Header("authorization")String token
            , @Path(value = "path", encoded = true) String path);*/

    //auth
    @POST
    Call<JsonObject> postJsonObject(@Url String path, @Body JsonObject jsonObject);

    @POST
    Call<JsonObject> postJsonObject(@Header("authorization")String token, @Url String path, @Body JsonObject jsonObject);
    @GET
    Call<JsonObject> callForJsonObject(@Header("authorization")String token, @Url String path);
    @GET
    Call<JsonArray> callForJsonArray(@Header("authorization")String token, @Url String path);
}
