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
import com.example.white_city.Modelo.Evento;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdapterEventos extends RecyclerView.Adapter<AdapterEventos.ViewHolder> {
    private LayoutInflater inflater;
    private ArrayList<Evento> eventos;
    private String doamin_image = "http://127.0.0.1:8000/v1/eventos";


    public AdapterEventos(Context context, ArrayList<Evento>eventos){
        this.inflater=LayoutInflater.from(context);
        this.eventos=eventos;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= inflater.inflate(R.layout.custoneventos_view,parent,false);
        return new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String evento = eventos.get(position).getEvento();
        String municipio = eventos.get(position).getMunicipio();
        String direccion = eventos.get(position).getDireccion();
        String horarios = eventos.get(position).getHorarios();
        String fecha_inicio = eventos.get(position).getFecha_inicio();
        String fecha_fin = eventos.get(position).getFecha_fin();
        String descripcion = eventos.get(position).getDescripcion();
        String tipo_evento = eventos.get(position).getTipo_evento();
        String imagen = eventos.get(position).getImagen();

        holder.txtEvento.setText(evento);
        holder.txtMunicipio.setText(municipio);
        holder.txtDireccion.setText(direccion);
        holder.txtHorarios.setText(horarios);
        holder.txtFecha_inicio.setText(fecha_inicio);
        holder.txtFecha_fin.setText(fecha_fin);
        holder.txtDescripcion.setText(descripcion);
        holder.txtTipo_evento.setText(tipo_evento);
        Picasso.get().load(imagen)
                .error(R.drawable.teatro1)
                .into(holder.imagen);
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtEvento,txtMunicipio,txtHorarios,txtFecha_inicio,txtDescripcion,txtFecha_fin,txtTipo_evento,txtDireccion;
        ImageView imagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetalleActivity.class);
                    intent.putExtra("id",eventos.
                            get(getAdapterPosition()).getId());
                    v.getContext().startActivity(intent);
                }
            });

            txtEvento = itemView.findViewById(R.id.txtEvento);
            txtMunicipio = itemView.findViewById(R.id.txtMunicipio);
            txtDireccion = itemView.findViewById(R.id.txtDireccion);
            txtHorarios = itemView.findViewById(R.id.txtHorarios);
            txtFecha_inicio = itemView.findViewById(R.id.txtFecha_inicio);
            txtFecha_fin = itemView.findViewById(R.id.txtFecha_fin);
            txtDescripcion = itemView.findViewById(R.id.txtDescripcion);
            txtTipo_evento = itemView.findViewById(R.id.txtTipo_evento);
            imagen = itemView.findViewById(R.id.imageNt);
        }
    }
    public void  filter(final String strSearch){
        if (strSearch.length() == 0) {
            eventos.clear();
            eventos.addAll(eventos);
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                eventos.clear();
                List<Evento> collect = eventos.stream()
                        .filter(i -> i.getEvento()
                                .toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());

                eventos.addAll(collect);
            }
            else {
                eventos.clear();
                for (Evento i : eventos) {
                    if (i.getEvento().toLowerCase().contains(strSearch)) {
                        eventos.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }


    public interface RecyclerItemClick {

        void itemClick(Evento evento);
    }
}




