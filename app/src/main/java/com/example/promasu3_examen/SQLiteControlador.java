package com.example.promasu3_examen;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteControlador {

    // Nuestra BD se llamará examen
    private String nombrebd;
    private Context context;
    public SQLiteControlador(Context context) {
        this.context = context;
        nombrebd = "examen";
    }

    // Llamada al SQLite Helper
    private SQLiteHelper getSQLiteHelper() {
        return new SQLiteHelper(context, nombrebd, null, 1);
    }

    // Metodo que recibe libro y lo añade a la BD
    public void addLibro(Libro libro) {
        SQLiteHelper sqlh = getSQLiteHelper();
        SQLiteDatabase db = sqlh.getWritableDatabase();

        db.execSQL("INSERT INTO Libro(isbn,titulo,autor,editorial,npags,leido) VALUES("
                +libro.getIsbn()+",'"
                +libro.getTitulo()+"','"
                +libro.getAutor()+"','"
                +libro.getEditorial()+"',"
                +libro.getNpags()+","
                +libro.getLeidoInt()+")");


        db.close();
    }

    // Metodo que recibe -1 si listamos todos los libros, 0 si es los no leidos y 1 los leidos
    // Recibimos valores de columna y valor si estamos aplicando algun filtro de busqueda
    // Si recibiriamos (-1, autor, Asier) buscariamos todos los libros del autor Asier
    public Libro[] getLibros(int leidos,String col,String val) {
        SQLiteHelper sqlh = getSQLiteHelper();
        SQLiteDatabase db = sqlh.getReadableDatabase();

        // Creamos la clausula where (o la dejamos vacia)
        String where = "";
        if (leidos==0) where = " WHERE leido=0";
        else if (leidos==1) where = " WHERE leido=1";

        if (!col.equals("")) {
            if (!where.equals("")) where += " AND ";
            else where += " WHERE ";
            where += col+" LIKE '%"+val+"%'";
        }

        // Primero vemos el numero de resultados y despues los metemos al array
        Cursor c = db.rawQuery("SELECT COUNT(isbn) FROM Libro"+where,null);
        c.moveToFirst();
        Libro[] libros = new Libro[c.getInt(0)];

        c = db.rawQuery("SELECT isbn,titulo,autor,editorial,npags,leido FROM Libro"+where,null);
        int x = 0;
        while (c.moveToNext()) {
            Libro libro = new Libro(
                    c.getInt(0),
                    c.getString(1),
                    c.getString(2),
                    c.getString(3),
                    c.getInt(4),
                    c.getInt(5)
            );
            libros[x]=libro;
            x++;
        }
        c.close();
        db.close();

        return libros;
    }

    // Metodo para borrar libro
    public void delLibro(int isbn) {
        SQLiteHelper sqlh = getSQLiteHelper();
        SQLiteDatabase db = sqlh.getWritableDatabase();

        db.execSQL("DELETE FROM Libro WHERE isbn="+isbn);

        db.close();

    }

}
