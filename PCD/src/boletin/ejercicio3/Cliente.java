package boletin.ejercicio3;

import java.util.ArrayList;
import java.util.Random;

public class Cliente extends Thread {
	private final int tiempo;
	private Torno pasadoTorno;		// Variable booleana que sirve para distinguir si un cliente ha pasado o no por un torno.
	private Zona pasadoZona;	// Variable para ver si ha pasado por una zona
	private final String identificador;
	private int indiceTorno;
	private Zona zona;
	private int zonaEscogida;
	private boolean usaBicicleta;
	
	public Cliente(Torno _pasadoTorno, Zona z,  String _id) {
		super();
		this.tiempo = generarTiempo();
		zona = z;
		identificador = _id;
		pasadoTorno = _pasadoTorno;
		pasadoZona = null;
		usaBicicleta = false;
	}
	
	private int generarTiempo() {
		Random random = new Random();
		int aleatorio = random.nextInt(4) + 1; // Entre 1 y 5
		return aleatorio;
	}
	
	public int getTiempo() {
		return 1;
	}
	
	
	/*public void setPasadoTorno(boolean b) {
		this.pasadoTorno = b;
	}*/

	public Torno getPasadoTorno() {
		return pasadoTorno;
	}

	public Zona getPasadoZona() {
		return pasadoZona;
	}

	public String getIdentificador() {
		return this.identificador;
	}
	public int getZonaEscogida() {
		return zonaEscogida;
	}
	public boolean getUsaBicicleta() {
		return this.usaBicicleta;
	}
	
	public void setUsaBicicleta(boolean usada) {
		this.usaBicicleta = usada;
	}
	public void setZonaEscogida(int _zona) {
		zonaEscogida = _zona;
	}
	public int getIndiceTorno() {return indiceTorno;}
	public void setIndiceTorno(int _torno) {indiceTorno = _torno;}
/*
	private void imprimirResult() {
		System.out.println("--------------------------------------------------------------\n" +
							"Cliente " + getIdentificador() + " ha pasado por el torno " +  getPasadoTorno().getIdentificador() + "\n"
							+ "Tiempo en el torno: " + getTiempo() + "\n"
							+ "Zona elegida: " + getPasadoZona().getIdentificador()+ "\n"
							+ "Tiempo de entrenamiento: " + getTiempo() + "\n"
							+ "Estimación de espera (sin incluirse a sí mismo): \n"
							+ "  Zona1(Cardio)= " + zonas[0].obtenerTiempoRestanteZona() + ",\n" 
							+ "  Zona2(fuerza)= " + zonas[1].obtenerTiempoRestanteZona() + ",\n" 
							+ "  Zona3(Funcional)= " + zonas[2].obtenerTiempoRestanteZona()+ ",\n"
							+ "  Zona4(estiramientos)= " + zonas[3].obtenerTiempoRestanteZona()+ "\n"
							+ "Espera en bicicleta premium(si aplica):\n"
							+ "--------------------------------------------------------------\n");
	}

*/	


	public void run() {
		
		
		try {
			// PARTE 1, PASAR POR EL TORNO
			pasadoTorno.intentarEntrar(this);
			Thread.sleep(tiempo);
			// PARTE 2 SALIR POR EL TORNO
			pasadoTorno.salir(this);
			
			
			// PARTE 3 ENTRAR EN LA ZONA, USAR TAMBIEN BICICLETA SI ES EL CASO
			zona.entrar(this);
			if(usaBicicleta) {
				zona.getMaquinaBicicleta().usarBicicleta(this);
			}
			Thread.sleep(tiempo);
			//PARTE 4 SALIR DE LA ZONA, SALIR TAMBIEN DE LA BICICLETA
			if(usaBicicleta) {
				zona.getMaquinaBicicleta().dejarBicicleta(this);
			}
			zona.salir(this);
			//Stalker referencia?

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
		/*
		

	
	*/
}
