package boletin.ejercicio3;

import java.util.Random;

public class Cliente extends Thread {
	private final int tiempo;
	private boolean pasadoTorno;		// Variable booleana que sirve para distinguir si un cliente ha pasado o no por un torno.
	private Torno[] tornos;
	private Zona[] zonas;
	
	public Cliente(Torno[] t, Zona[] z) {
		super();
		this.tiempo = generarTiempo();
		tornos = t;
		zonas = z;
	}
	
	private int generarTiempo() {
		Random random = new Random();
		int aleatorio = random.nextInt();
		return aleatorio;
	}
	
	public int getTiempo() {
		return tiempo;
	}
	
	public void setPasadoTorno(boolean b) {
		this.pasadoTorno = b;
	}

	
	private int comprobarZonaMenorTiempo() {
		int menorTiempo = Integer.MAX_VALUE;
		int zona = -1;
		int tiempo;
		for(int i = 0; i < zonas.length;i++) {
			tiempo = zonas[i].obtenerTiempoRestanteZona();
			if(tiempo < menorTiempo) {
				menorTiempo = tiempo;
				zona = i;
			}
		}
		return zona;
	}


	public void run() {
		// parte 1 
		// entrar en el torno
		
		
		
		
		
		// parte 2
		
		// comprobar tiempo de espera de la zona
		
		// entrar a la zona de menos tiempo
		
		
	}
	
	
}
