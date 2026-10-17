/**
 * @author Angel Antolin Caramazana, Alejandro Garcia Lanvadera, Manuel Arribas Martinez, Marcos Gomez Medina
 */
package sdis.brokermsg.tcp.client.unit;

import sdis.brokermsg.tcp.client.PruebaPeticiones;
import sdis.brokermsg.tcp.common.MalMensajeProtocoloException;
import sdis.brokermsg.tcp.common.MensajeProtocolo;
import sdis.brokermsg.tcp.common.Primitiva;


//Prueba la primitiva ReadQ, para sacar el mensaje mas antiguo de una cola
public class ReadQ {
    static final private int PUERTO = 2000;
    static final private String HOST = "localhost";
    static java.io.ObjectInputStream in = null;
    static java.io.ObjectOutputStream out = null;

    public static void main(String[] args) throws java.io.IOException, MalMensajeProtocoloException {
        if (args.length != 1) {
            System.out.println("[Servidor]\nUso:\n java ReadQ <mensaje>");
            System.exit(-1);
        }

        // Guardamos el mensaje
        String mensaje = args[0];

        // Establecemos la conexion con el servidor
        java.net.Socket sock = new java.net.Socket(HOST, PUERTO);

        try {
            // Recogemos el buffer de entrada y salida
            out = new java.io.ObjectOutputStream(sock.getOutputStream());
            in = new java.io.ObjectInputStream(sock.getInputStream());

            // Leemos la bienvenida del servidor y la mostramos por pantalla
            MensajeProtocolo info = (MensajeProtocolo) in.readObject();
            System.out.println(info);

            // Forzamos el inicio de sesion y realizamos la primitiva
            PruebaPeticiones.prueba(new MensajeProtocolo(Primitiva.XAUTH, "admin", "$%&/()="), in, out);
            PruebaPeticiones.prueba(new MensajeProtocolo(Primitiva.READQ, mensaje), in, out);


        } catch (java.io.EOFException e) {
            System.err.println("Cliente: Fin de conexion");

        } catch (java.io.IOException e) {
            System.err.println("Cliente: Error de apertura o E/S sobre objetos: " + e);

        } catch (Exception e) {
            System.err.println("Cliente: Excepcion. Cerrando Sockets: " + e);

        } finally {//Finalmente cerramos los input y outputs asi como el socket
            in.close();
            out.close();
            sock.close();
        }

    }
}
