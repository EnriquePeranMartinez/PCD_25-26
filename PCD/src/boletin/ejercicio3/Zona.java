package boletin.ejercicio3;

import java.util.ArrayList;
import java.util.concurrent.locks.*;

public class Zona {
	// segundo monitor
	// para
	// primer monitor
	//private ReentrantLock cerrojo = new ReentrantLock(true);
	//private final Condition condicionSalida = cerrojo.newCondition();
	//private final Condition colaEspera = cerrojo.newCondition();
	private int clientesParaSalir = 0;
	private int maquinas;
	private final int id;
	private ArrayList<Cliente> clientesUsandoMaquinas;
	private ArrayList<Cliente> clientesCola;
	

	public Zona() {
		maquinas = 2;
		id = 0;
		clientesCola = new ArrayList<Cliente>();
		clientesUsandoMaquinas = new ArrayList<Cliente>();
	}
	
	public int getIdentificador(){
		return id;
	}
	
	
	public synchronized boolean estaOcupada() {
		// ver si la zona está completamente ocupada
		return maquinas == 0;
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
		//cerrojo.lock();
		try {
			clientesCola.add(c);
			
			// espera ocupada
			while (estaOcupada()){
				wait();
			}
			clientesCola.remove(c);
		    clientesUsandoMaquinas.add(c);
		    maquinas--;
		} 	finally {
			//cerrojo.unlock();
		}
		// Entrar en la cola para ver el tiempo de espera

	}
	
	/* Los procesos que entran se colocan en la cola de espera si hay un proceso que quiere salir.
	 * Los procesos para salir harán máquinas++ 
	 * Luego, los que estaban esperando hacen la entrada.
	 * */
	 
	 public synchronized void salir(Cliente c)throws InterruptedException {
		 //cerrojo.lock();
		 try {
			 maquinas++;
			 if(!clientesCola.isEmpty()) { // Aquí iba el cerrojo antes con hasWaiters(colaEspera)
				 //colaEspera.signal();
				 notify();
				 
				 //clientesParaSalir++;
				 //condicionSalida.await();
				 //clientesParaSalir--;
				 clientesCola.remove(c);
			 }
			 //if(clientesParaSalir > 0) //condicionSalida.signal();
			 
		 } finally {
			 //cerrojo.unlock();
		 }
	 }
}
