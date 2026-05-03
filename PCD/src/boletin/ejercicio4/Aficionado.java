package boletin.ejercicio4;

import messagepassing.MailBox;

import java.util.Random;


public class Aficionado extends Thread {
	 
    private static final int REPETICIONES  = 5;
    private static final int MAX_ACCION_MS = 2000; 
    private final String id;
    private final int indice;          
    private final MailBox solicitudControl;
    private final MailBox miRespuesta;
    private final MailBox tornoR;
    private final MailBox tornoL;
    private final MailBox mutexPantalla;
    private final Random random = new Random();
 
    public Aficionado(String id, int indice,
                      MailBox solicitudControl, MailBox miRespuesta,
                      MailBox tornoR, MailBox tornoL,
                      MailBox mutexPantalla) {
        this.id               = id;
        this.indice           = indice;
        this.solicitudControl = solicitudControl;
        this.miRespuesta      = miRespuesta;
        this.tornoR           = tornoR;
        this.tornoL           = tornoL;
        this.mutexPantalla    = mutexPantalla;
    }
 
    @Override
    public void run() {
 
        for (int vuelta = 0; vuelta < REPETICIONES; vuelta++) {

            try {
                Thread.sleep(random.nextInt(MAX_ACCION_MS) + 1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
 
            int tiempoEstimado = random.nextInt(10) + 1;
            Solicitud sol = new Solicitud(id, indice, tiempoEstimado);
            solicitudControl.send(sol);
            String colaAsignada = (String) miRespuesta.receive();
 
            MailBox tornoAsignado = colaAsignada.equals("R") ? tornoR : tornoL;

            tornoAsignado.receive();
            try {
                Thread.sleep(tiempoEstimado * 100L); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            mutexPantalla.receive();
 
            System.out.println("----------------------------------------------------");
            System.out.println("Aficionado " + id + " ha usado la cola " + colaAsignada);
            System.out.println("Tiempo de validación = " + tiempoEstimado);
            System.out.println("Thread.sleep(" + tiempoEstimado + ")");
            System.out.println("Aficionado " + id + " liberando la cola " + colaAsignada);
            System.out.println("----------------------------------------------------");
 
            mutexPantalla.send("token");
            tornoAsignado.send("token");
        }
    }
}