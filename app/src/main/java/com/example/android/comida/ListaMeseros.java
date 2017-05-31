package com.example.android.comida;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListaMeseros extends AppCompatActivity {

    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_meseros);
        lista = (ListView)findViewById(R.id.lista);
        ArrayList<String> datos = Datos.getMeseros(getApplicationContext());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,datos);
        lista.setAdapter(adapter);

    }

}
