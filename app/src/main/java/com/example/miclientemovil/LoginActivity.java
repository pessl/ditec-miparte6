package com.example.miclientemovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.miclientemovil.Model.LoginInteractorImpl;
import com.example.miclientemovil.Presenter.LoginPresenter;
import com.example.miclientemovil.Presenter.LoginPresenterImpl;
import com.example.miclientemovil.View.LoginView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity implements LoginView {
    HttpURLConnection conexion;
    private EditText Login;
    private EditText password;
    //private SharedPreferences prefs;
    //private SharedPreferences.Editor editor;
    //private boolean login;

    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginPresenter = new LoginPresenterImpl(this, new LoginInteractorImpl(),this);
        loginPresenter.validarlogin();

        /*prefs= getSharedPreferences("Config",MODE_PRIVATE);
        login = prefs.getBoolean("onlogin", false);
        if (login) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }*/
 /*       setContentView(R.layout.activity_login);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(). permitNetwork().build());
        Login = (EditText) findViewById(R.id.edtUsuario);
        password = (EditText) findViewById(R.id.edtPassword);*/
    }

  /*  public int ValidaDatos(String usuario, String pass) {
       int result=3;
        try {
            URL url=new URL(getString(R.string.dominio)+getString(R.string.login)+usuario+"&pass="+pass);
            conexion = (HttpURLConnection) url
                    .openConnection();
            if (conexion.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                String linea = reader.readLine();
                if (linea.equals("success"))
                    result=0;
                else
                    result=1;
            } else {
                Log.e("Asteroides", conexion.getResponseMessage());
            }
        } catch (Exception e) {
            Log.e("Asteroides", e.getMessage(), e);
        } finally {
            if (conexion!=null) conexion.disconnect();
            return result;
        }
    }*/

    public void onLogin(View view) {

        loginPresenter.validateCredentials(Login.getText().toString(), password.getText().toString());

        /*int V = ValidaDatos(Login.getText().toString(), password.getText().toString());
        if (V == 0) {
            editor = prefs.edit();
            editor.putBoolean("onlogin", true);
            editor.apply();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        } else
            Toast.makeText(this, "Ingreso Fallido", Toast.LENGTH_SHORT).show();*/
    }

    @Override
    public void navigateToHome() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onfailure() {
        Toast.makeText(this, "Ingreso Fallido", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void cargarVistar() {
        setContentView(R.layout.activity_login);
        Login = (EditText) findViewById(R.id.edtUsuario);
        password = (EditText) findViewById(R.id.edtPassword);
    }
}