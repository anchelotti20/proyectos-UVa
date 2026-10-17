DELETE FROM DISPONIBILIDADES;
DELETE FROM VINCULACIONESCONLAEMPRESA;
DELETE FROM ROLESENEMPRESA;
DELETE FROM EMPLEADOSENNEGOCIOSSUSCRITOS;

-- Empleados en negocios suscritos
INSERT INTO EMPLEADOSENNEGOCIOSSUSCRITOS (Nif, Nombre, Password, Email) VALUES 
('85783884W', 'Isabel Gimenez Saez', 'hC4dP1uK', 'zeaug2tijm@techie.com'),   
('63278116X', 'Maria Angeles Salas Sole', 'fB5dB3iF', 'uom6jcmd@earthling.net'),     
('42854860H', 'Guillermo Lozano Palma', 'hB3gN1fL', 'v4s5uv3i@mail.com'),
('12345678Z', 'Carlos Ruiz Mateos', 'aA1bB2cC', 'cruizm@techie.com'),      
('87654321X', 'Laura Torres Sanz', 'dD3eE4fF', 'ltorres@earthling.net'),  
('11223344B', 'David Garcia Lopez', 'gG5hH6iI', 'dgarcia@mail.com'),     
('55667788Z', 'Elena Martin Diaz', 'jJ7kK8lL', 'emartin@techie.com');      

-- Roles en empresa
INSERT INTO ROLESENEMPRESA (ComienzoEnRol, Nif, Rol) VALUES 
('2021-09-15', '85783884W', 1),
('2022-02-01', '63278116X', 2),
('2023-06-12', '42854860H', 3),
('2024-01-10', '12345678Z', 2), 
('2024-03-15', '87654321X', 3), 
('2023-11-20', '11223344B', 1), 
('2022-05-05', '55667788Z', 1);

-- Vinculaciones con la empresa
INSERT INTO VINCULACIONESCONLAEMPRESA (Inicio, Nif, Vinculo) VALUES
('2021-09-15', '85783884W', 1), -- Vinculado normal
('2025-11-01', '63278116X', 2), -- En Erte (Causa inactividad)
('2023-06-12', '42854860H', 1), -- Vinculado normal
('2024-01-10', '12345678Z', 1), -- Vinculado normal
('2024-03-15', '87654321X', 1), -- Vinculado normal
('2023-11-20', '11223344B', 1), -- Vinculado normal 
('2026-01-15', '55667788Z', 3); -- Fuera de la empresa (Causa inactividad)

-- Disponibilidades
INSERT INTO DISPONIBILIDADES (Comienzo, Fin, Nif, Disponibilidad) VALUES 
('2026-01-08', NULL, '85783884W', 1), -- Trabajando (Disponible)
('2025-10-15', NULL, '63278116X', 1), -- Trabajando (Disponible)
('2026-03-21', NULL, '42854860H', 2), -- De Vacaciones (Causa de inactividad)
('2025-12-01', NULL, '12345678Z', 1), -- Trabajando (Disponible)
('2026-02-10', NULL, '87654321X', 1), -- Trabajando (Disponible)
('2026-04-01', NULL, '11223344B', 3), -- Baja temporal (Causa inactividad) 
('2025-06-01', NULL, '55667788Z', 1); -- Trabajando (Disponible) 
-- =============================================================================
-- RESUMEN DE DATOS PARA LAS PRUEBAS:
-- 1. EXITO (Rol 1): NIF '85783884W' / Contraseña 'hC4dP1uK'
-- 2. ERROR CONTRASEÑA: NIF '85783884W' / Contraseña 'error'
-- 3. ERROR NO EXISTE: NIF '99999999Z' / Contraseña 'cualquiera'
-- 4. ERROR INACTIVO (Vínculo 2 - ERTE): NIF '63278116X' / Contraseña 'fB5dB3iF'
-- 5. ERROR INACTIVO (Disponibilidad 2 - VACACIONES): NIF '42854860H' / Contraseña 'hB3gN1fL'
-- 6. EXITO (Rol 2, Disp 1, Vinc 1): NIF '12345678Z' / Contraseña 'aA1bB2cC'
-- 7. EXITO (Rol 3, Disp 1, Vinc 1): NIF '87654321X' / Contraseña 'dD3eE4fF'
-- 8. ERROR INACTIVO (Disponibilidad 3 - Baja temporal): NIF '11223344B' / Contraseña 'gG5hH6iI'
-- 9. ERROR INACTIVO (Vínculo 3- Fuera empresa): NIF '55667788Z' / Contraseña 'jJ7kK8lL'
-- =============================================================================