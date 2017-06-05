
package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TestRemote extends Remote {

    String saludar(String name) throws RemoteException;
    String segundaTarea(String name) throws RemoteException;
    Double sumaNumeros(Double a, Double b) throws RemoteException;

} //fin TestRemote
