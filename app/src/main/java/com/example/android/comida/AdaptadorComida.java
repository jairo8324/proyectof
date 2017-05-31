package com.example.android.comida;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.comida.Clases.Cliente;
import com.example.android.comida.Clases.Pedido;

import java.util.ArrayList;

/**
 * Created by android on 02/05/2017.
 */

public class AdaptadorComida extends BaseAdapter{
    private Context contexto;
    private ArrayList<Pedido> comidas;
    private OnPedidoClickListener clickListener;

    public AdaptadorComida(Context contexto, ArrayList<Pedido> comidas, OnPedidoClickListener clickListener){
        this.contexto=contexto;
        this.comidas = comidas;
        this.clickListener=clickListener;
    }

    @Override
    public int getCount() {
        return comidas.size();
    }

    @Override
    public Object getItem(int position) {
        return comidas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Declarar Variables.
        TextView nombre, apellido,pedido,bebida;
        ImageView foto;
        LayoutInflater inflater;
        View itemView;
        final int pos = position;

        //Uso del Inflater.
        inflater = (LayoutInflater)contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
        itemView = inflater.inflate(R.layout.item_comida,null);

        //Captura de los objetos.
        nombre = (TextView)itemView.findViewById(R.id.txtNombreCliente);
        apellido = (TextView)itemView.findViewById(R.id.txtApellidoCliente);
        foto = (ImageView)itemView.findViewById(R.id.imgFoto);
        pedido=(TextView)itemView.findViewById(R.id.txtPedidoCliente);
        bebida = (TextView)itemView.findViewById(R.id.txtBebidaCliente);


        //Pasar la información.
        Cliente cliente = Datos.buscarCliente(contexto, comidas.get(position).getCliente());
        nombre.setText(cliente.getNombre());
        apellido.setText(cliente.getApellido());
        pedido.setText(comidas.get(position).getPedido());
        bebida.setText(comidas.get(position).getBebida());
        //Le pone la misma foto a todos.
        foto.setImageResource(R.drawable.imagen);



        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onPedidoClick(comidas.get(pos));
            }
        });



        //Retornar ItemView
        return itemView;
    }


    public interface OnPedidoClickListener {
        void onPedidoClick(Pedido p);
    }
}
