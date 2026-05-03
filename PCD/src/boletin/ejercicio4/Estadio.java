package boletin.ejercicio4;

import messagepassing.MailBox;

public class Estadio {
	
	
	public static void main(String[] args) {
		
		tornoR = new MailBox(1);
		tornoL = new MailBox(1);
		
		tornoR.send("token");
		tornoL.send("token");
		
		inicializarAficionados();
		
		
	}
	
}
