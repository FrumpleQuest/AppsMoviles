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
    String sqlCreateCategorias = "CREATE TABLE Categorias " +
            "(ID_Categoria INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "ID_Mascota INTEGER, " +
            "Nombre TEXT, " +
            "EjeX TEXT, " +
            "EjeY TEXT, " +
            "CONSTRAINT fk_mascotas FOREIGN KEY (ID_Mascota) REFERENCES Mascotas(ID_Mascota))";
    String sqlCreateDato = "CREATE TABLE Datos " +
            "(ID_Dato INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "ID_Categoria INTEGER, " +
            "Valor INTEGER, " +
            "CONSTRAINT fk_categorias FOREIGN KEY (ID_Categoria) REFERENCES Categorias(ID_Categoria))";

    String sqlCreateArchivos = "CREATE TABLE Archivos " +
            "(ID_Archivo INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "Nombre TEXT, " +
            "ID_Mascota INTEGER, " +
            "CONSTRAINT fk_mascotas FOREIGN KEY (ID_Mascota) REFERENCES Mascotas(ID_Mascota))";

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void addDatosIniciales(SQLiteDatabase db){
        //Insertamos a la Mako
        ContentValues mako = new ContentValues(7);
        mako.put("ID_Mascota", 1);
        mako.put("Nombre", "Mako");
        mako.put("Especie", "GataMala");
        mako.put("Sexo", "Hembra");
        mako.put("FechaNacimiento", "Ayer");
        mako.put("Raza", "GataPeliCorta");
        mako.put("Esterilizado", 1);

        db.insert("Mascotas",null, mako);

        ContentValues categoriaPeso = new ContentValues(1);
        ContentValues recordatorio = new ContentValues(1);
        ContentValues archivo = new ContentValues(1);
        ContentValues dato = new ContentValues(1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        Log.d("CreadaDB","Base de Datos Creada");

        db.execSQL(sqlCreateMascotas);
        db.execSQL(sqlCreateCategorias);
        db.execSQL(sqlCreateArchivos);
        db.execSQL(sqlCreateRecordatorios);
        db.execSQL(sqlCreateDato);

        //Poblamos la base de datos con un dato para cada Tabla
        addDatosIniciales(db);

        Cursor makotestcursor = db.rawQuery("SELECT * FROM Mascotas",null);
        makotestcursor.moveToFirst();

        Log.d("CreadaDB", makotestcursor.getString(1));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        Log.d("ActualizadaDB","Base de Datos Actualizada");
    }
}
