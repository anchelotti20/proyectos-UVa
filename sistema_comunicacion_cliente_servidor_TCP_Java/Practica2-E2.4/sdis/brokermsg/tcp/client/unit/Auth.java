/**
 * @author Angel Antolin Caramazana, Alejandro Garcia Lanvadera, Manuel Arribas Martinez, Marcos Gomez Medina
 */
package sdis.brokermsg.tcp.client.unit;

import sdis.brokermsg.tcp.common.MalMensajeProtocoloException;
import sdis.brokermsg.tcp.common.MensajeProtocolo;
import sdis.brokermsg.tcp.common.Primitiva;

//Prueba la primitiva de inicio de sesion

public class Auth {
    static final private int PUERTO  = 2000;
    static final private String HOST = "localhost";
    static java.io.ObjectInputStream in = null;
    static java.io.ObjectOutputStream out = null;

    public static void main(String[] args) throws java.io.IOException, MalMensajeProtocoloException {
        if(args.length != 2){
            System.out.println("Uso:\njava Auth <usuario> <password>");
            System.exit(-1);
        }

        String user = args[0];
        String passwd = args[1];

        // Establecemos la conexion con el servidor
        java.net.Socket sock = new java.net.Socket(HOST, PUERTO);

        try {
            // Buffers de entrada salida del cliente
            out = new java.io.ObjectOutputStream(sock.getOutputStream());
            in = new java.io.ObjectInputStream(sock.getInputStream());

            // Leer el mensaje de bienvenida del servidor y lo muestra por pantalla
            MensajeProtocolo bienvenida = (MensajeProtocolo) in.readObject();
            System.out.println("Servidor: " + bienvenida);

            // Creamos y enviamos el mensaje XAUTH con argumentos
            MensajeProtocolo authmsg = new MensajeProtocolo(Primitiva.XAUTH, user, passwd);
            out.writeObject(authmsg);
            out.flush();
            System.out.println("Enviado: " + authmsg);

            // Leemos la respuesta del servidor
            MensajeProtocolo respuesta = (MensajeProtocolo) in.readObject();
            System.out.println("Servidor: " + respuesta);

            // Verificamos autenticacion
            if(respuesta.getPrimitiva() == Primitiva.XAUTH){
                System.out.println("Autenticacion exitosa");
            }else {
                System.out.println("Fallo de autenticacion: " + respuesta.getMensaje());
            }

        }catch (java.io.EOFException e){
            System.err.println("Cliente: Fin de conexion");

        }catch(java.io.IOException e){
            System.err.println("Cliente: Error de apertura o E/S sobre objetos: " + e);

        }catch(Exception e){
            System.err.println("Cliente: Excepcion. Cerrando Sockets: " + e);

        }finally {
            in.close();
            out.close();
            sock.close();
        }
    }
}
