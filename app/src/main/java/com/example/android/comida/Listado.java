package com.example.android.comida;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.android.comida.Clases.Cliente;
import com.example.android.comida.Clases.Pedido;

import java.util.ArrayList;

public class Listado extends AppCompatActivity {

    private TableLayout tabla;
    private String aux;
    private ArrayList<Pedido> comidas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        tabla = (TableLayout)findViewById(R.id.tblComidas);
        comidas = Datos.traerComida(getApplicationContext());

        for (int i = 0; i < comidas.size() ; i++) {
            TableRow fila = new TableRow(this);
            TextView c1 = new TextView(this);
            TextView c2 = new TextView(this);
            TextView c3 = new TextView(this);
            TextView c4 = new TextView(this);
            TextView c5 = new TextView(this);

            c1.setText(""+(i+1));
            Cliente cliente = Datos.buscarCliente(Listado.this,comidas.get(i).getCliente());
            c2.setText(cliente.getNombre());
            c3.setText(cliente.getApellido());
            c4.setText(comidas.get(i).getPedido());
            c5.setText(comidas.get(i).getBebida());

            fila.addView(c1);
            fila.addView(c2);
            fila.addView(c3);
            fila.addView(c4);
            fila.addView(c5);

            tabla.addView(fila);
        }
    }
}
