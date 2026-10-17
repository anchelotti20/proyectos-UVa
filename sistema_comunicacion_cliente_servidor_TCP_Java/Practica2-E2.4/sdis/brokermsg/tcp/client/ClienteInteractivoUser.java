/**
 * @author Angel Antolin Caramazana, Alejandro Garcia Lanvadera, Manuel Arribas Martinez, Marcos Gomez Medina
 */
package sdis.brokermsg.tcp.client;


import sdis.brokermsg.tcp.common.MalMensajeProtocoloException;
import sdis.brokermsg.tcp.common.MensajeProtocolo;
import sdis.brokermsg.tcp.common.Primitiva;

//Prueba las distintas primitivas iniciando sesion como un usuario normal, sin status de admin

public class ClienteInteractivoUser {
    public static final int PUERTO = 2000;
    public static final String HOST = "localhost";
    static java.io.ObjectInputStream entradaServidor = null;
    static java.io.ObjectOutputStream enviaServidor = null;

    public static void main(String[] args) throws java.io.IOException, ClassNotFoundException{
        // Establecemos la conexion con el servidor
        java.net.Socket sock = new java.net.Socket(HOST, PUERTO);

        try{
            // Buffers de E/S del cliente
            entradaServidor = new java.io.ObjectInputStream(sock.getInputStream());
            enviaServidor = new java.io.ObjectOutputStream(sock.getOutputStream());

            // Recibimos la bienvenida del servidor y la mostramos por pantalla
            MensajeProtocolo info = (MensajeProtocolo) entradaServidor.readObject();
            System.out.println(info);
            System.out.println("Pulsa <Enter> para comenzar");System.in.read();

            //PRUEBA 2 INTENTOS DE SESION FALLIDOS E INICIA BIEN EN EL TERCERO
            PruebaPeticiones.prueba(new MensajeProtocolo(Primitiva.XAUTH, "hectr", "lkjlkj"), entradaServidor, enviaServidor);

            PruebaPeticiones.prueba(new MensajeProtocolo(Primitiva.XAUTH, "hector", "lkhlkh"), entradaServidor, enviaServidor);

            PruebaPeticiones.prueba(new MensajeProtocolo(Primitiva.XAUTH, "hector", "lkjlkj"), entradaServidor, enviaServidor);

            //PRUEBA STATE ANTES DE ANIADIR NADA, AL SER UN USER, DEVUELVE NOTAUTH

            PruebaPeticiones.prueba(new MensajeProtocolo(Primitiva.STATE), entradaServidor, enviaServidor);

            //INTENTA METER MENSAJES A LA COLA, SI EXISTEN DEVUELVEN ADDED, SI NO DEVUELVE NOTAUTH
            PruebaPeticiones.prueba(new MensajeProtocolo(Primitiva.ADDMSG, "hola", "Hola Alex"), entradaServidor, enviaServidor);

            PruebaPeticiones.prueba(new MensajeProtocolo(Primitiva.ADDMSG, "hola", "Hola Marcos"), entradaServidor, enviaServidor);

            PruebaPeticiones.prueba(new MensajeProtocolo(Primitiva.ADDMSG, "Adios", "Adios Manuel"), entradaServidor, enviaServidor);

            //LEE MENSAJES DE LAS COLAS
            PruebaPeticiones.prueba(new MensajeProtocolo(Primitiva.READQ, "hola"), entradaServidor, enviaServidor);

            PruebaPeticiones.prueba(new MensajeProtocolo(Primitiva.READQ, "Adios"), entradaServidor, enviaServidor);

            PruebaPeticiones.prueba(new MensajeProtocolo(Primitiva.READQ, "Adios"), entradaServidor, enviaServidor);

            PruebaPeticiones.prueba(new MensajeProtocolo(Primitiva.READQ, "ColaFalsa"), entradaServidor, enviaServidor);

            //PRUEBA A BORRAR UNA COLA, NOTHAUTH AL NO SER ADMIN
            PruebaPeticiones.prueba(new MensajeProtocolo(Primitiva.DELETEQ, "hola"), entradaServidor, enviaServidor);

            //PRUEBA UNA PRIMITIVA BADCODE

            PruebaPeticiones.prueba(new MensajeProtocolo(Primitiva.BADCODE, "hola"), entradaServidor, enviaServidor);


        }catch(java.io.IOException e) {
            System.err.println("Cliente: Error E/S");

        }catch (MalMensajeProtocoloException e){

            System.err.println("Cliente: Malmensaje protocolo.");

        }



    }




}






