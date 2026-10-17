package sdis.brokermsg.server;

import sdis.brokermsg.rmi.common.Authenticator;
import sdis.brokermsg.rmi.server.AuthenticatorImpl;
import sdis.utils.MultiMap;

import java.rmi.RemoteException;


/**
 * El serverBroker se trata de un lanzador muy espcial, ya que gracias a el
 * podemos iniciar los servicios que han de convivir en nuestro sistema,
 * estos son TCP y RMI.
 */
public class ServerBroker {

    //Creamos las colas de mensajes aqui para que sea comun a TCP y RMI
    private static final MultiMap<String, String> mapa = new MultiMap<>();


    public static void main(String [] args) throws RemoteException {
        try{
            iniciaColas();//Se trata de un metodo para iniciar un MultiMapa con colas para pruebas
            sdis.brokermsg.rmi.server.LauncherRMI launcherRMI = new sdis.brokermsg.rmi.server.LauncherRMI();//Creamos una instancia de launcher RMI
            sdis.brokermsg.tcp.server.ServidorTCP serverTCP = new sdis.brokermsg.tcp.server.ServidorTCP();//Creamos instancia lanzador TCP

            AuthenticatorImpl oAuth = new AuthenticatorImpl("sha-256");

            //Lanzamos los servidores
            launcherRMI.iniciar(mapa, oAuth);
            serverTCP.lanzarServerTCP(mapa);

        }catch(Exception e){
            System.err.println("> ServerBroker: Error lanzando uno de los dos lanzadores" + e.toString());
        }   // Fin try-catch
    }   // Fin main

    //Inicia el MultiMapa con algunas Colas por Defecto
    public static void iniciaColas(){

        mapa.push("default", "cola default vacia");
        mapa.pop("default");

        mapa.push("primera", "solo un mensaje");

        mapa.push("muchas", "cola con varios mensajes");
        mapa.push("muchas", "otro mensaje");
        mapa.push("muchas", "mas mensajes");
        mapa.push("muchas", "otro mas");
    }   // Fin iniciaColas



}   // Fin de la clase
