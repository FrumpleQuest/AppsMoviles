package com.example.appsmoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    ImageButton recordatorio,configuracion;
    //FloatingActionButton Material Design;
    FloatingActionButton plus_Button;
    TextView tv1;
    ListView rv1;

    //Arreglo para crear los perfiles
    ListView lst;

    //Esta imagen esta hardcodeada, debería estar en la BD y ser accedida
    Integer[] imgid = {R.drawable.makoimg,R.drawable.iconopatita,R.drawable.iconopatita};
    @Override
    public void onResume(){
        super.onResume();
        Log.d("Resumido","Resumido be like");
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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_mascotas);







        recordatorio= (ImageButton) findViewById(R.id.recordatorio);
        recordatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( MainActivity.this, recordatorios.class);
                startActivity(i);
            }
        });

        configuracion= (ImageButton) findViewById(R.id.configuracion);
        configuracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( MainActivity.this, configuracion.class);
                startActivity(i);
            }
        });

        FloatingActionButton fab = findViewById(R.id.floating_action_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( MainActivity.this, PetAdd.class);
                startActivity(i);
            }
        });
    }

}