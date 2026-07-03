# Reservation Management System — Full-Stack App

![Java](https://img.shields.io/badge/Java_17-ED8B00?style=flat-square&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot_3-6DB33F?style=flat-square&logo=springboot&logoColor=white)
![React](https://img.shields.io/badge/React-20232A?style=flat-square&logo=react&logoColor=61DAFB)
![Material UI](https://img.shields.io/badge/MUI-007FFF?style=flat-square&logo=mui&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-blue.svg?style=flat-square)

A full-stack web application for managing customer reservations, built with a decoupled backend/frontend architecture and following standard development best practices, including automated testing and API documentation.

<!-- Add a screenshot or GIF of the app here once available -->
<!-- ![Demo](./docs/demo.gif) -->

## Overview

This project implements a complete reservation management workflow, from data validation and persistence on the backend to an interactive, responsive interface on the frontend. The backend and frontend are fully decoupled and communicate through a REST API, documented with OpenAPI/Swagger.

## Features

- **Create reservations** — form validation and duplicate-entry control
- **List reservations** — real-time data display
- **Delete reservations** — deletion flow with confirmation step
- **Robust validation** — server-side error handling combined with frontend visual feedback

## Tech Stack

**Backend (REST API)**
- Java 17 & Spring Boot 3
- JPA / Hibernate for data persistence
- H2 Database (in-memory database for development)
- JUnit 5 & Mockito for unit and integration testing
- OpenAPI / Swagger for automatic API documentation
- Maven for dependency management

**Frontend**
- React Router for client-side navigation (`/admin`, `/user`, `/home`)
- Material UI (MUI) as the component library
- Interactive forms with visual feedback and responsive design

## Project Structure

```
gestion-reservas-fullstack/
├── reservas-api/         # Spring Boot backend (REST API)
└── reservas-frontend/    # React frontend (SPA)
```

## Getting Started

### Prerequisites

- Java 17+
- Node.js and npm
- Maven (or use the included wrapper)

### 1. Backend

The API server runs on port `8080`.

```bash
cd reservas-api
./mvnw spring-boot:run
```

API documentation (Swagger UI): `http://localhost:8080/swagger-ui/index.html`

### 2. Frontend

The web client runs on port `5173`.

```bash
cd reservas-frontend
npm install
npm run dev
```

Application URL: `http://localhost:5173`

### 3. Running Tests

The backend includes test coverage for the core business logic.

```bash
cd reservas-api
./mvnw test
```

## What I Learned

Working on this project helped me deepen my understanding of decoupled architectures, where the frontend and backend evolve independently and communicate exclusively through a well-defined REST contract. It also gave me hands-on practice writing meaningful unit and integration tests rather than treating testing as an afterthought, and using Swagger to keep API documentation in sync with the actual implementation.

## License

This project is licensed under the MIT License.

## Author

**Miguel Alcázar**
[LinkedIn](https://www.linkedin.com/in/miguel-alc%C3%A1zar-5b3bb5283/)
