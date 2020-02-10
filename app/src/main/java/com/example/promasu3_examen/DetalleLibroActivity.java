package com.example.promasu3_examen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class DetalleLibroActivity extends AppCompatActivity {

    TextView titulo,autor,isbn,editorial,paginas,leido;
    int isbnint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_libro);

        // Variables con sus TextViews
        titulo = findViewById(R.id.tvTitulo);
        autor = findViewById(R.id.tvAutor);
        isbn = findViewById(R.id.tvISBN);
        editorial = findViewById(R.id.tvEditorial);
        paginas = findViewById(R.id.tvPaginas);
        leido = findViewById(R.id.tvLeido);

        // Obtenemos los datos por separado de libro ya que tuvimos problemas enviando el objeto
        Bundle extras = getIntent().getExtras();

        // Escribimos cada dato donde corresponda
        titulo.setText(extras.getString("titulo"));
        autor.setText(extras.getString("autor"));
        editorial.setText(extras.getString("editorial"));

        isbnint = extras.getInt("isbn");
        isbn.setText(isbnint+"");

        paginas.setText(extras.getInt("paginas")+" "+getResources().getString(R.string.paginas));

        String libroleido = "NO LEIDO";
        if (extras.getBoolean("leido")) libroleido = "LEIDO";
        leido.setText(getResources().getString(R.string.libro)+" "+libroleido);
    }

    // Metodo que elimina el libro pasando el ISBN
    public void eliminar(View v) {
        SQLiteControlador sql = new SQLiteControlador(this);
        sql.delLibro(isbnint);
        cancelar(v);
    }

    public void cancelar(View v) {
        finish();
    }
}
