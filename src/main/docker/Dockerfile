FROM openjdk:11-jre-slim-sid

LABEL maintainer="Prova"
LABEL version="1.0"

ENV DB_NAME="postgres"
ENV DB_HOSTNAME="172.16.238.12"
ENV DB_PORT="5432"
ENV DB_USERNAME="postgres"
ENV DB_PWD="builder"

EXPOSE 8080

COPY maven/*.jar prova.jar

ENTRYPOINT [ "java" , "-Xms128m",  "-Xmx400m", "-jar", "-DLog4jContextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector", "prova.jar", "--jdbc.url=jdbc:postgresql://${DB_HOSTNAME}:${DB_PORT}/${DB_NAME}", "--jdbc.password=${DB_PWD}", "--jdbc.username=${DB_USERNAME}"]