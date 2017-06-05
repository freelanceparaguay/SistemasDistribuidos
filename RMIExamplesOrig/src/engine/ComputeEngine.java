package engine;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import compute.Compute;
import compute.Task;

public class ComputeEngine implements Compute {

    public ComputeEngine() {
        super(); //constructor, llamada al metodo de la clase superior
    }
    
    // implementación de métodos declarados en las interfaces
    public String segundaTarea(String name) throws RemoteException {
        return "Respuesta del servidor -> de segundaTarea(), " + name;
    }    
    
    public Double sumaNumeros(Double a, Double b) throws RemoteException {
        System.out.println("Ejecutando sumaNumeros()");
        return (a+b);
    }            

    
/*    
    public <T> T executeTask(Task<T> t) {
        return t.execute();
    }
*/
    public static void main(String[] args) {
        // manejo de seguridad
//        if (System.getSecurityManager() == null) {
//            System.setSecurityManager(new SecurityManager());
//        }
        try {
            String name = "Compute"; //asignación del nombre para invocación de los métodos
            Compute engine = new ComputeEngine();
            // exporta objetos 
            Compute stub = (Compute) UnicastRemoteObject.exportObject(engine, 0);

            Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);            
//            Registry registry = LocateRegistry.getRegistry();
            registry.bind(name, stub);
            System.out.println("ComputeEngine bound");
        } catch (Exception e) {
            System.err.println("ComputeEngine exception:");
            e.printStackTrace();
        }
    }
}
