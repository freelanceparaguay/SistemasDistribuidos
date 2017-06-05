
package Server;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {

        Remote stub = UnicastRemoteObject.exportObject(new TestRemote() {
            @Override
            public String sayHello(String name) throws RemoteException {
                return "Holaaaaaa, " + name;
            }
            
            @Override
            public String segundaTarea(String name) throws RemoteException {
                return "Segunda Tarea, " + name;
            }
        }, 0);

        Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        registry.bind("Test", stub);

    }

}