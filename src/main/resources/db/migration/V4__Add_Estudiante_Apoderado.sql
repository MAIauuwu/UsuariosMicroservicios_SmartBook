-- V4__Add_Estudiante_Apoderado.sql
-- Tabla de relacion entre estudiantes y apoderados

CREATE TABLE estudiante_apoderado (
    id SERIAL PRIMARY KEY,
    estudiante_id INTEGER NOT NULL,
    apoderado_id INTEGER NOT NULL,
    parentesco VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (estudiante_id) REFERENCES users(id),
    FOREIGN KEY (apoderado_id) REFERENCES users(id),
    UNIQUE(estudiante_id, apoderado_id)
);

CREATE INDEX idx_ea_estudiante ON estudiante_apoderado(estudiante_id);
CREATE INDEX idx_ea_apoderado ON estudiante_apoderado(apoderado_id);
