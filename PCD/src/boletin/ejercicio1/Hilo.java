package boletin.ejercicio1;

import java.util.concurrent.locks.ReentrantLock;


public class Hilo extends Thread {
    private Matriz A, B;
    private ReentrantLock cerrojo = new ReentrantLock();
    public Hilo(ReentrantLock c){
        A = new Matriz();
        cerrojo = c;
    }

    public void run(int caso){
      try{
        cerrojo.lock();
        switch (caso) {
          case 0:
            B.sumar(A, A);
            B.mostrarMatriz();
          case 1:
            B.multiplicar(A, A);
            B.mostrarMatriz();
          default: 
            System.err.println("Número de caso desconocido");
        } 
      } finally {
        cerrojo.unlock();
      }
    }
  
}

	  
