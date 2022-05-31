package com.example.appsmoviles;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

public class RecordatorioAdd extends AppCompatActivity {
    ImageButton record1, Initial;
    ExtendedFloatingActionButton boton_agregar;
    TextInputLayout tv1,tv2,tv3;
    String text1,text2,text3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recordatorio_add);

        boton_agregar = findViewById(R.id.extended_fab);
        boton_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Aqui almacenamos los valores en la BD
                tv1 = findViewById(R.id.text_rec_rec);
                text1 = tv1.getEditText().getText().toString();
                tv2 = findViewById(R.id.text_rec_fecha);
                text2 = tv2.getEditText().getText().toString();
                tv3 = findViewById(R.id.text_rec_obs);
                text3 = tv3.getEditText().getText().toString();

                SQLiteHelper usdbh = new SQLiteHelper(getApplicationContext(), "DBUsuarios", null, 1);
                SQLiteDatabase db = usdbh.getWritableDatabase();

                ContentValues nuevorecordatorio = new ContentValues(4);
                //ESTA ID MASCOTA DEBERÍA DEPENDER DE LA MASCOTA QUE PONES EN LA CREACION
                nuevorecordatorio.put("ID_Mascota",1);
                nuevorecordatorio.put("Titulo", "Mako: " + text1);
                nuevorecordatorio.put("Subtitulo", "Subtitulo: " + text3);
                nuevorecordatorio.put("Fecha",text2);
                db.insert("Recordatorios",null, nuevorecordatorio);

                Intent i = new Intent( RecordatorioAdd.this, recordatorios.class);
                startActivity(i);
            }
        });

        //-----------------------------Codigo duplicado-----------------------------------
        record1= (ImageButton) findViewById(R.id.recordatorio);
        record1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( RecordatorioAdd.this, recordatorios.class);
                startActivity(i);
            }
        });
        Initial = (ImageButton) findViewById(R.id.principal_layout);
        Initial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(RecordatorioAdd.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
