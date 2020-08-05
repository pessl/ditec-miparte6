package com.example.miclientemovil.Presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.miclientemovil.MainActivity;
import com.example.miclientemovil.Model.LoginInteractor;
import com.example.miclientemovil.View.LoginView;

import static android.content.Context.MODE_PRIVATE;

public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.OnLoginFinishedListener {
    LoginView loginView;
    LoginInteractor loginInteractor;
    Context micontext;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    public LoginPresenterImpl(LoginView loginView, LoginInteractor loginInteractor, Context micontext) {
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
        this.micontext =  micontext;
        prefs= micontext.getSharedPreferences("Config",MODE_PRIVATE);
    }

    @Override
    public void onSuccess() {
        editor = prefs.edit();
        editor.putBoolean("onlogin", true);
        editor.apply();
        loginView.navigateToHome();
    }

    @Override
    public void validateCredentials(String username, String password) {
        loginInteractor.login(username,password,this, micontext);
    }

    @Override
    public void validarlogin() {
        Boolean login = prefs.getBoolean("onlogin", false);
        if (login) {
            loginView.navigateToHome();
        }else {
            loginView.cargarVistar();
        }
    }

    @Override
    public void onfailure() {
        loginView.onfailure();
    }
}