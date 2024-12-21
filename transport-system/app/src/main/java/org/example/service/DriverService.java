package org.example.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.example.dao.DriverDAO;
import org.example.dao.StaffDAO;
import org.example.entity.Driver;
import org.example.entity.Staff;
import org.example.entity.enums.Position;

public class DriverService {
    // Fetch a driver by ID
    public Driver getDriverById(long id) {
        Staff staff = StaffDAO.getStaffById(id);
        
        // Check if the staff is an instance of Driver
        if (staff instanceof Driver) {
            return (Driver) staff;
        } else {
            return null;
        }
    }

    // Fetch all drivers
    public List<Driver> getAllDrivers() {
        List<Staff> staffList = StaffDAO.getStaffByPosition(Position.DRIVER);
        List<Driver> drivers = new ArrayList<>();
        
        // Iterate through staffList and cast only Driver objects
        for (Staff staff : staffList) {
            if (staff instanceof Driver) {
                drivers.add((Driver) staff);  // Cast Staff to Driver
            }
        }
        
        return drivers;
    }

    // Save a new driver
    public void saveDriver(Driver driver) {
        validateDriver(driver);
        DriverDAO.saveDriver(driver);
    }

    // Update an existing driver
    public void updateDriver(Driver driver) {
        if ((Driver) StaffDAO.getStaffById(driver.getId()) == null) {
            throw new IllegalArgumentException("Cannot update non-existent driver with ID " + driver.getId());
        }
        validateDriver(driver);
        DriverDAO.updateDriver(driver);
    }

    // Soft delete driver (mark as deleted)
    public void deleteDriver(Driver driver) {
        if (driver == null || driver.getId() == 0) {
            throw new IllegalArgumentException("Invalid driver");
        }
        StaffDAO.softDeleteStaff(driver.getId());
    }

    // Method to hard delete a driver
    public void hardDeleteDriver(Driver driver) {
        if (driver == null || driver.getId() == 0) {
            throw new IllegalArgumentException("Invalid driver");
        }
        DriverDAO.hardDeleteDriver(driver);
    }

    // Validate a driver object
    private void validateDriver(Driver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("Driver cannot be null.");
        }

        // Name validation
        if (driver.getName() == null || driver.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Driver name cannot be null or empty.");
        }
        if (driver.getName().length() < 1 || driver.getName().length() > 100) {
            throw new IllegalArgumentException("Driver name must be between 1 and 100 characters.");
        }

        // Position validation
        if (driver.getPosition() == null) {
            throw new IllegalArgumentException("Position cannot be null.");
        }

        // Age validation
        if (driver.getAge() <= 0) {
            throw new IllegalArgumentException("Age must be greater than 0.");
        }

        // Salary validation
        if (driver.getSalary() == null || driver.getSalary().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Salary must be a non-negative value.");
        }

        // Yearly bonus percentage validation
        if (driver.getYearlyBonusPercentage() < 0 || driver.getYearlyBonusPercentage() > 100) {
            throw new IllegalArgumentException("Yearly bonus percentage must be between 0 and 100.");
        }

        // Hiring date validation
        if (driver.getHiringDate() == null) {
            throw new IllegalArgumentException("Hiring date cannot be null.");
        }
        if (driver.getHiringDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Hiring date cannot be in the future.");
        }

        // Company validation
        if (driver.getCompany() == null) {
            throw new IllegalArgumentException("Company cannot be null.");
        }

        // Email validation
        if (driver.getEmail() != null && !driver.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("Email must be valid.");
        }

        // Driving category validation (updated to a single string)
        if (driver.getDrivingCategory() == null) {
            throw new IllegalArgumentException("Driving category cannot be null or empty.");
        }

        // Total kilometers driven validation
        if (driver.getTotalKmDrivenForCompany() != null && driver.getTotalKmDrivenForCompany() < 0) {
            throw new IllegalArgumentException("Total kilometers driven for the company must be zero or positive.");
        }

        // Total fines and sanctions validation
        if (driver.getTotalFinesAndSanctions() != null) {
            if (driver.getTotalFinesAndSanctions().compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Total fines and sanctions must be zero or positive.");
            }
        }
    }
}