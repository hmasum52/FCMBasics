package com.hmasum18.fcmbasics.api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public interface ObjectPostable<T extends JsonElement> {
    void callToPostObject(String relativePath, JsonObject object);
    void setOnCallSuccessListener(OnCallSuccessListener<T> onCallSuccessListener);
}

