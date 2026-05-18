-- V2__Insert_Initial_Data.sql
-- Insertar roles iniciales
INSERT INTO roles (nombre) VALUES ('ADMINISTRADOR');
INSERT INTO roles (nombre) VALUES ('DOCENTE');
INSERT INTO roles (nombre) VALUES ('USUARIO');

-- Insertar un curso de ejemplo
INSERT INTO cursos (nombre, nivel, anio) VALUES ('Matemáticas Básicas', 'Principiante', 2024);
INSERT INTO cursos (nombre, nivel, anio) VALUES ('Historia Mundial', 'Intermedio', 2024);
INSERT INTO cursos (nombre, nivel, anio) VALUES ('Física Avanzada', 'Avanzado', 2024);

