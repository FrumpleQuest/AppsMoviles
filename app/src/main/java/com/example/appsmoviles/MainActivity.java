package com.example.appsmoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button siguiente, configuracion, archivos;
    //Arreglo para crear los perfiles
    ListView lst;
    String[] Name={"Don Gato","Mako"};
    String[] Especie = {"Gato","Gato"};
    String[] Edad = {"5 años","11 años"};
    String[] Sexo = {"Macho","Hembra"};
    String[] Estirilizado = {"Si","Si"};
    Integer[] imgid = {R.drawable.iconopatita,R.drawable.iconopatita};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_mascotas);

        //loadData();
        //Acá estoy probando la list View
        lst=(ListView) findViewById(R.id.mylistview);//lo estoy haciendo diferente al profe, realizando un casteo
        PerfilClass perfilClass = new PerfilClass(this,Name,Especie,Edad,Sexo,Estirilizado,imgid);
        lst.setAdapter(perfilClass);
        //Abrimos la base de datos 'DBUsuarios' en modo escritura
        SQLiteHelper usdbh =
                new SQLiteHelper(this, "DBUsuarios", null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();

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