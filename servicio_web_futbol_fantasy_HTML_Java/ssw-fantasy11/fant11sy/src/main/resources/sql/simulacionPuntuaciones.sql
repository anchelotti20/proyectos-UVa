/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */

USE fantasy11;

-- =========================================================================
-- 1. CONFIGURACIÓN DE LOS EQUIPOS DE LOS 4 USUARIOS (Formación 4-3-3)
-- =========================================================================
-- Vaciamos asignaciones previas por si acaso
DELETE FROM EquipoJugador;

-- Equipo de Anche (id_equipo = 1)
INSERT INTO EquipoJugador (id_equipo, id_jugador, slot) VALUES
(1, 35, 1),     -- POR
(1, 758, 11),   -- DEF
(1, 807, 12),   -- DEF
(1, 1233, 13),  -- DEF
(1, 1712, 14),  -- DEF
(1, 850, 21),   -- MED
(1, 1442, 22),  -- MED
(1, 2662, 23),  -- MED
(1, 1488, 31),  -- DEL
(1, 2337, 32),  -- DEL
(1, 3372, 33);  -- DEL

-- Equipo de Markitos (id_equipo = 2)
INSERT INTO EquipoJugador (id_equipo, id_jugador, slot) VALUES
(2, 169, 1),    -- POR
(2, 1628, 11),  -- DEF
(2, 2452, 12),  -- DEF
(2, 3752, 13),  -- DEF
(2, 6378, 14),  -- DEF
(2, 2709, 21),  -- MED
(2, 2781, 22),  -- MED
(2, 2827, 23),  -- MED
(2, 4285, 31),  -- DEL
(2, 4417, 32),  -- DEL
(2, 5974, 33);  -- DEL

-- Equipo de Jorgasa (id_equipo = 3)
INSERT INTO EquipoJugador (id_equipo, id_jugador, slot) VALUES
(3, 1462, 1),   -- POR
(3, 8846, 11),  -- DEF
(3, 8881, 12),  -- DEF
(3, 9306, 13),  -- DEF
(3, 9510, 14),  -- DEF
(3, 3385, 21),  -- MED
(3, 4583, 22),  -- MED
(3, 7160, 23),  -- MED
(3, 6234, 31),  -- DEL
(3, 10602, 32), -- DEL
(3, 12673, 33); -- DEL

-- Equipo de Luis (id_equipo = 4)
INSERT INTO EquipoJugador (id_equipo, id_jugador, slot) VALUES
(4, 3027, 1),   -- POR
(4, 9710, 11),  -- DEF
(4, 9781, 12),  -- DEF
(4, 12227, 13), -- DEF
(4, 12321, 14), -- DEF
(4, 8201, 21),  -- MED
(4, 8470, 22),  -- MED
(4, 8585, 23),  -- MED
(4, 14302, 31), -- DEL
(4, 26256, 32), -- DEL
(4, 319282, 33);-- DEL


-- =========================================================================
-- 2. RESOLVER LOS PARTIDOS DE LA JORNADA 3 (Actualizar tabla Partido)
-- Basado estrictamente en tus enfrentamientos reales
-- =========================================================================

-- Celtic(53) 3 - 1 Hearts(314)
UPDATE Partido SET golesLocal = 3, golesVisitante = 1, ganador = 'Celtic' 
WHERE numero_jornada = 3 AND id_club_local = 53;

-- Aberdeen(273) 1 - 1 Kilmarnock(180)
UPDATE Partido SET golesLocal = 1, golesVisitante = 1, ganador = 'Empate' 
WHERE numero_jornada = 3 AND id_club_local = 273;

-- Hibernian(66) 0 - 2 Rangers(62)
UPDATE Partido SET golesLocal = 0, golesVisitante = 2, ganador = 'Rangers' 
WHERE numero_jornada = 3 AND id_club_local = 66;

-- Livingston(258) 0 - 0 Dundee United(282)
UPDATE Partido SET golesLocal = 0, golesVisitante = 0, ganador = 'Empate' 
WHERE numero_jornada = 3 AND id_club_local = 258;

-- Dundee(284) 2 - 1 Motherwell(309)
UPDATE Partido SET golesLocal = 2, golesVisitante = 1, ganador = 'Dundee' 
WHERE numero_jornada = 3 AND id_club_local = 284;

-- Falkirk(770) 1 - 2 St. Mirren(496)
UPDATE Partido SET golesLocal = 1, golesVisitante = 2, ganador = 'St. Mirren' 
WHERE numero_jornada = 3 AND id_club_local = 770;


-- =========================================================================
-- 3. INSERTAR ACTUACIONES Y PUNTOS PRECALCULADOS PARA LOS 44 JUGADORES
-- =========================================================================
DELETE FROM ActuacionPartido;

-- Sintaxis: (goles, minutos, asistencias, amarillas, rojas, golesPropia, golesContra, puntos, id_partido, id_jugador)
INSERT INTO ActuacionPartido (goles, minutos, asistencias, tarjetasAmarillas, tarjetasRojas, golesEnPropia, golesEnContra, puntos, id_partido, id_jugador) VALUES
-- EQUIPO 1 (Anche)
(0, 90, 0, 0, 0, 0, 3, 1, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 53), 35),   -- Gordon (Hearts) | Pts: 2 - 1(gc) = 1
(1, 90, 1, 0, 0, 0, 0, 11, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 66), 758),  -- Tavernier (Rangers) | Pts: 2 + 6 + 3 = 11
(0, 90, 0, 1, 0, 0, 2, 0, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 66), 807),   -- Hanley (Hibernian) | Pts: 2 - 1 - 1 = 0
(0, 90, 0, 0, 0, 0, 1, 6, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 770), 1233), -- D. John (St Mirren) | Pts: 2 + 4 = 6
(0, 90, 0, 0, 0, 0, 1, 6, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 53), 1712),  -- Carter-Vickers (Celtic) | Pts: 2 + 4 = 6
(1, 75, 0, 0, 0, 0, 0, 7, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 53), 850),   -- Oxlade-C. (Celtic) | Pts: 2 + 5 = 7
(0, 65, 0, 1, 0, 0, 0, 1, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 258), 1442), -- Arfield (Livingston) | Pts: 2 - 1 = 1
(0, 90, 1, 0, 0, 0, 0, 5, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 284), 2662), -- Halliday (Motherwell) | Pts: 2 + 3 = 5
(2, 90, 0, 0, 0, 0, 0, 10, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 53), 1488), -- Iheanacho (Celtic) | Pts: 2 + 8 = 10
(1, 90, 0, 0, 0, 0, 0, 6, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 284), 2337), -- Russell (Dundee) | Pts: 2 + 4 = 6
(0, 45, 0, 0, 0, 0, 0, 1, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 273), 3372), -- Watkins (Kilmarnock) | Pts: 1

-- EQUIPO 2 (Markitos)
(0, 90, 0, 0, 0, 0, 1, 6, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 53), 169),   -- Schmeichel (Celtic) | Pts: 6
(0, 90, 0, 0, 0, 0, 3, 1, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 53), 1628),  -- Kingsley (Hearts) | Pts: 1
(0, 80, 0, 0, 0, 0, 2, 1, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 66), 2452),  -- Obita (Hibernian) | Pts: 1
(0, 90, 0, 1, 0, 0, 0, 1, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 258), 3752), -- Wilson (Livingston) | Pts: 1
(0, 90, 0, 0, 0, 0, 2, 1, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 770), 6378), -- Donaldson (Falkirk) | Pts: 1
(0, 90, 0, 0, 0, 0, 0, 2, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 258), 2709), -- Digby (Dundee U) | Pts: 2
(0, 90, 0, 0, 0, 0, 0, 2, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 66), 2781),  -- Newell (Hibernian) | Pts: 2
(0, 60, 0, 0, 0, 0, 0, 1, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 258), 2827), -- Shinnie (Livingston) | Pts: 1
(0, 90, 0, 0, 0, 0, 0, 2, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 273), 4285), -- Kennedy (Kilmarnock) | Pts: 2
(1, 90, 0, 0, 0, 0, 0, 6, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 770), 4417), -- Mandron (St Mirren) | Pts: 6
(0, 20, 0, 0, 0, 0, 0, 1, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 258), 5974), -- May (Livingston) | Pts: 1

-- EQUIPO 3 (Jorgasa)
(0, 90, 0, 0, 0, 0, 0, 6, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 66), 1462),  -- Butland (Rangers) | Pts: 6
(0, 90, 0, 0, 0, 0, 1, 2, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 770), 8846), -- Tanser (St Mirren) | Pts: 2
(0, 90, 0, 0, 0, 1, 3, -1,(SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 53), 8881),  -- Kent (Hearts) - GOL PROPIA | Pts: -1
(0, 90, 0, 0, 0, 0, 1, 2, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 284), 9306), -- Robertson (Dundee) | Pts: 2
(0, 90, 0, 1, 0, 0, 2, 1, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 284), 9510), -- McGinn (Motherwell) | Pts: 1
(0, 45, 0, 0, 0, 0, 0, 1, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 273), 3385), -- Tshibola (Kilmarnock) | Pts: 1
(0, 90, 0, 0, 1, 0, 0, -1,(SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 66), 4583),  -- Barlaser (Hibernian) | Pts: -1
(0, 90, 1, 0, 0, 0, 0, 5, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 770), 7160), -- Donnelly (St Mirren) | Pts: 5
(1, 90, 0, 0, 0, 0, 0, 6, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 284), 6234), -- Charles-Cook (Motherwell) | Pts: 6
(0, 90, 0, 0, 0, 0, 0, 2, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 258), 10602),-- Muirhead (Livingston) | Pts: 2
(0, 70, 0, 0, 0, 0, 0, 2, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 258), 12673),-- Nouble (Livingston) | Pts: 2

-- EQUIPO 4 (Luis)
(0, 90, 0, 0, 0, 0, 1, 1, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 273), 3027), -- Roos (Kilmarnock) | Pts: 1
(1, 90, 0, 0, 0, 0, 2, 7, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 770), 9710), -- Hart (Falkirk) | Pts: 7
(0, 90, 0, 0, 0, 0, 1, 2, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 770), 9781), -- Gogic (St Mirren) | Pts: 2
(0, 90, 1, 0, 0, 0, 0, 9, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 258), 12227),-- Halliday (Dundee U) | Pts: 9
(0, 90, 0, 0, 0, 0, 2, 1, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 284), 12321),-- O'Donnell (Motherwell) | Pts: 1
(0, 90, 0, 1, 0, 0, 0, 1, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 258), 8201), -- Montaño (Livingston) | Pts: 1
(1, 90, 0, 0, 0, 0, 0, 7, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 258), 8470), -- Wright (Dundee U) | Pts: 7
(0, 90, 2, 0, 0, 0, 0, 8, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 53), 8585),  -- McGregor (Celtic) | Pts: 8
(1, 90, 0, 0, 0, 0, 0, 6, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 273), 14302),-- Olusanya (Aberdeen) | Pts: 6
(0, 80, 0, 0, 0, 0, 0, 2, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 258), 26256),-- Bokila (Livingston) | Pts: 2
(0, 90, 1, 0, 0, 0, 0, 5, (SELECT id_partido FROM Partido WHERE numero_jornada = 3 AND id_club_local = 53), 319282);-- Maeda (Celtic) | Pts: 5