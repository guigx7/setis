# User Management API

API REST desenvolvida em **Java com Spring Boot** para gerenciamento de usuários, implementando operações completas de CRUD, validações de domínio, paginação e documentação automática.

---

## 📌 Funcionalidades

- Cadastro de usuários
- Consulta de usuário por ID
- Listagem de usuários
- Atualização de dados de usuário
- Remoção de usuários
- Paginação na listagem
- Validações de regras de negócio
- Documentação automática da API com Swagger

---

## 🧩 Regras de Negócio Implementadas

- O e-mail do usuário deve ser **único**
- Não é permitido cadastrar usuário com **data de nascimento no futuro**
- Campos de auditoria são gerenciados pela aplicação:
  - Data de criação
  - Data de edição
- Validação de parâmetros em todas as requisições

---

## 🔗 Endpoints Disponíveis

### Criar usuário
```
POST /users
```

### Buscar usuário por ID
```
GET /users/{id}
```

### Listar usuários (com paginação)
```
GET /users
```

### Atualizar usuário
```
PUT /users/{id}
```

### Remover usuário
```
DELETE /users/{id}
```

---

## 🛠️ Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Bean Validation (Jakarta Validation)
- Hibernate / JPA
- Banco de dados relacional
- Swagger / OpenAPI
- Maven
- JUnit

---

## 📂 Estrutura do Projeto

O projeto segue uma organização em camadas, facilitando manutenção e evolução:

- **Controller** – Camada de entrada da API (endpoints REST)
- **Service** – Regras de negócio e validações
- **Repository** – Acesso a dados
- **Entity** – Mapeamento das entidades
- **DTO** – Objetos de transferência de dados
- **Exception / Handler** – Tratamento centralizado de erros

---

## 🧪 Testes

Foram implementados **testes unitários** para validação das regras de negócio e dos serviços, garantindo confiabilidade e prevenindo regressões.

---

## 📑 Documentação da API

A documentação da API é gerada automaticamente via **Swagger/OpenAPI**.

Após iniciar a aplicação, acesse:
```
http://localhost:8080/swagger-ui.html
```
ou
```
http://localhost:8080/swagger-ui/index.html
```

---

## ▶️ Como Executar o Projeto

### Pré-requisitos

- Java 17+ (ou a versão configurada no projeto)
- Maven

### Passos
```
mvn clean install
mvn spring-boot:run
```

A aplicação estará disponível em:
```
http://localhost:8080
```

---

## 🎯 Objetivo do Projeto

Este projeto tem como objetivo demonstrar conhecimentos em:

- Desenvolvimento de APIs REST com Java e Spring Boot
- Boas práticas de arquitetura backend
- Validações de domínio
- Integração com banco de dados via JPA
- Escrita de testes unitários
- Documentação de APIs

---
