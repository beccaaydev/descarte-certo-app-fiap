# 🌱 Descarte Certo — API RESTful com Spring Boot

Sistema desenvolvido para incentivar o descarte correto de resíduos e promover ações sustentáveis através de uma plataforma de ecopontos.
Este projeto foi desenvolvido em grupo para a disciplina de Microsserviços com Spring, utilizando boas práticas de desenvolvimento backend, arquitetura RESTful, segurança e integração com banco de dados Oracle.

---

## 📌 Sobre o Projeto

O **Descarte Certo** é uma solução voltada para ESG (*Environmental, Social and Governance*), criada com o objetivo de facilitar o gerenciamento de descarte de materiais recicláveis por meio de ecopontos e agendamentos.

A aplicação foi construída utilizando o ecossistema Spring, desde a configuração inicial até recursos mais avançados de validação, tratamento de exceções e segurança da API.

---

## 🚀 Funcionalidades

✔️ API RESTful estruturada com Spring Boot
✔️ Endpoints para gerenciamento da aplicação
✔️ Integração com banco de dados Oracle
✔️ Versionamento do banco com Flyway
✔️ Validação de dados com Bean Validation
✔️ Tratamento global de exceções
✔️ Arquitetura baseada em microsserviços
✔️ Segurança de endpoints com Spring Security
✔️ Persistência de dados com Spring Data JPA
✔️ Containerização com Docker

---

## 🛠️ Tecnologias Utilizadas

### Backend

* Java 17
* Spring Boot 3
* Spring Web
* Spring Data JPA
* Spring Security
* Bean Validation
* Flyway

### Banco de Dados

* Oracle Database

### DevOps & Ferramentas

* Docker
* Docker Compose
* Maven

---

## 📂 Estrutura do Projeto

```bash
src
 ┣ main
 ┃ ┣ java
 ┃ ┃ ┗ br/com/descartecerto
 ┃ ┣ resources
 ┃ ┃ ┣ application.properties
 ┃ ┃ ┗ db/migration
 ┣ test
```

---

## 🔐 Segurança

A aplicação utiliza o **Spring Security** para proteger endpoints e garantir controle de acesso adequado.

Entre os conceitos aplicados:

* autenticação
* autorização
* proteção de rotas
* tratamento de acessos indevidos
* boas práticas de segurança para APIs REST

---

## 🧩 Conceitos Aplicados

Este projeto teve foco acadêmico e prático no desenvolvimento de APIs modernas utilizando o ecossistema Spring.

### Alguns conceitos trabalhados:

* arquitetura RESTful
* microsserviços
* injeção de dependência
* DTOs
* validações
* tratamento de exceções
* versionamento de banco
* segurança de APIs
* persistência de dados
* integração com Oracle
* containerização

---

## ▶️ Como Executar o Projeto

### Pré-requisitos

* Java 17+
* Maven
* Docker e Docker Compose
* Oracle Database

---

### Clone o repositório

```bash
git clone https://github.com/beccaaydev/descarte-certo-app-fiap.git
```

```bash
cd descarte-certo-app-fiap
```

---

### Executar com Docker

```bash
docker compose up --build
```

---

### Executar localmente

```bash
mvn clean install
```

```bash
mvn spring-boot:run
```

---

## 🗄️ Banco de Dados

A aplicação utiliza Oracle Database integrado ao Spring Data JPA.

As migrations são gerenciadas utilizando o Flyway.

---

## 📖 Endpoints REST

A API possui endpoints RESTful desenvolvidos para gerenciamento das funcionalidades do sistema.

Exemplos:

* gerenciamento de usuários
* materiais recicláveis
* ecopontos
* agendamentos

---

## 👩‍💻 Equipe

Projeto desenvolvido em grupo para a disciplina de Microsserviços com Spring.

Desenvolvido por:

* [Rebeca Ayres](https://github.com/beccaaydev?utm_source=chatgpt.com) - @beccaaydev
* [Elise Oliveira](https://github.com/euelise?utm_source=chatgpt.com) - @eueelise
* [Laís Sallas](https://github.com/laissallas?utm_source=chatgpt.com) - @laissallas
* [Gabrielli Martinelli](https://github.com/Gabrielli-Martinelli?utm_source=chatgpt.com) - @Gabrielli-Martinelli

---

## 🌍 Objetivo ESG

O projeto foi criado com foco em conscientização ambiental e incentivo ao descarte sustentável de resíduos, contribuindo para práticas alinhadas aos princípios ESG.

---

## 📄 Licença

Este projeto possui fins acadêmicos e educacionais.
