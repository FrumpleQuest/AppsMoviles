package com.example.appsmoviles;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterArchive extends ArrayAdapter<String> {
    private final Activity contexto;
    private final Integer[] imagenes;
    private final String[] nombreArchivo;

    public AdapterArchive(Activity contexto, String[] nombreArchivo, Integer[] imagenes) {
        super(contexto, R.layout.item_listview_archivos, nombreArchivo);
        this.contexto = contexto;
        this.nombreArchivo = nombreArchivo;
        this.imagenes = imagenes;
    }
        //se crea la lista que mostrara los elementos
        public View getView(int position, View view, ViewGroup parent){
            LayoutInflater inflater=contexto.getLayoutInflater();
            View renglon = inflater.inflate(R.layout.item_listview_archivos, null, true);
            TextView txtNombre = (TextView) renglon.findViewById(R.id.txtnombre);
            ImageView imgfotografia = (ImageView) renglon.findViewById(R.id.img_archivos);
            txtNombre.setText(nombreArchivo[position]);
            imgfotografia.setImageResource(imagenes[position]);
            return renglon;

        };
}

