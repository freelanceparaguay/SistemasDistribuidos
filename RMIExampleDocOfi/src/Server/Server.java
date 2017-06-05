/* 
    Clase principal que genera el código necesario para el servidor.
    Utilizando la definición de los métodos dentro de la funcion del stub.

*/
package Server;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


import interfaces.TestRemote; //inicializar interfaces


public class Server {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {

        System.out.println("Arancando servidor de objetos");
        System.out.println("Exportando objetos");
        Remote stub = UnicastRemoteObject.exportObject(new TestRemote() {
            /*Se sobreescriben los métodos definidos en las interfaces*/
            @Override
            public String saludar(String name) throws RemoteException {
                return "Respuesta desde el servidor, " + name;
            }
            
            @Override
            public String segundaTarea(String name) throws RemoteException {
                return "Ejecución de la Segunda Tarea, " + name;
            }
            
            @Override
            public Double sumaNumeros(Double a, Double b) throws RemoteException {
                return (a+b);
            }            
        }, 0); //fin de stub

        System.out.println("Registrando objetos");
        Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);

        System.out.println("Disponibilizando objetos");
        registry.bind("Test", stub); //liga a un nombre remoto url

        System.out.println("Servidor a la espera");
    } //fin main

}//fin clase