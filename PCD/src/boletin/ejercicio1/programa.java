package boletin.ejercicio1;

//import java.util.concurrent.locks.ReentrantLock;

public class Programa{

  //private ReentrantLock pantalla = new ReentrantLock();

  public static void main(String[] args){
        Hilo h1 = new hilo();
        Hilo h2 = new hilo();
        h1.run(0);
        h2.run(1);
  }
}
