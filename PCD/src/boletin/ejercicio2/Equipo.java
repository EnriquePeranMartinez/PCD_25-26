package boletin.ejercicio2;



import java.util.stream.IntStream;

import boletin.Panel;
import boletin.ejercicio1.Matriz;


public class Equipo extends Thread {
    private final String idEquipo;
    public final static int TAMANO_MATRIZ_EJERCICIO_2 = 5;

    public Equipo(String id) {
        this.idEquipo = id;
    }

    public String getIdEquipo() {
        return idEquipo;
    }

    public void run() {
        for (int i = 0; i < 3; i++) {
            
            Matriz[] matricesAtletas = IntStream.range(0, 4)
                    .mapToObj(atleta -> new Matriz(5, 5, true)) 
                    .toArray(Matriz[]::new);

            Matriz R = Matriz.calcularMatrizResumen(matricesAtletas);

            try {
                CentroDeportivo.semaforoGeneral.acquire();

                int panelUsado = -1;
                for (int p = 0; p < CentroDeportivo.NUM_PANELES; p++) {
                    if (CentroDeportivo.semBinario[p].tryAcquire()) {
                        panelUsado = p;
                        break;
                    }
                }

                if (panelUsado != -1) {
                    Panel panel = CentroDeportivo.paneles.get(panelUsado);
                    
                    panel.escribir_mensaje("Usando panel " + panelUsado + " el hilo " + idEquipo);
                    panel.escribir_mensaje("Matriz R (resumen del " + idEquipo + ": mejores marcas) = " + R.toString());
                    
                    
                    panel.escribir_mensaje("Terminando de usar panel " + panelUsado + " el hilo " + idEquipo + "\n");

                    CentroDeportivo.semBinario[panelUsado].release();
                }

                CentroDeportivo.semaforoGeneral.release();

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }
}

