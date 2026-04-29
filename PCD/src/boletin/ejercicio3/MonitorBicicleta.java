package boletin.ejercicio3;

import java.util.concurrent.locks.*;


public class MonitorBicicleta {

  private ReentrantLock bicicleta = new ReentrantLock();
  private Condition colaBicicleta = bicicleta.newCondition();



  public void usarBicicleta(Cliente c){
    bicicleta.lock;
    try{
      
    } finally{
      bicicleta.unlock;
    }

    
  }

  public void dejarBicicleta(Cliente c){
    bicicleta.lock;
    try{
      notify();
    } finally{
      bicicleta.unlock;
    }

  }
  
}
