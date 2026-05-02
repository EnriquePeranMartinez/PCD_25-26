package boletin.ejercicio4;

//Recuerda meter en el proyecto si no está, la biblioteca que se descarga de clase messagepassing
import messagepassing.MailBox;

import java.util.Random;

import messagepassing.CommunicationScheme;

public class Aficionado extends Thread {
	
	private int tiempoEstimado;		// Imagino que no tendrá un getter porque no hay memoria compartida
	private final String id;
	
	private MailBox solicitud;
	
	
	public Aficionado(String id) {
		tiempoEstimado = generarTiempo();
		this.id = id;
		solicitud = new MailBox();
	}
	
	private int generarTiempo() {
		Random random = new Random();
		int aleatorio = random.nextInt(9) + 1; // Entre 1 y 10
		return aleatorio;
	}

	public void run(){
		/*
		1. Realiza una acción previa (usando Sleep ()), por ejemplo caminar hacia los baños. 
		2. Solicita ponerse en una cola (R o L según el controlador de accesos le indique). 
		3. Realiza la validación en el torno asignado. 
		4. Libera la cola (deja libre el torno)
		5. Imprime en pantalla la información 
		*/
		for (int i = 0; i < 5; i++) {
			
			// 2
			solicitud.send(tiempoEstimado);
		}
		
	}
}
