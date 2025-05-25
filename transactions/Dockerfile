FROM openjdk:23-jdk-slim

WORKDIR /app

COPY target/transactions-0.0.1-SNAPSHOT.jar /app/transactions.jar

EXPOSE 8084

ENTRYPOINT ["java", "-jar", "/app/transactions.jar"]