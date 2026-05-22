#!/bin/bash

echo "Limpando Docker Compose..."
docker-compose down --rmi local -v

echo "Iniciando Docker Compose..."
docker-compose up --build

