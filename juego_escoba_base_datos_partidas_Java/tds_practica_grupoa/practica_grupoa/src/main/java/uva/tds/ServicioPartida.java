package uva.tds;

/**
 * Servicio para gestionar partidas de un juego de cartas,
 * accedendo a un repositorio de partidas para almacenar y recuperar partidas.
 * Utiliza la clase GestorPartida para validar partidas y obtener estadisticas.
 * @author Angel Antolin Caramazana
 * @author Marcos Gomez Medina
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServicioPartida {
    

    private IRepositorioPartida repositorioPartida;
    
    /**
     * Establece el repositorio de partidas a utilizar
     * @param repositorioPartida repositorio de partidas
     */
    public void setRepositorioPartida(IRepositorioPartida repositorioPartida){
        
        if(repositorioPartida==null){
            throw new IllegalArgumentException("El repositorio de partidas no puede ser nulo");
        }
        this.repositorioPartida = repositorioPartida;
    }

    /**
     * Comprueba si un objeto es nulo
     * @param obj objeto a comprobar
     * @throws IllegalArgumentException si el objeto es nulo
     */
    private void esNull(Object obj){
        if(obj==null){
            throw new IllegalArgumentException("El objeto no puede ser nulo");
        }
    }

    /**
     * Obtiene el jugador 1 de una partida
     * @param p partida
     * @return jugador 1 de la partida
     */
    public Jugador getJugador1Partida(Partida p){

        esNull(p);
        return new Jugador(p.getNombreJugador1());
        

    }

    /**
     * Obtiene el jugador 2 de una partida
     * @param p partida
     * @return jugador 2 de la partida
     */
    public Jugador getJugador2Partida(Partida p){

        esNull(p);
        return new Jugador(p.getNombreJugador2());
        

    }

    /**
     * Valida una partida completa reproduciendo todas las rondas y jugadas
     * Comprueba que las puntuaciones finales coincidan con las obtenidas en la partida
     * @param p partida a validar
     * @return true si la partida es valida, false en caso contrario
     */
    public boolean validarPartida(Partida p){

        esNull(p);

        GestorPartida gestor=new GestorPartida(new Baraja(), getJugador1Partida(p), getJugador2Partida(p));

        gestor.repartoCartasMesa(p.getRondas().get(0).getMesaInicial());

        for(Ronda rondita: p.getRondas()){

            
            gestor.repartoCartasJugador(rondita.getManoJugador1(), 1);
            gestor.repartoCartasJugador(rondita.getManoJugador2(), 2);

            for(Jugada jugaditas: rondita.getJugadas()){

                gestor.jugarCarta(jugaditas.getCartaJugada());

                if(!jugaditas.getCartasCapturadas().isEmpty()){

                    gestor.capturarCartas(jugaditas.getCartasCapturadas());
                }


            }


        }
        
        gestor.finalizarJuego();
        gestor.puntuacionFinal();

        return comprobarPuntuaciones(p, gestor);

    }

    /**
     * Guarda una partida en el repositorio
     * @param p partida a guardar
     * @throws Exception si ocurre un error al guardar la partida en el repositorio
     */
    public void guardarPartida(Partida p){

        esNull(repositorioPartida);

        this.repositorioPartida.guardar(p);

        
    }

    /**
     * Actualiza una partida en el repositorio
     * @param p partida a actualizar
     * @throws Exception si ocurre un error al actualizar la partida en el repositorio
     */
    public void actualizarPartida(Partida p){

        esNull(repositorioPartida);
        
        this.repositorioPartida.actualizar(p);

        

    }

    /**
     * Carga una partida del repositorio
     * @param id string identificador de la partida a cargar
     * @return partida cargada
     * @throws Exception si ocurre un error al cargar la partida del repositorio
     */
    public Partida cargarPartida(String id){

        esNull(repositorioPartida);
        
        return repositorioPartida.cargar(id);
            
    
    }

    /**
     * Elimina una partida del repositorio
     * @param id identificador de la partida a eliminar
     * @throws Exception si ocurre un error al eliminar la partida del repositorio
     */
    public void eliminarPartida(String id){

        esNull(repositorioPartida);
        
        this.repositorioPartida.eliminar(id);

        


    }

    /**
     * Obtiene las partidas de un jugador del repositorio
     * @param nombre string nombre del jugador
     * @return lista de partidas del jugador
     * @throws Exception si ocurre un error al obtener las partidas del repositorio
     */
    public List<Partida> obtenerPartidasJugador(String nombre){

        esNull(repositorioPartida);
    
        return this.repositorioPartida.obtenerPartidasPorJugador(nombre);
            
        
    }

    /**
     * Obtiene las partidas entre dos fechas del repositorio
     * @param fechaInicial string fecha inicial (AAAA-MM-DD)
     * @param fechaFinal string fecha final (AAAA-MM-DD)
     * @return lista de partidas entre las dos fechas
     * @throws Exception si ocurre un error al obtener las partidas del repositorio
     */
    public List<Partida> obtenerPartidasFecha(String fechaInicial,String fechaFinal){

        esNull(repositorioPartida);
        
        return this.repositorioPartida.obtenerPartidasPorFecha(fechaInicial,fechaFinal);

    }

    /**
     * Comprueba que las puntuaciones de una partida coincidan con las obtenidas en el juego
     * @param partida partida a comprobar
     * @return true si las puntuaciones coinciden, false en caso contrario
     */
    public boolean comprobarPuntuaciones(Partida partida, GestorPartida gestor){

        boolean puntuacionesCorrectas=true;
        Jugador jugador1=gestor.getJugador1();
        Jugador jugador2=gestor.getJugador2();

        if(partida.getResumenJugador1().get(0) != jugador1.getNumeroEscobas()){
        
            puntuacionesCorrectas=false;
        }
        if(partida.getResumenJugador1().get(1) != jugador1.getCartasObtenidas().size()){
            puntuacionesCorrectas=false;
        }
        if(partida.getResumenJugador1().get(2) != gestor.contarOros(jugador1.getCartasObtenidas())){
            puntuacionesCorrectas=false;
        }
        if(partida.getResumenJugador1().get(3) != gestor.contarSietes(jugador1.getCartasObtenidas())){
            puntuacionesCorrectas=false;
        
        }
        if(partida.getResumenJugador1().get(4)!=jugador1.getGuindis()){
            puntuacionesCorrectas=false;
        }

        if(partida.getResumenJugador1().get(5)!=jugador1.getPuntos()){
            puntuacionesCorrectas=false;
        }

        if(partida.getResumenJugador2().get(0)!=jugador2.getNumeroEscobas()){
            puntuacionesCorrectas=false;
        }
        if(partida.getResumenJugador2().get(1)!=jugador2.getCartasObtenidas().size()){
            puntuacionesCorrectas=false;
        }
        if(partida.getResumenJugador2().get(2)!= (10 - gestor.contarOros(jugador1.getCartasObtenidas()))){
            puntuacionesCorrectas=false;
        }
        if(partida.getResumenJugador2().get(3)!=(4-gestor.contarSietes(jugador1.getCartasObtenidas()))){
            puntuacionesCorrectas=false;
        
        }
        if(partida.getResumenJugador2().get(4)!=jugador2.getGuindis()){
            puntuacionesCorrectas=false;
        }
        if(partida.getResumenJugador2().get(5)!=jugador2.getPuntos()){
            puntuacionesCorrectas=false;
        }
        
        return puntuacionesCorrectas;
        
    }

    /**
     * Obtiene las estadisticas de partidas entre dos fechas
     * @param fechaInicial fecha inicial (AAAA-MM-DD)
     * @param fechaFinal fecha final (AAAA-MM-DD)
     * @return array de enteros con las estadisticas: [0] numero de partidas, [1] numero de jugadores distintos
     * @throws IllegalStateException si el repositorio de partidas no ha sido establecido
     */
    public int[] obtenerEstadisticasEntreFechas(String fechaInicial,String fechaFinal){

        if (repositorioPartida == null) {
            throw new IllegalStateException();
        }

        int[]stats=new int[2];

        stats[0]=this.repositorioPartida.obtenerPartidasPorFecha(fechaInicial, fechaFinal).size();
        stats[1]=obtenerNumeroJugadores(this.repositorioPartida.obtenerPartidasPorFecha(fechaInicial, fechaFinal));

        return stats;

    }

    /**
     * Obtiene el numero de jugadores distintos en una lista de partidas
     * @param lista lista de partidas
     * @return numero de jugadores distintos
     */
    public int obtenerNumeroJugadores(List<Partida> lista){

        esNull(lista);
        List<String> jugadores=new ArrayList<>();

        for(Partida partidita: lista){
            if(!jugadores.contains(partidita.getNombreJugador1())){
                jugadores.add(partidita.getNombreJugador1());
            }
            if(!jugadores.contains(partidita.getNombreJugador2())){
                jugadores.add(partidita.getNombreJugador2());
            }
        }

        return jugadores.size();

    }


    /**
     * Obtiene las estadisticas de un jugador
     * @param nombreJugador string nombre del jugador
     * @return array de enteros con las estadisticas: [0] partidas totales, [1] partidas ganadas, [2] partidas perdidas, [3] partidas con guindis
     * @throws IllegalStateException si el repositorio de partidas no ha sido establecido
     */
    public int[] obtenerEstadisticasJugador(String nombreJugador){

    
        if (repositorioPartida == null) {
            throw new IllegalStateException();
        }


        //Partidas totales, ganadas, perdidas,numero de partidas con guindis

        int[]stats= new int[4];


        stats[0]=obtenerPartidasJugador(this.repositorioPartida.obtenerPartidasPorJugador(nombreJugador),nombreJugador);
        stats[1]=obtenerGanadasJugador(this.repositorioPartida.obtenerPartidasPorJugador(nombreJugador),nombreJugador);
        stats[2]=stats[0]-stats[1];
        stats[3]=obtenerPartidasJugadorConGuindis(this.repositorioPartida.obtenerPartidasPorJugador(nombreJugador),nombreJugador);
            
        return stats;


    }

    /**
     * Obtiene el numero de partidas jugadas por un jugador en una lista de partidas
     * @param lista lista de partidas
     * @param nombre string nombre del jugador
     * @return numero de partidas jugadas por el jugador
     */
    public int obtenerPartidasJugador(List<Partida> lista,String nombre){

        esNull(lista);
        
        int contador=0;

        for(Partida partidita: lista){
            if(jugadorEnPartida(partidita, nombre)){
                contador++;
            }
        }

        return contador;

    }

    /**
     * Comprueba si un jugador esta en una partida
     * @param p partida
     * @param nombre nombre del jugador
     * @return true si el jugador esta en la partida, false en caso contrario
     */
    public boolean jugadorEnPartida(Partida p, String nombre){

        return p.getNombreJugador1().equals(nombre)||p.getNombreJugador2().equals(nombre);
    }



    /**
     * Obtiene el numero de partidas ganadas por un jugador en una lista de partidas
     * @param lista lista de partidas
     * @param nombreJugador string nombre del jugador
     * @return numero de partidas ganadas por el jugador
     */
    public int obtenerGanadasJugador(List<Partida> lista, String nombreJugador){


        int contador=0;

        esNull(lista);


        for(Partida partidita: lista){
            
            if(partidita.getGanador().equals(nombreJugador)){
                contador++;
            }

        }


        return contador;


    }


    /**
     * Obtiene el numero de partidas en las que un jugador obtuvo el guindis en una lista de partidas
     * @param lista lista de partidas
     * @param nombre string nombre del jugador
     * @return numero de partidas con guindis del jugador
     */
    public int obtenerPartidasJugadorConGuindis(List<Partida> lista,String nombre){


        int contador=0;

        for(Partida partidita: lista){


            if(partidita.getNombreJugador1().equals(nombre) && partidita.getResumenJugador1().get(4)==1){

                contador++;
            }

            if(partidita.getNombreJugador2().equals(nombre)&&partidita.getResumenJugador2().get(4)==1){

                contador++;
            }

        }

        return contador;

    }

    /**
     * Obtiene el top 10 de jugadores con mas victorias en el historico de partidas
     * @return lista de strings con el top 10 de jugadores y sus victorias
     * @throws IllegalStateException si el repositorio de partidas no ha sido establecido
     */
    public List<String> obtenerTop10(){

        esNull(repositorioPartida);
        List<Partida> historicoPartidas=obtenerPartidasFecha("2000-01-01", "2100-11-26");
        
        Map<String, Integer> ganadores = new HashMap<>();
        int top;
        List<String> top10 = new ArrayList<>();

        for(Partida partidita: historicoPartidas){

            ganadores.put(partidita.getGanador(), ganadores.getOrDefault(partidita.getGanador(), 0) + 1);

        }

        List<Map.Entry<String,Integer>> topTotal = new ArrayList<>(ganadores.entrySet());

        topTotal.sort((a,b) -> b.getValue() - a.getValue());

        top = Math.min(10, topTotal.size());

        for(int i = 0; i < top; i++){

            Map.Entry<String,Integer> entrada = topTotal.get(i);

            top10.add(entrada.getKey() + " - " + entrada.getValue() + " victorias");

        }

        return top10;

    }
}
