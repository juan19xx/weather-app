# Weather App

API REST desarrollada con **Quarkus** siguiendo los principios de **Arquitectura Hexagonal (Ports & Adapters)**.

La aplicación consume la API de **OpenWeather** para consultar información del clima y realizar búsquedas de ciudades.

## Tecnologías

- Java 21
- Quarkus 3
- Maven
- Mutiny (Programación Reactiva)
- MicroProfile REST Client
- Jackson
- JUnit 5
- Mockito
- Lombok
- MapStruct

## Arquitectura

El proyecto está organizado siguiendo Arquitectura Hexagonal.

```
src/main/java/com/weather
├── application
│   ├── ports
│   │   ├── in
│   │   └── out
│   └── service
├── domain
│   └── model
└── infrastructure
    ├── client
    ├── dto
    ├── mapper
    ├── resource
    └── rest
```

### Capas

**Domain**

Contiene el modelo de negocio y no depende de ninguna tecnología.

**Application**

Contiene los casos de uso y los puertos de entrada y salida.

**Infrastructure**

Implementa los adaptadores necesarios para exponer la API REST y consumir OpenWeather mediante MicroProfile REST Client.

## Funcionalidades

Actualmente la aplicación permite:

- Consultar el clima actual por ciudad.
- Buscar ciudades.
- Buscar una ciudad filtrando por país.

## API

### Obtener clima

```
GET /weather?q=Monterrey
```

### Buscar ciudades

```
GET /weather/search?q=Monterrey
```

### Buscar ciudad por país

```
GET /weather/search?q=Monterrey&country=MX
```

## Configuración

Crear un archivo `.env` en la raíz del proyecto.

```
OPENWEATHER_API_KEY=TU_API_KEY
```

El `application.yaml` utiliza dicha variable:

```yaml
openweather:
  api:
    key: ${OPENWEATHER_API_KEY}
```

También es necesario configurar la URL base del cliente REST.

```yaml
quarkus:
  rest-client:
    openweather:
      url: https://api.openweathermap.org
```

## Ejecutar la aplicación

Modo desarrollo:

```bash
mvn quarkus:dev
```

La aplicación estará disponible en:

```
http://localhost:8080
```

## Ejecutar pruebas

Pruebas unitarias:

```bash
mvn test
```

Pruebas de integración:

```bash
mvn verify -DskipITs=false
```

## Compilar

```bash
mvn clean package
```

## Autor

Juan Coronado