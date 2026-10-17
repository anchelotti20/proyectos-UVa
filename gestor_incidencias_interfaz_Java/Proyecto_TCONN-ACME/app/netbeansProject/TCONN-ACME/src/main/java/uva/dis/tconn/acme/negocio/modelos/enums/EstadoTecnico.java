package uva.dis.tconn.acme.negocio.modelos.enums;

/**
 * Enum con los diferentes estados posibles de los tecnicos
 * @author Marcos Gomez Medina
 * @author Angel Antolin
 * @author Elisa Martinez
 */
public enum EstadoTecnico {
    ASIGNADO("asignado"),
    DISPONIBLE("disponible"),
    NO_DISPONIBLE("noDisponible"); // Fíjate cómo mapeamos el camelCase de la BD

    private final String valorBD;

    // Constructor privado del Enum
    EstadoTecnico(String valorBD) {
        this.valorBD = valorBD;
    }

    /**
     * Convierte el texto exacto de la base de datos en su constante Enum correspondiente.
     */
    public static EstadoTecnico desdeBD(String texto) {
        if (texto == null) return null;
        
        for (EstadoTecnico e : EstadoTecnico.values()) {
            if (e.valorBD.equals(texto)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Estado técnico no reconocido en BD: " + texto);
    }
    
    public String getValorBD(){
        
        return this.valorBD;
        
    }
}