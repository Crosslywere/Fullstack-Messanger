# Messanger

This is a project that I am creating to show my knowledge of Java(Spring Boot) in combination with React(Typescript) for a fullstack portfolio.

## What The Application Does/Should Do

This is meant to be a simple messaging application. It does not need to capture any data other than a username, password and messages sent.

The application should behave similarly to the WhatsApp messaging structure. Meaning the application should;

1. Have a way for users to subscribe to one another.
2. Have a way for users to unsubscribe from one another.
3. Group messages from the same sender.
4. Allow people to send, recieve and reply to messages.
5. Allow messages to be updated and deleted by the sender.

These requirements may change in the future as I work on the project.

## Backend

The backend is written in Java using the Spring Boot framework.
The configuration can be found on the [start.spring.io](https://start.spring.io/#!type=maven-project&language=java&platformVersion=4.0.5&packaging=jar&configurationFileFormat=properties&jvmVersion=21&groupId=com.crossly&artifactId=backend&packageName=com.crossly.backend&dependencies=devtools,lombok,web,security,data-jpa,postgresql,websocket,validation) site.
It also uses the JWT dependency version `0.13.0`.

### Dependency List

| Name                 | Version  |
| -------------------- | -------- |
| Spring Boot DevTools | `4.0.5`  |
| Lombok               | `4.0.5`  |
| Spring Web           | `4.0.5`  |
| Spring Security      | `4.0.5`  |
| Spring Data JPA      | `4.0.5`  |
| PostgreSQL Driver    | `4.0.5`  |
| WebSocket            | `4.0.5`  |
| Validation           | `4.0.5`  |
| jjwt-api             | `0.13.0` |
| jjwt-impl            | `0.13.0` |
| jjwt-jackson         | `0.13.0` |

**_Java_** version `21 LTS`
