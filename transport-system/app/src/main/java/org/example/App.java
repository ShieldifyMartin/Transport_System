package org.example;

import java.math.BigDecimal;
import java.util.List;
import org.example.entity.Company;
import org.example.entity.Customer;
import org.example.entity.Driver;
import org.example.entity.Staff;
import org.example.entity.TransportOrder;
import org.example.entity.Vehicle;
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

import static org.example.utils.CompanyUtils.generateRandomEmail;

public class App {
    public static void main(String[] args) {
        // Initialize services
        System.out.println("Initializing services...");
        CompanyService companyService = new CompanyService();
        CustomerService customerService = new CustomerService();
        DriverService driverService = new DriverService();
        StaffService staffService = new StaffService();
        TransportOrderService transportOrderService = new TransportOrderService();
        VehicleService vehicleService = new VehicleService();

        // Seed initial data
        System.out.println("\nSeeding initial data...");
        DataSeeder.seed(companyService, customerService, driverService, staffService, transportOrderService, vehicleService);

        // --- Functionality 1: Update and get operations for companies ---
        System.out.println("\nUpdating and retrieving company details...");
        Company existingCompany = companyService.getCompanyById(1);
        if (existingCompany != null) {
            existingCompany.setName("Global Transport Ltd.");
            existingCompany.setAddress("456 Broad Ave");
            existingCompany.setTotalRevenue(BigDecimal.valueOf(10_000));
            existingCompany.setTotalExpenses(BigDecimal.valueOf(5_000));
            companyService.updateCompany(existingCompany);
            System.out.println("Updated Company: " + existingCompany);
        }

        // --- Functionality 2: Update and get operations for customers ---
        System.out.println("\nUpdating and retrieving customer details...");
        Customer existingCustomer = customerService.getCustomers().get(0);
        if (existingCustomer != null) {
            existingCustomer.setName("Jane Smith");
            existingCustomer.setEmail(generateRandomEmail());
            customerService.updateCustomer(existingCustomer);
            System.out.println("Updated Customer: " + existingCustomer);
        }

        // --- Functionality 3: Update and get operations for vehicles ---
        System.out.println("\nUpdating and retrieving vehicle details...");
        Vehicle existingVehicle = vehicleService.getVehicleById(1);
        if (existingVehicle != null) {
            existingVehicle.setModel("Volvo FH");
            existingVehicle.setVehicleType(VehicleType.VAN);
            vehicleService.updateVehicle(existingVehicle);
            System.out.println("Updated Vehicle: " + existingVehicle);
        }

        // --- Functionality 4: Update and get operations for staff ---
        System.out.println("\nUpdating and retrieving staff details...");
        Staff existingStaff = staffService.getStaffById(1);
        if (existingStaff != null) {
            existingStaff.setName("Bob Williams");
            existingStaff.setPosition(Position.MANAGER);
            staffService.updateStaff(existingStaff);
            System.out.println("Updated Staff: " + existingStaff);
        }

        // --- Functionality 4.1: Update and get operations for drivers ---
        System.out.println("\nUpdating and retrieving driver details...");
        Driver existingDriver = driverService.getDriverById(1);
        if (existingDriver != null) {
            existingDriver.setTotalKmDrivenForCompany(15000L);
            existingDriver.setTotalFinesAndSanctions(new BigDecimal("200.00"));
            driverService.updateDriver(existingDriver);
            System.out.println("Updated Driver: " + existingDriver);
        }

        // --- Functionality 5: Record transport data ---
        System.out.println("\nUpdating and retrieving transport order details...");
        TransportOrder existingTransportOrder = transportOrderService.getTransportOrderById(1);
        if (existingTransportOrder != null) {
            existingTransportOrder.setDepartureLocation("Chicago");
            existingTransportOrder.setDestinationLocation("Miami");
            existingTransportOrder.setTransportType(TransportType.CARGO_TRANSPORTATION);
            existingTransportOrder.setAmount(BigDecimal.valueOf(3000.0));
            existingTransportOrder.setIsPaid(true);
            transportOrderService.updateTransportOrder(existingTransportOrder);
            transportOrderService.completeTransportOrder(existingTransportOrder);
            System.out.println("Updated Transport Order: " + existingTransportOrder);
        }

        // --- Save and load transport orders ---
        System.out.println("\nSaving and loading transport orders...");
        String filePath = "transport_orders.csv";
        transportOrderService.saveTransportOrdersToFile(filePath);
        System.out.println("Transport orders saved to file: " + filePath);

        List<TransportOrder> loadedTransportOrders = transportOrderService.loadTransportOrdersFromFile(filePath);
        System.out.println("Transport orders loaded from file:");
        loadedTransportOrders.forEach(System.out::println);

        // --- Sorting and filtering data ---
        System.out.println("\nSorting and filtering data...");
        List<Company> sortedByName = companyService.getCompaniesSortedByName();
        System.out.println("Companies sorted by name:");
        sortedByName.forEach(System.out::println);

        List<Customer> customersSortedByMoneySpent = customerService.getCustomersSortedByMoneySpent();
        System.out.println("Customers sorted by money spent:");
        customersSortedByMoneySpent.forEach(System.out::println);

        List<TransportOrder> sortedByAmountDesc = transportOrderService.sortTransportOrdersByAmountDesc();
        System.out.println("Transport Orders sorted by amount (descending):");
        sortedByAmountDesc.forEach(System.out::println);

        List<Vehicle> sortedByProductionYear = vehicleService.sortByProductionYearDescending();
        System.out.println("Vehicles sorted by production year (descending):");
        sortedByProductionYear.forEach(System.out::println);
    }
}