/**
* Script creacion de la base de datos
* 
*/

DROP DATABASE IF EXISTS fantasy11;
CREATE DATABASE fantasy11;
USE fantasy11;

-- ==========================================
-- BORRADO DE TABLAS (Orden inverso a la creación) por si ya existiesen
-- ==========================================
DROP TABLE IF EXISTS ActuacionPartido;
DROP TABLE IF EXISTS EquipoJugador;
DROP TABLE IF EXISTS PuntuacionJornada;
DROP TABLE IF EXISTS Jugador;
DROP TABLE IF EXISTS Partido;
DROP TABLE IF EXISTS Equipo;
DROP TABLE IF EXISTS Jornada;
DROP TABLE IF EXISTS Usuario;
DROP TABLE IF EXISTS Club;
DROP TABLE IF EXISTS Liga;

-- ==========================================
-- TABLAS INDEPENDIENTES (Sin Claves Foráneas)
-- ==========================================

-- Liga General de todos los usuarios
CREATE TABLE Liga (
    id_liga INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    numeroJugadores INT
);

-- Clubes 
CREATE TABLE Club (
    id_club INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    foto VARCHAR(255)
);

-- ==========================================
-- TABLAS DE PRIMER NIVEL DE DEPENDENCIA
-- ==========================================

-- Usuarios, pertenecen a la liga general y tienen un equipo
CREATE TABLE Usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    usuario VARCHAR(50) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    puntos INT DEFAULT 0,
    puesto INT,
    id_liga INT,
    FOREIGN KEY (id_liga) REFERENCES Liga(id_liga) ON DELETE SET NULL
);

-- Jornadas, semanales
CREATE TABLE Jornada (
    numero INT PRIMARY KEY,
    id_liga INT,
    FOREIGN KEY (id_liga) REFERENCES Liga(id_liga) ON DELETE CASCADE
);

-- ==========================================
--  TABLAS DE SEGUNDO NIVEL
-- ==========================================

-- Equipo formado por 11 jugadores, pertenece a un usuario y tienen alineacion
CREATE TABLE Equipo (
    id_equipo INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    formacion VARCHAR(20) DEFAULT '4-3-3',
    id_usuario INT UNIQUE, 
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario) ON DELETE CASCADE
);

-- Partidos entre 2 clubes y se jugan por jornadas
CREATE TABLE Partido (
    id_partido INT AUTO_INCREMENT PRIMARY KEY,
    ganador VARCHAR(50),
    golesLocal INT,
    golesVisitante INT,
    numero_jornada INT,
    id_club_local INT,
    id_club_visitante INT,
    FOREIGN KEY (numero_jornada) REFERENCES Jornada(numero) ON DELETE CASCADE,
    FOREIGN KEY (id_club_local) REFERENCES Club(id_club),
    FOREIGN KEY (id_club_visitante) REFERENCES Club(id_club)
);

-- =======================
-- TABLAS DE TERCER NIVEL 
-- =======================

-- Jugadores, pertenecen a un club y a equipos de jugadores, ademas tienen una actuacion
CREATE TABLE Jugador (
    id INT PRIMARY KEY, 
    nombre VARCHAR(100) NOT NULL,
    posicion VARCHAR(50),
    nacionalidad VARCHAR(50),
    foto VARCHAR(255),
    id_club INT,
    FOREIGN KEY (id_club) REFERENCES Club(id_club)
);

-- Es la puntuacion que hace un usuario en una jornada en concreto
CREATE TABLE PuntuacionJornada (
    id_puntuacion INT AUTO_INCREMENT PRIMARY KEY,
    puntuacionJornada INT,
    id_usuario INT,
    numero_jornada INT,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario) ON DELETE CASCADE,
    FOREIGN KEY (numero_jornada) REFERENCES Jornada(numero) ON DELETE CASCADE
);

-- Permite que haya el mismo jugador en varios equipos
CREATE TABLE EquipoJugador (
    id_equipo INT,
    id_jugador INT,
    slot INT NOT NULL, 
    PRIMARY KEY (id_equipo, slot),
    FOREIGN KEY (id_equipo) REFERENCES Equipo(id_equipo) ON DELETE CASCADE,
    FOREIGN KEY (id_jugador) REFERENCES Jugador(id) ON DELETE CASCADE
);

-- ==========================================
-- TABLAS DE CUARTO NIVEL
-- ==========================================

-- Guarda la puntuacion de un jugador en un partido
CREATE TABLE ActuacionPartido (
    id_actuacion INT AUTO_INCREMENT PRIMARY KEY,
    goles INT DEFAULT 0,
    minutos INT DEFAULT 0,
    asistencias INT DEFAULT 0,
    tarjetasAmarillas INT DEFAULT 0,
    tarjetasRojas INT DEFAULT 0,
    golesEnPropia INT DEFAULT 0,
    golesEnContra INT DEFAULT 0,
    puntos INT DEFAULT 0,
    id_partido INT,
    id_jugador INT,
    FOREIGN KEY (id_partido) REFERENCES Partido(id_partido) ON DELETE CASCADE,
    FOREIGN KEY (id_jugador) REFERENCES Jugador(id) ON DELETE CASCADE
);

