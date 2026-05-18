export interface User {
  id: number;
  nombre: string;
  apellido: string;
  email: string;
  rolId: number;
  createdAt?: string;
}

export interface Role {
  id: number;
  nombre: string;
}

export interface Estudiante {
  id: number;
  nombre: string;
  apellido: string;
  email: string;
  rolId: number;
  cursoId: number;
  matricula: string;
  createdAt?: string;
}

export interface Docente {
  id: number;
  nombre: string;
  apellido: string;
  email: string;
  rolId: number;
  numeroColegiatura: string;
  especialidad?: string;
  gradoAcademico?: string;
  createdAt?: string;
}

export interface Apoderado {
  id: number;
  nombre: string;
  apellido: string;
  email: string;
  rolId: number;
  parentesco: string;
  telefono: string;
  direccion?: string;
  documentoIdentidad: string;
  createdAt?: string;
}

export interface Perfil {
  id: number;
  userId: number;
  biografia?: string;
  fotoUrl?: string;
  telefono?: string;
  direccion?: string;
  ciudad?: string;
  pais?: string;
  createdAt?: string;
  updatedAt?: string;
}

export interface Curso {
  id: number;
  nombre: string;
  nivel: string;
  anio: number;
}

export interface Evaluacion {
  id: number;
  nombre: string;
  descripcion: string;
  cursoId: number;
  fecha: string;
  puntajeMaximo: number;
  createdAt?: string;
}

export interface Nota {
  id: number;
  estudianteId: number;
  evaluacionId: number;
  nota: number;
}

export interface AuthResponse {
  token: string;
  message: string;
}

export interface LoginRequest {
  email: string;
  password: string;
}

export interface RegisterRequest {
  nombre: string;
  apellido: string;
  email: string;
  password: string;
}
