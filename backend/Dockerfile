# Etapa de construcción
FROM maven:3.9.5-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

# Etapa final
FROM eclipse-temurin:21-jdk
WORKDIR /opt
COPY --from=builder /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
