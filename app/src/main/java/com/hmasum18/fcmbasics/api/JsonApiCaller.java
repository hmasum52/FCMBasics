package com.hmasum18.fcmbasics.api;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This class fetch data from the remote server as json object or json array
 *
 * It calls api end point for data by retrofit2
 * and notify the repositories when data is received.
 *
 * @author Hasan Masum
 * @version 1.0
 * @since 21/01/2021
 */
public class JsonApiCaller<T extends JsonElement> implements ObjectCallable<T>, ArrayCallable<T>,ObjectPostable<T> {
    public static final String TAG = "JsonApiCaller:-->";
    public static final String BASE_URL = "https://fcm.googleapis.com/fcm/send";
    private String authToken = null;
    private final FCMEndPoint FCMEndPoint; //api endpoints
    private OnCallSuccessListener<T> onCallSuccessListener;
    private Context context;

    public JsonApiCaller(Context context){
        this.context = context;
        FCMEndPoint = FCMEndPointService.getInstance(BASE_URL).getFCMEndPoint();
        //authToken = CacheManager.getCachedData(context, AuthRepository.AUTH_TOKEN_CACHE_FILE_NAME);
        //Log.d(TAG, " JsonApiCaller(): "+authToken);
    }
    @Override
    public void setOnCallSuccessListener(OnCallSuccessListener<T> onCallSuccessListener) {
        this.onCallSuccessListener = onCallSuccessListener;
    }

    @Override
    public void callForArray(String relativePath){
        if(authToken == null) {
            Log.d(TAG, " JsonApiCaller(): "+authToken);
            Log.d(TAG," auth token is null. user is not authenticated.");
            return;
        }
        Call<JsonArray> call = FCMEndPoint.callForJsonArray(authToken,relativePath);
        Request request = call.request();
        Log.d(TAG,"callForArray>method: "+request.method());
        Log.d(TAG,"callForArray>url: "+request.url());
       // Log.d(TAG,"callForArray>headers: "+request.headers());
        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if(response.isSuccessful()){
                    JsonArray jsonArray = response.body();
                    Log.d(TAG,"callForArray>onResponse: yahoo. process is successful");
                    onCallSuccessListener.onSuccess((T) jsonArray);
                }else{
                    Log.d(TAG,"callForArray>onResponse:  process is not successful "+response.code());
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                String className = t.getClass().toString();
                if(className.endsWith("UnknownHostException") )
                    Log.d(TAG,"Server is not responding");
                else if(className.endsWith("JsonSyntaxException"))
                    Log.d(TAG,"Response is not a com.google.gson.JsonArray");
                t.printStackTrace();
            }
        });
    }

    @Override
    public void callForObject(String relativePath){
        if(authToken ==null) {
            Log.d(TAG," auth token is null. user is not authenticated.");
            return;
        }
        Call<JsonObject> call = FCMEndPoint.callForJsonObject(authToken,relativePath);
        this.enqueueObjectCall(call);
    }

    @Override
    public void callToPostObject(String relativePath, JsonObject object) {
        Call<JsonObject> call;
        if(object.has("id_token")){
            Log.d(TAG, "callToPostObject()=> auth call ");
            call = FCMEndPoint.postJsonObject(relativePath, object);
        }
        else {
            Log.d(TAG, "callToPostObject()=> not auth call ");
            call = FCMEndPoint.postJsonObject(authToken, relativePath, object);
        }
        this.enqueueObjectCall(call);
    }

    private void enqueueObjectCall(Call<JsonObject> call){
        //for debugging
        //start
        Request request = call.request();
        Log.d(TAG,"callForObject>method: "+request.method());
        Log.d(TAG,"callForObject>url: "+request.url());
       // Log.d(TAG,"callForObject>headers: "+request.headers());
       // Log.d(TAG,"callForObject>body: "+request.body());
        //end
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.isSuccessful()){
                    JsonObject jsonObject = response.body();
                    Log.d(TAG,"callForObject>onResponse: yahoo. process is successful");
                    onCallSuccessListener.onSuccess((T) jsonObject);
                }else{
                    Log.d(TAG,"callForObject>onResponse:  process is not successful "+response.code());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                String className = t.getClass().toString();
                if(className.endsWith("UnknownHostException") )
                    Log.d(TAG,"callForObject>Server is not responding");
                else if(className.endsWith("JsonSyntaxException"))
                    Log.d(TAG,"callForObject>Response is not a com.google.gson.JsonArray");
                t.printStackTrace();
            }
        });
    }
}
