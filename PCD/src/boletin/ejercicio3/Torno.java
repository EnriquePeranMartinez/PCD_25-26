package boletin.ejercicio3;

import java.util.ArrayList;
import java.util.List;

public class Torno {
	// primer monitor
	private List<Cliente> clientesCola;
	private Cliente clienteEnElTorno;
	
	
	public Torno() {
		clientesCola = new ArrayList<Cliente>();
		clienteEnElTorno = null;
	}

	public synchronized int getTiempoEspera() {
		return clientesCola.stream()
				.mapToInt(c -> c.getTiempo())
				.sum() 
				+ 
				clienteEnElTorno.getTiempo();
	}
	
	
	public synchronized void pasar(Cliente cliente) {
		// Cuando el cliente pasa por el torno
		clientesCola.add(cliente);
		
		try {
			clienteEnElTorno = cliente;
			Thread.sleep(cliente.getTiempo());
			//cliente.setPasadoTorno(true);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// Al salir del torno, se debe liberar el puntero del atributo 
			clienteEnElTorno = null; 
		}
		
			
	}
	
	public synchronized boolean estaLibre() {
		// Si no hay ningún cliente en el torno, se devolverá nulo
		return clienteEnElTorno == null;
	}
	
	
}









