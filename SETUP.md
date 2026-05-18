# SmartBook - Usuario Microservicio

## 📋 Descripción del Proyecto

Sistema de gestión de usuarios, estudiantes, cursos, evaluaciones y notas para la plataforma SmartBook.

## ✨ Características Implementadas

✅ **Modelos de Datos Completos:**
- User (Usuario base)
- Estudiante (Hereda de User)
- Role (Roles del sistema)
- Curso
- Evaluacion
- Nota

✅ **Autenticación y Autorización:**
- JWT (JSON Web Token)
- Spring Security
- Cifrado de contraseñas con BCrypt

✅ **API REST Completa:**
- CRUD para todas las entidades
- Rutas autenticadas y públicas
- DTOs con validaciones
- Manejo de errores

✅ **Base de Datos:**
- PostgreSQL
- Migraciones Flyway
- Relaciones adecuadas entre tablas

✅ **Services con Lógica de Negocio:**
- RoleService
- UserService
- EstudianteService
- CursoService
- EvaluacionService
- NotaService
- JwtService
- AuthenticationService

✅ **Controllers REST:**
- RoleController
- UserController
- EstudianteController
- CursoController
- EvaluacionController
- NotaController
- AuthenticationController

## 🔧 Configuración Requerida

### 1. Base de Datos PostgreSQL

Asegúrate de tener PostgreSQL instalado y crear la base de datos:

```sql
CREATE DATABASE smartbook_db;
```

### 2. Variables de Entorno (application.properties)

El archivo `src/main/resources/application.properties` está configurado con:
- **URL**: jdbc:postgresql://localhost:5432/smartbook_db
- **Usuario**: postgres
- **Contraseña**: postgres

Modifica estas credenciales según tu configuración local.

### 3. Build y Ejecución

Con Maven instalado:

```bash
# Compilar
mvn clean compile

# Ejecutar tests
mvn test

# Empaquetar
mvn package

# Ejecutar la aplicación
mvn spring-boot:run
```

## 📌 Endpoints Principales

### Autenticación
- `POST /api/auth/register` - Registrar nuevo usuario
- `POST /api/auth/login` - Iniciar sesión y obtener JWT

### Roles
- `GET /api/roles` - Obtener todos los roles
- `POST /api/roles` - Crear nuevo rol
- `PUT /api/roles/{id}` - Actualizar rol
- `DELETE /api/roles/{id}` - Eliminar rol

### Usuarios
- `GET /api/users` - Obtener todos los usuarios
- `POST /api/users` - Crear nuevo usuario
- `GET /api/users/{id}` - Obtener usuario por ID
- `GET /api/users/email/{email}` - Obtener usuario por email
- `PUT /api/users/{id}` - Actualizar usuario
- `DELETE /api/users/{id}` - Eliminar usuario

### Estudiantes
- `GET /api/estudiantes` - Obtener todos los estudiantes
- `POST /api/estudiantes` - Crear nuevo estudiante
- `GET /api/estudiantes/{id}` - Obtener estudiante por ID
- `GET /api/estudiantes/matricula/{matricula}` - Obtener por matrícula
- `GET /api/estudiantes/curso/{cursoId}` - Obtener estudiantes por curso
- `PUT /api/estudiantes/{id}` - Actualizar estudiante
- `DELETE /api/estudiantes/{id}` - Eliminar estudiante

### Cursos
- `GET /api/cursos` - Obtener todos los cursos
- `POST /api/cursos` - Crear nuevo curso
- `GET /api/cursos/{id}` - Obtener curso por ID
- `GET /api/cursos/anio/{anio}` - Obtener cursos por año
- `PUT /api/cursos/{id}` - Actualizar curso
- `DELETE /api/cursos/{id}` - Eliminar curso

### Evaluaciones
- `GET /api/evaluaciones` - Obtener todas las evaluaciones
- `POST /api/evaluaciones` - Crear nueva evaluación
- `GET /api/evaluaciones/{id}` - Obtener evaluación por ID
- `GET /api/evaluaciones/curso/{cursoId}` - Obtener evaluaciones por curso
- `PUT /api/evaluaciones/{id}` - Actualizar evaluación
- `DELETE /api/evaluaciones/{id}` - Eliminar evaluación

### Notas
- `GET /api/notas` - Obtener todas las notas
- `POST /api/notas` - Crear nueva nota
- `GET /api/notas/{id}` - Obtener nota por ID
- `GET /api/notas/estudiante/{estudianteId}` - Obtener notas del estudiante
- `GET /api/notas/evaluacion/{evaluacionId}` - Obtener notas por evaluación
- `PUT /api/notas/{id}` - Actualizar nota
- `DELETE /api/notas/{id}` - Eliminar nota

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

## 📊 Estructura de la Base de Datos

### Tabla: users
- `id` (PK)
- `nombre` (VARCHAR)
- `apellido` (VARCHAR)
- `email` (VARCHAR, UNIQUE)
- `password` (VARCHAR)
- `rol_id` (FK)
- `user_type` (VARCHAR) - Para discriminación de herencia
- `created_at` (TIMESTAMP)

### Tabla: estudiantes (hereda de users)
- `id` (PK, FK de users)
- `curso_id` (FK)
- `matricula` (VARCHAR, UNIQUE)

### Tabla: roles
- `id` (PK)
- `nombre` (VARCHAR, UNIQUE)

### Tabla: cursos
- `id` (PK)
- `nombre` (VARCHAR)
- `nivel` (VARCHAR)
- `anio` (INTEGER)

### Tabla: evaluaciones
- `id` (PK)
- `nombre` (VARCHAR)
- `descripcion` (VARCHAR)
- `curso_id` (FK)
- `fecha` (TIMESTAMP)
- `puntaje_maximo` (INTEGER)
- `created_at` (TIMESTAMP)

### Tabla: notas
- `id` (PK)
- `estudiante_id` (FK)
- `evaluacion_id` (FK)
- `nota` (NUMERIC)

## 🛠️ Validaciones en DTOs

Todas las DTOs tienen validaciones:
- `@NotNull` - Campo requerido
- `@NotBlank` - No puede estar vacío
- `@Size` - Límites de longitud
- `@Email` - Formato de email válido
- `@DecimalMin/Max` - Rangos numéricos
- `@Min/Max` - Límites de números enteros

## 📝 Cambios Corregidos

✅ Corregido typo en `Role.java`: `Interger` → `Integer`
✅ Creada entidad `Evaluacion` que faltaba
✅ Implementada herencia de `Estudiante` extendiendo de `User`
✅ Removida referencia de FK directa a cursos en User
✅ Añadidas todas las validaciones en DTOs
✅ Implementada seguridad con Spring Security y JWT

## 🚀 Solución al Error de GitHub Actions

El error "Unable to find main class" se debe a que la aplicación Spring Boot necesita compilar correctamente. 

**Solución en el GitHub Actions:**

Asegúrate de que en tu workflow de GitHub Actions tengas:

```yaml
- name: Build with Maven
  run: mvn clean package -DskipTests
```

## 📚 Tecnologías Utilizadas

- **Java 21**
- **Spring Boot 3.3.0**
- **Spring Security**
- **Spring Data JPA**
- **PostgreSQL**
- **JWT (JJWT 0.12.5)**
- **Lombok**
- **Flyway** (Migraciones de BD)
- **Maven**

## ⚠️ Notas Importantes

1. La clase main app es: `org.smartbook.UsuarioServicesApplication`
2. Cambiar `spring.jpa.hibernate.ddl-auto=validate` a `update` si necesitas auto-generación
3. Las contraseñas se cifran con BCrypt
4. Los tokens JWT expiran en 24 horas (86400000 ms)
5. La herencia de Estudiante usa estrategia JOINED

## 📧 Email para más información

Si necesitas ayuda adicional, contacta al equipo de SmartBook.

---

**Última actualización:** 2024-05-17

