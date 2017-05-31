package com.example.android.comida.Clases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.android.comida.BD.ClienteSQLite;
import com.example.android.comida.BD.MeseroSQLite;

/**
 * Created by sistemas04 on 23/05/2017.
 */

public class Mesero {
    String nombre;

    public Mesero() {
    }

    public Mesero(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void guardar(Context contexto){

        //Declarar las variables.
        SQLiteDatabase db;
        String sql;

        //Abrir la conexion en modo escritura.
        MeseroSQLite aux = new MeseroSQLite(contexto,"DBMeseros",null,1);
        db = aux.getWritableDatabase();

        //Consulta para hacer la inserción. Forma 1.
        sql = "INSERT INTO Mesero values('"+this.getNombre()+"')";
        db.execSQL(sql);

        db.close();
    }
}
