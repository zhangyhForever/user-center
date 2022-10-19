# syntax=docker/dockerfile:1
FROM openjdk:8u191-jdk-alpine
WORKDIR /app
COPY ./user-web/target/*-exec.jar /user-center.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/user-center.jar"]