package com.example.appsmoviles;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
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

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class estadisticas extends AppCompatActivity {

    List<String> categorias;
    TextView nombreMascota;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.estadisticas);

        //Predefinimos la selección del spinner
        Spinner spin = findViewById(R.id.spinner);
        spin.setSelection(0);

        //Abrimos la base de datos 'DBUsuarios' en modo lectura-escritura
        SQLiteHelper usdbh = new SQLiteHelper(this, "DBUsuarios", null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();

        nombreMascota=(TextView) findViewById(R.id.name_statistics_pet);
        Bundle bundle = getIntent().getExtras();
        String nombre = getIntent().getExtras().getString("id");
        nombreMascota.setText(nombre);

        String[] arreglo = new String[] {nombre};
        Cursor c2 = db.rawQuery("SELECT * FROM Mascotas WHERE Nombre = ?",arreglo);
        c2.moveToFirst();

        TextView fecha = (TextView) findViewById(R.id.stats_fecha);
        TextView sexo = (TextView) findViewById(R.id.stats_sexo);
        TextView especie = (TextView) findViewById(R.id.stats_especie);
        TextView raza = (TextView) findViewById(R.id.stats_raza);
        TextView esterilizado = (TextView) findViewById(R.id.stats_color);

        String siono;
        if(c2.getString(6) == "1") siono = "si";
        else siono = "no";

        fecha.setText("Fecha de Nacimiento: " + c2.getString(4));
        sexo.setText("Sexo: " + c2.getString(3));
        especie.setText("Especie: " + c2.getString(2));
        raza.setText("Raza: " + c2.getString(5));
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
        updateCategorias();
        updateChart();
    }

    public void updateChart(){
        //Obtenemos categoria actual del spinner
        Spinner spin = findViewById(R.id.spinner);
        String categoria = spin.getSelectedItem().toString();

        //Abrimos la base de datos 'DBUsuarios' en modo lectura-escritura
        SQLiteHelper usdbh = new SQLiteHelper(this, "DBUsuarios", null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();

        //Obtenemos los elementos desde la BD
        String[] arr = {categoria};
        Cursor c = db.rawQuery("SELECT * FROM Categorias WHERE Nombre = ?",arr);
        c.moveToFirst();

        //LLenamos los valores del grafico

        //flag true significa que hay una categoria con ese nombre
        boolean flag = !c.isAfterLast();
        ArrayList<Entry> valores = new ArrayList<Entry>();
        if(flag){
            String ID_Categoria = c.getString(0);
            String[] arr2 = {ID_Categoria};
            Cursor c2 = db.rawQuery("SELECT * FROM Datos WHERE ID_Categoria = ?",arr2);
            c2.moveToFirst();
            if (c2.isAfterLast()){
                flag = false; //No hay datos
            }
            else{
                int d = 0;
                while(!c2.isAfterLast()){
                    String fecha = c2.getString(2); //Por ahora ignorara la fecha, luego debera transformarla
                    valores.add(new Entry(d++, Float.parseFloat(c2.getString(3))));
                    c2.moveToNext();
                }
            }
        }

        //Construimos la estetica del grafico
        LineChart grafico = findViewById(R.id.line_chart);
        grafico.setNoDataText("No existen datos disponibles");
        grafico.setNoDataTextColor(R.color.black);
        grafico.setDrawGridBackground(true);
        grafico.setDrawBorders(true);

        grafico.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        grafico.getAxisLeft().setEnabled(false);

        Legend legend = grafico.getLegend();
        legend.setEnabled(true);
        legend.setTextColor(R.color.teal_200);
        legend.setTextSize(15);

        Description desc = new Description();
        desc.setTextSize(0);
        desc.setText("");
        grafico.setDescription(desc);

        //Creamos y mostramos el grafico
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        //Construimos la estetica de los datos
        if(flag){
            LineDataSet data = new LineDataSet(valores,categoria);
            data.setLineWidth(5);
            data.setColor(Color.CYAN);
            data.setCircleHoleColor(Color.CYAN);
            data.setCircleColor(Color.BLACK);
            data.setCircleRadius(4);
            data.setCircleHoleRadius(1.5f);
            dataSets.add(data);
            LineData ldata = new LineData(dataSets);
            ldata.setValueTextColor(R.color.teal_200);
            ldata.setValueTextSize(12);
            grafico.setData(ldata);
        }
        else{
            grafico.setData(null);
        }
        grafico.invalidate();

    }

    public void updateCategorias(){

        //Abrimos la base de datos 'DBUsuarios' en modo lectura-escritura
        SQLiteHelper usdbh = new SQLiteHelper(this, "DBUsuarios", null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();

        String[] nombre_arr = {nombreMascota.getText().toString()};

        //Buscamos la ID_Categoria de la mascota
        Cursor c_pre = db.rawQuery("SELECT * FROM Mascotas where Nombre = ?",nombre_arr);
        c_pre.moveToFirst();

        String ID = c_pre.getString(0);

        String[] ID_arr = {ID};
        //Obtenemos categorias
        Cursor c = db.rawQuery("SELECT * FROM Categorias where ID_Mascota = ?",ID_arr);
        c.moveToFirst();

        categorias = new ArrayList<>();
        if (c.isAfterLast()) categorias.add("                      ");
        while(!c.isAfterLast()){
            categorias.add(c.getString(2));
            c.moveToNext();
        }

        Spinner spin = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adaptador = new ArrayAdapter(this,android.R.layout.simple_spinner_item,categorias);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateChart();
            }
            //Esta funcion no sirve de nada lol
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d("spintest","nothing");
            }
        });
        spin.setAdapter(adaptador);
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
                        final String Ejey = text2.getText().toString();
                        final String Ejex = text3.getText().toString();



                        SQLiteHelper usdbh2 = new SQLiteHelper(getApplicationContext(), "DBUsuarios", null, 1);
                        SQLiteDatabase db2 = usdbh2.getWritableDatabase();

                        String[] arreglo = new String[] {nombreMascota.getText().toString()};
                        Cursor c = db2.rawQuery("SELECT * FROM Mascotas WHERE Nombre = ?",arreglo);
                        c.moveToFirst();
                        int ID = Integer.parseInt(c.getString(0));
                        ContentValues nueva_categoria = new ContentValues(4);
                        nueva_categoria.put("ID_Mascota",ID);
                        nueva_categoria.put("Nombre", categoria);
                        nueva_categoria.put("EjeX", Ejex);
                        nueva_categoria.put("EjeY",Ejey);
                        db2.insert("Categorias",null, nueva_categoria);

                        updateCategorias();
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
        Spinner spin = findViewById(R.id.spinner);
        String categoria = spin.getSelectedItem().toString();
        Log.d("selecteditem",categoria);
        Bundle bundle = new Bundle();
        bundle.putString("categoria",categoria);
        Intent statistics = new Intent(estadisticas.this,addDato.class);
        statistics.putExtras(bundle);
        startActivity(statistics);
    }

    public void verArchivosOnClick(View view) {
        Intent i = new Intent( estadisticas.this, archivos.class);
        startActivity(i);
    }
}
