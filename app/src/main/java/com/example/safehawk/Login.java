package com.example.safehawk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity
{

    TextView num0;
    TextView num1;
    TextView num2;
    TextView num3;
    TextView num4;
    TextView num5;
    TextView num6;
    TextView num7;
    TextView num8;
    TextView num9;
    TextView delC;
    TextView ok;
    TextInputEditText pass;

    String password = "";
    String contra = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Método que inicializa los componentes en el layout.
        initComp();
        //Método que asigna el valor de acuerdo al número persionado.
        seleccionarNum();
    }

    protected void initComp()
    {
        num0 = findViewById(R.id.id_0);
        num1 = findViewById(R.id.id_1);
        num2 = findViewById(R.id.id_2);
        num3 = findViewById(R.id.id_3);
        num4 = findViewById(R.id.id_4);
        num5 = findViewById(R.id.id_5);
        num6 = findViewById(R.id.id_6);
        num7 = findViewById(R.id.id_7);
        num8 = findViewById(R.id.id_8);
        num9 = findViewById(R.id.id_9);
        delC = findViewById(R.id.id_C);
        ok = findViewById(R.id.id_OK);;
        pass = findViewById(R.id.id_pass);
    }

    protected void seleccionarNum()
    {

        num0.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                password += "0";
                contra += "*";
                pass.setText(contra);
            }
        });

        num1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                password += "1";
                contra += "*";
                pass.setText(contra);
            }
        });

        num2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                password += "2";
                contra += "*";
                pass.setText(contra);
            }
        });

        num3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                password += "3";
                contra += "*";
                pass.setText(contra);
            }
        });

        num4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                password += "4";
                contra += "*";
                pass.setText(contra);
            }
        });

        num5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                password += "5";
                contra += "*";
                pass.setText(contra);
            }
        });

        num6.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                password += "6";
                contra += "*";
                pass.setText(contra);
            }
        });

        num7.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                password += "7";
                contra += "*";
                pass.setText(contra);
            }
        });

        num8.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                password += "8";
                contra += "*";
                pass.setText(contra);
            }
        });

        num9.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                password += "9";
                contra += "*";


                pass.setText(contra);
            }
        });

        delC.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (password.length() > 0)
                {
                    password = password.substring(0, password.length() - 1);
                }
                if (contra.length() > 0)
                {
                    contra = contra.substring(0, contra.length() - 1);
                    pass.setText(contra);
                }
            }
        });

        ok.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
