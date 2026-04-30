package boletin.ejercicio3;

import java.util.Random;
import java.util.concurrent.locks.*;

public class Zona {
	
	


	private MonitorBicicleta maquinaBicicleta;
	private final int[] maquinasPorZona = {5, 5, 5, 5};
	private final int[] tiempoPorZona = {0, 0, 0, 0};
	
	private ReentrantLock monitorZona = new ReentrantLock(true);
	private Condition[] zonas = {monitorZona.newCondition(), monitorZona.newCondition(), monitorZona.newCondition(), monitorZona.newCondition()};
	

	

	public Zona() {
		maquinaBicicleta = new MonitorBicicleta(false);

	}	
	
	public int getIdentificador(){
		return 0;
	}
	public MonitorBicicleta getMaquinaBicicleta() {
		return this.maquinaBicicleta;
	}
	
	private synchronized boolean isZonaOcuapada(int zona) {
		// ver si la zona está completamente ocupada
		return maquinasPorZona[zona] == 0;
	}
	
	private boolean isBicicletaElected() {
		return new Random().nextInt(5) % 5 == 0;
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
	
	
	private void escribirResultadoPorPantalla(Cliente c) {
		int tiempoAntes = tiempoPorZona[c.getZonaEscogida()] + c.getTiempo();
		int tiempoDespues = tiempoPorZona[c.getZonaEscogida()];
		if(c.getUsaBicicleta()) {
			System.out.println("--------------------------------------------------------------\n" +
					"Cliente " + c.getIdentificador() + " ha pasado por el torno " +  c.getIndiceTorno() + "\n"
					+ "Tiempo en el torno: " + c.getTiempo() + "\n"
					+ "Zona elegida: " + c.getZonaEscogida()+ "\n"
					+ "Tiempo de entrenamiento: " + c.getTiempo() + "\n"
					+ "Estimación de espera (sin incluirse a sí mismo): \n"
					+ "  Zona1(Cardio)= " + tiempoPorZona[0] + ",\n" 
					+ "  Zona2(fuerza)= " + tiempoPorZona[1] + ",\n" 
					+ "  Zona3(Funcional)= " + tiempoPorZona[2]+ ",\n"
					+ "  Zona4(estiramientos)= " + tiempoPorZona[3]+ "\n"
					+ "Espera en bicicleta premium(si aplica): "+ maquinaBicicleta.getTiempoEsperaBicicleta()+ "\n"
					+ "--------------------------------------------------------------\n"
					+"Me he ido de la zona " + c.getZonaEscogida() + " que tiene un tiempo original de  " + tiempoAntes + " y le he quitado mi tiempo " + c.getTiempo() + " y se ha quedado en " + tiempoDespues +"\n"
					);
		} else {
			System.out.println("--------------------------------------------------------------\n" +
					"Cliente " + c.getIdentificador() + " ha pasado por el torno " +  c.getIndiceTorno() + "\n"
					+ "Tiempo en el torno: " + c.getTiempo() + "\n"
					+ "Zona elegida: " + c.getZonaEscogida()+ "\n"
					+ "Tiempo de entrenamiento: " + c.getTiempo() + "\n"
					+ "Estimación de espera (sin incluirse a sí mismo): \n"
					+ "  Zona1(Cardio)= " + tiempoPorZona[0] + ",\n" 
					+ "  Zona2(fuerza)= " + tiempoPorZona[1] + ",\n" 
					+ "  Zona3(Funcional)= " + tiempoPorZona[2]+ ",\n"
					+ "  Zona4(estiramientos)= " + tiempoPorZona[3]+ "\n"
					+ "Espera en bicicleta premium(si aplica):\n"
					+ "--------------------------------------------------------------\n"
					+"Me he ido de la zona " + c.getZonaEscogida() + " que tiene un tiempo original de  " + tiempoAntes + " y le he quitado mi tiempo " + c.getTiempo() + " y se ha quedado en " + tiempoDespues +"\n"
					);
		}

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
		case 0 -> new Random().nextInt(4); // se elige de forma aleatoria
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
			if(zonaElegida == 0 && isBicicletaElected()) {
				c.setUsaBicicleta(true);;
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
			 
				 // Aquí iba el cerrojo antes con hasWaiters(colaEspera) !clientesCola.isEmpty()
				 //colaEspera.signal();
				 
			zonas[c.getZonaEscogida()].signal();
			maquinasPorZona[c.getZonaEscogida()]++;
				 
			 //if(clientesParaSalir > 0) //condicionSalida.signal();
			tiempoPorZona[c.getZonaEscogida()] -= c.getTiempo();
			escribirResultadoPorPantalla(c);
		 } finally {
			 monitorZona.unlock();
		 }
	 }
}
