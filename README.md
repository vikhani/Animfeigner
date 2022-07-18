# Animfeigner
Test assignment project that uses OpenFeign to act as a microservice communicating with the Animventory project.

## Run
`java -Dserver.port=[port to run on] -Danimfeigner.client.url=[url for running Animfeigner, ex. http://localhost:8085/] -Danimventory.username=[username] -Danimventory.password=[password] -jar path/to/Animfeigner.jar`

**Example:**

`java -Dserver.port=8083 -Danimfeigner.client.url=http://localhost:8085/ -Danimventory.username=user -Danimventory.password=pass -jar Animfeigner.jar`

## Build
`./gradlew bootJar`

## API
`GET /animventory/animals`

Gets result info about request for all animals for the user with the provided credentials.

## Logging
Log is generated in `./logs/animal_request.log`

## Stack
- Java
- Spring Boot
- h2
- Hibernate
- OpenFeign
- slf4j + Logback
