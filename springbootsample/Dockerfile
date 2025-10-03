# Use an official OpenJDK 17 runtime as a parent image
FROM openjdk:17-jdk-slim

# Set a label to identify the maintainer
LABEL maintainer="user@example.com"

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled JAR file from the target directory to the container
# The JAR_FILE build argument is passed from the Jenkinsfile
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the JAR file 
ENTRYPOINT ["java","-jar","/app/application.jar"]