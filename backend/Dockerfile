# Build the application locally and copy the JAR
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy the built JAR from local build
COPY target/*.jar app.jar

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
