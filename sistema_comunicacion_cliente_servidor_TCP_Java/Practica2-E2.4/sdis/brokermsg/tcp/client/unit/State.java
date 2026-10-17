/**
 * @author Angel Antolin Caramazana, Alejandro Garcia Lanvadera, Manuel Arribas Martinez, Marcos Gomez Medina
 */
package sdis.brokermsg.tcp.client.unit;

import java.io.IOException;

import sdis.brokermsg.tcp.client.PruebaPeticiones;
import sdis.brokermsg.tcp.common.MalMensajeProtocoloException;
import sdis.brokermsg.tcp.common.MensajeProtocolo;
import sdis.brokermsg.tcp.common.Primitiva;

/*
* SOLO ADMIN, prueba la primitiva STATE
*/
public class State {
    private static final int PUERTO = 2000;
    private static final String HOST = "localhost";
    static java.io.ObjectInputStream in = null;
    static java.io.ObjectOutputStream out = null;

    public static void main(String[] args) throws IOException {
        if(args.length != 2){
            System.out.println("[SOLO ADMIN]\nUso:\njava State <usuario> <password>");
            System.exit(-1);
        }

        String usuario = args[0];
        String passwd = args[1];

        //Conectamos con el server
        java.net.Socket sock = new java.net.Socket(HOST, PUERTO);

        try {
            // Recogemos el buffer de entrada y salida
            out = new java.io.ObjectOutputStream(sock.getOutputStream());
            in = new java.io.ObjectInputStream(sock.getInputStream());

            // Leemos la bienvenida del servidor y la mostramos por pantalla
            MensajeProtocolo info = (MensajeProtocolo) in.readObject();
            System.out.println(info);

            // Forzamos el inicio de sesion y realizamos la primitiva
            PruebaPeticiones.prueba(new MensajeProtocolo(Primitiva.XAUTH, usuario, passwd), in, out);

            PruebaPeticiones.prueba(new MensajeProtocolo(Primitiva.STATE), in, out);

        //A continuacion catcheamos los posibles errores
        } catch(IOException | ClassNotFoundException | MalMensajeProtocoloException e){
            System.err.println("Error en State: " + e.getMessage());
        }
    }
}
