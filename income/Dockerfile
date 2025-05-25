FROM openjdk:23-jdk-slim

WORKDIR /app

COPY target/income-0.0.1-SNAPSHOT.jar /app/income.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "/app/income.jar"]