package boletin.ejercicio3;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.*;

public class Zona {
	
	

	private int clientesParaSalir = 0;
	private int maquinas;
	private final int[] maquinasPorZona = {5, 5, 5, 5};
	private final int[] tiempoPorZona = {0, 0, 0, 0};
	
	private ReentrantLock monitorZona = new ReentrantLock(true);
	private Condition[] zonas;
	

	

	public Zona() {
		maquinas = 5;

		for(int i = 0; i < 4; i ++) {
			zonas[i] = monitorZona.newCondition();
		}
	}	
	
	public int getIdentificador(){
		return 0;
	}
	
	
	private synchronized boolean isZonaOcuapada(int zona) {
		// ver si la zona está completamente ocupada
		return maquinasPorZona[zona] == 0;
	}
	
	
	/*
	public int obtenerTiempoRestanteZona(){
		return clientesCola.stream()
				.mapToInt(c -> c.getTiempo())
				.sum() 
				+ 
				clientesUsandoMaquinas.stream()
				.mapToInt(c -> c.getTiempo())
				.sum();	
	}
	*/
	
	private int obtenerZonaMenorTiempo() {
		int menorTiempo = Integer.MAX_VALUE;
		int zona = -1;
		for(int i = 0; i < tiempoPorZona.length; i++) {
			
			if(menorTiempo > tiempoPorZona[i]) {
				menorTiempo = tiempoPorZona[i]; // en caso de igualdad, se toma aquella que primero se descubrió
				zona = i;
			}
		}
		return zona;
	}
	
	
	
	
	private int eleccionZona() {
		// dos casos
		// CASO 0: TODAS LIBRES
		// CASO 1: HAY ZONAS OCUPADAS
		
		int caso = 0;
		for(int t = 0; t < tiempoPorZona.length; t++) {
			if(tiempoPorZona[t] != 0) {
				caso = 1;
			}
		}
		
		int zonaEscogida = switch (caso) {
		case 0 -> new Random().nextInt() % 4; // se elige de forma aleatoria
		case 1 -> obtenerZonaMenorTiempo(); 
		
		default -> 
			throw new IllegalArgumentException("Unexpected value: " + caso);
		}; 
		
		return zonaEscogida;
	}
	
	
	
	
	
	public void entrar(Cliente c) throws InterruptedException {
		// Elegir máquina random de entre las 5
		// Poner como ocupada esa máquina en el array
		// Dormir el hilo Y milisegundos
		monitorZona.lock();
		try {
			
			int zonaElegida = eleccionZona();
			tiempoPorZona[zonaElegida] += c.getTiempo(); // se suma el tiempo del cliente que quiere entrar y se mete en la zona del condition
			// espera ocupada en la zona
			while (isZonaOcuapada(zonaElegida)){
				zonas[zonaElegida].await();;
			}
			
			
			maquinasPorZona[zonaElegida]--;
			
			// el proceso se queda dormido
			c.setZonaEscogida(zonaElegida);		
		} 	finally {
			monitorZona.unlock();
		}
		

	}
	
	/* Los procesos que entran se colocan en la cola de espera si hay un proceso que quiere salir.
	 * Los procesos para salir harán máquinas++ 
	 * Luego, los que estaban esperando hacen la entrada.
	 * */
	 
	 public void salir(Cliente c)throws InterruptedException {
		 monitorZona.lock();
		 try {
			 if(monitorZona.hasWaiters(zonas[c.getZonaEscogida()])) { 
				 // Aquí iba el cerrojo antes con hasWaiters(colaEspera) !clientesCola.isEmpty()
				 //colaEspera.signal();
				 
				 zonas[c.getZonaEscogida()].signal();
				 maquinasPorZona[c.getZonaEscogida()]++;
	
			 }
			 //if(clientesParaSalir > 0) //condicionSalida.signal();
			 
		 } finally {
			 monitorZona.unlock();
		 }
	 }
}
