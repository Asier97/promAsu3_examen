package com.example.promasu3_examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class BuscarLibrosActivity extends AppCompatActivity {

    RadioButton titulo,autor,editorial;
    TextView buscarpor;
    EditText buscar;
    Button bBuscar;
    String col;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_libros);

        titulo = findViewById(R.id.rbBuscarTitulo);
        autor = findViewById(R.id.rbBuscarAutor);
        editorial = findViewById(R.id.rbBuscarEditorial);
        buscarpor = findViewById(R.id.tvBuscarPor);
        buscar = findViewById(R.id.etBuscar);
        bBuscar = findViewById(R.id.bBuscar);

        visibilidad(View.GONE);
    }

    public void mostrar(View v) {
        switch (v.getId()) {
            case R.id.rbBuscarTitulo:
                col = "titulo";
                buscarpor.setText(getResources().getString(R.string.titulo));
                visibilidad(View.VISIBLE);
                break;
            case R.id.rbBuscarAutor:
                col = "autor";
                buscarpor.setText(getResources().getString(R.string.autor));
                visibilidad(View.VISIBLE);
                break;
            case R.id.rbBuscarEditorial:
                col = "editorial";
                buscarpor.setText(getResources().getString(R.string.editorial));
                visibilidad(View.VISIBLE);
                break;
            default:
                visibilidad(View.GONE);
        }
    }

    public void buscar(View v) {
        SQLiteControlador sql = new SQLiteControlador(this);
        String val = buscar.getText().toString();
        Libro[] libros = sql.getLibros(-1,col,val);
        int resultados = libros.length;
        if (resultados==0) {
            Toast t = Toast.makeText(getApplicationContext(),"No se han encontrado resultados",Toast.LENGTH_SHORT);
            t.show();
        } else {
            if (resultados==1) {
                Intent i = new Intent(BuscarLibrosActivity.this,DetalleLibroActivity.class);
                i.putExtra("titulo",libros[0].getTitulo());
                i.putExtra("autor",libros[0].getAutor());
                i.putExtra("isbn",libros[0].getIsbn());
                i.putExtra("editorial",libros[0].getEditorial());
                i.putExtra("paginas",libros[0].getNpags());
                i.putExtra("leido",libros[0].getLeido());
                startActivity(i);
            } else {
                Intent i = new Intent(BuscarLibrosActivity.this,ListadoLibrosActivity.class);
                i.putExtra("col",col);
                i.putExtra("val",val);
                startActivity(i);
            }
        }
    }

    private void visibilidad(int v) {
        buscarpor.setVisibility(v);
        buscar.setVisibility(v);
        bBuscar.setVisibility(v);
    }
}
