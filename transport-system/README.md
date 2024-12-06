# Transport Companies Management System Documentation

## Overview
This **Transport Companies Management System** is a Java application designed to simulate the operations of a different kind of transport companies.

The system is designed to help transport companies streamline their operations effectively. Each transport company manages vehicles and employees as part of their workflow. The core activity of these companies involves transporting goods and passengers to various destinations. Each transport is defined by key details such as the starting point, destination, departure date, and arrival date. Additionally, the nature of the transported items must be clearly specified. If goods are being transported, the total weight of the cargo needs to be recorded.

Transport companies can register their transport activities and set prices for each trip. The vehicles in their fleet come in various types, including buses, trucks, tankers, and more. Drivers operating these vehicles possess different qualifications, tailored to the specific requirements of their roles.

The **SOLID** principles are mostly strictly followed, with an exception of the Interface Segregation Principle.

Some of the used design patterns are **Observer Pattern** for multi entities updates for the salaries payments for example and **Strategy Pattern** for the filtering data logic.

## Technology Stack

##### Programming Language: Java
##### ORM: Hibernate
##### Testing Framework: JUnit
##### Data Processing: Java Streams API
##### Build Tool: Gradle

## Key Features

### Transport Company Operations 
Load, create, edit, and delete transport company records. Each company profile includes key details such as revenue & expenses, the employees they hire, the vehicles they use, the transport orders they handle etc.

### Customers Operations
Load, create, edit, and delete transport company customers records. Each customer profile captures key details such as contact information, loyalty status, total lifetime spending, the date of their first engagement with the company, and more.

### Vehicles Operations
Load, create, edit, and delete transport company vehicles records. Each vehicle profile captures key details such as vehicle type, manifacturer & model, the current carrying amount, the driving category that it requires, and more technical specifications.

### Staff Operations
Load, create, edit, and delete transport company employees records. Each staff profile includes essential details such as their position, current salary, annual bonuses, hiring date, contract terms, and salary payment history.

### Transport Orders Operations
Load, create, edit, and delete transport company orders records. Each order profile includes essential details such as the order type, the driver and vehicle assigned to it, departure and destination locations and dates, as well as the payment status.

### Sorting & Filtering Data and Detailed Report Generation
- There are mulptiple function which allows the user to fetch sorted and filtered data by different arguments and there are also custom filtering functionality for all components of the application.

- There is an option to save the transport data in a file and the ability to retrieve and display this data.

- Display reports for the total number of transportations performed, the total amount of transportations performed, a list of drivers and how many transportations each of them has performed, the company's revenue for a certain period of time, how much revenue each of the drivers has, etc.

## Roadmap
- Enhanced Role-Based Access Control
- Development of web and mobile front-end applications to enhance user experience (UX)
- Real-Time Notifications about scheduled orders, salary payments, employees notice period and others