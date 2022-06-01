package com.example.appsmoviles;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class recordatorios extends AppCompatActivity {





    @Override
    public void onResume(){
        super.onResume();
        //Ahora crear la listview ocurre en una funcion aparte
        crearListview();

        //Codigo duplicado
        ImageButton recordatorio= (ImageButton) findViewById(R.id.recordatorio);
        recordatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Aqui no hace nada pq estamos en recordatorios
            }
        });

        ImageButton configuracion= (ImageButton) findViewById(R.id.configuracion);
        configuracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( recordatorios.this, configuracion.class);
                startActivity(i);
            }
        });

        ImageButton boton_main= (ImageButton) findViewById(R.id.main_boton);
        boton_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( recordatorios.this, MainActivity.class);
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordatorios);


    }
    public void crearListview(){
        ListView mylistview = (ListView) findViewById(R.id.rv1);

        //Abrimos la base de datos 'DBUsuarios' en modo lectura-escritura
        SQLiteHelper usdbh = new SQLiteHelper(this, "DBUsuarios", null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();

        //Obtenemos los elementos desde la BD
        Cursor c = db.rawQuery("SELECT * FROM Recordatorios",null);
        c.moveToFirst();

        //LLenamos la lista con la BD (Aqui habría que tener 3 listas para los 3 atributos)
        HashMap<String, String> nameAddresses = new HashMap<>();
        boolean flag = true;
        if (c.isAfterLast()) flag = false; //Aqui por si no hay recordatorios en la lista
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
        mylistview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                HashMap<String, String> itemValue = lisitems.get(i);

                AlertDialog.Builder popup = new AlertDialog.Builder(recordatorios.this);
                popup.setMessage("Quiere eliminar este recordatorio?").setCancelable(false);
                popup.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        SQLiteHelper usdbh = new SQLiteHelper(getApplicationContext(), "DBUsuarios", null, 1);
                        SQLiteDatabase db = usdbh.getWritableDatabase();
                        String tabla = "Recordatorios";
                        String whereClause = "Subtitulo=?";
                        String[] whereArgs = new String[] {itemValue.get("second")};
                        db.delete(tabla, whereClause, whereArgs);

                        Log.d("test_delete", itemValue.get("second"));
                        dialog.dismiss();
                        //Aqui se refresca la listview
                        crearListview();
                        //Intent refresh = new Intent( recordatorios.this, recordatorios.class);
                        //startActivity(refresh);
                    }
                });
                popup.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = popup.create();
                dialog.setTitle("Eliminar Recordatorio");
                dialog.show();

                return false;
            }
        });
    }
}