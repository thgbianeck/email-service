#!/bin/bash
# .docker/deploy.sh

# Navegar para o diretório raiz do projeto
cd "$(dirname "$0")/.."

echo "🚀 Iniciando deploy em produção..."

# Verificar se está no branch correto
BRANCH=$(git branch --show-current)
if [ "$BRANCH" != "main" ] && [ "$BRANCH" != "master" ]; then
    echo "⚠️  Você não está no branch principal. Continuar? (y/n)"
    read -r response
    if [ "$response" != "y" ]; then
        echo "❌ Deploy cancelado."
        exit 1
    fi
fi

# Executar testes
echo "🧪 Executando testes..."
mvn clean test

if [ $? -ne 0 ]; then
    echo "❌ Testes falharam! Deploy cancelado."
    exit 1
fi

# Build da aplicação
echo "📦 Construindo aplicação..."
mvn clean package -DskipTests

# Deploy com Docker Compose
echo "🐳 Fazendo deploy com Docker..."
docker-compose -f .docker/docker-compose.prod.yml up -d --build

echo "✅ Deploy concluído!"
echo "🌐 Aplicação disponível em: http://localhost"