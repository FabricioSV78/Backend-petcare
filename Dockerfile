FROM openjdk:17
EXPOSE 8080
COPY build/libs/web-0.0.1-SNAPSHOT.jar java-app.jar
ENTRYPOINT ["java", "-jar", "/java-app.jar"]