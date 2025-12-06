# Açougue Pro 🥩

Sistema ERP + PDV para gestão moderna de açougues.

## Tecnologias
- Java 17+
- Spring Boot 3.x
- Maven multi-módulos
- PostgreSQL (produção) + H2 (dev/test)
- JavaFX 21 + SQLite (PDV offline-first)
- JWT Security + RBAC
- JUnit 5 + Testcontainers
- Docker Compose

## Estrutura
- `acougue-core` → entidades e regras de negócio
- `acougue-persistence` → repositórios JPA + Flyway
- `acougue-api` → REST controllers + segurança
- `acougue-infra` → adapters (ESC/POS, ACBr mock)
- `acougue-pdv` → PDV JavaFX offline
- `acougue-docs` → documentação
- `docker` → ambiente real

## Como rodar
```bash
mvn clean install
java -jar acougue-api/target/*.jar
java -jar acougue-pdv/target/*.jar
