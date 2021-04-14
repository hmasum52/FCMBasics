package com.hmasum18.fcmbasics;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FCMService extends FirebaseMessagingService {
    public static final String TAG = "FCMService";
    /**
     * There are two scenarios when onNewToken is called:
     * 1) When a new token is generated on initial app startup
     * 2) Whenever an existing token is changed
     * Under #2, there are three scenarios when the existing token is changed:
     * A) App is restored to a new device
     * B) User uninstalls/reinstalls the app
     * C) User clears app data
     */
    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "onNewToken(): Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // FCM registration token to your app server.
        //sendRegistrationToServer(token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        //super.onMessageReceived(remoteMessage);
        Log.d(TAG,"onMessageReceived(): remoteMessage: ");
        Log.d(TAG,"onMessageReceived(): From: "+remoteMessage.getFrom());

        //get the notification
        //and handle it
        if(remoteMessage.getNotification() != null){
            Log.d(TAG,"onMessageReceived(): "+remoteMessage.getNotification().getTitle());
            Log.d(TAG,"onMessageReceived(): "+remoteMessage.getNotification().getBody());
        }

        //get extra data
        if(remoteMessage.getData().size()>0){
            Log.d(TAG,"onMessageReceived(): "+remoteMessage.getData());
        }
    }

    @Override
    public void onMessageSent(@NonNull String s) {
        //super.onMessageSent(s);
        Log.d(TAG,"onMessageSent(): "+s);
    }
}
