package boletin.ejercicio3;

import java.util.Iterator;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Random;


public class Torno {
	// primer monitor
	private boolean ocupado[];
	private final int identificador;
	
	private ReentrantLock l = new ReentrantLock();
	private Condition torno1 = l.newCondition();
	private Condition torno2 = l.newCondition();
	private Condition torno3 = l.newCondition();
	
	public Torno() {
		identificador = 0;
		for (int i = 0; i < ocupado.length; i++) {
			ocupado[i] = false;
		}
	}


	public int getIdentificador() {
		return identificador;
	}

	
	// Si no está ocupado le dejamos entrar y lo ocupamos
	public void intentarEntrar(Cliente c) throws InterruptedException{
		l.lock();
		try {
			if (!l.hasWaiters(torno1)) {	// Mira si hay sitio en el primero
				c.setIndiceTorno(0);
			} 
			else if (!l.hasWaiters(torno2)){ // Mira si hay sitio en el segundo
				c.setIndiceTorno(1);
			}
			else if (!l.hasWaiters(torno3)) { // Mira si hay sitio en el tercero
				c.setIndiceTorno(2);
			}
			else {						    // Si no hay sitio, uno random
				c.setIndiceTorno(new Random().nextInt() % 3);
			}
			
		} finally {
			l.unlock();
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
		l.lock();
		try{
			ocupado[c.getIndiceTorno()] = false; // Desocupamos el torno
			notify(); // Le decimos que pase el siguiente
		} finally{
			l.unlock;
		}
	}
	
	
}









