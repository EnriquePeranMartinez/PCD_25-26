package boletin.ejercicio3;

public class Gimnasio {
	
	public static final int NUMERO_CLIENTES = 50;
	public static final int NUMERO_TORNOS = 3;
	public static final int NUMERO_ZONAS = 4;
	
	public static Torno monitorTorno;
	private static Zona monitorZona;
	private static Cliente[] clientes;
	
	
	public static void inicializar() {
		
		monitorTorno = new Torno();
		monitorZona = new Zona();
	    clientes = new Cliente[NUMERO_CLIENTES];
		
		for(int i = 0; i < NUMERO_CLIENTES; i++){
			clientes[i] = new Cliente(monitorTorno, monitorZona, "c"+i);
		}
	}

	public static void main(String[] args) {
		
		inicializar();
		for(int i = 0; i < NUMERO_CLIENTES; i++){
			clientes[i].start();
		}
	
	}
}
