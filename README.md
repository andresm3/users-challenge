# users-challenge

## Descripción

API Backend creacion de usuarios.

## Requisitos Previos

> [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

> [Maven 3.9.4](https://archive.apache.org/dist/maven/maven-3/3.9.4/binaries/)

> [Postman](https://www.postman.com/downloads/)



## Ejecutar

### Paso 1: Clonar el repositorio

```sh
git clone https://github.com/andresm3/users-challenge.git
```

### Paso 2: Descargar las dependencias con maven
Con Maven:

```sh
mvn clean install
```
Con Maven Wrapper:

```sh
.\mvnw.cmd clean install
```

#### Paso 3: Configurar los properties

- El parametro regex para el password es configurable, actualmente restringe lo siguiente:
  - At least one upper case English letter, (?=.*?[A-Z])
  - At least one lower case English letter, (?=.*?[a-z])
  - At least one digit, (?=.*?[0-9])
  - At least one special character, (?=.*?[#?!@$%^&*-])
  - Minimum eight in length .{8,} (with the anchors)

Nos vamos al path `src/main/resources` y nos aseguramos de que el archivo `application.properties` con la siguiente configuración:

```properties
spring.application.name=users
spring.datasource.url=jdbc:h2:mem:temporaldb;NON_KEYWORDS=user
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.show-sql=true
spring.h2.console.enabled=true

# springdoc configuration
springdoc.swagger-ui.path=/swagger-ui
springdoc.api-docs.enabled=true

# app configuration
app.password.regex=^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$

```


### Paso 4: Ejecutar la aplicación

En nuestro IDE (IntelliJ IDEA, Eclipse, VSCode) ejecutamos la clase `UsersApplication.java`

En caso que se requiera ejecutar la aplicación por consola con Maven:

```ssh
mvn spring-boot:run
```



### Paso 5: Documentación Swagger

Abrimos en el navegador la url http://localhost:8080/swagger-ui y nos debe mostrar la documentación swagger de la aplicación.


### Paso 6: Probar el servicio con Postman

Abrimos Postman y configuramos lo siguiente:

Url: `http://localhost:8080/api/users`

Método: `POST`

Body: `raw` y `JSON`

```json
{
  "name": "Lolo",
  "email": "lolo@crema.com",
  "password": "Univer$itar10",
  "phones": [
    {
      "number": "987987987",
      "citycode": "11",
      "countrycode": "51"
    }
  ]
}
```

En caso de probar con curl:

```ssh
curl --location 'http://localhost:8080/api/users' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Lolo",
    "email": "lolo@crema.com",
    "password": "Univer$itar10",
    "phones": [
        {
            "number": "987987987",
            "citycode": "11",
            "countrycode": "51"
        }
    ]
}'
```

Ejecutamos la petición y nos debe mostrar la respuesta del servicio, como el siguiente ejemplo:

```json
{
  "id": "c0f11a50-c711-4677-84c1-53a6f06fbc0a",
  "name": "Lolo",
  "email": "lolo@crema.com",
  "password": "Univer$itar10",
  "phoneList": [
    {
      "number": "987987987",
      "cityCode": "11",
      "countryCode": "51"
    }
  ],
  "modified": "2024-12-15T14:12:54.644+00:00",
  "created": "2024-12-15T14:12:54.644+00:00",
  "isactive": true
}
```

## Pruebas Unitarias

### Paso 1: Ejecutar los test unitarios

Con Maven:

```sh
mvn test
```