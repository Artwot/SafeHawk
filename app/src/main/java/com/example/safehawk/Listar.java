package com.example.safehawk;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;

import com.example.safehawk.SQLite.SQLite;

import java.util.ArrayList;

public class Listar extends AppCompatActivity
{
    SQLite sqlite;
    ArrayList<String> reg;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
    }
/*
    public void baseDeDatos(){
        sqlite = new SQLite(Listar.this);
        sqlite.abrir();
        String usuario="a";

        Cursor cursor = sqlite.getUbicaciones(usuario);
        reg = sqlite.getUbicaciones(cursor);
        imagenes = sqlite.getImagenes(cursor);
    }*/
}