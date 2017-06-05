package multithreadserver;

import java.net.*;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultithreadServer {
      private static int PORT = 10005;

  public static void main(String args[]) throws IOException {
      try{      
          final ExecutorService executor = Executors.newCachedThreadPool();
           int contador=0;
          System.out.println("Creando serverSocket");
          ServerSocket serverSocket = new ServerSocket(PORT);
          System.out.println("Lanzando un nuevo proceso");
          contador++;

          while(true){
              Socket socket=serverSocket.accept();
              MyServerThread proceso=new MyServerThread(socket,contador);
              //executor.submit();
              executor.execute(proceso);
          }//while 
      }catch(IOException e){}

  }//fin main       
}//MultithreadServer
