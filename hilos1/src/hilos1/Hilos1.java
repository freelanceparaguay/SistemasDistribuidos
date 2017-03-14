/*
 * Ejemplo de multihilos
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

