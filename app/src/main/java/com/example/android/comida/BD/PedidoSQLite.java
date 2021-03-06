package com.example.android.comida.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sistemas04 on 23/05/2017.
 */

public class PedidoSQLite extends SQLiteOpenHelper {
    String sql = "CREATE TABLE Pedido(pedido text, ingrediente text, bebida text, sabor text, cliente text, mesero text)";

    public PedidoSQLite(Context contexto, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(contexto, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Pedido");
        db.execSQL(sql);
    }
}