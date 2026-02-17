# PayMyCard Spring Boot Backend

This is a lightweight, production-ready Spring Boot backend for the PayMyCard application.

## 🛠 Tech Stack
- **Java 17**
- **Spring Boot 3.2.2**
- **Spring Data JPA** (PostgreSQL)
- **Spring Security** (JWT Authentication)
- **Lombok**
- **Maven**

## 📂 Project Structure
```
src/main/java/com/paymycard/backend/
├── config/             # Configuration beans
├── controller/         # REST Controllers (14 endpoints)
├── dto/                # Data Transfer Objects
├── exception/          # Global Exception Handling
├── model/              # JPA Entities
├── repository/         # Spring Data Repositories
├── security/           # JWT & Security Configuration
└── service/            # Business Logic
```

## 🚀 How to Run

### 1. Prerequisites
- JDK 17 installed
- PostgreSQL installed and running
- Maven installed

### 2. Database Setup
Create a database named `paymycard` in your PostgreSQL.

### 3. Configuration
Update `src/main/resources/application.properties` with your database credentials:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/paymycard
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 4. Build and Run
```bash
./mvnw clean install
./mvnw spring-boot:run
```

## 🔑 Authentication
The API uses JWT for authentication. 
1. **Signup/Login**: Use `/api/auth/signup` or `/api/auth/login` to get a token.
2. **Authorized Requests**: Add `Authorization: Bearer <your_token>` header to all other requests.

## 📡 Endpoints Summary

### Auth
- `POST /api/auth/signup`
- `POST /api/auth/login`
- `POST /api/auth/logout`

### Profile
- `GET /api/user/profile`
- `PUT /api/user/profile`

### Recipients
- `GET /api/recipients`
- `POST /api/recipients`
- `DELETE /api/recipients/{id}`

### Merchant
- `GET /api/merchant/profile`
- `POST /api/merchant/profile`
- `PUT /api/merchant/profile`
- `DELETE /api/merchant/profile`

### Dashboard
- `GET /api/dashboard` (Public)

---
Developed for PayMyCard Mobile Application.
