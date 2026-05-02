package boletin.ejercicio2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import boletin.Panel;
import boletin.ejercicio1.Matriz;

  
public class Equipo extends Thread {
  private final String idEquipo;
  private final List<Matriz> atletas = new ArrayList<>();
  private final int idPanel;
  public final static int TAMANO_MATRIZ_EJERCICIO_2 = 5;
  private Matriz R;

  public Equipo(String id) {
	    this.idEquipo = id;

  }
  public Equipo(String id,int idP){
    this.idEquipo = id;
    this.idPanel = idP;
  }
  
  @Override
  public void run(){
    for(int i = 0; i < 3; i++){
    	
    		
          Matriz[] matricesAtletas = (Matriz[]) IntStream.range(0, 4).
        		  				mapToObj(matriz -> new Matriz()).
        		  				toArray(Matriz[]::new);
          R = Matriz.calcularMatrizResumen(matricesAtletas);
    

		    try {
		    	CentroDeportivo.semaforoGeneral.acquire();
		      
		      
		      for(int p = 0; p < CentroDeportivo.NUM_PANELES; p++) {
		    	  if(CentroDeportivo.semBinario[p].tryAcquire()) {
		    		  String msgInicio = "Usando panel " + p + " el hilo " + idEquipo;
		    		  CentroDeportivo.paneles.get(p).escribir_mensaje(msgInicio);
		    		  
		    		  CentroDeportivo.paneles.get(p).escribir_mensaje("Matriz R: " + R.toString());
		    		  
		    		  String msgFin = "Terminando de usar panel " + p + " el hilo " + idEquipo;
		    		  CentroDeportivo.paneles.get(p).escribir_mensaje(msgFin);
		    	  }
		      }
		      paneles.get(idPanel).mostrarR(R,idPanel,idEquipo);
		      
		      signal(mutex[i]);
		      signal(semaforo_general);
		    } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
    	
    	} 
    }
  }

}
