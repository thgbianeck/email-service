#!/bin/bash
# .docker/run.sh

# Navegar para o diretório raiz do projeto
cd "$(dirname "$0")/.."

echo "🚀 Iniciando email-service..."

# Verificar se a imagem existe
if ! docker images | grep -q "viasoft/email-service"; then
    echo "📦 Imagem não encontrada. Construindo..."
    .docker/build.sh
fi

# Parar container existente se estiver rodando
echo "🛑 Parando container existente..."
docker stop email-service 2>/dev/null || true
docker rm email-service 2>/dev/null || true

# Criar diretório de logs se não existir
mkdir -p logs

# Executar novo container
echo "🎯 Iniciando novo container..."
docker run -d \
  --name email-service \
  -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=docker \
  -e JAVA_OPTS="-Xmx512m -Xms256m" \
  -e MAIL_INTEGRACAO=AWS \
  -v $(pwd)/logs:/app/logs \
  --restart unless-stopped \
  viasoft/email-service:latest

if [ $? -eq 0 ]; then
    echo "✅ Container iniciado com sucesso!"
    echo "🌐 Aplicação disponível em: http://localhost:8080"
    echo "🏥 Health check: http://localhost:8080/actuator/health"
    echo "📚 Swagger UI: http://localhost:8080/swagger-ui.html"
    echo ""
    echo "📊 Status do container:"
    docker ps | grep email-service
    echo ""
    echo "📋 Para ver os logs em tempo real, execute:"
    echo "   docker logs -f email-service"
else
    echo "❌ Erro ao iniciar o container!"
    exit 1
fi