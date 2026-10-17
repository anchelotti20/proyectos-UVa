package com.uva.fant11sy.api;

import com.uva.fant11sy.daos.ActuacionDAO;
import com.uva.fant11sy.daos.PartidoDAO;
import com.uva.fant11sy.modelo.Actuacion;
import com.uva.fant11sy.util.Config;
import java.io.IOException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;

/**
 * Clase encargada de obtener los resultados reales de los partidos durante el fin de semana.
 * Actualiza los marcadores en la base de datos y calcula la actuacion individual de los jugadores.
 * @author Angel Antolin
 * @author Marcos Gomez
 * @author Jorge Hernandez
 * @author Luis Anton
 */
public class ActualizarResultadosAPI {

    private static final int ID_MINUTOS = 118;
    private static final int ID_GOLES = 52;
    private static final int ID_ASISTENCIAS = 79;
    private static final int ID_AMARILLAS = 84;
    private static final int ID_ROJAS = 83;
    private static final int ID_GOLES_PROPIA = 53; 
    private static final int ID_GOLES_CONTRA = 88;

    /**
     * Procesa una jornada completada para una fecha especifica.
     * Actualiza los goles de cada partido y procesa las actuaciones de los jugadores involucrados.
     * @param fechaYMD, cadena con la fecha a consultar en formato YYYY-MM-DD.
     */
    public void procesarJornada(String fechaYMD) {
        String url = "https://api.sportmonks.com/v3/football/fixtures/date/" + fechaYMD + "?include=scores,lineups.details&api_token=" + Config.getApiToken();

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            JSONObject jsonResponse = new JSONObject(response.body());
            JSONArray partidos = jsonResponse.getJSONArray("data");

            for (int i = 0; i < partidos.length(); i++) {
                JSONObject partido = partidos.getJSONObject(i);
                int idPartido = partido.getInt("id");
                
                int golesLocal = 0;
                int golesVisitante = 0;
                
                if (partido.has("scores")) {
                    JSONArray scores = partido.getJSONArray("scores");
                    for (int s = 0; s < scores.length(); s++) {
                        JSONObject score = scores.getJSONObject(s);
                        if (score.getJSONObject("score").getString("participant").equals("home") && score.getInt("description") == 1) { 
                             golesLocal = score.getJSONObject("score").getInt("goals");
                        } else if (score.getJSONObject("score").getString("participant").equals("away") && score.getInt("description") == 1) {
                             golesVisitante = score.getJSONObject("score").getInt("goals");
                        }
                    }
                }
                
                PartidoDAO.actualizarResultado(idPartido, golesLocal, golesVisitante);

                if (partido.has("lineups")) {
                    JSONArray alineaciones = partido.getJSONArray("lineups");
                    List<Actuacion> actuacionesPartido = new ArrayList<>();
                    
                    for (int j = 0; j < alineaciones.length(); j++) {
                        JSONObject jugador = alineaciones.getJSONObject(j);
                        int idJugador = jugador.getInt("player_id");
                        
                        Actuacion actuacion = generarActuacionDesdeAPI(idPartido, idJugador, jugador.optJSONArray("details"));
                        actuacionesPartido.add(actuacion);
                    }
                    
                    ActuacionDAO.procesarYGuardarActuaciones(actuacionesPartido);
                }
            }
        } catch (IOException | InterruptedException | JSONException e) {
            
            throw new RuntimeException("Error al cargar jornada", e);
        }
    }

    /**
     * Mapea los datos brutos recibidos de la API al JavaBean Actuacion.
     * @param idPartido, identificador del partido.
     * @param idJugador, identificador del jugador.
     * @param detalles, JSONArray con los eventos especificos del jugador durante el partido.
     * @return objeto Actuacion con las estadisticas rellenadas.
     */
    private Actuacion generarActuacionDesdeAPI(int idPartido, int idJugador, JSONArray detalles) {
        Actuacion act = new Actuacion();
        act.setIdPartido(idPartido);
        act.setIdJugador(idJugador);

        if (detalles != null) {
            for (int k = 0; k < detalles.length(); k++) {
                JSONObject detalle = detalles.getJSONObject(k);
                int typeId = detalle.getInt("type_id");
                int valor = detalle.optInt("value", 1);
                
                switch (typeId) {
                    case ID_MINUTOS:
                        act.setMinutos(valor);
                        break;
                    case ID_GOLES:
                        act.setGoles(act.getGoles() + valor);
                        break;
                    case ID_ASISTENCIAS:
                        act.setAsistencias(act.getAsistencias() + valor);
                        break;
                    case ID_AMARILLAS:
                        act.setAmarillas(act.getAmarillas() + valor);
                        break;
                    case ID_ROJAS:
                        act.setRojas(act.getRojas() + valor);
                        break;
                    case ID_GOLES_PROPIA:
                        act.setGolesEnPropia(act.getGolesEnPropia() + valor);
                        break;
                    case ID_GOLES_CONTRA:
                        act.setGolesEnContra(act.getGolesEnContra() + valor);
                        break;
                    default:
                        break;
                }
            }
        }
        return act;
    }
}