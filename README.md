# 📜 Permit Service

The **Permit Service** is a Spring Boot microservice that manages citizen permits.  
It provides APIs for:
- Creating and managing permits
- Tracking permit status & payment status
- Managing permit types (CRUD)
- Fetching permits by applicant (citizen)

⚠️ **Note:** This service communicates with the **User Service** (via RestTemplate).  
You **must run `user-service` first** before starting this service.

---

## 🚀 Features
- Permit APIs:
  - Create new permit
  - Get all permits
  - Get permits by applicant (citizen)
  - Update permit status
  - Update payment status
- Permit Type APIs:
  - Create new permit type
  - Get all permit types
  - Update permit type
  - Delete permit type
- Integration with **User Service** (RestTemplate)
- PostgreSQL integration
- Global API response wrapper (`ApiResponse<T>`)
- Exception handling with unified error responses
- Swagger UI for API docs

---

## 🛠️ Tech Stack
- **Java 17**
- **Spring Boot 3**
- Spring Web
- Spring Data JPA (Hibernate)
- PostgreSQL
- MapStruct (DTO mapping)
- Docker
- Swagger (OpenAPI)

---

## ⚙️ How to Run

### 1️⃣ Run with Maven (local)
```bash
mvn spring-boot:run
```
### 2️⃣ Run with Docker
- Build the image:
```bash
docker build -t user-service .
```
- Run container:
```bash
docker run -p 8082:8082 user-service
```

### 3️⃣ Run with Docker Compose
Recommended way (with PostgreSQL):
```bash
docker-compose up --build
```

---
## 🌍 API Endpoints

### 📝 Permit Endpoints (/api/v1/permits)
| Method | Endpoint               | Description                  |
| ------ | ---------------------- | ---------------------------- |
| POST   | `/`                    | Create a new permit          |
| GET    | `/`                    | Get all permits              |
| GET    | `/applicant/{id}`      | Get permits by applicant ID  |
| PUT    | `/{id}/status`         | Update permit status         |
| PUT    | `/{id}/payment-status` | Update permit payment status |

---
### 🏷️ Permit Type Endpoints (/api/v1/permit-types)
| Method | Endpoint | Description              |
| ------ | -------- | ------------------------ |
| POST   | `/`      | Create new permit type   |
| GET    | `/`      | Get all permit types     |
| PATCH  | `/{id}`  | Update permit type by ID |
| DELETE | `/{id}`  | Delete permit type by ID |

---

## 📖 Example Requests (Postman)

### ➡️ Create Permit

```bash
POST /api/v1/permits
Content-Type: application/json

{
  "applicantId": "e2c33fba-1df1-4e22-98cd-71a64a812abc",
  "permitTypeId": 1,
  "details": "Requesting a building permit"
}
```

### ➡️ Update Permit Status
```bash
PUT /api/v1/permits/{id}/status?status=APPROVED
```

### ➡️ Update Payment Status
```bash
PUT /api/v1/permits/{id}/payment-status?status=PAID
```

### ➡️ Create Permit Type
```bash
POST /api/v1/permit-types
Content-Type: application/json

{
  "name": "Construction",
  "description": "Permit for building construction"
}
```

---

## 🐘 Database (PostgreSQL)
Default database config (edit in application.yml if needed):
```bash
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/permitdb
    username: user
    password: password
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```
You can view tables & data using pgAdmin.

---

## 📦 Project Structure
```bash
permit-service/
 ┣ src/main/java/ghaidaa/com/permit_service
 ┃ ┣ controllers/        # REST Controllers
 ┃ ┣ dtos/              # Request & Response DTOs
 ┃ ┣ entities/          # JPA Entities
 ┃ ┣ enums/             # Permit, Payment Status enums
 ┃ ┣ exceptions/        # Custom exceptions + handler
 ┃ ┣ mappers/           # MapStruct interfaces
 ┃ ┣ repositories/      # JPA Repositories
 ┃ ┣ services/          # Service interfaces & impls
 ┃ ┗ PermitServiceApp.java # Main class
 ┣ src/main/resources/
 ┃ ┣ application.yml     # Config
 ┗ pom.xml

```
