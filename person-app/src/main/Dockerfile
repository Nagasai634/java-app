FROM openjdk:11-jre-slim

WORKDIR /app

# Copy the entire project
COPY . .

# Install Maven
RUN apt-get update && apt-get install -y maven

# Build the application
RUN mvn clean package -DskipTests

# Expose port 8080
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "/app/target/person-app-1.0.0.jar"]
