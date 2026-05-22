# Descarte Certo - Infraestrutura e Banco de Dados

Projeto Spring Boot com infraestrutura completa para gerenciar banco de dados Oracle da aplicação Descarte Certo, que gerencia ações de reciclagem e descarte correto de materiais através de uma plataforma de ecopontos.

**Este repositório contém apenas a infraestrutura de banco de dados e configuração. A implementação das APIs será feita separadamente.**

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.5.14**
- **Oracle Database 11g/XE**
- **Flyway** (Database Migrations)
- **Docker & Docker Compose**
- **Maven**

## Estrutura do Projeto

```
descarte-certo-app-java/
├── src/
│   ├── main/
│   │   └── resources/
│   │       ├── application.properties
│   │       └── db/migration/          # Scripts Flyway
│   │           ├── V2__Create_Usuarios_Table.sql
│   │           ├── V3__Create_Materiais_Table.sql
│   │           ├── V4__Create_Ecopontos_Table.sql
│   │           ├── V5__Create_Agendamentos_Table.sql
│   │           └── V6__Insert_Initial_Data.sql
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── .gitignore
```

## Executando com Docker (RECOMENDADO)

### Pré-requisitos
- Docker e Docker Compose instalados

### Passos para executar

1. **Clone o repositório**
```bash
git clone <repository-url>
cd descarte-certo-app-java
```

2. **Build da imagem e start dos containers**
```bash
docker-compose up --build
```

O primeiro startup pode levar alguns minutos pois o banco de dados Oracle precisa ser inicializado e as migrations Flyway serão executadas automaticamente.

O banco de dados estará disponível em:
- **Host**: `localhost`
- **Porta**: `1521`
- **SID**: `ORCL`
- **Usuário**: `system`
- **Senha**: `oracle`

### Verificar logs
```bash
docker-compose logs -f
```

### Parar containers
```bash
docker-compose down
```

### Remover volumes (limpar dados)
```bash
docker-compose down -v
```

## Executando localmente (sem Docker)

### Pré-requisitos
- JDK 17+
- Maven 3.9+
- Oracle Database 11g/XE instalado localmente ou acesso ao servidor Oracle da FIAP

### Passos

1. **Clonar repositório**
```bash
git clone <repository-url>
cd descarte-certo-app-java
```

2. **Configurar credenciais do banco** (em `src/main/resources/application.properties`)
```properties
spring.datasource.url=jdbc:oracle:thin:@//seu-servidor:1521/seu-banco
spring.datasource.username=seu-usuario
spring.datasource.password=sua-senha
```

3. **Build da aplicação (migrations serão executadas automaticamente)**
```bash
mvn clean compile
```
