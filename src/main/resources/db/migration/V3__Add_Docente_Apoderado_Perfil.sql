-- V3__Add_Docente_Apoderado_Perfil.sql
-- Crear tabla docentes (hereda de users)
CREATE TABLE docentes (
    id INTEGER PRIMARY KEY,
    numero_colegiatura VARCHAR(20) NOT NULL UNIQUE,
    especialidad VARCHAR(100),
    grado_academico VARCHAR(50),
    FOREIGN KEY (id) REFERENCES users(id)
);

-- Crear tabla apoderados (hereda de users)
CREATE TABLE apoderados (
    id INTEGER PRIMARY KEY,
    parentesco VARCHAR(50) NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    direccion VARCHAR(200),
    documento_identidad VARCHAR(20) NOT NULL UNIQUE,
    FOREIGN KEY (id) REFERENCES users(id)
);

-- Crear tabla perfiles
CREATE TABLE perfiles (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL UNIQUE,
    biografia VARCHAR(500),
    foto_url VARCHAR(255),
    telefono VARCHAR(20),
    direccion VARCHAR(200),
    ciudad VARCHAR(100),
    pais VARCHAR(100),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Crear índices
CREATE INDEX idx_docentes_colegiatura ON docentes(numero_colegiatura);
CREATE INDEX idx_docentes_especialidad ON docentes(especialidad);
CREATE INDEX idx_apoderados_documento ON apoderados(documento_identidad);
CREATE INDEX idx_apoderados_parentesco ON apoderados(parentesco);
CREATE INDEX idx_perfiles_user_id ON perfiles(user_id);
