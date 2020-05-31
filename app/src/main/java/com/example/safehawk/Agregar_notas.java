package com.example.safehawk;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.safehawk.SQLite.SQLite;

public class Agregar_notas extends AppCompatActivity
{
    Button limpiar, agregar;
    EditText txtNombre,txtContenido;
    SQLite sqlite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_notas);

        limpiar=findViewById(R.id.btnLimpiarN);
        agregar=findViewById(R.id.btnGuardarN);
        txtContenido=findViewById(R.id.txtContenido);
        txtNombre=findViewById(R.id.txtnombre);

        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtNombre.setText("");
                txtContenido.setText("");

            }
        });
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sqlite = new SQLite(Agregar_notas.this);
                sqlite.abrir();
                int id= (int) Math.floor(Math.random()*1000+1);
                String usuario="a";

                if (!txtNombre.getText().toString().equals("") && !txtContenido.getText().toString().equals("")){
                    if (sqlite.addTextos(id,usuario,txtNombre.getText().toString(),txtContenido.getText().toString())){
                        Toast.makeText(Agregar_notas.this,
                                "REGISTRO EXITOSO",
                                Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Agregar_notas.this,
                                "REGISTRO NO EXITOSO",
                                Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(Agregar_notas.this,
                            "CAMPOS VACÍOS",
                            Toast.LENGTH_SHORT).show();
                }





            }
        });



    }
}
