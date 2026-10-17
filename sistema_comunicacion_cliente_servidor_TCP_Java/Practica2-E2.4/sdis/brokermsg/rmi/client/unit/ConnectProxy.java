package sdis.brokermsg.rmi.client.unit;


import sdis.brokermsg.rmi.common.Authenticator;
import sdis.brokermsg.rmi.common.BrokerAdmMsg;

import java.rmi.Naming;

public class ConnectProxy {


    private String token;
    private BrokerAdmMsg servidor;
    private Authenticator auth;


    public ConnectProxy(Authenticator oAuth, String urlServer, String user, String pass)  {
        try{
            // Almacenamos el token de la conexion del usuario
            token = oAuth.connect(user, pass);

            servidor = (BrokerAdmMsg) java.rmi.Naming.lookup(urlServer);

            auth = oAuth;


        } catch (Exception e) {
            System.out.println("ConnectProxy: " + e);
        }
    }   // Fin ConnectProxy


    public void add2Q (String mensaje){

        try{

            servidor.add2Q(token, mensaje);

        } catch (Exception e) {

            System.out.println("ConnectProxy:Exception  " + e);

        }

    }   // Fin add2Q

    public void add2Q (String cola, String mensaje){

        try{

            servidor.add2Q(token, cola, mensaje);

        } catch (Exception e) {

            System.out.println("ConnectProxy:Exception  " + e);
        }

    }   // Fin add2Q



    public String readQ(){

        try{

            return (servidor.readQ(token));

        }catch(Exception e){

            System.out.println("ConnectProxy:Exception"+ e);
        }

        return null;

    }   // Fin readQ




    public String readQ(String cola){

        try{
            return (servidor.readQ(token,cola));

        }catch(Exception e){

            System.out.println("ConnectProxy:Exception"+ e);
        }

        return null;

    }   // Fin readQ




    public String peekQ (){

        try{

            return (servidor.peekQ(token));

        }catch(Exception e){

            System.out.println("ConnectProxy:Exception"+ e);
        }

        return null;

    }   // Fin peekQ


    public String peekQ (String cola){

        try{

            return (servidor.peekQ(token, cola));

        }catch (Exception e){

            System.out.println("ConnectProxy:Exception"+ e);

        }

        return null;
    }   // Fin peekQ

    public String deleteQ (String cola){

        try{

            return (servidor.deleteQ(token, cola));

        }catch(Exception e){

            System.out.println("ConnectProxy:Exception"+ e);

        }

        return null;

    }   // Fin deleteQ


    public String getQueueList (){

        try{

            return (servidor.getQueueList(token));

        }catch (Exception e){


            System.out.println("ConnectProxy:Exception"+ e);

        }

        return null;
    }   // Fin getQueueList


    public void disconnect(){


        try{

            auth.disconnect(token);


        }catch (Exception e){

            System.out.println("ConnectProxy:Exception"+ e);
        }

    }   // Fin disconnect


}   // Fin clase
