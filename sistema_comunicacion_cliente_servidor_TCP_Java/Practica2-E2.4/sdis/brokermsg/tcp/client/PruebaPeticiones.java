/**
 * @author Angel Antolin Caramazana, Alejandro Garcia Lanvadera, Manuel Arribas Martinez, Marcos Gomez Medina
 */
package sdis.brokermsg.tcp.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import sdis.brokermsg.tcp.common.MalMensajeProtocoloException;
import sdis.brokermsg.tcp.common.MensajeProtocolo;
import sdis.brokermsg.tcp.common.Primitiva;


//Realiza una peticion de la primitiva indicada al servidor
//Permite no tener que repetir este codigo en todos los clientes
public class PruebaPeticiones {

    public static void prueba(MensajeProtocolo mensaje, ObjectInputStream entradaServidor, ObjectOutputStream enviaServidor)
            throws java.io.IOException, MalMensajeProtocoloException,ClassNotFoundException {

        //Depuracion de lo que se ha enviado
        if(mensaje.getPrimitiva() == Primitiva.ADDMSG || mensaje.getPrimitiva() == Primitiva.READQ || mensaje.getPrimitiva() == Primitiva.DELETEQ){
            System.out.println("[PRIMITIVA]" + mensaje.getPrimitiva() + ": "  + mensaje);
        }else{
            System.out.println("[PRIMITIVA]" + mensaje);
        }
        // Enviamos el mensaje al servidor para que lo procese
        enviaServidor.writeObject(mensaje);

        //Depuracion de lo que se ha recibido
        System.out.println("< " + (MensajeProtocolo)entradaServidor.readObject());

        //Continua al pulsar enter
        System.out.println("Pulsa <Enter> "); System.in.read();
    }
}
