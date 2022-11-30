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

import com.example.white_city.DetalleFotografiaActivity;
import com.example.white_city.FotografActivity;
import com.example.white_city.R;
import com.example.white_city.Modelo.Fotografias;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdapterFotografia extends RecyclerView.Adapter<AdapterFotografia.ViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<Fotografias>fotografias;
    private String doamin_image ="http://127.0.0.1:8000/v1/fotografias";

    public AdapterFotografia(Context context, ArrayList<Fotografias>fotografias){
        this.inflater= LayoutInflater.from(context);
        this.fotografias=fotografias;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= inflater.inflate(R.layout.customfotografias_view,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String nombre=fotografias.get(position).getNombre();
        String descripcion =fotografias.get(position).getDescripcion();
        String imagen=fotografias.get(position).getImagen();
        holder.txtNombre.setText(nombre);
        holder.txtDescripcion.setText(descripcion);
        Picasso.get().load(imagen)
                .error(R.drawable.caldas1)
                .into(holder.imagen);

    }

    @Override
    public int getItemCount() {

        return fotografias.size();
    }

    public interface OnQueryTextListener {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombre,txtDescripcion;
        ImageView imagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View .OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(v.getContext(), FotografActivity.class);
                    intent.putExtra("ID",String.valueOf(fotografias.get(getAdapterPosition()).getId()));
                    intent.putExtra("nombre", String.valueOf(fotografias.get(getAdapterPosition()).getNombre()));
                    intent.putExtra("imagen", String.valueOf(fotografias.get(getAdapterPosition()).getImagen()));
                    intent.putExtra("descripcion",String.valueOf(fotografias.get(getAdapterPosition()).getDescripcion()));
                         v.getContext().startActivity(intent);

                   // Intent intent = new Intent(v.getContext(), DetalleFotografiaActivity.class);
                   // intent.putExtra("id", fotografias
                           // .get(getAdapterPosition()).getId());
                    //v.getContext().startActivity(intent);
                }
            });
            txtNombre=itemView.findViewById(R.id.txtNombre);
            txtDescripcion=itemView.findViewById(R.id.txtDescripcion);
            imagen=itemView.findViewById(R.id.imageNt);
        }

    }
    public void filter(final String strSearch) {
        if (strSearch.length() == 0) {
            fotografias.clear();
            fotografias.addAll(fotografias);
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                fotografias.clear();
                List<Fotografias> collect = fotografias.stream()
                        .filter(i -> i.getNombre()
                                .toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());

                fotografias.addAll(collect);
            }
            else {
                fotografias.clear();
                for (Fotografias i : fotografias) {
                    if (i.getNombre().toLowerCase().contains(strSearch)) {
                        fotografias.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }


    public interface RecyclerItemClick {

        void itemClick(Fotografias fotografia);
    }
}
