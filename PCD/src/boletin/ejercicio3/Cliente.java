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
	
	public Cliente(Zona z, String _id, Torno _pasadoTorno) {
		super();
		this.tiempo = generarTiempo();
		zona = z;
		identificador = _id;
		pasadoTorno = _pasadoTorno;
		pasadoZona = null;
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
	
	public void setZonaEscogida(int _zona) {
		zonaEscogida = _zona;
	}
	public int getIndiceTorno() {return indiceTorno;}
	public void setIndiceTorno(int _torno) {indiceTorno = _torno;}
/*
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
		
		// PARTE 1, PASAR POR EL TORNO
		
		pasadoTorno.intentarEntrar(this);
		wait(tiempo);
		pasadoTorno.salir(this);
		
		// PARTE 2 SALIR POR EL TORNO
		
		
		// PARTE 3 ENTRAR EN LA ZONA
		zona.entrar(this);
		wait(tiempo);
		
		//PARTE 4 SALIR DE LA ZONA
		zona.salir(this);
		//Stalker referencia?
		
		
	}
		/*
		
		
		// PARTE 1: TORNOS
		boolean haEntrado = false;
		// Ver si hay algún torno libre y meterse
		for (Torno torno : tornos) {
			if(torno.intentarEntrar()) {
				//System.out.println("DEBUG: CLIENTE " + this.identificador + " TORNO ELECTO: " + torno.getIdentificador());
				pasadoTorno = torno;
				haEntrado = true;
				//System.out.println("Soy "+ identificador + " he entrado en el torno :)");
				break;
			}
		}
		
		// Si no había libres, meterse en uno random y hacer cola
		if (!haEntrado) {
			int tornoRandom = new Random().nextInt(tornos.length);
			pasadoTorno = tornos[tornoRandom]; // Aquí entiendo que da igual si cae en el mismo, es aleatorio
			try {
				pasadoTorno.hacerCola(); // Se pone a la cola del torno que le ha tocado
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		try {
			Thread.sleep(tiempo);
		} catch (InterruptedException e) {e.printStackTrace();}
		finally {
			pasadoTorno.salir(); // Cuando terminemos de usarlo, salimos del torno
		}
		
		//System.out.println("DEBUG: CLIENTE " + this.identificador + " TORNO ELECTO: " + this.pasadoTorno.getIdentificador());

		
		// PARTE 2: ZONAS/MÁQUINAS
		
		// Bucle en el que vemos las zonas que están libres y ocupadas, y guardamos las libres para luego utilizarlas
		int zonasOcupadas = 0;
		ArrayList<Zona> zonasLibres = new ArrayList<>();
		for (Zona zona : zonas) {
			if(zona.estaOcupada()) { 
				zonasOcupadas++;
			} else {
				zonasLibres.add(zona);
			}
			
		}
		
		// 3 CASOS del estado de las 4 zonas
		
		// CASO 1: Todas las zonas ocupadas
		
		if (zonasOcupadas == Gimnasio.NUMERO_ZONAS) { // Tengo que meterme en la zona con menor tiempo restante
			int mejorZona = comprobarZonaMenorTiempo();
			
			try {
				System.out.println("DEBUG (CASO 1): ZONA ELECTA: " + zonas[mejorZona].getIdentificador());
				zonas[mejorZona].entrar(this);
				Thread.sleep(tiempo);
				zonas[mejorZona].salir(this);
			} catch (InterruptedException e) {e.printStackTrace();}
			pasadoZona = zonas[mejorZona];
		}
		
		
		// CASO 2: Todas las zonas libres
		
		else if (zonasOcupadas == 0) { // Me meto en una zona random
			int zonaRandom = new Random().nextInt(zonas.length);
			try {
				System.out.println("DEBUG (CASO 2): ZONA ELECTA: " + zonas[zonaRandom].getIdentificador());
				zonas[zonaRandom].entrar(this);
				Thread.sleep(tiempo);
				zonas[zonaRandom].salir(this);
			} catch (InterruptedException e) {e.printStackTrace();}
			pasadoZona = zonas[zonaRandom];
		}
		
		// CASO 3: Algunas libres y otras ocupadas
		
		else {	// Me meto en una random de las que están libres
			// genera un entero hacerle el módulo con el tamaño del array de zonasLibres y ya estaría :)
			int zonaLibRandom = new Random().nextInt(zonasLibres.size());
			try {
				System.out.println("DEBUG (CASO 3): ZONA ELECTA: " + zonasLibres.get(zonaLibRandom).getIdentificador());
				zonasLibres.get(zonaLibRandom).entrar(this);
				Thread.sleep(tiempo);
				zonasLibres.get(zonaLibRandom).salir(this);
			} catch (InterruptedException e) {e.printStackTrace();}
			pasadoZona = zonas[zonaLibRandom];

		}
		
		imprimirResult();
	}
	
	*/
}
