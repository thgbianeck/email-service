# .docker/Makefile

# Definir diretório raiz do projeto
PROJECT_ROOT = ..

.PHONY: build run stop test clean logs restart prod help

help:
    @echo "📋 Comandos disponíveis:"
    @echo "  build     - Construir imagem Docker"
    @echo "  run       - Executar aplicação em desenvolvimento"
    @echo "  stop      - Parar aplicação"
    @echo "  test      - Executar testes"
    @echo "  clean     - Limpar recursos Docker"
    @echo "  logs      - Mostrar logs da aplicação"
    @echo "  restart   - Reiniciar aplicação"
    @echo "  prod      - Deploy em produção"

build:
    @echo "🏗️ Construindo imagem Docker..."
    cd $(PROJECT_ROOT) && docker build -f .docker/Dockerfile -t viasoft/email-service:latest .

run:
    @echo "🚀 Iniciando aplicação..."
    cd $(PROJECT_ROOT) && docker-compose -f .docker/docker-compose.yml up -d

stop:
    @echo "🛑 Parando aplicação..."
    cd $(PROJECT_ROOT) && docker-compose -f .docker/docker-compose.yml down

test:
    @echo "🧪 Executando testes..."
    cd $(PROJECT_ROOT) && mvn clean test

clean:
    @echo "🧹 Limpando recursos Docker..."
    docker system prune -f

logs:
    @echo "📋 Mostrando logs..."
    cd $(PROJECT_ROOT) && docker-compose -f .docker/docker-compose.yml logs -f email-service

restart: stop run

prod:
    @echo "🚀 Deploy em produção..."
    cd $(PROJECT_ROOT) && docker-compose -f .docker/docker-compose.prod.yml up -d --build