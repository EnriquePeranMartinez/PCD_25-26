package boletin.ejercicio4;

//Recuerda meter en el proyecto si no está, la biblioteca que se descarga de clase messagepassing
import messagepassing.MailBox;


import messagepassing.CommunicationScheme;

public class ControlAccesos extends Thread {
	 
    // ── Constantes ────────────────────────────────────────────────────────────
    /** Número de aficionados gestionados. */
    private static final int NUMERO_AFICIONADOS = 50;
 
    /** Veces que cada aficionado solicita acceso. */
    private static final int REPETICIONES = 5;
 
    /** Umbral: tiempo ≤ UMBRAL → cola R; tiempo > UMBRAL → cola L. */
    private static final int UMBRAL_RAPIDO = 5;
 
    // ── Buzones ───────────────────────────────────────────────────────────────
    /** Buzón compartido: todos los aficionados envían sus solicitudes aquí. */
    private final MailBox solicitudControl;
 
    /**
     * Array de buzones de respuesta personal, uno por aficionado.
     * El controlador accede al buzón del aficionado mediante su índice.
     */
    private final MailBox[] respuestas;
 
    // ─────────────────────────────────────────────────────────────────────────
    /**
     * @param solicitudControl Buzón compartido donde los aficionados depositan solicitudes.
     * @param respuestas       Array con el buzón personal de respuesta de cada aficionado.
     */
    public ControlAccesos(MailBox solicitudControl, MailBox[] respuestas) {
        this.solicitudControl = solicitudControl;
        this.respuestas       = respuestas;
    }
 
    // ─────────────────────────────────────────────────────────────────────────
    @Override
    public void run() {
 
        int totalSolicitudes = NUMERO_AFICIONADOS * REPETICIONES;
 
        for (int i = 0; i < totalSolicitudes; i++) {
 
            // Recibir la siguiente solicitud de cualquier aficionado (bloqueante)
            Solicitud sol = (Solicitud) solicitudControl.receive();
 
            // Decidir cola según el tiempo estimado
            String colaAsignada = (sol.getTiempoEstimado() <= UMBRAL_RAPIDO) ? "R" : "L";
 
            // Responder al aficionado en su buzón personal
            respuestas[sol.getIndice()].send(colaAsignada);
        }
    }
}