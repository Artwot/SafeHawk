package com.example.safehawk.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.safehawk.AgregarImagen;
import com.example.safehawk.AgregarPassword;
import com.example.safehawk.AgregarUbicacion;
import com.example.safehawk.Agregar_notas;
import com.example.safehawk.LoginNube;
import com.example.safehawk.R;
import com.example.safehawk.SQLite.SQLite;
import com.example.safehawk.ui.imagenes.Imagenes;
import com.example.safehawk.ui.registro.RegistroFragment;

public class HomeFragment extends Fragment
{
    Button btn_img;
    Button btn_video;
    Button btn_docs;
    Button btn_pass;
    Button btn_nube;
    Button btn_ubicacion;


    private HomeViewModel homeViewModel;

    SQLite sqlite;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        //sqlite = new SQLite(getActivity());
        //sqlite.abrir();

        btn_img = root.findViewById(R.id.btn_imagen);
        btn_video = root.findViewById(R.id.btn_video);
        btn_docs = root.findViewById(R.id.btn_docs);
        btn_pass = root.findViewById(R.id.btn_password);
        btn_nube = root.findViewById(R.id.btn_nube);
        btn_ubicacion = root.findViewById(R.id.btn_ubicacion);

        btn_img.setOnClickListener(new View.OnClickListener()
        {
            Intent img = new Intent(getActivity(), AgregarImagen.class);
            @Override
            public void onClick(View v)
            {
                startActivity(img);
            }
        });

        btn_video.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Crea el nuevo fragmento y la transacción.
                Fragment nuevoFragmento = new Imagenes();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, nuevoFragmento);
                transaction.addToBackStack(null);

                // Commit a la transacción
                transaction.commit();
            }
        });

        btn_docs.setOnClickListener(new View.OnClickListener()
        {
            Intent intent = new Intent(getActivity(), Agregar_notas.class);
            @Override
            public void onClick(View v)
            {
                startActivity(intent);
            }
        });

        btn_pass.setOnClickListener(new View.OnClickListener()
        {
            Intent intent = new Intent(getActivity(), AgregarPassword.class);
            @Override
            public void onClick(View v)
            {
                startActivity(intent);
            }
        });

        btn_nube.setOnClickListener(new View.OnClickListener()
        {
            Intent intent = new Intent(getActivity(), LoginNube.class);
            @Override
            public void onClick(View v)
            {
                startActivity(intent);
            }
        });

        btn_ubicacion.setOnClickListener(new View.OnClickListener()
        {
            Intent intent = new Intent(getActivity(), AgregarUbicacion.class);
            @Override
            public void onClick(View v)
            {
                startActivity(intent);
            }
        });

        return root;
    }
}
