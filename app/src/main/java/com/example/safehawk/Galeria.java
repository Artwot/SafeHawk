package com.example.safehawk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.safehawk.SQLite.SQLite;

import java.io.File;
import java.util.ArrayList;

public class Galeria extends AppCompatActivity
{
    ImageView ivGaleria,izq, der;
    int position=0;
    ArrayList<String> imagenes;
    SQLite sqlite;
    ArrayList<String> reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);

        ivGaleria=findViewById(R.id.ivGaleria);
        izq=findViewById(R.id.btnIzq);
        der=findViewById(R.id.btnDer);


        baseDeDatos();


        cargarImagen(imagenes.get(position),ivGaleria );



        izq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position--;
                cargarImagen(imagenes.get(position),ivGaleria );
            }
        });

        der.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position++;
                cargarImagen(imagenes.get(position),ivGaleria );
            }
        });


    }


    public void baseDeDatos(){
        sqlite = new SQLite(Galeria.this);
        sqlite.abrir();
        String usuario="a";

        Cursor cursor = sqlite.getMultimedia(usuario);
        reg = sqlite.getMultimedia(cursor);
        imagenes = sqlite.getImagenes(cursor);
    }


    //cargar imagen
    public void cargarImagen(String imagen, ImageView iv) {
        try {
            File filePhoto = new File(imagen);
            Uri uriPhoto = FileProvider.getUriForFile(Galeria.this, "com.example.safehawk", filePhoto);
            iv.setImageURI(uriPhoto);
        } catch (Exception ex) {
            //Toast.makeText(Galeria.this, "", Toast.LENGTH_SHORT).show();
            Log.d("Cargar Imagen", "Error al cargar imagen: " + imagen + "\nMensaje: " + ex.getMessage() + "\nCausa: " + ex.getCause());
        }

    }




    /**/
}