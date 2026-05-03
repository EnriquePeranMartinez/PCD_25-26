package boletin.ejercicio1;

import java.util.concurrent.locks.ReentrantLock;

public class programa {

  public static void main(String[] args){
	  	ReentrantLock c = new ReentrantLock();
        Hilo h1 = new Hilo(c);
        Hilo h2 = new Hilo(c);
        h1.run(0);
        h2.run(1);
  }
}
