package boletin.ejercicio4;

import messagepassing.MailBox;

public class Estadio {
	
	
		
		private static final int NUMERO_AFICIONADOS = 50;
		 
	    public static void main(String[] args) {
	 

	        MailBox tornoR = new MailBox(1);
	        MailBox tornoL = new MailBox(1);
	        tornoR.send("token");
	        tornoL.send("token");
	 
	        MailBox mutexPantalla = new MailBox(1);
	        mutexPantalla.send("token");
	 
	        MailBox solicitudControl = new MailBox();
	 
	        MailBox[] respuestas = new MailBox[NUMERO_AFICIONADOS];
	        for (int i = 0; i < NUMERO_AFICIONADOS; i++) {
	            respuestas[i] = new MailBox(1); // cada aficionado solo espera 1 respuesta a la vez
	        }
	 
	        ControlAccesos controlAccesos = new ControlAccesos(solicitudControl, respuestas);
	        controlAccesos.start();
	 
	        Aficionado[] aficionados = new Aficionado[NUMERO_AFICIONADOS];
	        for (int i = 0; i < NUMERO_AFICIONADOS; i++) {
	            aficionados[i] = new Aficionado("a" + i, i, solicitudControl, respuestas[i], tornoR, tornoL, mutexPantalla);
	            aficionados[i].start();
	        }
	 
	        try {
	            for (Aficionado a : aficionados) {
	                a.join();
	            }
	            controlAccesos.join();
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	        }
	 
	        System.out.println("=== Simulación finalizada ===");
	    }
	
}
