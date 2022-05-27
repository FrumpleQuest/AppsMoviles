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
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class recordatorios extends AppCompatActivity {

    ListView mylistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordatorios);
        mylistview = (ListView) findViewById(R.id.rv1);

        //Abrimos la base de datos 'DBUsuarios' en modo lectura-escritura
        SQLiteHelper usdbh = new SQLiteHelper(this, "DBUsuarios", null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();

        //Obtenemos los elementos desde la BD
        Cursor c = db.rawQuery("SELECT * FROM Recordatorios",null);
        c.moveToFirst();

        //LLenamos la lista con la BD (Aqui habría que tener 3 listas para los 3 atributos)
        HashMap<String, String> nameAddresses = new HashMap<>();
        boolean flag = true;
        while(flag){
            nameAddresses.put(c.getString(2) + ", " + c.getString(4), c.getString(3));
            if (c.isLast()){
                flag = false;
            }
            c.moveToNext();
        }

        List<HashMap<String, String>> lisitems= new ArrayList<>();
        SimpleAdapter adapter = new SimpleAdapter(this,
                lisitems,
                android.R.layout.simple_list_item_2,
                new String[]{"first", "second"},
                new int[]{android.R.id.text1, android.R.id.text2}
        );
        Iterator it = nameAddresses.entrySet().iterator();
        while(it.hasNext()){
            HashMap<String, String> resultMap = new HashMap<>();
            Map.Entry pair = (Map.Entry)it.next();
            resultMap.put("first", pair.getKey().toString());
            resultMap.put("second", pair.getValue().toString());
            lisitems.add(resultMap);
        }

        /*
        LinkedList<String> TitulosFechas = new LinkedList<String>();
        LinkedList<String> Subtitulos = new LinkedList<String>();
        boolean flag = true;
        while(flag){
            TitulosFechas.add(c.getString(2) + ", " + c.getString(4));
            Subtitulos.add(c.getString(3));
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
                TitulosFechas
        );
*/
        mylistview.setAdapter(adapter);

    }
}