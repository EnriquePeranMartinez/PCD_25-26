package boletin.ejercicio3;

public class Gimnasio {
	
	public static final int NUMERO_CLIENTES = 50;
	public static final int NUMERO_TORNOS = 3;
	public static final int NUMERO_ZONAS = 4;
	
	public static Torno[] tornos;
	private static Zona[] zonas;
	private static Cliente[] clientes;
	
	
	public static void inicializar() {
		
		tornos = new Torno[NUMERO_TORNOS];
	    zonas = new Zona[NUMERO_ZONAS];
	    clientes = new Cliente[NUMERO_CLIENTES];
		
		for (int i = 0; i < NUMERO_TORNOS; i++) {
			tornos[i] = new Torno(i);
		}
		
		for(int i = 0; i < NUMERO_ZONAS; i++) {	
			zonas[i] = new Zona(i);
		}
		
		for(int i = 0; i < NUMERO_CLIENTES; i++){
			clientes[i] = new Cliente(tornos, zonas, "c"+i);
		}
	}

	public static void main(String[] args) {
		
		inicializar();
		for(int i = 0; i < NUMERO_CLIENTES; i++){
			clientes[i].start();
		}
	
	}
}
