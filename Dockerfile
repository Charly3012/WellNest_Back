FROM openjdk:21-jdk-slim
ARG JAR_FILE=target/wellnest-0.0.1.jar
COPY ${JAR_FILE} app_wellnest.jar
EXPOSE 8001
ENTRYPOINT ["java", "-jar", "app_wellnest.jar"]