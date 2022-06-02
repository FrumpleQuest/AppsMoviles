package com.example.appsmoviles;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RecordatorioAdd extends AppCompatActivity {
    ImageButton record1, Initial;
    ExtendedFloatingActionButton boton_agregar;
    TextInputLayout tv1,tv2,tv3;
    String text1,text2,text3;
    EditText mDateFormat;
    DatePickerDialog.OnDateSetListener onDateSetListener;
    TextInputEditText editText;
    final Calendar myCalendar = Calendar.getInstance();
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
                nuevorecordatorio.put("Titulo", "Mako: " + text1 );
                nuevorecordatorio.put("Subtitulo", "Subtitulo: " + text3);
                nuevorecordatorio.put("Fecha",text2.replaceAll("/", "-"));
                db.insert("Recordatorios",null, nuevorecordatorio);

                Intent i = new Intent( RecordatorioAdd.this, recordatorios.class);
                startActivity(i);
            }
        });

        //-----------------------------Codigo duplicado-----------------------------------
        //Codigo duplicado
        ImageButton recordatorio= (ImageButton) findViewById(R.id.recordatorio);
        recordatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( RecordatorioAdd.this, recordatorios.class);
                startActivity(i);
            }
        });

        ImageButton configuracion= (ImageButton) findViewById(R.id.configuracion);
        configuracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( RecordatorioAdd.this, configuracion.class);
                startActivity(i);
            }
        });

        ImageButton boton_main= (ImageButton) findViewById(R.id.main_boton);
        boton_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( RecordatorioAdd.this, MainActivity.class);
                startActivity(i);
            }
        });

        //acá se agrega el calendario
        editText=(TextInputEditText) findViewById(R.id.dateFormat);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);
                updateLabel();
            }
        };
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(RecordatorioAdd.this, date, myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

    }
    private void updateLabel(){
        String myFormat= "MM/dd/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        editText.setText(dateFormat.format(myCalendar.getTime()));
    }
}
