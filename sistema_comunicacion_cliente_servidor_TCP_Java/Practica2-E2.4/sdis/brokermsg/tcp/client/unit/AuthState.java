/**
 * @author Angel Antolin Caramazana, Alejandro Garcia Lanvadera, Manuel Arribas Martinez, Marcos Gomez Medina
 */
package sdis.brokermsg.tcp.client.unit;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import sdis.brokermsg.tcp.common.MalMensajeProtocoloException;
import sdis.brokermsg.tcp.common.MensajeProtocolo;
import sdis.brokermsg.tcp.common.Primitiva;

//Prueba el inicio de sesion en conjunto con la primitiva STATE

public class AuthState {
    private static final int PUERTO = 2000;
    private static final String HOST = "localhost";
    static java.io.ObjectInputStream in = null;
    static java.io.ObjectOutputStream out = null;

    public static void main(String[] args) throws IOException {
        if(args.length != 2){
            System.out.println("[SOLO ADMIND]\nUso:\njava State <usuario> <password>");
            return;
        }

        String user = args[0];
        String passwd = args[1];

        java.net.Socket sock = new java.net.Socket(HOST, PUERTO);
        out = new ObjectOutputStream(sock.getOutputStream());
        in = new ObjectInputStream(sock.getInputStream());

        try {
            // Leemos mensaje de bienvenida
            in.readObject();

            // Enviamos las credenciales al servidor
            out.writeObject(new MensajeProtocolo(Primitiva.XAUTH, user, passwd));
            out.flush();

            // Comprobamos si el inicio de sesion ha sido exitoso
            MensajeProtocolo authResp = (MensajeProtocolo) in.readObject();
            if(authResp.getPrimitiva() != Primitiva.XAUTH){
                System.out.println("[Servidor] Autenticacion fallida: " + authResp.getMensaje());
                return;
            }

            // Enviamos la solicitud para el state
            out.writeObject(new MensajeProtocolo(Primitiva.STATE, user));
            out.flush();
            MensajeProtocolo statResp = (MensajeProtocolo) in.readObject();

            // Mostramos por pantalla la respuesta
            System.out.println("[Servidor] State: " + statResp.getMensaje());


        } catch(IOException | ClassNotFoundException | MalMensajeProtocoloException e){
            System.err.println("Error en State: " + e.getMessage());
        }
    }
}
