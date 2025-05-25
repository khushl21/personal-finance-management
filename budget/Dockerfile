FROM openjdk:23-jdk-slim

WORKDIR /app

COPY target/budget-0.0.1-SNAPSHOT.jar /app/budget.jar

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "/app/budget.jar"]