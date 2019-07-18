FROM java:8-jdk-alpine

FROM openjdk:8-jdk-alpine

VOLUME /tmp

ARG JAR_FILE

ARG PROPERTIES_FILE

COPY build/libs/*.jar app.jar

COPY ${PROPERTIES_FILE} application.properties

ENTRYPOINT [ "java", "-jar" "/app.jar", "-Dspring.config.location=application.properties"]