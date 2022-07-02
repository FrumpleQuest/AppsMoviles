package com.example.appsmoviles;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class estadisticas extends AppCompatActivity {

    String[] strings = {"Opcion 1", "Opcion 2", "Opcion 3"};
    TextView nombreMascota;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.estadisticas);
        nombreMascota=(TextView) findViewById(R.id.name_statistics_pet);
        Bundle bundle = getIntent().getExtras();
        String nombre = getIntent().getExtras().getString("id");
        nombreMascota.setText(nombre);



        //Abrimos la base de datos 'DBUsuarios' en modo lectura-escritura
        SQLiteHelper usdbh = new SQLiteHelper(this, "DBUsuarios", null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();

        String[] arreglo = new String[] {nombre};
        Cursor c = db.rawQuery("SELECT * FROM Mascotas WHERE Nombre = ?",arreglo);
        c.moveToFirst();

        TextView fecha = (TextView) findViewById(R.id.stats_fecha);
        TextView sexo = (TextView) findViewById(R.id.stats_sexo);
        TextView especie = (TextView) findViewById(R.id.stats_especie);
        TextView raza = (TextView) findViewById(R.id.stats_raza);
        TextView esterilizado = (TextView) findViewById(R.id.stats_color);

        String siono;
        if(c.getString(6) == "1") siono = "si";
        else siono = "no";

        fecha.setText("Fecha de Nacimiento: " + c.getString(4));
        sexo.setText("Sexo: " + c.getString(3));
        especie.setText("Especie: " + c.getString(2));
        raza.setText("Raza: " + c.getString(5));
        esterilizado.setText("Esterilizada/o: " + siono);


        //-----------------------------Codigo duplicado-----------------------------------
        //Codigo duplicado
        ImageButton recordatorio= (ImageButton) findViewById(R.id.recordatorio);
        recordatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( estadisticas.this, recordatorios.class);
                startActivity(i);
            }
        });

        ImageButton configuracion= (ImageButton) findViewById(R.id.configuracion);
        configuracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( estadisticas.this, configuracion.class);
                startActivity(i);
            }
        });

        ImageButton boton_main= (ImageButton) findViewById(R.id.main_boton);
        boton_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( estadisticas.this, MainActivity.class);
                startActivity(i);
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();

        Spinner spin = (Spinner) findViewById(R.id.spinner);
        //Log.d("spinner ql","hola");
        ArrayAdapter adaptador = new ArrayAdapter(this,android.R.layout.simple_spinner_item,strings);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adaptador);
    }


    public void verArchivosOnClick(View view) {
        Intent i = new Intent( estadisticas.this, archivos.class);
        startActivity(i);
    }

    public void addCategoria(View view) {
                LinearLayout linear = new LinearLayout(this);
                linear.setOrientation(LinearLayout.VERTICAL);
                EditText text = new EditText(this);
                EditText text2 = new EditText(this);
                EditText text3 = new EditText(this);


                text.setHint("Nombre de Categoría");
                text2.setHint("Nombre del Eje X");
                text3.setHint("Nombre del Eje Y");
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(50,0,50,0);
                text.setLayoutParams(layoutParams);
                text2.setLayoutParams(layoutParams);
                text3.setLayoutParams(layoutParams);
                linear.addView(text);
                linear.addView(text2);
                linear.addView(text3);


                AlertDialog.Builder popup = new AlertDialog.Builder(estadisticas.this);
                popup.setView(linear).setCancelable(true);
                popup.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        final String categoria = text.getText().toString();
                        final String Ejey = text.getText().toString();
                        final String Ejex = text.getText().toString();


                    }
                });
                popup.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog dialog = popup.create();
                dialog.setTitle("Añadir Categoria");
                dialog.show();


    }

    public void agregarDato(View view) {
        Intent i = new Intent(estadisticas.this,addDato.class);
        startActivity(i);
    }
}
