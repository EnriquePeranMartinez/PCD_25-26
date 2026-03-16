package guion3.ejercicio4;

public class MonitorPalabra {
    private int turno = 0; // 0:hola, 1:amigos, 2:del, 3:mundo
    private final int totalHilos = 4;

    public synchronized void pedirTurno(int id, String palabra) {
        try {
            // Mientras no sea mi turno, me duermo
            while (turno != id) {
                wait();
            }

            // Es mi turno: imprimo la palabra
            System.out.print(palabra);

            // Cambio el turno al siguiente (0->1->2->3->0...)
            turno = (turno + 1) % totalHilos;

            // Despierto a todos los hilos para que el siguiente compruebe su turno
            notifyAll();
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}