package com.example.promasu3_examen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class NuevoLibroActivity extends AppCompatActivity {

    EditText titulo,autor,isbn,editorial,npags;
    CheckBox leido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_libro);

        // Asignamos los edittext y el checkbox a las variables de mas arriba
        titulo = findViewById(R.id.etTitulo);
        autor = findViewById(R.id.etAutor);
        isbn = findViewById(R.id.etISBN);
        editorial = findViewById(R.id.etEditorial);
        npags = findViewById(R.id.etNPags);
        leido = findViewById(R.id.cbLeido);

    }

    // Añadimos el libro obteniendo los datos de los ET o CB y luego limpiamos
    public void addlibro(View v) {
        Libro libro = new Libro(
                Integer.parseInt(isbn.getText().toString()),
                titulo.getText().toString(),
                autor.getText().toString(),
                editorial.getText().toString(),
                Integer.parseInt(npags.getText().toString()),
                leido.isChecked()
        );
        SQLiteControlador sql = new SQLiteControlador(this);
        sql.addLibro(libro);
        limpiar(v);
    }
    //Limpiamos los campos
    public void limpiar(View v) {
        titulo.setText("");
        autor.setText("");
        isbn.setText("");
        editorial.setText("");
        npags.setText("");
        leido.setChecked(false);
    }

    public void volver(View v) {
        finish();
    }
}
