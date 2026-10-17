package sdis.brokermsg.rmi.server;

import sdis.brokermsg.rmi.common.Authenticator;
import sdis.brokermsg.tcp.server.GsonCredenciales;

import java.rmi.RemoteException;
import java.util.concurrent.ConcurrentHashMap;
import sdis.utils.Digest;


/**
 * Implementación remota del autenticador que permite a los usuarios
 * iniciar sesión (obtener un token), cerrar sesión y validar tokens.
 */
public class AuthenticatorImpl extends java.rmi.server.UnicastRemoteObject implements Authenticator {

    //HasMap con los usuarios registrados
    private  static final ConcurrentHashMap<String,String> listaUsuarios = GsonCredenciales.cargarUsuarios();
    //HasMap del token asociado al usuario
    private  final ConcurrentHashMap<String, String> tokens;
    private  Digest miMD;

    //Inicializa el Authenticator con el algoritmo usado
    public AuthenticatorImpl(String algoritmoHash) throws java.rmi.RemoteException{
        super();//constructor del UniCastRemoteObject
        this.tokens = new ConcurrentHashMap<>();
        this.miMD = new Digest(algoritmoHash);

    }

    /**
     * Metodo remoto para autenticar al usuario.
     * Si la autenticación es correcta, devuelve un token único.
     * Si falla, devuelve [null]
     */
    public String connect(String usuario, String password) throws java.rmi.RemoteException{

        // Recogemos los datos necesarios
        String passwd = listaUsuarios.get(usuario);

        //Comprueba que exista el usuario y coincida con la contrasenia introducida
        if(passwd != null && passwd.equals(password)){

            String token = miMD.generateHash(usuario + password);//Genera token
            tokens.put(token, usuario);//Pone token en el HashMap
            return token;

        }else{
            System.err.println("> Server [RMI]: Usuario o contraseña incorrectos");
            return null;
        }


    }


    /**
     * Metodo remoto para desconectar (eliminar) una sesión de usuario.
     */

    public void disconnect(String token) throws RemoteException {
        // Borramos el token del usuario
        String usuario = tokens.remove(token);

        // Mostramos feedback
        if(usuario != null){
            System.out.println("> Server [RMI]: Usuario eliminado: [" + usuario + "]");
        }else{
            System.err.println("> Server [RMI]: Usuario no encontrado");
        }
    }



    /**
     * Metodo remoto para verificar si un token es válido.
     */
    public boolean checkToken(String token) throws java.rmi.RemoteException{
        // Mostramos feedback
        System.out.println("> Server: Comprobando el token [" + token + "]");
        return tokens.containsKey(token);
    }




    public String getUsuario(String token) throws java.rmi.RemoteException{
        String usuario = tokens.get(token);
        return usuario;
    }

}
