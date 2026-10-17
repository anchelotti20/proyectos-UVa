/**
 * @author Angel Antolin Caramazana, Alejandro Garcia Lanvadera, Manuel Arribas Martinez, Marcos Gomez Medina
 */
package sdis.brokermsg.tcp.client.unit;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import sdis.brokermsg.tcp.client.PruebaPeticiones;
import sdis.brokermsg.tcp.common.MalMensajeProtocoloException;
import sdis.brokermsg.tcp.common.MensajeProtocolo;
import sdis.brokermsg.tcp.common.Primitiva;

//Prueba la primitiva AddMsg
public class AddMsg {
    final private static int PUERTO = 2000;
    final private static String HOST = "localhost";
    static java.io.ObjectInputStream in = null;
    static java.io.ObjectOutputStream out = null;

    public static void main(String[] args) throws java.io.IOException {
        if(args.length != 2) {
            System.out.println("Uso:\njava AddMsg  <cola> <mensaje>");
            System.exit(1);
        }

        String cola = args[0];
        String mensaje = args[1];

        // Establecemos la conexion con el servidor
        java.net.Socket sock = new java.net.Socket(HOST, PUERTO);

        try{
            // Buffers E/S del cliente
            in = new ObjectInputStream(sock.getInputStream());
            out = new ObjectOutputStream(sock.getOutputStream());

            // Leemos la bienvenida del servidor y la mostramos por pantalla
            MensajeProtocolo info = (MensajeProtocolo) in.readObject();
            System.out.println(info);

            // Forzamos el inicio de sesion
            PruebaPeticiones.prueba(new MensajeProtocolo(Primitiva.XAUTH, "admin", "$%&/()="), in, out);

            // Realizamos la primitiva
            PruebaPeticiones.prueba(new MensajeProtocolo(Primitiva.ADDMSG, cola, mensaje), in, out);



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
