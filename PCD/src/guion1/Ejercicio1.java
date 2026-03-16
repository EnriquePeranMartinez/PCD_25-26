package guion1;

public class Ejercicio1 {

	/*
	 * Ejercicio 1: Escribir un programa que imprima “Hola Mundo” usando dos hilos que 
		heredan de la clase Thread, uno imprime “Hola” y otro “mundo”. El hilo principal 
		imprime en pantalla un mensaje cuando finaliza. Repetir la ejecución varias veces.
	 */
	
	public static void main(String[] args) {
		
		Thread a = new HiloConHerencia("Hola ");
		Thread b = new HiloConHerencia("mundo ");
		
		a.start();
		b.start();
		
		System.out.println("Fin del hilo principal");
	}
}
