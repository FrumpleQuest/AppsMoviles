package com.example.appsmoviles;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper{

    //Aqui faltan los CREATE de las otras tablas
    String sqlCreateMascotas = "CREATE TABLE Mascotas " +
            "(ID_Mascota INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "Nombre TEXT, " +
            "Especie TEXT, " +
            "Sexo INTEGER, " +
            "FechaNacimiento TEXT, " +
            "Raza TEXT, " +
            "Esterilizado INTEGER" +
            ")";
    String sqlCreateRecordatorios = "CREATE TABLE Recordatorios " +
            "(ID_Recordatorio INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "ID_Mascota INTEGER, " +
            "Titulo TEXT" +
            "Subtitulo TEXT, " +
            "Fecha TEXT, " +
            "CONSTRAINT fk_mascotas FOREIGN KEY (ID_Mascota) REFERENCES Mascotas(ID_Mascota))";

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        Log.d("CreadaDB","Base de Datos Creada");

        //Aqui faltan los CREATE de las otras tablas
        db.execSQL(sqlCreateMascotas);

        ContentValues valores = new ContentValues(7);
        valores.put("ID_Mascota", 1);
        valores.put("Nombre", "Mako");
        valores.put("Especie", "GataMala");
        valores.put("Sexo", "Hembra");
        valores.put("FechaNacimiento", "Ayer");
        valores.put("Raza", "GataPeliCorta");
        valores.put("Esterilizado", 1);

        db.insert("Mascotas",null, valores);

        Cursor makotestcursor = db.rawQuery("SELECT * FROM Mascotas",null);
        makotestcursor.moveToFirst();

        Log.d("CreadaDB", makotestcursor.getString(1));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        Log.d("ActualizadaDB","Base de Datos Actualizada");
    }
}
