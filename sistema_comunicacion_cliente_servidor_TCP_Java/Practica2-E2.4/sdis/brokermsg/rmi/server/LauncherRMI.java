package sdis.brokermsg.rmi.server;



import sdis.brokermsg.server.ServerBroker;
import sdis.utils.MultiMap;

public class LauncherRMI {

        public void iniciar (MultiMap<String, String> colas, AuthenticatorImpl oAuth){
            final int PUERTO = 1099;

            try{
                //Creacion de los objetos remotos
                BrokerAdmMsgImpl oRemotoAdm = new BrokerAdmMsgImpl(oAuth, colas);
                BrokerMsgImpl oRemotoMsg = new BrokerMsgImpl(oRemotoAdm);


                //Creamos una referencia al registro
                java.rmi.registry.Registry registro = java.rmi.registry.LocateRegistry.createRegistry(PUERTO);

                //Asociamos nombre con referencia remota
                registro.rebind("objetoBrokerMsgImpl", oRemotoMsg);
                registro.rebind("objetoBrokerAdmMsgImpl", oRemotoAdm);
                registro.rebind("objetoAuthenticatorImpl", oAuth);


                //Mensaje de depuracion
                System.out.println("> Servidor [RMI]: Preparado");



            }catch (Exception e){

                System.err.println("Ha ocurrido un error" + e);
                e.printStackTrace();

            }

        }




}
