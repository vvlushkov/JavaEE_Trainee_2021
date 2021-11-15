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
FROM tomcat:9-jdk11
EXPOSE 8080
RUN rm -fr /usr/local/tomcat/webapps/ROOT
COPY --from=build ./target/*.war /usr/local/tomcat/webapps/ROOT.war
CMD ["catalina.sh", "run"]