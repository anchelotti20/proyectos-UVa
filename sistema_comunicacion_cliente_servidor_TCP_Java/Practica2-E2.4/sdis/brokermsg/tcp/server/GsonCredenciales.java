/**
 * @author Angel Antolin Caramazana, Alejandro Garcia Lanvadera, Manuel Arribas Martinez, Marcos Gomez Medina
 */
package sdis.brokermsg.tcp.server;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

// Lee del archivo json para tratar los datos almacenados
public class GsonCredenciales {

    //Variable que almacena el archivo JSON
    private static final Gson gson = new Gson();

    //Carga el contenido del archivo en un HashMap para ser comprobado por el Sirviente
    public static ConcurrentHashMap<String,String> cargarUsuarios() {

        try{
            //Lee el archivo
            java.io.InputStreamReader lector = new java.io.InputStreamReader(new java.io.FileInputStream(("./credenciales.json")));
            //Guarda el contenido del archivo en el HashMap
            ConcurrentHashMap<String, String> usuarios = new Gson().fromJson(lector, new TypeToken<ConcurrentHashMap<String,String>>(){}.getType());
            lector.close();
            return usuarios;

        }catch (IOException e){
            System.out.println("Error al leer el archivo" + e.getMessage());
            return new ConcurrentHashMap<String, String>();
        }
    }
}
