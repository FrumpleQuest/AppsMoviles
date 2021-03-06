package com.example.appsmoviles;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class archivos extends AppCompatActivity {
    ImageButton recordatorio,configuracion,boton_main;
    ListView listArchivos;
    private static final int TAKE_PICTURE = 0;
    FloatingActionButton btnCam;
    //Button btnCam;

    ImageView imageView;

    boolean isImageFitToScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archivos);

        listArchivos = (ListView) findViewById(R.id.iv1);
        String[] nombre = new String[]{
                "Gatito 1",
                "Gatito 2"
        };
        Integer[] imagenes={
                R.drawable.makoimg,
                R.drawable.makoimg
        };

        AdapterArchive adaptadorarchivos = new AdapterArchive(this, nombre, imagenes);
        listArchivos.setAdapter(adaptadorarchivos);




            //imageView = (ImageView) findViewById(R.id.img_archivos);
        listArchivos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(archivos.this ,Fullscreen.class );
                Integer selectedImage = imagenes[position];
                i.putExtra("imagenes", selectedImage);
                startActivity(i);
            }
        });



        btnCam = (FloatingActionButton) findViewById(R.id.btn_foto_archivo);
        //btnCam = (Button) findViewById(R.id.btn_foto_archivo);
        btnCam.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent();
                    intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    //startActivity(intent);
                    startActivityForResult(intent,TAKE_PICTURE);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
//-------------------------------------------------------------------------------------------
        recordatorio= (ImageButton) findViewById(R.id.recordatorio);
        recordatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //recordatorio.setImageResource(R.drawable.calendarselected);
                Intent i = new Intent( archivos.this, recordatorios.class);
                startActivity(i);
            }
        });

        configuracion= (ImageButton) findViewById(R.id.configuracion);
        configuracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( archivos.this, configuracion.class);
                startActivity(i);
            }
        });

        boton_main= (ImageButton) findViewById(R.id.main_boton);
        boton_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Aqui no se hace nada pq es el main
                Intent i = new Intent(archivos.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case TAKE_PICTURE:
                if(resultCode == Activity.RESULT_OK){
                    ImageView imgFoto = findViewById(R.id.img_archivos);

                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");

                    // En algunos dispositivos la app de la c??mara funciona siempre en modo landscape
                    int orientation = getResources().getConfiguration().orientation;

                    if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        imgFoto.setImageBitmap(imageBitmap);
                    } else {
                        Bitmap imageBitmapRotated = rotateImage(imageBitmap, -90);
                        imgFoto.setImageBitmap(imageBitmapRotated);
                    }
                    //ArrayAdapter adaptador = new ArrayAdapter(archivos.this, R.layout.item_listview_archivos);
                    //ListView lv = findViewById(R.id.rv1);

                    //lv.setAdapter(adaptador);



                }
        }

    }

    private Bitmap rotateImage(Bitmap imageBitmap, float i) {
        Matrix matrix = new Matrix();
        matrix.postRotate(i);
        return Bitmap.createBitmap(imageBitmap,0,0,imageBitmap.getWidth(), imageBitmap.getHeight(),matrix, true);
    }


    /*

    public void sacarFoto(View view){
        if(view.getId() == R.id.btn_foto_archivo && getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)){
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            if(takePictureIntent.resolveActivity(getPackageManager())!=null){
                startActivityForResult(takePictureIntent, TAKE_PICTURE);
            } else {
                Toast.makeText(this,"No existe una aplicacion para tomar c??mara",Toast.LENGTH_LONG).show();
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
*/
}