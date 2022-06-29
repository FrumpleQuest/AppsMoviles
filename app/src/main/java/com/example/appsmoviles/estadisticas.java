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
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class estadisticas extends AppCompatActivity {

    String[] strings = {"Opcion 1", "Opcion 2", "Opcion 3"};
    TextView nombreMascota;




    //String nombre = LoQueMeDeElCamilo();

    //Aqui el ton busca en la BD, obtiene las estadisticas y las presenta

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.estadisticas);
        nombreMascota=(TextView) findViewById(R.id.name_statistics_pet);
        Bundle bundle = getIntent().getExtras();
        String id = getIntent().getExtras().getString("id");
        nombreMascota.setText(id);

        Log.d("Holablobla ",id);








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
                RelativeLayout relativeLayout = new RelativeLayout(this);
                TextView textView = new TextView(this);
                textView.setText("Test");
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                textView.setLayoutParams(layoutParams);
                relativeLayout.addView(textView);

                AlertDialog.Builder popup = new AlertDialog.Builder(estadisticas.this);
                popup.setView(relativeLayout);
                popup.setMessage("Nombre de Categoria").setCancelable(true);

                AlertDialog dialog = popup.create();
                dialog.setTitle("Añadir Categoria");
                dialog.show();

                //AQUI FALTA ORDENAR EL RELATIVE LAYOUT Y LUEGO EDITAR LA BD PARA AGREGAR LA CATEGORIA

                /*
                final EditText nombre_categoria = new EditText(estadisticas.this);
                final EditText eje_x = new EditText(estadisticas.this);
                final EditText eje_y = new EditText(estadisticas.this);
                AlertDialog.Builder popup = new AlertDialog.Builder(estadisticas.this);
                popup.setView(relativeLayout);
                popup.setMessage("Nombre de Categoria").setCancelable(true);
                popup.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        //Obtenemos nombre de mascota
                        String nombre = nombre_categoria.getText().toString();
                        //Abrimos BD
                        SQLiteHelper usdbh = new SQLiteHelper(getApplicationContext(), "DBUsuarios", null, 1);
                        SQLiteDatabase db = usdbh.getWritableDatabase();

                        //Revisamos si la mascota existe
                        String[] arreglo = new String[] {nombre};
                        Log.d("testeo",arreglo[0]);
                        Cursor c = db.rawQuery("SELECT * FROM Mascotas WHERE Nombre = ?",arreglo);
                        c.moveToFirst();
                        if (c.isAfterLast()){
                            Log.d("test_delete","La mascota no existe");
                            dialog.dismiss();
                            AlertDialog.Builder noexiste_builder = new AlertDialog.Builder(estadisticas.this);
                            noexiste_builder.setMessage("La mascota no existe");
                            AlertDialog noexiste = noexiste_builder.create();
                            noexiste.show();
                        }

                        //Construimos la query
                        String tabla = "Mascotas";
                        String whereClause = "Nombre=?";
                        String[] whereArgs = new String[] {nombre};
                        //Ejecutamos la query
                        db.delete(tabla, whereClause, whereArgs);


                        Log.d("test_delete", nombre);
                        dialog.dismiss();

                        CharSequence texto = (CharSequence) nombre + " Eliminade";
                        Toast.makeText(estadisticas.this,texto, Toast.LENGTH_LONG).show();
                        Intent intento = new Intent( estadisticas.this, MainActivity.class);
                        startActivity(intento);
                    }
                });
                AlertDialog dialog = popup.create();
                dialog.setTitle("Eliminar Mascota");
                dialog.show();

                */
    }
}
