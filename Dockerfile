FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the source code to the working directory
COPY src .

# Run Maven clean install to build the JAR
RUN mvn clean install

# Set the entrypoint to run the built JAR
ENTRYPOINT ["java", "-jar", "target/*.jar"]
