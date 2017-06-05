/* 
Servidor de socket que maneja solicitudes concurrentes
Adaptado por http://otroblogdetecnologias.blogspot.com

Implementa la clase Runnable para el manejo de los hilos

*/
package multithreadserver;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyServerThread implements Runnable {

    private PrintWriter out = null;
    private BufferedReader in = null;
    private int nombreProceso=0;
    
    
  public MyServerThread(Socket clientSocket,int contador) throws IOException {

    out = new PrintWriter(clientSocket.getOutputStream(), true);                   
    in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
    nombreProceso=contador;

  }//fin constructor MyServerThread

  public void run() {
    //do stuff with **in** and **out** to interact with client
    System.out.println("Dentro del thread ->"+nombreProceso);
    //-----------------------------------------------------------
    
                String inputLine;    
        try {
            //lectura de las cadenas entrantes
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Recibiendo->"+inputLine);
                
                //proceso de comandos recibidos desde el flujo de entrada
                if (inputLine.startsWith("COMANDO")){
                    out.println("El servidor envia-> COMANDO");                    
                }else{
                if(inputLine.startsWith("DESCARGAR")){     
                    out.println("El servidor envia-> DESCARGAR");                                        
                }else{

                     if(inputLine.startsWith("CERRAR")){
                        in.close();
                        out.close();

                        (new Thread(this)).interrupt();
                        //Thread.currentThread().interrupt();
                     }else{
                        out.println("NO SE RECONOCE->"+inputLine);                        
                     }//if
                
                
                }//descargar    

                }//fin if COMANDO               
            }//fin while in
            //-----------------------------------------------------------
        } catch (IOException ex) {
            Logger.getLogger(MyServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }// catch
        
  }// fin run
}// fin de clase