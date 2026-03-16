package boletin.ejercicio3;

public class Torno {
	// primer monitor
	
	//private Condition condicion = new Condition();
	
	
	public synchronized void pasar(Cliente cliente) {
		// Cuando el cliente pasa por el torno
		try {
			Thread.sleep(cliente.getTiempo());
			cliente.setPasadoTorno(true);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
		
	}
	
	public boolean hayColaLibre() {
		return false;
	}
	
	
}









