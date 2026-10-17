/**
 * @author Angel Antolin Caramazana, Alejandro Garcia Lanvadera, Manuel Arribas Martinez, Marcos Gomez Medina
 */
package sdis.brokermsg.tcp.common;

public enum Primitiva {
    INFO,   // Da la bienvenida al usuario
    XAUTH,   //Permite al cliente logearse y si ha ido bien el servidor responde con ella
    ADDMSG, //Aniade mensajes a las colas o las crea en caso de que no existan y el usuario se ADMIN
    ADDED, //El sevidor confirma que el mensaje ha sido aniadido a la cola
    READQ, //Consume el valor mas antiguo de una cola siempre y cuando esta exista
    MSG,  //Devuelve al Cliente el mensaje que READQ retiro de la cola
    EMPTY, //Servidor comunica que la cola no existe o esta vacia
    STATE, //Devuelve informacion que solo puede ser vista por el usuario ADMIN
    DELETEQ, //Borra la cola especificada, solo ADMIN
    DELETED, //Servidor confirma que la cola ha sido borrada
    NOTAUTH, //Multiuso para fallo de login o permisos insuficientes
    ERROR, //Error por fallos de login o conexiones simultaneas
    BADCODE; //Intercambio ilegal de protocolo
}
