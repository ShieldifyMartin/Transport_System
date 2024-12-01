package org.example.utils;

import org.example.dao.*;
import org.example.entity.*;
import org.example.entity.enums.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public class DataSeeder {
    public static void seed() {
        // Check if any company exists in the database
        if (!CompanyDAO.getCompanies().isEmpty()) {
            System.out.println("Data is already seeded. Skipping seeding.");
            return; // Skip the seeding process if data already exists
        }

        try {
            // Create Companies
            Company company1 = new Company(
                "ABC Transport",
                "123 Transport Street, City, Country",
                LocalDate.of(2010, 5, 1),
                "VAT123456789",
                "Logistics",
                "John Doe, Jane Smith",
                new BigDecimal("5000000.00")
            );
            Company company2 = new Company(
                "XYZ Logistics",
                "456 Logistics Road, City, Country",
                LocalDate.of(2015, 8, 15),
                "VAT987654321",
                "Logistics",
                "Alice Brown, Bob White",
                new BigDecimal("2000000.00")
            );
            CompanyDAO.saveCompany(company1);
            CompanyDAO.saveCompany(company2);

            // Create Staff
            Staff staff1 = new Staff("John Doe", Position.MANAGER, 35, new BigDecimal("5000.00"), 10.0, LocalDate.of(2018, 6, 1), company1);
            Staff staff2 = new Staff("Jane Smith", Position.DRIVER, 28, new BigDecimal("3000.00"), 5.0, LocalDate.of(2020, 3, 15), company2);
            StaffDAO.saveStaff(staff1);
            StaffDAO.saveStaff(staff2);

            // Create Vehicles
            Vehicle vehicle1 = new Vehicle(company1, VehicleType.TRUCK, "Volvo", "FH16", "ABC123", 2018, 120000.0, 500, new BigDecimal("50000.00"), 25.0, 1000.0, 50, DrivingCategory.C, false);
            Vehicle vehicle2 = new Vehicle(company2, VehicleType.BUS, "Mercedes", "Sprinter", "XYZ456", 2020, 80000.0, 150, new BigDecimal("35000.00"), 12.5, 500.0, 12, DrivingCategory.B, true);
            VehicleDAO.saveVehicle(vehicle1);
            VehicleDAO.saveVehicle(vehicle2);

            // Create Drivers
            Driver driver1 = new Driver(
                "Jack Daniels",
                Position.DRIVER,
                30,
                new BigDecimal("35000.00"),
                10.5,
                LocalDate.of(2015, 6, 10),
                company1,
                Set.of(DrivingCategory.B, DrivingCategory.C),
                120000L,
                new BigDecimal("500.00")
            );
            Driver driver2 = new Driver(
                "Mary Johnson",
                Position.DRIVER,
                25,
                new BigDecimal("30000.00"),
                8.0,
                LocalDate.of(2018, 3, 15),
                company2,
                Set.of(DrivingCategory.D, DrivingCategory.B),
                80000L,
                new BigDecimal("200.00")
            );
            DriverDAO.saveDriver(driver1);
            DriverDAO.saveDriver(driver2);

            // Create Transport Orders
            TransportOrder order1 = new TransportOrder(company1, vehicle1, driver1, "New York", "Los Angeles", LocalDate.of(2024, 12, 5), LocalDate.of(2024, 12, 7), 4000.0, TransportType.PEOPLE_TRANSPORTATION, 2000, new BigDecimal("10000.00"));
            TransportOrder order2 = new TransportOrder(company2, vehicle2, driver2, "San Francisco", "Chicago", LocalDate.of(2024, 12, 10), LocalDate.of(2024, 12, 12), 3000.0, TransportType.CARGO_TRANSPORTATION, 500, new BigDecimal("5000.00"));
            TransportOrderDAO.saveTransportOrder(order1);
            TransportOrderDAO.saveTransportOrder(order2);

            // Create Customers
            Customer customer1 = new Customer(
                null,
                "Acme Corporation",
                "Leading supplier of construction materials",
                LocalDate.of(2015, 1, 1),
                null,
                LoyaltyStatus.GOLD,
                new BigDecimal("50000.00"),
                "+1234567890",
                "123 Business Ave, New York, NY"
            );
            Customer customer2 = new Customer(
                null,
                "Beta Enterprises",
                "Supplier of office equipment",
                LocalDate.of(2018, 5, 20),
                null,
                LoyaltyStatus.SILVER,
                new BigDecimal("25000.00"),
                "+9876543210",
                "456 Corporate Blvd, San Francisco, CA"
            );
            CustomerDAO.saveCustomer(customer1);
            CustomerDAO.saveCustomer(customer2);

            System.out.println("Database has been seeded successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}