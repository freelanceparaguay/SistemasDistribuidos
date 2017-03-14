/*
 * Ejemplo de multihilos
 * Comentado por http://otroblogdetecnologias.blogspot.com
Desde el proceso principal, se crea un objeto de la clase Tarea, la misma implementa Runable
y luego se utiliza un pool de threads instanciado a partir de la clase 
Executor.
Por medio de un for se envían 5 hilos los cuales relian funciones ficticias.
En este ejemplo no se devuelven resultados.
*/
package hilos1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
 
public class Hilos1{
   public static void main (String args[]){
    System.out.println("Inicio de la ejecución principal");
    ExecutorService ejecutor = Executors.newFixedThreadPool(10);
    Tarea t; //clase con la tarea a ejecutar
    
     for(int i = 0;i<5; i++){
         System.out.println("Creando tarea="+i);
         t = new Tarea(""+i); //creación de tareas
         System.out.println("Lanzando tarea="+i);         
         ejecutor.execute(t);
     } //fin for
     ejecutor.shutdown(); //cierra el ejecutor creado
     
   }// fin main
}// fin Hilos1

