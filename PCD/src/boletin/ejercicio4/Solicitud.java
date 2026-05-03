package boletin.ejercicio4;
 
import java.io.Serializable;
 
/**
 * Objeto de solicitud que cada aficionado envía al controlador de accesos.
 * Debe implementar Serializable para poder ser enviado a través de un MailBox.
 */
public class Solicitud implements Serializable {
 
    private static final long serialVersionUID = 1L;
 
    /** Identificador textual del aficionado (p.ej. "a0", "a1", ...) */
    private final String idAficionado;
 
    /** Índice numérico del aficionado (0-49), usado para localizar su buzón de respuesta */
    private final int indice;
 
    /** Tiempo estimado de validación en el torno (entre 1 y 10) */
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
