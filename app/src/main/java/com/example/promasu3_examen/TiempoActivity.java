package com.example.promasu3_examen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TiempoActivity extends AppCompatActivity {

    // En la variable tiempo se guardara el objeto tiempo
    Tiempo tiempo;
    // Para escribir en los textviews
    TextView en,dia,max,min,cielo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiempo);

        // Asociamos variables a textviews
        en = findViewById(R.id.tvTiempoEn);
        dia = findViewById(R.id.tvDia);
        max = findViewById(R.id.tvTemax);
        min = findViewById(R.id.tvTemin);
        cielo = findViewById(R.id.tvCielo);
    }

    // Si le damos a vitoria cargamos el XML indicando que es para vitoria
    public void vitoria(View v) {
        en.setText(
                getResources().getString(R.string.tiempoen)
                +" "+getResources().getString(R.string.vitoria)
        );
        CargarXmlTask tarea = new CargarXmlTask();
        tarea.execute("http://xml.tutiempo.net/xml/8043.xml","vitoria");
    }

    // Si le damos a bilbao o donostia distinguimos cual de las dos es y pasamos el
    // correspondiente XML junto a un indicador de que es para ellos (ver codigo ParserDOM)
    public void otros(View v) {
        String url = "";
        switch (v.getId()) {
            case R.id.bBilbao:
                en.setText(
                        getResources().getString(R.string.tiempoen)
                        +" "+getResources().getString(R.string.bilbao)
                );
                url = "https://api.tutiempo.net/xml/?lan=es&apid=qsTX4X4qq44as6Q&lid=8050";
                break;
            case R.id.bDonostia:
                en.setText(
                        getResources().getString(R.string.tiempoen)
                                +" "+getResources().getString(R.string.sanse)
                );
                url = "https://api.tutiempo.net/xml/?lan=es&apid=qsTX4X4qq44as6Q&lid=4917";
                break;

        }
        CargarXmlTask tarea = new CargarXmlTask();
        tarea.execute(url,"otros");
    }

    // Tarea asincrona que carga el xml y muestra los datos del tiempo
    private class CargarXmlTask extends AsyncTask<String,Integer,Boolean> {

        protected Boolean doInBackground(String... params) {
            RSSParserDOM domParser = new RSSParserDOM(params[0],params[1]);
            tiempo = domParser.parse();
            return true;
        }

        protected void onPostExecute(Boolean result) {
            if (tiempo != null) {

                dia.setText(getResources().getString(R.string.dia)+" "+tiempo.getDia());
                max.setText(getResources().getString(R.string.temax)+" "+tiempo.getTempmax());
                min.setText(getResources().getString(R.string.temin)+" "+tiempo.getTempmin());
                cielo.setText(getResources().getString(R.string.estadocielo)+" "+tiempo.getEstadocielo());

            }
        }
    }

    public void volver(View v) {
        finish();
    }
}
