FROM eclipse-temurin:17

WORKDIR /app

COPY target/rimma-0.0.1.jar /app/rimma-0.0.1.jar

EXPOSE 9000

CMD ["java", "-jar", "/app/rimma-0.0.1.jar"]