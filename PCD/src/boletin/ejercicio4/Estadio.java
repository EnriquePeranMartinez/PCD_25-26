package boletin.ejercicio4;

import messagepassing.MailBox;

public class Estadio {
	
	
	public static void main(String[] args) {
		
		MailBox tornoR = new MailBox(1);
		MailBox tornoL = new MailBox(1);
		
		tornoR.send("token");
		tornoL.send("token");
		
		ControlAccesos.inicializarAficionados();
		
		
	}
	
}
