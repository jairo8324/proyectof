package com.example.android.comida;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.android.comida.BD.ClienteSQLite;
import com.example.android.comida.BD.MeseroSQLite;
import com.example.android.comida.BD.PedidoSQLite;
import com.example.android.comida.Clases.Cliente;
import com.example.android.comida.Clases.Mesero;
import com.example.android.comida.Clases.Pedido;

import java.util.ArrayList;


public class Datos {
    public static ArrayList<Pedido> traerComida(Context contexto){
        //Declarar variables.
        SQLiteDatabase db;
        String sql, pedido, ingrediente, bebida, sabor, cliente, mesero;
        ArrayList<Pedido> comidas = new ArrayList();

        //Abrir la base de datos de lectura.
        PedidoSQLite aux = new PedidoSQLite(contexto,"DBPedidos",null,1);
        db = aux.getReadableDatabase();

        //Como haremos un recorrido hacemos un cursor.
        sql = "Select pedido, ingrediente, bebida, sabor, cliente, mesero from Pedido";
        Cursor c = db.rawQuery(sql,null);

        //Recorrido del cursor.
        if(c.moveToFirst()){
            do{

                pedido = c.getString(0);
                ingrediente = c.getString(1);
                bebida = c.getString(2);
                sabor = c.getString(3);
                cliente = c.getString(4);
                mesero =c.getString(5);
                Pedido com = new Pedido(pedido, ingrediente, bebida, sabor, cliente, mesero);
                comidas.add(com);
            }while(c.moveToNext());
        }
        //Cerrar conexión.
        db.close();
        //Retornar comidas.
        return comidas;
    }

    public static Cliente buscarCliente(Context contexto, String id){
        //Declarar variables.
        SQLiteDatabase db;
        String sql, nombre, apellido, identificacion;
        Cliente cliente = null;

        //Abrir la base de datos de lectura.
        ClienteSQLite aux = new ClienteSQLite(contexto,"DBClientes",null,1);
        db = aux.getReadableDatabase();

        //Como haremos un recorrido hacemos un cursor.
        sql = "Select nombre, apellido, identificacion from Cliente";
        Cursor c = db.rawQuery(sql,null);

        //Recorrido del cursor.
        if(c.moveToFirst()){
            do {
                nombre = c.getString(0);
                apellido = c.getString(1);
                identificacion = c.getString(2);

               Cliente nuevo = new Cliente(nombre, apellido, identificacion);
                if (identificacion.equalsIgnoreCase(id)) {
                    cliente =nuevo;
                }
            }while (c.moveToNext());
        }
        //Cerrar conexión.
        db.close();
        //Retornar personas.
        return cliente;
    }

    public static ArrayList<Cliente> getClientes(Context contexto){
        //Declarar variables.
        SQLiteDatabase db;
        String sql, nombre, apellido, identificacion;
        ArrayList<Cliente> clientes = new ArrayList<>();

        //Abrir la base de datos de lectura.
        ClienteSQLite aux = new ClienteSQLite(contexto,"DBClientes",null,1);
        db = aux.getReadableDatabase();

        //Como haremos un recorrido hacemos un cursor.
        sql = "Select nombre, apellido, identificacion from Cliente";
        Cursor c = db.rawQuery(sql,null);

        //Recorrido del cursor.
        if(c.moveToFirst()) {
            do {


                nombre = c.getString(0);
                apellido = c.getString(1);
                identificacion = c.getString(2);

                Cliente cliente = new Cliente(nombre, apellido, identificacion);
                clientes.add(cliente);
            } while (c.moveToNext());
        }
        //Cerrar conexión.
        db.close();
        //Retornar personas.
        return clientes;
    }


    public static ArrayList<String> getMeseros(Context contexto){
        //Declarar variables.
        SQLiteDatabase db;
        String sql, nombre;
        ArrayList<String> meseros = new ArrayList<>();

        //Abrir la base de datos de lectura.
        MeseroSQLite aux = new MeseroSQLite(contexto,"DBMeseros",null,1);
        db = aux.getReadableDatabase();

        //Como haremos un recorrido hacemos un cursor.
        sql = "Select nombre from Mesero";
        Cursor c = db.rawQuery(sql,null);

        //Recorrido del cursor.
        if(c.moveToFirst()) {
            do {
                nombre = c.getString(0);


                Mesero mesero = new Mesero(nombre);
                meseros.add(mesero.getNombre());
            } while (c.moveToNext());
        }
        //Cerrar conexión.
        db.close();
        //Retornar personas.
        return meseros;
    }

    public static String[] getMeserosV(Context contexto){
        ArrayList<String> listaMeseros = getMeseros(contexto);
        String[] meseros={""};

        for (int i = 0; i < listaMeseros.size(); i++) {
            meseros[i]=listaMeseros.get(i);
        }

        return meseros;
    }

}
