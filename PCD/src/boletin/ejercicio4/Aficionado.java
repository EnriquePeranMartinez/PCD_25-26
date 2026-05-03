package boletin.ejercicio4;

//Recuerda meter en el proyecto si no está, la biblioteca que se descarga de clase messagepassing
import messagepassing.MailBox;

import java.util.Random;

import messagepassing.CommunicationScheme;

public class Aficionado extends Thread {
	 
    // ── Constantes ────────────────────────────────────────────────────────────
    private static final int REPETICIONES  = 5;
    private static final int MAX_ACCION_MS = 2000; // tiempo máximo de la acción previa
 
    // ── Identificación ────────────────────────────────────────────────────────
    private final String id;
    private final int    indice;          // posición en el array de buzones de respuesta
 
    // ── Buzones compartidos ───────────────────────────────────────────────────
    /** Buzón donde se envían las solicitudes al controlador de accesos. */
    private final MailBox solicitudControl;
 
    /** Buzón personal de este aficionado: el controlador le responde aquí con "R" o "L". */
    private final MailBox miRespuesta;
 
    /** Torno R (rápido): token de capacidad 1 que actúa como semáforo binario. */
    private final MailBox tornoR;
 
    /** Torno L (lento): token de capacidad 1 que actúa como semáforo binario. */
    private final MailBox tornoL;
 
    /**
     * Mutex de pantalla: token de capacidad 1 para garantizar que ningún aficionado
     * imprime simultáneamente con otro (exclusión mutua sobre la pantalla).
     */
    private final MailBox mutexPantalla;
 
    // ── Utilidad aleatoria ────────────────────────────────────────────────────
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
 
            // ── 1. ACCIÓN PREVIA ──────────────────────────────────────────────
            // Simula que el aficionado camina hacia los baños / zona de comida.
            try {
                Thread.sleep(random.nextInt(MAX_ACCION_MS) + 1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
 
            // Generar tiempo estimado de validación para esta iteración (1-10)
            int tiempoEstimado = random.nextInt(10) + 1;
 
            // ── 2. SOLICITAR COLA AL CONTROLADOR ─────────────────────────────
            // Enviamos una Solicitud (Serializable) con nuestro id, índice y tiempo.
            Solicitud sol = new Solicitud(id, indice, tiempoEstimado);
            solicitudControl.send(sol);
 
            // ── 3. RECIBIR COLA ASIGNADA ──────────────────────────────────────
            // El controlador nos responde en nuestro buzón personal con "R" o "L".
            String colaAsignada = (String) miRespuesta.receive();
 
            // Seleccionamos el buzón-torno correspondiente
            MailBox tornoAsignado = colaAsignada.equals("R") ? tornoR : tornoL;
 
            // ── 4. ADQUIRIR EL TORNO (esperar turno) ─────────────────────────
            // Hacemos receive del token: si el torno está ocupado nos bloqueamos aquí.
            tornoAsignado.receive();
 
            // ── 5. VALIDAR LA ENTRADA (tiempo de validación) ──────────────────
            try {
                Thread.sleep(tiempoEstimado * 100L); // *100 para que no dure demasiado
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
 
            // ── 6. IMPRIMIR EN EXCLUSIÓN MUTUA ────────────────────────────────
            // Adquirimos el mutex de pantalla para evitar salida entremezclada.
            mutexPantalla.receive();
 
            System.out.println("----------------------------------------------------");
            System.out.println("Aficionado " + id + " ha usado la cola " + colaAsignada);
            System.out.println("Tiempo de validación = " + tiempoEstimado);
            System.out.println("Thread.sleep(" + tiempoEstimado + ")");
            System.out.println("Aficionado " + id + " liberando la cola " + colaAsignada);
            System.out.println("----------------------------------------------------");
 
            // Liberamos el mutex de pantalla
            mutexPantalla.send("token");
 
            // ── 7. LIBERAR EL TORNO ───────────────────────────────────────────
            // Devolvemos el token al torno para que otro aficionado pueda usarlo.
            tornoAsignado.send("token");
        }
    }
}