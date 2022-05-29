package com.example.appsmoviles;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class recordatorios extends AppCompatActivity {

    ListView mylistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordatorios);
        mylistview = (ListView) findViewById(R.id.rv1);

        ImageButton Initial;

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
        mylistview.setAdapter(adapter);

        Initial = (ImageButton) findViewById(R.id.principal_layout);
        Initial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(recordatorios.this, MainActivity.class);
                startActivity(i);
            }
        });

        FloatingActionButton fab1 = findViewById(R.id.floating_action_button_2);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( recordatorios.this, RecordatorioAdd.class);
                startActivity(i);
            }
        });


    }
}