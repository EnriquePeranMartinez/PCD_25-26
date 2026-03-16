package boletin.ejercicio3;

import java.util.Random;

public class Gimnasio {
	
	private static Torno[] tornos;
	private static Zona[] zonas;
	private static Cliente[] clientes;
	
	
	public void inicializar() {
		zonas[0] = new ZonaCardio();
		
		for(int i = 1; i < 4; i++) {
			if(i < 4) tornos[i] = new Torno();
			//zonas[i] = new Zona();
		}
	}

	
	public static void main(String[] args) {
		

		for(int i = 0; i < 50; i++){
			clientes[i] = new Cliente(tornos);
		}
		
		for(int i = 0; i < 50; i++){
			clientes[i].start();
		}
	}
}
