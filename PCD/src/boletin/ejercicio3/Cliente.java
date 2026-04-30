package boletin.ejercicio3;

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
		return tiempo;
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

}
