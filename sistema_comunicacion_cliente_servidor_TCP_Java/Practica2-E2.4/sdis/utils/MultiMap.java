/**
 * @author Angel Antolin Caramazana, Alejandro Garcia Lanvadera, Manuel Arribas Martinez, Marcos Gomez Medina
 */
package sdis.utils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentLinkedQueue;


//MultiMapa para las colas de mensajes, concurrente para evitar condiciones de carrera
public class MultiMap<K, T> {

    //Al hacerlo concurrente se evitan las condiciones de carrera
    private final ConcurrentMap<K, ConcurrentLinkedQueue<T>> map = new ConcurrentHashMap<K, ConcurrentLinkedQueue<T>>();

    //Devuelve la cola asociada a la clave
    public ConcurrentLinkedQueue<T> getCola(K key) {

        return map.get(key);

    }

    //Metodo que mete un mensaje "valor" en la cola "clave"

    public void push(K clave, T valor) {

        //Obtiene la cola referida a la clave
        java.util.Queue<T> cola = map.get(clave);

        if (cola == null) {

            //Crea una nueva cola y la guarda en el mapa
            ConcurrentLinkedQueue<T> nueva = new ConcurrentLinkedQueue<T>();
            ConcurrentLinkedQueue<T> previa = map.putIfAbsent(clave, nueva);

            //Si la cola ya habia sido creada por otro hilo, usa la previa
            if(previa == null){

                cola = nueva;
            }else{

                cola = previa;
            }

        }

        //aniade el valor a la cola
        cola.add(valor);
    }


    //Elimina el elemento mas antiguo de la cola
    public T pop(K clave) {

        ConcurrentLinkedQueue<T> cola = map.get(clave);

        //Si existe, elimina el elemento en la cabeza, si no, devuelve null
        if(cola != null){

            return cola.poll();
        }else{

            return  null;
        }

    }


    public boolean borraCola(K clave){

        java.util.Queue<T> cola = map.get(clave);

        if(cola != null){

            //Si la cola existia, intenta borrarla pero si otro hilo lo hace antes devuelve false
            return map.remove(clave, cola);

        }else{

            //Si no existia, devuelve false
            return false;

        }
    }

    //Comprueba si existe la cola asociada a esa clave
    public boolean compruebaColaExiste(K clave){

        java.util.Queue<T> cola = map.get(clave);

        return cola != null;

    }

    //Devuelve el primer mensaje de una cola, pero no lo elimina
    public T peekQ(K clave){
        return map.get(clave).peek();
    }

    //Devuelve un String con los nombres de las listas creadas
    public String getQueueList(){

        StringBuilder nombres = new StringBuilder();

        for(K nombreCola : map.keySet()){

            nombres.append(nombreCola).append(", ");
        }

        //Elimina la coma y el espacio sobrantes
        if (nombres.length() > 0){

            nombres.setLength(nombres.length() - 2);

        }
        return nombres.toString();
    }


}
