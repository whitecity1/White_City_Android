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

import com.example.white_city.Modelo.Lugarturistico;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdapterLugarturistico extends RecyclerView.Adapter<AdapterLugarturistico.ViewHolder> {
    private LayoutInflater inflater;
    private ArrayList<Lugarturistico> lugares;
    private String doamin_image = "http://10.0.2.2:8000/v1/lugaresturisticos";



    public AdapterLugarturistico(Context context, ArrayList<Lugarturistico>lugares){
        this.inflater=LayoutInflater.from(context);
        this.lugares=lugares;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= inflater.inflate(R.layout.custonlugarturistico_view,parent,false);
        return new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String lugar_turistico = lugares.get(position).getLugar_turistico();
        String detalles = lugares.get(position).getDetalles();
        String horario_apertura = lugares.get(position).getHorario_apertura();
        String horario_cierre = lugares.get(position).getHorario_cierre();
        String municipio = lugares.get(position).getMunicipio();
        String direccion = lugares.get(position).getDireccion();
        String telefono = lugares.get(position).getTelefono();
        String correo_electronico = lugares.get(position).getCorreo_electronico();
        String tipo_lugar = lugares.get(position).getTipo_lugar();
        String imagen = lugares.get(position).getImagen();


        holder.txtLugar_turistico.setText(lugar_turistico);
        holder.txtDetalles.setText(detalles);
        holder.txtHorario_apertura.setText(horario_apertura);
        holder.txtHorario_cierre.setText(horario_cierre);
        holder.txtMunicipio.setText(municipio);
        holder.txtDireccion.setText(direccion );
        holder.txtTelefono.setText(telefono);
        holder.txtCorreo_electronico.setText(correo_electronico);
        holder.txtTipo_lugar.setText(tipo_lugar);
        Picasso.get().load(imagen)
                .error(R.drawable.popayanfondo)
                .into(holder.imagen);

    }

    @Override
    public int getItemCount() {
        return lugares.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtLugar_turistico,txtDetalles,txtHorario_apertura,txtHorario_cierre,txtMunicipio,txtDireccion,txtTelefono,
                txtCorreo_electronico,txtTipo_lugar;
        ImageView imagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetalleActivity.class);
                    intent.putExtra("id",lugares.
                            get(getAdapterPosition()).getId());
                    v.getContext().startActivity(intent);

                }
            });

            txtLugar_turistico= itemView.findViewById(R.id.txtLugar_turistico);
            txtDetalles = itemView.findViewById(R.id.txtDetalles);
            txtHorario_apertura = itemView.findViewById(R.id.txth_apertura);
            txtHorario_cierre = itemView.findViewById(R.id.txtHorario_cierre);
            txtMunicipio = itemView.findViewById(R.id.txtMunicipio);
            txtDireccion = itemView.findViewById(R.id.txtDireccion);
            txtTelefono = itemView.findViewById(R.id.txtTelefono);
            txtCorreo_electronico = itemView.findViewById(R.id.txtCorreo_electronico);
            txtTipo_lugar = itemView.findViewById(R.id.txtTipo_lugar);
            imagen = itemView.findViewById(R.id.imageNt);


        }
    }
    public void  filter(final String strSearch){
        if (strSearch.length() == 0) {
            lugares.clear();
            lugares.addAll(lugares);
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                lugares.clear();
                List<Lugarturistico> collect = lugares.stream()
                        .filter(i -> i.getLugar_turistico()
                                .toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());

                lugares.addAll(collect);
            }
            else {
                lugares.clear();
                for (Lugarturistico i : lugares) {
                    if (i.getLugar_turistico().toLowerCase().contains(strSearch)) {
                        lugares.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }


    public interface RecyclerItemClick {

        void itemClick(Lugarturistico lugarturistico);
    }
}




