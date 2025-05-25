FROM openjdk:23-jdk-slim

WORKDIR /app

COPY target/apigateway-0.0.1-SNAPSHOT.jar /app/apigateway.jar

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "/app/apigateway.jar"]