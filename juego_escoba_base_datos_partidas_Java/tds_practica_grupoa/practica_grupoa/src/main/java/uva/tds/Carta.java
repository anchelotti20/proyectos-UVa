package uva.tds;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Clase que representa una carta
 * Cada carta tiene un numero del 1 al 40 y un valor asociado
 * valor: valor numerico asociado a la carta
 * numeroCarta: entero que representa el numero de la carta del 1 al 40
 * @author Marcos Gomez Medina
 * @author Angel Antolin Caramazana
 */

@Entity
@Table(name = "Cartas")
public class Carta {

    //Refactorizacion "Replace with Magic Number"
    private int valorSota=8;
    private int valorCaballo=9;
    private int valorRey=10;

    @Id
    @Column(name = "numero_carta", unique = true, nullable = false)
    private int numeroCarta;

    @Column(name = "valor", nullable = false)
    private int valor;

    
    /**
     * Constructor de la clase Carta
     * Inicializa una carta con un numero del 1 al 40, asignando su palo, valor y nombre correspondiente
     * @param numeroCarta numero de la carta del 1 al 40
     * @throws IllegalArgumentException si el numero de carta no es valido (no esta entre 1 y 40)
     */
    public Carta(int numeroCarta){

        if(numeroCarta <=0 || numeroCarta>40){
            throw new IllegalArgumentException("El numero de carta no es valido");

        }
        
        switch(numeroCarta){

        case 8,18,28,38:
            this.valor=valorSota;
            break;
            
        case 9,19,29,39:
            this.valor=valorCaballo;
            break;
        
        case 10,20,30,40:
            this.valor=valorRey;
            break;

        default:
            this.valor=numeroCarta%10;    
            break;
            
        }

        this.numeroCarta=numeroCarta;

    }

    public Carta(){
    }

    /**
     * Obtiene el valor asociado a una carta
     * @return valor, entero asociado a la carta
     */
    public int getValor(){
        return this.valor;
    }


    /**
     * Obtiene el numero de carta que es representado por la carta [1,40]
     * @return entero, numero de la Carta
     */
    public int getNumeroCarta(){
        return this.numeroCarta;
    }
    
    /**
     * Compara si dos cartas son iguales basandose en su numero de carta
     * @param obj objeto a comparar
     * @return true si las cartas son iguales, false en caso contrario
     */
    @Override
    public boolean equals (Object obj){

        if(this==obj){
            return true;
        }

        if(obj==null||getClass()!=obj.getClass()){
            return false;
        }

        Carta otraCarta=(Carta)obj;
        return this.numeroCarta==otraCarta.numeroCarta;

    }

    /**
     * Genera un codigo hash basado en el numero de carta
     * @return entero que representa el codigo hash de la carta
     */
    @Override
    public int hashCode(){
        return Integer.hashCode(this.numeroCarta);
    }
    


}