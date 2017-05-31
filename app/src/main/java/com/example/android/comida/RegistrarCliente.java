package com.example.android.comida;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.comida.Clases.Cliente;

public class RegistrarCliente extends AppCompatActivity {

    EditText cajaNombre, cajaApellido, cajaIde;
    Button aceptar, cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cliente);
        cajaNombre = (EditText)findViewById(R.id.txtNombreC);
        cajaApellido = (EditText)findViewById(R.id.txtApellidoC);
        cajaIde = (EditText)findViewById(R.id.txtIdC);
        aceptar = (Button)findViewById(R.id.btnAgregarC);
        cancelar = (Button)findViewById(R.id.btnCancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrarCliente.this,Principal.class);
                startActivity(intent);
                finish();
            }
        });
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar();
            }
        });
    }

    public void guardar(){
        String nombre, apellido, ide;
        nombre = cajaNombre.getText().toString();
        apellido = cajaApellido.getText().toString();
        ide = cajaIde.getText().toString();
        if(!nombre.isEmpty()){
            if(!apellido.isEmpty()){
                if(!ide.isEmpty()){
                    Cliente cliente = new Cliente();
                    cliente.setNombre(nombre);
                    cliente.setApellido(apellido);
                    cliente.setIdentificacion(ide);
                    cliente.guardar(this);
                    Toast.makeText(RegistrarCliente.this, getString(R.string.mensaje2), Toast.LENGTH_SHORT).show();
                }else{
                    cajaIde.setError(getString(R.string.identificaion));
                }
            }else{
                cajaApellido.setError(getString(R.string.apellido));
            }
        }else{
            cajaNombre.setError(getString(R.string.nombre));
        }
    }
}
