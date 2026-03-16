package guion3;

class Consumidor extends Thread { 
	 
    private MonitorBuffer buffer; 
 
    public Consumidor(MonitorBuffer b) { 
        buffer = b; 
    } 
 
    public void run() { 
        for (int i = 1; i < 100; i++) { 
 
            // extraer item 
            int item = buffer.extraer(); 
 
            // consumir item 
            try { 
                Thread.sleep((int) Math.random() * 10); 
            } catch (InterruptedException e) { 
                e.printStackTrace(); 
            } 
        } 
    } 
} 
 
