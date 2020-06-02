package com.example.safehawk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ListarStorage extends AppCompatActivity
{

    private ListView listView;
    private StorageReference mStorage;
    private ArrayList <String> imagenes;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_storage);

        listView = findViewById(R.id.id_list);
        imagenes = new ArrayList<>();

        //Inicialización del obj. de Firebase Storage.
        mStorage = FirebaseStorage.getInstance().getReference();

        //Referencia del bucket donde se encuentran almacenadas las imágenes.
        StorageReference ref = mStorage.child("Fotos");

        //Muestra todos los elementos existentes en Storage.
        ref.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>()
        {
            @Override
            public void onSuccess(ListResult listResult)
            {
                Log.e("Fotos", "Recuperando imágenes");
                for (StorageReference item: listResult.getItems())
                {
                    imagenes.add(item.getName() + "");
                    Log.e("Imagen: --->>>", item.getPath() + "----" +item.getName());
                }

                //Configurar el adaptador de la lista:
                ArrayAdapter <String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, imagenes);

                //Muestra el adaptador en la vista.
                listView.setAdapter(adapter);
            }
        });
    }
}