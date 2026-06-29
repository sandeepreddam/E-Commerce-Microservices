<div align="center">

# 🛒 E-Commerce Microservices Platform

### Java Spring Boot Microservices Architecture (Production-Oriented Backend System)

Distributed system for authentication, product catalog, cart, orders, payments, and notifications using event-driven and service-oriented design.

[![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk)]()
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?logo=springboot)]()
[![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-Microservices-6DB33F)]()
[![Kafka](https://img.shields.io/badge/Kafka-Event%20Streaming-231F20?logo=apachekafka)]()
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-336791?logo=postgresql)]()
[![Redis](https://img.shields.io/badge/Redis-Cache-DC382D?logo=redis)]()
[![Docker](https://img.shields.io/badge/Docker-Containerized-2496ED?logo=docker)]()
[![License](https://img.shields.io/badge/License-MIT-blue.svg)]()

</div>

---

## 📑 Table of Contents

- [Overview](#-overview)
- [Project Highlights](#-project-highlights)
- [Why I Built This Project](#-why-i-built-this-project)
- [Key Features](#-key-features)
- [Technology Stack](#-technology-stack)
- [Folder Structure](#-folder-structure)
- [Microservices](#-microservices)
- [Architecture Overview](#-architecture-overview)
- [Architecture Diagram](#-architecture-diagram-mermaid)
- [Authentication Flow](#-authentication-flow)
- [Authentication Sequence Diagram](#-authentication-sequence-diagram-mermaid)
- [Service Communication](#-service-communication)
- [Kafka Messaging](#-kafka-messaging)
- [Payment Flow](#-payment-flow)
- [AWS S3 Upload Flow](#-aws-s3-upload-flow)
- [Database & Caching](#-database--caching)
- [Database Design](#-database-design)
- [Infrastructure](#-infrastructure)
- [Getting Started](#-getting-started)
- [Running the Application](#-running-the-application)
- [Environment Variables](#-environment-variables)
- [API Documentation](#-api-documentation)
- [Example Requests](#-example-requests-curl)
- [Example Responses](#-example-responses)
- [Error Responses](#-error-responses)
- [Security Implementation](#-security-implementation)
- [Logging](#-logging)
- [Testing](#-testing)
- [CI/CD Pipeline](#-cicd-pipeline)
- [Roadmap / Future Improvements](#-roadmap--future-improvements)
- [Postman Collection](#-postman-collection)
- [Contributing](#-contributing)
- [License](#-license)
- [Author](#-author)

---

## 📌 Overview

This project demonstrates a **real-world microservices-based e-commerce backend system** built using Java, Spring Boot, and Spring Cloud.

Each business capability is implemented as an independent microservice, enabling scalability, fault isolation, and independent deployment.

---

## 🌟 Project Highlights

- **7 independently deployable Spring Boot microservices**, each owning a single business capability (auth, catalog, cart, orders, payments, notifications) plus an API Gateway.
- **Stateless JWT authentication** with role-based access control distinguishing `ADMIN` and `CUSTOMER`.
- **Event-driven notifications** — Kafka decouples the Payment Service from Email/SMS/WhatsApp delivery.
- **Service discovery via Eureka** instead of hardcoded service URLs.
- **Cloud-native integrations** — AWS S3 for product images, Stripe for payments.
- **Infrastructure-as-Code and CI/CD groundwork** with Terraform and Jenkins.

---

## 🎯 Why I Built This Project

I built this project to gain hands-on experience designing and developing a production-oriented microservices application using the Spring ecosystem. Instead of creating a basic CRUD application, I wanted to understand how distributed systems are structured and how multiple services collaborate to deliver a complete business workflow.

The project allowed me to implement industry-relevant concepts such as stateless JWT authentication, API Gateway routing, service discovery with Eureka, synchronous communication using OpenFeign, asynchronous event-driven messaging with Apache Kafka, Redis caching, AWS S3 integration, Stripe payment processing, Docker-based containerization, and Infrastructure as Code with Terraform.

Throughout the development process, I focused on writing modular, maintainable, and scalable code while following microservices best practices such as service isolation, separation of concerns, and independent deployment. This project also helped me strengthen my understanding of backend architecture, cloud-native development, distributed communication, and DevOps fundamentals.

The primary goal of this repository is to showcase practical backend engineering skills and demonstrate how modern Java technologies can be combined to build a scalable e-commerce platform.

---

## ✨ Key Features

<details open>
<summary><strong>🔐 Authentication Service</strong></summary>

- User registration
- Login
- JWT authentication (generation + validation)
- Role-Based Access Control (RBAC)

</details>

<details open>
<summary><strong>📦 Product Service</strong></summary>

- CRUD operations
- Search
- Filtering
- Sorting
- Pagination
- AWS S3 image upload

</details>

<details open>
<summary><strong>🛒 Cart Service</strong></summary>

- Add item
- Remove item
- Update quantity
- Calculate total

</details>

<details open>
<summary><strong>📑 Order Service</strong></summary>

- Checkout
- Order creation
- Order status
- Order history

</details>

<details open>
<summary><strong>💳 Payment Service</strong></summary>

- Stripe payment integration
- Payment verification via webhooks
- Order status update

</details>

<details open>
<summary><strong>📨 Notification Service</strong></summary>

- Kafka consumer
- Email
- SMS
- WhatsApp

</details>

<details open>
<summary><strong>🏗️ Infrastructure</strong></summary>

- Docker
- Docker Compose
- Terraform
- Jenkins pipeline

</details>

---

## 🛠 Technology Stack

| Category | Technologies |
|---|---|
| **Backend** | Java 21, Spring Boot, Spring Security, Spring Data JPA, Spring Cloud, Maven |
| **Authentication** | JWT, Role-Based Access Control (RBAC) |
| **Microservices** | Spring Cloud Gateway, Eureka Server, OpenFeign |
| **Messaging** | Apache Kafka |
| **Database** | PostgreSQL |
| **Caching** | Redis |
| **Cloud** | AWS S3 |
| **Payments** | Stripe |
| **Notifications** | JavaMail, SMS, WhatsApp |
| **Infrastructure** | Docker, Docker Compose, Terraform, Jenkins |
| **Testing** | JUnit 5, Mockito |
| **Logging** | SLF4J |

---

## 📂 Folder Structure

```text
E-Commerce-Microservices/
│
├── api-gateway/             # API Gateway (Routing & Security)
├── auth-service/            # Authentication & JWT
├── product-service/         # Product Management
├── cart-service/            # Shopping Cart
├── order-service/           # Order Processing
├── payment-service/         # Stripe Payment Integration
├── notification-service/    # Kafka Consumer & Notifications
├── eureka-server/           # Service Discovery
│
├── docs/                    # Architecture diagrams & screenshots
├── terraform/               # Infrastructure as Code
├── jenkins/                 # Jenkins pipeline configuration
├── postman/                 # Postman Collection & Environment
│
├── docker-compose.yml       # Local development environment
├── README.md                # Project documentation
├── LICENSE                  # MIT License
└── .gitignore                # Git ignore rules
```

**Per-service layout (example: `auth-service`):**

```text
auth-service/
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   └── test/
├── pom.xml
└── Dockerfile
```

---

## 🧩 Microservices

| Service | Default Port | Responsibility |
|---|:---:|---|
| API Gateway | `8080` | Entry point for all client requests and request routing |
| Eureka Server | `8761` | Service discovery and registration |
| Authentication Service | `8081` | User registration, login, JWT generation and validation |
| Product Service | `8082` | Product management, search, filtering, pagination, AWS S3 integration |
| Cart Service | `8083` | Shopping cart management |
| Order Service | `8084` | Checkout, order creation and order management |
| Payment Service | `8085` | Stripe payment processing and payment verification |
| Notification Service | `8086` | Kafka consumer for Email, SMS and WhatsApp notifications |

---

## 🏛 Architecture Overview

The system follows a **microservices architecture** with:

- A **database-per-service** pattern for service autonomy.
- **Synchronous REST communication** (via OpenFeign) for request/response use cases.
- **Asynchronous event-driven communication** (via Kafka) for decoupled workflows like notifications.
- **Service discovery** through Eureka, avoiding hardcoded service URLs.
- A **single entry point** (API Gateway) for all client traffic.

### Deployment

The application is designed as a distributed microservices system. During development, all services are deployed locally using Docker Compose, with each microservice running in its own container.

The architecture has been designed to support future deployment to container orchestration platforms such as Kubernetes (Amazon EKS) with minimal architectural changes.

---

## 🗺 Architecture Diagram (Mermaid)

```mermaid
flowchart TB
    Client[Client]

    Client --> Gateway[API Gateway]

    Gateway --> Auth[Auth Service]
    Gateway --> Product[Product Service]
    Gateway --> Cart[Cart Service]
    Gateway --> Order[Order Service]
    Gateway --> Payment[Payment Service]

    Auth -. registers with .-> Eureka[Eureka Server]
    Product -. registers with .-> Eureka
    Cart -. registers with .-> Eureka
    Order -. registers with .-> Eureka
    Payment -. registers with .-> Eureka
    Notification[Notification Service] -. registers with .-> Eureka
    Gateway -. registers with .-> Eureka

    Order -- OpenFeign --> Cart
    Order -- OpenFeign --> Product

    Payment -- Kafka Event --> Notification

    Product --> S3[(AWS S3)]
    Payment --> Stripe[(Stripe)]

    Auth --> DB[(PostgreSQL)]
    Product --> DB
    Cart --> DB
    Order --> DB
    Payment --> DB

    Product --> Redis[(Redis Cache)]
```

The diagram illustrates the overall system architecture, showing how client requests flow through the API Gateway, how services communicate using OpenFeign and Kafka, and how external components such as PostgreSQL, Redis, AWS S3, Stripe, and Eureka Server integrate with the platform.

---

## 🔐 Authentication Flow

The application uses **stateless JWT (JSON Web Token) authentication** to secure APIs.

### Authentication Process

1. User registers or logs in
2. Authentication Service validates credentials
3. JWT token is generated on success (with role claim: `ADMIN` / `CUSTOMER`)
4. Client stores the JWT token
5. Token is sent in the `Authorization` header for every subsequent request
6. API Gateway / services validate the token
7. Access is granted or denied based on role

### Workflow

```text
Client
  ↓
Login Request
  ↓
Auth Service
  ↓
Validate Credentials
  ↓
Generate JWT
  ↓
Return Token
  ↓
Client Stores Token
  ↓
Protected API Request
  ↓
JWT Validation
  ↓
Response
```

### Authorization Header Example

```http
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

---

## 🔁 Authentication Sequence Diagram (Mermaid)

```mermaid
sequenceDiagram
    participant C as Client
    participant G as API Gateway
    participant A as Auth Service

    C->>G: POST /login (credentials)
    G->>A: Forward request
    A->>A: Validate credentials
    A->>A: Generate JWT (role claim)
    A-->>G: Return JWT
    G-->>C: Return JWT

    C->>G: GET /products (Authorization: Bearer JWT)
    G->>G: Validate JWT
    G->>A: (or downstream service) Verify role/claims
    G-->>C: Authorized response
```

### JWT Validation

- The API Gateway validates JWT access tokens for all protected endpoints.
- Requests with invalid or expired tokens are rejected before reaching downstream services.
- Authenticated user information is propagated to downstream microservices through forwarded headers/security context.
- Downstream services rely on the Gateway for primary authentication and enforce authorization for their own resources where applicable.

---

## 🔗 Service Communication

The platform uses a combination of **synchronous** and **asynchronous** communication patterns to ensure scalability, loose coupling, and efficient data exchange between services.

### Synchronous Communication (OpenFeign)

For operations that require an immediate response, services communicate using **OpenFeign**, enabling seamless REST-based service-to-service communication.

```text
Order Service
        │
        │ Fetch Product Details
        ▼
Product Service

Order Service
        │
        │ Retrieve Cart Information
        ▼
Cart Service

Payment Service
        │
        │ Update Order Status
        ▼
Order Service
```

**Use Cases**

- Retrieve product information during checkout.
- Fetch cart details before creating an order.
- Update order status after successful payment.

### Asynchronous Communication (Apache Kafka)

For background processing and event-driven workflows, the platform uses **Apache Kafka**. Instead of directly calling another service, an event is published to a Kafka topic, allowing other services to consume it independently.

```text
Payment Completed
        │
        ▼
Payment Service
        │
        │ Publish Event
        ▼
Apache Kafka
        │
        ▼
Notification Service
        │
        ├── Email
        ├── SMS
        └── WhatsApp
```

**Benefits**

- Loose coupling between services.
- Improved scalability.
- Better fault isolation.
- Non-blocking background processing.

### Service Discovery

All microservices register themselves with **Eureka Server**, allowing services to discover each other dynamically without relying on hardcoded URLs.

### API Gateway

The **API Gateway** acts as the single entry point for all client requests by:

- Routing requests to the appropriate microservice.
- Forwarding authenticated requests.
- Simplifying client interaction with the distributed system.
- Providing a unified access layer for all backend services.

---

## 📨 Kafka Messaging

Kafka is used to decouple the Payment Service from the Notification Service. On payment completion, an event is published; the Notification Service consumes it independently.

### Topics

| Topic | Producer | Consumer | Purpose |
|---|---|---|---|
| `payment-success` | Payment Service | Notification Service | Publishes successful payment events |
| `payment-failed` | Payment Service | Notification Service | Publishes failed payment events |

### Event Payload

```json
{
  "eventId": "8a9b3f1d-1234-4d9d-a123-987654321000",
  "orderId": "6f2c1d3a-5678-4b90-a456-123456789abc",
  "userId": "12ab34cd-56ef-7890-ab12-cd34ef567890",
  "email": "user@example.com",
  "phoneNumber": "+919876543210",
  "amount": 2499.00,
  "currency": "INR",
  "paymentStatus": "SUCCESS",
  "paymentMethod": "CARD",
  "transactionId": "pi_3Rxxxxxx",
  "timestamp": "2026-06-28T12:15:30Z"
}
```

### Kafka Configuration

| Property | Value |
|---|---|
| Bootstrap Server | `localhost:9092` |
| Topic | `payment-success` |
| Partitions | `1` |
| Replication Factor | `1` |
| Consumer Group | `notification-service-group` |
| Delivery Guarantee | At Least Once |

### Message Flow

```mermaid
flowchart LR
    Payment[Payment Service] -->|Publishes Event| Topic{{payment-success / payment-failed}}
    Topic -->|Consumed by| Notification[Notification Service]
    Notification --> Email[Email]
    Notification --> SMS[SMS]
    Notification --> WhatsApp[WhatsApp]
```

---

## 💳 Payment Flow

```text
Customer
    │
    ▼
API Gateway
    │
    ▼
Order Service
    │
    ├── Validate Order
    ├── Calculate Total Amount
    └── Create Pending Order
    │
    ▼
Payment Service
    │
    ├── Create Stripe Payment Intent
    ├── Process Payment
    ├── Update Payment Status
    └── Publish Payment Event (Kafka)
    │
    ▼
Kafka Topic (payment-success / payment-failed)
    │
    ▼
Notification Service
    ├── Send Email
    ├── Send SMS
    └── Send WhatsApp (optional)
    │
    ▼
Order Service
    └── Update Order Status (PAID / FAILED)
    │
    ▼
Response Returned to Customer
```

### Flow Description

1. The customer initiates the checkout process.
2. The request passes through the API Gateway.
3. The Order Service validates the cart, calculates the total amount, and creates a pending order.
4. The Payment Service creates a Stripe Payment Intent and processes the payment.
5. After the payment is completed, the Payment Service updates the payment status in the database.
6. The Payment Service publishes a payment event to Kafka (`payment-success` or `payment-failed`).
7. The Notification Service consumes the event and sends email, SMS, and optionally WhatsApp notifications.
8. The Order Service updates the order status based on the payment outcome.
9. A final response is returned to the customer.

### Payment Verification (Stripe Webhooks)

The Payment Service verifies the final payment status using **Stripe Webhooks** rather than synchronous polling.

1. The Payment Service creates a Payment Intent using the Stripe API.
2. The customer completes the payment through the Stripe Checkout/payment page.
3. Stripe sends a webhook event to the Payment Service.
4. The Payment Service verifies the webhook signature using the configured webhook secret.
5. Based on the event type (e.g. `payment_intent.succeeded` or `payment_intent.payment_failed`), the Payment Service:
   - Updates the payment status in the database.
   - Publishes a Kafka payment event.
   - Triggers downstream notification processing.

**Webhook Endpoint**

**Supported Stripe Events**

- `payment_intent.succeeded`
- `payment_intent.payment_failed`
- `payment_intent.canceled`

> All webhook requests are verified using Stripe's webhook signature before processing to ensure authenticity and prevent unauthorized requests.

### Key Components

| Component | Responsibility |
|---|---|
| **Order Service** | Creates and manages the order lifecycle. |
| **Payment Service** | Integrates with Stripe and processes payment transactions. |
| **Stripe** | Securely handles customer payment processing. |
| **Apache Kafka** | Publishes payment events for downstream consumers. |
| **Notification Service** | Sends Email, SMS, and WhatsApp notifications after successful payment. |

---

## ☁️ AWS S3 Upload Flow

```text
Customer
    │
    ▼
API Gateway
    │
    ▼
Product Service
    │
    ├── Validate Request
    ├── Validate File Type & Size
    ├── Generate Unique File Name
    ├── Upload File to AWS S3
    ├── Store S3 Object URL/Key in Database
    └── Return Product Details
    │
    ▼
AWS S3 Bucket
    │
    └── Stores Product Images
    │
    ▼
Database
    └── Stores Product Metadata + S3 Object URL/Key
```

### Flow Description

1. The client sends a product creation or image upload request through the API Gateway.
2. The Product Service validates the uploaded file (type, size, and required fields).
3. A unique object key (e.g. UUID-based) is generated to prevent filename collisions.
4. The Product Service uploads the image to the configured AWS S3 bucket.
5. After a successful upload, the S3 object key or URL is stored in the database along with the product information.
6. The Product Service returns the product details, including the image URL (or a pre-signed URL if private bucket access is used).

### Upload Validation

- Supported file formats: JPG, JPEG, PNG, WEBP
- Maximum file size: configurable (e.g., 5–10 MB)
- Duplicate filenames are avoided using unique object keys
- Invalid file types or oversized files are rejected before upload

### S3 Storage Configuration

**Bucket**

**Object Key Structure**

Example:

**Access Policy**

- S3 bucket is **private**.
- Objects are accessed using **pre-signed URLs** generated by the Product Service.
- Direct public access is disabled.
- IAM policies grant only the required permissions (`s3:GetObject`, `s3:PutObject`, `s3:DeleteObject`) to the application.

### Security

- AWS credentials are managed securely using IAM roles or environment variables.
- Uploaded files are validated before storage.
- Bucket access follows the principle of least privilege.
- Public access is disabled unless explicitly required.
- HTTPS is used for all communication with AWS S3.

---

## 🗄 Database & Caching

The platform uses **PostgreSQL** as the primary relational database and **Redis** as an in-memory caching layer to improve application performance and reduce database load.

### PostgreSQL

PostgreSQL is used as the persistent data store for business entities across the microservices.

**Stored Data**

- User accounts and roles
- Product catalog
- Shopping cart information
- Orders and order history
- Payment transactions

Each microservice manages its own data using **Spring Data JPA**, following the **database-per-service** design principle to maintain service independence and loose coupling.

### Redis Cache

Redis is used to cache frequently accessed data, reducing repeated database queries and improving response times.

**Cached Data**

- Frequently requested product information
- Product search results
- Frequently accessed catalog data

```text
Client Request
      │
      ▼
Product Service
      │
      ▼
Redis Cache
   │         │
Cache Hit   Cache Miss
   │         │
   ▼         ▼
 Return    PostgreSQL
 Response      │
               ▼
        Store in Redis
               │
               ▼
         Return Response
```

**Benefits**

- Faster API response times for read-heavy operations.
- Reduced load on the PostgreSQL database.
- Improved scalability for frequently accessed resources.
- Better overall application performance through in-memory caching.

---

## 🗄️ Database Design

Each microservice owns its own database (**database-per-service** pattern). No shared tables across services.

> ⚠️ **Note:** The schema below uses `UUID` primary keys and is modeled against PostgreSQL (per the project's tech stack). Adjust types if your actual implementation differs.

### 👤 Auth / User Data

**users**
- `id` (UUID, PK)
- `name` (VARCHAR)
- `email` (VARCHAR, UNIQUE)
- `password_hash` (VARCHAR)
- `role` (ENUM: USER, ADMIN)
- `created_at` (TIMESTAMP)
- `updated_at` (TIMESTAMP)

### 📦 Product Service

**products**
- `id` (UUID, PK)
- `name` (VARCHAR)
- `description` (TEXT)
- `price` (DECIMAL)
- `stock_quantity` (INT)
- `image_url` (TEXT)
- `created_at` (TIMESTAMP)
- `updated_at` (TIMESTAMP)

### 🛒 Cart Service

**cart_items**
- `id` (UUID, PK)
- `user_id` (UUID)
- `product_id` (UUID)
- `quantity` (INT)
- `created_at` (TIMESTAMP)

Relationships: One user → many cart items

### 📑 Order Service

**orders**
- `id` (UUID, PK)
- `user_id` (UUID)
- `total_amount` (DECIMAL)
- `status` (ENUM: PENDING, PAID, FAILED, SHIPPED)
- `created_at` (TIMESTAMP)

**order_items**
- `id` (UUID, PK)
- `order_id` (UUID, FK → orders.id)
- `product_id` (UUID)
- `quantity` (INT)
- `price` (DECIMAL)

Relationships: One order → many order_items

### 💳 Payment Service

**payments**
- `id` (UUID, PK)
- `order_id` (UUID)
- `payment_provider` (STRING: STRIPE)
- `status` (ENUM: SUCCESS, FAILED, PENDING)
- `transaction_id` (STRING)
- `amount` (DECIMAL)
- `created_at` (TIMESTAMP)

### 🔗 Entity Relationships (High Level)

### 📊 ER Diagram (Logical View)

### Design Principles

- Each microservice owns its database (no cross-service joins)
- Communication happens via APIs or Kafka events
- Data duplication is allowed for performance (event-driven sync)
- No foreign key constraints across services

---

## 🏗 Infrastructure

The project follows modern backend development practices by using containerization, Infrastructure as Code (IaC), and Continuous Integration/Continuous Deployment (CI/CD) to simplify development, deployment, and maintenance.

### Docker

Each microservice is containerized using **Docker**, ensuring a consistent runtime environment across development and deployment.

**Benefits**

- Isolated execution environment
- Consistent application behavior
- Simplified deployment process

### Docker Compose

**Docker Compose** is used to orchestrate the complete local development environment. It manages the API Gateway, all core services, PostgreSQL, Redis, and Apache Kafka — allowing the entire stack to be started with a single command.

```bash
docker-compose up --build
```

### Terraform

Infrastructure provisioning is managed using **Terraform**, allowing cloud resources to be defined as code. Terraform configurations are used to provision resources such as:

- AWS VPC
- Subnets
- Security Groups
- IAM Roles
- Amazon S3

### Jenkins CI/CD

A **Jenkins Pipeline** automates the software delivery process.

```text
GitHub
   │
   ▼
Checkout Source Code
   │
   ▼
Build Application
   │
   ▼
Run Unit Tests
   │
   ▼
Package Services
   │
   ▼
Docker Image Build
   │
   ▼
Deployment
```

### Infrastructure Components

| Component | Purpose |
|---|---|
| **Docker** | Containerizes each microservice |
| **Docker Compose** | Runs the complete application locally |
| **Terraform** | Provisions AWS infrastructure using Infrastructure as Code |
| **Jenkins** | Automates build, test, and deployment workflows |
| **AWS S3** | Stores product images |
| **PostgreSQL** | Primary relational database |
| **Redis** | In-memory caching layer |
| **Apache Kafka** | Event-driven messaging between services |

---

## 🚀 Getting Started

### Prerequisites

| Software | Version |
|---|---|
| Java | 21 |
| Maven | 3.9+ |
| Docker | Latest |
| Docker Compose | Latest |
| PostgreSQL | 16+ |
| Redis | Latest |
| Apache Kafka | Latest |
| Git | Latest |
| IntelliJ IDEA (Recommended) | Latest |

### Clone the Repository

```bash
git clone https://github.com/mved2003/E-Commerce-Microservices.git
cd E-Commerce-Microservices
```

### Configure the Application

Before starting the services, update the required configuration in each service's `application.properties` (or `application.yml`):

- PostgreSQL
- Redis
- Kafka
- JWT Secret
- AWS S3 Credentials
- Stripe API Keys
- SMTP Email Configuration
- SMS Provider Configuration
- WhatsApp Provider Configuration

### Build the Project

```bash
mvn clean install
```

---

## 🟢 Running the Application

The system can be started in two modes: containerized setup (recommended) or manual local development.

### Option 1: Docker Compose (Recommended)

```bash
docker-compose up --build
```

This starts:
- PostgreSQL
- Redis
- Kafka + Zookeeper
- All microservices
- API Gateway

### Option 2: Manual Startup (Development Mode)

Services must be started in the correct order due to dependencies.

**1. Infrastructure (mandatory first)**

```bash
docker-compose up postgres redis kafka zookeeper
```

**2. Service Registry (Eureka)**

```bash
cd eureka-server
mvn spring-boot:run
```

**3. API Gateway**

```bash
cd api-gateway
mvn spring-boot:run
```

**4. Core Services**

Start in any order after the registry is up:

```text
auth-service
product-service
cart-service
order-service
payment-service
notification-service
```

### ⚠️ Startup Dependency Rules

- Eureka must be running before all services register
- Kafka must be running before Order/Payment services start publishing events
- PostgreSQL must be available before any service initializes JPA
- API Gateway should be last in manual mode for clean routing validation

### Verify the Setup

| Service | URL |
|---|---|
| Eureka Dashboard | http://localhost:8761 |
| API Gateway | http://localhost:8080 |

If all services are registered in Eureka and the API Gateway is running successfully, the application is ready to use.

---

## 🔑 Environment Variables

All configuration is externalized using environment variables (no hardcoded secrets).

### Common Configuration (All Services)

| Variable | Description |
|---|---|
| `SERVER_PORT` | Service port |
| `SPRING_PROFILES_ACTIVE` | Active Spring profile |
| `DB_HOST` | Database host |
| `DB_PORT` | Database port |
| `DB_NAME` | Database name |
| `DB_USERNAME` | Database username |
| `DB_PASSWORD` | Database password |

### Security (JWT / Auth)

| Variable | Description |
|---|---|
| `JWT_SECRET` | Secret key for signing JWT tokens |
| `JWT_EXPIRATION` | Token expiration time (ms) |

### Messaging (Kafka)

| Variable | Description |
|---|---|
| `KAFKA_BOOTSTRAP_SERVERS` | Kafka broker URL |
| `KAFKA_GROUP_ID` | Consumer group ID |

### AWS (S3)

| Variable | Description |
|---|---|
| `AWS_ACCESS_KEY_ID` | IAM access key |
| `AWS_SECRET_ACCESS_KEY` | IAM secret key |
| `AWS_S3_BUCKET_NAME` | S3 bucket name |
| `AWS_REGION` | AWS region |

### Stripe Payment

| Variable | Description |
|---|---|
| `STRIPE_SECRET_KEY` | Stripe API secret key |
| `STRIPE_WEBHOOK_SECRET` | Webhook verification secret |

### Notification Services

| Variable | Description |
|---|---|
| `SMTP_HOST` | Email server host |
| `SMTP_PORT` | Email server port |
| `SMTP_USERNAME` | Email username |
| `SMTP_PASSWORD` | Email password |
| `TWILIO_ACCOUNT_SID` | SMS/WhatsApp provider SID |
| `TWILIO_AUTH_TOKEN` | SMS/WhatsApp auth token |

### Security Rules

- All secrets are injected via environment variables only
- No credentials are stored in code or the Git repository
- `.env` file is used only for local development

---

## 📘 API Documentation

The platform exposes RESTful APIs through the **API Gateway**, which acts as the single entry point for all client requests.

All protected endpoints require a valid **JWT Bearer Token** obtained after successful authentication.

**Base URL**

```text
http://localhost:8080
```

All endpoints follow the convention:

### 🛡️ Auth Service

| Method | Endpoint | Description | Auth |
|---|---|---|---|
| POST | `/api/v1/auth/register` | Register new user | ❌ |
| POST | `/api/v1/auth/login` | Authenticate user and generate JWT | ❌ |
| POST | `/api/v1/auth/refresh` | Refresh JWT token | ❌ |
| POST | `/api/v1/auth/logout` | Logout user | ✅ |

### 👤 User Service

| Method | Endpoint | Description | Auth |
|---|---|---|---|
| GET | `/api/v1/users/profile` | Get user profile | ✅ |
| PUT | `/api/v1/users/profile` | Update user profile | ✅ |

### 📦 Product Service

| Method | Endpoint | Description | Auth |
|---|---|---|---|
| GET | `/api/v1/products` | List all products | ❌ |
| GET | `/api/v1/products/{id}` | Get product by ID | ❌ |
| POST | `/api/v1/products` | Create product | ADMIN |
| PUT | `/api/v1/products/{id}` | Update product | ADMIN |
| DELETE | `/api/v1/products/{id}` | Delete product | ADMIN |

### 🛒 Cart Service

| Method | Endpoint | Description | Auth |
|---|---|---|---|
| GET | `/api/v1/cart` | Get user cart | ✅ |
| POST | `/api/v1/cart/items` | Add item to cart | ✅ |
| PUT | `/api/v1/cart/items/{id}` | Update item quantity | ✅ |
| DELETE | `/api/v1/cart/items/{id}` | Remove item | ✅ |

### 📑 Order Service

| Method | Endpoint | Description | Auth |
|---|---|---|---|
| POST | `/api/v1/orders/checkout` | Place order | ✅ |
| GET | `/api/v1/orders` | Get user orders | ✅ |
| GET | `/api/v1/orders/{id}` | Get order details | ✅ |

### 💳 Payment Service

| Method | Endpoint | Description | Auth |
|---|---|---|---|
| POST | `/api/v1/payments/create` | Create payment | ✅ |
| POST | `/api/v1/payments/webhook` | Stripe webhook | ❌ |
| GET | `/api/v1/payments/{id}` | Payment status | ✅ |

### 📢 Notification Service

| Method | Endpoint | Description | Auth |
|---|---|---|---|
| POST | `/api/v1/notifications/email` | Send email | INTERNAL |
| POST | `/api/v1/notifications/sms` | Send SMS | INTERNAL |

> The Notification Service operates primarily as a **Kafka consumer**; the endpoints above are internal-only and not intended for direct customer interaction.

### Authentication Header

```http
Authorization: Bearer <your-jwt-token>
```

### API Testing

The APIs can be tested using:
- Postman
- IntelliJ HTTP Client
- cURL

A Postman collection is available in the `postman/` directory.

---

## 🔍 Example Requests (cURL)

### 🔐 Register User

```bash
curl -X POST http://localhost:8080/api/v1/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Ved Mishra",
    "email": "ved@example.com",
    "password": "securePassword123"
  }'
```

### 🔑 Login User

```bash
curl -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "ved@example.com",
    "password": "securePassword123"
  }'
```

### 🛒 Add Item to Cart

```bash
curl -X POST http://localhost:8080/api/v1/cart/items \
  -H "Authorization: Bearer <JWT_TOKEN>" \
  -H "Content-Type: application/json" \
  -d '{
    "productId": "9d4d5c18-3d91-4b6a-94d2-5b7a7d91f2b8",
    "quantity": 2
  }'
```

### 📦 Checkout Order

```bash
curl -X POST http://localhost:8080/api/v1/orders/checkout \
  -H "Authorization: Bearer <JWT_TOKEN>" \
  -H "Content-Type: application/json"
```

### 💳 Stripe Webhook (Example Payload)

```bash
curl -X POST http://localhost:8080/api/v1/payments/webhook \
  -H "Content-Type: application/json" \
  -d '{
    "type": "payment_intent.succeeded",
    "data": {
      "object": {
        "id": "pi_123456789"
      }
    }
  }'
```

---

## 📤 Example Responses

### 🔐 Register User

**Response (201 Created)**

```json
{
  "success": true,
  "message": "User registered successfully",
  "data": {
    "userId": "a12b34cd-5678-90ef-gh12-345678ijkl90",
    "name": "Ved Mishra",
    "email": "ved@example.com",
    "role": "USER",
    "createdAt": "2026-06-29T10:15:30Z"
  }
}
```

### 🔑 Login User

**Response (200 OK)**

```json
{
  "success": true,
  "message": "Login successful",
  "data": {
    "accessToken": "eyJhbGciOiJIUzI1NiIs...",
    "refreshToken": "dGhpcyBpcyBhIHJlZnJlc2g=",
    "expiresIn": 3600
  }
}
```

### 🛒 Get Cart

**Response (200 OK)**

```json
{
  "success": true,
  "data": {
    "userId": "a12b34cd-5678-90ef-gh12-345678ijkl90",
    "items": [
      {
        "productId": "p1",
        "name": "Wireless Mouse",
        "quantity": 2,
        "price": 499
      }
    ],
    "totalAmount": 998
  }
}
```

### 📦 Order Checkout

**Response (200 OK)**

```json
{
  "success": true,
  "message": "Order placed successfully",
  "data": {
    "orderId": "o123456",
    "status": "PENDING_PAYMENT",
    "totalAmount": 1499,
    "createdAt": "2026-06-29T10:20:00Z"
  }
}
```

---

## ⚠️ Error Responses

All services use a unified error response structure.

### Standard Error Schema

```json
{
  "success": false,
  "status": 400,
  "errorCode": "VALIDATION_ERROR",
  "message": "Request validation failed",
  "errors": [
    "Email must not be empty",
    "Password must be at least 8 characters"
  ],
  "timestamp": "2026-06-29T10:30:00Z",
  "path": "/api/v1/auth/register"
}
```

### HTTP Status Codes Used

| Status Code | Meaning |
|---|---|
| 400 | Bad Request (validation failure) |
| 401 | Unauthorized (invalid/expired JWT) |
| 403 | Forbidden (insufficient permissions) |
| 404 | Resource not found |
| 409 | Conflict (duplicate email, etc.) |
| 500 | Internal server error |

### Common Error Codes

| Error Code | Description |
|---|---|
| `VALIDATION_ERROR` | Input validation failed |
| `AUTH_FAILED` | Authentication failure |
| `ACCESS_DENIED` | Authorization failure |
| `RESOURCE_NOT_FOUND` | Entity does not exist |
| `PAYMENT_FAILED` | Payment processing error |
| `INTERNAL_ERROR` | Unexpected server error |

### Design Rules

- Every error response follows the same schema across all microservices
- No raw stack traces are exposed to clients
- All errors include a timestamp and request path for debugging

---

## 🔐 Security Implementation

The system uses a layered security model across all microservices.

### 🔑 Authentication & Password Security

- Passwords are hashed using **BCrypt** (adaptive hashing with salt — Spring Security default)
- No plaintext passwords are ever stored or logged

```java
PasswordEncoder encoder = new BCryptPasswordEncoder();
```

### 🪪 Authentication Mechanism

- JWT-based authentication (stateless)
- Tokens signed using `HS256`
- Token contains: `userId`, `email`, `role` (USER / ADMIN)

### 🛡️ Authorization (RBAC)

- Role-Based Access Control enforced via Spring Security
- Method-level security enabled:

```java
@PreAuthorize("hasRole('ADMIN')")
```

- Gateway validates JWT before routing requests
- Services optionally re-validate token for defense-in-depth

### 🌐 CORS Policy

Configured at the API Gateway level.

- **Allowed origins:** `http://localhost:3000` (frontend dev), production frontend domain (to be configured)
- **Allowed methods:** GET, POST, PUT, DELETE, OPTIONS
- **Allowed headers:** Authorization, Content-Type

### 🚦 Rate Limiting

Implemented at the API Gateway using a token bucket strategy:

- 100 requests / minute per IP (default)
- Stricter limits for auth endpoints (login/register)

Protection covers brute-force login attempts, API abuse, and traffic spikes.

### 🔥 Security Headers

- `Authorization` header required for protected routes
- HTTPS enforced in production
- Stateless session management (no server-side sessions)

### 🧱 Defense-in-Depth Model

| Layer | Responsibility |
|---|---|
| Gateway | JWT validation, rate limiting, CORS |
| Services | RBAC enforcement |
| DB | Hashed passwords only |
| Transport | HTTPS encryption |

---

## 📜 Logging

The system uses centralized structured logging across all microservices, implemented using **SLF4J**.

### 🧾 Log Format (Standardized JSON)

```json
{
  "timestamp": "2026-06-29T10:45:00Z",
  "level": "INFO",
  "service": "order-service",
  "traceId": "c1a2b3d4",
  "spanId": "e5f6g7h8",
  "userId": "a12b34cd",
  "message": "Order created successfully",
  "endpoint": "/api/v1/orders/checkout"
}
```

### 📊 Log Levels

| Level | Usage |
|---|---|
| ERROR | Failures, exceptions, payment failures |
| WARN | Suspicious or recoverable issues |
| INFO | Normal business events (order placed, login success) |
| DEBUG | Detailed debugging (disabled in production) |

### 🔗 Correlation Strategy

- Each request is assigned a **traceId**
- Propagated through the API Gateway, Kafka events, and downstream services
- Enables full request tracing across microservices

### ☁️ Centralized Logging (Recommended Setup)

Logs shipped to **ELK Stack** (Elasticsearch + Logstash + Kibana) or **Grafana Loki**, enabling search by traceId, filtering by service/userId, and real-time monitoring dashboards.

### 🧱 Logging Rules

- No `System.out.println` in any service
- No logging of sensitive data (passwords, tokens, Stripe keys)
- All logs must include service name + traceId
- Error logs must include stack trace (internal only)

---

## 🧪 Testing

The project includes unit tests to verify the correctness of the business logic across individual microservices.

### Testing Frameworks

| Framework | Purpose |
|---|---|
| **JUnit 5** | Unit testing framework |
| **Mockito** | Mocking dependencies and isolating business logic |

### Test Coverage

The test suite focuses on validating:

- Service layer business logic
- Authentication and authorization logic
- Product management operations
- Cart operations
- Order processing
- Payment service logic
- Exception handling
- Repository interactions using mocked dependencies

### Testing Approach

- Unit tests
- Mock-based testing using Mockito
- Service layer validation
- Business logic verification

Dependencies such as repositories and external services are mocked to ensure tests are isolated, repeatable, and fast.

### Running Tests

```bash
mvn test
```

To build the project along with executing tests:

```bash
mvn clean install
```

### Example

```java
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService service;

    @Test
    void shouldReturnProductById() {
        // Test implementation
    }
}
```

---

## 🔄 CI/CD Pipeline

The project uses **Jenkins** to automate the build and deployment workflow, ensuring code changes are continuously validated before deployment.

### Pipeline Workflow

```text
Developer
    │
    ▼
Push Code to GitHub
    │
    ▼
Jenkins Pipeline
    │
    ├── Checkout Source Code
    ├── Build Project (Maven)
    ├── Run Unit Tests
    ├── Package JAR Files
    ├── Build Docker Images
    └── Deploy Application
```

### Pipeline Stages

| Stage | Description |
|---|---|
| **Checkout** | Retrieves the latest source code from the GitHub repository. |
| **Build** | Compiles all microservices using Maven. |
| **Test** | Executes unit tests using JUnit 5 and Mockito. |
| **Package** | Packages each microservice into an executable JAR. |
| **Docker Build** | Builds Docker images for each microservice. |
| **Deployment** | Deploys the application to the target environment. |

### Technologies Used

- Jenkins
- Maven
- Docker
- GitHub

### Running the Pipeline

After pushing code to the GitHub repository, Jenkins automatically executes the configured pipeline. The pipeline can also be triggered manually from the Jenkins dashboard when required.

---

## 🗺️ Roadmap / Future Improvements

### Phase 1: Core Backend (Completed / In Progress)
- Authentication (JWT + RBAC)
- User Service
- Product Service
- Cart Service
- Order Service
- Payment Integration (Stripe)
- Basic Notification Service

### Phase 2: Event-Driven Enhancements
- Kafka-based async processing
- Order → Payment → Notification event flow
- Retry mechanisms for failed events
- Dead-letter queue handling

### Phase 3: Infrastructure & Scalability
- Dockerization of all services
- Docker Compose orchestration
- Centralized logging (ELK / Loki)
- Monitoring (Prometheus + Grafana)

### Phase 4: Production Hardening
- Rate limiting at gateway
- Circuit breakers (Resilience4j)
- API versioning
- Load balancing support

### Phase 5: Optional Enhancements
- [ ] Responsive frontend using Angular and TypeScript
- [ ] Saga Pattern for distributed transaction management
- [ ] Refresh Token support for JWT authentication
- [ ] Centralized configuration using Spring Cloud Config
- [ ] Distributed tracing with Zipkin
- [ ] Container orchestration with Kubernetes (EKS)
- [ ] Increase unit and integration test coverage
- [ ] API documentation using OpenAPI (Swagger)
- [ ] Role-based audit logging
- [ ] Inventory reservation and stock management
- [ ] Support for multiple payment gateways (e.g., Razorpay, PayPal)
- [ ] Admin dashboard
- [ ] Recommendation system
- [ ] Deploy the platform to AWS for public access

---

## 📮 Postman Collection

A Postman collection is provided to simplify API testing and demonstrate the complete application workflow.

### Location

```text
postman/
└── E-Commerce-Microservices.postman_collection.json
```

### Importing the Collection

1. Open **Postman**.
2. Click **Import**.
3. Select `E-Commerce-Microservices.postman_collection.json`.
4. Configure the required environment variables (e.g., API Gateway URL and JWT token).
5. Execute the requests in the recommended order.

### Recommended API Flow

1. Register a new user *(optional)*
2. Login to obtain a JWT token
3. Retrieve products
4. Add a product to the cart
5. Checkout the order
6. Complete the Stripe payment
7. Verify Email, SMS, and WhatsApp notifications

### Environment Variables

| Variable | Description |
|---|---|
| `baseUrl` | API Gateway URL (e.g., `http://localhost:8080`) |
| `jwtToken` | JWT token returned after login |

### Collection Features

- Authentication APIs
- Product APIs
- Cart APIs
- Order APIs
- Payment APIs
- Pre-configured request bodies
- JWT authentication support
- Ready for local testing

---

## 🤝 Contributing

Contributions are welcome! If you'd like to improve the project, please follow these steps:

1. Fork the repository.
2. Create a new feature branch.

```bash
   git checkout -b feature/your-feature-name
```

3. Commit your changes.

```bash
   git commit -m "Add: your feature description"
```

4. Push the branch to your fork.

```bash
   git push origin feature/your-feature-name
```

5. Open a Pull Request describing your changes.

### Contribution Guidelines

- Follow the existing project structure and coding conventions.
- Write clean, readable, and maintainable code.
- Include unit tests for new functionality where applicable.
- Update the documentation if your changes affect project behavior.
- Ensure the project builds successfully before submitting a Pull Request.

Thank you for helping improve the project!

---

## 📄 License

This project is licensed under the **MIT License**.

---

<div align="center">

## 👤 Author

**Ved Mishra**

Java Backend Developer • Spring Boot • Microservices • PostgreSQL • Kafka • Redis • AWS • Docker

📧 Email: mved82986@gmail.com

💼 LinkedIn: https://www.linkedin.com/in/ved-mishra-java/

💻 GitHub: https://github.com/mved2003

---

⭐ **If you found this project helpful, consider giving it a star!**

</div>
