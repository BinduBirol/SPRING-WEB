# SPRING-WEB

A Spring Boot based full-stack web application with role-based authentication, profile management, and secure password reset functionality using a modern Bootstrap UI.

---

## 📋 Overview

SB Web is a production-ready full-stack web application built using Spring Boot. It demonstrates real-world backend development concepts including authentication, session management, role-based access control, and user profile handling.

The project is designed to showcase enterprise-grade backend development skills with a focus on security and user experience.

---

## ✨ Features

- ✅ User authentication with session-based login  
- ✅ Role-based access control (USER / ADMIN)  
- ✅ Secure password reset flow with token validation  
- ✅ Profile information viewing and editing  
- ✅ Bootstrap 5 responsive UI  
- ✅ SweetAlert2 integration for enhanced UX  
- ✅ Clean MVC architecture  
- ✅ Password encryption using BCrypt  

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|-----------|
| **Backend** | Java, Spring Boot, Spring Security |
| **Frontend** | Thymeleaf, Bootstrap 5, JavaScript |
| **Database** | MySQL / Oracle |
| **Security** | BCrypt, Session Management |
| **UI Enhancements** | SweetAlert2 |

---

## 🔐 Authentication System

- Secure login with session management
- Role-based dashboard redirection
- Password encryption using BCrypt
- Session invalidation on logout
- CSRF protection
- Secure session handling

---

## 🔑 Password Reset Flow

1. User requests password reset
2. System generates a secure, time-limited token
3. Token is sent via email link
4. Token validation with expiry check
5. Password updated after successful validation
6. Token invalidated after use

---

## 👤 User Features

- 📄 View profile information
- ✏️ Edit profile details
- 🔒 Change password securely
- 📊 Access role-based dashboard
- 🚪 Secure logout with session cleanup

---

## 🎨 UI Features

- 📱 Responsive Bootstrap layout
- 🧭 Intuitive sidebar navigation
- 👤 User profile card layout
- 🔔 SweetAlert2 notifications:
  - ✅ Success messages
  - ❌ Error handling
  - ⚠️ Logout confirmation
  - ℹ️ Info dialogs

---

## 📁 Project Structure
SPRING-WEB
├── src/main/java
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── model/
│   ├── config/
│   └── security/
│
├── src/main/resources
│   ├── templates/
│   ├── static/
│   │   ├── css/
│   │   ├── js/
│   │   └── images/
│   └── application.properties
│
└── pom.xml

---

## 🚀 Getting Started

### Prerequisites
- Java 11+
- Maven 3.6+
- MySQL 8.0+ or Oracle Database
- Git

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/BinduBirol/SPRING-WEB.git
   cd SPRING-WEB

## Configure Database

Update `application.properties` with your database credentials:
  `spring.datasource.url=jdbc:mysql://localhost:3306/spring-web?useLegacyDatetimeCode=false&serverTimezone=Asia/Jakarta&useSSL=true&allowPublicKeyRetrieval=true
  spring.datasource.username=root
  spring.datasource.password=yourpassword`

## Build the project

bash
`mvn clean install`
Run the application

bash
`mvn spring-boot:run`
Access the application

Open your browser and navigate to `http://localhost:8081/spring`

# 📖 Usage

## Register
- Click on "Create Account" link on the login page
- Fill in your details (username, email, password)
- Accept terms and conditions
- Click "Register" button
- Account created successfully, you can now login

## Login
- Navigate to the login page
- Enter your credentials
- You'll be redirected to your role-based dashboard

## Profile Management
- Click on your profile icon
- View or edit your information
- Changes are saved securely

## Password Reset
- Click "Forgot Password" on the login page
- Enter your email address
- Check your email for the reset link
- Follow the link and set a new password

---

# 🔒 Security Features

- **Password Encryption:** BCrypt hashing for all passwords
- **Session Security:** Secure session management with timeout
- **CSRF Protection:** Spring Security CSRF tokens
- **SQL Injection Prevention:** Parameterized queries
- **XSS Protection:** Template engine escaping
- **Role-Based Access:** Method-level security annotations

---

# 🎯 Purpose

This project demonstrates:

- ✅ Enterprise-grade backend development skills
- ✅ Secure authentication & authorization design
- ✅ Real-world system architecture
- ✅ Full-stack web development capability
- ✅ Best practices in Spring Boot development
- ✅ Production-ready code quality

