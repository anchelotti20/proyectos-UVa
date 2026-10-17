/**
 * @author Angel Antolin Caramazana, Alejandro Garcia Lanvadera, Manuel Arribas Martinez, Marcos Gomez Medina
 */
package sdis.brokermsg.tcp.server;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

import sdis.brokermsg.tcp.common.MalMensajeProtocoloException;
import sdis.brokermsg.tcp.common.MensajeProtocolo;
import sdis.brokermsg.tcp.common.Primitiva;
import sdis.brokermsg.tcp.common.Strings;
import sdis.utils.MultiMap;

class Sirviente implements Runnable {
    private final Socket socket;            //Socket de la conexion
    private final MultiMap<String, String> colasMensajes;     //Colas de mensajes
    private final ThreadPoolExecutor poolHilos;     //Permite conocer el numero de hilos activos
    private final ObjectOutputStream enviaCliente;  //Envia los objetos Mensaje Protocolo al Cliente
    private final ObjectInputStream  leeCliente;     //Lee los objetos Mensaje Protocolo del Cliente
    private final int ns;                            //Cuenta las veces que se ha creado un Sirviente
    private static AtomicInteger nInstance = new AtomicInteger();
    private int estadoCliente;                       //Guarda el STATUS del usuario (0 = No logeado, 1 = USER, 2 = ADMIN)
    private static final BlackListManager conexiones = new BlackListManager();  //Lista negra de conexiones simultaneas
    private static final BlackListManager intentosLogin = new BlackListManager(); //Lista negra de intentos de inicio de sesion
    private static AtomicInteger nMensaje = new AtomicInteger(); //Cuenta las veces que se aniade un mensaje a una cola
    private static AtomicInteger nSacado = new AtomicInteger();  //Cuenta las veces que se saca un mensaje de una cola
    private int mensajesSubidos;
    private int mensajesSacados;

    //Inicia el HashMap de usuarios a partir del Json
    private  static final ConcurrentHashMap<String,String> usuarios = GsonCredenciales.cargarUsuarios();

    //Constructor de Sirviente
    Sirviente(Socket s, MultiMap<String, String> c, ExecutorService exec) throws IOException {
        this.socket = s;
        this.colasMensajes   = c;
        this.poolHilos = (ThreadPoolExecutor) exec;
        this.ns     = nInstance.getAndIncrement();
        this.enviaCliente = new ObjectOutputStream(socket.getOutputStream());
        this.leeCliente = new ObjectInputStream(socket.getInputStream());
        this.estadoCliente = 0;
        this.mensajesSubidos = nMensaje.get();
        this.mensajesSacados = nSacado.get();

    } // se invoca en el Servidor, usualmente

    //METODO RUN DEL SIRVIENTE
    public void run() {
        try {

            //Obtiene la IP del cliente
            String ipCliente = socket.getInetAddress().getHostAddress();

            //Registra las conexiones simultaneas
            conexiones.registrarIntento(ipCliente);
            conexiones.imprimirIntentosConexion(ipCliente);

            //Si tiene mas de 3 conexiones simultaneas, PRIMITIVA ERROR
            if(conexiones.getIntentos().get(ipCliente) <= 3) {

                MensajeProtocolo ms = null;
                //Envia la primitiva INFO al cliente para comenzar la conexion
                enviaCliente.writeObject(new MensajeProtocolo(Primitiva.INFO, Strings.mensajeBienvenida()));

                while (true) {

                    //Recibe la primitiva del cliente
                    MensajeProtocolo me = (MensajeProtocolo) leeCliente.readObject();

                    //Mensaje de depuracion
                    System.out.println("Sirviente: " + ns + ": [ME: " + me + "]" + ": [PRIM: " + me.getPrimitiva());

                    switch (me.getPrimitiva()) {

                        //Primitiva de inicio de sesion
                        case XAUTH:

                            //Registra intento de iniciar sesion
                            intentosLogin.registrarIntento(ipCliente);

                            //Maximo 3 intentos de login seguidos, si no PRIMITIVA ERROR
                            if(intentosLogin.getIntentos().get(ipCliente) <= 3) {
                                //Recoge del usuario el username y el password
                                String username = me.getIdCola();
                                String password = me.getMensaje();

                                //Comprueba que pertenece a los usuarios registrados
                                if (usuarios.containsKey(username) && usuarios.get(username).equals(password)) {

                                    //Reinicia los intentos de login de la ip
                                    intentosLogin.loginCorrecto(ipCliente);
                                    //Devuelve el mensaje de login correcto
                                    ms = new MensajeProtocolo(Primitiva.XAUTH, Strings.mensajeLoginCorrecto());

                                    if (username.equals("admin")) {
                                        //Si es el usuario admin, lo controla
                                        this.estadoCliente = 2;

                                    } else {
                                        //Si no, usuario normal
                                        this.estadoCliente = 1;

                                    }

                                } else {

                                    intentosLogin.imprimirIntentosLogin(ipCliente);
                                    //En caso de que no este guardada, devuelve la primitiva de NOTAUTH
                                    ms = new MensajeProtocolo(Primitiva.NOTAUTH, Strings.mensajeReLogin());



                                }
                            }else{

                                ms = new MensajeProtocolo(Primitiva.ERROR, Strings.mensajeErrorLogin());

                            }
                            break;

                        /*Aniade mensajes a la cola de mensajes especificada, si la cola no existe
                         *  la crea si eres admin, si no NOAUTH, si sale bien ADDED
                         */
                        case ADDMSG:

                            //Si no esta logeado, devuelve NOTAUTH
                            if(this.estadoCliente != 0) {

                                //Si cola existe, aniade el mensaje y suma 1 al contador global
                                if (colasMensajes.compruebaColaExiste(me.getIdCola())) {

                                    colasMensajes.push(me.getIdCola(), me.getMensaje());

                                    mensajesSubidos = nMensaje.incrementAndGet();

                                    ms = new MensajeProtocolo(Primitiva.ADDED);

                                    //Si no existe y es admin, la crea y le aniade el mensaje
                                }else if(this.estadoCliente == 2){

                                    colasMensajes.push(me.getIdCola(), me.getMensaje());

                                    mensajesSubidos = nMensaje.incrementAndGet();

                                    ms = new MensajeProtocolo(Primitiva.ADDED);

                                //No puedes crear cola
                                }else{

                                    ms = new MensajeProtocolo(Primitiva.NOTAUTH, Strings.noAdmin());

                                }
                            //No login
                            }else{

                                ms = new MensajeProtocolo(Primitiva.NOTAUTH, Strings.loginRequerido());


                            }

                            break;

                        /*Saca el mensaje mas antiguo de la cola indicada, si la cola no existe
                         * o esta vacia EMPTY, si no MSG con el mensaje sacado de la cola
                         */
                        case READQ:

                            //Si no esta logeado, NOTAUTH
                            if(this.estadoCliente != 0){

                                //Si la cola existe y no esta vacia, devuelve el mensaje mas antiguo
                                if(colasMensajes.compruebaColaExiste(me.getIdCola()) &&
                                        !(colasMensajes.getCola(me.getIdCola()).isEmpty())) {

                                    mensajesSacados = nSacado.incrementAndGet();
                                    ms = new MensajeProtocolo(Primitiva.MSG, colasMensajes.pop(me.getIdCola()));

                                //Si no, EMPTY
                                }else {


                                    ms = new MensajeProtocolo(Primitiva.EMPTY);


                                }
                            //No logeado
                            }else{

                                ms = new MensajeProtocolo(Primitiva.NOTAUTH, Strings.loginRequerido());


                            }

                            break;

                        //Devuelve una serie de informacion solo si el usuario es ADMIN
                        case STATE:

                                if(this.estadoCliente == 2){

                                    //Imprime la pool de hilos total, los hilos activos y los mensajes totales aniadidos y sacados de las colas
                                    ms = new MensajeProtocolo(Primitiva.STATE, Strings.estado(poolHilos.getActiveCount(), mensajesSubidos, mensajesSacados));

                                }else if(this.estadoCliente == 1){

                                    ms = new MensajeProtocolo(Primitiva.NOTAUTH, Strings.noAdmin());

                                }else{

                                    ms = new MensajeProtocolo(Primitiva.NOTAUTH, Strings.loginRequerido());

                                }

                            break;

                        /*Borra la cola indicada, si no existe o la ha borrado otro hilo en el proceso
                         *EMPTY, si no eres admin NOTAUTH, en los casos buenos DELETED
                         */
                        case DELETEQ:

                            //SI NO ERES ADMIN, NOT AUTH
                            if(this.estadoCliente == 2){

                                //Si no existe, EMPTY
                                if(colasMensajes.compruebaColaExiste(me.getIdCola())) {

                                    //Si la borra DELETED y si lo ha hecho otro hilo EMPTY
                                    if(colasMensajes.borraCola(me.getIdCola())) {

                                        ms = new MensajeProtocolo(Primitiva.DELETED);


                                    }else{

                                        ms = new MensajeProtocolo(Primitiva.EMPTY);


                                    }

                                }else {

                                    ms = new MensajeProtocolo(Primitiva.EMPTY);

                                }

                            //No admin
                            }else if(this.estadoCliente == 1){

                                ms = new MensajeProtocolo(Primitiva.NOTAUTH, Strings.noAdmin());

                            //No login
                            }else{

                                ms = new MensajeProtocolo(Primitiva.NOTAUTH, Strings.loginRequerido());

                            }


                            break;

                        //Por defecto, BADCODE
                        default:

                            ms = new MensajeProtocolo(Primitiva.BADCODE, Strings.malProtocolo());
                            break;
                    } // fin del selector segun el mensaje entrante

                    enviaCliente.writeObject(ms); //Envia el mensaje
                    enviaCliente.flush();

                    // depuracion de mensaje saliente
                    System.out.println("Sirviente: " + ns + ": [RESP: " + ms + "]" + ": [PRIM: " + ms.getPrimitiva() + "]");

                }// Fin while

            }else{

                //PRIMITIVA ERROR POR EXCESO DE CONEXIONES SIMULTANEAS
                enviaCliente.writeObject(new MensajeProtocolo(Primitiva.ERROR, Strings.mensajeErrorConexiones()));


            }//IF DE CONEXIONES

        } catch (IOException e) {
            System.err.println("Sirviente: "+ns+": [FIN]");

        } catch (ClassNotFoundException ex) {
            System.err.println("Sirviente: "+ns+": [ERR Class not found]");

        } catch (MalMensajeProtocoloException ex) {
            System.err.println("Sirviente: "+ns+": [ERR MalMensajeProtocolo !!]");

        } finally {
            // seguimos deshaciendonos de los sockets y canales abiertos.
            try {

                //Saca la ip del cliente que se va a desconectar
                String ipCliente = socket.getInetAddress().getHostAddress();

                //Baja uno al contador de conexiones simultaneas
                conexiones.clienteDesconectado(ipCliente);
                conexiones.imprimirIntentosConexion(ipCliente);

                //Cierra todo
                leeCliente.close();
                enviaCliente.close();
                socket.close();
            } catch (Exception x) {
                System.err.println("Sirviente: "+ns+": [ERR Cerrando sockets]");
            }
        }
    }
}