package SimpleThreads;

/**
 * Fuente: http://docs.oracle.com/javase/tutorial/essential/concurrency/simple.html
 * 
 * El ejemplo consiste en un ciclo principal el cual lanza un hilo que ejecuta
 * una tarea repetitiva. Esta tarea consiste en mostrar mensajes en pantalla.
 * El ejemplo cuenta con la posibilidad de capturar interrupciones e indicar que
 * el ciclo repetitivo no ha terminado.
 * El proceso principal tiene un determinado tiempo de paciencia para que finalice
 * el hilo lanzado, si este periodo es mayor al tiempo de paciencia se corta la
 * ejecución del hilo.
 * 
 * Comentado por http://otroblogdetecnologias.blogspot.com
 * @author usuario
 */

public class SimpleThreads {

    // Muestra los mensajes y el nombre del hilo que lo ejecuta
    static void threadMessage(String message) {
        String threadName =Thread.currentThread().getName();
        System.out.format("%s: %s%n",threadName, message);
    }

    /* Esta clase, implementa una tarea en un ciclo repetitivo, la tarea
    consite en mostrar mensajes. La misma puede ser interrumpida.
    El ejemplo podría ser ampliado colocando la clase MessageLoop en un archivo
    separado. 
    Encaso de que el ciclo no haya terminado, la clase puede capturar la excepción
    y anunciar que no terminó.
    */
    private static class MessageLoop implements Runnable {
        public void run() {
            String importantInfo[] = {
                "Mares eat oats",
                "Does eat oats",
                "Little lambs eat ivy",
                "A kid will eat ivy too"
            };
            try {
                //comienza el trabajo en el ciclo del hilo
                for (int i = 0; i < importantInfo.length; i++) {
                    // Pause for 4 seconds
                    Thread.sleep(4000);
                    // Print a message
                    threadMessage(importantInfo[i]);//muestra un mensaje de ejecución
                }//for
            } catch (InterruptedException e) {
                threadMessage("I wasn't done!"); //avisa que fue interrumpido y no terminó
            }//try/catch
        }//fin run
    }//fin clase privada MessageLoop

    public static void main(String args[]) throws InterruptedException {

        // Espera en milisegundos antes de interrumpir al thread
        // MessageLoop
        // thread (por defecto espera una hora).
        //Cambiar la paciencia para probar el funcionamiento del hilo
        //long patience = 1000 * 60 * 60;
        long patience = 1 * 6 * 6;
        
        // El if es para recibir valores de la linea de comandos y asignar
        //el valor para paciencia en segundos
        if (args.length > 0) {
            try {
                patience = Long.parseLong(args[0]) * 1000;
            } catch (NumberFormatException e) {
                System.err.println("Argument must be an integer.");
                System.exit(1);
            }//fin tray/catch
        }//fin if argumentos

        threadMessage("Starting MessageLoop thread"); //llama al método para mostrar el mensaje
        long startTime = System.currentTimeMillis();//guarda el tiempo de partida
        Thread t = new Thread(new MessageLoop()); //instanciar un objeto del tipo thread
        t.start(); //arranca el proceso

        threadMessage("Waiting for MessageLoop thread to finish");
        // Realiza el ciclo mientras el thread de la clase MessageLoop exista
        while (t.isAlive()) {
            threadMessage("Still waiting...");//mientras gira en el ciclo, avisa que sigue esperando
            // Espera de un segundo al threat para terminar.
            t.join(1000);
            //si el tiempo de ejecución del principal es mayor al tiempo de paciencia           
            if (((System.currentTimeMillis() - startTime) > patience)
                  && t.isAlive()) {
                threadMessage("Tired of waiting!"); //avisa que terminó el tiempo
                t.interrupt(); //interrumpe al proceso
                // Shouldn't be long now
                // -- wait indefinitely
                t.join();
            }//fin if
        }//cilo while principal
        threadMessage("Finally!");
    }//fin main
}//Fin SimpleThreads.