package com.example.appsmoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class configuracion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

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