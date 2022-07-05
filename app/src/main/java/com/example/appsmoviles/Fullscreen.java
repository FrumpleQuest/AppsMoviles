package com.example.appsmoviles;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Fullscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        ImageView fullscreenimageview = (ImageView) findViewById(R.id.imageViewFull);

        Intent callingActivityIntent = getIntent();
        if(callingActivityIntent!=null){
            Uri imageUri = callingActivityIntent.getData();
            if(imageUri!=null && fullscreenimageview != null){
                String a ="hola";
            }
        }

    }
}