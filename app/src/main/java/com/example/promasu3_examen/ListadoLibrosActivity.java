package com.example.promasu3_examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ListadoLibrosActivity extends AppCompatActivity {

    //Variables variadas para utilizar despues en el programa y sin problemas
    private Libro[] libros;
    SQLiteControlador sql;
    RadioGroup rg;
    RadioButton rbleido,rbnoleido,rbtodos;
    ListView lv;
    AdaptadorLibros adaptadorLibros;
    String col,val;
    boolean sel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_libros);

        //Asignamos la mayoria de variables a componentes
        rg = findViewById(R.id.rgLeidos);
        rbleido = findViewById(R.id.rbLeido);
        rbnoleido = findViewById(R.id.rbNoLeidos);
        rbtodos = findViewById(R.id.rbTodosLeidos);
        lv = findViewById(R.id.lvLibros);
        sql = new SQLiteControlador(this);

        // Si venimos de hacer una busqueda lo sabremos aquí
        Bundle extras = getIntent().getExtras();

        // Dejamos las variables de columna y valor vacias por si no hay ninguna busqueda
        col = "";
        val = "";
        // Si hay nullpointerexception es que no hay extras, es decir, no tenemos que hacer
        // una busqueda por autor ni nada así. Si hay extras se asignan los valores
        try {
            if (extras.getString("col") != null) {
                col = extras.getString("col");
                val = extras.getString("val");
                rbleido.setEnabled(false);
                rbnoleido.setEnabled(false);
                rbtodos.setEnabled(false);
            }
        } catch (NullPointerException e) {
            Log.i("ListadoLibros","No hay extras");
        }

        // Cargamos todos los libros, y filtramos la busqueda si se da el caso (ver sqlitecontrolador)
        libros = sql.getLibros(-1,col,val);
        //Con los libros cargados actualizamos el LV
        cargarLista();
        // Long click listener para ver detalles del libro
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                Libro libro = libros[pos];
                Intent i = new Intent(ListadoLibrosActivity.this,DetalleLibroActivity.class);
                i.putExtra("titulo",libro.getTitulo());
                i.putExtra("autor",libro.getAutor());
                i.putExtra("isbn",libro.getIsbn());
                i.putExtra("editorial",libro.getEditorial());
                i.putExtra("paginas",libro.getNpags());
                i.putExtra("leido",libro.getLeido());
                startActivity(i);

                return true;
            }
        });
    }

    // Dependiendo de que RB pulsemos buscamos todos los libros (-1), los no leidos (0) o los leidos (1)
    public void recargarLibros(View v) {
        switch (v.getId()) {
            case R.id.rbLeido:
                libros = sql.getLibros(1,col,val);
                break;
            case R.id.rbNoLeidos:
                libros = sql.getLibros(0,col,val);
                break;
            default:
                libros = sql.getLibros(-1,col,val);
        }
        cargarLista();
    }

    private void cargarLista() {
        adaptadorLibros = new AdaptadorLibros(this, libros);
        lv.setAdapter(adaptadorLibros);
    }

    public void volver(View v) {
        finish();
    }
}
