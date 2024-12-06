package org.example.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import org.example.entity.*;
import org.example.entity.enums.*;
import org.example.service.CompanyService;
import org.example.service.CustomerService;
import org.example.service.DriverService;
import org.example.service.StaffService;
import org.example.service.TransportOrderService;
import org.example.service.VehicleService;

public class DataSeeder {
    public static void seed(CompanyService companyService, CustomerService customerService, DriverService driverService, StaffService staffService, TransportOrderService transportOrderService, VehicleService vehicleService) {
        // Check if any company exists in the database
        if (!companyService.getCompanies().isEmpty()) {
            System.out.println("Data is already seeded. Skipping seeding.");
            return;
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
            Company company3 = new Company(
                "Global Movers",
                "789 Cargo Lane, City, Country",
                LocalDate.of(2012, 3, 20),
                "VAT456789123",
                "Cargo Services",
                "Chris Green, Amanda Black",
                new BigDecimal("7500000.00")
            );
            companyService.saveCompany(company1);
            companyService.saveCompany(company2);
            companyService.saveCompany(company3);

            // Create Staff
            Staff staff1 = new Staff("John Doe", Position.MANAGER, 35, new BigDecimal("5000.00"), 10.0, LocalDate.of(2018, 6, 1), company1, "john18@gmail.com");
            Staff staff2 = new Staff("Jane Smith", Position.DRIVER, 28, new BigDecimal("3000.00"), 5.0, LocalDate.of(2020, 3, 15), company2, "jane@gmail.com");
            Staff staff3 = new Staff("Alice Brown", Position.ACCOUNTANT, 40, new BigDecimal("4000.00"), 12.0, LocalDate.of(2015, 1, 10), company3, "alice@global.com");
            staffService.saveStaff(staff1);
            staffService.saveStaff(staff2);
            staffService.saveStaff(staff3);

            // Create Vehicles
            Vehicle vehicle1 = new Vehicle(company1, VehicleType.TRUCK, "Volvo", "FH16", "ABC123", 2018, 120000.0, 500, new BigDecimal("50000.00"), 25.0, 1000.0, 50, DrivingCategory.C, false);
            Vehicle vehicle2 = new Vehicle(company2, VehicleType.BUS, "Mercedes", "Sprinter", "XYZ456", 2020, 80000.0, 150, new BigDecimal("35000.00"), 12.5, 500.0, 12, DrivingCategory.B, true);
            Vehicle vehicle3 = new Vehicle(company3, VehicleType.VAN, "Ford", "Transit", "GHI789", 2021, 60000.0, 100, new BigDecimal("20000.00"), 15.0, 800.0, 25, DrivingCategory.B, false);
            vehicleService.saveVehicle(vehicle1);
            vehicleService.saveVehicle(vehicle2);
            vehicleService.saveVehicle(vehicle3);

            // Create Drivers
            Driver driver1 = new Driver(
                "Jack Daniels",
                Position.DRIVER,
                30,
                new BigDecimal("35000.00"),
                10.5,
                LocalDate.of(2015, 6, 10),
                company1,
                "jack15@gmail.com",
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
                "mary@gmail.com",
                Set.of(DrivingCategory.D, DrivingCategory.B),
                80000L,
                new BigDecimal("200.00")
            );
            Driver driver3 = new Driver(
                "Peter Parker",
                Position.DRIVER,
                32,
                new BigDecimal("40000.00"),
                12.0,
                LocalDate.of(2010, 4, 12),
                company3,
                "peter@global.com",
                Set.of(DrivingCategory.C, DrivingCategory.D),
                150000L,
                new BigDecimal("800.00")
            );
            driverService.saveDriver(driver1);
            driverService.saveDriver(driver2);
            driverService.saveDriver(driver3);

            // Create Transport Orders
            TransportOrder order1 = new TransportOrder(company1, vehicle1, driver1, "New York", "Los Angeles", LocalDate.of(2024, 12, 5), LocalDate.of(2024, 12, 7), 4000.0, TransportType.PEOPLE_TRANSPORTATION, 2000, new BigDecimal("10000.00"), false);
            TransportOrder order2 = new TransportOrder(company2, vehicle2, driver2, "San Francisco", "Chicago", LocalDate.of(2024, 12, 10), LocalDate.of(2024, 12, 12), 3000.0, TransportType.CARGO_TRANSPORTATION, 500, new BigDecimal("5000.00"), true);
            TransportOrder order3 = new TransportOrder(company3, vehicle3, driver3, "Miami", "Houston", LocalDate.of(2024, 12, 15), LocalDate.of(2024, 12, 17), 5000.0, TransportType.CARGO_TRANSPORTATION, 1200, new BigDecimal("15000.00"), false);
            transportOrderService.saveTransportOrder(order1);
            transportOrderService.saveTransportOrder(order2);
            transportOrderService.saveTransportOrder(order3);

            // Create Customers
            Customer customer1 = new Customer(
                "Acme Corporation",
                "Leading supplier of construction materials",
                LocalDate.of(2015, 1, 1),
                null,
                LoyaltyStatus.GOLD,
                new BigDecimal("50000.00"),
                "1234567890",
                "123 Business Ave, New York, NY",
                "contacts@acme.com"
            );
            Customer customer2 = new Customer(
                "Beta Enterprises",
                "Supplier of office equipment",
                LocalDate.of(2018, 5, 20),
                null,
                LoyaltyStatus.SILVER,
                new BigDecimal("25000.00"),
                "9876543210",
                "456 Corporate Blvd, San Francisco, CA",
                "info@beta.com"
            );
            Customer customer3 = new Customer(
                "Omega Tech",
                "Technology solutions provider",
                LocalDate.of(2020, 7, 10),
                null,
                LoyaltyStatus.PLATINUM,
                new BigDecimal("100000.00"),
                "1029384756",
                "789 Tech Park, Silicon Valley, CA",
                "support@omega.com"
            );
            customerService.saveCustomer(customer1);
            customerService.saveCustomer(customer2);
            customerService.saveCustomer(customer3);

            System.out.println("Database has been seeded successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}