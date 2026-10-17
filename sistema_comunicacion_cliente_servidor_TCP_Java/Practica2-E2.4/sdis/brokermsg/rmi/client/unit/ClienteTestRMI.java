package sdis.brokermsg.rmi.client.unit;

import sdis.brokermsg.rmi.common.Authenticator;
import sdis.brokermsg.rmi.common.BadAuthException;
import sdis.brokermsg.rmi.common.NotAuthException;
import sdis.brokermsg.rmi.common.BrokerAdmMsg;

import java.rmi.Naming;

public class ClienteTestRMI {
    public static final int PUERTO = 1099;
    public static final String host = "localhost";


    public static void main(String[] args) throws java.io.IOException, ClassNotFoundException {
                try {
                    BrokerAdmMsg oBrokerAdmMsg = (BrokerAdmMsg) Naming.lookup("rmi://" + host + ":" + PUERTO + "/objetoBrokerAdmMsgImpl");

                    // Conectado al servidor
                    System.out.println("> Cliente conectado con el servidor RMI: " + host);
                    System.out.println("Pulsa <Enter> ");
                    System.in.read();
                    System.out.print("");

                    // Obtenemos la referencia remota
                    Authenticator auth = (Authenticator) Naming.lookup("rmi://" + host + ":" + PUERTO + "/objetoAuthenticatorImpl");

                    // Enviamos las credenciales de login
                    String user = "cllamas";
                    String pass = "qwerty";

                    // Autenticamos
                    String token = auth.connect(user, pass);//Genera el token al conectar el usuario
                    if (token == null) {
                        System.err.println("> Server [RMI]: Usuario o password incorrectos.");
                        System.exit(1);
                    } else {
                        System.out.println("> Server [RMI]: Login correcto -> token [" + token + "]");
                    }
                    System.out.println("Pulsa <Enter> ");
                    System.in.read();
                    System.out.print("");

                    System.out.println("Intenta ver la lista de colas siendo usuario");
                    try {
                        // Obtener listado de las colas > getQueueList
                        String listaColas = oBrokerAdmMsg.getQueueList(token);
                        System.out.println("> Lista de Colas : " + listaColas);


                    } catch (BadAuthException e) {

                        System.err.println("> Cliente: " + e.getMessage());

                    }

                    System.out.println("Pulsa <Enter> ");
                    System.in.read();
                    System.out.print("");

                    System.out.println("Agrega Mensajes a colas existentes y a una que no existe");
                    // Mandamos mensajes a las colas
                    oBrokerAdmMsg.add2Q(token, "1. Mensaje agregado sin especificar la cola");
                    oBrokerAdmMsg.add2Q(token, "primera", "2. Mensaje agregado especificando la cola primera");

                    try {

                        oBrokerAdmMsg.add2Q(token, "ColaQueNoExiste", "No deberia dejar");

                    } catch (BadAuthException e) {

                        System.err.println("> Cliente: " + e.getMessage());

                    }
                    System.out.println("> Mensajes agregados a las colas");
                    System.out.println("Pulsa <Enter> ");
                    System.in.read();
                    System.out.print("");

                    System.out.println("Intenta hacer peek siendo usuario");
                    try {

                        // PEEK (solo admin)
                        String peek = oBrokerAdmMsg.peekQ(token);
                        System.out.println("> Peek (sin especificar cola): " + peek);

                    } catch (BadAuthException e) {

                        System.err.println("> Cliente: " + e.getMessage());
                    }

                    System.out.println("Pulsa <Enter> ");
                    System.in.read();
                    System.out.print("");

                    System.out.println("Lee mensajes de colas que existen y de una que no");
                    // Leer mensajes > readQ()
                    String mensaje = oBrokerAdmMsg.readQ(token);
                    System.out.println("> Mensaje [sin especificar cola]: " + mensaje);
                    mensaje = oBrokerAdmMsg.readQ(token, "ColaQueNoExiste");
                    System.out.println("> Mensaje [ColaQueNoExiste]: " + mensaje);
                    System.out.println("Pulsa <Enter> ");
                    System.in.read();
                    System.out.print("");

                    System.out.println("Intenta borrar una cola siendo usuario");
                    try{

                        // Borrar colas > deleteQ
                        String deleteResult = oBrokerAdmMsg.deleteQ(token, "default");
                        System.out.println("> Resultado eliminar [default]: " + deleteResult);

                    }catch (BadAuthException e) {

                        System.err.println("> Cliente: " + e.getMessage());
                    }

                    System.out.println("Pulsa <Enter> "); System.in.read();
                    System.out.print("");

                    System.out.println("Intenta llamar a un metodo con un token no valido");
                    //Prueba que lanza bien la excepcion cuando no se reconoce el token
                    try{

                        oBrokerAdmMsg.readQ("hola", "default");

                    }catch (NotAuthException er) {

                        System.err.println(">Cliente: " + er.getMessage());
                    }

                    // Desconectando el cliente
                    System.out.println("> ClienteTestRMI: Desconectando con el servidor [RMI]: " + host);
                    auth.disconnect(token);



                }catch (java.rmi.RemoteException er){
                    System.err.println("<Cliente: Excepcion RMI:" +er);


                }catch(Exception e){
                    System.err.println("<Cliente: Exception: "+e);
                    e.printStackTrace();
                }   // Fin try-catch
    }   // Fin main
}   // Fin clase ClienteTestRMI
