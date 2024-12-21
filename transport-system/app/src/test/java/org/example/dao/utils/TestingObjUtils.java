package org.example.dao.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;
import org.example.entity.Company;
import org.example.entity.Driver;
import org.example.entity.Vehicle;
import org.example.entity.enums.DrivingCategory;
import org.example.entity.enums.Position;
import org.example.entity.enums.VehicleType;


public class TestingObjUtils {
    private String generateRandomVAT() {
        int randomNum = (int) (Math.random() * 900000000) + 100000000;
        return "VAT" + randomNum;
    }
    private String generateRandomEmail() {
        int randomNum = (int) (Math.random() * 90000) + 10000;
        return "driver" + randomNum + "@example.com";
    }

    private String generateRandomLicensePlate() {
        Random random = new Random();
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";

        StringBuilder licensePlate = new StringBuilder();

        // First three letters
        for (int i = 0; i < 3; i++) {
            licensePlate.append(letters.charAt(random.nextInt(letters.length())));
        }

        // Hyphen
        licensePlate.append("-");

        // Three numbers
        for (int i = 0; i < 3; i++) {
            licensePlate.append(numbers.charAt(random.nextInt(numbers.length())));
        }

        return licensePlate.toString();
    }

    public Company createTestCompany() {
        return new Company(
            "Test Company",
            "123 Test Street",
            LocalDate.of(2000, 1, 1),
            generateRandomVAT(),
            "Private",
            "Owner1, Owner2",
            new BigDecimal("100000.00")
        );
    }

    public Vehicle createTestVehicle() {
        Company company = new Company("Test Company", "123 Test Street", LocalDate.of(2000, 1, 1), generateRandomVAT(), "Private", "Owner1, Owner2", new BigDecimal("100000.00"));
        
        Vehicle vehicle = new Vehicle(
            company,
            VehicleType.BUS,
            "Test Manufacturer",
            "Test Model",
            generateRandomLicensePlate(),
            2024,
            10000.0,
            150,
            new BigDecimal("500.00"),
            8.5,
            400.0,
            5,
            DrivingCategory.B,
            false
        );
    
        return vehicle;
    }
    
    public Driver createTestDriver() {
        Driver driver = new Driver(
            "Sample driver",
            Position.DRIVER,
            32,
            new BigDecimal("10000.00"),
            10.0,
            LocalDate.of(2020, 4, 12),
            createTestCompany(),
            generateRandomEmail(),
            DrivingCategory.D,
            150000L,
            new BigDecimal("900.00")
        );
        return driver;
    }
}
