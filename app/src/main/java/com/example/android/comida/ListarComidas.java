package com.example.android.comida;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.comida.Clases.Pedido;

import java.util.ArrayList;

public class ListarComidas extends AppCompatActivity implements AdaptadorComida.OnPedidoClickListener {

    private ListView lista;
    private ArrayList<Pedido> comidas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_comidas);

        //Capturar los objetos.
        lista = (ListView)findViewById(R.id.lvListadoComidas);
        comidas = Datos.traerComida(getApplicationContext());

        //Creacion de Array Adapter de las comidas.
        AdaptadorComida adapter = new AdaptadorComida(getApplicationContext(), comidas,this);
        lista.setAdapter(adapter);
    }

    @Override
    public void onPedidoClick(Pedido p) {

        Intent i = new Intent(ListarComidas.this,Detalle.class);
        i.putExtra("cliente",p.getCliente());
        i.putExtra("pedido",p.getPedido());
        i.putExtra("ingrediente",p.getIngrediente());
        i.putExtra("bebida",p.getBebida());
        i.putExtra("sabor",p.getSabor());
        i.putExtra("mesero", p.getMesero());
        startActivity(i);

    }
}
