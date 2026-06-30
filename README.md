# Ecommerce Microservices Platform

A **production-ready Spring Boot microservices architecture** for scalable e-commerce applications with service discovery, API gateway, and JWT authentication.

## 🎯 What This Project Shows

- **Microservices Architecture** – Decoupled services with independent databases
- **Spring Cloud Ecosystem** – Eureka service discovery, API Gateway, OpenFeign clients
- **Security** – JWT authentication, Spring Security, encrypted token validation
- **Full-Stack** – Java backend + React frontend with modern tooling
- **Scalability** – Stateless services, load balancing, event-driven design

## 🏗️ Tech Stack

| Layer | Technology |
|-------|-----------|
| **Backend** | Java 17, Spring Boot 3.5+, Spring Cloud 2025.0.1 |
| **API** | Spring Cloud Gateway, REST APIs, OpenFeign |
| **Auth** | JWT (JJWT 0.13), Spring Security |
| **Database** | MySQL 8.0+ (service isolation) |
| **Frontend** | React, Vite, JavaScript |
| **Build** | Maven 3.8+ |

## 📦 Core Services

```
api-gateway/          → Request routing & JWT validation
eureka-server/        → Service registry & discovery
auth-service/         → User authentication & token generation
product-service/      → Product catalog management
order-service/        → Order processing with async events
payment-service/      → Payment processing with WebFlux
notification-service/ → Event-driven notifications
review-service/       → Product reviews & ratings
admin-service/        → Admin operations
frontend/             → React UI with Vite
```

## 🚀 Quick Start

### Prerequisites
- Java 17+, Maven 3.8+, MySQL 8.0+, Node.js 18+

### Run Services

```bash
# Start Eureka Server
cd eureka-server
mvn spring-boot:run

# In separate terminals, start services
cd api-gateway && mvn spring-boot:run
cd auth-service && mvn spring-boot:run
cd product-service && mvn spring-boot:run
cd order-service && mvn spring-boot:run
cd payment-service && mvn spring-boot:run

# Start Frontend
cd frontend && npm install && npm run dev
```

**Access Points:**
- Eureka Dashboard: `http://localhost:8761`
- Frontend: `http://localhost:5173`
- API Gateway: `http://localhost:8080`

## 🔐 Key Features

✅ JWT token-based authentication with refresh mechanism  
✅ API Gateway with centralized routing and request filtering  
✅ Service-to-service communication with Feign clients  
✅ Microservices with database per service isolation  
✅ Eureka-based dynamic service discovery  
✅ RESTful API design with proper HTTP status codes  
✅ Database transaction management & Hibernate ORM  

## 💡 Architecture Highlights

**Synchronous Communication:**
```java
@FeignClient("product-service")
public interface ProductClient {
    @GetMapping("/api/products/{id}")
    ProductDTO getProduct(@PathVariable Long id);
}
```

**Built by:** [@omethperera](https://github.com/omethperera)
