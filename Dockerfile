FROM eclipse-temurin:21-jdk-alpine

COPY target/herbnet-*.jar /opt/app.jar
EXPOSE 8080

CMD ["java", "-jar", "/opt/app.jar"]
