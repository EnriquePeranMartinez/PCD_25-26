package guion1;

public class HiloConHerencia extends Thread{

	private String texto; 
	
	public HiloConHerencia(String _texto) { 
		texto = _texto; 
	} 
	
	public void run() { 
		System.out.println(texto);
		
	} 
}
