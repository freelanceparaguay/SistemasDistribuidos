package compute;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Compute extends Remote {
    //<T> T executeTask(Task<T> t) throws RemoteException;
    public String segundaTarea(String name) throws RemoteException;
    public Double sumaNumeros(Double a, Double b) throws RemoteException;
}
