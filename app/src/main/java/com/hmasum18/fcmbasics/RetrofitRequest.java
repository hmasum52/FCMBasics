package com.hmasum18.fcmbasics;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;
import com.hmasum18.fcmbasics.api.Constants;
import com.hmasum18.fcmbasics.api.JsonApiCaller;
import com.hmasum18.fcmbasics.api.OnCompleteListener;

public class RetrofitRequest {
    public static final String TAG = "RetrofitRequest->";
    private  Context context;
    public RetrofitRequest(Context context){
        this.context = context;
    }

    public void sendFCMNotificationByRetrofit(){
        JsonApiCaller<JsonObject> caller = new JsonApiCaller<>(JsonObject.class, Constants.BASE_URL_FCM);
        JsonObject mainObj = new JsonObject();
        mainObj.addProperty("to","/topics/offline");

        //notification
        JsonObject notification = new JsonObject();
        notification.addProperty("title","CSE203 DSA-Offline 6");
        notification.addProperty("body","Offline on graph. Deadline 23 April");
        mainObj.add("notification",notification);

        //send extra data
        JsonObject data = new JsonObject();
        data.addProperty("course","CSE203");
        data.addProperty("offline",5);
        data.addProperty("offline_title","Graph Traversal");
        mainObj.add("data",data);


        caller.POST(Constants.fcmHeaderMap,"fcm/send",mainObj)
                .addOnCompleteListener(new OnCompleteListener<JsonObject>() {
                    @Override
                    public void onSuccess(JsonObject jsonObject) {
                        Log.d(TAG,"Notification sent successfully");
                        Log.d(TAG,"Response: "+jsonObject.toString());
                    }

                    @Override
                    public void onFailure(Exception e) {
                        Log.d(TAG,"Failed sending notification");
                    }
                });
    }
}
