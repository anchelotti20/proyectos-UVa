package sdis.brokermsg.rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

//Interfaz remota para el usuario ADMIN, tiene las acciones de un user normal mas las suyas exclusivas
public interface BrokerAdmMsg extends Remote, BrokerMsg {

    //String auth(String username, String password) throws RemoteException;

    void add2Q(String token,String mensaje) throws RemoteException;
    void add2Q(String token,String cola,String mensaje) throws RemoteException;

    String readQ(String token) throws RemoteException;
    String readQ(String token,String cola) throws RemoteException;

    String peekQ(String token) throws RemoteException;
    String peekQ(String token,String cola) throws RemoteException;

    String deleteQ(String token,String cola) throws RemoteException;

    String getQueueList(String token) throws RemoteException;
}
