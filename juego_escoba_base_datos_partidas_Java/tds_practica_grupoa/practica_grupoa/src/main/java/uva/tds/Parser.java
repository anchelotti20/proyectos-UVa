package uva.tds;

/**
 * Clase que parsea cartas desde su representacion en JSON (1-oros, 11-copas, etc.) a objetos Carta
 * @author Angel Antolin Caramazana
 * @author Marcos Gomez Medina
 */

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;


public class Parser{

    /**
     * Parsea una carta desde su representacion en JSON (numero-palo) al objeto Carta
     * @param cartaJSON String con la representacion en JSON de la carta
     * @return Carta objeto Carta parseado desde el JSON
     * @throws IllegalArgumentException si el String es nulo
     */
    public Carta parseaCartaJSON(String cartaJSON){

        esNull(cartaJSON);

        Carta carta = null;

        String [] partes = cartaJSON.split("-");

        int numeroCartaJSON = Integer.parseInt(partes[0]);
        
        switch (partes[1]) {
            case "oros":

                if(numeroCartaJSON < 10){

                    carta = new Carta(numeroCartaJSON);

                }else{

                    numeroCartaJSON -= 2;
                    carta = new Carta(numeroCartaJSON);
                }

                break;
            case "copas":

                if(numeroCartaJSON < 10){

                    numeroCartaJSON += 10;
                    carta = new Carta(numeroCartaJSON);
                }else{

                    numeroCartaJSON += 8;
                    carta = new Carta(numeroCartaJSON);
                }
                break;

            case "espadas":
                if(numeroCartaJSON < 10){

                    numeroCartaJSON += 20;
                    carta = new Carta(numeroCartaJSON);
                }else{
                    numeroCartaJSON += 18;
                    carta = new Carta(numeroCartaJSON);
                }
                break;
            case "bastos":
                if(numeroCartaJSON < 10){

                    numeroCartaJSON += 30;
                    carta = new Carta(numeroCartaJSON);
                }else{
                    numeroCartaJSON += 28;
                    carta = new Carta(numeroCartaJSON);
                }
                break;
            default:
                throw new IllegalArgumentException("Palo no valido en el JSON");
        }

        return carta; 

    }

    /**
     * Obtiene las cartas en su representacion en JSON y las parsea a objetos Carta y
     * las guarda en una lista
     * @param cartasJSON JSONArray con la representacion en JSON de las cartas
     * @return List<Carta> lista de objetos Carta parseados desde el JSON
     * @throws IllegalArgumentException si el JSONArray es nulo
     */
    public List<Carta> parsearCartasDesdeJSON(JSONArray cartasJSON){

        esNull(cartasJSON);

        List<Carta> listaCartas = new ArrayList<>();

        for(int i=0; i < cartasJSON.length(); i++){

            String cartaStr = cartasJSON.getString(i);
            Carta carta = parseaCartaJSON(cartaStr);
            listaCartas.add(carta);

        }

        return listaCartas;

    }

    /**
     * Parsea una lista de strings con la representacion en JSON de las cartas a una lista de objetos Carta
     * @param listaStrings List<String> con la representacion en JSON de las cartas
     * @return List<Carta> lista de objetos Carta parseados desde el JSON
     * @throws IllegalArgumentException si la lista de strings es nula
     */
    public List<Carta> parsea(List<String> listaStrings){

        esNull(listaStrings);

        List<Carta> listaCartas= new ArrayList<>();

        for(int i=0;i<listaStrings.size();i++){

            String cartaStr=listaStrings.get(i);
            Carta carta=parseaCartaJSON(cartaStr);
            listaCartas.add(carta);

        }

        return listaCartas;


    }


    /**
     * Comprueba si el objeto es nulo
     * @param obj
     * @throws IllegalArgumentException si es nulo.
     */

    private void esNull(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("El parámetro no puede ser nulo.");
        }
        
    }




}