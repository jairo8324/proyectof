package com.example.android.comida;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.android.comida.Clases.Cliente;

import java.util.ArrayList;

public class ListarClientes extends AppCompatActivity {

    private TableLayout tabla1;
    private String aux;
    private ArrayList<Cliente> clientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_clientes);

        tabla1 = (TableLayout)findViewById(R.id.tblClientes);
        clientes = Datos.getClientes(getApplicationContext());

        for (int i = 0; i < clientes.size() ; i++) {
            TableRow fila = new TableRow(this);
            TextView c1 = new TextView(this);
            TextView c2 = new TextView(this);
            TextView c3 = new TextView(this);

            c1.setText("" + (i + 1));
            c2.setText(clientes.get(i).getNombre());
            c3.setText(clientes.get(i).getApellido());

            fila.addView(c1);
            fila.addView(c2);
            fila.addView(c3);

            tabla1.addView(fila);
        }
    }
}
