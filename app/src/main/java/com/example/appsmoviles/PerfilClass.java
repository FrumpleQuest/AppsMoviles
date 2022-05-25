package com.example.appsmoviles;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.LinkedList;

public class PerfilClass extends ArrayAdapter<String>{
    //creamos un constructor
    private LinkedList Mascota;
    private String [] Name;
    private String[] Especie;
    private String[] Edad;
    private String[] Sexo;
    private String[] Estirilizado;
    private Integer[] imgid;
    private Activity context;
    public final static String LOGTAG ="Hola Logs";
    public PerfilClass(Activity context,LinkedList Mascota, String [] Name, String[] Especie, String[] Edad, String[] Sexo, String[] Estirilizado, Integer[] imgid) {

        super(context, R.layout.item_listview,Name);
        Log.i(LOGTAG, "Se muestra la vista");
        this.context= context;

        this.Mascota = Mascota;

        this.Name=Name;
        this.Especie=Especie;
        this.Edad=Edad;
        this.Sexo=Sexo;
        this.Estirilizado=Estirilizado;
        this.imgid=imgid;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View r = convertView;
        ViewHolder viewHolder = null;

        if(r==null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.item_listview,null,true);
            viewHolder= new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else{
            viewHolder= (ViewHolder) r.getTag();
        }
        viewHolder.perfil.setImageResource(imgid[position]);
        viewHolder.tvw1.setText(Name[position]);
        viewHolder.tvw2.setText(Especie[position]);
        viewHolder.edad.setText(Edad[position]);
        viewHolder.tvw3.setText(Sexo[position]);
        viewHolder.tvw4.setText(Estirilizado[position]);

        return r;
    }

    class ViewHolder{
        TextView tvw1;
        TextView tvw2;
        TextView edad;
        TextView tvw3;
        TextView tvw4;
        ImageView perfil;
        //creamos un constructor
        ViewHolder(View v){
            tvw1 = (TextView) v.findViewById(R.id.Nombre);
            tvw2 = (TextView) v.findViewById(R.id.Especie);
            edad = (TextView) v.findViewById(R.id.Edad);
            tvw3 = (TextView) v.findViewById(R.id.Sexo);
            tvw4 = (TextView) v.findViewById(R.id.Estirilizado);
            perfil = (ImageView) v.findViewById(R.id.foto_perfil);
        }

    }
}
