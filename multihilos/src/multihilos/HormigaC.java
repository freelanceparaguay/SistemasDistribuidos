package multihilos;

/**
 *
 * La clase implementa métodos de Callable, esto permite devolver valores encapsulados
 * en un objeto.
 */
import java.util.*;
import java.util.concurrent.*;

  public class HormigaC implements Callable {
    private String word;
    private int numero;
    public HormigaC(String word,int numeroGet) {
      this.numero=numeroGet;  
      this.word = word;
    }//constructor

    public Integer call() {
        System.out.println("hormiga="+numero+ "-> ejecutando ");
        System.out.println("Enviando resultados-> desde hormiga="+numero);
        return Integer.valueOf(word.length());//devuelve un valor entero
    }//call    
  }//Hormiga  