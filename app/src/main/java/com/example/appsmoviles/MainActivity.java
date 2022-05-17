package com.example.appsmoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button siguiente, configuracion, archivos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_mascotas);

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