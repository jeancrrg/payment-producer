![Logo Kafka](../payment-producer/src/main/resources/assets/logo-kafka.png)

# 💳 Payment Producer - Microserviço de Produção de Pagamentos com Kafka

## 📌 Sobre o microserviço
Este microserviço tem como objetivo **simular uma API de pagamento de clientes**.  
Quando um pagamento é recebido via API REST, ele é **publicado em um tópico Kafka (`payment-topic`)**, permitindo que **diversos consumidores** recebam e processem este pagamento de forma assíncrona.

Este projeto foi desenvolvido para estudo e prática de **integração de microsserviços utilizando Apache Kafka**.

---

## 💻 Ambiente de desenvolvimento
- **Java 21**
- **Spring Boot 3.x**
- **Apache Kafka**
- **Docker & Docker Compose** (para subir o ambiente Kafka local)
- **Maven** (gerenciamento de dependências)

---

## ⚙️ Tecnologias utilizadas
- **Spring Boot Web** → Exposição de endpoints REST.
- **Spring for Apache Kafka** → Integração com o Kafka.
- **Kafka** → Mensageria distribuída para troca de mensagens entre microserviços.
- **Lombok** → Redução de código boilerplate.
- **Docker** → Subida rápida do ambiente de mensageria.

---

## 📋 Requisitos
Antes de rodar o projeto, certifique-se de ter instalado:
- [Java 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
- [Maven](https://maven.apache.org/)
- [Docker](https://docs.docker.com/get-docker/) e [Docker Compose](https://docs.docker.com/compose/)

---

## ▶️ Como rodar o projeto

### 1. Subir o ambiente do Kafka via Docker Compose
Na raiz do projeto (onde está o `docker-compose.yml`) deve executar o seguinte comando no terminal:
```bash
docker-compose up
```
Este comando irá subir as aplicações do kafka em um container do docker, sendo elas:
- Kafka (Mensageria)
- Kafdrop (Interface gráfica do kafka)
- Zookeeper (Gerenciador do cluster do kafka)

**OBS:** Para acessar a interface do Kafka e acompanhar os tópicos, basta acessar http://localhost:19000 após subir o container do docker.
