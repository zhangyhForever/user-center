# syntax=docker/dockerfile:1
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY *.jar ./user-center.jar

EXPOSE 8080
CMD ["java", "-jar", "/user-center.jar"]