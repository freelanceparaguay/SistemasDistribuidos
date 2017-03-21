/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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



public class ParseAlerta {
    
    private int fuenteDatos=0; //default 0=url 1=archivo
    
    private String url="http://www.meteorologia.gov.py/aviso/alerta.php";
    private String directorioActual = System.getProperty("user.dir"); //por default se toma el directorio de la aplicacion
//    private String nombreArchivo="alertaSinAviso.html"; //ejemplo para prueba de no avisos meteorologicos
    private String nombreArchivo="alertaAviso.html"; // ejemplo de un aviso meteorologico de tiempo seveero
    private String fileHandler; //por default    

    private Document doc;


        //campos a completar
    private String tituloAlertaS=""; //
    private String encabezadoAlertaS=""; //
    private String fechaAlertaS="";
    private String horaAlertaS="";
    private String comentarioAlertaS="";
    private String zonaCoberturaAlertaS="";
    private String fotografiaAlertaS="";

    //variables temporales para parsear
    private String fechaTemp="";
    private String horaTemp="";        

    
    public ParseAlerta(String url) throws IOException{
        this.fuenteDatos=0;
        this.doc=Jsoup.connect(url).get();
    }//fin constructor

    public ParseAlerta(String nombreArchivo, String codificacion) throws IOException{
        this.fuenteDatos=1;
        fileHandler=directorioActual+"/"+nombreArchivo; //por default
        File input = new File(fileHandler);
        this.doc = Jsoup.parse(input, "UTF-8");        
    }//fin constructor
    
    
    
    public void parsear(){
        //----------------------------------------------------------------------
        //listado de objetos html a parsear    
        Elements elementsTituloAlerta = doc.select("h1"); //busca los <h1></h1>
        Elements elementsEncabezadoAlerta = doc.select("h2");//busca los <h2></h2>
        Elements elementsTexto = doc.select("p");//busca los <p></p>
        Elements elementsZonaCobertura = doc.select("li");//busca los <li></li>
        //--------------------------------------------------------

//        print("\nTitulo: (%d)", elementsTituloAlerta.size());        
        for (Element src : elementsTituloAlerta) {
//            System.out.println(src.text()); //descomentar para debug
            tituloAlertaS=tituloAlertaS+src.text();
        }        
        
//       print("\nEncabezado: (%d)", elementsEncabezadoAlerta.size());
        for (Element src : elementsEncabezadoAlerta) {
//            System.out.println(src.text());  //descomentar para debug
            encabezadoAlertaS=encabezadoAlertaS+src.text();
        }

//       print("\nTExto: (%d)", elementsTexto.size());
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
        
//       print("\nZona Cobertura (%d)", elementsZonaCobertura.size());
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
        //----------------------------------------------------------------------
    
    }//fin parsear
    
    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }//fin print

        
}//fin ParseAlerta
