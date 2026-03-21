package boletin.ejercicio3;

import java.util.Random;

public class Cliente extends Thread {
	private final int tiempo;
	private boolean pasadoTorno = false;		// Variable booleana que sirve para distinguir si un cliente ha pasado o no por un torno.
	private boolean pasadoZona = false;		// Variable para ver si ha pasado por una zona
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
		int aleatorio = random.nextInt(5); 
		return aleatorio;
	}
	
	public int getTiempo() {
		return tiempo;
	}
	
	/*public void setPasadoTorno(boolean b) {
		this.pasadoTorno = b;
	}*/

	private int comprobarTornoMenorTiempo(){
		int menorTiempo = Integer.MAX_VALUE;
		int torno = -1;
		int tiempo;
		for(int i = 0; i < tornos.length;i++) {
			tiempo = tornos[i].getTiempoEspera();
			if(tiempo < menorTiempo) {
				menorTiempo = tiempo;
				torno = i;
			}
		}
		return torno;
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
		// PARTE 1: TORNOS
		// Ver si hay algún torno libre y meterse
		for (Torno torno : tornos) {
			if(torno.estaLibre()) {
				torno.pasar(this);
				pasadoTorno = true;
				break;
			}
		}
		
		// Si no había libres, ver cúal tiene el menor tiempo de espera
		if (!pasadoTorno) {
			int mejorTorno = comprobarTornoMenorTiempo();
			tornos[mejorTorno].pasar(this);
		}
		
		// PARTE 2: ZONAS/MÁQUINAS
		// Ver cuántas hay libres y elegir una aleatoriamente
		//if(pasadoTorno){
			for (Zona zona : zonas) {
			if (zona.hayMaquinaLibre()) {
				zona.entrar(this);
				pasadoZona = true;
				}
			}
		//}

		
		// Si no ha pasado por una zona, vemos cuál es la que tiene 
		// el menor tiempo de espera para ponernos en cola 
		if (!pasadoZona) {
			int mejorZona = comprobarZonaMenorTiempo();
			zonas[mejorZona].entrar(this);
		}
	}
}
