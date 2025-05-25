FROM openjdk:23-jdk-slim

WORKDIR /app

COPY target/users-0.0.1-SNAPSHOT.jar /app/users.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app/users.jar"]