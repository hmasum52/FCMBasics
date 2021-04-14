package com.hmasum18.fcmbasics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.hmasum18.fcmbasics.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity->";
    ActivityMainBinding mVB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVB = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mVB.getRoot());

        FirebaseMessaging.getInstance().subscribeToTopic("offline");

        VollyRequest vollyRequest = new VollyRequest(this);
        mVB.sendUsingVolly.setOnClickListener(v -> {
            Log.d(TAG,"sending request to FCM");
            //vollyRequest.sendFCMNotification();
            new RetrofitRequest(this).sendFCMNotificationByRetrofit();
        });
    }

    private void getFCMToken(){
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        String msg = "Message token from FCM: " + token;
                        Log.d(TAG, msg);
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }

}