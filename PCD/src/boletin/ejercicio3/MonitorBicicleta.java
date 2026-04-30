package boletin.ejercicio3;

import java.util.concurrent.locks.*;


public class MonitorBicicleta {

	  private ReentrantLock bicicleta = new ReentrantLock();
	  private Condition colaBicicleta = bicicleta.newCondition();
	  private boolean isBicicletaOccupied;
	  private int tiempoEsperaBicicleta;
	  
	  
	  public MonitorBicicleta(boolean ocupado) {
			this.isBicicletaOccupied = ocupado;
			tiempoEsperaBicicleta = 0;
	  }
	  
	  
	  
	  public boolean getIsBicicletaOccupied() {
		  return isBicicletaOccupied;
	  }
	
	  public void setBicicletaOccupied(boolean isBicicletaOccupied) {
		  this.isBicicletaOccupied = isBicicletaOccupied;
	  }
	  
	  public int getTiempoEsperaBicicleta() {
		return tiempoEsperaBicicleta;
	}




	  public void usarBicicleta(Cliente c) throws InterruptedException{
	    bicicleta.lock();
	    try{
	    	while(isBicicletaOccupied) {
	    		colaBicicleta.await();
	      }
	    	isBicicletaOccupied = true;
	    	tiempoEsperaBicicleta += c.getTiempo();
	    } finally{
	    	bicicleta.unlock();
	    }
	  }
	
	  public void dejarBicicleta(Cliente c){
	    bicicleta.lock();
	    try{
	    	isBicicletaOccupied = false;
	    	tiempoEsperaBicicleta -= c.getTiempo();
	    	colaBicicleta.signal();
	    } finally{
	    	bicicleta.unlock();
	    }
	
	  }
  
}
