/**
 * @author Angel Antolin Caramazana, Alejandro Garcia Lanvadera, Manuel Arribas Martinez, Marcos Gomez Medina
 */
package sdis.brokermsg.tcp.client;

import sdis.brokermsg.tcp.common.MalMensajeProtocoloException;
import sdis.brokermsg.tcp.common.MensajeProtocolo;
import sdis.brokermsg.tcp.common.Primitiva;

//Prueba las primitivas con usuario de STATUS = ADMIN

public class ClienteInteractivoAdmin {
    public static final int PUERTO = 2000;
    public static final String HOST = "localhost";
    static java.io.ObjectInputStream entradaServidor = null;
    static java.io.ObjectOutputStream enviaServidor = null;


    public static void main(String[] args) throws java.io.IOException, ClassNotFoundException{

        // Establecemos conexion con el servidor
        java.net.Socket sock = new java.net.Socket(HOST, PUERTO);

        try{
            // Buffers de E/S del cliente
            entradaServidor = new java.io.ObjectInputStream(sock.getInputStream());
            enviaServidor = new java.io.ObjectOutputStream(sock.getOutputStream());

            // Mostramos la bienvenida del servidor en el cliente
            MensajeProtocolo info = (MensajeProtocolo) entradaServidor.readObject();
            System.out.println(info);
            System.out.println("Pulsa <Enter> para comenzar");System.in.read();

            //PRUEBA 2 INTENTOS DE SESION FALLIDOS E INICIA BIEN EN EL TERCERO
            PruebaPeticiones.prueba(new MensajeProtocolo(Primitiva.XAUTH, "amin", "$%&/()="), entradaServidor, enviaServidor);

            PruebaPeticiones.prueba(new MensajeProtocolo(Primitiva.XAUTH, "amin", "$%&/()="), entradaServidor, enviaServidor);

            PruebaPeticiones.prueba(new MensajeProtocolo(Primitiva.XAUTH, "admin", "$%&/()="), entradaServidor, enviaServidor);

            //PRUEBA STATE ANTES DE ANIADIR NADA
            PruebaPeticiones.prueba(new MensajeProtocolo(Primitiva.STATE), entradaServidor, enviaServidor);

            //PRUEBA A CREAR UNA COLA Y METERLA MENSAJES
            PruebaPeticiones.prueba(new MensajeProtocolo(Primitiva.ADDMSG, "hola", "Hola Angel"), entradaServidor, enviaServidor);

            PruebaPeticiones.prueba(new MensajeProtocolo(Primitiva.ADDMSG, "hola", "Hola admin"), entradaServidor, enviaServidor);

            PruebaPeticiones.prueba(new MensajeProtocolo(Primitiva.ADDMSG, "Adios", "Adios prueba"), entradaServidor, enviaServidor);

            //PRUEBA A COGER MENSAJES DE LAS 2 COLAS E INTENTA COGER DE LA COLA QUE QUEDA VACIA Y DE UNA QUE NO EXISTE
            PruebaPeticiones.prueba(new MensajeProtocolo(Primitiva.READQ, "hola"), entradaServidor, enviaServidor);

            PruebaPeticiones.prueba(new MensajeProtocolo(Primitiva.READQ, "Adios"), entradaServidor, enviaServidor);

            PruebaPeticiones.prueba(new MensajeProtocolo(Primitiva.READQ, "Adios"), entradaServidor, enviaServidor);

            PruebaPeticiones.prueba(new MensajeProtocolo(Primitiva.READQ, "ColaFalsa"), entradaServidor, enviaServidor);

            //PRUEBA LA PRIMITIVA STATE DESPUES DE METER MENSAJES Y SACAR

            PruebaPeticiones.prueba(new MensajeProtocolo(Primitiva.STATE), entradaServidor, enviaServidor);

            //PRUEBA A BORRAR UNA COLA QUE EXISTE Y OTRA QUE NO

            PruebaPeticiones.prueba(new MensajeProtocolo(Primitiva.DELETEQ, "hola"), entradaServidor, enviaServidor);

            PruebaPeticiones.prueba(new MensajeProtocolo(Primitiva.DELETEQ, "ColaFalsa"), entradaServidor, enviaServidor);

            //PRUEBA UNA PRIMITIVA BADCODE

            PruebaPeticiones.prueba(new MensajeProtocolo(Primitiva.BADCODE, "hola"), entradaServidor, enviaServidor);



        }catch(java.io.IOException e) {
            System.err.println("Cliente: Error E/S");

        }catch (MalMensajeProtocoloException e){

            System.err.println("Cliente: Malmensaje protocolo.");

        }



    }


}
