# Ecommerce Microservices Platform

A production-ready **Spring Boot microservices architecture** for building scalable e-commerce applications with cloud-native patterns, service discovery, and API gateway routing.

## 🎯 Project Overview

This is a **distributed e-commerce platform** showcasing modern Java backend architecture with:
- **Microservices Architecture** – Decoupled services handling independent domains
- **API Gateway** – Centralized routing and JWT authentication
- **Service Discovery** – Eureka-based service registry
- **Spring Cloud** – Enterprise-grade distributed computing patterns
- **React Frontend** – Modern UI with Vite bundler
- **MySQL Database** – Persistent data layer across services

**Technology Stack:**
- **Backend:** Java 17, Spring Boot 3.5+, Spring Cloud 2025.0.1
- **Frontend:** React, Vite, JavaScript
- **Infrastructure:** Netflix Eureka, Spring Cloud Gateway
- **Authentication:** JWT (JJWT 0.13.0)
- **Database:** MySQL 8.0+
- **Build:** Maven 3.8+

---

## 🏗️ System Architecture

### Core Microservices

| Service | Purpose | Tech Stack |
|---------|---------|-----------|
| **API Gateway** | Request routing, JWT validation, load balancing | Spring Cloud Gateway WebMVC, JJWT |
| **Auth Service** | User authentication, token generation | Spring Security, JWT |
| **Product Service** | Product catalog management | Spring Data JPA, REST API |
| **Order Service** | Order processing & fulfillment | Spring Data JPA, RabbitMQ, OpenFeign |
| **Payment Service** | Payment processing & transactions | Spring Data JPA, WebFlux, OpenFeign |
| **Notification Service** | Email/SMS notifications | Event-driven architecture |
| **Review Service** | Product reviews & ratings | Spring Data JPA, REST API |
| **Admin Service** | Administrative operations | OpenFeign client communication |
| **Eureka Server** | Service discovery & registration | Netflix Eureka Server |

### Communication Patterns
- **Synchronous:** OpenFeign (service-to-service calls)
- **Asynchronous:** RabbitMQ (event-driven messaging)
- **Client Routing:** Eureka-based load balancing (`lb://service-name`)

---

## 📊 Key Features

### ✅ Implemented
- ✓ Multi-tenant microservices architecture
- ✓ Centralized API Gateway with JWT middleware
- ✓ Service discovery with Eureka
- ✓ Database isolation per service
- ✓ Inter-service communication (Feign clients)
- ✓ Asynchronous messaging with AMQP
- ✓ React-based frontend with modern build tooling

### 🚀 Demonstrates Expertise In
- **System Design:** Microservices patterns, service decoupling, scalability
- **Spring Ecosystem:** Spring Boot, Spring Cloud, Spring Security, Spring Data JPA
- **Backend Development:** REST APIs, JWT authentication, database design
- **Distributed Systems:** Service discovery, API gateways, async messaging
- **Frontend:** React, Vite, modern JavaScript tooling
- **DevOps-Ready:** Multi-module Maven project, containerizable architecture

---

## 📦 Project Structure
