package com.example.appsmoviles;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Fullscreen extends AppCompatActivity {

    ImageView imageview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        imageview = (ImageView) findViewById(R.id.imageViewFull);

        Intent callingActivityIntent = getIntent();
        if(callingActivityIntent.getExtras()!=null){
            Integer imagen = callingActivityIntent.getIntExtra("imagenes",0);
            imageview.setImageResource(imagen);
        }

    }
}