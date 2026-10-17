/**
 * @author Angel Antolin Caramazana, Alejandro Garcia Lanvadera, Manuel Arribas Martinez, Marcos Gomez Medina
 */
package sdis.brokermsg.tcp.client.unit;

import sdis.brokermsg.tcp.common.MalMensajeProtocoloException;
import sdis.brokermsg.tcp.common.MensajeProtocolo;
import sdis.brokermsg.tcp.common.Primitiva;

//Prueba tanto el inicio de sesion como AddMsg
public class AuthAddMsg{
    final private static int PUERTO = 2000;
    final private static String HOST = "localhost";
    static java.io.ObjectInputStream in = null;
    static java.io.ObjectOutputStream out = null;

    public static void main(String[] args) throws java.io.IOException {
        if(args.length != 3) {
            System.out.println("Use:\njava AddMsg <usuario> <password> <mensaje>");
            System.exit(-1);
        }

        String user = args[0];
        String passwd = args[1];
        String msg = args[2];

        // Establecemos la conexion con el servidor
        java.net.Socket socket = new java.net.Socket(HOST, 2000);

        // Codigo de AuthAddMsg
        try {
            // Buffers de E/S del cliente
            out = new java.io.ObjectOutputStream(socket.getOutputStream());
            in = new java.io.ObjectInputStream(socket.getInputStream());

            // Leemos el mensaje de bienvenida
            MensajeProtocolo info = (MensajeProtocolo) in.readObject();
            System.out.println(info);

            // Realizamos el inicio de sesion
            out.writeObject(new MensajeProtocolo(Primitiva.XAUTH, user, passwd));
            out.flush();
            MensajeProtocolo authResp = (MensajeProtocolo) in.readObject();

            // Validamos el inicio de sesion
            if (authResp.getPrimitiva() != Primitiva.XAUTH) {
                System.out.println("Autenticacion fallida: " + authResp.getMensaje());
                return;
            }

            // Enviamos el mensaje ADDMSG
            out.writeObject(new MensajeProtocolo(Primitiva.ADDMSG, user, passwd));
            out.flush();
            MensajeProtocolo respuesta = (MensajeProtocolo) in.readObject();

            // Mostramos por consola la respuesta por parte del servidor
            System.out.println("[Servidor] " + respuesta);

        }catch (java.io.EOFException e){
            System.err.println("Cliente: Fin de conexion.");

        }catch(java.io.IOException e){
            System.err.println("Cliente: Error de apertura E/S sobre objetos");

        }catch(MalMensajeProtocoloException e){
            System.err.println("Cliente: Error Mensaje Protocolo " + e);

        }catch(Exception e){
            System.err.println("Cliente: Exception. Cerrando Socket" + e);
        }
    }

}
