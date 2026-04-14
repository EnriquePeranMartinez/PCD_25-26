package boletin.ejercicio3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Torno {
	// primer monitor
	private boolean ocupado = false;
	private final int identificador;
	
	private ReentrantLock l = new ReentrantLock();
	private Condition torno1 = l.newCondition();
	private Condition torno2 = l.newCondition();
	private Condition torno3 = l.newCondition();
	
	public Torno() {
		identificador = 0;
	}

	
	public int getIdentificador() {
		return identificador;
	}

	
	// Si no está ocupado le dejamos entrar y lo ocupamos
	public void intentarEntrar() throws InterruptedException{
		l.lock();
		try {
			
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









