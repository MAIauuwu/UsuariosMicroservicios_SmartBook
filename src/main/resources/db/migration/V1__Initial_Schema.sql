-- V1__Initial_Schema.sql
-- Crear tabla roles
CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

-- Crear tabla users con herencia JOINED
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    rol_id INTEGER NOT NULL,
    user_type VARCHAR(50) NOT NULL DEFAULT 'User',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (rol_id) REFERENCES roles(id)
);

-- Crear tabla estudiantes (hereda de users)
CREATE TABLE estudiantes (
    id INTEGER PRIMARY KEY,
    curso_id INTEGER NOT NULL,
    matricula VARCHAR(20) NOT NULL UNIQUE,
    FOREIGN KEY (id) REFERENCES users(id)
);

-- Crear tabla cursos
CREATE TABLE cursos (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    nivel VARCHAR(50),
    anio INTEGER
);

-- Agregar constraint de curso a estudiantes
ALTER TABLE estudiantes ADD FOREIGN KEY (curso_id) REFERENCES cursos(id);

-- Crear tabla evaluaciones
CREATE TABLE evaluaciones (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(500) NOT NULL,
    curso_id INTEGER NOT NULL,
    fecha TIMESTAMP NOT NULL,
    puntaje_maximo INTEGER NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (curso_id) REFERENCES cursos(id)
);

-- Crear tabla notas
CREATE TABLE notas (
    id SERIAL PRIMARY KEY,
    estudiante_id INTEGER NOT NULL,
    evaluacion_id INTEGER NOT NULL,
    nota NUMERIC(3, 2) NOT NULL,
    FOREIGN KEY (estudiante_id) REFERENCES estudiantes(id),
    FOREIGN KEY (evaluacion_id) REFERENCES evaluaciones(id)
);

-- Crear índices
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_users_rol_id ON users(rol_id);
CREATE INDEX idx_estudiantes_matricula ON estudiantes(matricula);
CREATE INDEX idx_estudiantes_curso_id ON estudiantes(curso_id);
CREATE INDEX idx_evaluaciones_curso_id ON evaluaciones(curso_id);
CREATE INDEX idx_notas_estudiante_id ON notas(estudiante_id);
CREATE INDEX idx_notas_evaluacion_id ON notas(evaluacion_id);

