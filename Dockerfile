FROM openjdk-11-jre

WORKDIR /usr/app

COPY build/libs/api-1.0.jar .

EXPOSE ${APP_PORT}

CMD["java", "-jar", "api-1.0.jar"]