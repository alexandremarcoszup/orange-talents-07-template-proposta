FROM openjdk:11
EXPOSE 8080
ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Xmx512m", "-jar", "/app.jar"]