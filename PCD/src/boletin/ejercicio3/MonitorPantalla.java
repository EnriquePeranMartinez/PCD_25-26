package boletin.ejercicio3;


import java.util.concurrent.locks.*;

public class MonitorPantalla {

	  private ReentrantLock pantalla = new ReentrantLock();
	  private Condition colaPantalla = pantalla.newCondition();
	  private boolean isPantallaOcupada;
	  
	  /*
		private void imprimirResult() {


		  
	  
	  public void escribirResultadoPorPantalla(Cliente c) {
			System.out.println("--------------------------------------------------------------\n" +
					"Cliente " + c.getIdentificador() + " ha pasado por el torno " +  c.getIndiceTorno() + "\n"
					+ "Tiempo en el torno: " + c.getTiempo() + "\n"
					+ "Zona elegida: " + c.getZonaEscogida()+ "\n"
					+ "Tiempo de entrenamiento: " + c.getTiempo() + "\n"
					+ "Estimación de espera (sin incluirse a sí mismo): \n"
					+ "  Zona1(Cardio)= " + zonas[0].obtenerTiempoRestanteZona() + ",\n" 
					+ "  Zona2(fuerza)= " + zonas[1].obtenerTiempoRestanteZona() + ",\n" 
					+ "  Zona3(Funcional)= " + zonas[2].obtenerTiempoRestanteZona()+ ",\n"
					+ "  Zona4(estiramientos)= " + zonas[3].obtenerTiempoRestanteZona()+ "\n"
					+ "Espera en bicicleta premium(si aplica):\n"
					+ "--------------------------------------------------------------\n");
}
	  }
	  
	  
	  public void salirPantalla() {
		  
	  }
	  */
}
