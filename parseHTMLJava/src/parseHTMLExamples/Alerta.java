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


/**
 * Example program to list links from a URL.
 */
public class Alerta {
    public static void main(String[] args) throws IOException {

//parámetros de configuracion
        String url="http://www.meteorologia.gov.py/aviso/alerta.php";
        String directorioActual = System.getProperty("user.dir"); //por default se toma el directorio de la aplicacion
//        String nombreArchivo="alertaSinAviso.html"; //ejemplo para prueba de no avisos meteorologicos
        String nombreArchivo="alertaAviso.html"; // ejemplo de un aviso meteorologico de tiempo seveero
        String fileHandler=directorioActual+"/"+nombreArchivo; //por default
        
        print("Descargando %s...", url);
        print("Fuente de datos de archivo=>"+directorioActual+"/"+nombreArchivo);        
        
        File input = new File(fileHandler);//archivo de entrada                
//        Document doc = Jsoup.parse(input, "UTF-8"); //descomentar con archivos

        Document doc = Jsoup.connect(url).get();

        //campos a completar
        String tituloAlertaS=""; //
	String encabezadoAlertaS=""; //
	String fechaAlertaS="";
	String horaAlertaS="";
	String comentarioAlertaS="";
	String zonaCoberturaAlertaS="";
	String fotografiaAlertaS="";
        //variables temporales para parsear
        String fechaTemp="";
        String horaTemp="";        
        
               
        //listado de objetos html a parsear    
        Elements elementsTituloAlerta = doc.select("h1"); //busca los <h1></h1>
        Elements elementsEncabezadoAlerta = doc.select("h2");//busca los <h2></h2>
        Elements elementsTexto = doc.select("p");//busca los <p></p>
        Elements elementsZonaCobertura = doc.select("li");//busca los <li></li>
        //--------------------------------------------------------

        print("\nTitulo: (%d)", elementsTituloAlerta.size());        
        for (Element src : elementsTituloAlerta) {
//            System.out.println(src.text()); //descomentar para debug
            tituloAlertaS=tituloAlertaS+src.text();
        }        
        
       print("\nEncabezado: (%d)", elementsEncabezadoAlerta.size());
        for (Element src : elementsEncabezadoAlerta) {
//            System.out.println(src.text());  //descomentar para debug
            encabezadoAlertaS=encabezadoAlertaS+src.text();
        }

       print("\nTExto: (%d)", elementsTexto.size());
       int contador=0;
        for (Element src : elementsTexto) {
            contador++;
//            System.out.println(contador+")"+src.text());  //descomentar para debug

	    switch(contador) {
                case 1 :
                    fechaTemp = src.text();
		    fechaAlertaS=fechaTemp.substring(6,17);
		    break;
		case 2 :
                    horaTemp=src.text();
                    horaAlertaS=horaTemp.substring(16,25);
                    break;
                case 4 :
                    comentarioAlertaS=comentarioAlertaS+src.text();
                    break;
            }//fin switch                        
        }//        
        
       print("\nZona Cobertura (%d)", elementsZonaCobertura.size());
       contador=0;
        for (Element src : elementsZonaCobertura) {
            contador++;
            //System.out.println(contador+")"+src.text()); //descomentar para debug
            zonaCoberturaAlertaS=zonaCoberturaAlertaS+src.text();
        }        


        System.out.println("---------------------------------");
        System.out.println(tituloAlertaS);
        System.out.println("---------------------------------");
        System.out.println(encabezadoAlertaS);
        System.out.println("Fecha: "+fechaAlertaS);
        System.out.println("Hora : "+horaAlertaS);
        System.out.println("Comentarios : "+comentarioAlertaS);
        System.out.println(zonaCoberturaAlertaS);
        System.out.println(fotografiaAlertaS);

        System.out.println("---------------------------------");
        
    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }


}//fin principal


