package guion3.ejercicio5;

public class MainSync {

	public static void main(String[] args) {
        MonitorPalabraSync monitor = new MonitorPalabraSync();

        // Creamos los 4 hilos con sus IDs y palabras correspondientes
        Thread h1 = new HiloPalabraSync(0, "hola ", monitor);
        Thread h2 = new HiloPalabraSync(1, "amigos ", monitor);
        Thread h3 = new HiloPalabraSync(2, "del ", monitor);
        Thread h4 = new HiloPalabraSync(3, "mundo\n", monitor);

        // Los lanzamos todos
        h1.start();
        h2.start();
        h3.start();
        h4.start();
    }
}


