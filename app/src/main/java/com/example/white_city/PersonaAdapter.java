package com.example.white_city;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.white_city.Model.Persona;

import java.util.List;

public class PersonaAdapter  extends ArrayAdapter<Persona> {

    private Context context;
    private List<Persona>personas;

    public PersonaAdapter(@NonNull Context context, int resource, @NonNull List<Persona> objects) {
        super(context, resource, objects);
        this.context=context;
        this.personas=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=layoutInflater.inflate(R.layout.activity_persona,parent, false);

        TextView txtidPersona=(TextView)rowView.findViewById(R.id.ID);
        TextView txtNombres=(TextView)rowView.findViewById(R.id.Nombres);
        TextView txtApellidos=(TextView)rowView.findViewById(R.id.Apellidos);
        TextView txtEmail=(TextView)rowView.findViewById(R.id.Email);
        TextView txtPassword=(TextView)rowView.findViewById(R.id.Password);

        txtidPersona.setText(String.format("ID:%",personas.get(position).getId()));
        txtNombres.setText(String.format("NOMBRES:%",personas.get(position).getNombres()));
        txtApellidos.setText(String.format("APELLIDOS:%",personas.get(position).getApellidos()));
        txtEmail.setText(String.format("EMAIL:%",personas.get(position).getEmail()));
        txtPassword.setText(String.format("PASSWORD:%",personas.get(position).getPassword()));

        return rowView;

    }
}
