package boletin.ejercicio4;
 
import java.io.Serializable;
 

public class Solicitud implements Serializable {
 
    private static final long serialVersionUID = 1L;
 
    private final String idAficionado;
     private final int indice;
    private final int tiempoEstimado;
 
    public Solicitud(String idAficionado, int indice, int tiempoEstimado) {
        this.idAficionado = idAficionado;
        this.indice        = indice;
        this.tiempoEstimado = tiempoEstimado;
    }
 
    public String getIdAficionado()  { return idAficionado; }
    public int    getIndice()        { return indice; }
    public int    getTiempoEstimado(){ return tiempoEstimado; }
}
