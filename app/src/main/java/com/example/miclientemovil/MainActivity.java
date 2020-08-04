package com.example.miclientemovil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MiNuevoAdaptador adaptador;
    private Vector<String> misdatos;
    public Vector<String> valor;
    private String res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        misdatos = new Vector<String>();
        misdatos.add("123000 Wilson Callisaya");
        misdatos.add("123000 Pepito Domingez");

        /*adaptador = new MiNuevoAdaptador(this, misdatos);*/
        adaptador = new MiNuevoAdaptador(this, ListaClientes(8));

        recyclerView.setAdapter(adaptador);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    public Vector<String> ListaClientes(int cantidad) {
        List<Cliente> clientes;
        clientes= leerJSon(conseguirstring());
        valor=new Vector<>();
        Vector<String> salida = new Vector<>();
        for (Cliente cliente : clientes) {
            salida.add(cliente.getNombre()+ " " + cliente.getApellido());
            valor.add(cliente.getcodigo());
        }
        Log.i("mierror",salida.toString());
        return salida;
    }

    public String conseguirstring() {
        try
        {
            HttpClient httpclient = new DefaultHttpClient();
            httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
            //la ruta del json
            HttpGet request = new HttpGet(this.getString(R.string.dominio)+this.getString(R.string.vercliente));
            HttpResponse response = httpclient.execute(request);
            HttpEntity resEntity = response.getEntity();
            String _response= EntityUtils.toString(resEntity);
            res=_response;
        }catch(Exception e)
        {
            return res="";
        }
        return res;
    }

    private List<Cliente> leerJSon(String string) {
        List<Cliente> Clientes = new ArrayList<>();
        try {
            JSONArray json_array = new JSONArray(string);
            for (int i = 0; i < json_array.length(); i++) {
                JSONObject objeto = json_array.getJSONObject(i);
                Clientes.add(new Cliente(objeto.getString("Cod_persona"), objeto.getString("Nombre"),objeto.getString("Apellidos")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Clientes;
    }
}