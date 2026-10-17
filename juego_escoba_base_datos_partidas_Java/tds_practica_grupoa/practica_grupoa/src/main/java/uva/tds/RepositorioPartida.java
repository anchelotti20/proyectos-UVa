package uva.tds;


/**
 * Implementacion de IRepositorioPartida usando Hibernate para la persistencia de datos.
 * @author Marcos Gomez Medina
 * @author Angel Antolin Caramazana
 */

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;



import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class RepositorioPartida implements IRepositorioPartida {

    private String configFile;

    /**
     * Constructor de la clase RepositorioPartida
     * @param configFile archivo de configuracion de Hibernate
     */
    public RepositorioPartida(String configFile) {
        this.configFile = configFile;
    }

    /**
     * Verifica si un objeto es nulo
     * @param obj objeto a verificar
     * @throws IllegalArgumentException si el objeto es nulo
     */
    private void esNull(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("El objeto no puede ser nulo");
        }
    }

    /**
     * Verifica si un string es vacio
     * @param str string a verificar
     * @throws IllegalArgumentException si el string es vacio
     */
    private void esStringVacio(String str) {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("El string no puede ser nulo o vacío");
        }
    }

    /**
     * Obtiene la sesion de Hibernate
     * @return sesion de Hibernate
     */
    private Session getSession() {
        
        SessionFactory factory = HibernateUtil.getSessionFactory(this.configFile);
        Session session;

        try{

            session = factory.getCurrentSession();
            return session;
        }catch (HibernateException e) {

            
            return null;
        }
        

        
    }

    /**
     * Guarda una partida en la base de datos
     * @param partida partida a guardar
     * @throws IllegalArgumentException si la partida es nula
     * @throws IllegalStateException si ya existe una partida con el mismo identificador
     */
    @Override
    public void guardar(Partida partida) {
        
        esNull(partida);

        Session session = getSession();

        
        if(session != null) {
            
            try{

                session.beginTransaction();

                Partida partidaExistente = session.get(Partida.class, partida.getIdPartida());
                if(partidaExistente != null) {
                    throw new IllegalStateException("Ya existe una partida con el mismo identificador");
                }


                session.merge(partida);

                session.flush();
                
            }catch (HibernateException e) {
                
                session.getTransaction().rollback();

            } finally {

                session.close();
            }

        }

    }

    /**
     * Actualiza una partida en la base de datos
     * @param partida partida a actualizar
     * @throws IllegalArgumentException si la partida es nula
     * @throws IllegalStateException si no existe una partida con el mismo identificador
     */
    @Override
    public void actualizar(Partida partida) {
        
        esNull(partida);

        Session session = getSession();

        

        if(session != null) {
            
            try{

                session.beginTransaction();

                Partida partidaExistente = session.get(Partida.class, partida.getIdPartida());
                if(partidaExistente == null) {
                    throw new IllegalStateException("No existe una partida con el mismo identificador");
                }

                session.merge(partida);

                session.flush();
                
            }catch (HibernateException e) {
                
                
                session.getTransaction().rollback();

            } finally {

                session.close();
            }

        }

    }

    /**
     * Carga una partida de la base de datos
     * @param identificador identificador de la partida a cargar
     * @return partida cargada
     * @throws IllegalArgumentException si el identificador es nulo o vacio
     * @throws IllegalStateException si no existe una partida con el identificador dado
     */
    @Override
    public Partida cargar(String identificador) {
        
        esNull(identificador);
        esStringVacio(identificador);

        Session session = getSession();

        if(session != null) {
            
            try{

                session.beginTransaction();

                Partida partida = session.get(Partida.class, identificador);
                
                if(partida == null) {
                    throw new IllegalStateException("No existe una partida con el identificador dado");
                }

                return partida;
                
            }catch (HibernateException e) {
                
                
                session.getTransaction().rollback();

            } finally {

                session.close();
            }

        }
        return null;
    }

    /**
     * Elimina una partida de la base de datos
     * @param identificador identificador de la partida a eliminar
     * @throws IllegalArgumentException si el identificador es nulo o vacio
     * @throws IllegalStateException si no existe una partida con el identificador dado
     */
    @Override
    public void eliminar(String identificador) {
        
        esNull(identificador);
        esStringVacio(identificador);

        Session session = getSession();

        

        if(session != null) {
            
            try{

                session.beginTransaction();

                Partida partidaExistente = session.get(Partida.class, identificador);
                if(partidaExistente == null) {
                    throw new IllegalStateException("No existe una partida con el identificador dado");
                }

                session.delete(partidaExistente);

                session.getTransaction().commit();
                
            }catch (HibernateException e) {
                
                session.getTransaction().rollback();

            } finally {

                session.close();
            }

        }

    }

    /**
     * Obtiene las partidas jugadas por un jugador
     * @param nombreJugador nombre del jugador
     * @return lista de partidas jugadas por el jugador
     * @throws IllegalArgumentException si el nombre del jugador es nulo o vacio
     */
    @Override
    public List<Partida> obtenerPartidasPorJugador(String nombreJugador) {
        
        esNull(nombreJugador);
        esStringVacio(nombreJugador);

        Session session = getSession();

        if(session != null) {
            
            try{

                session.beginTransaction();

                Query<Partida> query = session.createQuery(
                    "FROM Partida p WHERE p.nombreJugador1 = :nombreJugador OR p.nombreJugador2 = :nombreJugador", Partida.class);    
                query.setParameter("nombreJugador", nombreJugador);

                return query.list();

                
            }catch (HibernateException e) {
                
                session.getTransaction().rollback();

            } finally {

                session.close();
            }

        }



        return new java.util.ArrayList<>();
    }

    /**
     * Obtiene las partidas jugadas en un rango de fechas
     * @param fechaInicial fecha inicial del rango (formato yyyy-MM-dd)
     * @param fechaFinal fecha final del rango (formato yyyy-MM-dd)
     * @return lista de partidas jugadas en el rango de fechas
     * @throws IllegalArgumentException si alguna de las fechas es nula, vacia o no valida
     */
    @Override
    public List<Partida> obtenerPartidasPorFecha(String fechaInicial, String fechaFinal) { 

        esNull(fechaFinal);
        esNull(fechaInicial);
        esStringVacio(fechaFinal);
        esStringVacio(fechaInicial);
        esFechaValida(fechaInicial);
        esFechaValida(fechaFinal);

        Session session = getSession();

        if(session != null) {
            
            try{

                session.beginTransaction();

                LocalDateTime fechaInicio = LocalDate.parse(fechaInicial).atStartOfDay();
                LocalDateTime fechaFin = LocalDate.parse(fechaFinal).atTime(LocalTime.MAX);

                Query<Partida> query = session.createQuery(
                    "FROM Partida p WHERE p.fechaHora BETWEEN :fechaInicio AND :fechaFin", Partida.class);
                query.setParameter("fechaInicio", fechaInicio);
                query.setParameter("fechaFin", fechaFin);

                return query.list();
                
            }catch (HibernateException e) {
                
                session.getTransaction().rollback();

            } finally {

                session.close();
            }

        }


        return new java.util.ArrayList<>();
    }
    
    /**
     * Verifica si una fecha es valida
     * @param fecha fecha a verificar
     * @throws IllegalArgumentException si la fecha no es valida
     */
    private void esFechaValida(String fecha) {
        
        String[] partes = fecha.split("-");

        if(partes.length != 3) {
            throw new IllegalArgumentException("La fecha no sigue el formato correcto yyyy-MM-dd");
        }

        if(partes[0].length() != 4 || partes[1].length() != 2 || partes[2].length() != 2) {
            throw new IllegalArgumentException("La fecha no sigue el formato correcto yyyy-MM-dd");
        }

        try {
            Integer.parseInt(partes[0]);
            Integer.parseInt(partes[1]);
            Integer.parseInt(partes[2]);

            if(Integer.parseInt(partes[1]) < 1 || Integer.parseInt(partes[1]) > 12) {
                throw new IllegalArgumentException("El mes de la fecha no es válido");
            }

            if(Integer.parseInt(partes[2]) < 1 || Integer.parseInt(partes[2]) > 31) {
                throw new IllegalArgumentException("El día de la fecha no es válido");
            }

        } catch (Exception e) {
            throw new IllegalArgumentException("La fecha no sigue el formato correcto yyyy-MM-dd");
        }


    }


}
