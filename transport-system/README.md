# Transport Companies Management System Documentation

## Overview
This **Transport Companies Management System** is a Java application designed to simulate the operations of a different kind of transport companies. It handles various functionalities including managing inventory, processing sales, calculating profits, and managing cashiers and checkouts. The system also handles exceptions such as insufficient stock and out-of-stock scenarios.

The **SOLID** principles are mostly followed, with a few minor exceptions, such as the Single Responsibility Principle in the `sellProducts()` function and the **Interface Segregation Principle**.

The **Facade** design pattern is used to provide a simplified interface to a complex subsystem. The `StoreService` class encapsulates the complexities of managing the Store class's operations. The client code interacts only with the StoreService for performing tasks, ensuring that changes in the Store class do not affect the client code directly. By using the Facade pattern, the code ensures that interactions with the `Store` class are streamlined, reducing the complexity and making the system easier to use and maintain.

## Technology Stack
Java, Hibernate, JUnit, Java Streams API, Gradle

## Key Features

### Inventory Management
This feature allows store managers to add new products to the inventory and remove products that are no longer available or expired. It helps in keeping the inventory updated, ensuring that only available products are listed for sale.

### Sales Processing
Handles the entire process of selling products, including checking stock availability, updating inventory, and generating receipts. Ensures accurate transaction processing and inventory management, reducing errors and enhancing customer satisfaction.
Handle sales transactions, including applying markups and discounts.

### Exception Handling
Manages exceptions related to out-of-stock and insufficient stock scenarios, ensuring that customers are informed about stock issues promptly. Enhances the reliability of the system by preventing transactions that cannot be fulfilled due to stock issues.

### Financial Calculations
Calculates the store's revenue from sales and determines the profit by subtracting expenses from the revenue. Provides accurate financial insights, helping store managers make informed business decisions.

### Receipt Processing
Generates detailed receipts on sales in a separate file for the user while providing additional insights into the store's performance. This helps in monitoring the store’s financial health and identifying areas for improvement.

### Customer Management
Manage customer information, including balance and transaction history, to enhance customer service. This would enable personalized service and better management of customer relationships in the future developement and marketing campaigns.

### Employee Management
Manage cashiers and checkouts, including adding new cashiers and assigning them to checkouts. This ensures efficient allocation of human resources and smooth operation of the store.

## Roadmap
- Enhanced User Interface: Develop a graphical user interface (GUI) for easier interaction with the system.
- Automated Restocking: Implement an automated system to reorder products when stock falls below a certain threshold.
- Advanced Reporting: Add detailed sales and financial reports to provide insights into the store’s performance.
- Special Offers: Add functionality to create special offers containing multiple products.
- Customer Loyalty Program: Introduce a loyalty program to reward repeat customers with discounts and special offers.
- Multilingual UX: Add support for multiple languages to cater to a diverse customer base in the project's entry point (`App.java`).
- Integration of third-party systems, such as payment gateways and supply chain management systems, enhancing the efficiency of the store by automating various processes and reducing manual effort.
- Implementation of the followin design patterns:
   - **Factory Pattern:**
The class has methods like `sellProducts()` which involve creating complex objects (e.g., `Receipt`). This method can be extracted into a factory class or method to handle the creation of `Receipt` objects.
   - **Strategy Pattern:**
The `applyMarkup()` and `applyDiscount()` methods implement different strategies for calculating prices based on product type and expiration date. These can be refactored into separate strategy classes.