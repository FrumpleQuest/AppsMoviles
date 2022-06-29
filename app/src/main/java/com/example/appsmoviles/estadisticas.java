package com.example.appsmoviles;

import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class estadisticas extends AppCompatActivity {

    String[] strings = {"Opcion 1", "Opcion 2", "Opcion 3"};
    TextView nombreMascota;




    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.estadisticas);
        nombreMascota=(TextView) findViewById(R.id.name_statistics_pet);
        Bundle bundle = getIntent().getExtras();
        String id = getIntent().getExtras().getString("id");
        nombreMascota.setText(id);

        Log.d("Holablobla ",id);

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
    /*public estadisticas(String Name) {
        this.Name = Name;
    }*/
}
