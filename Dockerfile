FROM adoptopenjdk/openjdk11:alpine-jre

WORKDIR /opt/app

ARG JAR_FILE=target/lastFmApiClient-0.0.1-SNAPSHOT.jar
ARG CONF_FILE=application.properties

COPY ${JAR_FILE} app.jar

COPY ${CONF_FILE} ${CONF_FILE}

ENTRYPOINT ["java","-jar","app.jar"]