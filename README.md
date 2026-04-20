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

| Name[groupId/artifactId]                                | Version   |
| ------------------------------------------------------- | --------- |
| **org.springframework.boot/sring-boot-starter-parent**  | `4.0.5`   |
| org.springframework.boot/spring-boot-starter-data-jpa   | `3.3.5`   |
| org.springframework.boot/spring-boot-starter-security   | `3.4.5`   |
| org.springframework.boot/spring-boot-starter-validation | `3.5.7`   |
| org.springframework.boot/spring-boot-starter-webmvc     | `4.0.0`   |
| org.springframework.boot/spring-boot-starter-websocket  | `3.4.5`   |
| org.springframework.boot/spring-boot-devtools           | `3.3.5`   |
| org.postgresql/postgresql                               | `42.7.10` |
| org.projectlombok/lombok                                | `1.18.30` |
| org.junit.jupiter/junit-jupiter-api                     | `5.10.2`  |
| org.springframework.boot/spring-boot-test               | `3.3.4`   |
| io.jsonwebtoken/jjwt-api                                | `0.13.0`  |
| io.jsonwebtoken/jjwt-impl                               | `0.13.0`  |
| io.jsonwebtoken/jjwt-jackson                            | `0.13.0`  |

**_Java_** version `21 LTS`

## Frontend

The frontend is created using Vite + React + TypeScript. The creation script:

```sh
npm create vite frontend -- --template react-ts
cd frontend
npm install axios react-router-dom sockjs-client @stomp/stompjs tailwindcss @tailwindcss/vite date-fns react-toastify
npm install --save-dev @types/sockjs-client
```

### Dependency List

| Name                 | Version  |
| -------------------- | -------- |
| @stomp/stompjs       | `7.3.0`  |
| @tailwindcss/vite    | `4.2.2`  |
| @types/sockjs-client | `1.5.4`  |
| axios                | `1.15.0` |
| date-fns             | `4.1.0`  |
| react                | `19.2.0` |
| react-dom            | `19.2.0` |
| react-icons          | `5.6.0`  |
| react-router-dom     | `7.14.1` |
| react-toastify       | `11.0.5` |
| sockjs-client        | `1.6.1`  |
| tailwindcss          | `4.2.2`  |

**_NPM_** version `11.12.1`
