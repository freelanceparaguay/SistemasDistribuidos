/*
 * Ejemplo de una tarea Runnable 
 * Comentado por http://otroblogdetecnologias.blogspot.com
 Clase que implementa Runnable para correr un thread. La finalidad es correr
un hilo, enviarlo a dormir para que tarde su ejecución.
El thread es creado en el cilo principal.

 */
package hilos1;


public class Tarea implements Runnable{
   private int sleepTime;
   private String name;
   public Tarea(String name){
       this.name = name;//le asignamos un nombre a cada tarea.
       sleepTime = 1000;
    }//fin cnstructor
    
   public void run(){
       try{
           System.out.printf("El hilo de la tarea "+this.name+" va a dormir durante %d milisegundos.\n",sleepTime);
           Thread.sleep(sleepTime);//hacemos que cada hilo duerma durante 1 segundo
       }catch(InterruptedException exception){
           exception.printStackTrace();
       }//fin try/catch
       System.out.println("El hilo "+this.name+" se despierta");
       System.out.println("El hilo "+this.name+" finaliza"); 
    }//fin run
}// fin clase Tarea