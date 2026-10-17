/**
 * @author Angel Antolin Caramazana, Alejandro Garcia Lanvadera, Manuel Arribas Martinez, Marcos Gomez Medina
 */
package sdis.brokermsg.tcp.server;

import java.util.concurrent.ConcurrentHashMap;

//Guarda en un ConcurrentHashMap los pares IP-INTENTOS para el control de conexiones e inicio de sesion
public class BlackListManager {

    //HashMap que guarda los pares
    private final ConcurrentHashMap<String, Integer> intentos;

    //Constructor de la clase
    public BlackListManager() {
        intentos = new ConcurrentHashMap<>();
    }

    //Devuelve el HashMap
    public ConcurrentHashMap<String, Integer> getIntentos() {
        return this.intentos;
    }

    /*Registra un intento para la ip pasada como argumento, si no estaba guardada
    * lo inicia en 1, si si que estaba, le suma 1 al valor que tuviera
    */
    public void registrarIntento(String ip){
        intentos.merge(ip, 1, Integer::sum);
    }

    //Reduce en uno el contador de conexiones simultaneas para una IP cuando un cliente se desconecta
    public void clienteDesconectado(String ip){
        intentos.put(ip, intentos.get(ip) - 1);
    }

    //En caso de login correcto, reinicia el contador de errores de login de la IP
    public void loginCorrecto(String ip){
        intentos.put(ip, 0);
    }

    //Mensaje de debug de los intentos de login
    public void imprimirIntentosLogin(String ip){
        int numeroIntentos = intentos.get(ip);
        System.out.println("[BM] (login fails) for IP /" + ip + " = " + numeroIntentos);
    }

    //Mensaje de debug de las conexiones simultaneas de la IP
    public void imprimirIntentosConexion(String ip){
        int numeroIntentos = intentos.get(ip);
        System.out.println("[BM] (connections) for IP /" + ip + " = " + numeroIntentos);
    }
}