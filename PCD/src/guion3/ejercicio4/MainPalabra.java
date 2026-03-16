package guion3.ejercicio4;

public class MainPalabra {
    public static void main(String[] args) {
        MonitorPalabra monitor = new MonitorPalabra();

        // Creamos los 4 hilos con sus IDs y palabras correspondientes
        Thread h1 = new HiloPalabra(0, "hola ", monitor);
        Thread h2 = new HiloPalabra(1, "amigos ", monitor);
        Thread h3 = new HiloPalabra(2, "del ", monitor);
        Thread h4 = new HiloPalabra(3, "mundo\n", monitor);

        // Los lanzamos todos
        h1.start();
        h2.start();
        h3.start();
        h4.start();
    }
}