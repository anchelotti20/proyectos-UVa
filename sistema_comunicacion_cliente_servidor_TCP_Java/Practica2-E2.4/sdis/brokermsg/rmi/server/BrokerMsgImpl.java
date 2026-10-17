package sdis.brokermsg.rmi.server;

import sdis.brokermsg.rmi.common.BrokerAdmMsg;
import sdis.brokermsg.rmi.common.BrokerMsg;
import sdis.brokermsg.server.ServerBroker;
import sdis.brokermsg.tcp.server.GsonCredenciales;
import sdis.utils.MultiMap;

import java.util.concurrent.ConcurrentHashMap;

//Delega todas sus funciones a BrokerAdmMsg, que implementa los mismos métodos más los de admin
//Considerdo el BrokerAdmMsgImpl como clase delegada y BrokerMsgImpl como clase subogada
public class BrokerMsgImpl
        extends java.rmi.server.UnicastRemoteObject
        implements BrokerMsg {

    private  static final ConcurrentHashMap<String,String> listaUsuarios = GsonCredenciales.cargarUsuarios();
    private BrokerAdmMsg brokerAdmMsg; //Referencia al otro objeto(delegado)

    public BrokerMsgImpl(BrokerAdmMsg brokerAdmMsg)throws java.rmi.RemoteException{
        super();//constructor de UniCastRemote
        this.brokerAdmMsg = brokerAdmMsg;
    }


    //Aniade mensaje a cola, delega al brokerAdmMsg
    public void add2Q(String token,String mensaje) throws java.rmi.RemoteException {

        brokerAdmMsg.add2Q(token,mensaje);

    }

    //aniade mensaje a cola pasada como parametro
    public void add2Q(String token,String cola, String mensaje) throws java.rmi.RemoteException {

        brokerAdmMsg.add2Q(token,cola, mensaje);

    }

    //Permite leer de manera destructiva, en la cola default
    public String readQ(String token) throws java.rmi.RemoteException {

        return brokerAdmMsg.readQ(token);

    }

    //Lee de manera destructiva de la cola pasada como parametro
    public String readQ(String token,String cola) throws java.rmi.RemoteException {

        return brokerAdmMsg.readQ(token,cola);


    }



}
