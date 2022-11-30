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

import com.example.white_city.Modelo.Restaurante;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdapterRestaurantes extends RecyclerView.Adapter<AdapterRestaurantes.ViewHolder> {
    private LayoutInflater inflater;
    private ArrayList<Restaurante> restaurantes;
    private String doamin_image = "http://10.0.2.2:8000/v1/restaurantes";



    public AdapterRestaurantes(Context context, ArrayList<Restaurante>restaurantes){
        this.inflater=LayoutInflater.from(context);
        this.restaurantes=restaurantes;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= inflater.inflate(R.layout.custonrestaurantes_view,parent,false);
        return new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String restaurante = restaurantes.get(position).getRestaurante();
        String telefono = restaurantes.get(position).getTelefono();
        String correo = restaurantes.get(position).getCorreo();
        String mun_ubicado = restaurantes.get(position).getMun_ubicado();
        String direccion = restaurantes.get(position).getDireccion();
        String apertura = restaurantes.get(position).getApertura();
        String cierre = restaurantes.get(position).getCierre();
        String imagen = restaurantes.get(position).getImagen();


        holder.txtRestaurante.setText(restaurante);
        holder.txtTelefono.setText(telefono);
        holder.txtCorreo.setText(correo);
        holder.txtMun_ubicado.setText(mun_ubicado);
        holder.txtDireccion.setText(direccion);
        holder.txtApertura.setText(apertura);
        holder.txtCierre.setText(cierre);
        Picasso.get().load(imagen)
                .error(R.drawable.restaurantes)
                .into(holder.imagen);

    }

    @Override
    public int getItemCount() {
        return restaurantes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
            TextView txtRestaurante,txtTelefono,txtCorreo,txtMun_ubicado,txtDireccion,txtApertura,txtCierre;
        ImageView imagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetalleActivity.class);
                    intent.putExtra("id",restaurantes.
                            get(getAdapterPosition()).getId());
                    v.getContext().startActivity(intent);

                }
            });

            txtRestaurante = itemView.findViewById(R.id.txtRestaurante);
            txtTelefono = itemView.findViewById(R.id.txtTelefono);
            txtCorreo = itemView.findViewById(R.id.txtCorreo);
            txtMun_ubicado = itemView.findViewById(R.id.txtMun_ubicado);
            txtDireccion = itemView.findViewById(R.id.txtDireccion);
            txtApertura = itemView.findViewById(R.id.txtApertura);
            txtCierre = itemView.findViewById(R.id.txtCierre);
            imagen = itemView.findViewById(R.id.imageNt);


        }
    }
    public void  filter(final String strSearch){
        if (strSearch.length() == 0) {
            restaurantes.clear();
            restaurantes.addAll(restaurantes);
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
              restaurantes.clear();
                List<Restaurante> collect = restaurantes.stream()
                        .filter(i -> i.getRestaurante()
                                .toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());

               restaurantes.addAll(collect);
            }
            else {
               restaurantes.clear();
                for (Restaurante i : restaurantes) {
                    if (i.getRestaurante().toLowerCase().contains(strSearch)) {
                        restaurantes.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }


    public interface RecyclerItemClick {

        void itemClick(Restaurante restaurante);
    }
}




