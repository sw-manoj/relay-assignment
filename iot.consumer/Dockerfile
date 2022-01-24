FROM openjdk:11
ADD target/iot-consumer.jar iot-consumer.jar
EXPOSE 7000
ENTRYPOINT ["java", "-jar", "iot-consumer.jar"]


#
# Build stage
#
# FROM maven:3.6.0-jdk-11-slim AS build
# COPY src /home/app/src
# COPY pom.xml /home/app
# RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
# FROM openjdk:11-jre-slim
# COPY --from=build /home/app/target/iot-consumer.jar /usr/local/lib/iot-consumer.jar
# EXPOSE 7000
# ENTRYPOINT ["java","-jar","/usr/local/lib/iot-consumer.jar"]