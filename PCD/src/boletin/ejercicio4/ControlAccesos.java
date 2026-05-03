package boletin.ejercicio4;

//Recuerda meter en el proyecto si no está, la biblioteca que se descarga de clase messagepassing
import messagepassing.MailBox;


import messagepassing.CommunicationScheme;

public class ControlAccesos extends Thread {
	
	private static final int NUMERO_AFICIONADOS = 50;
	
	private static MailBox tornoR;		// Ambos tornos
	private static MailBox tornoL;
	
	private static Aficionado[] aficionados;	// Array con los aficionados
	
	
	public static void inicializarAficionados() {
		aficionados = new Aficionado[NUMERO_AFICIONADOS];
		for (int i = 0; i < NUMERO_AFICIONADOS; i++) {
			aficionados[i] = new Aficionado("a"+i);
		}
		
	}
	


}
