package com.example.safehawk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.safehawk.ui.registro.RegistroFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginNube extends AppCompatActivity
{
    private EditText edEmail, edPassword;
    private Button btnIngresar;
    private TextView reg;

    private String email = "";
    private String pass = "";

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_nube);

        edEmail = findViewById(R.id.id_correoL);
        edPassword = findViewById(R.id.id_passL);
        btnIngresar = findViewById(R.id.id_ingresar);
        reg = findViewById(R.id.id_reg);

        auth = FirebaseAuth.getInstance();

        //email = edEmail.getText().toString().trim();
        //pass = edPassword.getText().toString().trim();

        btnIngresar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                email = edEmail.getText().toString().trim();
                pass = edPassword.getText().toString().trim();
                if (!email.isEmpty() && !pass.isEmpty())
                {
                    loginUser();
                }
                else
                {
                    Toast.makeText(LoginNube.this, "Complete los campos.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        reg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getSupportFragmentManager().beginTransaction()
                        .add(android.R.id.content, new RegistroFragment()).commit();
            }
        });
    }

    private void loginUser()
    {
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {
                    Intent intent = new Intent(LoginNube.this, InicioNube.class);
                    Toast.makeText(LoginNube.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                }else
                {
                    Toast.makeText(LoginNube.this, "Correo o contraseña incorrectos.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void registrarse(View view)
    {
        Intent intent = new Intent(this, RegistroFragment.class);
        startActivity(intent);
        finish();
    }

}