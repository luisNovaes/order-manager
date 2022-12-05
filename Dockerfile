FROM openjdk:17-jdk-alpine
EXPOSE 8080
ADD /target/order-manager.jar order-manager.jar
ENTRYPOINT ["java","-jar","order-manager.jar"]
