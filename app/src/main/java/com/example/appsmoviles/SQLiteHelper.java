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
            "Titulo TEXT," +
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
    //AQUI HAY QUE ARREGLAR PARA QUE TENGA LA DIRECCION DE LOS ARCHIVOS Y EL NOMBRE
    String sqlCreateArchivos = "CREATE TABLE Archivos " +
            "(ID_Archivo INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "ID_Mascota INTEGER, " +
            "Nombre TEXT, " +
            "CONSTRAINT fk_mascotas FOREIGN KEY (ID_Mascota) REFERENCES Mascotas(ID_Mascota))";

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void addDatosIniciales(SQLiteDatabase db){
        //Insertamos a la Mako
        ContentValues mako = new ContentValues(6);
        mako.put("Nombre", "Mako");
        mako.put("Especie", "Gata Mala");
        mako.put("Sexo", "Hembra");
        mako.put("FechaNacimiento", "2012-12-15");
        mako.put("Raza", "GataPeliCorta");
        mako.put("Esterilizado", 1);
        db.insert("Mascotas",null, mako);
        //Insertamos al don Gato
        ContentValues donGato = new ContentValues(6);
        donGato.put("Nombre", "Don Gato");
        donGato.put("Especie", "Gato Bueno");
        donGato.put("Sexo", "Macho");
        donGato.put("FechaNacimiento", "2017-01-03");
        donGato.put("Raza", "GatoPeliLargo");
        donGato.put("Esterilizado", 0);
        db.insert("Mascotas",null, donGato);

        //Creamos 3 recordatorios para la demostración :)
        ContentValues recordatorio = new ContentValues(4);
        recordatorio.put("ID_Mascota",1);
        recordatorio.put("Titulo", "Mako: Vacuna");
        recordatorio.put("Subtitulo", "Subtitulo: Triple Felina");
        recordatorio.put("Fecha","2022-08-01");
        db.insert("Recordatorios",null, recordatorio);

        ContentValues recordatorio2 = new ContentValues(4);
        recordatorio2.put("ID_Mascota",2);
        recordatorio2.put("Titulo", "Don Gato: Veterinario");
        recordatorio2.put("Subtitulo", "Subtitulo: Hospital Veterinario Valparaiso");
        recordatorio2.put("Fecha","2022-09-15");
        db.insert("Recordatorios",null, recordatorio2);

        ContentValues recordatorio3 = new ContentValues(4);
        recordatorio3.put("ID_Mascota",2);
        recordatorio3.put("Titulo", "Don Gato: Comprar Comida");
        recordatorio3.put("Subtitulo", "Subtitulo: Nutragold");
        recordatorio3.put("Fecha","2022-06-02");
        db.insert("Recordatorios",null, recordatorio3);

        ContentValues makoPeso = new ContentValues(4);
        makoPeso.put("ID_Mascota", 1);
        makoPeso.put("Nombre", "Peso");
        makoPeso.put("EjeX", "Fecha ");
        makoPeso.put("EjeY", "Peso [Kg]");
        db.insert("Categorias",null,makoPeso);

        ContentValues archivo = new ContentValues(2);
        archivo.put("ID_Mascota", 1);
        archivo.put("Nombre", "Nombre_Archivo.jepeg");
        db.insert("Archivos",null,archivo);

        ContentValues dato = new ContentValues(2);
        dato.put("ID_Categoria", 1);
        dato.put("Valor", 5);
        db.insert("Datos",null,dato);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        Log.d("CreadaDB","Base de Datos Creada");

        //Creamos las tablas de la BD
        db.execSQL(sqlCreateMascotas);
        db.execSQL(sqlCreateCategorias);
        db.execSQL(sqlCreateArchivos);
        db.execSQL(sqlCreateRecordatorios);
        db.execSQL(sqlCreateDato);

        //Poblamos la base de datos con un dato para cada Tabla
        addDatosIniciales(db);
        //accedemos a los valores de la bd para testear que todo va bien
        Cursor makotestcursor = db.rawQuery("SELECT * FROM Mascotas",null);
        makotestcursor.moveToFirst();
        Log.d("CreadaDB", makotestcursor.getString(1));
        makotestcursor.moveToNext();
        Log.d("CreadaDB", makotestcursor.getString(1));

        Cursor rectestcursor= db.rawQuery("SELECT * FROM Recordatorios",null);
        rectestcursor.moveToFirst();
        Log.d("CreadaDB", rectestcursor.getString(2));
        rectestcursor.moveToNext();
        Log.d("CreadaDB", rectestcursor.getString(2));
        rectestcursor.moveToNext();
        Log.d("CreadaDB", rectestcursor.getString(2));

        Cursor Pesotestcursor= db.rawQuery("SELECT * FROM Categorias",null);
        Pesotestcursor.moveToFirst();
        Log.d("CreadaDB", Pesotestcursor.getString(2));

        Cursor arctestcursor= db.rawQuery("SELECT * FROM Archivos",null);
        arctestcursor.moveToFirst();
        Log.d("CreadaDB", arctestcursor.getString(2));

        Cursor datotestcursor= db.rawQuery("SELECT * FROM Datos",null);
        datotestcursor.moveToFirst();
        Log.d("CreadaDB",datotestcursor.getString(2));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        Log.d("ActualizadaDB","Base de Datos Actualizada");
    }
}
