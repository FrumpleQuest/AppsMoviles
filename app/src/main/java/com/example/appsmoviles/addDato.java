package com.example.appsmoviles;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class addDato extends Activity {

    String categoria_actual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adddato);

        Bundle bundle = getIntent().getExtras();
        String categoria = getIntent().getExtras().getString("categoria");
        categoria_actual = categoria;

    }

    @Override
    public void onResume(){
        super.onResume();
        crearTabla();

        //Codigo duplicado
        ImageButton recordatorio= (ImageButton) findViewById(R.id.recordatorio);
        recordatorio.setImageResource(R.drawable.calendarselected);
        recordatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( addDato.this, recordatorios.class);
                startActivity(i);
            }
        });

        ImageButton configuracion= (ImageButton) findViewById(R.id.configuracion);
        configuracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( addDato.this, configuracion.class);
                startActivity(i);
            }
        });

        ImageButton boton_main= (ImageButton) findViewById(R.id.main_boton);
        boton_main.setImageResource(R.drawable.icon_main_unselect);
        boton_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( addDato.this, MainActivity.class);
                startActivity(i);
            }
        });

        FloatingActionButton fab1 = findViewById(R.id.floating_action_button_2);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout linear = new LinearLayout(addDato.this);
                linear.setOrientation(LinearLayout.VERTICAL);
                EditText text = new EditText(addDato.this);
                EditText text2 = new EditText(addDato.this);

                text.setHint("Valor del Eje X");
                text2.setHint("Valor del Eje Y");
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(50,0,50,0);
                text.setLayoutParams(layoutParams);
                text2.setLayoutParams(layoutParams);
                linear.addView(text);
                linear.addView(text2);

                AlertDialog.Builder popup = new AlertDialog.Builder(addDato.this);
                popup.setView(linear).setCancelable(true);
                popup.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        final String fecha = text.getText().toString();
                        final String valor = text2.getText().toString();

                        SQLiteHelper usdbh2 = new SQLiteHelper(getApplicationContext(), "DBUsuarios", null, 1);
                        SQLiteDatabase db2 = usdbh2.getWritableDatabase();

                        String[] arreglo = new String[] {categoria_actual};
                        Cursor c = db2.rawQuery("SELECT * FROM Categorias WHERE Nombre = ?",arreglo);
                        c.moveToFirst();

                        String ID = c.getString(0).toString();

                        ContentValues nuevo_dato = new ContentValues(4);
                        nuevo_dato.put("ID_Categoria",ID);
                        nuevo_dato.put("Fecha", fecha);
                        nuevo_dato.put("Valor", valor);
                        db2.insert("Datos",null, nuevo_dato);

                        crearTabla();
                    }
                });
                popup.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog dialog = popup.create();
                dialog.setTitle("Añadir Dato");
                dialog.show();
            }
        });
    }

    public TextView createTextDato(String str){
        TextView tx = new TextView(this);
        TableLayout.LayoutParams params = new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,1f);
        tx.setLayoutParams(params);
        tx.setGravity(Gravity.CENTER);
        tx.setText(str);
        tx.setPadding(0,10,0,10);
        tx.setTextSize(16);
        tx.setBackgroundResource(R.drawable.borde);
        return tx;
    }

    private void crearTabla() {
        //Obtenemos tabla
        TableLayout tabla = findViewById(R.id.tabla);
        tabla.removeAllViews();
        //Obtenemos datos de BD
        TreeMap<String,String> tree = getSortedTreeMap();

        //Creamos una lista de filas
        ArrayList<LinearLayout> rows = new ArrayList<>();

        //Llenamos las filas de datos
        int c = 0;
        for(Map.Entry<String,String> entry : tree.entrySet()) {
            rows.add(new LinearLayout(this));
            rows.get(c).setOrientation(LinearLayout.HORIZONTAL);
            rows.get(c).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            rows.get(c).addView(createTextDato(entry.getKey().toString()));
            rows.get(c).addView(createTextDato(entry.getValue().toString()));
            tabla.addView(rows.get(c));
            c++;
        }
    }

    public TreeMap<String,String> getSortedTreeMap(){
        //Abrimos la base de datos 'DBUsuarios' en modo lectura-escritura
        SQLiteHelper usdbh = new SQLiteHelper(this, "DBUsuarios", null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();

        boolean existe_categoria = true;
        //Encontramos id categoria actual
        String[] arg = {categoria_actual};
        Cursor c_pre = db.rawQuery("SELECT * FROM Categorias WHERE Nombre = ?",arg);
        c_pre.moveToFirst();
        if (c_pre.isAfterLast()) existe_categoria = false;
        if (existe_categoria){
            String ID = c_pre.getString(0);
            String[] ID_arg = {ID};
            //Obtenemos los elementos desde la BD
            Cursor c = db.rawQuery("SELECT * FROM Datos WHERE ID_Categoria = ?",ID_arg);
            c.moveToFirst();
            //LLenamos la lista con la BD (Aqui habría que tener 3 listas para los 3 atributos)
            TreeMap<String, String> dicc = new TreeMap<>();
            boolean flag = true;
            if (c.isAfterLast()) flag = false; //Aqui por si no hay recordatorios en la lista
            while(flag){
                dicc.put(c.getString(2), c.getString(3));
                if (c.isLast()){
                    flag = false;
                }
                c.moveToNext();
            }
            return dicc;
        }
        return new TreeMap<>();





    }

}
