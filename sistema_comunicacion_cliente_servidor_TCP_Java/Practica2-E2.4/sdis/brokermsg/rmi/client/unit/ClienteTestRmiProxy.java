package sdis.brokermsg.rmi.client.unit;


import sdis.brokermsg.rmi.common.Authenticator;
import sdis.brokermsg.rmi.common.NotAuthException;

public class ClienteTestRmiProxy {

    public static final String host = "localhost";

    public static void main (String []args) throws java.io.IOException, ClassNotFoundException {

        try{
            Authenticator oAuth = (Authenticator) java.rmi.Naming.lookup("rmi://" + host + ":1099/objetoAuthenticatorImpl");

            ConnectProxy proxy = new ConnectProxy(oAuth, "rmi://" + host + ":1099/objetoBrokerAdmMsgImpl", "admin", "$%&/()=");


            // Conectado al servidor
            System.out.println("> Cliente conectado con el servidor RMI: ");
            System.out.println("Pulsa <Enter> "); System.in.read();
            System.out.print("");


            // Obtener listado de las colas > getQueueList
            String listaColas = proxy.getQueueList();
            System.out.println("> Lista de Colas : " + listaColas);
            System.out.println("Pulsa <Enter> "); System.in.read();
            System.out.print("");

            // Mandamos mensajes a las colas
            proxy.add2Q("1. Mensaje agregado sin especificar la cola desde el proxy");
            proxy.add2Q("primera", "2. Mensaje agregado especificando la cola primera desde el proxy");
            proxy.add2Q("muchas", "3. Mensaje agregado especificando la cola muchas desde el proxy");
            System.out.println("> Mensajes agregados a las colas del proxy");
            System.out.println("Pulsa <Enter> "); System.in.read();
            System.out.print("");

            // PEEK (solo admin)
            String peek = proxy.peekQ();
            System.out.println("> Peek (sin especificar cola): " + peek);
            peek = proxy.peekQ("primera");
            System.out.println("> Peek (primera): " + peek);
            System.out.println("Pulsa <Enter> "); System.in.read();
            System.out.print("");

            // Leer mensajes > readQ()
            String mensaje = proxy.readQ();
            System.out.println("> Mensaje [sin especificar cola]: " + mensaje);
            mensaje = proxy.readQ("muchas");
            System.out.println("> Mensaje [muchas]: " + mensaje);
            System.out.println("Pulsa <Enter> "); System.in.read();
            System.out.print("");


            // Borrar colas > deleteQ
            String deleteResult = proxy.deleteQ("default");
            System.out.println("> Resultado eliminar [default]: " + deleteResult);
            listaColas = proxy.getQueueList();
            System.out.println("> Lista de Colas actualizada: " + listaColas);
            System.out.println("Pulsa <Enter> "); System.in.read();
            System.out.print("");



            // Desconectando el cliente
            System.out.println("> ClienteTestRMIProxy: Desconectando con el servidor [RMI]: " + host);
            proxy.disconnect();



        }catch (java.rmi.RemoteException er){

            System.out.println("> ClienteProxy excepcion RMI: " + er);


        }catch (Exception e){

            System.out.println("> ClienteProxy excepcion: " + e);
        }
    }

}
