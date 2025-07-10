#!/bin/bash
# .docker/stop.sh

# Navegar para o diretório raiz do projeto
cd "$(dirname "$0")/.."

echo "🛑 Parando email-service..."

# Parar via Docker Compose se estiver rodando
if docker-compose -f .docker/docker-compose.yml ps | grep -q email-service; then
    echo "📦 Parando via Docker Compose (desenvolvimento)..."
    docker-compose -f .docker/docker-compose.yml down
fi

if docker-compose -f .docker/docker-compose.prod.yml ps | grep -q email-service; then
    echo "�� Parando via Docker Compose (produção)..."
    docker-compose -f .docker/docker-compose.prod.yml down
fi

# Parar container individual se estiver rodando
if docker ps | grep -q email-service; then
    echo "🐳 Parando container individual..."
    docker stop email-service
    docker rm email-service
fi

echo "✅ Email-service parado com sucesso!"