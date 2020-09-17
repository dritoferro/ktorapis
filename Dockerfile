FROM openjdk:11-jdk

WORKDIR /usr/app

COPY build/libs/api-1.0-all.jar .

EXPOSE ${APP_PORT}

ENTRYPOINT java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005 -jar api-1.0-all.jar