package com.example.android.comida;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.comida.Clases.Cliente;
import com.example.android.comida.Clases.Pedido;

public class Registro extends AppCompatActivity {

    private EditText cajaNombre, cajaApellido;
    private CheckBox perro, hamburg, pizza;
    private Spinner comboPedido, comboBebida, comboMeseros;
    private String[] opcPedido, opcBebida, opcMesero;
    private ArrayAdapter<String> adapterPedido, adapterBebida, adapterMeseros;
    private RadioButton r1,r2,r3;
    private Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        res = getResources();
        cajaNombre = (EditText)findViewById(R.id.txtNombre);
        cajaApellido = (EditText)findViewById(R.id.txtApellido);

        perro = (CheckBox)findViewById(R.id.chkPerros);
        hamburg = (CheckBox)findViewById(R.id.chkHamb);
        pizza = (CheckBox)findViewById(R.id.chkPizza);

        comboPedido = (Spinner)findViewById(R.id.cmbPedido);
        comboBebida = (Spinner)findViewById(R.id.cmbBebida);
        comboMeseros = (Spinner)findViewById(R.id.cmbMeseros);

        r1 = (RadioButton)findViewById(R.id.r1cc);
        r2 = (RadioButton)findViewById(R.id.r2pep);
        r3 = (RadioButton)findViewById(R.id.r3pos);
        opcPedido = res.getStringArray(R.array.opc_pedido);
        adapterPedido = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, opcPedido);
        opcBebida = res.getStringArray(R.array.opc_bebida);
        adapterBebida = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, opcBebida);

        adapterMeseros = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Datos.getMeseros(this));
        comboMeseros.setAdapter(adapterMeseros);


        comboPedido.setAdapter(adapterPedido);
        comboBebida.setAdapter(adapterBebida);

    }

    public void guardar(View v){
        if (validar()){
            String foto, iden, apellido, pedido, aux="", bebida, aux1="",mesero;
            //Este ahora seria identificacion
            iden = cajaNombre.getText().toString().trim();
            //Este el Nombre
            apellido = cajaApellido.getText().toString().trim();
            if(perro.isChecked()) aux = res.getString(R.string.perro);
            if(hamburg.isChecked()) aux = aux +", "+res.getString(R.string.hamburguesa);
            if(pizza.isChecked()) aux = aux +", "+res.getString(R.string.pizza);
            pedido = comboPedido.getSelectedItem().toString();
            bebida = comboBebida.getSelectedItem().toString();
            mesero = comboMeseros.getSelectedItem().toString();

            if(r1.isChecked()) aux1 = getResources().getString(R.string.cocacola);
            if(r2.isChecked()) aux1 = getResources().getString(R.string.pepsi);
            if(r3.isChecked()) aux1 = getResources().getString(R.string.postobon);

            Pedido c = new Pedido();
            c.setBebida(aux1);
            c.setCliente(iden);
            c.setPedido(aux);
            c.setIngrediente(pedido);
            c.setSabor(bebida);
            c.setMesero(mesero);
            c.guardar(getApplicationContext());

            new AlertDialog.Builder(this).setMessage(res.getString(R.string.mensaje)).show();
            limpiar();
        }
    }

    public boolean validar(){
        if (cajaNombre.getText().toString().isEmpty()){
            cajaNombre.requestFocus();
            cajaNombre.setError(res.getString(R.string.error_1));
            return false;
        }
        if (cajaApellido.getText().toString().isEmpty()){
            new AlertDialog.Builder(this).setMessage(res.getString(R.string.alerta2))
                    .setPositiveButton(res.getString(R.string.agregar), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(Registro.this, RegistrarCliente.class);
                            startActivity(i);
                            finish();
                        }
                    })
                    .setNegativeButton(res.getString(R.string.cancelar), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        }
                    })
                    .show();
            return false;
        }
        return true;
    }

    public void borrar(View v){
        limpiar();
    }

    public void elimiar(View v){
        if(!cajaNombre.getText().toString().isEmpty()) {
            Pedido pedido = new Pedido();
            pedido.setCliente(cajaNombre.getText().toString());
            pedido.eliminar(this);
            Toast.makeText(Registro.this, "Eliminado", Toast.LENGTH_SHORT).show();
        }else{
            cajaNombre.setError(res.getString(R.string.identificaion));
        }
    }

    public void limpiar(){
        cajaNombre.setText("");
        cajaApellido.setText("");
        perro.setChecked(true);
        hamburg.setChecked(false);
        pizza.setChecked(false);
        comboPedido.setSelection(0);
        comboBebida.setSelection(0);
        r1.setChecked(true);
        cajaNombre.requestFocus();
    }

    public void buscar(View v){
        String identificacion;
        Cliente c;

        identificacion = cajaNombre.getText().toString().trim();
        c = Datos.buscarCliente(getApplicationContext(),identificacion);

        if(c!=null) {
            cajaApellido.setText(c.getNombre());
        }else{
            cajaApellido.setText("");
            if (cajaApellido.getText().toString().isEmpty()){
                new AlertDialog.Builder(this).setMessage(res.getString(R.string.alerta2))
                        .setPositiveButton(res.getString(R.string.agregar), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(Registro.this, RegistrarCliente.class);
                                startActivity(i);
                                finish();
                            }
                        })
                        .setNegativeButton(res.getString(R.string.cancelar), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        }

    }

    /*
    public int fotoAleatoria(){
        int fotos[] = {R.drawable.imagen, R.drawable.imagen1};
        int numero = (int) (Math.random() *2);
        return fotos[numero];
    }
    */

    private double calcularPrecio(String pedido, String ingrediente, String bebida, String sabor){
        Resources r = getResources();
        if(pedido.equalsIgnoreCase(r.getString(R.string.perro))){
            if(ingrediente.equalsIgnoreCase(r.getString(R.string.sencillo))) {
                if (sabor.equalsIgnoreCase(r.getString(R.string.cocacola)) || sabor.equalsIgnoreCase(r.getString(R.string.pepsi)) || sabor.equalsIgnoreCase(r.getString(R.string.postobon))) {
                    if (bebida.equalsIgnoreCase(r.getString(R.string.grande))) {
                        return 9000;
                    } else if (bebida.equalsIgnoreCase(r.getString(R.string.mediana))) {
                        return 8000;
                    } else if (bebida.equalsIgnoreCase(r.getString(R.string.pequeña))) {
                        return 7000;
                    }
                    return 0;
                }
            } else if(ingrediente.equalsIgnoreCase(r.getString(R.string.pitbull))) {
                if (sabor.equalsIgnoreCase(r.getString(R.string.cocacola)) || sabor.equalsIgnoreCase(r.getString(R.string.pepsi)) || sabor.equalsIgnoreCase(r.getString(R.string.postobon))) {
                    if (bebida.equalsIgnoreCase(r.getString(R.string.grande))) {
                        return 12000;
                    } else if (bebida.equalsIgnoreCase(r.getString(R.string.mediana))) {
                        return 11000;
                    } else if (bebida.equalsIgnoreCase(r.getString(R.string.pequeña))) {
                        return 10000;
                    }
                    return 0;
                }
            }
            return 0;
        } else if(pedido.equalsIgnoreCase(r.getString(R.string.hamburguesa))) {
            if (ingrediente.equalsIgnoreCase(r.getString(R.string.carne))) {
                if (sabor.equalsIgnoreCase(r.getString(R.string.cocacola)) || sabor.equalsIgnoreCase(r.getString(R.string.pepsi)) || sabor.equalsIgnoreCase(r.getString(R.string.postobon))) {
                    if (bebida.equalsIgnoreCase(r.getString(R.string.grande))) {
                        return 11000;
                    } else if (bebida.equalsIgnoreCase(r.getString(R.string.mediana))) {
                        return 10000;
                    } else if (bebida.equalsIgnoreCase(r.getString(R.string.pequeña))) {
                        return 9000;
                    }
                    return 0;
                }
            } else if (ingrediente.equalsIgnoreCase(r.getString(R.string.doblecarne))) {
                if (sabor.equalsIgnoreCase(r.getString(R.string.cocacola)) || sabor.equalsIgnoreCase(r.getString(R.string.pepsi)) || sabor.equalsIgnoreCase(r.getString(R.string.postobon))) {
                    if (bebida.equalsIgnoreCase(r.getString(R.string.grande))) {
                        return 13000;
                    } else if (bebida.equalsIgnoreCase(r.getString(R.string.mediana))) {
                        return 12000;
                    } else if (bebida.equalsIgnoreCase(r.getString(R.string.pequeña))) {
                        return 11000;
                    }
                    return 0;
                }
            }
            return 0;
        } else if(pedido.equalsIgnoreCase(r.getString(R.string.pizza))) {
            if (ingrediente.equalsIgnoreCase(r.getString(R.string.jamon)) || ingrediente.equalsIgnoreCase(r.getString(R.string.hawaiana))) {
                if (sabor.equalsIgnoreCase(r.getString(R.string.cocacola)) || sabor.equalsIgnoreCase(r.getString(R.string.pepsi)) || sabor.equalsIgnoreCase(r.getString(R.string.postobon))) {
                    if (bebida.equalsIgnoreCase(r.getString(R.string.grande))) {
                        return 15000;
                    } else if (bebida.equalsIgnoreCase(r.getString(R.string.mediana))) {
                        return 14000;
                    } else if (bebida.equalsIgnoreCase(r.getString(R.string.pequeña))) {
                        return 13000;
                    }
                    return 0;
                }
                return 0;
            }
            return 0;
        }
        return 0;
    }
}
