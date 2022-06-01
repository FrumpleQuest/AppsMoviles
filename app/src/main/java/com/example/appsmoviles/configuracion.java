package com.example.appsmoviles;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class configuracion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        //Funcionalidad de eliminar mascota
        TextView eliminar_mascota = findViewById(R.id.eliminar_mascota);
        eliminar_mascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText edittext = new EditText(configuracion.this);
                AlertDialog.Builder popup = new AlertDialog.Builder(configuracion.this);
                popup.setView(edittext);
                popup.setMessage("Ingrese el nombre de la mascota que quiere eliminar").setCancelable(false);
                popup.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        //Obtenemos nombre de mascota
                        String nombre = edittext.getText().toString();
                        //Abrimos BD
                        SQLiteHelper usdbh = new SQLiteHelper(getApplicationContext(), "DBUsuarios", null, 1);
                        SQLiteDatabase db = usdbh.getWritableDatabase();

                        //Revisamos si la mascota existe
                        String[] arreglo = new String[] {nombre};
                        Cursor c = db.rawQuery("SELECT * FROM Mascotas WHERE Nombre = ?",arreglo);
                        c.moveToFirst();
                        if (c.isAfterLast()){
                            Log.d("test_delete","La mascota no existe");
                            dialog.dismiss();
                            AlertDialog.Builder noexiste_builder = new AlertDialog.Builder(configuracion.this);
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
                    }
                });
                popup.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = popup.create();
                dialog.setTitle("Eliminar Mascota");
                dialog.show();
            }
        });

        ImageButton recordatorio= (ImageButton) findViewById(R.id.recordatorio);
        recordatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( configuracion.this, recordatorios.class);
                startActivity(i);
            }
        });

        ImageButton configuracion= (ImageButton) findViewById(R.id.configuracion);
        configuracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //No hace nada pq es configuracion
            }
        });

        ImageButton boton_main= (ImageButton) findViewById(R.id.main_boton);
        boton_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( configuracion.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

}