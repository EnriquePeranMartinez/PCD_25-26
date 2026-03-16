package guion3;

class Monitor1 { 
	 
    private volatile int compar; 
 
    public Monitor1(int val) { 
        compar = val; 
    } 
 
    public synchronized void decrementar(int cantidad) { 
        while (cantidad > compar) { 
            try { 
                wait(); 
            } catch (InterruptedException e) { 
                e.printStackTrace(); 
            } 
        } 
 
        compar -= cantidad; 
        System.out.println("variable=" + compar); 
    } 
 
    public synchronized void incrementar(int cantidad) { 
        compar += cantidad; 
        notifyAll(); // Desbloquear y continuar porque sigue haciendo cosas justo después, no sale
        System.out.println("variable=" + compar); 
    } 
} 
 

 
