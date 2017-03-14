/*
Comentado por http://otroblogdetecnologias.blogspot.com

Este es un ejemplo de pool de threads o hilos que devuelven valores.
Se lanzan los hilos y se esperan resultados en forma sincronizada cuando terminan todos.
Los resultados se recolectan en un array.
 */
package multihilos;

import java.util.*;
import java.util.concurrent.*;

/*
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
*/


public class Multihilos {
  /*
    Prepara el envio de procesos, los lanza y espera resultados luego del cálculo    
  */
    
  public static void main(String args[]) throws Exception {
      //Argumentos de entrada que serán analizados por las tareas
      String argumentos[]={"1","12","123","1234","12345","123456"};
      
      ExecutorService pool = Executors.newFixedThreadPool(10); //prepara el pool de procesos

      Set<Future<Integer>> set = new HashSet<Future<Integer>>();
      //---------------LLAMADA 
      int contadorProcesos=0;
      //recorre la cantidad de argumentos del vector
      
      System.out.println("Principal-> Bucle de argumentos ");
      for (String word: argumentos) {
          contadorProcesos++;
          //crea un proceso pasandole el parametro argumentos[]
          System.out.println("Principal-> creando hormiga "+contadorProcesos);
          Callable<Integer> callable = new HormigaC(word,contadorProcesos);
          //prepara al Future para que devuelva resultado
          //obtenido en el pool de los callables
          System.out.println("Principal-> enviando hormiga "+contadorProcesos+ " al pool ");          
          Future<Integer> future = pool.submit(callable);
          //agrega al conjunto que recoge los valores
          set.add(future);
      }//for

      //variables para conteo, demuestran los resultados que resuelven
      //los procesos
      int sum = 0; int contador=0;
    //en este ciclo procesa los datos en forma sincrona
    System.out.println("Principal-> procesando en forma sincrona los resultados obtenidos");    
    for (Future<Integer> future : set) {
      System.out.println("Principal-> Future-> "+contador);
      contador++;//contador
      sum += future.get();
      System.out.println("Principal-> Suma->"+sum);      
    }//for..
    //---------------------------------------
    pool.shutdown();
    // Espera que los procesos terminen
   
    while (!pool.isTerminated()) {
    }//WHILE


    
    //---------------------------------------    
    //muestra ls datos procesados
    System.out.printf("La suma de las longitudes de palabras es  %s%n", sum);
    //System.exit(0);
  }//main    

}//fin Multihilos