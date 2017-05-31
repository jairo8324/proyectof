package com.example.android.comida.Clases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.android.comida.BD.ClienteSQLite;

/**
 * Created by sistemas04 on 23/05/2017.
 */

public class Cliente {
    String nombre;
    String apellido;
    String identificacion;

    public Cliente() {
    }

    public Cliente(String nombre, String apellido, String identificacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.identificacion = identificacion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void guardar(Context contexto){

        //Declarar las variables.
        SQLiteDatabase db;
        String sql;

        //Abrir la conexion en modo escritura.
        ClienteSQLite aux = new ClienteSQLite(contexto,"DBClientes",null,1);
        db = aux.getWritableDatabase();

        //Consulta para hacer la inserción. Forma 1.
        sql = "INSERT INTO Cliente values('"+this.getNombre()+"','"+this.getApellido()+"','"+this.getIdentificacion()+"')";
        db.execSQL(sql);

        db.close();
    }


}
