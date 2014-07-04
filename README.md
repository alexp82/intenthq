 README
========

## Requisites
- Java
- Gradle
- Preferred IDE/Text editor
- Internet connection

## Basic commands
- `$ ./gradlew test`: run tests
- `$ ./gradlew build`: builds the project
- `$ java -jar build/libs/horse-racing-0.1.0.jar`: runs the server

Point your browser to [http://localhost:8080/horse-racing](http://localhost:8080/horse-racing) to read the wording of the exercise.

## Design Considerations

The application was modeled with a layered architecture in mind. An application service (RaceService) was designed to allow clients to organize races, assign horses to a race, run a race and display results during and after the race. The service accepts commands, commands' attributes are not strongly typed as this service could be exported as REST (only using basic types).

### Domain Layer
- The Domain Layer contains the real business logic, but does not contain any infrastructure specific code. The infrastructure specific implementation is provided by the Infrastructure Layer. 

### Application Layer
- The Application Layer takes commands from the Client Layer and translates these commands to use case invocations on the domain layer. 
- The Application Layer also provides transaction control for business operations (not implemented here). 
- The Application layer is responsible to translate Aggregate data into the client specific presentation model 
- Commands' attributes are not strongly typed as this service will be consumed by Client Layer which could be implemented as a REST service
- The return type of initializeRace and updateRace of RaceService is boolean, although in a real scenario we might use a response object with the status of the execution of the command, command id, and reason of failure if any.
- Spring @Service, @Repository annotations were not used. To simulate the default behaviour of spring beans I've implemented the Repository and Service as Singletons. In Spring environment, we would have used DI.

### Infrastructure Layer (Repositories)
- The Infrastructure Layer provides the infrastructure dependent parts for all other layers, like a Hibernate or JPA backed implementation. Here we have an in memory repository for the race.

### Client Layer
- The Client Layer consumes Application Services and invokes business logic on these services (not implemented). Every invocation is a new transaction. The Client Layer can be a SOAP webservice endpoint or a RESTful web resource.