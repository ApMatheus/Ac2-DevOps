FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/Ac2-project-0.0.1-SNAPSHOT.jar /app/Ac2-project.jar
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java -jar Ac2-project.jar --spring.profiles.active=${SPRING_PROFILES_ACTIVE:-default}"]