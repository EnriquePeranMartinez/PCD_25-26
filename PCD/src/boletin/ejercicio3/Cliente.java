package boletin.ejercicio3;

import java.util.Random;

public class Cliente extends Thread {
	private final int tiempo;
	private Torno[] tornos;
	private boolean pasadoTorno;
	Random random = new Random();
	
	
	public Cliente(Torno[] tornos) {
		super();
		this.tiempo = generarTiempo();
		this.tornos = tornos;
	}
	
	private int generarTiempo() {
		int aleatorio = random.nextInt();
		return aleatorio;
	}
	
	public int getTiempo() {
		return tiempo;
	}
	
	public void setPasadoTorno(boolean b) {
		this.pasadoTorno = b;
	}




	public void run() {
		// 
	}
	
	
}
