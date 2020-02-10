package com.example.promasu3_examen;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tiempo {

    //Variables de tiempo
    private Date dia;
    private int tempmin;
    private int tempmax;
    private String estadocielo;

    public Tiempo() {}

    // Dia lo guardamos como Date, pero recibimos y devolvemos texto
    public String getDia() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(dia);
    }

    public void setDia(String fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.dia = sdf.parse(fecha);
        } catch (ParseException e) {
            this.dia = null;
        }
    }
    // El resto de getters y setters
    public String getEstadocielo() {
        return estadocielo;
    }

    public void setEstadocielo(String estadocielo) {
        this.estadocielo = estadocielo;
    }

    public int getTempmin() {
        return tempmin;
    }

    public void setTempmin(int tempmin) {
        this.tempmin = tempmin;
    }

    public int getTempmax() {
        return tempmax;
    }

    public void setTempmax(int tempmax) {
        this.tempmax = tempmax;
    }
}
