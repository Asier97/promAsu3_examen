package com.example.promasu3_examen;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class RSSParserDOM {

    private URL rssURL;
    private String ciudad;
    // Recibimos dos parametros, la URL del XML y para que ciudad va a ser
    public RSSParserDOM(String url, String ciudad){
        this.ciudad = ciudad;
        try{
            this.rssURL = new URL (url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public Tiempo parse() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Tiempo tiempo = new Tiempo();

        // Segun la ciudad es vitoria o si es las otras dos, las etiquetas cambian.
        // Por eso, segun la ciudad recibida pediremos unas y otras
        String tag;
        String fecha;
        String max;
        String min;
        String cielo;
        if (ciudad=="vitoria") {
            tag = "dia";
            fecha = "fecha";
            max = "temp_maxima";
            min = "temp_minima";
            cielo = "texto";
        } else {
            tag = "day1";
            fecha = "date";
            max = "temperature_max";
            min = "temperature_min";
            cielo = "text";
        }

        // Parser normal en el que tendremos en cuenta lo de las etiquetas
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dom = builder.parse(this.getInputStream());
            Element root = dom.getDocumentElement();

            //Localizamos todos los elementos <dia> o <day1>
            NodeList items = root.getElementsByTagName(tag);

            //for (int i=0; i<items.getLength(); i++){

                //ETIQUETA DIA
                Node hora = items.item(0);

                //OBTENEMOS LOS DATOS DE DIA
                NodeList datosTemporal = hora.getChildNodes();

                //RECORREMOS CADA ELEMENTO
                for (int j=0; j<datosTemporal.getLength(); j++){
                    //OBTENER NOMBRE ETIQUETA
                    Node dato = datosTemporal.item(j);
                    String etiqueta = dato.getNodeName();

                    if (etiqueta.equals(fecha)) {
                        String texto = obtenerTexto(dato);
                        tiempo.setDia(texto);
                    } else if (etiqueta.equals(max)) {
                        String texto = obtenerTexto(dato);
                        tiempo.setTempmax(Integer.parseInt(texto));
                    } else if (etiqueta.equals(min)) {
                        String texto = obtenerTexto(dato);
                        tiempo.setTempmin(Integer.parseInt(texto));
                    } else if (etiqueta.equals(cielo)) {
                        String texto = obtenerTexto(dato);
                        tiempo.setEstadocielo(texto);
                    }
                }
            //}

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }

        return tiempo;

    }

    // Obtenemos el texto de las etiquetas
    public String obtenerTexto (Node dato) {
        StringBuilder texto = new StringBuilder();
        NodeList fragmentos = dato.getChildNodes();

        for (int k=0; k<fragmentos.getLength(); k++) {
            texto.append(fragmentos.item(k).getNodeValue());
        }
        return texto.toString();
    }

    private InputStream getInputStream() {
        try {
            return rssURL.openConnection().getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
