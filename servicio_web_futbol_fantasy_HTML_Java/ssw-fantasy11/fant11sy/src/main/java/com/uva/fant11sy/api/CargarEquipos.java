package com.uva.fant11sy.api;

import com.uva.fant11sy.daos.ClubDAO;
import com.uva.fant11sy.daos.JugadorDAO;
import com.uva.fant11sy.util.Config;
import java.io.IOException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Se encarga de conectarse a la API de Sportmonks para descargar la informacion de los equipos y jugadores.
 * @author Angel Antolin
 * @author Marcos Gomez
 * @author Jorge Hernandez
 * @author Luis Anton
 */
public class CargarEquipos {

    // Declaramos la URL de la TEMPORADA (para traer todos los equipos de golpe)
    // Usamos el include=players.player para obtener la info del jugador dentro de la plantilla del equipo
    private static final String URL_API = "https://api.sportmonks.com/v3/football/teams/seasons/25598?include=players.player&api_token=" + Config.getApiToken();

    /**
     * Descarga los datos de los clubes y delega su guardado en la base de datos a los DAOs.
     */
    public void cargarDatos() {
        try {
            // Hacemos la petición a la API
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL_API))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            
            
            // Parseamos el JSON
            JSONObject jsonResponse = new JSONObject(response.body());
            JSONArray equipos = jsonResponse.getJSONArray("data");

            for (int i = 0; i < equipos.length(); i++) {
                JSONObject equipo = equipos.getJSONObject(i);
                
                int idClub = equipo.getInt("id");
                String nombreClub = equipo.getString("name");
                String fotoClub = equipo.optString("image_path", "");

                // Guardamos el club delegando en su DAO
                ClubDAO.guardarClub(idClub, nombreClub, fotoClub);

                // Extraer plantilla
                if (equipo.has("players")) {
                    JSONArray plantilla = equipo.getJSONArray("players");
                    
                    for (int j = 0; j < plantilla.length(); j++) {
                        // En la respuesta, cada elemento de 'players' es el registro de la plantilla, que contiene a 'player'
                        JSONObject squadMember = plantilla.getJSONObject(j);
                        JSONObject jugadorData = squadMember.getJSONObject("player");
                        
                        int idJugador = jugadorData.getInt("id");
                        String nombreJugador = jugadorData.getString("common_name");
                        
                        // Traducimos la posicion (Buscamos el position_id)
                        // A veces está en el objeto player y a veces en la raíz del squadMember
                        int positionId = 0;
                        if (jugadorData.has("position_id") && !jugadorData.isNull("position_id")) {
                            positionId = jugadorData.getInt("position_id");
                        } else if (squadMember.has("position_id") && !squadMember.isNull("position_id")) {
                            positionId = squadMember.getInt("position_id");
                        }

                        String posicionStr = "Desconocida";
                        switch(positionId) {
                            case 24: posicionStr = "Portero"; break;
                            case 25: posicionStr = "Defensa"; break;
                            case 26: posicionStr = "Mediocentro"; break;
                            case 27: posicionStr = "Delantero"; break;
                        }
                        
                        // NACIONALIDAD (Guardamos el ID numérico traducido a String)
                        String nacionalidad = "Desconocida";
                        if (jugadorData.has("nationality_id") && !jugadorData.isNull("nationality_id")) {
                            int nationalityId = jugadorData.getInt("nationality_id");
                            nacionalidad = traducirNacionalidad(nationalityId);
                        }
                        
                        // Foto
                        String fotoJugador = jugadorData.optString("image_path", ""); 
                        
                        // Guardamos el jugador delegando en su DAO
                        JugadorDAO.guardarJugador(idJugador, nombreJugador, posicionStr, nacionalidad, fotoJugador, idClub);
                    }
                }
            }
            
            System.out.println("¡Carga de equipos y jugadores completada con éxito!");

        } catch (IOException | InterruptedException | JSONException e) {
            throw new RuntimeException("Error al cargar equipos", e);
        }
    }
    
    /**
     * Traduce los IDs numéricos de Sportmonks al nombre del país en castellano
     * @param id Identificador de la nacionalidad
     * @return Nombre del país
     */
    private String traducirNacionalidad(int id) {
        switch (id) {
            case 2:     return "Polonia";
            case 5:     return "Brasil";
            case 11:    return "Alemania";
            case 17:    return "Francia";
            case 20:    return "Portugal";
            case 23:    return "Costa de Marfil";
            case 38:    return "Países Bajos";
            case 47:    return "Suecia";
            case 62:    return "Inglaterra"; 
            case 98:    return "Australia";
            case 116:   return "Chipre";
            case 125:   return "Grecia";
            case 143:   return "Austria";
            case 158:   return "Uruguay";
            case 224:   return "Bulgaria";
            case 245:   return "República Checa";
            case 266:   return "Croacia";
            case 296:   return "Serbia";
            case 320:   return "Dinamarca";
            case 353:   return "Colombia";
            case 401:   return "Eslovaquia";
            case 455:   return "República de Irlanda";
            case 458:   return "México";
            case 462:   return "Inglaterra";
            case 468:   return "Ghana";
            case 479:   return "Japón";
            case 491:   return "Irlanda del Norte";
            case 515:   return "Gales";
            case 556:   return "Bélgica";
            case 607:   return "Burkina Faso";
            case 674:   return "Hungría";
            case 712:   return "Corea del Sur";
            case 716:   return "Nigeria";
            case 748:   return "Letonia";
            case 1004:  return "Canadá";
            case 1161:  return "Escocia";
            case 1179:  return "Kenia";
            case 1233:  return "Finlandia";
            case 1320:  return "República Democrática del Congo";
            case 1439:  return "Túnez";
            case 1442:  return "Canadá"; 
            case 1578:  return "Noruega";
            case 1640:  return "Irlanda";
            case 1707:  return "Guinea-Bisáu";
            case 1796:  return "Islandia";
            case 2325:  return "Zimbabue";
            case 2345:  return "Moldavia";
            case 2405:  return "Estonia";
            case 2427:  return "Kazajistán";
            case 2454:  return "Albania";
            case 2507:  return "Gambia";
            case 2568:  return "Zambia";
            case 2817:  return "Nueva Zelanda";
            case 3483:  return "Estados Unidos";
            case 4125:  return "Uganda";
            case 57142: return "Macedonia del Norte";
            case 137919: return "Angola";
            default: return "Desconocida (" + id + ")"; 
        }
    }
}