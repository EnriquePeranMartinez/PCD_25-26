package boletin.ejercicio3;

import java.util.ArrayList;
import java.util.concurrent.locks.*;

public class Zona {
	// segundo monitor
	// para
	// primer monitor
	private ReentrantLock cerrojo = new ReentrantLock();
	private final static int NUMERO_CLIENTES = 50;
	private Condition[] condiciones;
	private int maquinas;
	private ArrayList<Cliente> clientesUsandoMaquinas;
	private ArrayList<Cliente> clientesCola;
	
	
	public Zona(ReentrantLock cerrojo, Condition[] condiciones) {
		for(int i = 0; i < 4; i++)
			condiciones[i] = cerrojo.newCondition();
		maquinas = 5;
	}
	
	
	private boolean verMaquinaLibre() {
		// ver en una zona si hay máquinas libres;
		return maquinas != 0;
	}
	
	public int obtenerTiempoRestanteZona(){
		return clientesCola.stream()
				.mapToInt(c -> c.getTiempo())
				.sum() 
				+ 
				clientesUsandoMaquinas.stream()
				.mapToInt(c -> c.getTiempo())
				.sum();
	}
	
	
	
	public synchronized void entrar(Cliente c) {
		// Elegir máquina random de entre las 5
		// Poner como ocupada esa máquina en el array
		// Dormir el hilo Y milisegundos
		
		// LOS CLIENTES SOLO PREGUNTAN SI HAY SITIO
		if(verMaquinaLibre()) {
			maquinas--;
		}
		
	}
	
	
	
}
