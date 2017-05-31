package com.example.android.comida;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.comida.Clases.Mesero;

public class RegistrarMesero extends AppCompatActivity {

    EditText txtmesero;
    Button btnAceptar, btnCancelar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_mesero);
        txtmesero = (EditText)findViewById(R.id.txtMesero);
        btnAceptar = (Button)findViewById(R.id.button);
        btnCancelar = (Button)findViewById(R.id.button2);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar();
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegistrarMesero.this,Principal.class);
                startActivity(i);
            }
        });

    }

    public void guardar(){
        Mesero mesero = new Mesero();
        if(!txtmesero.getText().toString().isEmpty()) {
            mesero.setNombre(txtmesero.getText().toString());
            mesero.guardar(this);
            Toast.makeText(this, getString(R.string.mensaje), Toast.LENGTH_SHORT).show();
        }else {
            txtmesero.setError(getString(R.string.mesero));
        }
    }
}
