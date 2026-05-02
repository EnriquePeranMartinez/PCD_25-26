package boletin.ejercicio2;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

import boletin.Panel;


public class CentroDeportivo {
	public static final int NUM_PANELES = 3;
	public static Semaphore semaforoGeneral = new Semaphore(3);
	public static Semaphore[] semBinario = {new Semaphore(1),new Semaphore(1), new Semaphore(1)};
  	public static ArrayList<Panel> paneles = new ArrayList<>();

 
  
  
  	public static void main(String[] args){
	  	
		  for (int i = 0; i < NUM_PANELES; i++) {
	          paneles.add(new Panel("Panel " + i, 100 + (i * 450), 100));
	      }
		  Equipo[] equipos = IntStream.range(0, 10)
	                .mapToObj(h -> new Equipo("Equipo " + h))
	                .toArray(Equipo[]::new);

	        for (Equipo e : equipos) e.start();
    
  	}
}
