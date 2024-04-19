FROM eclipse-temurin:17-jdk
LABEL authors="Tribushko Danil"
WORKDIR /app
COPY build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]