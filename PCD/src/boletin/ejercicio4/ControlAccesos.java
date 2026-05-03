package boletin.ejercicio4;

import messagepassing.MailBox;


public class ControlAccesos extends Thread {
	 

    private static final int NUMERO_AFICIONADOS = 50;
    private static final int REPETICIONES = 5;
    private static final int UMBRAL_RAPIDO = 5;
    
    private final MailBox solicitudControl;
    private final MailBox[] respuestas;
 
    public ControlAccesos(MailBox solicitudControl, MailBox[] respuestas) {
        this.solicitudControl = solicitudControl;
        this.respuestas       = respuestas;
    }
 
    @Override
    public void run() {
 
        int totalSolicitudes = NUMERO_AFICIONADOS * REPETICIONES;
 
        for (int i = 0; i < totalSolicitudes; i++) {
 
            Solicitud sol = (Solicitud) solicitudControl.receive();
            String colaAsignada = (sol.getTiempoEstimado() <= UMBRAL_RAPIDO) ? "R" : "L";
            respuestas[sol.getIndice()].send(colaAsignada);
        }
    }
}