package com.example.safehawk;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.example.safehawk.SQLite.SQLite;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AgregarImagen extends AppCompatActivity
{

    public static final int REQUEST_TAKE_PHOTO = 1;
    String imagen = "n", currentPhotoPath, nombrefoto;
    Uri photoURI;
    Button btnFoto, btnGuardar;
    ImageView ivFoto;
    SQLite sqlite;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregra_imagen);
        btnFoto=findViewById(R.id.btnFoto);
        ivFoto=findViewById(R.id.ivFoto);
        btnGuardar=findViewById(R.id.btnGuardar);


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlite = new SQLite(AgregarImagen.this);
                sqlite.abrir();
                int id= (int) Math.floor(Math.random()*1000+1);
                String usuario="a";

                Toast.makeText(AgregarImagen.this,
                        imagen,
                        Toast.LENGTH_SHORT).show();

                if(!imagen.equals("")){

                    if (sqlite.addImagenes(id,usuario,imagen)){
                        Toast.makeText(AgregarImagen.this,
                                        "REGISTRO AÑADIDO",
                                            Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(AgregarImagen.this,
                                "ERROR AL REGISTAR",
                                Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(AgregarImagen.this,
                            "No hay foto para guardar",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });


        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tomarfoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //Se comprueba que se encontro una actividad para genera la foto

                File photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException ex) {
                    Toast.makeText(AgregarImagen.this,
                            "Ocurrio un error mientras se generaba el archivo", Toast.LENGTH_SHORT).show();
                }
                //se comprueba que la imagen fue creada correctamente
                if (photoFile != null) {
                    photoURI = FileProvider.getUriForFile(AgregarImagen.this,
                            "com.example.safehawk", photoFile);
                    tomarfoto.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(tomarfoto, REQUEST_TAKE_PHOTO);
                }
            }

        });
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            // Bundle extras = data.getExtras();
            // Bitmap imageBitmap = (Bitmap) extras.get("data");
            ivFoto.setImageURI(photoURI);
            imagen = currentPhotoPath;

        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        nombrefoto=imageFileName;
        File storageDir = this.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }


}
