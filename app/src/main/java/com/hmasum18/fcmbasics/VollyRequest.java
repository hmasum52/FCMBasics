package com.hmasum18.fcmbasics;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VollyRequest {
    public static final String TAG="VollyRequest";
    public static final String BASE_URL = "https://fcm.googleapis.com/fcm/send";
    public static final String KEY = "AAAAAOJHYwA:APA91bHGSc-f-w7KY6RiML6lJS6o9JqMWACuCjmhWeSmmqCL0nm5llHF6vx1wX9vMLLuj2pEZOZ-7GfozNsrdIqtuGMIIafjQrIgSrRlTaQAzwgoQLVa59IpHl7L7A1mCOYi9UUUnS6r";
    private RequestQueue requestQueue;
    public VollyRequest(Context context){
        requestQueue = Volley.newRequestQueue(context);
    }

    public void sendFCMNotification() {
        try {
            JSONObject mainObj = new JSONObject();
            mainObj.put("to", "/topics/offline");

            //build a notification
            JSONObject notification = new JSONObject();
            notification.put("title","CSE203 DSA-Offline 6");
            notification.put("body","Offline on graph. Deadline 23 April");
            mainObj.put("notification",notification);

            //send extra data
            JSONObject data = new JSONObject();
            data.put("course","CSE203");
            data.put("offline",5);
            data.put("offline_title","Graph Traversal");
            mainObj.put("data",data);

            //make a volly json object request
            JsonObjectRequest request = new JsonObjectRequest(
                    Request.Method.POST,
                    BASE_URL,
                    mainObj,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d(TAG,"notification sent");
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d(TAG,"notification was not sent");
                        }
                    }
            ){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> header = new HashMap<>();
                    header.put("content-type","application/json");
                    header.put("authorization","key="+KEY);
                    return header;
                }
            };

            //request is build
            //finnaly send the request
            requestQueue.add(request);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
