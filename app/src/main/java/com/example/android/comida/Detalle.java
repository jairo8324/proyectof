package com.example.android.comida;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.android.comida.Clases.Cliente;

public class Detalle extends AppCompatActivity {

    TextView detalle;
    Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        detalle = (TextView)findViewById(R.id.lblDetalle);
        res = getResources();
        Intent i = getIntent();
        String cliente = i.getStringExtra("cliente");
        String pedido  = i.getStringExtra("pedido");
        String ingrediente  = i.getStringExtra("ingrediente");
        String bebida  = i.getStringExtra("bebida");
        String sabor  = i.getStringExtra("sabor");
        String mesero = i.getStringExtra("mesero");

        Cliente c;
        c = Datos.buscarCliente(getApplicationContext(),cliente);
         String detallePedido = "\n"+res.getString(R.string.pedido)+": "+pedido+"\n"+
                 "\n"+res.getString(R.string.ingrediente)+": "+ingrediente+"\n"+
                 "\n"+res.getString(R.string.bebida)+": "+bebida+"\n"+
                 "\n"+res.getString(R.string.sabor)+": "+sabor+"\n"+
                 "\n"+res.getString(R.string.nombre)+": "+c.getNombre()+"\n"+
                 "\n"+res.getString(R.string.apellido)+": "+c.getApellido()+"\n"+
                 "\n"+res.getString(R.string.identificaion)+": "+c.getIdentificacion()+"\n"+
                 "\n"+res.getString(R.string.atendido_por)+": "+mesero+"\n"+
                 "";
        detalle.setText(detallePedido);
    }
}
