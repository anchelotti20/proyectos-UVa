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


-- 11. CLIENTES Y CONTRATOS
INSERT INTO PERSONA (id_persona, nombre, apellidos, telefono_contacto, direccion, nif) VALUES
(14, 'Cliente1', 'García Cepeda', 600200201, ROW('Calle Cliente', 1, 1, 'A', 47001, 'VLL', 'VLL')::tipo_direccion, '14000014N'),
(15, 'Cliente2', 'Fernandez Lopez', 600200202, ROW('Avenida Cliente', 2, 2, 'B', 41001, 'VLL', 'VLL')::tipo_direccion, '15000015O'),
(16, 'Cliente3', 'Lopez Garcia', 600200203, ROW('Plaza Cliente', 3, 3, 'C', 28001, 'VLL', 'VLL')::tipo_direccion, '16000016P');


-- Definimos a los personas como clientes y clientes particulares
INSERT INTO Cliente (id_persona) VALUES 
(14),
(15),
(16);
INSERT INTO ClienteParticular (id_persona) VALUES 
(14),
(15),
(16);

-- Definimos los contratos de los clientes
INSERT INTO Contrato (id_contrato, numero_contrato, fecha_inicio, fecha_fin, ubicacion, activo, id_cliente) VALUES 
(1, 'TC-PAR-4521', '2020-01-01', '2026-12-31', ROW('Calle Cliente', 1, 1, 'A', 47001, 'VLL', 'VLL')::tipo_direccion, true, 14),
(2, 'TC-PAR-4522', '2020-01-01', '2026-12-31', ROW('Avenida Cliente', 2, 2, 'B', 41001, 'VLL', 'VLL')::tipo_direccion, true, 15),
(3, 'TC-PAR-4523', '2021-01-01', '2026-12-31', ROW('Plaza Cliente', 3, 3, 'C', 28001, 'VLL', 'VLL')::tipo_direccion, true, 16);

-- 12. LLAMADAS
INSERT INTO Llamada (id_llamada, fecha, numero_telefono, hora, duracion, id_coordinador) VALUES
(1, '2026-05-07', 600200201, '10:00:00', 25, 2),
(2, '2026-05-07', 600200201, '11:00:00', 15, 2),
(3, '2026-05-09', 600200201, '12:00:00', 30, 2),
(4, '2026-05-10', 600200202, '13:00:00', 20, 2),
(5, '2026-05-07', 600200203, '14:00:00', 25, 2),
(6, '2026-05-07', 600200203, '15:00:00', 10, 2),
(7, '2026-05-07', 600200203, '16:00:00', 5, 2);

-- Vinculamos las llamadas a los contratos
INSERT INTO LlamadaContrato (id_llamada, id_contrato) VALUES 
(1, 1),
(2, 1),
(3, 1),
(4, 2),
(5, 3),
(6, 3),
(7, 3);

-- Las añadimos como llamadas correctas
INSERT INTO LlamadaCorrecta (id_llamada) VALUES 
(1),
(2),
(3),
(4),
(5),
(7);

-- 13. ACCIONES DE SERVICIO Y MANTENIMIENTO
INSERT INTO AccionDeServicio (id_accion, direccion_intervencion, clase_intervencion, fecha_creacion, id_cita_previa) VALUES
(1, ROW('Calle Cliente', 1, 1, 'A', 47001, 'VLL', 'VLL')::tipo_direccion, 'primera', '2026-05-07', null),
(2, ROW('Avenida Cliente', 2, 2, 'B', 41001, 'VLL', 'VLL')::tipo_direccion, 'primera', '2026-05-08', null),
(3, ROW('Plaza Cliente', 3, 3, 'C', 28001, 'VLL', 'VLL')::tipo_direccion, 'primera', '2026-05-09', null),
(4, ROW('Calle Cliente', 1, 1, 'A', 47001, 'VLL', 'VLL')::tipo_direccion, 'primera', '2026-05-07', null),
(5, ROW('Avenida Cliente', 2, 2, 'B', 41001, 'VLL', 'VLL')::tipo_direccion, 'primera', '2026-05-07', null),
(6, ROW('Plaza Cliente', 3, 3, 'C', 28001, 'VLL', 'VLL')::tipo_direccion, 'primera', '2026-05-07', null);

INSERT INTO Mantenimiento (id_accion, tipo) VALUES
(1, 'incidencia'),
(2, 'incidencia'),
(3, 'incidencia'),
(4, 'incidencia'),
(5, 'incidencia'),
(6, 'incidencia');

-- 14. INCIDENCIAS
INSERT INTO Incidencia (id_incidencia, id_mantenimiento, codigo, descripcion, fecha, criticidad, estado, tiempo_total_resolucion_minutos, id_llamada_correcta) VALUES
(1, 1, 1, 'El router no enciende', '2026-05-07', 'alta', 'resueltaRemotamente', 25, 1),
(2, 2, 2, 'El router no responde', '2026-05-08', 'media', 'resueltaInSitu', 15, 2),
(3, 3, 3, 'El router reinicia constantemente', '2026-05-09', 'alta', 'escaladaAEspecialista', 30, 3),
(4, 4, 4, 'La conexión es muy lenta', '2026-05-07', 'baja', 'resueltaRemotamente', 20, 4),
(5, 5, 5, 'No se pueden conectar dispositivos', '2026-05-07', 'media', 'resueltaInSitu', 25, 5),
(6, 6, 6, 'Caida total del sistema', '2026-05-07', 'alta', 'escaladaAEspecialista', 10, 7);

--CASO DE USO: ATENDER LLAMADA

INSERT INTO Persona (id_persona, nombre, apellidos, telefono_contacto, direccion, nif) VALUES 
(201, 'Juan', 'Perez', 600111111, ROW('Calle Mayor', 1, 2, 'A', 47001, 'Valladolid', 'Valladolid')::tipo_direccion, '11111111A');

INSERT INTO Cliente (id_persona) VALUES (201);
INSERT INTO ClienteParticular (id_persona) VALUES (201);

INSERT INTO Contrato (id_contrato, numero_contrato, fecha_inicio, ubicacion, activo, id_cliente) VALUES 
(201, 'ESVLL111', '2020-01-01', ROW('Calle Mayor', 1, 2, 'A', 47001, 'Valladolid', 'Valladolid')::tipo_direccion, true, 201);

-- 3. CLIENTE EMPRESARIAL (Secuencia Normal extra)
-- Contrato en vigor. CIF empieza por Letra.
INSERT INTO Persona (id_persona, nombre, apellidos, telefono_contacto, direccion, nif) VALUES 
(202, 'Contacto', 'Empresa', 600222222, ROW('Poligono Norte', 10, 1, 'B', 47002, 'Valladolid', 'Valladolid')::tipo_direccion, 'B22222222');

INSERT INTO Cliente (id_persona) VALUES (202);

INSERT INTO ClienteEmpresarial (id_persona, cif, nombre_razon_social, sede, telefono) VALUES 
(202, 'B22222222', 'Empresa SA', ROW('Poligono Norte', 10, 1, 'B', 47002, 'Valladolid', 'Valladolid')::tipo_direccion, 600222222);

INSERT INTO Contrato (id_contrato, numero_contrato, fecha_inicio, ubicacion, activo, id_cliente) VALUES 
(202, 'ESVLL222', '2020-01-01', ROW('Poligono Norte', 10, 1, 'B', 47002, 'Valladolid', 'Valladolid')::tipo_direccion, true, 202);

INSERT INTO ContratoEmpresarial (id_contrato, id_cliente_empresarial) VALUES (202, 202);


-- 4. CLIENTE CON CONTRATO NO EN VIGOR (Para probar Alternativa 4a)
-- Activo = false y fecha_fin en el pasado.
INSERT INTO Persona (id_persona, nombre, apellidos, telefono_contacto, direccion, nif) VALUES 
(203, 'Luis', 'Gomez', 600333333, ROW('Calle Baja', 3, 3, 'C', 47003, 'Valladolid', 'Valladolid')::tipo_direccion, '33333333C');

INSERT INTO Cliente (id_persona) VALUES (203);
INSERT INTO ClienteParticular (id_persona) VALUES (203);

INSERT INTO Contrato (id_contrato, numero_contrato, fecha_inicio, fecha_fin, ubicacion, activo, id_cliente) VALUES 
(203, 'ESVLL333', '2020-01-01', '2023-01-01', ROW('Calle Baja', 3, 3, 'C', 47003, 'Valladolid', 'Valladolid')::tipo_direccion, false, 203);


-- Evitar que el contador automatico de postgress rompa por los inserts manuales
SELECT setval('base_id_base_seq', 100);
SELECT setval('persona_id_persona_seq', 100);
SELECT setval('turnotecnico_id_turno_seq', 100);
SELECT setval('cambiodesituaciontecnico_id_cambio_seq', 100);
SELECT setval('contrato_id_contrato_seq', 100);
SELECT setval('llamada_id_llamada_seq', 100);
SELECT setval('acciondeservicio_id_accion_seq', 100);
SELECT setval('incidencia_id_incidencia_seq', 100);