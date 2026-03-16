package boletin.ejercicio3;

import java.util.concurrent.locks.*;

public class Zona {
	// segundo monitor
	// para
	// primer monitor
	private ReentrantLock cerrojo = new ReentrantLock();
	private final static int NUMERO_CLIENTES = 50;
	private Condition[] condiciones;
	
	
	public Zona(ReentrantLock cerrojo, Condition[] condiciones) {
		for(int i = 0; i < 4; i++)
			condiciones[i] = cerrojo.newCondition();
	}
	
	
	private boolean verMaquinaLibre() {
		// ver en una zona si hay máquinas libres;
		return false;
	}
	
	public void entrar() {
	
	
	}
}
