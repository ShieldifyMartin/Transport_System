package org.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.example.entity.Company;
import org.example.entity.Customer;
import org.example.entity.Driver;
import org.example.entity.Staff;
import org.example.entity.TransportOrder;
import org.example.entity.Vehicle;
import org.example.entity.enums.DrivingCategory;
import org.example.entity.enums.LoyaltyStatus;
import org.example.entity.enums.Position;
import org.example.entity.enums.TransportType;
import org.example.entity.enums.VehicleType;
import org.example.service.CompanyService;
import org.example.service.CustomerService;
import org.example.service.DriverService;
import org.example.service.StaffService;
import org.example.service.TransportOrderService;
import org.example.service.VehicleService;
import org.example.utils.DataSeeder;

import static org.example.utils.CompanyUtils.generateRandomVAT;

public class App {
    public static void main(String[] args) {
        // Initialize services
        CompanyService companyService = new CompanyService();
        CustomerService customerService = new CustomerService();
        DriverService driverService = new DriverService();
        StaffService staffService = new StaffService();
        TransportOrderService transportOrderService = new TransportOrderService();
        VehicleService vehicleService = new VehicleService();

        // Seed initial data if necessary
        DataSeeder.seed(companyService);

        // Functionality 1: CRUD operations for companies
        Company newCompany = new Company(
            "ABC Transport",
            "123 Transport Street, City, Country",
            LocalDate.of(2010, 5, 1),
            generateRandomVAT(),
            "Logistics",
            "John Doe, Jane Smith",
            new BigDecimal("5000000.00")
        );
        companyService.saveCompany(newCompany);
        System.out.println("Added Company: " + newCompany);

        Company existingCompany = companyService.getCompanyById(1);
        if (existingCompany != null) {
            existingCompany.setName("Global Transport Ltd.");
            existingCompany.setAddress("456 Broad Ave");
            existingCompany.setTotalRevenue(BigDecimal.valueOf(10_000));
            existingCompany.setTotalExpenses(BigDecimal.valueOf(5_000));
            companyService.updateCompany(existingCompany);
            System.out.println("Updated Company: " + existingCompany);
        } else {
            System.out.println("No company found with ID 1");
        }

        companyService.hardDeleteCompany(newCompany.getId());
        System.out.println("Deleted Company with ID 2");

        // Functionality 2: CRUD operations for clients
        Customer newCustomer = new Customer(
                null,
                "Acme Corporation",
                "Leading supplier of construction materials",
                LocalDate.of(2015, 1, 1),
                null,
                LoyaltyStatus.GOLD,
                new BigDecimal("50000.00"),
                "1234567890",
                "123 Business Ave, New York, NY",
                "contact@acme.com"
            );
            customerService.saveCustomer(newCustomer);
        System.out.println("Added Client: " + newCustomer);

        Customer existingCustomer = customerService.getCustomerById(1);
        if (existingCustomer != null) {
            existingCustomer.setName("Jane Smith");
            existingCustomer.setEmail("jane.smith@example.com");
            customerService.updateCustomer(existingCustomer);
            System.out.println("Updated Client: " + existingCustomer);
        }

        customerService.hardDeleteCustomer(newCustomer);
        System.out.println("Deleted the newly added Customer");

        // Functionality 3: CRUD operations for vehicles
        Vehicle existingVehicle = vehicleService.getVehicleById(1);
        if (existingCompany != null) {
            Vehicle newVehicle = new Vehicle(existingCompany, VehicleType.CAR, "AUDI", "A6", "СВ1233СВ", 2020, 12000.0, 550, new BigDecimal("70000.00"), 17.0, 1000.0, 5, DrivingCategory.B, false);
            vehicleService.saveVehicle(newVehicle);
            System.out.println("Added Vehicle: " + newVehicle);

            if (existingVehicle != null) {
                existingVehicle.setModel("Volvo FH");
                existingVehicle.setVehicleType(VehicleType.VAN);
                vehicleService.updateVehicle(existingVehicle);
                System.out.println("Updated Vehicle: " + existingVehicle);
            }
            vehicleService.hardDeleteVehicle(newVehicle);
            System.out.println("Deleted Vehicle with ID 2");
        } else {
            System.out.println("Company not found for Vehicle operations.");
        }

        // Functionality 4: CRUD operations for employees
        Staff newStaff = new Staff("John Doe", Position.MANAGER, 35, new BigDecimal("5000.00"), 10.0, LocalDate.of(2018, 6, 1), existingCompany, "john@gmail.com");
        staffService.saveStaff(newStaff);
        System.out.println("Added Employee: " + newStaff);

        Staff existingStaff = staffService.getStaffById(1);
        if (existingStaff != null) {
            existingStaff.setName("Bob Williams");
            existingStaff.setPosition(Position.MANAGER);
            staffService.updateStaff(existingStaff);
            System.out.println("Updated Employee: " + existingStaff);
        }
        staffService.hardDeleteStaff(newStaff);
        System.out.println("Deleted Staff with ID 2");

        // Functionality 4.1: CRUD operations for drivers
        Set<DrivingCategory> drivingCategories = new HashSet<>();
        drivingCategories.add(DrivingCategory.B);
        Driver newDriver = new Driver(
                "Jack Daniels",
                Position.DRIVER,
                30,
                new BigDecimal("35000.00"),
                10.5,
                LocalDate.of(2015, 6, 10),
                existingCompany,
                "jack@gmail.com",
                Set.of(DrivingCategory.B, DrivingCategory.C),
                120000L,
                new BigDecimal("500.00")
            );
        driverService.saveDriver(newDriver);
        System.out.println("Added Driver: " + newDriver);

        Driver existingDriver = driverService.getDriverById(1);
        if (existingDriver != null) {
            existingDriver.setTotalKmDrivenForCompany(15000L);
            existingDriver.setTotalFinesAndSanctions(new BigDecimal("200.00"));
            driverService.updateDriver(existingDriver);
            System.out.println("Updated Driver: " + existingDriver);
        }

        driverService.hardDeleteDriver(newDriver);
        System.out.println("Deleted Driver with ID 2");

        // Functionality 5: Record transport data
        if (existingVehicle != null && existingDriver != null) {
            TransportOrder newTransportOrder = new TransportOrder(existingCompany, existingVehicle, existingDriver, "New York", "Los Angeles", LocalDate.of(2024, 12, 5), LocalDate.of(2024, 12, 7), 4000.0, TransportType.PEOPLE_TRANSPORTATION, 2000, new BigDecimal("10000.00"), false);
            transportOrderService.saveTransportOrder(newTransportOrder);
            System.out.println("Added Transport: " + newTransportOrder);

            TransportOrder existingTransportOrder = transportOrderService.getTransportOrderById(1);
            if (existingTransportOrder != null) {
                existingTransportOrder.setDepartureLocation("Chicago");
                existingTransportOrder.setDestinationLocation("Miami");
                existingTransportOrder.setTransportType(TransportType.CARGO_TRANSPORTATION);
                existingTransportOrder.setAmount(BigDecimal.valueOf(3000.0));
                transportOrderService.updateTransportOrder(existingTransportOrder);
                transportOrderService.completeTransportOrder(existingTransportOrder);
                System.out.println("Updated Transport: " + existingTransportOrder);
            }

            newTransportOrder.setIsPaid(true);
            transportOrderService.hardDeleteTransportOrder(newTransportOrder);
            System.out.println("Deleted Transport with ID 2");
        } else {
            System.out.println("Vehicle or Driver not found for Transport operations.");
        }

        // Functionality 6: Save and load transport orders from an external file
        
        // Save transport orders to file
        String filePath = "transport_orders.csv";
        transportOrderService.saveTransportOrdersToFile(filePath);
        System.out.println("Transport orders saved to file: " + filePath);

        // Load transport orders from file
        List<TransportOrder> loadedTransportOrders = transportOrderService.loadTransportOrdersFromFile(filePath);
        System.out.println("Transport orders loaded from file:");
        for (TransportOrder order : loadedTransportOrders) {
            System.out.println(order);
        }
        transportOrderService.exportFullReportToFile();

        // Load all active companies
        List<Company> companies = companyService.getCompanies();
        System.out.println("All active companies:");
        companies.forEach(company -> System.out.println(company));

        // Load all companies (including soft deleted ones)
        List<Company> allCompanies = companyService.getAllCompanies();
        System.out.println("All companies:");
        allCompanies.forEach(company -> System.out.println(company));
    }
}