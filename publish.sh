#!/bin/bash
# Script para publicar la aplicación en Docker Hub

# Variables (modifica según tu caso)
USERNAME="c0rvvz"  
APP_NAME="cda-api"
VERSION="latest"

# Iniciar sesión en Docker Hub
echo "Iniciando sesión en Docker Hub..."
docker login

# Construir la imagen
echo "Construyendo la imagen de la aplicación..."
docker-compose build

# Etiquetar la imagen
echo "Etiquetando la imagen como $USERNAME/$APP_NAME:$VERSION..."
docker tag cda-api_service:latest $USERNAME/$APP_NAME:$VERSION

# Subir la imagen a Docker Hub
echo "Subiendo la imagen a Docker Hub..."
docker push $USERNAME/$APP_NAME:$VERSION

echo "¡Publicación completada con éxito!"
