package boletin.ejercicio2;

import boletin.ejercicio1.Matriz;
  
public class Equipo extends thread{
  private final String idEquipo;
  private final Array<Atleta> team;
  private final String idPanel;
  public final static TAMANO_MATRIZ_EJERCICIO_2 = 5;
  private Matriz R;

  public Equipo(String id){
    this.idEquipo = id;
  }

  public void run(){

    
    for(int i = 0; i < 3; i++){
          Matriz[4] matrices = IntStream.stream.mapToObj(i -> new Matriz()).toArray(Matriz::new);
          R = calcularMatrizResumen(matrices[0],matrices[1],matrices[2],matrices[3]);
    }

    try {
      wait(semaforo_general);
      wait(mutex[i])
      paneles[this.idPanel].mostrarR(R,idPanel,idEquipo);
      signal(mutex[i]);
      signal(semaforo_general);
    } 
  }


}
