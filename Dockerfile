FROM openjdk:11-jdk

WORKDIR /usr/app

COPY build/libs/api-1.0-all.jar .

EXPOSE ${APP_PORT}

ENTRYPOINT java -jar api-1.0-all.jar