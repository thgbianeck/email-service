#!/bin/bash
# .docker/build.sh

# Navegar para o diretório raiz do projeto
cd "$(dirname "$0")/.."

echo "🏗️  Construindo imagem Docker..."

# Verificar se o Dockerfile existe
if [ ! -f ".docker/Dockerfile" ]; then
    echo "❌ Dockerfile não encontrado em .docker/!"
    exit 1
fi

# Construir a imagem usando o Dockerfile na pasta .docker
echo "📦 Construindo imagem viasoft/email-service:latest..."
docker build -f .docker/Dockerfile -t viasoft/email-service:latest .

if [ $? -eq 0 ]; then
    echo "✅ Imagem construída com sucesso!"

    # Listar imagens
    echo "�� Imagens disponíveis:"
    docker images | grep email-service

    # Mostrar informações da imagem
    echo "📊 Tamanho da imagem:"
    docker inspect viasoft/email-service:latest --format='{{.Size}}' | numfmt --to=iec-i --suffix=B
else
    echo "❌ Erro ao construir a imagem!"
    exit 1
fi