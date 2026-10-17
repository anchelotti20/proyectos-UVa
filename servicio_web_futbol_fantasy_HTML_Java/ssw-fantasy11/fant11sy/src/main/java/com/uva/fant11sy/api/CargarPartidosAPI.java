package com.uva.fant11sy.api;

import com.uva.fant11sy.daos.PartidoDAO;
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
 * Clase encargada de realizar la carga inicial del calendario de partidos de la temporada.
 * Se conecta a la API de Sportmonks para volcar los encuentros programados en la BD.
 * @author Angel Antolin
 * @author Marcos Gomez
 * @author Jorge Hernandez
 * @author Luis Anton
 */
public class CargarPartidosAPI {

    private static final String URL_API = "https://api.sportmonks.com/v3/football/fixtures/seasons/25598?include=participants,round&api_token=" + Config.getApiToken();

    /**
     * Obtiene de la API todos los partidos de la temporada y delega su guardado al DAO correspondiente.
     */
    public void cargarCalendarioInicial() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL_API)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            JSONObject jsonResponse = new JSONObject(response.body());
            JSONArray partidos = jsonResponse.getJSONArray("data");

            for (int i = 0; i < partidos.length(); i++) {
                JSONObject partido = partidos.getJSONObject(i);
                
                int idPartido = partido.getInt("id");
                
                int jornada = 1;
                if (partido.has("round") && !partido.isNull("round")) {
                    jornada = partido.getJSONObject("round").optInt("name", 1);
                }
                
                int idLocal = -1;
                int idVisitante = -1;
                
                if (partido.has("participants")) {
                    JSONArray participantes = partido.getJSONArray("participants");
                    for (int j = 0; j < participantes.length(); j++) {
                        JSONObject participante = participantes.getJSONObject(j);
                        if (participante.getJSONObject("meta").getString("location").equals("home")) {
                            idLocal = participante.getInt("id");
                        } else {
                            idVisitante = participante.getInt("id");
                        }
                    }
                }
                
                if (idLocal != -1 && idVisitante != -1) {
                    PartidoDAO.guardarPartidoInicial(idPartido, idLocal, idVisitante, jornada);
                }
            }
        } catch (IOException | InterruptedException | JSONException e) {
            throw new RuntimeException("Error al cargar partidos", e);
        }
    }
}