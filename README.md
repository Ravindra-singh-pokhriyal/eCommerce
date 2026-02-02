<h1 align="center"> Ecommerce </h1>
<p align="center"> The Secure, Scalable Shopping Cart Solution Built with Spring Boot, Thymeleaf, and MySQL. </p>

<p align="center">
  <img alt="Build" src="https://img.shields.io/badge/Build-Passing-brightgreen?style=for-the-badge">
  <img alt="Java Version" src="https://img.shields.io/badge/Java-21-blue?style=for-the-badge">
  <img alt="Framework" src="https://img.shields.io/badge/Framework-Spring%20Boot%204.0.1-green?style=for-the-badge">
  <img alt="Contributions" src="https://img.shields.io/badge/Contributions-Welcome-orange?style=for-the-badge">
</p>
<!-- 
  **Note:** These are static placeholder badges. Replace them with your project's actual badges.
  You can generate your own at https://shields.io
-->

## 📋 Table of Contents

- [⭐ Overview](#-overview)
- [✨ Key Features](#-key-features)
- [🛠️ Tech Stack & Architecture](#-tech-stack--architecture)
- [📁 Project Structure](#-project-structure)
- [🚀 Getting Started](#-getting-started)
- [🔧 Usage](#-usage)
- [🤝 Contributing](#-contributing)

---

## ⭐ Overview

This project provides a robust, fully functional **web_app** e-commerce platform that manages product catalogs, user authentication, shopping cart functionality, and order processing. Built on the modern Spring Boot framework, it offers a secure, reliable foundation for creating an online retail presence.

### The Problem

> Building a cohesive, full-stack e-commerce solution requires meticulous handling of several critical components: secure user data management, transactional integrity (cart and orders), and a maintainable administrative interface. Developers often struggle to integrate these complex services into a single, scalable application while ensuring robust security and a dynamic user experience.

### The Solution

The **Ecommerce** platform delivers a complete, integrated solution. Leveraging the power of Spring Boot and Spring Data JPA, this application provides built-in mechanisms for persistence, security (utilizing custom Spring Security configurations), and dynamic content rendering via Thymeleaf templates. It separates concerns effectively, offering dedicated dashboards for administrators to manage inventory and users, while providing customers with a seamless shopping experience—from product browsing to secure checkout and order tracking.

### Architecture Overview

This application adheres to a classic Model-View-Controller (MVC) pattern, implemented using Spring Boot. Business logic resides in the `service` layer, handling transactions and complex operations, while the `controller` layer manages HTTP requests, and the `templates` (views) render dynamic HTML using Thymeleaf. Data persistence is handled efficiently by Spring Data JPA connected to a MySQL database.

---

## ✨ Key Features

The Ecommerce application is designed around user value, offering distinct capabilities for both customers and platform administrators.

### 🛍️ Customer Experience Features

-   **Secure User Authentication & Profiles:** Users can securely register (`register.html`) and log in (`login.html`), benefiting from robust access control implemented via custom Spring Security configuration (`SecurityConfig.java`). Users also have dedicated profile management areas (`user/profile.html`).
-   **Intuitive Product Catalog:** Browse a dynamically rendered list of available products (`product.html`) and view detailed specifications and images (`view_product.html`).
-   **Seamless Shopping Cart Management:** Easily add products to a persistent shopping cart (`Cart` model) and manage quantities before checkout (`user/cart.html`).
-   **Comprehensive Order Tracking:** Users can view their complete order history (`user/my_orders.html`) and track the status of current purchases, ensuring full visibility into the fulfillment process.
-   **Password Recovery:** Built-in functionality for forgotten passwords using dedicated templating (`forgot_password.html`, `reset_password.html`) for enhanced user support.

### ⚙️ Administrative Control Features

-   **Dashboard Management:** Access a dedicated, secure administrative dashboard (`admin/index.html`) to oversee all system activities.
-   **Product Lifecycle Control:** Admins can add new products (`admin/add_product.html`), edit existing inventory details (`admin/edit_product.html`), and manage the entire product catalog (`admin/products.html`).
-   **Category Organization:** Centralized management of product categories (`admin/category.html`) with the ability to edit existing categories (`admin/edit_category.html`), crucial for maintaining a structured store.
-   **User and Order Oversight:** Manage the list of registered users (`admin/users.html`) and monitor all incoming and pending orders (`admin/orders.html`), ensuring timely fulfillment.
-   **Admin Profile Management:** Secure area for administrative profile updates (`admin/profile.html`) and adding new administrator accounts (`admin/add_admin.html`).

---

## 🛠️ Tech Stack & Architecture

This project is built using industry-leading, verified technologies to ensure high performance, security, and developer productivity. The core structure is driven by the Spring Ecosystem, managed entirely by Maven.

| Technology | Purpose | Why it was Chosen (Benefit) |
| :--- | :--- | :--- |
| **Java 21** | Primary Programming Language | Offers modern language features, strong performance, and long-term support for enterprise applications. |
| **Spring Boot** | Core Application Framework | Provides rapid application development, auto-configuration, and robust dependency management, simplifying setup and deployment. |
| **Spring Data JPA** | Data Persistence Layer | Simplifies database interactions by providing repository interfaces, dramatically reducing boilerplate code for CRUD operations. |
| **MySQL Connector/J** | Database Driver | Enables reliable, high-speed connection between the Java application and the highly popular MySQL relational database. |
| **Thymeleaf** | View Layer/Templating Engine | Offers natural templating capabilities, allowing designers to work on static prototypes that seamlessly integrate with Spring Boot data structures. |
| **Lombok (1.18.42)** | Boilerplate Reduction Utility | Reduces tedious getter, setter, constructor, and logging code, leading to cleaner, more readable, and concise Java classes (Models). |
| **Maven** | Build and Dependency Management | Provides a standardized process for building, testing, and managing the project's complex dependencies with version control and consistency. |
| **Spring DevTools** | Development Utility | Enables faster development cycles through automatic restarts and live-reloading of changes during active development. |

---

## 📁 Project Structure

The project is meticulously organized to separate configuration, application logic, persistence layers, and static resources, ensuring high maintainability and clarity. The structure follows standard Maven conventions.

```
📂 Ravindra-singh-pokhriyal-eCommerce-1c2b948/
├── 📄 .gitattributes
├── 📄 pom.xml                      # Maven Project Object Model (Dependencies & Build Configuration)
├── 📄 mvnw.cmd                     # Maven Wrapper for Windows (Execution Script)
├── 📄 mvnw                         # Maven Wrapper for Linux/macOS (Execution Script)
├── 📄 README.md                    # Project Documentation (This file)
├── 📄 .gitignore                   # Files and directories ignored by Git
├── 📂 src/                         # Main source directory
│   ├── 📂 test/
│   │   ├── 📂 java/
│   │       └── 📂 com/
│   │           └── 📂 ravi/
│   │               └── 📂 e_commerce/
│   │                   └── 📄 ECommerceApplicationTests.java  # Core application test file
│   └── 📂 main/
│       ├── 📂 resources/           # Configuration and static resources
│       │   ├── 📄 application.properties.example # Example file for database configuration
│       │   ├── 📂 templates/       # Thymeleaf HTML view templates
│       │   │   ├── 📄 reset_password.html      # Password reset page
│       │   │   ├── 📄 login.html               # User login view
│       │   │   ├── 📄 product.html             # Product listing page
│       │   │   ├── 📄 register.html            # User registration view
│       │   │   ├── 📄 view_product.html        # Single product detail view
│       │   │   ├── 📄 message.html             # Generic message/feedback page
│       │   │   ├── 📄 forgot_password.html     # Password recovery initiation
│       │   │   ├── 📄 base.html                # Thymeleaf layout base template
│       │   │   ├── 📄 index.html               # Public home page
│       │   │   ├── 📂 admin/                   # Administrator views and dashboards
│       │   │   │   ├── 📄 edit_product.html    # Edit existing product form
│       │   │   │   ├── 📄 products.html        # Admin list of all products
│       │   │   │   ├── 📄 add_product.html     # Add new product form
│       │   │   │   ├── 📄 add_admin.html       # Form to add new admin users
│       │   │   │   ├── 📄 users.html           # Admin list of all users
│       │   │   │   ├── 📄 edit_category.html   # Edit existing category form
│       │   │   │   ├── 📄 profile.html         # Admin profile management
│       │   │   │   ├── 📄 category.html        # Admin category list/management
│       │   │   │   ├── 📄 orders.html          # Admin view of all customer orders
│       │   │   │   └── 📄 index.html           # Admin dashboard landing page
│       │   │   └── 📂 user/                    # Authenticated customer views
│       │   │       ├── 📄 cart.html            # Shopping cart view
│       │   │       ├── 📄 success.html         # Post-transaction success page
│       │   │       ├── 📄 home.html            # User's personalized home/dashboard
│       │   │       ├── 📄 profile.html         # User profile management
│       │   │       ├── 📄 order.html           # Specific order details view
│       │   │       └── 📄 my_orders.html       # User's list of past orders
│       │   └── 📂 static/            # Static assets (CSS, JS, Images)
│       │       ├── 📂 js/
│       │       │   └── 📄 script.js          # Custom JavaScript functions
│       │       ├── 📂 images/
│       │       │   ├── 📄 ecom3.jpg
│       │       │   ├── 📄 ecom.png
│       │       │   ├── 📄 ecom2.jpg
│       │       │   ├── 📄 carousel2.jpg
│       │       │   ├── 📄 carousel1.jpg
│       │       │   ├── 📄 carousel3.jpg
│       │       │   ├── 📄 ecom1.png
│       │       │   ├── 📂 login/             # Login specific images
│       │       │   │   ├── 📄 login1.jpg
│       │       │   │   └── 📄 login.jpg
│       │       │   ├── 📂 product_img/       # Product-specific images (inventory assets)
│       │       │   │   ├── 📄 TV.jpg
│       │       │   │   ├── 📄 Samsung galaxy-a32-5g.webp
│       │       │   │   ├── 📄 groccery.jpg
│       │       │   │   ├── 📄 lipstcik.jpg
│       │       │   │   ├── 📄 vivo v40.png
│       │       │   │   ├── 📄 S22.avif
│       │       │   │   ├── 📄 mobile.png
│       │       │   │   ├── 📄 vivo v30 5G.png
│       │       │   │   ├── 📄 asus vivobook 15-i5.jpg
│       │       │   │   ├── 📄 laptop.jpg
│       │       │   │   ├── 📄 pant.png
│       │       │   │   ├── 📄 appli.png
│       │       │   │   ├── 📄 beuty.png
│       │       │   │   ├── 📄 Shirt.png
│       │       │   │   └── 📄 pbi-iphone-15-pro-max.avif
│       │       │   ├── 📂 profile_img/       # User profile images
│       │       │   │   ├── 📄 img.txt
│       │       │   │   └── 📄 ravi.jpg
│       │       │   └── 📂 category_img/      # Category representation images
│       │       │       ├── 📄 groccery.jpg
│       │       │       ├── 📄 mobile.png
│       │       │       ├── 📄 laptop.jpg
│       │       │       ├── 📄 pant.png
│       │       │       ├── 📄 appli.png
│       │       │       └── 📄 beuty.png
│       │       └── 📂 css/
│       │           └── 📄 style.css          # Application styling
│       └── 📂 java/
│           └── 📂 com/
│               └── 📂 ravi/
│                   └── 📂 e_commerce/
│                       ├── 📄 ECommerceApplication.java   # Main Spring Boot entry point
│                       ├── 📂 config/                     # Spring Security and custom configuration
│                       │   ├── 📄 CustomUser.java
│                       │   ├── 📄 AuthSuccessHandlerImpl.java # Custom success handler for post-login redirection
│                       │   ├── 📄 UserDetailsServiceImpl.java # Service for loading user-specific data during authentication
│                       │   ├── 📄 SecurityConfig.java       # Core Spring Security configuration
│                       │   └── 📄 AuthFailureHandlerImpl.java # Custom failure handler for login errors
│                       ├── 📂 repository/                 # Spring Data JPA repositories
│                       │   ├── 📄 CategoryRepository.java
│                       │   ├── 📄 UserRepository.java
│                       │   ├── 📄 CartRepository.java
│                       │   ├── 📄 ProductRepository.java
│                       │   └── 📄 ProductOrderRepository.java
│                       ├── 📂 service/                    # Business logic interfaces
│                       │   ├── 📄 CategoryService.java
│                       │   ├── 📄 OrderService.java
│                       │   ├── 📄 UserService.java
│                       │   ├── 📄 CommonServiceImpl.java
│                       │   ├── 📄 CartService.java
│                       │   ├── 📄 CommonService.java
│                       │   ├── 📄 ProductService.java
│                       │   └── 📂 impl/                   # Business logic implementations
│                       │       ├── 📄 CategoryServiceImpl.java
│                       │       ├── 📄 CartServiceImpl.java
│                       │       ├── 📄 OrderServiceImpl.java
│                       │       ├── 📄 UserServiceImpl.java
│                       │       └── 📄 ProductServiceImpl.java
│                       ├── 📂 model/                      # JPA Entities and DTOs (Data Models)
│                       │   ├── 📄 ProductOrder.java       # Model for tracking specific ordered products
│                       │   ├── 📄 UserDtls.java           # User details entity (main user model)
│                       │   ├── 📄 OrderRequest.java       # DTO/Model for capturing order submission data
│                       │   ├── 📄 Cart.java               # Shopping cart item entity
│                       │   ├── 📄 Product.java            # Product catalog entity
│                       │   ├── 📄 Category.java           # Product category entity
│                       │   └── 📄 OrderAddress.java       # Model for customer shipping address
│                       ├── 📂 util/                       # Helper classes and constants
│                       │   ├── 📄 CommonUtil.java
│                       │   ├── 📄 AppConstant.java
│                       │   └── 📄 OrderStatus.java        # Enum/Class defining possible order states
│                       └── 📂 controller/                 # MVC Controllers (Request handling)
│                           ├── 📄 Homecontroller.java     # Handles public, non-authenticated routes
│                           ├── 📄 UserController.java     # Handles authenticated customer actions
│                           └── 📄 AdminController.java    # Handles secured administrative actions
├── 📂 bin/                         # Compiled class files and secondary structure (often generated during build)
│   ├── 📄 .gitattributes
│   ├── 📄 pom.xml
│   ├── 📄 mvnw.cmd
│   ├── 📄 mvnw
│   ├── 📄 .gitignore
│   ├── 📂 src/
│   │   ├── 📂 test/
│   │   │   └── 📂 java/
│   │   │       └── 📂 com/
│   │   │           └── 📂 ravi/
│   │   │               └── 📂 e_commerce/
│   │   │                   └── 📄 ECommerceApplicationTests.class
│   │   └── 📂 main/
│   │       ├── 📂 resources/
│   │       │   ├── 📄 application.properties # Active configuration file
│   │       │   └── 📂 templates/
│   │       │       └── 📄 index.html
│   │       └── 📂 java/
│   │           └── 📂 com/
│   │               └── 📂 ravi/
│   │                   └── 📂 e_commerce/
│   │                       ├── 📄 ECommerceApplication.class
│   │                       └── 📂 controller/
│   │                           └── 📄 Homecontroller.class
│   └── 📂 .mvn/
│       └── 📂 wrapper/
│           └── 📄 maven-wrapper.properties
└── 📂 .mvn/                        # Maven wrapper configuration
    └── 📂 wrapper/
        └── 📄 maven-wrapper.properties # Properties for Maven version and repository path
```

---

## 🚀 Getting Started

To run the **Ecommerce** application locally, you must satisfy the core software requirements specified by the `pom.xml` and the configuration files.

### Prerequisites

Ensure you have the following software installed on your system:

1.  **Java Development Kit (JDK) 21:** This project explicitly targets Java 21, as defined in the `pom.xml`.
2.  **Apache Maven:** Used for building the project and managing dependencies. (The provided `mvnw` wrapper scripts can typically handle this automatically.)
3.  **MySQL Server:** Required for data persistence, as the project uses the `mysql-connector-j` dependency and Spring Data JPA.

### Installation

Follow these steps to set up and run the application:

#### 1. Clone the Repository

```bash
git clone https://github.com/Ravindra-singh-pokhriyal-eCommerce-1c2b948.git
cd Ravindra-singh-pokhriyal-eCommerce-1c2b948
```

#### 2. Configure Database Connection

The application uses an external MySQL database for persistence.

1.  Ensure your MySQL server is running.
2.  Create a new schema (database) dedicated to this project (e.g., `ecommerce_db`).
3.  Locate the example configuration file: `src/main/resources/application.properties.example`.
4.  Copy this file and rename it to `src/main/resources/application.properties`.

```bash
# Example configuration adjustments (inside application.properties)
# Update these placeholders with your actual MySQL credentials
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db?useSSL=false
spring.datasource.username=YOUR_MYSQL_USER
spring.datasource.password=YOUR_MYSQL_PASSWORD

# Ensure JPA is configured to create/update tables automatically
spring.jpa.hibernate.ddl-auto=update
```

#### 3. Build and Run the Application

Use the Maven wrapper (`mvnw`) to compile and package the Spring Boot application.

**On Linux/macOS:**
```bash
# Grant execution permissions to the wrapper
chmod +x mvnw

# Compile, package, and run the application
./mvnw spring-boot:run
```

**On Windows:**
```bash
# Compile, package, and run the application
mvnw spring-boot:run
```

The application will start, typically running on `http://localhost:8080`. The Spring Security component will initialize, providing secure access control for user and admin roles.

---

## 🔧 Usage

The **Ecommerce** project is a full-featured **web_app** requiring only a modern web browser for interaction once running.

### Accessing the Platform

Once the application is successfully started (look for the Spring Boot banner and log indicating the server started on port 8080):

1.  Open your web browser and navigate to: `http://localhost:8080`

### Customer Workflow

Users interact primarily through the `Homecontroller` and `UserController` routes:

1.  **Registration:** Navigate to `/register` to create a new user account (`register.html`).
2.  **Login:** Access the application via `/login` (`login.html`). Upon successful authentication, users are directed to their personalized home view (`user/home.html`) or handled by `AuthSuccessHandlerImpl`.
3.  **Browsing:** View the product catalog and click on individual items for detailed views (`product.html`, `view_product.html`).
4.  **Shopping:** Add products to the cart and proceed to `/cart` (`user/cart.html`) for review and checkout.
5.  **Order Management:** Track past purchases through the dedicated orders page (`user/my_orders.html`).

### Administrative Workflow

Administrative access is secured and managed by the `AdminController`.

1.  **Access:** Log in with a user configured with the 'ADMIN' role (you may need to manually seed the database or use a script to set up the first admin user).
2.  **Dashboard:** Access the administrative hub, typically at `/admin/index` (`admin/index.html`).
3.  **Product Management:** Use the dedicated product routes (`admin/add_product.html`, `admin/products.html`) to maintain inventory, including updating images stored in `src/main/resources/static/images/product_img/`.
4.  **Order Fulfillment:** Monitor pending and fulfilled orders via the orders management page (`admin/orders.html`). Admins are responsible for updating `OrderStatus` for products.

---

## 🤝 Contributing

We welcome contributions to improve the **Ecommerce** platform! Your input helps make this project better for everyone by enhancing features, fixing bugs, and improving documentation.

### How to Contribute

1. **Fork the repository** - Click the 'Fork' button at the top right of this page
2. **Create a feature branch** 
   ```bash
   git checkout -b feature/amazing-feature
   ```
3. **Make your changes** - Improve code, documentation, or features within the Java controllers, services, repositories, or Thymeleaf templates.
4. **Test thoroughly** - Ensure all existing and new functionality works as expected. Run unit tests if provided (e.g., `ECommerceApplicationTests.java`).
   ```bash
   ./mvnw test
   ```
5. **Commit your changes** - Write clear, descriptive commit messages focusing on the purpose of the change.
   ```bash
   git commit -m 'Feat: Implement dynamic category fetching in AdminController'
   ```
6. **Push to your branch**
   ```bash
   git push origin feature/amazing-feature
   ```
7. **Open a Pull Request** - Submit your changes for review against the main branch.

### Development Guidelines

-   ✅ **Code Style:** Follow the existing Spring and Java coding conventions. Utilize Lombok annotations responsibly for clean code.
-   📝 **Documentation:** Add Javadocs for new classes and complex methods within the `service` and `controller` layers.
-   🧪 **Testing:** Write integration tests, especially for new repository or service layer logic, utilizing the provided test dependencies like `spring-boot-starter-data-jpa-test`.
-   📚 **Template Consistency:** Maintain the structure and styling found in `src/main/resources/templates/base.html` when adding new views.
-   🎯 **Commit Focus:** Keep commits atomic, focusing on one logical change per commit.

### Ideas for Contributions

We're looking for help with improvements in several areas:

-   🐛 **Bug Fixes:** Address any issues related to transactional integrity or security configurations.
-   ✨ **Feature Enhancements:** Expanding checkout options or advanced user filtering for the administrative dashboard.
-   📖 **Documentation:** Creating detailed guides for deploying the application to cloud environments (e.g., AWS, Azure).
-   🎨 **UI/UX:** Modernizing the CSS (`style.css`) and improving the responsiveness of the Thymeleaf templates.
-   ⚡ **Performance:** Optimizing database queries defined within the JPA repositories.

### Code Review Process

-   All submissions are subject to a detailed code review by project maintainers.
-   We aim to provide constructive feedback quickly to facilitate rapid integration.
-   Once approved, your Pull Request will be merged, and you will be credited as a contributor to the **Ecommerce** project.

### Questions?

Feel free to open an issue for any questions regarding the codebase or contributions. We appreciate your interest!

---

<p align="center">Made By Ravindra Singh Pokhriyal</p>
<p align="center">
  <a href="#">⬆️ Back to Top</a>
</p>
