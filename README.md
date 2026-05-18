# SmartBook - Usuario Microservicio

## 📋 Descripción

Sistema de gestión de usuarios, estudiantes, docentes, apoderados, cursos, evaluaciones y notas para la plataforma SmartBook.

## 🏗️ Entidades del Microservicio de Usuarios

| Entidad | Descripción |
|---------|-------------|
| **User** | Usuario base del sistema (login, autenticación) |
| **Role** | Roles del sistema (ADMINISTRADOR, DOCENTE, ESTUDIANTE, APODERADO) |
| **Estudiante** | Extiende de User - Alumnos con matrícula y curso |
| **Docente** | Extiende de User - Profesores con colegiatura y especialidad |
| **Apoderado** | Extiende de User - Padres/tutores con parentesco y documento |
| **Perfil** | Información adicional de cualquier usuario (biografía, foto, contacto) |

## 🚀 Quick Start

### Con Docker (Recomendado)
```bash
docker-compose up -d
```

### Local (Requiere PostgreSQL)
```bash
# Crear base de datos
createdb smartbook_db

# Compilar y ejecutar
./mvnw.cmd clean package
java -jar target/auth-service-1.0-SNAPSHOT.jar
```

## 📚 Documentación API

### Swagger UI
Una vez ejecutando la aplicación:
```
http://localhost:8080/swagger-ui.html
```

### Endpoints principales

#### 🔐 Autenticación
| Método | Ruta | Descripción | Auth |
|--------|------|-------------|------|
| POST | `/api/auth/register` | Registrar usuario | ❌ |
| POST | `/api/auth/login` | Iniciar sesión | ❌ |

#### 👤 Usuarios
| Método | Ruta | Descripción | Auth |
|--------|------|-------------|------|
| GET | `/api/users` | Listar usuarios | ✅ |
| POST | `/api/users` | Crear usuario | ✅ |
| GET | `/api/users/{id}` | Obtener usuario por ID | ✅ |
| GET | `/api/users/email/{email}` | Obtener usuario por email | ✅ |
| PUT | `/api/users/{id}` | Actualizar usuario | ✅ |
| DELETE | `/api/users/{id}` | Eliminar usuario | ✅ |

#### 🎭 Roles
| Método | Ruta | Descripción | Auth |
|--------|------|-------------|------|
| GET | `/api/roles` | Listar roles | ✅ |
| POST | `/api/roles` | Crear rol | ✅ |
| PUT | `/api/roles/{id}` | Actualizar rol | ✅ |
| DELETE | `/api/roles/{id}` | Eliminar rol | ✅ |

#### 🎓 Estudiantes
| Método | Ruta | Descripción | Auth |
|--------|------|-------------|------|
| GET | `/api/estudiantes` | Listar estudiantes | ✅ |
| POST | `/api/estudiantes` | Crear estudiante | ✅ |
| GET | `/api/estudiantes/{id}` | Obtener por ID | ✅ |
| GET | `/api/estudiantes/matricula/{matricula}` | Obtener por matrícula | ✅ |
| GET | `/api/estudiantes/curso/{cursoId}` | Obtener por curso | ✅ |
| PUT | `/api/estudiantes/{id}` | Actualizar estudiante | ✅ |
| DELETE | `/api/estudiantes/{id}` | Eliminar estudiante | ✅ |

#### 👨‍🏫 Docentes
| Método | Ruta | Descripción | Auth |
|--------|------|-------------|------|
| GET | `/api/docentes` | Listar docentes | ✅ |
| POST | `/api/docentes` | Crear docente | ✅ |
| GET | `/api/docentes/{id}` | Obtener por ID | ✅ |
| GET | `/api/docentes/colegiatura/{numero}` | Obtener por colegiatura | ✅ |
| GET | `/api/docentes/especialidad/{especialidad}` | Obtener por especialidad | ✅ |
| PUT | `/api/docentes/{id}` | Actualizar docente | ✅ |
| DELETE | `/api/docentes/{id}` | Eliminar docente | ✅ |

#### 👨‍👩‍👧 Apoderados
| Método | Ruta | Descripción | Auth |
|--------|------|-------------|------|
| GET | `/api/apoderados` | Listar apoderados | ✅ |
| POST | `/api/apoderados` | Crear apoderado | ✅ |
| GET | `/api/apoderados/{id}` | Obtener por ID | ✅ |
| GET | `/api/apoderados/documento/{documento}` | Obtener por documento | ✅ |
| GET | `/api/apoderados/parentesco/{parentesco}` | Obtener por parentesco | ✅ |
| PUT | `/api/apoderados/{id}` | Actualizar apoderado | ✅ |
| DELETE | `/api/apoderados/{id}` | Eliminar apoderado | ✅ |

#### 📋 Perfiles
| Método | Ruta | Descripción | Auth |
|--------|------|-------------|------|
| POST | `/api/perfiles` | Crear perfil | ✅ |
| GET | `/api/perfiles/{id}` | Obtener por ID | ✅ |
| GET | `/api/perfiles/user/{userId}` | Obtener por usuario | ✅ |
| PUT | `/api/perfiles/{id}` | Actualizar perfil | ✅ |
| DELETE | `/api/perfiles/{id}` | Eliminar perfil | ✅ |

#### 📚 Cursos
| Método | Ruta | Descripción | Auth |
|--------|------|-------------|------|
| GET | `/api/cursos` | Listar cursos | ✅ |
| POST | `/api/cursos` | Crear curso | ✅ |
| GET | `/api/cursos/{id}` | Obtener por ID | ✅ |
| GET | `/api/cursos/anio/{anio}` | Obtener por año | ✅ |
| PUT | `/api/cursos/{id}` | Actualizar curso | ✅ |
| DELETE | `/api/cursos/{id}` | Eliminar curso | ✅ |

#### 📝 Evaluaciones
| Método | Ruta | Descripción | Auth |
|--------|------|-------------|------|
| GET | `/api/evaluaciones` | Listar evaluaciones | ✅ |
| POST | `/api/evaluaciones` | Crear evaluación | ✅ |
| GET | `/api/evaluaciones/{id}` | Obtener por ID | ✅ |
| GET | `/api/evaluaciones/curso/{cursoId}` | Obtener por curso | ✅ |
| PUT | `/api/evaluaciones/{id}` | Actualizar evaluación | ✅ |
| DELETE | `/api/evaluaciones/{id}` | Eliminar evaluación | ✅ |

#### 📊 Notas
| Método | Ruta | Descripción | Auth |
|--------|------|-------------|------|
| GET | `/api/notas` | Listar notas | ✅ |
| POST | `/api/notas` | Crear nota | ✅ |
| GET | `/api/notas/{id}` | Obtener por ID | ✅ |
| GET | `/api/notas/estudiante/{estudianteId}` | Obtener por estudiante | ✅ |
| GET | `/api/notas/evaluacion/{evaluacionId}` | Obtener por evaluación | ✅ |
| PUT | `/api/notas/{id}` | Actualizar nota | ✅ |
| DELETE | `/api/notas/{id}` | Eliminar nota | ✅ |

## 🔧 Configuración

### Variables de Entorno

| Variable | Default | Descripción |
|----------|---------|-------------|
| `SPRING_DATASOURCE_URL` | `jdbc:postgresql://localhost:5432/smartbook_db` | URL de PostgreSQL |
| `SPRING_DATASOURCE_USERNAME` | `postgres` | Usuario BD |
| `SPRING_DATASOURCE_PASSWORD` | `postgres` | Contraseña BD |
| `JWT_SECRET` | `MiClaveSecreta...` | Clave para firmar JWT |
| `CORS_ALLOWED_ORIGINS` | `*` | Orígenes permitidos |

### Perfiles

| Perfil | Descripción |
|--------|-------------|
| `default` | PostgreSQL local (smartbook_db) |
| `dev` | PostgreSQL dev (smartbook_db_dev) con logs detallados |
| `test` | PostgreSQL test (smartbook_test) |
| `prod` | Producción con variables de entorno |

## 🏥 Health Checks

```bash
curl http://localhost:8080/actuator/health
curl http://localhost:8080/actuator/info
curl http://localhost:8080/actuator/metrics
```

## 🔐 Autenticación con JWT

### 1. Registrar un usuario
```json
POST /api/auth/register
{
  "nombre": "Juan",
  "apellido": "Pérez",
  "email": "juan@example.com",
  "password": "password123"
}

Response:
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "message": "Usuario registrado exitosamente"
}
```

### 2. Iniciar sesión
```json
POST /api/auth/login
{
  "email": "juan@example.com",
  "password": "password123"
}

Response:
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "message": "Autenticación exitosa"
}
```

### 3. Usar el token en las solicitudes
Agrega el token en el header `Authorization`:
```
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

## 🧪 Tests

```bash
./mvnw.cmd test
```

## 📦 Estructura del Proyecto

```
src/main/java/org/smartbook/
├── config/          # Configuración (Security, CORS, OpenAPI)
├── controller/      # REST Controllers
├── dto/             # Data Transfer Objects
├── exception/       # Manejo de errores
├── model/           # Entidades JPA
├── repository/      # Repositorios Spring Data
└── service/         # Lógica de negocio
```

## 🛠️ Tecnologías

- Java 21 + Spring Boot 3.3.0
- Spring Security + JWT
- PostgreSQL + Flyway
- Lombok + Validation
- OpenAPI/Swagger
- Docker + Docker Compose
- Maven

## 📝 Licencia

Apache 2.0
