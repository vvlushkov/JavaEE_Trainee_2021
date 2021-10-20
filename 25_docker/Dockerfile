#
# BUILD STAGE
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src ./src
COPY pom.xml .
RUN mvn -f pom.xml clean package

#
# PACKAGE STAGE
#
FROM openjdk:11-jre-slim
COPY --from=build ./target/DockerApp.jar /usr/local/lib/DockerApp.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/DockerApp.jar"]