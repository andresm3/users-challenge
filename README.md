# users-challenge

## Descripción

API Backend creacion de usuarios.

## Requisitos Previos

> [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

> [Maven 3.9.4](https://archive.apache.org/dist/maven/maven-3/3.9.4/binaries/)

> [Postman](https://www.postman.com/downloads/)



## Ejecutar en Desarrollo

### Paso 1: Clonar el repositorio

```sh
git clone -b develop git@bitbucket.org:lp-devsecops/biz-commercial-calculate-quote-vida-lpv-v1.git
```

### Paso 2: Crear nuestra rama feature

A partir de la rama develop, creamos nuestra rama feature. El número del RQ dependerá del requerimiento.

```sh
git checkout -b feature/RQ2023-001
```

### Paso 3: Descargar las dependencias con maven
Con Maven:

```sh
mvn clean install
```
Con Maven Wrapper:

```sh
.\mvnw.cmd clean install
```

#### Paso 4: Configurar los properties

Nos vamos al path `src/main/resources` y nos aseguramos de que el archivo `application.properties` tenga la siguiente configuración:

```properties
spring.profiles.active=local
```

Esto cargará el perfil `application-local.properties` que cuenta con las variables de entorno necesarias para ejecutar la aplicación en local.
En caso que requiera ejecutar con recursos de AWS como son Parameters Storage, SQS, Secret Manager, se debe configurar las [credenciales](https://docs.aws.amazon.com/cli/latest/userguide/cli-configure-files.html?icmpid=docs_sso_user_portal)  en la PC del desarrollador y configurar el archivo `application.properties` con la siguiente configuración:

```properties
spring.profiles.active=prod
```


### Paso 5: Ejecutar la aplicación

En nuestro IDE favorito (IntelliJ IDEA, Eclipse, VSCode) ejecutamos la clase `ExampleApplication.java`, que se encuentra en el path `src/main/java` y dentro del package `pe.com.lapositiva.tipo.procesonegocio.operacion.recurso.ramo.empresa`

En caso que se requiera ejecutar la aplicación por consola con Maven:

```ssh
mvn spring-boot:run
```



### Paso 4: Documentación Swagger

Abrimos en el navegador la url http://localhost:8080/swagger-u y nos debe mostrar la documentación swagger de la aplicación.

Ahí encontraremos la url de petición del servicio: `http://localhost`, los paths de las peticiones, los métodos, el esquema de entrada petición con un ejemplo, el modelo de respuesta con un ejemplo.

### Paso 5: Probar el servicio con Postman

Abrimos Postman y configuramos lo siguiente:

Url: `http://localhost:8080/users/create`

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
curl --location 'http://localhost:8080/users/create' \
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

## Testear la Aplicación

### Paso 1: Ejecutar los test unitarios

Con Maven:

```sh
mvn test
```