package com.hmasum18.fcmbasics.api;


import com.google.gson.JsonElement;

public interface ArrayCallable<T extends JsonElement> {
    void callForArray(String relativePath);
    void setOnCallSuccessListener(OnCallSuccessListener<T> onCallSuccessListener);
}
