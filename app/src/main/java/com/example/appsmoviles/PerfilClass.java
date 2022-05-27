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
    private LinkedList Nombres;
    private LinkedList Especies;
    private LinkedList Sexos;
    private LinkedList Fechas;
    private LinkedList Razas;
    private LinkedList Esterilizados;

    private Integer[] imgid;
    private Activity context;
    public final static String LOGTAG ="Hola Logs";
    public PerfilClass(Activity context,LinkedList Nombres,LinkedList Especies,LinkedList Sexos,LinkedList Fechas,LinkedList Razas,LinkedList Esterilizados, Integer[] imgid) {

        super(context, R.layout.item_listview,Nombres);
        Log.i(LOGTAG, "Se muestra la vista");
        this.context= context;

        this.Nombres = Nombres;
        this.Especies = Especies;
        this.Sexos = Sexos;
        this.Fechas = Fechas;
        this.Razas = Razas;
        this.Esterilizados = Esterilizados;

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
        viewHolder.tvw1.setText((String) Nombres.get(position));
        viewHolder.tvw2.setText((String) Especies.get(position));
        viewHolder.edad.setText((String) Fechas.get(position));
        viewHolder.tvw3.setText((String) Sexos.get(position));
        viewHolder.razas.setText((String) Razas.get(position));
        viewHolder.tvw4.setText((String) Esterilizados.get(position));

        return r;
    }

    class ViewHolder{
        TextView tvw1;
        TextView tvw2;
        TextView edad;
        TextView tvw3;
        TextView tvw4;
        ImageView perfil;
        TextView razas;
        //creamos un constructor
        ViewHolder(View v){
            tvw1 = (TextView) v.findViewById(R.id.Nombre);
            tvw2 = (TextView) v.findViewById(R.id.Especie);
            edad = (TextView) v.findViewById(R.id.Edad);
            tvw3 = (TextView) v.findViewById(R.id.Sexo);
            tvw4 = (TextView) v.findViewById(R.id.Estirilizado);
            perfil = (ImageView) v.findViewById(R.id.foto_perfil);
            razas = (TextView) v.findViewById(R.id.Raza);
        }

    }
}
