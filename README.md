# CDA - Control de Acceso y Asistencia

Desarrollada con Spring Boot y desplegada con Docker y Kubernetes. El proyecto también se integra con el stack ELK (
Elasticsearch, Logstash y Kibana) para la recolección y visualización de logs.

## Tecnologías

- Java 21
- Spring Boot 3.x
- MySQL 8.0
- Docker 24+
- Docker Compose 1.29+
- Kubernetes v1.27+
- Minikube 1.35+
- Elasticsearch 8.12.0
- Logstash 8.12.0
- Kibana 8.12.0

---

## Cómo clonar el repositorio

git clone https://github.com/yeisont08/cda.git
cd cda

---

## Ejecución con Docker Compose

### 1. Construir el JAR

./mvnw clean package -DskipTests

### 2. Construir la imagen Docker

docker build -t cda-api:latest .

### 3. Levantar todos los servicios

docker-compose up -d

Servicios disponibles:

- Backend: http://localhost:8080
- MySQL: puerto `3307`
- Kibana: http://localhost:5601
- Elasticsearch: http://localhost:9200

---

## Ejecución en Kubernetes (Minikube)

### 1. Iniciar Minikube

minikube start --driver=docker --kubernetes-version=v1.27.3
eval $(minikube docker-env)

### 2. Construir la imagen dentro de Minikube

./mvnw clean package -DskipTests
docker build -t api-service:latest .

### 3. Aplicar manifiestos Kubernetes

kubectl apply -f kube/

### 4. Acceder a los servicios

minikube service api-service
minikube service kibana

---

## Estructura del repositorio

cda/
├── src/ # Código fuente Java
├── Dockerfile
├── docker-compose.yml
├── kube/ # Archivos YAML para Kubernetes
├── logstash.conf
├── logstash.yml
├── README.md
└── pom.xml

---

## Requisitos

- Java 21 (o usar `openjdk:21` vía Docker)
- Maven 3.8+
- Docker instalado
- Minikube para entornos K8s
