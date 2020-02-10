package com.example.promasu3_examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BaseDatosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_datos);
    }

    public void nuevolibro(View v) {
        Intent i = new Intent(BaseDatosActivity.this,NuevoLibroActivity.class);
        startActivity(i);
    }

    public void listadoLibro(View v) {
        Intent i = new Intent(BaseDatosActivity.this,ListadoLibrosActivity.class);
        startActivity(i);
    }

    public void buscarlibro(View v) {
        Intent i = new Intent(BaseDatosActivity.this,BuscarLibrosActivity.class);
        startActivity(i);
    }

    public void salir(View v) {
        finish();
    }
}
