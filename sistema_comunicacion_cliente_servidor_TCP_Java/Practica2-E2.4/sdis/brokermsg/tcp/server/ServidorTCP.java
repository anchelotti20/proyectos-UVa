/**
 * @author Angel Antolin Caramazana, Alejandro Garcia Lanvadera, Manuel Arribas Martinez, Marcos Gomez Medina
 */
package sdis.brokermsg.tcp.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import sdis.brokermsg.server.ServerBroker;
import sdis.utils.MultiMap;

//Servidor, se encarga de crear la POOL hilos y llamar a los hilos sirviente con cada cliente que se conecta
public class ServidorTCP {

    public void lanzarServerTCP(MultiMap<String, String> colas){
        // Maximo 5 hilos (uno para cada cliente)
        int NThreads = 5;
        //Numero de puerto
        int PUERTO = 2000;

        // MultiMapa cola de mensajes
        //MultiMap<String, String> mapa = new MultiMap<>();

        // Executor
        java.util.concurrent.ExecutorService exec = java.util.concurrent.Executors.newFixedThreadPool(NThreads);

        try {
            // Creamos el socket del servidor
            ServerSocket servidor  = new ServerSocket(PUERTO);

            Thread mainServer = new Thread(() -> {

                try{
                    // Bucle principal del servidor
                    while(true){
                        // Cliente conectado correctamente
                        Socket socket = servidor.accept();

                        try{
                            //Crea el sirviente, le pasa el socket, el mapa y el exec (para poder acceder al numero de hilos activos)
                            Sirviente serv = new Sirviente(socket, colas, exec);
                            exec.execute(serv);

                        } catch (java.io.IOException ioe) {
                            System.err.println("Servidor: WHILE [ERR ObjectStreams]");
                        }
                    }

                }catch (IOException e){
                    System.err.println("Servidor: WHILE [Error.E/S]");

                }catch (Exception e){
                    System.err.println("Servidor: WHILE [Error.execute]");

                }

            }, "RUN (WHILE)"); // fin-newThread separado para el servidor
            mainServer.start();
            System.out.println("---Server waiting For Client-----");

        }catch (IOException ioe){
            System.err.println("Servidor: [ERR SOKET]");
        }
    }
}