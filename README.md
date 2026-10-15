# Customer API

Microservicio reactivo para administrar clientes con Spring Boot, Spring WebFlux y MongoDB.

## Requisitos

- Java 17
- Maven 3.6 o superior
- MongoDB disponible en `mongodb://localhost:27017`

La aplicacion usa la base de datos `bootcampbd` y escucha en el puerto `8085`.

## Levantar el proyecto

Inicia MongoDB y, desde la raiz del proyecto, ejecuta:

```bash
mvn spring-boot:run
```

Para generar y ejecutar el JAR:

```bash
mvn clean package
java -jar target/ms-customer-mongodb-webflux-0.0.1-SNAPSHOT.jar
```

La API queda disponible en `http://localhost:8085/api`. La documentacion interactiva de Swagger UI esta en `http://localhost:8085/swagger-ui/index.html`.

## Endpoints

Los ejemplos usan el cliente con ID `65f1a2b3c4d5e6f789012345`. MongoDB genera el `id` al crear el documento; no se envia en las solicitudes de creacion ni de actualizacion.

### Listar clientes

`GET /api/customers`

**Respuesta `200 OK`:**

```json
[
  {
    "id": "65f1a2b3c4d5e6f789012345",
    "nombre": "Ana",
    "apellido": "Perez",
    "correo": "ana.perez@example.com",
    "dni": "12345678"
  }
]
```

Si no hay clientes, la respuesta es una lista vacia: `[]`.

### Obtener un cliente por ID

`GET /api/customers/{id}`

Ejemplo: `GET /api/customers/65f1a2b3c4d5e6f789012345`

**Respuesta `200 OK`:**

```json
{
  "id": "65f1a2b3c4d5e6f789012345",
  "nombre": "Ana",
  "apellido": "Perez",
  "correo": "ana.perez@example.com",
  "dni": "12345678"
}
```

Si el ID no existe, responde `404 Not Found`.

### Crear un cliente

`POST /api/customers`

**Solicitud:**

```json
{
  "nombre": "Ana",
  "apellido": "Perez",
  "correo": "ana.perez@example.com",
  "dni": "12345678"
}
```

**Respuesta `201 Created`:**

```json
{
  "id": "65f1a2b3c4d5e6f789012345",
  "nombre": "Ana",
  "apellido": "Perez",
  "correo": "ana.perez@example.com",
  "dni": "12345678"
}
```

### Actualizar un cliente

`PUT /api/customers/{id}`

Se actualizan unicamente los campos incluidos en el cuerpo. Los campos omitidos o con valor `null` conservan su valor actual.

**Solicitud de ejemplo (actualiza solo el correo):**

```json
{
  "correo": "ana.nueva@example.com"
}
```

**Respuesta `200 OK`:**

```json
{
  "id": "65f1a2b3c4d5e6f789012345",
  "nombre": "Ana",
  "apellido": "Perez",
  "correo": "ana.nueva@example.com",
  "dni": "12345678"
}
```

Si el ID no existe, responde `404 Not Found`.

### Eliminar un cliente

`DELETE /api/customers/{id}`

Ejemplo: `DELETE /api/customers/65f1a2b3c4d5e6f789012345`

**Respuesta:** `200 OK`, sin cuerpo.
