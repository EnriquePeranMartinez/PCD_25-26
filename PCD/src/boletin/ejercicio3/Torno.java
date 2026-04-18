package boletin.ejercicio3;

import java.util.Iterator;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

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
	public void intentarEntrar() throws InterruptedException{
		l.lock();
		try {
			if (!l.hasWaiters(torno1)) {	// Mira si hay sitio en el primero
				
			} 
			else if (!l.hasWaiters(torno2)){ // Mira si hay sitio en el segundo
				
			}
			else if (!l.hasWaiters(torno3)) { // Mira si hay sitio en el tercero
				 
			}
			else {						    // Si no hay sitio, uno random
				
			}
			
		} finally {
			l.unlock();
		}
	}
	
	
	public synchronized void hacerCola() throws InterruptedException {
		while (ocupado) {
			wait();	 // Nos esperamos hasta que esté libre
		}
		ocupado = true;
	}
	
	public synchronized void salir() {
		ocupado = false; // Desocupamos el torno
		notify(); // Le decimos que pase el siguiente
	}
	
	
}









