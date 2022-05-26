package com.example.appsmoviles;


import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedList;

public class recordatorios extends AppCompatActivity {

    ListView mylistview;

    String[] l_recordatorios = {"Lunes 3, Llevar al veterinario", "Lunes 10, poner vacuna", "Lunes 17, Esterilizar"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordatorios);


        //Abrimos la base de datos 'DBUsuarios' en modo lectura-escritura
        SQLiteHelper usdbh = new SQLiteHelper(this, "DBUsuarios", null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();

        //Obtenemos los elementos desde la BD
        Cursor c = db.rawQuery("SELECT * FROM Recordatorios",null);
        c.moveToFirst();

        //LLenamos la lista con la BD (Aqui habría que tener 3 listas para los 3 atributos)
        LinkedList<String> Titulos = new LinkedList<String>();
        boolean flag = true;
        while(flag){
            Titulos.add(c.getString(2));
            if (c.isLast()){
                flag = false;
            }
            c.moveToNext();
        }

        //Accedemos al listview
        mylistview = (ListView) findViewById(R.id.rv1);
        ArrayAdapter adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                Titulos
        );

        mylistview.setAdapter(adapter);

    }
}