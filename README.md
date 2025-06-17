# 🤖 Genius Microservices Platform

This project is a modern **microservices architecture** built to power an **LLM (Large Language Model)** and **RAG (Retrieval-Augmented Generation)** based **chatbot** and **intelligent assistant** system.

## 🚀 Objective

To provide a scalable, modular, and secure backend infrastructure for LLM-based solutions that involve document-based QA, intelligent conversations, and assistant functionalities.

---

## 🏗️ Architecture & Design Principles

The project follows **modern backend architecture principles**, including:

- **Clean Code** practices (readable, maintainable, testable code)
- **Domain-Driven Design (DDD)** with clear separation of core business logic
- **Use Case → Service → Impl** layered structure for better modularity and testability
- Organized as a **monorepo** for unified dependency and version management
- Built with **Java 21**, **Spring Boot**, **Spring Cloud**, and **OpenFeign** for seamless microservice communication

---

## Repository Structure

```
genius/
│
├── auth-service/       # Spring Boot Auth Microservice
├── user-service/       # User Management Microservice
├── common/             # Shared DTOs, Utilities, Enums, etc.
├── config-server/      # Spring Cloud Config for central config
├── docker/             # Dockerfiles and docker-compose.yml
└── README.md
```


The services are built using **Spring Boot**, **Spring Cloud**, **OpenFeign**, and **Maven**.

---

## 🔐 Microservices Overview

| Service           | Description                                                                 |
|-------------------|-----------------------------------------------------------------------------|
| `auth-service`    | User registration, login, token generation and refresh token functionality |
| `user-service`    | User data management, roles, and authorization logic                        |
| `common`          | Reusable components, response models, DTOs shared across services          |
| `config-server`   | Centralized configuration management for all environments                  |

---

## ⚙️ Tech Stack

- **Java 21+**
- **Spring Boot 3.4**
- **Spring Security + JWT**
- **Spring Cloud Config**
- **OpenFeign (for service-to-service communication)**
- **PostgreSQL**
- **Docker / Docker Compose**
- **Maven (Monorepo)**
- **JUnit 5 & Mockito & MockMvc (for integration testing)**

---

## 🧪 Testing

All services are tested using:

- `@SpringBootTest` with `MockMvc`
- `@MockBean` for mocking Feign clients
- Test-specific PostgreSQL database (via `application-test.yml`)
- Transactional rollback to avoid permanent DB writes during tests

```bash
# Run tests
mvn clean test
```

## 🐳 Running with Docker
To spin up the platform using Docker Compose:

```bash
docker-compose up --build
```

## 📌 Roadmap / TODO

- [x] Implement `auth-service` and `user-service`
- [x] Create a `common` module for shared models/utilities
- [ ] Add `config-server` for centralized environment configs
- [ ] Implement `chat-service` (LLM + RAG pipeline)
- [ ] Add `vector-store` service for document embedding & semantic search
- [ ] Integrate frontend (Next.js / React)

## 📜 License
This project is licensed under the MIT License – see the LICENSE file for details.

## 👨‍💻 Author & Contact
#### Developed by IOTIQ
#### 📧 Email: suat@iotiq.net
#### 🔗 LinkedIn: https://linkedin.com/in/suatbayir