/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */


USE fantasy11;



-- Insertamos 11 jugadores en la plantilla del equipo manteniendo la formación 4-3-3
INSERT IGNORE INTO EquipoJugador (id_equipo, id_jugador, slot) VALUES
-- 1 Portero
(1, 35, 1),   -- Craig Gordon

-- 4 Defensas
(1, 758, 11),  -- J. Tavernier
(1, 807, 12),  -- G. Hanley
(1, 1233, 13), -- D. John
(1, 1712, 14), -- C. Carter-Vickers

-- 3 Mediocentros
(1, 850, 21),  -- A. Oxlade-Chamberlain
(1, 1442, 22), -- S. Arfield
(1, 2662, 23), -- A. Halliday

-- 3 Delanteros
(1, 1488, 31), -- K. Iheanacho
(1, 2337, 32), -- J. Russell
(1, 3372, 33); -- M. Watkins
