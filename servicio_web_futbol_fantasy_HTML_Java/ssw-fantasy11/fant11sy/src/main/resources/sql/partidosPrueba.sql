/**
* Script de partidos de prueba para la Scottish Premiership (FANT11SY)
* Basado en los IDs reales de la API de Sportmonks.
*/

USE fantasy11;

-- Limpiamos los partidos anteriores por si ejecutas el script varias veces
DELETE FROM Partido;

-- ==========================================
-- JORNADA 1 (Todos los partidos finalizados)
-- ==========================================
INSERT INTO Partido (ganador, golesLocal, golesVisitante, numero_jornada, id_club_local, id_club_visitante) VALUES 
('Celtic', 2, 1, 1, 53, 62),          -- Celtic vs Rangers
('Empate', 1, 1, 1, 273, 314),        -- Aberdeen vs Hearts
('Hibernian', 2, 0, 1, 66, 180),      -- Hibernian vs Kilmarnock
('Dundee United', 0, 1, 1, 284, 282), -- Dundee vs Dundee United
('Motherwell', 3, 0, 1, 309, 496),    -- Motherwell vs St. Mirren
('Falkirk', 1, 2, 1, 258, 770);       -- Livingston vs Falkirk

-- ==========================================
-- JORNADA 2 (Todos los partidos finalizados)
-- ==========================================
INSERT INTO Partido (ganador, golesLocal, golesVisitante, numero_jornada, id_club_local, id_club_visitante) VALUES 
('Celtic', 0, 3, 2, 180, 53),         -- Kilmarnock vs Celtic
('Rangers', 2, 0, 2, 62, 273),        -- Rangers vs Aberdeen
('Hearts', 1, 0, 2, 314, 66),         -- Hearts vs Hibernian
('Empate', 2, 2, 2, 282, 309),        -- Dundee United vs Motherwell
('Empate', 0, 0, 2, 496, 258),        -- St. Mirren vs Livingston
('Dundee', 1, 3, 2, 770, 284);        -- Falkirk vs Dundee

-- ==========================================
-- JORNADA 3 (JORNADA ACTUAL - Parcialmente jugada)
-- 3 partidos tienen ganador y 3 están en NULL
-- ==========================================
INSERT INTO Partido (ganador, golesLocal, golesVisitante, numero_jornada, id_club_local, id_club_visitante) VALUES 
(NULL, 0, 0, 3, 53, 314),         -- Celtic vs Hearts (Jugado)
(NULL, 0, 0, 3, 273, 180),      -- Aberdeen vs Kilmarnock (Jugado)
(NULL, 0, 0, 3, 66, 62),          -- Hibernian vs Rangers (Jugado)
(NULL, 0, 0, 3, 258, 282),            -- Livingston vs Dundee United (POR JUGAR)
(NULL, 0, 0, 3, 284, 309),            -- Dundee vs Motherwell (POR JUGAR)
(NULL, 0, 0, 3, 770, 496);            -- Falkirk vs St. Mirren (POR JUGAR)

-- ==========================================
-- JORNADAS 4 a 22 (Todos los partidos pendientes)
-- (ganador en NULL, goles a 0)
-- ==========================================
INSERT INTO Partido (ganador, golesLocal, golesVisitante, numero_jornada, id_club_local, id_club_visitante) VALUES 
-- JORNADA 4
(NULL, 0, 0, 4, 62, 53), (NULL, 0, 0, 4, 314, 273), (NULL, 0, 0, 4, 180, 66),
(NULL, 0, 0, 4, 282, 284), (NULL, 0, 0, 4, 496, 309), (NULL, 0, 0, 4, 770, 258),
-- JORNADA 5
(NULL, 0, 0, 5, 53, 273), (NULL, 0, 0, 5, 62, 314), (NULL, 0, 0, 5, 66, 282),
(NULL, 0, 0, 5, 180, 284), (NULL, 0, 0, 5, 258, 309), (NULL, 0, 0, 5, 770, 496),
-- JORNADA 6
(NULL, 0, 0, 6, 273, 53), (NULL, 0, 0, 6, 314, 62), (NULL, 0, 0, 6, 282, 66),
(NULL, 0, 0, 6, 284, 180), (NULL, 0, 0, 6, 309, 258), (NULL, 0, 0, 6, 496, 770),
-- JORNADA 7
(NULL, 0, 0, 7, 53, 66), (NULL, 0, 0, 7, 62, 180), (NULL, 0, 0, 7, 273, 282),
(NULL, 0, 0, 7, 314, 284), (NULL, 0, 0, 7, 258, 496), (NULL, 0, 0, 7, 309, 770),
-- JORNADA 8
(NULL, 0, 0, 8, 66, 53), (NULL, 0, 0, 8, 180, 62), (NULL, 0, 0, 8, 282, 273),
(NULL, 0, 0, 8, 284, 314), (NULL, 0, 0, 8, 496, 258), (NULL, 0, 0, 8, 770, 309),
-- JORNADA 9
(NULL, 0, 0, 9, 53, 180), (NULL, 0, 0, 9, 62, 66), (NULL, 0, 0, 9, 273, 284),
(NULL, 0, 0, 9, 314, 282), (NULL, 0, 0, 9, 258, 770), (NULL, 0, 0, 9, 309, 496),
-- JORNADA 10
(NULL, 0, 0, 10, 180, 53), (NULL, 0, 0, 10, 66, 62), (NULL, 0, 0, 10, 284, 273),
(NULL, 0, 0, 10, 282, 314), (NULL, 0, 0, 10, 770, 258), (NULL, 0, 0, 10, 496, 309),
-- JORNADA 11
(NULL, 0, 0, 11, 53, 282), (NULL, 0, 0, 11, 62, 284), (NULL, 0, 0, 11, 66, 258),
(NULL, 0, 0, 11, 180, 309), (NULL, 0, 0, 11, 273, 496), (NULL, 0, 0, 11, 314, 770),
-- JORNADA 12
(NULL, 0, 0, 12, 282, 53), (NULL, 0, 0, 12, 284, 62), (NULL, 0, 0, 12, 258, 66),
(NULL, 0, 0, 12, 309, 180), (NULL, 0, 0, 12, 496, 273), (NULL, 0, 0, 12, 770, 314),
-- JORNADA 13
(NULL, 0, 0, 13, 53, 284), (NULL, 0, 0, 13, 62, 258), (NULL, 0, 0, 13, 66, 309),
(NULL, 0, 0, 13, 180, 496), (NULL, 0, 0, 13, 273, 770), (NULL, 0, 0, 13, 314, 282),
-- JORNADA 14
(NULL, 0, 0, 14, 284, 53), (NULL, 0, 0, 14, 258, 62), (NULL, 0, 0, 14, 309, 66),
(NULL, 0, 0, 14, 496, 180), (NULL, 0, 0, 14, 770, 273), (NULL, 0, 0, 14, 282, 314),
-- JORNADA 15
(NULL, 0, 0, 15, 53, 258), (NULL, 0, 0, 15, 62, 309), (NULL, 0, 0, 15, 66, 496),
(NULL, 0, 0, 15, 180, 770), (NULL, 0, 0, 15, 273, 282), (NULL, 0, 0, 15, 314, 284),
-- JORNADA 16
(NULL, 0, 0, 16, 258, 53), (NULL, 0, 0, 16, 309, 62), (NULL, 0, 0, 16, 496, 66),
(NULL, 0, 0, 16, 770, 180), (NULL, 0, 0, 16, 282, 273), (NULL, 0, 0, 16, 284, 314),
-- JORNADA 17
(NULL, 0, 0, 17, 53, 309), (NULL, 0, 0, 17, 62, 496), (NULL, 0, 0, 17, 66, 770),
(NULL, 0, 0, 17, 180, 282), (NULL, 0, 0, 17, 273, 284), (NULL, 0, 0, 17, 314, 258),
-- JORNADA 18
(NULL, 0, 0, 18, 309, 53), (NULL, 0, 0, 18, 496, 62), (NULL, 0, 0, 18, 770, 66),
(NULL, 0, 0, 18, 282, 180), (NULL, 0, 0, 18, 284, 273), (NULL, 0, 0, 18, 258, 314),
-- JORNADA 19
(NULL, 0, 0, 19, 53, 496), (NULL, 0, 0, 19, 62, 770), (NULL, 0, 0, 19, 66, 282),
(NULL, 0, 0, 19, 180, 284), (NULL, 0, 0, 19, 273, 258), (NULL, 0, 0, 19, 314, 309),
-- JORNADA 20
(NULL, 0, 0, 20, 496, 53), (NULL, 0, 0, 20, 770, 62), (NULL, 0, 0, 20, 282, 66),
(NULL, 0, 0, 20, 284, 180), (NULL, 0, 0, 20, 258, 273), (NULL, 0, 0, 20, 309, 314),
-- JORNADA 21
(NULL, 0, 0, 21, 53, 770), (NULL, 0, 0, 21, 62, 282), (NULL, 0, 0, 21, 66, 284),
(NULL, 0, 0, 21, 180, 258), (NULL, 0, 0, 21, 273, 309), (NULL, 0, 0, 21, 314, 496),
-- JORNADA 22
(NULL, 0, 0, 22, 770, 53), (NULL, 0, 0, 22, 282, 62), (NULL, 0, 0, 22, 284, 66),
(NULL, 0, 0, 22, 258, 180), (NULL, 0, 0, 22, 309, 273), (NULL, 0, 0, 22, 496, 314);