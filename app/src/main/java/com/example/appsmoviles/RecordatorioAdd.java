package com.example.appsmoviles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class RecordatorioAdd extends AppCompatActivity {
    ImageButton record1, Initial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recordatorio_add);

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
