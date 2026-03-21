package boletin.ejercicio3;

public class Gimnasio {
	
	private static final int NUMERO_CLIENTES = 2;
	
	public static Torno[] tornos;
	private static Zona[] zonas;
	private static Cliente[] clientes;
	
	
	public static void inicializar() {
		
		tornos = new Torno[3];
	    zonas = new Zona[4];
	    clientes = new Cliente[NUMERO_CLIENTES];
		
		for (int i = 0; i < 3; i++) {
			tornos[i] = new Torno();
		}
		
		for(int i = 0; i < 4; i++) {	
			zonas[i] = new Zona();
		}
		
		for(int i = 0; i < NUMERO_CLIENTES; i++){
			clientes[i] = new Cliente(tornos, zonas);
		}
	}

	
	public static void main(String[] args) {
		
		inicializar();
		for(int i = 0; i < NUMERO_CLIENTES; i++){
			clientes[i].start();
		}
	
	}
}
