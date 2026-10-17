/**
 * @author Angel Antolin Caramazana, Alejandro Garcia Lanvadera, Manuel Arribas Martinez, Marcos Gomez Medina
 */
package sdis.brokermsg.tcp.common;

//Guarda los mensajes que el servidor comunica con el usuario
public class Strings {
    public static String mensajeBienvenida(){
        return "Welcome, please type your credentials to LOG in";
    }

    public static String mensajeErrorLogin(){
        return "Err Max Number of login attempts reached.";
    }

    public static String mensajeErrorConexiones(){
        return "Err Max Number of connections reached.";
    }

    public static String mensajeReLogin(){
        return "Err 401 ~ Credentials DO NOT MATCH. Try again.";
    }

    public static String mensajeLoginCorrecto(){
        return "User successfully logged in";
    }

    public static String loginRequerido(){
        return "User login is required";
    }

    public static String noAdmin(){
        return "NO ADMIN";
    }


    public static String estado(int hilosEnUso, int mensajesSubidos, int mensajesSacados){
        return "<5 hilos max> : <" + hilosEnUso + " hilos ocupados> : <"+ mensajesSubidos +" msg pushed> : <"+ mensajesSacados + "msg pulled>";
    }

    public static String malProtocolo(){
        return "Ilegal primitive type";
    }
}
