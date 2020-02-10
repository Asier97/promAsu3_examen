package com.example.promasu3_examen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class SQLiteHelper extends SQLiteOpenHelper {

    // Libros que habrá por defecto cuando creemos la BD
    private Libro[] libros = new Libro[]{
            new Libro(9788491,"Memoria del Consumismo","Federico Jimenes Losantos","La esfera de los libros",1032,0),
            new Libro(978842,"El último apaga la luz","Nicanor Parra","Lumen",464,0)
    };

    // Constructor standard sin nada especial
    public SQLiteHelper(Context contexto, String nombre, CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    // Metodo oncreate para crear
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS Libro");
        db.execSQL("CREATE TABLE Libro (isbn INTEGER PRIMARY KEY, titulo TEXT, autor TEXT, editorial TEXT, npags INTEGER, leido INTEGER)");

        for (int x=0;x<libros.length;x++) {
            db.execSQL("INSERT INTO Libro(isbn,titulo,autor,editorial,npags,leido) VALUES("
                    +libros[x].getIsbn()+",'"
                    +libros[x].getTitulo()+"','"
                    +libros[x].getAutor()+"','"
                    +libros[x].getEditorial()+"',"
                    +libros[x].getNpags()+","
                    +libros[x].getLeidoInt()+")");
        }
    }

    // Metodo por si actualizamos que no es el caso
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS Libro");
        db.execSQL("CREATE TABLE Libro (isbn INTEGER PRIMARY KEY, titulo TEXT, autor TEXT, editorial TEXT, npags INTEGER, leido INTEGER)");

        for (int x=0;x<libros.length;x++) {
            db.execSQL("INSERT INTO Libro(isbn,titulo,autor,editorial,npags,leido) VALUES("
                    +libros[x].getIsbn()+",'"
                    +libros[x].getTitulo()+"','"
                    +libros[x].getAutor()+"','"
                    +libros[x].getEditorial()+"',"
                    +libros[x].getNpags()+","
                    +libros[x].getLeidoInt()+")");
        }
    }
}
