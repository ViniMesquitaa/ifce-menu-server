# 🛡️ Portal do Aluno IFCE - Backend

Backend da aplicação **Portal do Aluno IFCE**, desenvolvido com **Java + Spring Boot**, com autenticação via **JWT**, segurança com **Spring Security**, persistência com **JPA** e banco de dados **PostgreSQL**.

---

## 🧭 Índice

- [🚀 Visão Geral](#-visão-geral)
- [🛠️ Tecnologias](#️-tecnologias)
- [⚙️ Configuração](#️-configuração)
    - [✅ Pré-requisitos](#-pré-requisitos)
    - [📥 Instalação](#-instalação)
- [📁 Estrutura de Pacotes](#-estrutura-de-pacotes)
- [🔐 Segurança e Autenticação](#-segurança-e-autenticação)
- [🌐 Endpoints Principais](#-endpoints-principais)
- [🎯 Scripts SQL](#-scripts-sql)

---

## 🚀 Visão Geral

Este backend expõe uma **API RESTful** para autenticação de estudantes, gerenciamento de perfis e integração com o frontend. A autenticação é baseada em **JWT**, com segurança configurada via **Spring Security**, banco de dados relacional **PostgreSQL** e arquitetura em camadas.

---

## 🛠️ Tecnologias

- Java 17+
- Spring Boot
- Spring Security
- Spring Data JPA
- Lombok
- JWT (JSON Web Token)
- PostgreSQL
- DevTools (Hot Reload)
- Maven

---

## ⚙️ Configuração

### ✅ Pré-requisitos

- Java 17 ou superior
- PostgreSQL rodando na porta 5432
- Maven instalado

### 📥 Instalação

1. Clone o repositório:  
   `git clone https://github.com/ViniMesquitaa/ifce-menu-server.git`

2. Acesse o diretório do projeto:  
   `cd ifce-menu-server`

3. Configure o banco de dados no arquivo `application.properties`:
    - URL, usuário e senha do PostgreSQL

4. Execute o projeto via IDE ou com o Maven:  
   `./mvnw spring-boot:run`

---

## 📁 Estrutura de Pacotes

src/main/java/...

- **controller/** → Controladores REST (`@RestController`)
- **entity/** → Entidades JPA
- **infra/cors/** → Configuração global de CORS
- **infra/security/** → Filtros, JWT, configurações de segurança
- **repositorie/** → Interfaces que estendem JpaRepository
- **service/** → Regras de negócio e lógica de aplicação

---

## 🔐 Segurança e Autenticação

A autenticação e autorização são feitas com **JWT**:

- Ao fazer login, um **token JWT** é gerado e retornado.
- O token deve ser enviado no header `Authorization: Bearer <token>`.
- O token tem expiração de 24h.
- O backend valida o token em cada requisição protegida.

**Spring Security** é configurado para:

- Proteger rotas privadas (`/profile`, `/`, etc.)
- Permitir livre acesso a rotas públicas (`/auth/login`, `/auth/register`)

---

## 🌐 Endpoints Principais

**Autenticação**
- `POST /auth/register` – Registro de novo aluno
- `POST /auth/login` – Login e retorno do token JWT

**Estudante**
- `GET /` – Dados do aluno autenticado
- `PUT /` – Atualiza nome, curso ou senha

---

## 🎯 SQL

É necessário criar um banco de dados e alterar as informações do banco de dados no arquivo `application.properties`:
- URL, usuário e senha do PostgreSQL

