package com.example.safehawk.SQLite;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.util.Log;

import java.util.ArrayList;

public class SQLite {

    private sql sql;
    private SQLiteDatabase db;

    public SQLite(Context context) {
        sql = new sql(context);
    }

    public void abrir() {
        Log.i("SQLite",
                "Se abre conexión a la base de datos" +
                        sql.getDatabaseName());
        db = sql.getWritableDatabase();
    }

    public void cerrar() {
        Log.i("SQLite",
                "Se cierra conexión a la base de datos" +
                        sql.getDatabaseName());
        sql.close();
    }

    public boolean addUsuario(
            int id,
            String nombre,
            String contraseña,
            String clave) {
        ContentValues cv = new ContentValues(); //Equivalente a putExtra
        cv.put("ID", id);
        cv.put("USUARIO", nombre);
        cv.put("CLAVE", clave);
        cv.put("CONTRASENIA", contraseña);
        return db.insert(
                "USUARIOS",
                null, cv) != -1;
    }

    public boolean addImagenes(
            int id,
            String usuario,
            String imagen) {
        ContentValues cv = new ContentValues(); //Equivalente a putExtra
        cv.put("ID", id);
        cv.put("USUARIO", usuario);
        cv.put("IMAGEN", imagen);
        return db.insert(
                "MULTIMEDIA",
                null, cv) != -1;
    }

    public boolean addUbicaciones(
            int id,
            String usuario,
            String nombre,
            String ubicacion) {
        ContentValues cv = new ContentValues(); //Equivalente a putExtra
        cv.put("ID", id);
        cv.put("USUARIO", usuario);
        cv.put("NOMBRE", nombre);
        cv.put("UBICACION", ubicacion);
        return db.insert(
                "UBICACIONES",
                null, cv) != -1;
    }


    public boolean addTextos(
            int id,
            String usuario,
            String nombre,
            String contenido) {
        ContentValues cv = new ContentValues(); //Equivalente a putExtra
        cv.put("ID", id);
        cv.put("NOMBRE", nombre);
        cv.put("USUARIO", usuario);
        cv.put("CONTENIDO", contenido);
        return db.insert(
                "TEXTOS",
                null, cv) != -1;
    }


    //Leer base de datos
    public Cursor getUsuarios() {

        return db.rawQuery("SELECT * FROM USUARIOS", null);
    }
    //
    public Cursor getUbicaciones()
    {
        return db.rawQuery("SELECT * FROM UBICACIONES", null);
    }

    public Cursor getMultimedia(String usuario)
    {
        return db.rawQuery("SELECT * FROM MULTIMEDIA WHERE USUARIO='"+usuario+"'", null);
    }

    public Cursor getTextos()
    {
        return db.rawQuery("SELECT * FROM TEXTOS", null);
    }


    public ArrayList<String> getImagenes(Cursor cursor){
        ArrayList<String> listData = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                listData.add(cursor.getString(2));
            }while (cursor.moveToNext());
        }
        return listData;
    }

    public ArrayList<String> getID(Cursor cursor) {
        ArrayList<String> listData = new ArrayList<>();
        String item = "";
        if (cursor.moveToFirst()) {
            do {
                item += "ID: [" + cursor.getString(0) + "]\r\n";
                listData.add(item);
                item = "";
            } while (cursor.moveToNext());
        }
        return listData;
    }

    public String updateUsuarios(
            int id,
            String nombre,
            String clave,
            String contraseña
    ) {
        ContentValues cv = new ContentValues(); //Equivalente a putExtra
        cv.put("ID", id);
        cv.put("NOMBRE", nombre);
        cv.put("CLAVE", clave);
        cv.put("CONTRASENIA", contraseña);
        int cant = db.update(
                "USUARIOS",
                cv,
                "ID=" + id,
                null);
        if (cant == 1) {
            return "Usuario modificado";
        } else {
            return "Error, no se modifico";
        }
    }

    public String updateTextos(
            int id,
            String usuario,
            String nombre,
            String contenido)
    {
        ContentValues cv = new ContentValues(); //Equivalente a putExtra
        cv.put("ID", id);
        cv.put("USUARIO", usuario);
        cv.put("NOMBRE", nombre);
        cv.put("CONTENIDO", contenido);
        int cant = db.update(
                "TEXTOS",
                cv,
                "ID=" + id,
                null);
        if (cant == 1) {
            return "Texto modificado";
        } else {
            return "Error, no se modifico";
        }
    }

    public Cursor getCant(int id) {
        return db.rawQuery(
                "SELECT * FROM USUARIOS WHERE ID =" + id,
                null);
    }

    public Cursor getMultimedia(int id) {
        return db.rawQuery(
                "SELECT * FROM MULTIMEDIA WHERE ID =" + id,
                null);
    }

    public int EliminarUsuarios(Editable id) {
        return db.delete(
                "USUARIOS",
                "ID=" + id,
                null);
    }

    public int EliminarTextos(Editable id) {
        return db.delete(
                "TEXTOS",
                "ID=" + id,
                null);
    }


    public int EliminarMultimedia(Editable id) {
        return db.delete(
                "MULTIMEDIA",
                "ID=" + id,
                null);
    }

    public int EliminarUbicaciones(Editable id) {
        return db.delete(
                "UBICACIONES",
                "ID=" + id,
                null);
    }


    /*
    public ArrayList<String> getUsuarios(Cursor cursor) {
        ArrayList<String> listData = new ArrayList<>();
        String item = "";
        if (cursor.moveToFirst()) {
            do {
                item += "ID: [" + cursor.getString(0) + "]\r\n";
                item += "Area: [" + cursor.getString(1) + "]\r\n";
                item += "Doctor: [" + cursor.getString(2) + "]\r\n";
                item += "Nombre: [" + cursor.getString(3) + "]\r\n";
                item += "Sexo: [" + cursor.getString(4) + "]\r\n";
                item += "Fecha de Ingreso: [" + cursor.getString(5) + "]\r\n";
                item += "Edad: [" + cursor.getString(6) + "]\r\n";
                item += "Estatura: [" + cursor.getString(7) + "]\r\n";
                item += "Peso: [" + cursor.getString(8) + "]\r\n";
                listData.add(item); //LO AGREGAMOS AL ARRAYLIST
                item = ""; //LIMPIAMOS LA CADENA ITEM
            } while (cursor.moveToNext()); //MIENTRAS LA CONSULTA TENGA DATOS
        }
        return listData;
    }
*/

    //Consulta a tabla multimedia
    public ArrayList<String> getMultimedia(Cursor cursor) {
        ArrayList<String> listData = new ArrayList<>();
        String item = "";
        if (cursor.moveToFirst()) {
            do {
                item += "ID: [" + cursor.getString(0) + "]\r\n";
                item += "Usuario: [" + cursor.getString(1) + "]\r\n";
                item += "Imagen: [" + cursor.getString(2) + "]\r\n";
                listData.add(item); //LO AGREGAMOS AL ARRAYLIST
                item = ""; //LIMPIAMOS LA CADENA ITEM
            } while (cursor.moveToNext()); //MIENTRAS LA CONSULTA TENGA DATOS
        }
        return listData;
    }


    public ArrayList<String> getUbicaciones(Cursor cursor) {
        ArrayList<String> listData = new ArrayList<>();
        String item = "";
        if (cursor.moveToFirst()) {
            do {
                item += "ID: [" + cursor.getString(0) + "]\r\n";
                item += "Usuario: [" + cursor.getString(1) + "]\r\n";
                item += "Nombre: [" + cursor.getString(2) + "]\r\n";
                item += "Ubicación: [" + cursor.getString(2) + "]\r\n";
                listData.add(item); //LO AGREGAMOS AL ARRAYLIST
                item = ""; //LIMPIAMOS LA CADENA ITEM
            } while (cursor.moveToNext()); //MIENTRAS LA CONSULTA TENGA DATOS
        }
        return listData;
    }

    public ArrayList<String> getTextos(Cursor cursor) {
        ArrayList<String> listData = new ArrayList<>();
        String item = "";
        if (cursor.moveToFirst()) {
            do {
                item += "ID: [" + cursor.getString(0) + "]\r\n";
                item += "Usuario: [" + cursor.getString(1) + "]\r\n";
                item += "Nombre: [" + cursor.getString(2) + "]\r\n";
                item += "Contenido: [" + cursor.getString(2) + "]\r\n";
                listData.add(item); //LO AGREGAMOS AL ARRAYLIST
                item = ""; //LIMPIAMOS LA CADENA ITEM
            } while (cursor.moveToNext()); //MIENTRAS LA CONSULTA TENGA DATOS
        }
        return listData;
    }

}
