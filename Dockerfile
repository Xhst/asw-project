FROM eclipse-temurin:17-jdk

ARG SERVICE_NAME
ARG JAR_FILE=/build/libs/$SERVICE_NAME.jar

RUN mkdir /opt/app

COPY ${JAR_FILE} /opt/app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Xmx128m", "-Xms128m", "-jar", "/opt/app/app.jar"]

HEALTHCHECK --start-period=30s --interval=10s CMD curl -f http://localhost:8080/actuator/health || exit 1