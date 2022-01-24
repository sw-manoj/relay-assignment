FROM openjdk:11
ADD target/sensor-api.jar sensor-api.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "sensor-api.jar"]