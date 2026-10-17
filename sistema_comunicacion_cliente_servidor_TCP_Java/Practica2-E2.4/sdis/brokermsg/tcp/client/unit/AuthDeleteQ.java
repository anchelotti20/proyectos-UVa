/**
 * @author Angel Antolin Caramazana, Alejandro Garcia Lanvadera, Manuel Arribas Martinez, Marcos Gomez Medina
 */
package sdis.brokermsg.tcp.client.unit;

import sdis.brokermsg.tcp.common.MalMensajeProtocoloException;
import sdis.brokermsg.tcp.common.MensajeProtocolo;
import sdis.brokermsg.tcp.common.Primitiva;



/**
 * Cliente unitario que se encarga de probar las primitas AUTH Y DELETEQ
 */
public class AuthDeleteQ {

    final private static int PUERTO = 2000;
    final private static String HOST = "localhost";
    static java.io.ObjectInputStream in = null;
    static java.io.ObjectOutputStream out = null;


    public static void main (String [] args) throws java.io.IOException, MalMensajeProtocoloException,ClassNotFoundException {

        if (args.length != 3) {
            System.out.println("Uso: \njava Auth <usuario> <password> <mensajeABorrar>");
            System.exit(-1);
        }

        String usuario=args[0];
        String password=args[1];
        String mensaje=args[2];

        // Realizamos la conexion con el servidor
        java.net.Socket sock = new java.net.Socket(HOST, PUERTO);

        try{
            // Buffers de entrada y salida del cliente
            out = new java.io.ObjectOutputStream(sock.getOutputStream());
            in = new java.io.ObjectInputStream(sock.getInputStream());

            // Leemos la bienvenida por parte del servidor y la mostramos por pantalla
            MensajeProtocolo info = (MensajeProtocolo) in.readObject();
            System.out.println(info);

            // Realizamos y verificamos el inicio de sesion
            out.writeObject(new MensajeProtocolo(Primitiva.XAUTH, usuario, password));
            out.flush();
            MensajeProtocolo authResp = (MensajeProtocolo) in.readObject();

            if(authResp.getPrimitiva() != Primitiva.XAUTH){
                System.out.println("Autenticacion fallida: " + authResp.getMensaje());
                return;
            }

            // Enviamos la cola para eliminar
            out.writeObject(new MensajeProtocolo(Primitiva.DELETEQ, mensaje));
            out.flush();

            // Esperamos a la respuesta por parte del servidor
            MensajeProtocolo respuesta = (MensajeProtocolo) in.readObject();
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
