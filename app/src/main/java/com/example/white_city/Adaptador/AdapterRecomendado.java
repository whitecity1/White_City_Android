package com.example.white_city.Adaptador;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.white_city.DetalleActivity;
import com.example.white_city.R;

import com.example.white_city.Modelo.Recomendado;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdapterRecomendado extends RecyclerView.Adapter<AdapterRecomendado.ViewHolder> {
    private LayoutInflater inflater;
    private ArrayList<Recomendado> recomendados;
    private String doamin_image = "http://10.0.2.2:8000/v1/recomendados";



    public AdapterRecomendado(Context context, ArrayList<Recomendado>recomendados){
        this.inflater=LayoutInflater.from(context);
        this.recomendados=recomendados;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= inflater.inflate(R.layout.custonrecomendado_view,parent,false);
        return new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String lugar_recomendado = recomendados.get(position).getLugar_recomendado();
        String calificaciones = recomendados.get(position).getCalificaciones();
        String resenahistorica = recomendados.get(position).getResenahistorica();
        String user_id = recomendados.get(position).getUser_id();
        String imagen =recomendados.get(position).getImagen();


        holder.txtLugar_recomendado.setText(lugar_recomendado);
        holder.txtCalificaciones.setText(calificaciones);
        holder.txtResenahistorica.setText(resenahistorica);
        holder.txtuser_id.setText(user_id);
        Picasso.get().load(imagen)
                .error(R.drawable.humilladero1)
                .into(holder.imagen);

    }

    @Override
    public int getItemCount() {
        return recomendados.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtLugar_recomendado,txtCalificaciones,txtResenahistorica,txtuser_id;
        ImageView imagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetalleActivity.class);
                    intent.putExtra("idrecomendados",recomendados.
                            get(getAdapterPosition()).getIdrecomendado());
                    v.getContext().startActivity(intent);

                }
            });

            txtLugar_recomendado = itemView.findViewById(R.id.txtLugar_recomendado);
            txtCalificaciones = itemView.findViewById(R.id.txtCalificaciones);
            txtResenahistorica = itemView.findViewById(R.id.txtResenahistorica);
            txtuser_id = itemView.findViewById(R.id.txtUser_id);

            imagen = itemView.findViewById(R.id.imageNt);


        }
    }
    public void  filter(final String strSearch){
        if (strSearch.length() == 0) {
            recomendados.clear();
            recomendados.addAll(recomendados);
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                recomendados.clear();
                List<Recomendado> collect = recomendados.stream()
                        .filter(i -> i.getLugar_recomendado()
                                .toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());

                recomendados.addAll(collect);
            }
            else {
                recomendados.clear();
                for (Recomendado i : recomendados) {
                    if (i.getLugar_recomendado().toLowerCase().contains(strSearch)) {
                        recomendados.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }


    public interface RecyclerItemClick {
        
        void itemClick(Recomendado recomendado);
    }
}




