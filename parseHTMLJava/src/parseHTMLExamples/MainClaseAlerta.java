/*
Ejemplos HTML parser, web scrap.
Organizados y compilados por
  http://otroblogdetecnologias.blogspot.com

Dependencias: jsoup-1.10.2.jar

Clase que demuestra el uso de jsoup como libreria para procesar etiquetas 
HTML desde una página web.
Se crea un objeto de la clase ParseAlerta, el cual recibe un parámetro como
url en su constructor, también puede inicializar el objeto utilizando
archivos html.

Se ofrecen dos archivos con formatos sobre los mensajes de alerta:
        alertaSinAviso.html
        alertaAviso.html

Otros códigos de ejemplo son:
  * Alerta.java -> contiene un ejemplo de parseo dentro de un metodo main() directamente para probar.
  * JsoupEjemplo.java -> ejemplo tomado directamente desde el sitio oficial de https://jsoup.org/cookbook/extracting-data/example-list-links

 */



package parseHTMLExamples;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;


public class MainClaseAlerta {
    public static void main(String[] args) throws IOException {
        
        //ejemplo de llamada directamente a una url
        ParseAlerta avisoSinAlerta=new ParseAlerta("http://www.meteorologia.gov.py/aviso/alerta.php");
        avisoSinAlerta.parsear();
                
        //ejemplo de llamada utilizando archivos de ejemplo
        ParseAlerta avisoArchivoAlerta=new ParseAlerta("alertaAviso.html","UTF-8");
        avisoArchivoAlerta.parsear();
        
        
        ParseAlerta avisoArchivoSinAlerta=new ParseAlerta("alertaSinAviso.html","UTF-8");
        avisoArchivoSinAlerta.parsear();        
        
    }//fin main
}//fin clase

