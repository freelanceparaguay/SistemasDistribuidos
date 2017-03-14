/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multihilos;

/**
 *
 * @author usuario
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
      return Integer.valueOf(word.length());
    }//call    
  }//Hormiga  