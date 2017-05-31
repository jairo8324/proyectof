package com.example.android.comida.Clases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.android.comida.BD.ClienteSQLite;
import com.example.android.comida.BD.PedidoSQLite;

/**
 * Created by sistemas04 on 23/05/2017.
 */

public class Pedido {

    String pedido;
    String ingrediente;
    String bebida;
    String sabor;
    //Esta la identificacion del cliente
    String cliente;
    String mesero;

    public Pedido() {
    }

    public Pedido(String pedido, String ingrediente, String bebida, String sabor, String cliente, String mesero) {
        this.pedido = pedido;
        this.ingrediente = ingrediente;
        this.bebida = bebida;
        this.sabor = sabor;
        this.cliente = cliente;
        this.mesero = mesero;
    }



    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public String getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(String ingrediente) {
        this.ingrediente = ingrediente;
    }

    public String getBebida() {
        return bebida;
    }

    public void setBebida(String bebida) {
        this.bebida = bebida;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getMesero() {
        return mesero;
    }

    public void setMesero(String mesero) {
        this.mesero = mesero;
    }

    public void guardar(Context contexto){

        //Declarar las variables.
        SQLiteDatabase db;
        String sql;

        //Abrir la conexion en modo escritura.
        ClienteSQLite aux = new ClienteSQLite(contexto,"DBPedidos",null,1);
        db = aux.getWritableDatabase();

        //Consulta para hacer la inserción. Forma 1.
        sql = "INSERT INTO Pedido values('"+this.getPedido()+"','"+this.getIngrediente()+
                "','"+this.getBebida()+"','"+this.getSabor()+"','"+this.getCliente()+"','"+this.getMesero()+"')";
        db.execSQL(sql);

        db.close();
    }

    public void modificar(Context contexto){

        //Declarar las variables.
        SQLiteDatabase db;
        String sql;

        //Abrir la conexion en modo escritura.
        PedidoSQLite aux = new PedidoSQLite(contexto,"DBPedidos",null,1);
        db = aux.getWritableDatabase();

        //Consulta para hacer la inserción. Forma 1.
        sql = "UPDATE Pedido set pedido='"+this.getPedido()+"',ingrediente='"+this.getIngrediente()+"',bebida='"+this.getBebida()+"'"+
                ",sabor='"+this.getSabor()+"',cliente='"+this.getCliente()+"',mesero='"+this.getMesero()+"' where cliente like '%"+this.getCliente()+"%'";
        db.execSQL(sql);
        db.close();
    }


    public void eliminar(Context contexto){

        //Declarar las variables.
        SQLiteDatabase db;
        String sql;

        //Abrir la conexion en modo escritura.
        PedidoSQLite aux = new PedidoSQLite(contexto,"DBPedidos",null,1);
        db = aux.getWritableDatabase();

        //Consulta para hacer la inserción. Forma 1.
        sql = "DELETE FROM Pedido where cliente like '%"+this.getCliente()+"%'";
        db.execSQL(sql);
        db.close();
    }

}
