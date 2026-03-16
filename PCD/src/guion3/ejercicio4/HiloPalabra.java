package guion3.ejercicio4;

public class HiloPalabra extends Thread {
    private final int id;
    private final String palabra;
    private final MonitorPalabra monitor;

    public HiloPalabra(int id, String palabra, MonitorPalabra monitor) {
        this.id = id;
        this.palabra = palabra;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            monitor.pedirTurno(id, palabra);
        }
    }
}