/* 
Fuente: http://www.javamexico.org/blogs/jpaul/ejemplo_bastante_simple_de_rmi
*/
package Cliente;

import interfaces.TestRemote;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente {

    public static void main(String[] args) throws RemoteException, NotBoundException {
        
        Double numero1=1.2;
        Double numero2=1.3;
        
        
        Registry registry = LocateRegistry.getRegistry();
        // Retorna el objeto remoto asociado con la url
        // regisro de objetos remotos que pueden ser localizados por nombres
        // retorna un stub asociado con el nombre en formato cadena
        TestRemote testRemote = (TestRemote) registry.lookup("Test");
        
        // utilizacion de las operaciones
        System.out.println(testRemote.saludar("Iniciar operaciones"));
        System.out.println(testRemote.segundaTarea("UN PARAMETRO"));       
        System.out.println(testRemote.sumaNumeros(numero1, numero2));         
    } //main cliente

} // fin Cliente.