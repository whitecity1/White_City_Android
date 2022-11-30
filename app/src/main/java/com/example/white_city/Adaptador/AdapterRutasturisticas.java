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

import com.example.white_city.Modelo.Rutaturistica;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdapterRutasturisticas extends RecyclerView.Adapter<AdapterRutasturisticas.ViewHolder> {
    private LayoutInflater inflater;
    private ArrayList<Rutaturistica> rutaturistica;
    private String doamin_image = "http://10.0.2.2:8000/v1/rutasturisticas";



    public AdapterRutasturisticas(Context context, ArrayList<Rutaturistica>rutaturistica){
        this.inflater=LayoutInflater.from(context);
        this.rutaturistica=rutaturistica;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= inflater.inflate(R.layout.custonrutasturisticas_view,parent,false);
        return new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String ruta_turistica = rutaturistica.get(position).getRuta_turistica();
        String descripcion = rutaturistica.get(position).getDescripcion();
        String municipio_ubicada = rutaturistica.get(position).getMunicipio_ubicada();
        String direccion_ruta = rutaturistica.get(position).getDireccion_ruta();
        String contactos = rutaturistica.get(position).getContactos();
        String h_apertura = rutaturistica.get(position).getH_apertura();
        String h_cierre = rutaturistica.get(position).getH_cierre();
        String tipo_rutaTur = rutaturistica.get(position).getTipo_rutaTur();
        String imagen = rutaturistica.get(position).getImagen();


        holder.txtRuta_turistica.setText(ruta_turistica);
        holder.txtDescripcion.setText(descripcion);
        holder.txtMun_ubicada.setText(municipio_ubicada);
        holder.txtDireccion_ruta.setText(direccion_ruta);
        holder.txtContactos.setText(contactos);
        holder.txtH_apertura.setText(h_apertura);
        holder.txtH_cierre.setText(h_cierre);
        holder.txtTipo_rutaTur.setText(tipo_rutaTur);
        Picasso.get().load(imagen)
                .error(R.drawable.purace)
                .into(holder.imagen);

    }

    @Override
    public int getItemCount() {
        return rutaturistica.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
            TextView txtRuta_turistica,txtDescripcion,txtMun_ubicada,txtDireccion_ruta,txtContactos,txtH_apertura,txtH_cierre,txtTipo_rutaTur;
        ImageView imagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetalleActivity.class);
                    intent.putExtra("id",rutaturistica.
                            get(getAdapterPosition()).getId());
                    v.getContext().startActivity(intent);

                }
            });

            txtRuta_turistica = itemView.findViewById(R.id.txtruta_turistica);
            txtDescripcion = itemView.findViewById(R.id.txtdescripcion);
            txtMun_ubicada = itemView.findViewById(R.id.txtmunicipio_ubicada);
            txtDireccion_ruta = itemView.findViewById(R.id.txtdireccion_ruta);
            txtContactos = itemView.findViewById(R.id.txtcontactos);
            txtH_apertura = itemView.findViewById(R.id.txth_apertura);
            txtH_cierre = itemView.findViewById(R.id.txth_cierre);
            txtTipo_rutaTur = itemView.findViewById(R.id.txttipo_rutaTur);
            imagen = itemView.findViewById(R.id.imageNt);


        }
    }
    public void  filter(final String strSearch){
        if (strSearch.length() == 0) {
            rutaturistica.clear();
           rutaturistica.addAll(rutaturistica);
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
               rutaturistica.clear();
                List<Rutaturistica> collect = rutaturistica.stream()
                        .filter(i -> i.getRuta_turistica()
                                .toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());

               rutaturistica.addAll(collect);
            }
            else {
               rutaturistica.clear();
                for (Rutaturistica i : rutaturistica) {
                    if (i.getRuta_turistica().toLowerCase().contains(strSearch)) {
                        rutaturistica.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }


    public interface RecyclerItemClick {

        void itemClick(Rutaturistica rutaturistica);
    }
}




