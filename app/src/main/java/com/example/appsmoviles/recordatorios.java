package com.example.appsmoviles;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class recordatorios extends AppCompatActivity {

    ListView mylistview;

    String[] recordatorios = {"Lunes 3, Llevar al veterinario", "Lunes 10, poner vacuna", "Lunes 17, Esterilizar"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordatorios);

        mylistview = (ListView) findViewById(R.id.rv1);//lo estoy haciendo diferente al profe, realizando un casteo
        ArrayAdapter adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                recordatorios
        );

        mylistview.setAdapter(adapter);

    }
}