package guion3.ejercicio5;


public class HiloPalabraSync extends Thread {
    private final int id;
    private final String palabra;
    private final MonitorPalabraSync monitor;

    public HiloPalabraSync(int id, String palabra, MonitorPalabraSync monitor) {
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
