package com.example.promasu3_examen;

public class Libro {

    // Los datos del libro
    private String titulo,autor,editorial;
    private int isbn,npags;
    private boolean leido;

    public Libro() {}

    // Para guardar la informacion podremos enviar el dato "leido" tanto en booleano como en int
    public Libro(int isbn, String titulo, String autor, String editorial, int npags, boolean leido) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.npags = npags;
        this.leido = leido;
    }

    public Libro(int isbn, String titulo, String autor, String editorial, int npags, int leido) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.npags = npags;
        if (leido == 0) this.leido = false;
        else this.leido = true;
    }

    // getters y setters incluyendo metodos tanto int como boolean para leido
    // Esto se hace por comodidad, ya que para trabajar con BD viene mejor int y para lo demas boolean
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public int getNpags() {
        return npags;
    }

    public void setNpags(int npags) {
        this.npags = npags;
    }

    public int getLeidoInt() {
        if (leido) return 1;
        else return 0;
    }

    public boolean getLeido() {
        return leido;
    }

    public void setLeidoInt(int leido) {
        if (leido == 0) this.leido = false;
        else this.leido = true;
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
    }
}
