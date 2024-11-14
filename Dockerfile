# Utilizando uma imagem base leve para Java
FROM openjdk:21-jdk-slim

# Definindo o diretório de trabalho
WORKDIR /app

# Copiando o arquivo JAR para o contêiner
COPY target/Ac2-project-0.0.1-SNAPSHOT.jar /app/Ac2-project.jar

# Definindo a porta que o contêiner irá expor
EXPOSE 8080

# Definindo uma variável de ambiente para o perfil Spring
ENV SPRING_PROFILES_ACTIVE=default

# Definindo o ponto de entrada para iniciar o aplicativo
ENTRYPOINT ["java", "-jar", "/app/Ac2-project.jar", "--spring.profiles.active=${SPRING_PROFILES_ACTIVE}"]
