package boletin.ejercicio1;

//import java.util.concurrent.locks.ReentrantLock;

public class Programa{

  //private ReentrantLock pantalla = new ReentrantLock();

  public static void main(String[] args){
        Matriz a = new Matriz();
        Matriz b = inicializar0();
        a.run(0);
        b.run(1);
  }
}
