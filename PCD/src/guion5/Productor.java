package guion5;

//fichero Productor.java
import messagepassing.CommunicationScheme;


public class Productor implements Runnable {
	private CommunicationScheme canal;
	public Productor(CommunicationScheme c) {
		this.canal = c;
	}
	public void run() {
		int i = 0;
		while (true) {
			// producir
			i++;
			canal.send(new Integer(i));
			try {
				Thread.sleep(50 * i);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
}