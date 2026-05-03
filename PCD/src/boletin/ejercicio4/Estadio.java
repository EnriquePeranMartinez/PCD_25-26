package boletin.ejercicio4;

import messagepassing.MailBox;

public class Estadio {
	
	
	public static void main(String[] args) {
		
		private static final int NUMERO_AFICIONADOS = 50;
		 
	    public static void main(String[] args) {
	 
	        // ── 1. CREAR BUZONES DE TORNOS (semáforos binarios) ──────────────────
	        // Capacidad 1 + token inicial → solo un aficionado usa el torno a la vez.
	        MailBox tornoR = new MailBox(1);
	        MailBox tornoL = new MailBox(1);
	        tornoR.send("token");
	        tornoL.send("token");
	 
	        // ── 2. MUTEX DE PANTALLA ──────────────────────────────────────────────
	        MailBox mutexPantalla = new MailBox(1);
	        mutexPantalla.send("token");
	 
	        // ── 3. BUZÓN COMPARTIDO DE SOLICITUDES ───────────────────────────────
	        // Ilimitado: todos los aficionados depositan aquí sus solicitudes.
	        MailBox solicitudControl = new MailBox();
	 
	        // ── 4. BUZONES PERSONALES DE RESPUESTA (uno por aficionado) ──────────
	        MailBox[] respuestas = new MailBox[NUMERO_AFICIONADOS];
	        for (int i = 0; i < NUMERO_AFICIONADOS; i++) {
	            respuestas[i] = new MailBox(1); // cada aficionado solo espera 1 respuesta a la vez
	        }
	 
	        // ── 5. CREAR Y ARRANCAR EL CONTROLADOR DE ACCESOS ────────────────────
	        ControlAccesos controlAccesos = new ControlAccesos(solicitudControl, respuestas);
	        controlAccesos.start();
	 
	        // ── 6. CREAR Y ARRANCAR LOS AFICIONADOS ──────────────────────────────
	        Aficionado[] aficionados = new Aficionado[NUMERO_AFICIONADOS];
	        for (int i = 0; i < NUMERO_AFICIONADOS; i++) {
	            aficionados[i] = new Aficionado(
	                    "a" + i,            // id textual
	                    i,                  // índice para el buzón de respuesta
	                    solicitudControl,   // buzón compartido de solicitudes
	                    respuestas[i],      // buzón personal de respuesta
	                    tornoR,
	                    tornoL,
	                    mutexPantalla
	            );
	            aficionados[i].start();
	        }
	 
	        // ── 7. ESPERAR A QUE TERMINEN TODOS ──────────────────────────────────
	        try {
	            for (Aficionado a : aficionados) {
	                a.join();
	            }
	            controlAccesos.join();
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	        }
	 
	        System.out.println("=== Simulación finalizada ===");
	    }
	
}
