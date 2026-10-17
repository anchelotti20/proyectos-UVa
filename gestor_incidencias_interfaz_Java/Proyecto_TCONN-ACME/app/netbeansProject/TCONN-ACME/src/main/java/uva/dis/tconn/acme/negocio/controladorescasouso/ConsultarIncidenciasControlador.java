/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.dis.tconn.acme.negocio.controladorescasouso;

import uva.dis.tconn.acme.servicioscomunes.excepciones.NoHayIncidenciasException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import uva.dis.tconn.acme.negocio.modelos.enums.EstadoIncidencia;
import uva.dis.tconn.acme.persistencia.daos.IncidenciaDAO;
import uva.dis.tconn.acme.servicioscomunes.excepciones.ErrorSistemaException;
import uva.dis.tconn.acme.servicioscomunes.excepciones.TiempoInvalidoException;

/**
 *
 * Controlador para el caso de uso Cosnsultar Incidencias
 * @author Marcos Gomez
 * @author Angel Antolin
 * @author Elisa Martinez
 */
public class ConsultarIncidenciasControlador {
    
    /**
     * Permite obtener una lista de las incidencias en formato String
     * @param fecha de tipo LocalDate
     * @param filtros siendo una lista de EstadoIncidencia, si es nula se consideran todos los estdos
     * @return lista, conteniendo las incidencias en orrden ascendente segun hora
     * @throws TiempoInvalidoException si el tiempo no es valido(tiempo futuro)
     * @throws ErrorSistemaException
     * @throws IOException
     * @throws NoHayIncidenciasException si la lista esta vacia
     * @throws IllegalArgumentException si  si fecha es nula
     * @throws IllegalArgumentException si filtros es nulo
     */
    public List<String> obtenerIncidencas(LocalDate fecha,List<EstadoIncidencia> filtros) throws TiempoInvalidoException, ErrorSistemaException, NoHayIncidenciasException{
        
        if(fecha==null){
            throw new IllegalArgumentException("La fecha no puede ser nula.");
        }
        
        if(fecha.isAfter(LocalDate.now())){
            throw new TiempoInvalidoException("La fecha es posterior a la fecha actual.");
        }
        
        List<String> listaIncidencias=IncidenciaDAO.obtenerIncidenciasSegunFecha(fecha,filtros);
        
        if(listaIncidencias.isEmpty()){
            throw new NoHayIncidenciasException("No hay incidencias registradas");
        }
        
        return listaIncidencias;
    }
     
}
