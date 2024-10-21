# Use an official Maven image to build the app
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the source code
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests

# Use a minimal Java runtime for the final image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/car-rental-backend-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
