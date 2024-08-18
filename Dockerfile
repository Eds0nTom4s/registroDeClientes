# Estágio de construção
FROM maven:3.8-openjdk-17 AS build

WORKDIR /app

# Copiar o código da aplicação
COPY . .

# Construir o projeto
RUN mvn clean install -DskipTests

# Estágio de execução
FROM openjdk:17-jdk-slim

# Expor a porta usada pela aplicação
EXPOSE 80

# Copiar o JAR construído para o contêiner
COPY --from=build /app/target/GestaoDeClienteApp-0.0.1-SNAPSHOT.jar /app/app.jar

# Definir o ponto de entrada
ENTRYPOINT ["java", "-jar", "/app/app.jar"]