package com.example.miclientemovil.Model;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;

import com.example.miclientemovil.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginInteractorImpl implements LoginInteractor {
    HttpURLConnection conexion;

    @Override
    public void login(String username, String password, OnLoginFinishedListener listener, Context micontext) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(). permitNetwork().build());
        try {
            URL url=new URL(micontext.getString(R.string.dominio) + micontext.getString(R.string.login)+username+"&pass="+password);
            conexion = (HttpURLConnection) url
                    .openConnection();
            if (conexion.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                String linea = reader.readLine();
                if (linea.equals("success"))
                    listener.onSuccess();
                else
                    listener.onfailure();
            } else {
                Log.e("Asteroides", conexion.getResponseMessage());
            }
        } catch (Exception e) {
            Log.e("Asteroides", e.getMessage(), e);
        } finally {
            if (conexion!=null) conexion.disconnect();

        }
    }
}