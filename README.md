# IT342 - User Registration and Authentication System
**Name:** Ralph John Arnejo  
**Group:** G3  
**Lab:** Laboratory 2 (Session 2)

---

## Project Description

A full-stack user registration and authentication system built with Spring Boot (backend) and ReactJS (web frontend). Users can register with a username and password (encrypted with BCrypt), log in, view their profile on a protected dashboard, and log out. The mobile application will be implemented in Session 2.

## Technologies Used

| Layer    | Technology                         |
|----------|------------------------------------|
| Backend  | Java 17, Spring Boot 4.1, Spring Security, Spring Data JPA |
| Database | MySQL 8 (`user_auth_db`)           |
| Web App  | ReactJS (Vite), React Router DOM   |
| Mobile   | *(Session 3)*                      |
| Others   | BCrypt Password Encryption, Maven  |

## Steps to Run Backend

```bash
# 1. Ensure MySQL is running and the database exists
mysql -u root -e "CREATE DATABASE IF NOT EXISTS user_auth_db;"

# 2. Navigate to backend folder
cd backend

# 3. Run the Spring Boot application
./mvnw spring-boot:run
```

The backend will start at `http://localhost:8080`.

> **Note:** If your MySQL has a password, update `backend/src/main/resources/application.properties` with your credentials.

## Steps to Run Web App

```bash
# 1. Navigate to the web folder
cd web

# 2. Install dependencies
npm install

# 3. Start the development server
npm run dev
```

The web app will start at `http://localhost:5173`.

## Steps to Run Mobile App

> Mobile app will be implemented in Laboratory Session 2.

## API Endpoints

| Method | Endpoint             | Auth     | Description                        |
|--------|----------------------|----------|------------------------------------|
| POST   | `/api/auth/register` | Public   | Register a new user (BCrypt encrypted) |
| POST   | `/api/auth/login`    | Public   | Login with username and password   |
| POST   | `/api/auth/logout`   | Public   | Invalidate session and log out     |
| GET    | `/api/user/me`       | Protected | Get the currently logged-in user's info |

### Request/Response Examples

**Register:**
```json
POST /api/auth/register
{ "username": "john", "password": "secret123" }
→ 200: "User john registered with encrypted password!"
→ 400: "Username already taken!"
```

**Login:**
```json
POST /api/auth/login
{ "username": "john", "password": "secret123" }
→ 200: "Login successful!"
→ 401: "Invalid username or password."
```

**Get Profile:**
```json
GET /api/user/me
→ 200: { "id": 1, "username": "john" }
→ 401: "Not authenticated."
```
