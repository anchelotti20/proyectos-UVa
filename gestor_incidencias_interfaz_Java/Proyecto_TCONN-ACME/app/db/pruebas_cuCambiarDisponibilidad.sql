-- =====================================================================
-- SCRIPT DE POBLACIÓN DE DATOS PARA PRUEBAS (TCONN-ACME)
-- Ejecutar DESPUÉS de createTablesActualizado.sql
-- =====================================================================

-- 1. ZONAS GEOGRÁFICAS
INSERT INTO ZonaGeografica (id_zona) VALUES 
('ZONA_NORTE'), 
('ZONA_SUR');

-- 2. BASES (1 en Norte, 1 en Sur)
INSERT INTO Base (id_base, direccion, ubicacion, id_zona) VALUES 
(1, ROW('Calle Norte', 1, 1, 'A', 47001, 'Valladolid', 'Valladolid')::tipo_direccion, ROW(41.65, -4.72)::tipo_coordenada_gps, 'ZONA_NORTE'),
(2, ROW('Avenida Sur', 20, 2, 'B', 41001, 'Sevilla', 'Sevilla')::tipo_direccion, ROW(37.38, -5.98)::tipo_coordenada_gps, 'ZONA_SUR');

-- 3. PERSONAS (13 perfiles para todos los casos de uso)
INSERT INTO Persona (id_persona, nombre, apellidos, telefono_contacto, direccion, nif) VALUES 
(1, 'Tecnico1', 'Perez', 600100101, ROW('Via 1', 1, 1, 'A', 47001, 'VLL', 'VLL')::tipo_direccion, '10000001A'),
(2, 'Coord1', 'Gomez', 600100102, ROW('Via 2', 1, 1, 'A', 47001, 'VLL', 'VLL')::tipo_direccion, '20000002B'),
(3, 'Super1', 'Lopez', 600100103, ROW('Via 3', 1, 1, 'A', 47001, 'VLL', 'VLL')::tipo_direccion, '30000003C'),
(4, 'ERTE1', 'Ruiz', 600100104, ROW('Via 4', 1, 1, 'A', 47001, 'VLL', 'VLL')::tipo_direccion, '40000004D'),
(5, 'Despedido1', 'Martin', 600100105, ROW('Via 5', 1, 1, 'A', 47001, 'VLL', 'VLL')::tipo_direccion, '50000005E'),
(6, 'Vacaciones1', 'Sanz', 600100106, ROW('Via 6', 1, 1, 'A', 47001, 'VLL', 'VLL')::tipo_direccion, '60000006F'),
(7, 'Baja1', 'Díaz', 600100107, ROW('Via 7', 1, 1, 'A', 47001, 'VLL', 'VLL')::tipo_direccion, '70000007G'),
(8, 'TecNorteA', 'Ayudante', 600100108, ROW('Via 8', 1, 1, 'A', 47001, 'VLL', 'VLL')::tipo_direccion, '80000008H'),
(9, 'TecNorteB', 'Ayudante', 600100109, ROW('Via 9', 1, 1, 'A', 47001, 'VLL', 'VLL')::tipo_direccion, '90000009I'),
(10, 'TecSur1', 'Actor', 600100110, ROW('Via 10', 1, 1, 'A', 47001, 'VLL', 'VLL')::tipo_direccion, '10000010J'),
(11, 'TecSurA', 'Ayudante', 600100111, ROW('Via 11', 1, 1, 'A', 47001, 'VLL', 'VLL')::tipo_direccion, '11000011K'),
(12, 'TecFueraTurno', 'Tarde', 600100112, ROW('Via 12', 1, 1, 'A', 47001, 'VLL', 'VLL')::tipo_direccion, '12000012L'),
(13, 'TecNoAsignado', 'Libre', 600100113, ROW('Via 13', 1, 1, 'A', 47001, 'VLL', 'VLL')::tipo_direccion, '13000013M');

-- 4. EMPLEADOS (Mismo ID que persona. Contraseña genérica '1234')
INSERT INTO Empleado (id_persona, fecha_incorporacion, email, password) VALUES 
(1, '2020-01-01', 'e1@acme.com', '1234'),
(2, '2020-01-01', 'e2@acme.com', '1234'),
(3, '2020-01-01', 'e3@acme.com', '1234'),
(4, '2020-01-01', 'e4@acme.com', '1234'),
(5, '2020-01-01', 'e5@acme.com', '1234'),
(6, '2020-01-01', 'e6@acme.com', '1234'),
(7, '2020-01-01', 'e7@acme.com', '1234'),
(8, '2020-01-01', 'e8@acme.com', '1234'),
(9, '2020-01-01', 'e9@acme.com', '1234'),
(10, '2020-01-01', 'e10@acme.com', '1234'),
(11, '2020-01-01', 'e11@acme.com', '1234'),
(12, '2020-01-01', 'e12@acme.com', '1234'),
(13, '2020-01-01', 'e13@acme.com', '1234');

-- 5. VINCULACIONES CON LA EMPRESA (RN-2 Check)
INSERT INTO VinculacionesConlaEmpresa (id_empleado, inicio, vinculo) VALUES 
(1, '2020-01-01', 'CONTRATADO'),
(2, '2020-01-01', 'CONTRATADO'),
(3, '2020-01-01', 'CONTRATADO'),
(4, '2020-01-01', 'EN_ERTE'),     -- <--- Error Login
(5, '2020-01-01', 'DESPEDIDO'),   -- <--- Error Login
(6, '2020-01-01', 'CONTRATADO'),
(7, '2020-01-01', 'CONTRATADO'),
(8, '2020-01-01', 'CONTRATADO'),
(9, '2020-01-01', 'CONTRATADO'),
(10, '2020-01-01', 'CONTRATADO'),
(11, '2020-01-01', 'CONTRATADO'),
(12, '2020-01-01', 'CONTRATADO'),
(13, '2020-01-01', 'CONTRATADO');

-- 6. DISPONIBILIDADES (RN-2 Check)
INSERT INTO Disponibilidades (id_empleado, comienzo, disponibilidad) VALUES 
(1, '2020-01-01', 'TRABAJANDO'),
(2, '2020-01-01', 'TRABAJANDO'),
(3, '2020-01-01', 'TRABAJANDO'),
(4, '2020-01-01', 'TRABAJANDO'),
(5, '2020-01-01', 'TRABAJANDO'),
(6, '2020-01-01', 'VACACIONES'),    -- <--- Error Login
(7, '2020-01-01', 'BAJA_TEMPORAL'), -- <--- Error Login
(8, '2020-01-01', 'TRABAJANDO'),
(9, '2020-01-01', 'TRABAJANDO'),
(10, '2020-01-01', 'TRABAJANDO'),
(11, '2020-01-01', 'TRABAJANDO'),
(12, '2020-01-01', 'TRABAJANDO'),
(13, '2020-01-01', 'TRABAJANDO');

-- 7. PUESTOS GENERALES
INSERT INTO Puesto (id_puesto, id_empleado, fecha_inicio) VALUES 
(1, 1, '2020-01-01'), (2, 2, '2020-01-01'), (3, 3, '2020-01-01'), 
(4, 4, '2020-01-01'), (5, 5, '2020-01-01'), (6, 6, '2020-01-01'), 
(7, 7, '2020-01-01'), (8, 8, '2020-01-01'), (9, 9, '2020-01-01'), 
(10, 10, '2020-01-01'), (11, 11, '2020-01-01'), (12, 12, '2020-01-01'), 
(13, 13, '2020-01-01');

-- 8. ESPECIALIZACIÓN DE PUESTOS
INSERT INTO Coordinador (id_puesto) VALUES (2);
INSERT INTO Supervisor (id_puesto) VALUES (3);

INSERT INTO Tecnico (id_puesto, nivel_tecnico) VALUES 
(1, 'basico'), (4, 'basico'), (5, 'basico'), (6, 'basico'), 
(7, 'basico'), (8, 'basico'), (9, 'basico'), (10, 'basico'), 
(11, 'basico'), (12, 'basico'), (13, 'basico');

-- 9. TURNOS TÉCNICOS (Usamos CURRENT_DATE para que siempre estén "en turno" hoy)
-- Excepto el ID 12, que lo ponemos ayer para probar la excepción 2a
INSERT INTO TurnoTecnico (id_turno, fecha_inicio, hora_inicio, fecha_fin, hora_fin, id_tecnico, id_base) VALUES 
(1, CURRENT_DATE, '00:00:00', CURRENT_DATE, '23:59:59', 1, 1), -- T1 (Norte)
(8, CURRENT_DATE, '00:00:00', CURRENT_DATE, '23:59:59', 8, 1), -- Ayudante Norte A
(9, CURRENT_DATE, '00:00:00', CURRENT_DATE, '23:59:59', 9, 1), -- Ayudante Norte B
(10, CURRENT_DATE, '00:00:00', CURRENT_DATE, '23:59:59', 10, 2), -- T10 (Sur)
(11, CURRENT_DATE, '00:00:00', CURRENT_DATE, '23:59:59', 11, 2), -- Ayudante Sur A
(12, '2020-01-01', '00:00:00', '2020-01-01', '23:59:59', 12, 1), -- T12 (FUERA DE TURNO)
(13, CURRENT_DATE, '00:00:00', CURRENT_DATE, '23:59:59', 13, 1); -- T13 (No Asignado)

-- 10. CAMBIOS DE SITUACIÓN TÉCNICO (El "Último Estado" de cada uno)
INSERT INTO CambioDeSituacionTecnico (id_cambio, estado, ubicacion, fecha, hora, id_turno_tecnico) VALUES 
(1, 'asignado', ROW(41.65, -4.72)::tipo_coordenada_gps, CURRENT_DATE, '10:00:00', 1),     -- T1: Actor Norte (Puede pasar a NoDisponible)
(2, 'disponible', ROW(41.65, -4.72)::tipo_coordenada_gps, CURRENT_DATE, '10:00:00', 8),   -- T8: Salva RN-3 en Norte
(3, 'disponible', ROW(41.65, -4.72)::tipo_coordenada_gps, CURRENT_DATE, '10:00:00', 9),   -- T9: Salva RN-3 en Norte
(4, 'asignado', ROW(37.38, -5.98)::tipo_coordenada_gps, CURRENT_DATE, '10:00:00', 10),    -- T10: Actor Sur (Bloqueado por RN-3 si pide NoDisponible)
(5, 'disponible', ROW(37.38, -5.98)::tipo_coordenada_gps, CURRENT_DATE, '10:00:00', 11),  -- T11: Único disponible en Sur
(6, 'asignado', ROW(41.65, -4.72)::tipo_coordenada_gps, '2020-01-01', '10:00:00', 12),    -- T12: Actor Fuera de Turno
(7, 'disponible', ROW(41.65, -4.72)::tipo_coordenada_gps, CURRENT_DATE, '10:00:00', 13);  -- T13: Actor No Asignado (Excepción 2b)

-- Evitar que el contador automatico de postgress rompa por los inserts manuales
SELECT setval('base_id_base_seq', 100);
SELECT setval('persona_id_persona_seq', 100);
SELECT setval('turnotecnico_id_turno_seq', 100);
SELECT setval('cambiodesituaciontecnico_id_cambio_seq', 100);