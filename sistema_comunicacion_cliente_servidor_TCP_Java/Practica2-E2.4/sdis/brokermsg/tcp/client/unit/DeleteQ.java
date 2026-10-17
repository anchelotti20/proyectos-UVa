/**
 * @author Angel Antolin Caramazana, Alejandro Garcia Lanvadera, Manuel Arribas Martinez, Marcos Gomez Medina
 */
package sdis.brokermsg.tcp.client.unit;

import sdis.brokermsg.tcp.client.PruebaPeticiones;
import sdis.brokermsg.tcp.common.MalMensajeProtocoloException;
import sdis.brokermsg.tcp.common.MensajeProtocolo;
import sdis.brokermsg.tcp.common.Primitiva;

//Prueba la primitiva DeleteQ para borrar colas

public class DeleteQ {
    static final private int PUERTO = 2000;
    static final private String HOST = "localhost";
    static java.io.ObjectInputStream in = null;
    static java.io.ObjectOutputStream out = null;

    public static void main(String[] args) throws java.io.IOException, MalMensajeProtocoloException {
        if(args.length != 1) {
            System.out.println("[Servidor]\nUso:\njava DeleteQ <nombre_lista>");
            System.exit(-1);
        }

        // Cola que se pasa como argumento y se desea borrar
        String cola = args[0];

        //Establecemos la conexion con el servidor
        java.net.Socket sock = new java.net.Socket(HOST, PUERTO);

        try{
            // Buffer de entrada salida
            out = new java.io.ObjectOutputStream(sock.getOutputStream());
            in = new java.io.ObjectInputStream(sock.getInputStream());

            // Leemos la bienvenida del servidor y la mostramos por pantalla
            MensajeProtocolo info = (MensajeProtocolo) in.readObject();
            System.out.println(info);

            // Forzamos el inicio de sesion
            PruebaPeticiones.prueba(new MensajeProtocolo(Primitiva.XAUTH, "admin", "$%&/()="), in, out);

            // Realizas la primitiva
            PruebaPeticiones.prueba(new MensajeProtocolo(Primitiva.DELETEQ, cola), in, out);


        }catch (java.io.EOFException e) {
            System.err.println("Cliente: Fin de conexion");

        } catch (java.io.IOException e) {
            System.err.println("Cliente: Error de apertura o E/S sobre objetos: " + e);

        }catch(Exception e){
            System.err.println("Cliente: Excepcion. Cerrando Sockets: " + e);

        } finally{//Finalmente cerramos los input y outputs asi como el socket
            in.close();
            out.close();
            sock.close();
        }
    }


}
