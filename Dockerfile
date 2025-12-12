# =========================
# Etapa de build
# =========================
FROM maven:3.9.4-eclipse-temurin-21 AS build

# Diretório de trabalho
WORKDIR /app

# Copia todos os arquivos do projeto
COPY . .

# Build do projeto (skip tests para agilizar)
RUN mvn clean package -DskipTests

# =========================
# Etapa final: imagem leve com Java 21
# =========================
FROM eclipse-temurin:21-jdk

# Diretório da aplicação
WORKDIR /app

# Copia o jar gerado na etapa de build
COPY --from=build /app/target/moringa.jar app.jar

# Porta padrão do Spring Boot
EXPOSE 8080

# Comando de execução
ENTRYPOINT ["java", "-jar", "app.jar"]
