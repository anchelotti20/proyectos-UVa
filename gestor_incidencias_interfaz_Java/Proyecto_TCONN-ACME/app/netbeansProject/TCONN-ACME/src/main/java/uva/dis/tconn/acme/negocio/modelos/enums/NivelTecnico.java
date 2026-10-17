package uva.dis.tconn.acme.negocio.modelos.enums;

/**
 * Enum con los diferentes niveles de tecnico correspondientes
 * @author Marcos Gomez
 * @author Angel Antolin
 * @author Elisa Martinez
 */
public enum NivelTecnico {
    BASICO("basico"),
    AVANZADO("avanzado"),
    ESPECIALIZADO("especializado");

    private final String valorBD;

    // Constructor privado del Enum
    NivelTecnico(String valorBD) {
        this.valorBD = valorBD;
    }

    /**
     * Convierte el texto exacto de la base de datos en su constante Enum correspondiente.
     */
    public static NivelTecnico desdeBD(String texto) {
        if (texto == null) return null;
        
        for (NivelTecnico n : NivelTecnico.values()) {
            if (n.valorBD.equals(texto)) {
                return n;
            }
        }
        throw new IllegalArgumentException("Nivel técnico no reconocido en BD: " + texto);
    }
    
    public String getValorBD(){
        
        return this.valorBD;
    }
}