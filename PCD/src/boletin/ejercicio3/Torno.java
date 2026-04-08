package boletin.ejercicio3;


public class Torno {
	// primer monitor
	private boolean ocupado = false;
	private final int identificador;
	
	
	
	
	public Torno(int _id) {
		identificador = _id;
	}

	
	public int getIdentificador() {
		return identificador;
	}

	
	// Si no está ocupado le dejamos entrar y lo ocupamos
	public synchronized boolean intentarEntrar() {
		if (!ocupado) {
			ocupado = true;
			return true;
		}
		return false;
	}
	
	
	public synchronized void hacerCola() throws InterruptedException {
		while (ocupado) {
			wait();	 // Nos esperamos hasta que esté libre
		}
		ocupado = true;
	}
	
	public synchronized void salir() {
		ocupado = false; // Desocupamos el torno
		notify(); // Le decimos que pase el siguiente
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
}









