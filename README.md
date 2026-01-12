# Gestor de Reservas - Full Stack App

Aplicación web completa para la gestión de reservas de clientes. Desarrollada con arquitectura de microservicios (Backend separado del Frontend) y mejores prácticas de desarrollo.

## Tecnologías Utilizadas

### Backend (API REST)
* **Java 17** & **Spring Boot 3**
* **JPA / Hibernate** (Persistencia de datos)
* **H2 Database** (Base de datos en memoria para desarrollo)
* **JUnit 5 & Mockito** (Tests Unitarios y de Integración)
* **OpenAPI / Swagger** (Documentación automática de la API)
* **Maven** (Gestión de dependencias)

### Frontend (SPA)
* **React + Vite** (Entorno de desarrollo rápido)
* **JavaScript (ES6+)**
* **CSS3** (Diseño responsivo y moderno)
* **Fetch API** (Consumo de servicios REST)

## Funcionalidades

* **Crear Reserva:** Validación de datos y control de duplicados.
* **Listar Reservas:** Visualización en tiempo real de los datos.
* **Eliminar Reserva:** Gestión de borrado con confirmación.
* **Validaciones:** Control de errores robusto (Backend) y feedback visual (Frontend).

## Instalación y Ejecución

### 1. Backend (Servidor)
El servidor corre en el puerto `8080`.
```bash
cd reservas-api
./mvnw spring-boot:run
```

Documentación API (Swagger): http://localhost:8080/swagger-ui/index.html

### 2. Frontend (Cliente)
La web corre en el puerto `5173`.
```bash
cd reservas-frontend
npm install
npm run dev
```
Acceso web: http://localhost:5173

### 3. Testing
El proyecto cuenta con cobertura de tests para la lógica de negocio. Para ejecutar las pruebas:
```bash
cd reservas-api
./mvnw test
```

### Autor

Miguel Alcázar - https://www.linkedin.com/in/miguel-alc%C3%A1zar-5b3bb5283/