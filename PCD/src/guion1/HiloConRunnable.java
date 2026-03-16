package guion1;

public class HiloConRunnable implements Runnable{

	private String texto; 
	
	public HiloConRunnable(String _texto) { 
		texto = _texto; 
	} 
	
	public void run() { 
		System.out.println(texto);
	} 
}
