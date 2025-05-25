FROM openjdk:23-jdk-slim

WORKDIR /app

COPY target/expenses-0.0.1-SNAPSHOT.jar /app/expenses.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "/app/expenses.jar"]