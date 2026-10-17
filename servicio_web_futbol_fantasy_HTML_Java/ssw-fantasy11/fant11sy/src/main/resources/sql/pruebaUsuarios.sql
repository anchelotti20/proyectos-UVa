/**
* Crea la liga y una serie de Usuarios de prueba
*/

USE fantasy11;

-- 1. Crear la Liga Global
INSERT INTO Liga (id_liga, nombre, numeroJugadores) VALUES 
(1, 'Liga Global Fantasy', 4);


INSERT INTO Jornada (numero, id_liga) VALUES 
(1, 1), (2, 1), (3, 1), (4, 1), (5, 1), (6, 1), (7, 1), (8, 1),
(9, 1), (10, 1), (11, 1), (12, 1), (13, 1), (14, 1), (15, 1), (16, 1),
(17, 1), (18, 1), (19, 1), (20, 1), (21, 1), (22, 1);


INSERT INTO Usuario (id_usuario, nombre, usuario, contrasena, id_liga) VALUES
(1, 'Angel', 'Anche', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 1),
(2, 'Marcos', 'Markitos', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 1),
(3, 'Jorge', 'Jorgasa', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 1),
(4, 'Luis', 'LusugoEnjoyer', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 1);


INSERT INTO Equipo (nombre, formacion, id_usuario) VALUES
('Equipo de Anche', '4-3-3', 1),
('Equipo de Markitos', '4-3-3', 2),
('Equipo de Jorgasa', '4-3-3', 3),
('Equipo de LusugoEnjoyer', '4-3-3', 4);

USE fantasy11;

-- 1. Actualizamos los puntos totales de tus usuarios (para el filtro "Total")
UPDATE Usuario SET puntos = 95 WHERE id_usuario = 1;
UPDATE Usuario SET puntos = 80 WHERE id_usuario = 2;
UPDATE Usuario SET puntos = 80 WHERE id_usuario = 3;
UPDATE Usuario SET puntos = 70 WHERE id_usuario = 4;

UPDATE Usuario SET puesto = 1 WHERE id_usuario = 1;
UPDATE Usuario SET puesto = 2 WHERE id_usuario = 2;
UPDATE Usuario SET puesto = 3 WHERE id_usuario = 3;
UPDATE Usuario SET puesto = 4 WHERE id_usuario = 4;

-- 2. Insertamos puntos específicos para la Jornada 1
-- (Para que cuando elijas "Jornada 1" en el desplegable, salgan datos)
INSERT INTO PuntuacionJornada (puntuacionJornada, numero_jornada, id_usuario) VALUES 
(40, 1, 1), -- Angel hizo 40
(60, 1, 2), -- Marcos hizo 60 (Iría 1º en la J1)
(35, 1, 3), -- Jorge hizo 35
(50, 1, 4); -- Luis hizo 50

-- 3. Insertamos puntos para la Jornada 2 (Para probar que el filtro cambia)
INSERT INTO PuntuacionJornada (puntuacionJornada, numero_jornada, id_usuario) VALUES 
(55, 2, 1), -- Angel ahora destaca
(20, 2, 2),
(45, 2, 3),
(20, 2, 4);