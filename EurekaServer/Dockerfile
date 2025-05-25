FROM openjdk:23-jdk-slim

WORKDIR /app

COPY target/EurekaServer-0.0.1-SNAPSHOT.jar /app/eurekaserver.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "/app/eurekaserver.jar"]