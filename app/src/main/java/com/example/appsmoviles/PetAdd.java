package com.example.appsmoviles;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class PetAdd extends AppCompatActivity {
    ImageButton recordatorio, Initial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet_add);

        //-----------------------------Codigo duplicado-----------------------------------
        recordatorio= (ImageButton) findViewById(R.id.recordatorio);
        recordatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( PetAdd.this, recordatorios.class);
                startActivity(i);
            }
        });
        Initial = (ImageButton) findViewById(R.id.principal_layout);
        Initial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(PetAdd.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}