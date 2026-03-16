package guion3;

public class MonitorBuffer { 
	 
    private int[] buffer; 
    private int in, out, tam; 
 
    public MonitorBuffer(int ele) { 
        buffer = new int[ele]; 
        for (int i = 0; i < buffer.length; i++) { 
            buffer[i] = 0; 
        } 
        in = out = tam = 0; 
    } 
 
    public synchronized void depositar(int ele) { 
        while (tam >= buffer.length) { 
            try { 
                wait(); 
            } catch (InterruptedException e) { 
                e.printStackTrace(); 
            } 
        } 
 
        buffer[in] = ele; 
        in = (in + 1) % buffer.length; 
        tam++; 
 
        System.out.println(this); 
        notifyAll(); 
    } 
 
    public synchronized int extraer() { 
        int ele = 0; 
 
        while (tam <= 0) { 
            try { 
                wait(); 
            } catch (InterruptedException e) { 
                e.printStackTrace(); 
            } 
        } 
 
        ele = buffer[out]; 
        buffer[out] = 0; 
        out = (out + 1) % buffer.length; 
        tam--; 
 
        System.out.println(this); 
        notifyAll(); 
 
        return ele; 
    } 
 
    public synchronized String toString() { 
        String texto = new String(); 
        for (int i = 0; i < buffer.length; i++) { 
            texto = texto.concat(String.valueOf(buffer[i]) + " "); 
        } 
        return texto; 
    } 
} 
 