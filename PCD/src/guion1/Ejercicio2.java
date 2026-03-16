package guion1;

import java.util.Iterator;

public class Ejercicio2 {
	
	/*
	 * Ejercicio 2: Escribir un programa que imprima “Hola Mundo” usando dos hilos que 
	 * reciben objetos que implementan la interfaz Runnable: uno imprime “Hola” y otro 
		“mundo”. El hilo principal deberá imprimir un mensaje cuando finaliza. Repetir la 
		ejecución varias veces.
	 */
	public static void main(String[] args) {
		
		HiloConRunnable a = new HiloConRunnable("Hola "); 
	    HiloConRunnable b = new HiloConRunnable("mundo"); 
	 
	    Thread t1 = new Thread(a); 
	    Thread t2 = new Thread(b); 
	 
	    
	    t1.start(); 
	    try { 
	        t1.join(); 
	    } catch (InterruptedException e) { 
	        e.printStackTrace(); 
	    } 
	    
	    t2.start(); 
	    try {  
	        t2.join(); 
	    } catch (InterruptedException e) { 
	        e.printStackTrace(); 
	    } 
	 
	    System.out.println("Fin del hilo principal"); 
	}
}
