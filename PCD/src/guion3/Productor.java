package guion3;

class Productor extends Thread { 
	 
    private MonitorBuffer buffer; 
 
    public Productor(MonitorBuffer b) { 
        buffer = b; 
    } 
 
    public void run() { 
        for (int i = 1; i < 100; i++) { 
 
            // producir elemento 
            try { 
                Thread.sleep((int) Math.random() * 10); 
            } catch (InterruptedException e) { 
                e.printStackTrace(); 
            } 
 
            int item = (int) (Math.random() * 10); 
 
            // depositar item 
            buffer.depositar(item); 
        } 
    } 
}