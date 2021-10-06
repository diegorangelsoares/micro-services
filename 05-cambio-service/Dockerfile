FROM adoptopenjdk/openjdk11:slim
MAINTAINER Diego Rangel <diegorangeljpa@gmail.com>
ARG JAR_FILE=05-cambio-service/target/*.jar
COPY ${JAR_FILE} app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.agd=file:/dev/./urandom","-jar","/app.jar"]