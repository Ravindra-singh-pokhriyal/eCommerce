<h1 align="center"> SpringVault Shop </h1>
<p align="center"> The Secure, Modern, and Scalable E-commerce Platform Built on Java 21 and Spring Boot. </p>

<p align="center">
  <img alt="Build" src="https://img.shields.io/badge/Build-Passing-brightgreen?style=for-the-badge&logo=apache-maven">
  <img alt="Language" src="https://img.shields.io/badge/Language-Java%2021-red?style=for-the-badge&logo=openjdk">
  <img alt="Framework" src="https://img.shields.io/badge/Framework-Spring%20Boot%204.0.1-green?style=for-the-badge&logo=spring">
  <img alt="Database" src="https://img.shields.io/badge/Database-MySQL%20JPA-blue?style=for-the-badge&logo=mysql">
  <img alt="Code Style" src="https://img.shields.io/badge/Code%20Style-Lombok%20Enabled-orange?style=for-the-badge">
</p>
<!-- 
  **Note:** These are static placeholder badges. Replace them with your project's actual badges, CI/CD status, and version numbers.
  You can generate your own at https://shields.io
-->

---

## 📖 Table of Contents

- [⭐ Overview](#-overview)
- [✨ Key Features](#-key-features)
- [🛠️ Tech Stack & Architecture](#-tech-stack--architecture)
- [📁 Project Structure](#-project-structure)
- [📸 Demo & Screenshots](#-demo--screenshots)
- [🚀 Getting Started](#-getting-started)
- [🔧 Usage](#-usage)
- [🤝 Contributing](#-contributing)
- [📝 License](#-license)

---

## ⭐ Overview

SpringVault Shop is a robust, full-featured web application designed to demonstrate a complete, modern e-commerce lifecycle using the rigorous architecture of the Java ecosystem. It serves as a comprehensive reference implementation for building secure, scalable, and data-driven online stores.

> Creating comprehensive, production-ready e-commerce solutions is a complex task, requiring careful handling of authentication, transactional integrity, state management (shopping carts), and detailed inventory and order workflows. Businesses need a solid, high-performance foundation that reliably handles complex business logic and data persistence without sacrificing development velocity or security.

SpringVault Shop eliminates the need to start from scratch by providing a meticulously structured, production-ready reference architecture built on Spring Boot 4.0.1 and the latest Java 21 standard. This foundation ensures a secure, scalable, and highly maintainable application suitable for high-traffic environments. It delivers comprehensive modules for secure user registration, detailed product catalog management, functional shopping cart persistence, and detailed administrative order tracking.

The system utilizes a clean, layered architecture based on the Model-View-Controller (MVC) pattern. Spring Boot handles the core configuration and request routing, while Spring Data JPA manages seamless persistence to the MySQL database. Server-side rendering is efficiently handled by Thymeleaf, providing dynamic and secure view generation tailored for both end-users and administrators. This architecture ensures a clear separation of concerns, resulting in code that is easier to test, debug, and scale.

---

## ✨ Key Features

SpringVault Shop is engineered to deliver a comprehensive digital retail experience, broken down into functional, user-centric modules:

### 🛍️ Core E-commerce Functionality

*   **Dynamic Product Catalog Browsing:** Users can effortlessly browse products categorized by defined types (`Category.java`). Products feature detailed information, including images (`product_img/`), ensuring an engaging shopping experience (`view_product.html`).
*   **Intuitive Shopping Cart Management:** Provides a persistent and interactive shopping cart system (`Cart.java`, `CartService.java`). Users can add, remove, and adjust quantities of products seamlessly before proceeding to checkout (`cart.html`).
*   **Secure Order Placement:** Facilitates a robust checkout process, capturing necessary delivery details (`OrderAddress.java`) and finalizing the order, generating detailed records for tracking (`ProductOrder.java`).
*   **Personalized Order Tracking:** Authenticated users can view a history of their purchases, including current status and details of past orders (`my_orders.html`).

### 🔑 Security & User Management

*   **Custom Authentication Flow:** Implements a highly customized and secure user authentication system (`SecurityConfig.java`, `UserDetailsServiceImpl.java`) supporting both standard user and administrative roles.
*   **Authentication Handlers:** Uses dedicated handlers (`AuthSuccessHandlerImpl.java`, `AuthFailureHandlerImpl.java`) to provide role-specific redirection upon login, enhancing user experience and security governance.
*   **User Profiles and Account Control:** Allows users to register (`register.html`), log in (`login.html`), and manage forgotten or reset passwords (`forgot_password.html`, `reset_password.html`).

### ⚙️ Administration & Inventory

*   **Comprehensive Admin Dashboard:** A dedicated backend portal (`admin/index.html`) gives administrators complete control over the system's core components.
*   **Inventory and Product Control:** Administrators can add new products (`add_product.html`), edit existing inventory details (`edit_product.html`), and manage product images.
*   **Category Management:** Enables the creation, modification, and deletion of product categories (`category.html`, `edit_category.html`), ensuring the catalog remains organized and scalable.
*   **User and Order Oversight:** Provides full visibility and control over all registered users (`users.html`) and the processing status of customer orders (`orders.html`).

---

## 🛠️ Tech Stack & Architecture

This project is built using industry-leading, high-performance technologies, adhering to modern software development standards.

| Technology | Version | Purpose | Why it was Chosen |
| :--- | :--- | :--- | :--- |
| **Java** | 21 | Primary Programming Language | Chosen for its robust performance, enterprise scalability, and long-term support (LTS) status, providing a reliable foundation. |
| **Spring Boot** | 4.0.1 | Application Framework | Provides rapid application development capabilities, dependency injection, and automatic configuration, significantly simplifying the setup of a complex web application. |
| **Spring Data JPA** | Starter | Data Persistence Layer | Abstracts the complexities of database operations, allowing for quick repository creation and efficient mapping between Java models and the relational database schema. |
| **Spring WebMVC** | Starter | Web Layer/Controller Management | Implements the core Model-View-Controller pattern, ensuring clean separation of business logic, request handling, and view rendering. |
| **Thymeleaf** | Starter | Server-Side Templating | Enables dynamic and secure rendering of HTML views (`index.html`, `base.html`, etc.) directly from the server, perfectly integrated with Spring MVC. |
| **MySQL Connector/J** | Runtime | Database Connectivity | The necessary JDBC driver to facilitate reliable, high-performance connection and transactional management with the required MySQL database instance. |
| **Lombok** | 1.18.42 | Code Generation Utility | Drastically reduces boilerplate code (getters, setters, constructors, logging) across all data models (`Product.java`, `Cart.java`), enhancing code clarity and maintainability. |
| **Maven** | N/A | Build Automation Tool | Standardizes the project lifecycle, dependency management (`pom.xml`), and build process, ensuring consistent execution and distribution across development environments. |

---

## 📁 Project Structure

The SpringVault Shop codebase is meticulously organized following standard Maven and Spring Boot conventions, promoting high maintainability and clarity.

```
Ravindra-singh-pokhriyal-eCommerce-d88d0ee/
├── 📄 .gitattributes             # Git configuration for path attributes
├── 📄 .gitignore                 # Files and directories ignored by Git
├── 📄 pom.xml                    # Maven Project Object Model (dependencies, build configuration)
├── 📄 mvnw                       # Maven wrapper script (Linux/macOS)
├── 📄 mvnw.cmd                   # Maven wrapper script (Windows)
├── 📂 .mvn/                      # Maven configuration files
│   └── 📂 wrapper/               # Maven wrapper properties
│       └── 📄 maven-wrapper.properties
└── 📂 src/                       # Source code directory
    ├── 📂 main/                  # Main application source code
    │   ├── 📂 java/              # Java source code base
    │   │   └── 📂 com/
    │   │       └── 📂 ravi/
    │   │           └── 📂 e_commerce/
    │   │               ├── 📄 ECommerceApplication.java     # Main Spring Boot entry point
    │   │               ├── 📂 config/                       # Security and application configuration
    │   │               │   ├── 📄 SecurityConfig.java
    │   │               │   ├── 📄 CustomUser.java
    │   │               │   ├── 📄 UserDetailsServiceImpl.java
    │   │               │   ├── 📄 AuthSuccessHandlerImpl.java
    │   │               │   └── 📄 AuthFailureHandlerImpl.java
    │   │               ├── 📂 controller/                   # Request handling layer (MVC)
    │   │               │   ├── 📄 AdminController.java      # Handles requests for the administrative backend
    │   │               │   ├── 📄 UserController.java       # Handles user-specific actions (profile, cart, orders)
    │   │               │   └── 📄 Homecontroller.java       # Handles public and index routes
    │   │               ├── 📂 model/                        # JPA Entities and Data Transfer Objects (DTOs)
    │   │               │   ├── 📄 Product.java              # Entity for product details
    │   │               │   ├── 📄 Category.java             # Entity for product categories
    │   │               │   ├── 📄 UserDtls.java             # Entity for user details
    │   │               │   ├── 📄 Cart.java                 # Entity for shopping cart items
    │   │               │   ├── 📄 ProductOrder.java         # Entity for individual items within an order
    │   │               │   ├── 📄 OrderRequest.java         # Data model for processing new orders
    │   │               │   └── 📄 OrderAddress.java         # Data model for shipping/billing address
    │   │               ├── 📂 repository/                   # Spring Data JPA repositories
    │   │               │   ├── 📄 ProductRepository.java
    │   │               │   ├── 📄 CartRepository.java
    │   │               │   ├── 📄 CategoryRepository.java
    │   │               │   ├── 📄 UserRepository.java
    │   │               │   └── 📄 ProductOrderRepository.java
    │   │               ├── 📂 service/                      # Business logic layer
    │   │               │   ├── 📄 CommonService.java
    │   │               │   ├── 📄 UserService.java
    │   │               │   ├── 📄 ProductService.java
    │   │               │   ├── 📄 CategoryService.java
    │   │               │   ├── 📄 CartService.java
    │   │               │   ├── 📄 OrderService.java
    │   │               │   ├── 📄 CommonServiceImpl.java
    │   │               │   └── 📂 impl/                     # Service implementations
    │   │               │       ├── 📄 UserServiceImpl.java
    │   │               │       ├── 📄 ProductServiceImpl.java
    │   │               │       ├── 📄 OrderServiceImpl.java
    │   │               │       └── 📄 CartServiceImpl.java
    │   │               ├── 📂 util/                         # Utility classes and constants
    │   │               │   ├── 📄 CommonUtil.java
    │   │               │   ├── 📄 AppConstant.java
    │   │               │   └── 📄 OrderStatus.java          # Enumeration for order lifecycle stages
    │   │               └── 📂 service/
    │   │                   └── 📂 impl/
    │   │                       └── 📄 CategoryServiceImpl.java
    │   └── 📂 resources/             # Static assets and configuration
    │       ├── 📄 application.properties  # Main Spring Boot configuration (DB connections, port)
    │       ├── 📂 static/               # Client-side static resources
    │       │   ├── 📂 css/              # Stylesheets
    │       │   │   └── 📄 style.css
    │       │   ├── 📂 js/               # JavaScript files
    │       │   │   └── 📄 script.js
    │       │   └── 📂 images/           # Application-wide image assets
    │       │       ├── 📄 ecom1.png
    │       │       ├── 📄 carousel1.jpg
    │       │       └── 📂 category_img/ # Category specific imagery
    │       │           └── 📄 laptop.jpg
    │       │           └── 📄 mobile.png
    │       │       └── 📂 product_img/  # Inventory imagery
    │       │           └── 📄 Samsung galaxy-a32-5g.webp
    │       │       └── 📂 login/        # Login page specific images
    │       │       └── 📂 profile_img/  # User profile default images
    │       └── 📂 templates/            # Thymeleaf HTML views
    │           ├── 📄 index.html          # Homepage
    │           ├── 📄 base.html           # Layout fragment for shared elements
    │           ├── 📄 login.html          # User login page
    │           ├── 📄 register.html       # New user registration page
    │           ├── 📄 product.html        # Product listing page
    │           ├── 📄 view_product.html   # Detailed product view
    │           ├── 📄 message.html
    │           ├── 📄 forgot_password.html
    │           ├── 📄 reset_password.html
    │           ├── 📂 admin/              # Administrator-specific views
    │           │   ├── 📄 index.html
    │           │   ├── 📄 products.html
    │           │   ├── 📄 orders.html
    │           │   ├── 📄 users.html
    │           │   ├── 📄 category.html
    │           │   ├── 📄 add_product.html
    │           │   ├── 📄 edit_product.html
    │           │   └── 📄 edit_category.html
    │           └── 📂 user/               # Authenticated user views
    │               ├── 📄 home.html
    │               ├── 📄 cart.html       # Shopping cart view
    │               ├── 📄 order.html
    │               ├── 📄 my_orders.html  # Order history view
    │               └── 📄 success.html    # Order confirmation/success page
    └── 📂 test/                      # Unit and integration tests
        └── 📂 java/
            └── 📂 com/
                └── 📂 ravi/
                    └── 📂 e_commerce/
                        └── 📄 ECommerceApplicationTests.java # Application context loading tests
---


## 🚀 Getting Started

To set up and run the SpringVault Shop application locally, ensure you meet the prerequisite requirements and follow the defined installation steps.

### Prerequisites

You must have the following software installed on your development machine:

1.  **Java Development Kit (JDK) 21 or higher:** This project is built using Java 21 features and requires a compatible runtime environment.
2.  **Apache Maven:** Used as the build automation and dependency management tool, as defined in `pom.xml`.
3.  **MySQL Server:** A running instance of MySQL is required for data persistence, managed via Spring Data JPA.

### Database Setup

The application connects to a MySQL database using the `mysql-connector-j` dependency.

1.  **Create Database Schema:** Log into your MySQL instance and create a new schema (e.g., `ecommercedb`).

    ```sql
    CREATE DATABASE ecommercedb;
    ```

2.  **Configure Connection:** Open the main configuration file located at `src/main/resources/application.properties`. Update the following properties to match your MySQL database credentials, replacing placeholders for hostname, port, database name, username, and password:

    ```properties
    # Example database configuration in application.properties
    spring.datasource.url=jdbc:mysql://localhost:3306/ecommercedb
    spring.datasource.username=your_db_username
    spring.datasource.password=your_db_password
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

    # Ensure JPA creates/updates tables automatically on startup
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    ```
    *Note: The application relies on `spring.jpa.hibernate.ddl-auto=update` to automatically create tables based on the defined Model entities (`Product.java`, `UserDtls.java`, etc.).*

### Installation and Build

Follow these steps to clone the repository and build the executable application using Maven:

1.  **Clone the Repository:**

    ```bash
    git clone [Repository URL Here] Ravindra-singh-pokhriyal-eCommerce-d88d0ee
    cd Ravindra-singh-pokhriyal-eCommerce-d88d0ee
    ```

2.  **Clean and Build Project:** Use the Maven Wrapper (`mvnw` or `mvnw.cmd`) to download dependencies and compile the Java code.

    ```bash
    # For Linux/macOS
    ./mvnw clean install

    # For Windows
    .\mvnw.cmd clean install
    ```
    This command will download all required dependencies listed in `pom.xml` (including Spring Boot starters, Thymeleaf, and Lombok) and package the application into a JAR file.

3.  **Run the Application:** Execute the compiled Spring Boot application using the `spring-boot:run` goal.

    ```bash
    ./mvnw spring-boot:run
    ```

The application will start, typically running on `http://localhost:8080`, unless specified otherwise in `application.properties`. Check the console output for confirmation that `ECommerceApplication.java` has successfully initialized the Spring context.

---

## 🔧 Usage

Once the SpringVault Shop is running, it operates as a full-featured web application accessible via any standard web browser.

### Accessing the Web Application

The application is hosted locally at:

```
http://localhost:8080
```

### 1. User E-commerce Flow

This flow mirrors a standard online shopping experience, utilizing the `UserController.java` and public templates.

| Step | Action | Endpoint/Template | Functionality Description |
| :--- | :--- | :--- | :--- |
| **Registration** | Create a new user account. | `/register` (via `register.html`) | Establishes a new `UserDtls` entry in the database. |
| **Authentication** | Log into the system. | `/login` (via `login.html`) | Authenticates against `UserDetailsServiceImpl` and redirects based on user role (User/Admin). |
| **Browsing** | View products and categories. | `/` or `/product` (via `index.html`, `product.html`) | Displays the catalog, leveraging `ProductService` and `CategoryService`. |
| **Shopping Cart** | Add items and review purchases. | `/cart` (via `cart.html`) | Manages cart state via `CartService` and `Cart.java` models. |
| **Checkout** | Finalize the order transaction. | `/order` (via `order.html`) | Captures shipping details (`OrderAddress.java`) and saves the transactional order data. |
| **History** | View past orders. | `/user/my_orders` (via `my_orders.html`) | Displays order history managed by `OrderService`. |

### 2. Administrator Management Flow

Access to the administrative dashboard is secured and role-gated, accessible via the `AdminController.java`.

1.  **Admin Login:** Log in using credentials associated with the administrative role. The system will redirect to the admin dashboard (`admin/index.html`).
2.  **Product Management:** Navigate to the products section to perform CRUD (Create, Read, Update, Delete) operations. This allows the administrator to maintain inventory using the forms provided by `add_product.html` and `edit_product.html`.
3.  **Category Management:** Update or add new categories to organize the product catalog via `admin/category.html`.
4.  **Order Fulfillment:** View all customer orders on the `admin/orders.html` page, update the `OrderStatus` for fulfillment, shipping, and delivery.
5.  **User Oversight:** Monitor and manage all registered user accounts via the `admin/users.html` interface.

---

## 🤝 Contributing

We welcome contributions to improve **SpringVault Shop**! Your input helps make this project better for everyone and strengthens this reference implementation for the Spring community.

### How to Contribute

1. **Fork the repository** - Click the 'Fork' button at the top right of this page.
2. **Clone your fork** - Get the codebase onto your local machine.
3. **Create a feature branch** 
   ```bash
   git checkout -b feature/refactor-cart-service-logic
   ```
4. **Make your changes** - Focus on clear, atomic changes. Improve Java code, Thymeleaf templates, utility classes (`CommonUtil.java`), or update documentation.
5. **Test thoroughly** - Ensure all existing functionality and your new additions work as expected. Although the analysis noted a dedicated `test` directory, you should rely on standard JUnit/Spring Boot testing practices.
   ```bash
   ./mvnw test
   ```
6. **Commit your changes** - Write clear, descriptive commit messages following conventional guidelines (e.g., `Fix:`, `Feat:`, `Refactor:`).
   ```bash
   git commit -m 'Feat: Implemented transactional support for order address saving'
   ```
7. **Push to your branch**
   ```bash
   git push origin feature/refactor-cart-service-logic
   ```
8. **Open a Pull Request** - Submit your changes from your fork back to the main repository for review by the maintainers.

### Development Guidelines

- ✅ Follow the existing Java/Spring Boot code style and conventions (especially the Service/Controller/Repository structure).
- 📝 Add Javadocs for complex methods and classes, particularly within the `service/impl` packages.
- 🧪 Write corresponding unit or integration tests for new business logic introduced in services.
- 📚 Update the README or create new documentation if you change critical configuration or application flow.
- 🔄 Ensure changes maintain backward compatibility where possible.
- 🎯 Keep commits focused and atomic to simplify the review process.

### Ideas for Contributions

We're actively seeking help with:

- 🐛 **Bug Fixes:** Address any runtime exceptions or logical errors, particularly around the complex order processing flow.
- ✨ **Feature Enhancements:** Integrate payment gateway stubs or advanced user filtering features for administrators.
- 📖 **Documentation:** Improve the setup guide for the MySQL connection and initial data population.
- ⚡ **Performance:** Optimize JPA queries within the repository layer for high-volume endpoints.
- 🧪 **Testing:** Increase test coverage for critical business logic within the `OrderServiceImpl.java` and `CartServiceImpl.java`.

### Code Review Process

- All submissions require review by one or more maintainers before merging.
- Maintainers will provide constructive feedback based on best practices and project goals.
- Changes may be requested to meet quality or style standards before final approval.
- Once approved, your PR will be merged, and you will be credited for your contribution.

---

<p align="center">Made by Ravindra Singh Pokhriyal by Java and Spring Boot</p>
<p align="center">
  <a href="#-table-of-contents">⬆️ Back to Top</a>
</p>
