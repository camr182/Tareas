package com.example.minecraft.petshop.Adaptadores;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minecraft.petshop.Activities.DetalleMascota;
import com.example.minecraft.petshop.model.ConstructorMascotas;
import com.example.minecraft.petshop.model.Mascota;
import com.example.minecraft.petshop.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minecraft on 12/03/2017.
 */

public class MascotaAdapter extends RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder> {

    private ArrayList<Mascota> items;
    Activity activity;

    public MascotaAdapter(ArrayList<Mascota> items, Activity activity) {
        this.items = items;
        this.activity = activity;
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {

        public ImageView foto;
        public TextView nombre;
        private TextView tvLikes;
        public ImageButton btnLike;

        public MascotaViewHolder(View itemView) {
            super(itemView);

            foto = (ImageView) itemView.findViewById(R.id.ivFoto);
            nombre = (TextView) itemView.findViewById(R.id.tvNombre);
            tvLikes = (TextView) itemView.findViewById(R.id.tvLike);
            btnLike = (ImageButton) itemView.findViewById(R.id.ibtnLike);

        }
    }



    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_mascota,parent,false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MascotaViewHolder holder, int position) {

        final Mascota mascota = items.get(position);
        //ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity);

        Picasso.with(activity)
                .load(mascota.getUrlFoto())
                .placeholder(R.drawable.fondo_imagen)
                .into(holder.foto);

        holder.tvLikes.setText(String.valueOf(mascota.getLikes()));
        //holder.foto.setImageResource(items.get(position).getImagen());
       // holder.nombre.setText(items.get(position).getNombre());
        //holder.tvLikes.setText(constructorMascotas.obtenerLikesMascota(mascota));


        holder.foto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(v.getContext(),mascota.getNombre(),Toast.LENGTH_SHORT).show();

                Intent i = new Intent(v.getContext(), DetalleMascota.class);
                i.putExtra("url",mascota.getUrlFoto());
                i.putExtra("likes",mascota.getLikes());
               // i.putExtra("imagen",mascota.getImagen());
                v.getContext().startActivity(i);

            }
        });


        /*
        holder.btnLike.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Diste like a "+mascota.getNombre(),Toast.LENGTH_SHORT).show();

                ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity);
                constructorMascotas.darLikeMascota(mascota);
                holder.tvLikes.setText(constructorMascotas.obtenerLikesMascota(mascota) + " " + "Likes");


            }
        });
        */
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
