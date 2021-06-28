FROM openjdk:11
ADD target/weather-provider.jar weather-provider.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "weather-provider.jar"]
