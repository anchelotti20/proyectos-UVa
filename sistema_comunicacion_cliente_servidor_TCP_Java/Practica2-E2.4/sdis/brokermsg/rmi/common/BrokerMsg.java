package sdis.brokermsg.rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

//Interfaz remota para donde incluimos los metodos para el usuario(no admin)
public interface BrokerMsg extends Remote {

    //String auth(String username, String password) throws RemoteException;

    void add2Q(String token,String mensaje) throws RemoteException;
    void add2Q(String token,String cola,String mensaje) throws RemoteException;

    String readQ(String token) throws RemoteException;
    String readQ(String token,String cola) throws RemoteException;

}
