# Animfeigner [![Java CI with Gradle](https://github.com/vikhani/Animfeigner/actions/workflows/gradle.yml/badge.svg?branch=master)](https://github.com/vikhani/Animfeigner/actions/workflows/gradle.yml)
Test assignment project that uses OpenFeign to act as a microservice communicating with the Animventory project.

## Run
`java -Dserver.port=[port to run on] -Danimventory.url=[url for running Animfeigner, ex. http://localhost:8085/] -Danimventory.username=[username] -Danimventory.password=[password] -jar path/to/Animfeigner.jar`

**Example:**

`java -Dserver.port=8083 -Danimventory.url=http://localhost:8085/ -Danimventory.username=user -Danimventory.password=pass -jar Animfeigner.jar`

## Build
`./gradlew bootJar`

## API
`GET /animventory/animals`

Gets result info about request for all animals for the user with the provided credentials, the animals are requested from [Animventory url]/animals via OpenFeign.

## Integration with Animventory
General outline is available in this [Postman Collection](https://www.getpostman.com/collections/b9c229bd07a0388b008b) (copy link and use Postman Import to import the collection). You'll need to change ports in `POST` requests to point to the Animventory port and to point to the Animfeigner port in the `GET` request.

First you need to register in Animventory and then use these credentials to access animals in Animventory from Animfeigner. Without this you won't be able to get any results other than 401 from Animfeigner (it'll still be written to db and log as a result).
*Request for Animventory url:*
`POST /registration`

*Bodyraw (json)*  
json  
{  
  "username": "...",  
  "password": "..."  
}

And then pass these credentials to Animfeigner like in the "Run" example (user can be logged out of Animventory).

## Logging
Log is generated in `./logs/animal_request.log`

## DB
While running DB is available on `/h2-console`

url: `jdbc:h2:mem:animfeigner`

Default credentials: admin/password


## Stack
- Java
- Spring Boot
- h2
- Hibernate
- OpenFeign
- slf4j + Logback
