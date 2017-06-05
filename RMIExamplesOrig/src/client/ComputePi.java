package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.math.BigDecimal;
import compute.Compute;

public class ComputePi {
    public static void main(String args[]) {
//        if (System.getSecurityManager() == null) {
//            System.setSecurityManager(new SecurityManager());
//        }
        try {
            String name = "Compute";
//            Registry registry = LocateRegistry.getRegistry(args[0]);
            Registry registry = LocateRegistry.getRegistry();
            Compute comp = (Compute) registry.lookup(name);
            Double numero1=1.3;
            Double numero2=1.4;
            
//            Pi task = new Pi(Integer.parseInt(args[1]));
//            BigDecimal pi = comp.executeTask(task);

              String resultado=comp.segundaTarea("PARAMETRO");
              Double suma=comp.sumaNumeros(numero1, numero2);

              System.out.println("Cliente-> Ejecución de órdenes");
              System.out.println("Cliente-> LLamado a comp.segundaTarea()"+resultado);
              System.out.println("Cliente-> "+suma);                       
//            System.out.println(pi);
        } catch (Exception e) {
//            System.err.println("ComputePi exception:");
//            e.printStackTrace();
        }
    }    
}
