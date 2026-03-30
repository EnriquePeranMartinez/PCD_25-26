package boletin.ejercicio3;

import java.util.ArrayList;
import java.util.concurrent.locks.*;

public class Zona {
	// segundo monitor
	// para
	// primer monitor
	private ReentrantLock cerrojo = new ReentrantLock(true);
	private final Condition condicionSalida = cerrojo.newCondition();
	private final Condition colaEspera = cerrojo.newCondition();
	private int clientesParaSalir = 0;
	private int maquinas;
	private final int id;
	private ArrayList<Cliente> clientesUsandoMaquinas;
	private ArrayList<Cliente> clientesCola;
	

	public Zona(int identificador) {
		maquinas = 5;
		id = identificador;
		clientesCola = new ArrayList<Cliente>();
		clientesUsandoMaquinas = new ArrayList<Cliente>();
	}
	
	public int getIdentificador(){
		return id;
	}
	
	
	public synchronized boolean hayMaquinaLibre() {
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
	
	public synchronized void entrar(Cliente c) throws InterruptedException {
		// Elegir máquina random de entre las 5
		// Poner como ocupada esa máquina en el array
		// Dormir el hilo Y milisegundos
		cerrojo.lock();
		try {
			clientesCola.add(c);
			
			// espera ocupada
			while (!hayMaquinaLibre()){
				colaEspera.await();
			}
			clientesCola.remove(c);
		    clientesUsandoMaquinas.add(c);
		    maquinas--;
		} 	finally {
			cerrojo.unlock();
		}
		// Entrar en la cola para ver el tiempo de espera

	}
	
	/* Los procesos que entran se colocan en la cola de espera si hay un proceso que quiere salir.
	 * Los procesos para salir harán máquinas++ 
	 * Luego, los que estaban esperando hacen la entrada.
	 * */
	 
	 public synchronized void salir(Cliente c)throws InterruptedException {
		 cerrojo.lock();
		 try {
			 maquinas++;
			 if(cerrojo.hasWaiters(colaEspera)) {
				 colaEspera.signal();
				 
				 
				 clientesParaSalir++;
				 condicionSalida.await();
				 clientesParaSalir--;
				 
			 }
			 if(clientesParaSalir > 0) condicionSalida.signal();
			 
		 } finally {
			 cerrojo.unlock();
		 }
	 }
}
