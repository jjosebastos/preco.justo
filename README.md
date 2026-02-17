
# 🦆 Desafio Back End Java – Preço Justo

API REST desenvolvida em **Java com Spring Boot** para gerenciamento de um sistema de venda de patos.
O sistema permite cadastro de patos, clientes e vendedores, além do registro e controle de vendas.

> ⚠️ Observação: A geração de relatórios em Excel e os relatorios de vendas/vendedores foram previstas na arquitetura, porém não foi implementada nesta versão.

---

# 📌 Objetivo

Desenvolver uma API REST para:

- Cadastro individual de patos (com rastreabilidade da mãe)
- Cadastro de clientes (com/sem desconto)
- Cadastro de vendedores (com validações de unicidade)
- Registro de vendas com aplicação automática de regras de negócio
- Listagem de patos vendidos
- Ranking de vendedores por volume e valor vendido

---

# 🏗️ Arquitetura do Projeto

Estrutura em camadas seguindo boas práticas:

```
controller
service
repository
entity
dto
exception
config
```

- **Controller** → Endpoints REST
- **Service** → Regras de negócio
- **Repository** → Persistência com Spring Data JPA
- **Entity** → Mapeamento ORM
- **DTO** → Transporte de dados

---

# 🗄️ Modelagem do Banco (DER)

Tabelas principais:

- T_PJ_PATO
- T_PJ_CLIENTE
- T_PJ_VENDEDOR
- T_PJ_VENDAS
- T_PJ_ITEM_VENDA

### Regras modeladas:

✔ Auto relacionamento de Pato (mãe)  
✔ Venda com múltiplos patos  
✔ Impedimento de venda duplicada do mesmo pato  
✔ Restrição para impedir exclusão de vendedor com vendas  
✔ Aplicação automática de desconto  

---

# 🦆 Regras de Negócio

## 💰 Preço dos Patos

| Situação | Valor |
|----------|-------|
| Pato sem filhos | R$ 70,00 |
| Pato com 1 filho | R$ 50,00 |
| Pato com 2 filhos | R$ 25,00 |

## 🧾 Venda

- Cliente com desconto → 20% aplicado automaticamente
- Data da venda registrada automaticamente
- Não é permitido vender o mesmo pato mais de uma vez
- Venda exige cliente e vendedor válidos

---

# 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA (Hibernate)
- Bean Validation
- PostgreSQL
- Flyway (versionamento de banco)
- Swagger / OpenAPI
- Docker & Docker Compose
- Gradle

---

# 📦 Dependências Principais

- spring-boot-starter-web
- spring-boot-starter-data-jpa
- spring-boot-starter-validation
- postgresql
- flyway-core
- springdoc-openapi-starter-webmvc-ui
- lombok

---

# 🐳 Execução com Docker (Recomendado)

## 1️⃣ Subir aplicação + banco

```bash
docker-compose up --build
```

A aplicação ficará disponível em:

```
http://localhost:8080
```

---

# ▶️ Execução Local (Sem Docker)

## Pré-requisitos

- Java 17
- PostgreSQL rodando
- Gradle
- Docker Desktop

## 1️⃣ Criar banco



## 2️⃣ Executar aplicação

```bash
gradle clean
gradle bootRun
```

---

# 🧬 Flyway

As migrations estão em:

```
src/main/resources/db/migration
```

O Flyway executa automaticamente ao iniciar a aplicação.

---

# 📘 Documentação da API (Swagger)

Disponível em:

```
http://localhost:8080/swagger-ui/index.html
```

Através do Swagger é possível:

- Testar todos os endpoints
- Visualizar contratos de requisição e resposta
- Validar DTOs
- Simular registros de venda

---

# 🔗 Endpoints Implementados

## 🦆 Patos

- POST /patos
- PUT /pato/{id}
- GET /pato/{id}

## 👤 Clientes

- POST /cliente
- PUT /cliente/{id}
- GET /cliente/{id}

## 🧑‍💼 Vendedores

- POST /vendedor
- GET /vendedor/{id}
- DELETE /vendedores/{id}

## 💰 Vendas

- POST /venda


---

# 🔒 Validações Implementadas

- CPF válido para vendedor
- Unicidade de CPF e matrícula
- Bloqueio de venda duplicada do mesmo pato
- Validações com Bean Validation

---

# 🧪 Testes

Estrutura preparada para testes unitários (JUnit + Mockito).

---

# 📅 Cronograma Estimado

| Atividade | Horas |
|------------|--------|
| Modelagem DER | 3h |
| Configuração Projeto | 2h |
| Cadastro de Entidades | 4h |
| Regras de Venda | 5h |
| Ranking | 3h |
| Docker + Flyway | 2h |

---

# 📈 Melhorias Futuras

- Implementação de geração de relatórios em Excel
- Paginação e filtros avançados
- Autenticação JWT
- Testes unitários completos
- Deploy em ambiente cloud

---

# 👨‍💻 Autor

José Bezerra  
Desenvolvedor Back-end Java
