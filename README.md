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

 DESIGN CONSIDERATIONS
=======================

The application was modeled as a service facade exposed to clients that allows them to organize races, assign horses to a race, run a race and display results during and after the race. The service accepts commands, commands' attributes are not strongly typed as this service could be exported as REST (only using basic types).

The return type of initializeRace and updateRace of RaceService is boolean, although in a real scenario we might use a response object with the status of the execution of the command, command id, and reason of failure if any.

Spring @Service, @Repository annotations were not used. To simulate the default behaviour of spring beans I've implemented the Repository and Service as Singletons. In Spring environment, we would have used DI.
