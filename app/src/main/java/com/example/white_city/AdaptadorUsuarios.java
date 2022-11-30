package com.example.white_city;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorUsuarios extends RecyclerView.Adapter<AdaptadorUsuarios.UsuariosViewHolder>{

    Context context;
    List<Usuario> listaUusuarios;

    public AdaptadorUsuarios(Context context, List<Usuario> listaUusuarios) {
        this.context = context;
        this.listaUusuarios = listaUusuarios;
    }

    @NonNull
    @Override
    public UsuariosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_usuario, null, false);
       return new AdaptadorUsuarios.UsuariosViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuariosViewHolder holder, int position) {
        holder.tvIdUsuario.setText(listaUusuarios.get(position).getIdUsuario());
        holder.tvNombres.setText(listaUusuarios.get(position).getNombres());
    }

    @Override
    public int getItemCount() {
        return listaUusuarios.size();
    }

    public class UsuariosViewHolder extends RecyclerView.ViewHolder {

        TextView tvIdUsuario, tvNombres;

        public UsuariosViewHolder(@NonNull View itemView) {
            super(itemView);

            tvIdUsuario = itemView.findViewById(R.id.tvIdUsuario);
            tvNombres = itemView.findViewById(R.id.tvNombres);
        }
    }
}
