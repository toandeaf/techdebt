FROM openjdk:11

COPY target/techdebt-0.0.1-SNAPSHOT.jar /app/app.jar
COPY target/classes/application.properties /app/application.properties
COPY target/classes/Engineers.csv /app/Engineers.csv
COPY target/classes/static /app/static

ENTRYPOINT ["java", "-jar", "/app/app.jar","--spring.config.location=/app/application.properties"]