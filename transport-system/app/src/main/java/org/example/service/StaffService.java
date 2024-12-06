package org.example.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.example.dao.StaffDAO;
import org.example.entity.Staff;

public class StaffService {
    // Standart getter functions

    // Get Staff by ID
    public Staff getStaffById(long id) {
        return StaffDAO.getStaffById(id);
    }

    // Get list of all active (non-deleted) staff
    public List<Staff> getActiveStaff() {
        return StaffDAO.getStaff();
    }

    // Sorting functions

    public List<Staff> sortStaffByName() {
        return getActiveStaff().stream()
            .sorted(Comparator.comparing(Staff::getName))
            .collect(Collectors.toList());
    }

    // Sorting: Sort staff by salary in descending order
    public List<Staff> sortStaffBySalaryDesc() {
        return getActiveStaff().stream()
            .sorted(Comparator.comparing(Staff::getSalary).reversed())
            .collect(Collectors.toList());
    }

    // Filter functions

    public List<Staff> filterStaffByMinSalary(BigDecimal minSalary) {
        return getActiveStaff().stream()
            .filter(staff -> staff.getSalary().compareTo(minSalary) >= 0)
            .collect(Collectors.toList());
    }

    // Filter staff by position type
    public List<Staff> filterStaffByPosition(String position) {
        if (position == null || position.isBlank()) {
            throw new IllegalArgumentException("Position type cannot be null or blank!");
        }
    
        return getActiveStaff().stream()
            .filter(staff -> position.equals(staff.getPosition()))
            .collect(Collectors.toList());
    }
    
    // Advanced Filtering: Filter staff based on multiple criteria
    public List<Staff> filterStaffAdvanced(String name, Integer minAge, Integer maxAge, BigDecimal minSalary, LocalDate hiredAfter) {
        return getActiveStaff().stream()
            .filter(staff -> (name == null || staff.getName().toLowerCase().contains(name.toLowerCase())))
            .filter(staff -> (minAge == null || staff.getAge() >= minAge))
            .filter(staff -> (maxAge == null || staff.getAge() <= maxAge))
            .filter(staff -> (minSalary == null || staff.getSalary().compareTo(minSalary) >= 0))
            .filter(staff -> (hiredAfter == null || staff.getHiringDate().isAfter(hiredAfter)))
            .collect(Collectors.toList());
    }

    // Save new staff
    public void saveStaff(Staff staff) {
        // Validate staff entity
        validateStaff(staff);

        // Save the validated staff
        StaffDAO.saveStaff(staff);
    }

    // Update existing staff
    public void updateStaff(Staff staff) {
        // Validate staff entity
        validateStaff(staff);

        // Update the validated staff
        StaffDAO.updateStaff(staff);
    }

    // Soft delete staff (mark as deleted)
    public void deleteStaff(Staff staff) {
        if (staff == null || staff.getId() == 0) {
            throw new IllegalArgumentException("Invalid staff");
        }
        StaffDAO.softDeleteStaff(staff);
    }

    // Hard delete staff (mark as deleted)
    public void hardDeleteStaff(Staff staff) {
        if (staff == null || staff.getId() == 0) {
            throw new IllegalArgumentException("Invalid staff");
        }
        StaffDAO.hardDeleteStaff(staff);
    }

    // Validate staff before saving or updating
    private void validateStaff(Staff staff) {
        // Check if staff is null
        if (staff == null) {
            throw new IllegalArgumentException("Staff cannot be null");
        }

        // Check if staff name is empty
        if (staff.getName() == null || staff.getName().isEmpty()) {
            throw new IllegalArgumentException("Staff name cannot be null or empty");
        }

        // Check name length (max 100 chars)
        if (staff.getName().length() > 100) {
            throw new IllegalArgumentException("Staff name must not exceed 100 characters");
        }

        // Check if position is null
        if (staff.getPosition() == null) {
            throw new IllegalArgumentException("Position cannot be null");
        }

        // Check if age is within valid range
        if (staff.getAge() < 16 || staff.getAge() > 100) {
            throw new IllegalArgumentException("Staff age must be between 16 and 100");
        }

        // Check if salary is greater than 0
        if (staff.getSalary() == null || staff.getSalary().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Salary must be greater than 0");
        }

        // Check salary precision (max 2 decimal places)
        if (staff.getSalary().scale() > 2) {
            throw new IllegalArgumentException("Salary must have no more than 2 decimal places");
        }

        // Check if yearly bonus percentage is valid
        if (staff.getYearlyBonusPercentage() < 0 || staff.getYearlyBonusPercentage() > 100) {
            throw new IllegalArgumentException("Yearly bonus percentage must be between 0 and 100");
        }

        // Check if hiring date is in the past or present
        if (staff.getHiringDate() == null || staff.getHiringDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Hiring date cannot be in the future");
        }

        // Check if email is valid
        if (staff.getEmail() == null || staff.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        // Check email length (max 100 chars)
        if (staff.getEmail().length() > 100) {
            throw new IllegalArgumentException("Email must not exceed 100 characters");
        }

        // Check if email has valid format
        if (!staff.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("Email must be a valid email address");
        }
        
        // Check if company is valid
        if (staff.getCompany() == null) {
            throw new IllegalArgumentException("Company cannot be null");
        }
    }
}
