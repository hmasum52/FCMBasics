package com.hmasum18.fcmbasics.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FCMEndPointService{
    public static final String TAG = "FCMEndPointService->";
    private static FCMEndPointService instance;
    private final FCMEndPoint FCMEndPoint;

    private FCMEndPointService(String baseUrl){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FCMEndPoint = retrofit.create(FCMEndPoint.class);
        System.out.println(TAG+"BUET edu api is created successfully");
    }

    public static synchronized FCMEndPointService getInstance(String baseUrl) {
        if(instance == null)
            instance = new FCMEndPointService(baseUrl);
        return instance;
    }

    public FCMEndPoint getFCMEndPoint() {
        return FCMEndPoint;
    }
}
