package com.example.appsmoviles;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.VolumeShaper;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class archivos extends AppCompatActivity {

    private static final int TAKE_PICTURE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archivos);
    }

    public void sacarFoto(View view){
        if(view.getId() == R.id.btn_foto_archivo && getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)){
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            if(takePictureIntent.resolveActivity(getPackageManager())!=null){
                startActivityForResult(takePictureIntent, TAKE_PICTURE);
            } else {
                Toast.makeText(this,"No existe una aplicacion para tomar cámara",Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this,"El dispositivo no tiene camara", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case TAKE_PICTURE:
                if(resultCode == Activity.RESULT_OK){
                    ImageView imgFoto= findViewById(R.id.img_archivos);

                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");

                    int orientation = getResources().getConfiguration().orientation;

                    if(orientation == Configuration.ORIENTATION_LANDSCAPE){
                        imgFoto.setImageBitmap(imageBitmap);
                    }else{
                        Bitmap imageBitmapRotated = rotateImage(imageBitmap, -90);
                        imgFoto.setImageBitmap(imageBitmapRotated);
                    }
                }
        }
    }

    private Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

}