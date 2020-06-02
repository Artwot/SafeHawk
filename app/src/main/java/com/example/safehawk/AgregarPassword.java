package com.example.safehawk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.safehawk.Firebase.Password;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AgregarPassword extends AppCompatActivity
{
    EditText txtCuenta, txtPassword;
    Button guardar, limpiar;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseStorage storage;
    StorageReference storageReference;

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

        FirebaseApp.initializeApp(AgregarPassword.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        //Acción de los botones.
        guardar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String cuenta = txtCuenta.getText().toString().trim();
                String pass = txtPassword.getText().toString().trim();

                if (!cuenta.isEmpty() && !pass.isEmpty())
                {
                    Password password = new Password();
                    password.setUid(UUID.randomUUID().toString());
                    password.setCuenta(cuenta);
                    password.setPassword(pass);

                    databaseReference.child("Password").child(password.getUid()).setValue(password);

                    Toast.makeText(AgregarPassword.this, "Registro añadido", Toast.LENGTH_SHORT).show();

                    txtCuenta.setText("");
                    txtPassword.setText("");
                }else
                {
                    Toast.makeText(AgregarPassword.this, "Campos vacíos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        limpiar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                txtCuenta.setText("");
                txtPassword.setText("");
            }
        });
    }
}