package sdis.brokermsg.rmi.client.unit;


import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.Naming;

import sdis.brokermsg.rmi.common.Authenticator;
import sdis.brokermsg.rmi.common.BrokerAdmMsg;


//Cliente que nos permite ir leyendo mientras destruimos la cola hasta que quede vacia
public class ContinuousReadQ {
    public static final String host = "localhost";
    public static final int PUERTO = 1099;

    public static void main (String[] args) throws IOException, NotBoundException, InterruptedException {
        // Obtenemos la referencia remota del Broker, importante el cast a la interfaz remota
        BrokerAdmMsg oBrokerAdmMsg = (BrokerAdmMsg) Naming.lookup("rmi://"+host+":"+PUERTO+"/objetoBrokerAdmMsgImpl");

        // Obtenemos la referencia remota
        Authenticator auth = (Authenticator) Naming.lookup("rmi://" + host + ":" + PUERTO + "/objetoAuthenticatorImpl");

        // Datos del usuario
        String user = "admin";
        String pass = "$%&/()=";

        // Autenticamos
        String token = auth.connect(user, pass);//Genera el token al conectar el usuario
        if(token == null){
            System.err.println("> Server [RMI]: Usuario o password incorrectos.");
            System.exit(1);
        }else{
            System.out.println("> Server [RMI]: Login correcto -> token [" + token + "]");
        }
        System.out.println("Pulsa <Enter> "); System.in.read();
        System.out.println(" ");


        // Recibimos como argumento la cola a leer, por defecto sera la cola [default]
        String cola = (args.length < 1) ? "default" : args[0];

        // Feedback
        System.out.println("> ContinuousReadQ: Conectado al servidor [RMI]: " + host + ":" + PUERTO);
        System.out.println("> ContinuousReadQ: Iniciando lectura de la cola [" + cola + "]");


        // Procedemos con la lectura destructiva de la cola
        String mensaje = oBrokerAdmMsg.readQ(token,cola);
        while((mensaje != null) && !mensaje.equalsIgnoreCase("EMPTY")) {
            // Mostramos el mensaje por consola
            System.out.println("> ContinuousReadQ: [" + mensaje + "]");
            // Pausa entre mensajes
            Thread.sleep(1000);
            // Conseguimos el siguiente mensaje
            mensaje = oBrokerAdmMsg.readQ(token,cola);
        }

        // Mensaje de cola vacia
        System.out.println("> ContinuousReadQ [FIN]: Cola vacia");

        // Desconectamos el usuario
        System.out.println("> ContinuousReadQ: Desconectando...");
        auth.disconnect(token);

    }

}
