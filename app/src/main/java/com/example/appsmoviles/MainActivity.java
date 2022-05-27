package com.example.appsmoviles;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    ImageButton recordatorio,configuracion;
    TextView tv1;
    ListView rv1;

    String[] recordatorios = {"Lunes 3, Llevar al veterinario", "Lunes 10, poner vacuna", "Lunes 17, Esterilizar"};

    //Arreglo para crear los perfiles
    ListView lst;
    String[][] a =
    {
            {"Don Gato","Mako"},//nombre
            {"Gato","Gato"},//especie
            {"Ayer","Mañana"},//fecha de nacimiento
            {"Gata Chica","Gata Chica"}, //
            {"Macho","Hembra"},
            {"Si","Si"}
    };
    //Esta imagen esta hardcodeada, debería estar en la BD y ser accedida

    Integer[] imgid = {R.drawable.iconopatita,R.drawable.iconopatita};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_mascotas);

        //loadData();
        /*Linea del Eric
        tv1 = (TextView)findViewById(R.id.tv1);
        rv1 = (ListView)findViewById(R.id.rv1);


        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, R.layout.list_item_recordatorio, recordatorios);
        rv1.setAdapter(adapter);
        */

        //Abrimos la base de datos 'DBUsuarios' en modo lectura-escritura
        SQLiteHelper usdbh = new SQLiteHelper(this, "DBUsuarios", null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();

        //Obtenemos los elementos para la listview
        Cursor cursorMascotas = db.rawQuery("SELECT * FROM Mascotas",null);

        //Creamos Listas de atributos de mascotas
        LinkedList Nombres = new LinkedList();
        LinkedList Especies = new LinkedList();
        LinkedList Sexos = new LinkedList();
        LinkedList Fechas = new LinkedList();
        LinkedList Razas = new LinkedList();
        LinkedList Esterilizados = new LinkedList();

        //LLenamos las listas de atributos con la BD
        cursorMascotas.moveToFirst();
        boolean flag = true;
        while(flag){
            Nombres.add(cursorMascotas.getString(1));
            Especies.add(cursorMascotas.getString(2));
            Sexos.add(cursorMascotas.getString(3));
            Fechas.add(cursorMascotas.getString(4));
            Razas.add(cursorMascotas.getString(5));
            Esterilizados.add(cursorMascotas.getString(6));
            if (cursorMascotas.isLast()){
                flag = false;
            }
            cursorMascotas.moveToNext();
        }

        //Aqui le pasamos las mascotas a PerfilClass
        lst=(ListView) findViewById(R.id.mylistview);//lo estoy haciendo diferente al profe, realizando un casteo
        PerfilClass perfilClass = new PerfilClass(this,Nombres,Especies,Sexos,Fechas,Razas,Esterilizados,imgid);
        lst.setAdapter(perfilClass);










        recordatorio= (ImageButton) findViewById(R.id.recordatorio);
        recordatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( MainActivity.this, recordatorios.class);
                startActivity(i);
            }
        });

        /*

        siguiente=(Button)findViewById(R.id.siguiente);
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( MainActivity.this, recordatorios.class);
                startActivity(i);
            }
        });

        configuracion=(Button)findViewById(R.id.configuracion);
        configuracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent( MainActivity.this, configuracion.class);
                startActivity(j);
            }
        });

        archivos=(Button)findViewById(R.id.archivos);
        archivos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent z = new Intent( MainActivity.this, archivos.class);
                startActivity(z);
            }
        });*/
    }
}