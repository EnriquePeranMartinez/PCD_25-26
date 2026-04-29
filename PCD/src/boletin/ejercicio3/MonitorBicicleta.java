package boletin.ejercicio3;

import java.util.concurrent.locks.*;


public class MonitorBicicleta {

	  private ReentrantLock bicicleta = new ReentrantLock();
	  private Condition colaBicicleta = bicicleta.newCondition();
	  private boolean isBicicletaOccupied;
	  
	  
	  public MonitorBicicleta(boolean ocupado) {
			this.isBicicletaOccupied = ocupado;
	  }
	  
	  
	  
	  public boolean getIsBicicletaOccupied() {
		  return isBicicletaOccupied;
	  }
	
	  public void setBicicletaOccupied(boolean isBicicletaOccupied) {
		  this.isBicicletaOccupied = isBicicletaOccupied;
	  }
	
	
	
	  public void usarBicicleta(Cliente c) throws InterruptedException{
	    bicicleta.lock();
	    try{
	    	while(isBicicletaOccupied) {
	    		colaBicicleta.await();
	      }
	    	isBicicletaOccupied = true;
	    } finally{
	    	bicicleta.unlock();
	    }
	  }
	
	  public void dejarBicicleta(Cliente c){
	    bicicleta.lock();
	    try{
	    	isBicicletaOccupied = false;
	    	colaBicicleta.notify();
	    } finally{
	    	bicicleta.unlock();
	    }
	
	  }
  
}
