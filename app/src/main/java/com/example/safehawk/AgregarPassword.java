package com.example.safehawk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AgregarPassword extends AppCompatActivity
{
    EditText txtCuenta, txtPassword;
    Button guardar, limpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_password);

        //Instancia de los objetos.
        txtCuenta = findViewById(R.id.id_cuenta);
        txtPassword = findViewById(R.id.id_password);
        guardar = findViewById(R.id.id_guardarPass);
        limpiar = findViewById(R.id.id_limpiar);

        //Acción de los botones.
        guardar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

        limpiar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
    }
}