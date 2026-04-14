package guion5;

import messagepassing.Channel;


public class ProductorConsumidorBuffer {
	public static void main(String[] args) {
		
		Channel pb = new Channel();
		Channel bc = new Channel();
		Buffer b = new Buffer(pb, bc, 10);
		Productor p = new Productor(pb);
		Consumidor c = new Consumidor(bc);
		Thread h1 = new Thread(p);
		Thread h2 = new Thread(c);
		Thread h3 = new Thread(b);
		h1.start();
		h2.start();
		h3.start();
		try {
			h1.join();
			h2.join();
			h3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

