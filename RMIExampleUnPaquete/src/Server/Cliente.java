/* 
Fuente: http://www.javamexico.org/blogs/jpaul/ejemplo_bastante_simple_de_rmi
*/
package Server;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente {

    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry();
        TestRemote testRemote = (TestRemote) registry.lookup("Test");
        System.out.println(testRemote.sayHello("JavaMexico"));
        System.out.println(testRemote.segundaTarea("JavaMexico"));        
    }

}