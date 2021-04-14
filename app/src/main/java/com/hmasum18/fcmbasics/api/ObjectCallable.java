package com.hmasum18.fcmbasics.api;

import com.google.gson.JsonElement;

/**
 *
 * @param <T>
 */
public interface ObjectCallable<T extends JsonElement> {
    void callForObject(String relativePath);
    void setOnCallSuccessListener(OnCallSuccessListener<T> onCallSuccessListener);
}
