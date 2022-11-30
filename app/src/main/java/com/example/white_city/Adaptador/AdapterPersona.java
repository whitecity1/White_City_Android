package com.example.white_city.Adaptador;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.white_city.DetalleActivity;
import com.example.white_city.Model.Persona;
import com.example.white_city.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdapterPersona extends RecyclerView.Adapter<AdapterPersona.ViewHolder> {
    private LayoutInflater inflater;
    private ArrayList<Persona> personas;
    private String doamin_image = "http://127.0.0.1:8000/v1/users";


    public AdapterPersona(Context context, ArrayList<Persona>personas){
        this.inflater=LayoutInflater.from(context);
        this.personas=personas;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= inflater.inflate(R.layout.custompersonas_view,parent,false);
        return new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String nombres = personas.get(position).getNombres();
        String apellidos = personas.get(position).getApellidos();
        String email = personas.get(position).getEmail();
        String password = personas.get(position).getPassword();

        holder.txtNombres.setText(nombres);
        holder.txtApellidos.setText(apellidos);
        holder.txtEmail.setText(email);
        holder.txtPassword.setText(password);
    }

    @Override
    public int getItemCount() {
        return personas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombres,txtApellidos,txtEmail,txtPassword;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetalleActivity.class);
                    intent.putExtra("id",personas.
                            get(getAdapterPosition()).getId());
                    v.getContext().startActivity(intent);
                }
            });

            txtNombres = itemView.findViewById(R.id.txtNombres);
            txtApellidos = itemView.findViewById(R.id.txtApellidos);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            txtPassword = itemView.findViewById(R.id.txtPassword);
        }
    }
    public void  filter(final String strSearch){
        if (strSearch.length() == 0) {
            personas.clear();
            personas.addAll(personas);
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                personas.clear();
                List<Persona> collect = personas.stream()
                        .filter(i -> i.getNombres()
                                .toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());

                personas.addAll(collect);
            }
            else {
                personas.clear();
                for (Persona i : personas) {
                    if (i.getNombres().toLowerCase().contains(strSearch)) {
                        personas.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }


    public interface RecyclerItemClick {

        void itemClick(Persona persona);
    }
}




