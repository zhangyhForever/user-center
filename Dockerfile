# syntax=docker/dockerfile:1
FROM openjdk:8u191-jdk-alpine
WORKDIR /app
COPY *.jar /user-center.jar
CMD ["--server.port=8080"]

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/user-center.jar"]