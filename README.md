# EcoSafe - Sistema de Monitoramento Ambiental

Sistema de monitoramento ambiental com API REST para gestão de usuários, locais e sensores.

## 🚀 Funcionalidades

- **Autenticação JWT** - Login e registro
- **CRUD Usuários** - Gestão de usuários
- **CRUD Locais** - Gestão de localizações
- **CRUD Sensores** - Gestão de sensores
- **API REST** - Endpoints com paginação
- **Documentação Swagger** - API documentada

## 🛠️ Tecnologias

- **Java 17**
- **Spring Boot 3.5.0**
- **Spring Security + JWT**
- **Spring Data JPA**
- **H2 Database**
- **Bean Validation**
- **Swagger/OpenAPI**

## 🚀 Como executar

```bash
mvn spring-boot:run
```

**Acessos:**
- API: http://localhost:8080
- Swagger: http://localhost:8080/swagger-ui.html
- H2 Console: http://localhost:8080/h2-console

## 🎯 Dados de Exemplo

**O sistema já vem com dados prontos para teste:**

### Usuários (senha: `123456`)
- `admin@ecosafe.com` - Administrador (São Paulo)
- `joao@ecosafe.com` - João Silva (Rio de Janeiro)  
- `maria@ecosafe.com` - Maria Santos (Belo Horizonte)

### Locais
- Centro de Monitoramento SP (São Paulo/SP)
- Estação Rio de Janeiro (Rio de Janeiro/RJ)
- Base Belo Horizonte (Belo Horizonte/MG)

### Sensores
- Pluviômetro, Termômetro (São Paulo)
- Anemômetro, Higrômetro (Rio de Janeiro)
- Barômetro (Belo Horizonte)

## 📚 Endpoints

### Autenticação
- `POST /api/auth/login`
- `POST /api/auth/registro`

### Usuários  
- `GET /api/usuarios` - Listar (paginado)
- `POST /api/usuarios` - Criar
- `GET /api/usuarios/{id}` - Buscar por ID
- `PUT /api/usuarios/{id}` - Atualizar
- `DELETE /api/usuarios/{id}` - Deletar
- `GET /api/usuarios/buscar?nome=` - Buscar por nome

### Locais
- `GET /api/locais` - Listar (paginado)
- `POST /api/locais` - Criar
- `GET /api/locais/{id}` - Buscar por ID
- `PUT /api/locais/{id}` - Atualizar
- `DELETE /api/locais/{id}` - Deletar
- `GET /api/locais/buscar/cidade?cidade=` - Buscar por cidade

### Sensores
- `GET /api/sensores` - Listar (paginado)
- `POST /api/sensores` - Criar
- `GET /api/sensores/{id}` - Buscar por ID
- `PUT /api/sensores/{id}` - Atualizar
- `DELETE /api/sensores/{id}` - Deletar
- `GET /api/sensores/buscar/tipo?tipo=` - Buscar por tipo

## 🔒 Autenticação

1. Registre um usuário em `/api/auth/registro`
2. Faça login em `/api/auth/login`
3. Use o token: `Authorization: Bearer {token}`

**Para teste rápido, use:** `admin@ecosafe.com` / `123456`

## 🗃️ Banco H2

- Console: http://localhost:8080/h2-console
- URL: `jdbc:h2:mem:ecosafe`
- User: `sa` | Password: (vazio) 