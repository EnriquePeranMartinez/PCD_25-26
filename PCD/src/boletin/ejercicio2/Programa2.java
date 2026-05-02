package boletin.ejercicio2:


public class Programa {
  private ArrayList<Equipo> hilos = new ArrayList<>();
  public static void main(String[] args){
        //atributo semáforo general iniciado a 3;
        //atributo semBinario[3] iniciados a 1;
        //atributo lista<Panel> paneles;
      
        hilos = IntStream().range(0, 10).mapToObj(h -> new Hilo("Equipo " + h)).toArray(Hilo::new);
        hilos.stream().forEach(Hilo::start);

    
  }
}
