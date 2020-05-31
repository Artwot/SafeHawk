package com.example.safehawk.SQLite;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class sql extends SQLiteOpenHelper {


    private static final String database = "cajafuerte";
    private static final int VERSION = 3;

    //Constructor
    public sql(Context context) {
        super(context, database, null, VERSION);
    }

    //metodos heredados
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tUSUARIOS);
        db.execSQL(tMULTIMEDIA);
        db.execSQL(tUBICACIONES);
        db.execSQL(tTEXTOS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL("DROP TABLE IF EXISTS MULTIMEDIA");
            db.execSQL("DROP TABLE IF EXISTS TEXTOS");
            db.execSQL("DROP TABLE IF EXISTS UBICACIONES");
            db.execSQL("DROP TABLE IF EXISTS USUARIOS");
            db.execSQL(tUSUARIOS);
            db.execSQL(tMULTIMEDIA);
            db.execSQL(tUBICACIONES);
            db.execSQL(tTEXTOS);
        }
    }


    //Tabla para imagenes y videos
    private final String tMULTIMEDIA =
            "CREATE TABLE MULTIMEDIA (" +
                    "ID INTEGER PRIMARY KEY  NOT NULL," +
                    "USUARIO TEXT NOT NULL," +
                    "IMAGEN TEXT NOT NULL);";

    //Tabla para ubicaciones
    private final String tUBICACIONES =
            "CREATE TABLE UBICACIONES (" +
                    "ID INTEGER PRIMARY KEY  NOT NULL," +
                    "USUARIO TEXT NOT NULL," +
                    "NOMBRE TEXT NOT NULL," +
                    "UBICACION TEXT NOT NULL);";
    //Tabla para textos
    private final String tTEXTOS =
            "CREATE TABLE TEXTOS (" +
                    "ID INTEGER PRIMARY KEY  NOT NULL," +
                    "USUARIO TEXT NOT NULL," +
                    "NOMBRE TEXT NOT NULL," +
                    "CONTENIDO TEXT NOT NULL);";

    //Tabla para usuarios
    private final String tUSUARIOS =
            "CREATE TABLE USUARIOS (" +
                    "ID INTEGER PRIMARY KEY  NOT NULL," +
                    "USUARIO TEXT NOT NULL," +
                    "CLAVE TEXT NOT NULL," +
                    "CONTRASENIA TEXT NOT NULL);";


}
