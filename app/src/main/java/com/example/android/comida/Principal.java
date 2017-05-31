package com.example.android.comida;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.comida.Clases.Cliente;
import com.example.android.comida.Clases.Mesero;

public class Principal extends AppCompatActivity {

    private ListView ls;
    private Resources res;
    private String[] opc;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        ls = (ListView)findViewById(R.id.lvopciones);
        res = this.getResources();
        opc = res.getStringArray(R.array.opciones);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,opc);
        ls.setAdapter(adapter);

        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        i = new Intent(Principal.this, Registro.class);
                        startActivity(i);
                        break;
                    case 1:
                        i = new Intent(Principal.this, OpcionesAgregar.class);
                        startActivity(i);
                        break;
                    case 2:
                        i = new Intent(Principal.this, Listado.class);
                        startActivity(i);
                        break;
                    case 3:
                        i = new Intent(Principal.this, ListarClientes.class);
                        startActivity(i);
                        break;
                    case 4:
                        i = new Intent(Principal.this, ListarComidas.class);
                        startActivity(i);
                        break;
                    case 5:
                        i = new Intent(Principal.this, ListaMeseros.class);
                        startActivity(i);
                        break;

                }
            }
        });

        if (Datos.getClientes(this).isEmpty()){
            if (Datos.getMeseros(this).isEmpty()) {
                agregarCliente();
                agregarMeseros();
            }
        }

    }


    public void agregarCliente(){
        Cliente cliente = new Cliente();

        cliente.setNombre("Juan");
        cliente.setApellido("Perez");
        cliente.setIdentificacion("1045023099");
        cliente.guardar(this);

        cliente.setNombre("Leonardo");
        cliente.setApellido("Lawlitte");
        cliente.setIdentificacion("1050458533");
        cliente.guardar(this);

        cliente.setNombre("Test");
        cliente.setApellido("Test1");
        cliente.setIdentificacion("1");
        cliente.guardar(this);

    }
    public void agregarMeseros(){
        Mesero mesero = new Mesero();

        mesero.setNombre("Pancho");
        mesero.guardar(this);

        mesero.setNombre("Felipe");
        mesero.guardar(this);

        mesero.setNombre("Juancho");
        mesero.guardar(this);


    }
}
