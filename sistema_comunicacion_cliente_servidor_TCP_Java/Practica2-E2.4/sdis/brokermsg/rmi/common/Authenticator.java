package sdis.brokermsg.rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Authenticator extends Remote {
    String connect(String usuario, String password) throws RemoteException;

    void disconnect(String token) throws RemoteException;

    boolean checkToken(String token) throws RemoteException;

    String getUsuario(String token) throws RemoteException;
}
