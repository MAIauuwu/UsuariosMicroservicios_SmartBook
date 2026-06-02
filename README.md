# SmartBook - Microservicio de Usuarios (Backend)

## Descripcion

Microservicio Backend con **Spring Boot** para gestion de usuarios, estudiantes, docentes, apoderados, cursos, evaluaciones y notas.

## Arquitectura

```
┌─────────────────┐     ┌─────────────────┐
│   Backend       │────▶│   PostgreSQL    │
│   Spring Boot   │     │   Database      │
│   Port: 8083    │     │   Port: 5432    │
└─────────────────┘     └─────────────────┘
```

## Quick Start

### Con Docker (Recomendado)
```bash
docker-compose up -d
```

Accede a:
- **Backend API**: http://localhost:8083
- **Swagger UI**: http://localhost:8083/swagger-ui.html

### Local (Requiere PostgreSQL)
```bash
# Crear base de datos
createdb smartbook_db

# Compilar y ejecutar
./mvnw.cmd clean package
java -jar target/auth-service-1.0-SNAPSHOT.jar
```

## Entidades del Microservicio de Usuarios

| Entidad | Descripcion |
|---------|-------------|
| **User** | Usuario base del sistema (login, autenticacion) |
| **Role** | Roles del sistema (ADMINISTRADOR, DOCENTE, ESTUDIANTE, APODERADO) |
| **Estudiante** | Extiende de User - Alumnos con matricula y curso |
| **Docente** | Extiende de User - Profesores con colegiatura y especialidad |
| **Apoderado** | Extiende de User - Padres/tutores con parentesco y documento |
| **Perfil** | Informacion adicional de cualquier usuario (biografia, foto, contacto) |

## Documentacion API

### Swagger UI
```
http://localhost:8083/swagger-ui.html
```

### Endpoints principales

#### Autenticacion
| Metodo | Ruta | Descripcion | Auth |
|--------|------|-------------|------|
| POST | `/api/auth/register` | Registrar usuario | No |
| POST | `/api/auth/login` | Iniciar sesion | No |

#### Usuarios
| Metodo | Ruta | Descripcion | Auth |
|--------|------|-------------|------|
| GET | `/api/users` | Listar usuarios | Si |
| POST | `/api/users` | Crear usuario | Si |
| GET | `/api/users/{id}` | Obtener usuario por ID | Si |
| GET | `/api/users/email/{email}` | Obtener usuario por email | Si |
| PUT | `/api/users/{id}` | Actualizar usuario | Si |
| DELETE | `/api/users/{id}` | Eliminar usuario | Si |

#### Roles
| Metodo | Ruta | Descripcion | Auth |
|--------|------|-------------|------|
| GET | `/api/roles` | Listar roles | Si |
| POST | `/api/roles` | Crear rol | Si |
| PUT | `/api/roles/{id}` | Actualizar rol | Si |
| DELETE | `/api/roles/{id}` | Eliminar rol | Si |

#### Estudiantes
| Metodo | Ruta | Descripcion | Auth |
|--------|------|-------------|------|
| GET | `/api/estudiantes` | Listar estudiantes | Si |
| POST | `/api/estudiantes` | Crear estudiante | Si |
| GET | `/api/estudiantes/{id}` | Obtener por ID | Si |
| GET | `/api/estudiantes/matricula/{matricula}` | Obtener por matricula | Si |
| GET | `/api/estudiantes/curso/{cursoId}` | Obtener por curso | Si |
| PUT | `/api/estudiantes/{id}` | Actualizar estudiante | Si |
| DELETE | `/api/estudiantes/{id}` | Eliminar estudiante | Si |

#### Docentes
| Metodo | Ruta | Descripcion | Auth |
|--------|------|-------------|------|
| GET | `/api/docentes` | Listar docentes | Si |
| POST | `/api/docentes` | Crear docente | Si |
| GET | `/api/docentes/{id}` | Obtener por ID | Si |
| GET | `/api/docentes/colegiatura/{numero}` | Obtener por colegiatura | Si |
| GET | `/api/docentes/especialidad/{especialidad}` | Obtener por especialidad | Si |
| PUT | `/api/docentes/{id}` | Actualizar docente | Si |
| DELETE | `/api/docentes/{id}` | Eliminar docente | Si |

#### Apoderados
| Metodo | Ruta | Descripcion | Auth |
|--------|------|-------------|------|
| GET | `/api/apoderados` | Listar apoderados | Si |
| POST | `/api/apoderados` | Crear apoderado | Si |
| GET | `/api/apoderados/{id}` | Obtener por ID | Si |
| GET | `/api/apoderados/documento/{documento}` | Obtener por documento | Si |
| GET | `/api/apoderados/parentesco/{parentesco}` | Obtener por parentesco | Si |
| PUT | `/api/apoderados/{id}` | Actualizar apoderado | Si |
| DELETE | `/api/apoderados/{id}` | Eliminar apoderado | Si |

#### Perfiles
| Metodo | Ruta | Descripcion | Auth |
|--------|------|-------------|------|
| POST | `/api/perfiles` | Crear perfil | Si |
| GET | `/api/perfiles/{id}` | Obtener por ID | Si |
| GET | `/api/perfiles/user/{userId}` | Obtener por usuario | Si |
| PUT | `/api/perfiles/{id}` | Actualizar perfil | Si |
| DELETE | `/api/perfiles/{id}` | Eliminar perfil | Si |

#### Cursos
| Metodo | Ruta | Descripcion | Auth |
|--------|------|-------------|------|
| GET | `/api/cursos` | Listar cursos | Si |
| POST | `/api/cursos` | Crear curso | Si |
| GET | `/api/cursos/{id}` | Obtener por ID | Si |
| GET | `/api/cursos/anio/{anio}` | Obtener por anio | Si |
| PUT | `/api/cursos/{id}` | Actualizar curso | Si |
| DELETE | `/api/cursos/{id}` | Eliminar curso | Si |

#### Evaluaciones
| Metodo | Ruta | Descripcion | Auth |
|--------|------|-------------|------|
| GET | `/api/evaluaciones` | Listar evaluaciones | Si |
| POST | `/api/evaluaciones` | Crear evaluacion | Si |
| GET | `/api/evaluaciones/{id}` | Obtener por ID | Si |
| GET | `/api/evaluaciones/curso/{cursoId}` | Obtener por curso | Si |
| PUT | `/api/evaluaciones/{id}` | Actualizar evaluacion | Si |
| DELETE | `/api/evaluaciones/{id}` | Eliminar evaluacion | Si |

#### Notas
| Metodo | Ruta | Descripcion | Auth |
|--------|------|-------------|------|
| GET | `/api/notas` | Listar notas | Si |
| POST | `/api/notas` | Crear nota | Si |
| GET | `/api/notas/{id}` | Obtener por ID | Si |
| GET | `/api/notas/estudiante/{estudianteId}` | Obtener por estudiante | Si |
| GET | `/api/notas/evaluacion/{evaluacionId}` | Obtener por evaluacion | Si |
| PUT | `/api/notas/{id}` | Actualizar nota | Si |
| DELETE | `/api/notas/{id}` | Eliminar nota | Si |

## Configuracion

### Variables de Entorno

| Variable | Default | Descripcion |
|----------|---------|-------------|
| `SPRING_DATASOURCE_URL` | `jdbc:postgresql://localhost:5432/smartbook_db` | URL de PostgreSQL |
| `SPRING_DATASOURCE_USERNAME` | `postgres` | Usuario BD |
| `SPRING_DATASOURCE_PASSWORD` | `postgres` | Contraseña BD |
| `JWT_SECRET` | `MiClaveSecreta...` | Clave para firmar JWT |
| `CORS_ALLOWED_ORIGINS` | `*` | Origines permitidos |

### Perfiles

| Perfil | Descripcion |
|--------|-------------|
| `default` | PostgreSQL local (smartbook_db) |
| `dev` | PostgreSQL dev (smartbook_db_dev) con logs detallados |
| `test` | PostgreSQL test (smartbook_test) |
| `prod` | Produccion con variables de entorno |

## Health Checks

```bash
curl http://localhost:8083/actuator/health
curl http://localhost:8083/actuator/info
curl http://localhost:8083/actuator/metrics
```

## Autenticacion con JWT

### 1. Registrar un usuario
```json
POST /api/auth/register
{
  "nombre": "Juan",
  "apellido": "Perez",
  "email": "juan@example.com",
  "password": "password123"
}

Response:
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "message": "Usuario registrado exitosamente"
}
```

### 2. Iniciar sesion
```json
POST /api/auth/login
{
  "email": "juan@example.com",
  "password": "password123"
}

Response:
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "message": "Autenticacion exitosa"
}
```

### 3. Usar el token en las solicitudes
Agrega el token en el header `Authorization`:
```
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

## Tests

```bash
./mvnw.cmd test
```

## Estructura del Proyecto

```
src/main/java/org/smartbook/
├── config/          # Configuracion (Security, CORS, OpenAPI)
├── controller/      # REST Controllers
├── dto/             # Data Transfer Objects
├── exception/       # Manejo de errores
├── model/           # Entidades JPA
├── repository/      # Repositorios Spring Data
└── service/         # Logica de negocio
```

## Tecnologias

- Java 21 + Spring Boot 3.3.0
- Spring Security + JWT
- PostgreSQL + Flyway
- Lombok + Validation
- OpenAPI/Swagger
- Maven

## Licencia

Apache 2.0
