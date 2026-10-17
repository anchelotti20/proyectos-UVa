/**
 * @author Angel Antolin Caramazana, Alejandro Garcia Lanvadera, Manuel Arribas Martinez, Marcos Gomez Medina
 */
package sdis.brokermsg.tcp.common;

import java.io.Serializable;

//Permite serializar los mensajes enviados entre Cliente y Servidor

public class MensajeProtocolo implements Serializable {
    private final Primitiva primitiva;  // Primitiva enviada
    private final String mensaje;       // Mensaje contenido
    private final String idCola;        // Cola objetivo del mensaje


    /* CONSTRUCTOR: ADDED, DELETED, EMTPY Y STATE*/
    public MensajeProtocolo(Primitiva p) throws MalMensajeProtocoloException {
        if (p == Primitiva.ADDED || p == Primitiva.DELETED || p == Primitiva.EMPTY || p == Primitiva.STATE) {
            this.primitiva = p;
            this.mensaje = null;
            this.idCola = null;

        } else
            throw new MalMensajeProtocoloException();
    }

    /* CONSTRUCTOR: XAUTH, READQ, DELETEQ MSG, NOTAUTH, ERROR, INFO, STATE, BADCODE*/
    public MensajeProtocolo(Primitiva p, String mensaje) throws MalMensajeProtocoloException {
        if (p == Primitiva.INFO || p == Primitiva.XAUTH || p == Primitiva.MSG || p == Primitiva.NOTAUTH || p == Primitiva.ERROR || p == Primitiva.STATE || p == Primitiva.BADCODE) {
            this.primitiva = p;
            this.mensaje = mensaje;
            this.idCola = null;

        }else if(p == Primitiva.READQ || p == Primitiva.DELETEQ){
            this.primitiva = p;
            this.mensaje = null;
            this.idCola = mensaje;

        }else{

            throw new MalMensajeProtocoloException();
        }
    }

    /* CONSTRUCTOR: XAUTH, ADDMSG*/
    //En caso de XAUTH, idCola = username, mensaje = password
    public MensajeProtocolo(Primitiva p, String idCola, String mensaje) throws MalMensajeProtocoloException {
        if (p == Primitiva.XAUTH || p == Primitiva.ADDMSG) {
            this.primitiva = p;
            this.mensaje = mensaje;
            this.idCola = idCola;
        } else
            throw new MalMensajeProtocoloException();
    }

    // GETTERS
    public Primitiva getPrimitiva() { return this.primitiva; }
    public String getMensaje() { return this.mensaje; }
    public String getIdCola() { return this.idCola; }

    public String toString() { /* prettyPrinter de la clase */
        switch(this.primitiva) {
            case INFO:
            case XAUTH:
            case NOTAUTH:
            case ERROR:
            case MSG:
            case STATE:
            case BADCODE:
                return this.primitiva.toString() + ": " + this.mensaje;

            case ADDMSG:
                return "[Cola: " + this.idCola + "]" + "[Mensaje: " + this.mensaje + "]";

            case READQ:
                return "[Cola: " + this.idCola + " ]";

            case DELETEQ:
                return "[Cola: " +this.idCola + " eliminada]";

            default:
                return this.primitiva.toString();
        }
    }
}
