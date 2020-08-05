package com.example.miclientemovil.Model;

import android.content.Context;

public interface LoginInteractor {
    interface OnLoginFinishedListener {
        void onSuccess();
        void onfailure();
    }
    void login(String username, String password, OnLoginFinishedListener listener, Context micontext);
}