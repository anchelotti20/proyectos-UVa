/**
 * @author Angel Antolin Caramazana, Alejandro Garcia Lanvadera, Manuel Arribas Martinez, Marcos Gomez Medina
 */
package sdis.brokermsg.rmi.server;

import sdis.brokermsg.rmi.common.Authenticator;
import sdis.brokermsg.rmi.common.BadAuthException;
import sdis.brokermsg.rmi.common.NotAuthException;
import sdis.brokermsg.rmi.server.AuthenticatorImpl;
import sdis.brokermsg.rmi.common.BrokerAdmMsg;
import sdis.brokermsg.tcp.server.GsonCredenciales;
import sdis.utils.MultiMap;

import java.util.concurrent.ConcurrentHashMap;


/**
 * Clase que implementa la interfaz remota del BrokerAdmImpl
 */
public class BrokerAdmMsgImpl
        extends java.rmi.server.UnicastRemoteObject
        implements BrokerAdmMsg {

    //Creación de colas de mensaje
    private final MultiMap<String,String> colaMensajes;
    private AuthenticatorImpl oAuth;


    //Constructor de la implementacion, donde necesita el multimapa
    public BrokerAdmMsgImpl(AuthenticatorImpl oAuth,MultiMap<String, String> colas) throws java.rmi.RemoteException{
        super();//esta esel costructor de UnicastRemote
        this.colaMensajes = colas;
        this.oAuth=oAuth;
    }


    //Permite aniadir mensaje a una cola
    public void add2Q(String token,String mensaje) throws java.rmi.RemoteException {


        //Comprobamos que el token existe
        if(oAuth.checkToken(token)){
            String cola="default";
            if(colaMensajes.compruebaColaExiste(cola)){


                System.out.println("> Server [RMI]: Agregando mensaje: [" + mensaje + "] a la cola [default]");
                colaMensajes.push(cola,mensaje);

            }else{
                if(oAuth.getUsuario(token).equals("admin")){
                    System.out.println("> Server [RMI]: Agregando mensaje [" + mensaje + "] a la cola [" + cola + "]");
                    colaMensajes.push(cola, mensaje);//introduce msg en la cola
                }else{
                    throw new BadAuthException("Server[RMI]: Cliente no tiene permisos para esta acción");
                }
            }
        }else{
            throw new NotAuthException(("Server[RMI]: Cliente no reconocido en el sisetma"));
        }


    }


    //Permite aniadir un mensaje a una cola deseada
    public void add2Q(String token,String cola, String mensaje) throws java.rmi.RemoteException {

        //Comprueba que el token existe
        if(oAuth.checkToken(token)){

            if(colaMensajes.compruebaColaExiste(cola)) {

                System.out.println("> Server [RMI]: Agregando mensaje [" + mensaje + "] a la cola [" + cola + "]");
                colaMensajes.push(cola, mensaje);//introduce msg en la cola

            }else{

                if(oAuth.getUsuario(token).equals("admin")){

                    System.out.println("> Server [RMI]: Agregando mensaje [" + mensaje + "] a la cola [" + cola + "]");
                    colaMensajes.push(cola, mensaje);//introduce msg en la cola


                }else{


                    throw new BadAuthException("Server[RMI]: Cliente no tiene permisos para esta acción");

                }
            }
        }else{

            throw new NotAuthException("Server[RMI]: Cliente no reconocido en el sistema");
        }

    }


    //Permite leer ( DESTRUTYENDO) un mensaje de la cola default
    public String readQ(String token) throws java.rmi.RemoteException {
        //Comprobacion de token
        if(oAuth.checkToken(token)){

            String cola="default";
            if(colaMensajes.compruebaColaExiste(cola)){

                String mensaje = colaMensajes.pop(cola);//elimina el mensaje mas antiguo de la cola
                System.out.println("> Server [RMI]: Leyendo mensaje [" + mensaje + "] de la cola [default]");
                return mensaje;//devulve el mensaje eliminado

            }else{

                return "Server[RMI]: La cola no existe";

            }

        }else{

            throw  new NotAuthException("Server[RMI]: Cliente no reconocido en el sistema");
        }

    }
    //Permite leer (DESTRUYENDO) un mensaje de la cola
    public String readQ(String token,String cola) throws java.rmi.RemoteException {


        if(oAuth.checkToken(token)){

            if(colaMensajes.compruebaColaExiste(cola)){

                String mensaje = colaMensajes.pop(cola);
                System.out.println("> Server [RMI]: Leyendo mensaje [" + mensaje + "] de la cola [" + cola + "]");
                return mensaje;

            }else{

                return "Server[RMI]: La cola no existe";
            }


        }else{

            throw new NotAuthException("Server[RMI]: Cliente no reconocido en el sistema");
        }

    }


    //Toma un elemento de la cola defult para leerlo , si no hay paramtro lo hace de una cola default,pero NO lo destruye
    public String peekQ(String token) throws java.rmi.RemoteException {

        if (oAuth.checkToken(token)) {

            if(oAuth.getUsuario(token).equals("admin")) {

                String cola = "default";//Si se introduce sin parametros debe usar una cola default

                if (colaMensajes.compruebaColaExiste(cola)) {
                    String mensaje = colaMensajes.peekQ(cola);
                    System.out.println("> Server [RMI]: Leyendo mensaje [" + mensaje + "] de la cola [default] sin eliminarlo");
                    return mensaje;


                } else {

                    return "Server[RMI]: La cola no existe";

                }

            }else{

                throw new BadAuthException("Server[RMI]: Cliente no tiene permisos para esta acción");

            }

        }else{

            throw new NotAuthException("Server[RMI]: Cliente no reconocido en el sistema");

        }

    }
    //Toma un elemento para leer de una cola introducida como parametro
    public String peekQ(String token,String cola) throws java.rmi.RemoteException {


        if (oAuth.checkToken(token)) {

            if(oAuth.getUsuario(token).equals("admin")) {

                if (colaMensajes.compruebaColaExiste(cola)) {
                    String mensaje = colaMensajes.peekQ(cola);
                    System.out.println("> Server [RMI]: Leyendo mensaje [" + mensaje + "] de la cola [default] sin eliminarlo");
                    return mensaje;


                } else {

                    return "Server[RMI]: La cola no existe";

                }

            }else{

                throw new BadAuthException("Server[RMI]: Cliente no tiene permisos para esta acción");

            }

        }else{

            throw new NotAuthException("Server[RMI]: Cliente no reconocido en el sistema");

        }


    }


    //Borra una cola por completo
    public String deleteQ(String token,String cola) throws java.rmi.RemoteException {


        if(oAuth.checkToken(token)){

            if(oAuth.getUsuario(token).equals("admin")) {

                if(colaMensajes.compruebaColaExiste(cola)){

                    System.out.println("> Server [RMI]: Borrando cola [" + cola + "] y todos sus mensajes");

                    colaMensajes.borraCola(cola);
                    System.out.println("> Server [RMI]: Cola borrada correctamente");
                    return "DELETED";


                }else{
                    return "Server[RMI]: La cola no existe";
                }


            }else{
                throw new BadAuthException(("Server[RMI]: Cliente no tiene permisos para esta accion"));
            }



        }else{
            throw new NotAuthException(("Server[RMI]: Cliente no reconocido en el sistema"));
        }


    }


    //Obtiene la lista de colas, lo devuelve como String
    public String getQueueList(String token) throws java.rmi.RemoteException {

        if(oAuth.checkToken(token)) {

            if (oAuth.getUsuario(token).equals("admin")) {

                System.out.println("> Server [RMI]: Listado de colas disponibles");

                return colaMensajes.getQueueList();

            } else {

                throw new BadAuthException("Server[RMI]: Cliente no tiene permisos para esta accion");
            }

        }else{

            throw new NotAuthException("Server[RMI]: Cliente no reconocido en el sistema");

        }

    }

}
