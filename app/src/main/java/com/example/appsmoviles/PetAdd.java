package com.example.appsmoviles;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;


public class PetAdd extends AppCompatActivity {
    ImageButton recordatorio, Initial;
    ExtendedFloatingActionButton boton_agregar_pet;
    TextInputLayout tv1,tv2,tv3,tv4,tv5,tv6,tv7;
    String text1,text2,text3,text4,text5,text6,text7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet_add);

        boton_agregar_pet = findViewById(R.id.boton_add_pet);
        boton_agregar_pet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Aqui almacenamos los valores en la BD

                tv1 = findViewById(R.id.text_add_pet);
                text1 = tv1.getEditText().getText().toString();
                tv2 = findViewById(R.id.text_add_especie);
                text2 = tv2.getEditText().getText().toString();
                tv3 = findViewById(R.id.text_add_sexo);
                text3 = tv3.getEditText().getText().toString();
                tv4 = findViewById(R.id.text_add_fecha);
                text4 = tv4.getEditText().getText().toString();
                tv5 = findViewById(R.id.text_add_raza);
                text5 = tv5.getEditText().getText().toString();
                tv6 = findViewById(R.id.text_add_color);
                text6 = tv6.getEditText().getText().toString();
                tv7 = findViewById(R.id.text_add_obs);
                text7 = tv7.getEditText().getText().toString();

                Log.d("add_mascotas",text1 + text2 + text3 + text4 + text5 + text6 + text7);

                SQLiteHelper usdbh2 = new SQLiteHelper(getApplicationContext(), "DBUsuarios", null, 1);
                SQLiteDatabase db2 = usdbh2.getWritableDatabase();
                ContentValues nueva_pet = new ContentValues(6);
                nueva_pet.put("Nombre",text1);
                nueva_pet.put("Especie", text2);
                nueva_pet.put("Sexo", text3);
                nueva_pet.put("FechaNacimiento",text4);
                nueva_pet.put("Raza", text5);
                nueva_pet.put("Esterilizado",1);
                db2.insert("Mascotas",null, nueva_pet);
                Intent i = new Intent( PetAdd.this, MainActivity.class);
                startActivity(i);
            }
        });
        //-----------------------------Codigo duplicado-----------------------------------
        //Codigo duplicado
        ImageButton recordatorio= (ImageButton) findViewById(R.id.recordatorio);
        recordatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( PetAdd.this, recordatorios.class);
                startActivity(i);
            }
        });

        ImageButton configuracion= (ImageButton) findViewById(R.id.configuracion);
        configuracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( PetAdd.this, configuracion.class);
                startActivity(i);
            }
        });

        ImageButton boton_main= (ImageButton) findViewById(R.id.main_boton);
        boton_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( PetAdd.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}