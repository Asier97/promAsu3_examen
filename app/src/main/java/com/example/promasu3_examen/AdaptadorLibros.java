package com.example.promasu3_examen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AdaptadorLibros extends ArrayAdapter<Libro> {

    private Libro[] datosLibros;
    public AdaptadorLibros(@NonNull Context context, Libro[] datos) {
        super(context, R.layout.adaptador_libros, datos);
        this.datosLibros = datos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.adaptador_libros, null);

        String titulo = datosLibros[position].getTitulo();
        String autor = datosLibros[position].getAutor();

        TextView tvTitulo = item.findViewById(R.id.tvTitulo);
        tvTitulo.setText(titulo);

        TextView tvAutor = item.findViewById(R.id.tvAutor);
        tvAutor.setText(autor);

        return (item);
    }
}