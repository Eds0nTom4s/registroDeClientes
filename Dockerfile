# Estágio de construção
FROM ubuntu:latest AS build

# Atualizar e instalar OpenJDK e Maven
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven

# Configurar diretório de trabalho
WORKDIR /app

# Copiar arquivos do projeto para o diretório de trabalho
COPY . .

# Construir o projeto
RUN mvn clean install

# Estágio de execução
FROM openjdk:17-jdk-slim

# Expor a porta usada pela aplicação
EXPOSE 8080

# Copiar o JAR construído para o contêiner
COPY --from=build /app/target/your-app-1.0.0.jar /app/app.jar

# Definir o ponto de entrada
ENTRYPOINT ["java", "-jar", "/app/app.jar"]