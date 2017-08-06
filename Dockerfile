# Use an official Python runtime as a parent image
FROM openjdk:8

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
ADD ./target/event-management.jar /app

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run app.py when the container launches
CMD ["java", "-jar", "/app/event-management.jar"]