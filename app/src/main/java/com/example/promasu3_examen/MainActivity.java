package com.example.promasu3_examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    // Main muy simple que solo lleva a otras actividades
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sqlite(View v) {
        Intent i = new Intent(MainActivity.this,BaseDatosActivity.class);
        startActivity(i);
    }

    public void eltiempo(View v) {
        Intent i = new Intent(MainActivity.this,TiempoActivity.class);
        startActivity(i);
    }

    public void salir(View v) {
        finish();
    }
}
