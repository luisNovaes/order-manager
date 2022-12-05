FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} order-manager.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/order-manager.jar"]