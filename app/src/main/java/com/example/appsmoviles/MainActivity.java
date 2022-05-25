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

<<<<<<< HEAD
    String[] recordatorios = {"Lunes 3, Llevar al veterinario", "Lunes 10, poner vacuna", "Lunes 17, Esterilizar"};
=======
    //Recordatorios base sin acceso a BD
    String recordatorios [] = {"Lunes 3, Llevar al veterinario", "Lunes 10, poner vacuna", "Lunes 17, Esterilizar"};
>>>>>>> 2e35308796ad0614f83f0f5b244b2fd61f964c5a

    //Arreglo para crear los perfiles
    ListView lst;
    String[][] a =
    {
            {"Don Gato","Mako"},
            {"Gato","Gato"},
            {"Ayer","Ayer"},
            {"Gata Chica","Gata Chica"},
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

        //Obtenemos los elementos para la listview (por ahora solo hay uno, falta iterar el cursor)
        Cursor cursorMascotas = db.rawQuery("SELECT * FROM Mascotas",null);
        String[] atributos = {"Nombre","Especie","Sexo","FechaNacimiento","Raza","Esterilizado"};
        LinkedList Mascotas = new LinkedList();

        cursorMascotas.moveToFirst();

        //Mandamos una mascota a perfilclass
        LinkedList pet = new LinkedList();
        int i = 0;
        while(i < 6){
            pet.addLast(cursorMascotas.getString(i+1));
            a[i][0] = cursorMascotas.getString(i+1);//Legacy
            i++;
        }

        /* Esto es para mandar a todas las mascotas a perfilclass en un futuro
        boolean flag = true;
        while(flag){
            ContentValues pet = new ContentValues(6);
            int i = 0;
            while(i < 6){
                pet.put(atributos[i],cursorMascotas.getString(i+1));
                a[i][0] = cursorMascotas.getString(i+1);//Legacy
                i++;
            }
            Mascotas.add(pet);
            if(cursorMascotas.isLast()) flag = false;
            else cursorMascotas.moveToNext();
        } */






        //Aqui le pasamos las mascotas a PerfilClass
        lst=(ListView) findViewById(R.id.mylistview);//lo estoy haciendo diferente al profe, realizando un casteo
        PerfilClass perfilClass = new PerfilClass(this,pet,a[0],a[1],a[2],a[3],a[4],imgid);
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