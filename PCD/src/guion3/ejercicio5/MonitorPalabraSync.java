package guion3.ejercicio5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MonitorPalabraSync {
    private int turno = 0; // 0:hola, 1:amigos, 2:del, 3:mundo
    private final int totalHilos = 4;
    private ReentrantLock l = new ReentrantLock();
    private Condition[] condiciones = new Condition[4];

    
    public MonitorPalabraSync() {
    	for (int i = 0; i < 4; i++) {
            condiciones[i] = l.newCondition();
        }
    }

    public void pedirTurno(int id, String palabra) {
    	l.lock(); 	// Entramos en la sección crítica
        try {
            // Mientras no sea mi turno, me duermo con mi condición
            while (turno != id) {
                condiciones[id].await();
            }

            // Es mi turno: imprimo la palabra
            System.out.print(palabra);

            // Cambio el turno al siguiente (0->1->2->3->0...)
            turno = (turno + 1) % totalHilos;

            // Despierto al siguiente hilo que le toca
            condiciones[turno].signal();;
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        	l.unlock(); 		// Desbloqueamos el cerrojo
        }
    }
}
