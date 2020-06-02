package com.example.safehawk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.safehawk.SQLite.SQLite;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;

public class InicioNube extends AppCompatActivity
{
    private SQLite sqlite;
    private ArrayList<String> reg;
    private ArrayList<String> imagenes;
    private Button sinc, ver;
    private int position = 0;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseStorage storage;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_nube);

        sinc = findViewById(R.id.id_sinc);
        ver = findViewById(R.id.id_ver);

        FirebaseApp.initializeApp(InicioNube.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        sinc.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                baseDeDatos();
                for (int i = 0; i < imagenes.size(); i++)
                {
                    cargarImagen(imagenes.get(position));
                    position++;
                }
            }
        });

        ver.setOnClickListener(new View.OnClickListener()
        {
            Intent intent = new Intent(InicioNube.this, ListarStorage.class);
            @Override
            public void onClick(View v)
            {
                startActivity(intent);
            }
        });
    }

    public void baseDeDatos(){
        sqlite = new SQLite(InicioNube.this);
        sqlite.abrir();
        String usuario="a";

        Cursor cursor = sqlite.getMultimedia(usuario);
        reg = sqlite.getMultimedia(cursor);
        imagenes = sqlite.getImagenes(cursor);
    }

    public void cargarImagen(String imagen) {
        try {
            File filePhoto = new File(imagen);
            Uri uriPhoto = FileProvider.getUriForFile(InicioNube.this, "com.example.safehawk", filePhoto);
            if (uriPhoto != null)
            {
                StorageReference filePath = storageReference.child("Fotos").child(uriPhoto.getLastPathSegment());
                filePath.putFile(uriPhoto).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(InicioNube.this, "Foto agregada.", Toast.LENGTH_SHORT).show();
                    }
                });
            }else
            {
                Toast.makeText(InicioNube.this, "Foto no agregada.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex) {
            //Toast.makeText(Galeria.this, "", Toast.LENGTH_SHORT).show();
            Log.d("Cargar Imagen", "Error al cargar imagen: " + imagen + "\nMensaje: " + ex.getMessage() + "\nCausa: " + ex.getCause());
        }

    }
}