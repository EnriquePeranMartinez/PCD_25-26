package boletin.ejercicio3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Random;


public class Torno {
	// primer monitor
	private boolean ocupado[] = {false, false, false};
	
	private ReentrantLock MonitorTorno = new ReentrantLock(true);
	private Condition tornos[] = {MonitorTorno.newCondition(), MonitorTorno.newCondition(), MonitorTorno.newCondition()};
	
	public Torno() {
		
	}


	
	// Si no está ocupado le dejamos entrar y lo ocupamos
	public void intentarEntrar(Cliente c) throws InterruptedException{
		MonitorTorno.lock();
		try {
			if (!ocupado[0]) {	// Mira si hay sitio en el primero
				//System.out.println("torno 1");
				ocupado[0] = true;
				c.setIndiceTorno(0);
			} 
			else if (!ocupado[1]){ // Mira si hay sitio en el segundo
				//System.out.println("torno 2");
				ocupado[1] = true;
				c.setIndiceTorno(1);
			}
			else if (!ocupado[2]) { // Mira si hay sitio en el tercero
				//System.out.println("torno 3");
				ocupado[2] = true;
				c.setIndiceTorno(2);
			}
			else {						    // Si no hay sitio, uno random
				
				int t = new Random().nextInt(3);
				ocupado[t] = true;
				//System.out.println("torno: " + t);
				c.setIndiceTorno(t);
			}
			
		} finally {
			MonitorTorno.unlock();
		}
	}
	
	
	public synchronized void hacerCola(Cliente c) throws InterruptedException {
		int i = c.getIndiceTorno() ;
		while (ocupado[i]) {
			wait();	 // Nos esperamos hasta que esté libre
		}
		ocupado[i] = true;
	}
	
	public synchronized void salir(Cliente c) {
		MonitorTorno.lock();
		try{
			ocupado[c.getIndiceTorno()] = false; // Desocupamos el torno
			tornos[c.getIndiceTorno()].signal(); // Le decimos que pase el siguiente
		} finally{
			MonitorTorno.unlock();
		}
	}
	
	
}









