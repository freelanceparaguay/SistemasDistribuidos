
package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TestRemote extends Remote {

    String sayHello(String name) throws RemoteException;
    String segundaTarea(String name) throws RemoteException;

}
