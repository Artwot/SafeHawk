package com.example.safehawk.ui.registro;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.safehawk.InicioNube;
import com.example.safehawk.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegistroFragment extends Fragment
{

    private RegistroViewModel mViewModel;

    private String name = "";
    private String email = "";
    private String password = "";

    private EditText edName, edEmail, edPass;
    private Button registrar, limpiar;

    FirebaseAuth auth;
    DatabaseReference database;

    public static RegistroFragment newInstance()
    {
        return new RegistroFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.registro_fragment, container, false);

        edName = root.findViewById(R.id.id_name);
        edEmail = root.findViewById(R.id.id_mail);
        edPass = root.findViewById(R.id.id_pass);
        registrar = root.findViewById(R.id.id_reg);
        limpiar = root.findViewById(R.id.id_limpR);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();

        /*name = edName.getText().toString().trim();
        email = edEmail.getText().toString().trim();
        password = edPass.getText().toString().trim();*/

        registrar.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                name = edName.getText().toString().trim();
                email = edEmail.getText().toString().trim();
                password = edPass.getText().toString().trim();
                if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty())
                {
                    if (password.length() >= 6)
                    {
                        registrarUsuario();
                    }else
                    {
                        Toast.makeText(getActivity(), "El password debe tener al menos 6 caracteres.", Toast.LENGTH_SHORT).show();
                    }

                }else
                {
                    Toast.makeText(getActivity(), "Debe llenar todos los campos.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        limpiar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                edName.setText("");
                edEmail.setText("");
                edPass.setText("");
            }
        });

        return root;
    }

    private void registrarUsuario()
    {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", name);
                    map.put("email", email);
                    map.put("password", password);
                    String id = auth.getCurrentUser().getUid();
                    database.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2)
                        {
                            if (task2.isSuccessful())
                            {
                                Intent intent = new Intent(getActivity(), InicioNube.class);
                                Toast.makeText(getActivity(), "Registro exitoso.", Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                                getChildFragmentManager().beginTransaction()
                                        .remove(getFragmentManager().findFragmentById(R.id.content)).commit();
                            } else
                            {
                                Toast.makeText(getActivity(), "Error al registrar usuario.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else
                {
                    Toast.makeText(getActivity(), "No se pudo registrar el usuario.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RegistroViewModel.class);
        // TODO: Use the ViewModel
    }

}
