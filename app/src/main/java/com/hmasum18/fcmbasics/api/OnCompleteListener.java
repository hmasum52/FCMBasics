package com.hmasum18.fcmbasics.api;

public interface OnCompleteListener<T>{
    void onSuccess(T t);
    void onFailure(Exception e);
}
