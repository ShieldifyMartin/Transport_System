package org.example.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.example.dao.VehicleDAO;
import org.example.entity.Vehicle;


public class VehicleService {
    // Get a list of all vehicles
    public List<Vehicle> getAllVehicles() {
        return VehicleDAO.getVehicles();
    }

    // Get a specific vehicle by ID
    public Vehicle getVehicleById(long id) {
        return VehicleDAO.getVehicleById(id);
    }

    // Save a new vehicle
    public void saveVehicle(Vehicle vehicle) {
        // First, validate the vehicle
        validateVehicle(vehicle);

        // Then, save it to the database
        VehicleDAO.saveVehicle(vehicle);
    }

    // Update an existing vehicle
    public void updateVehicle(Vehicle vehicle) {
        // Validate the vehicle before updating
        validateVehicle(vehicle);

        // Update the vehicle in the database
        VehicleDAO.updateVehicle(vehicle);
    }

    // Soft delete a vehicle by marking it as deleted
    public void deleteVehicle(Vehicle vehicle) {
        if (vehicle == null || vehicle.getId() == 0) {
            throw new IllegalArgumentException("Invalid vehicle");
        }
        VehicleDAO.softDeleteVehicle(vehicle);
    }

    // Hard delete vehicle (mark as deleted)
    public void hardDeleteVehicle(Vehicle vehicle) {
        if (vehicle == null || vehicle.getId() == 0) {
            throw new IllegalArgumentException("Invalid vehicle");
        }
        VehicleDAO.hardDeleteVehicle(vehicle);
    }

    // Validate the vehicle data manually (without using javax.validation)
    private void validateVehicle(Vehicle vehicle) {
        StringBuilder errorMessage = new StringBuilder();

        // Check vehicle type
        if (vehicle.getVehicleType() == null) {
            errorMessage.append("Vehicle type cannot be null! ");
        }

        // Check manufacture
        if (vehicle.getManifacture() == null || vehicle.getManifacture().isBlank()) {
            errorMessage.append("Manufacturer cannot be blank! ");
        } else if (vehicle.getManifacture().length() > 100) {
            errorMessage.append("Manufacturer name cannot exceed 100 characters! ");
        }

        // Check model
        if (vehicle.getModel() == null || vehicle.getModel().isBlank()) {
            errorMessage.append("Model cannot be blank! ");
        } else if (vehicle.getModel().length() > 100) {
            errorMessage.append("Model name cannot exceed 100 characters! ");
        }

        // Check license plate
        if (vehicle.getLicensePlate() == null || vehicle.getLicensePlate().isBlank()) {
            errorMessage.append("License plate cannot be blank! ");
        } else if (vehicle.getLicensePlate().length() > 20) {
            errorMessage.append("License plate cannot exceed 20 characters! ");
        }

        // Check production year
        if (vehicle.getProductionYear() < 1800 || vehicle.getProductionYear() > 2100) {
            errorMessage.append("Production year must be between 1800 and 2100! ");
        }

        // Check mileage
        if (vehicle.getMileageKm() < 0) {
            errorMessage.append("Mileage must be zero or positive! ");
        }

        // Check horsepower
        if (vehicle.getHorsePower() < 1 || vehicle.getHorsePower() > 4000) {
            errorMessage.append("Horsepower must be between 1 and 4000! ");
        }

        // Check carrying amount
        if (vehicle.getCarryingAmount().compareTo(BigDecimal.ZERO) < 0) {
            errorMessage.append("Carrying amount must be zero or positive! ");
        }

        // Check fuel consumption
        if (vehicle.getFuelConsumptionPer100Km() < 0) {
            errorMessage.append("Fuel consumption per 100km must be zero or positive! ");
        }

        // Check luggage capacity
        if (vehicle.getLuggageCapacityKg() < 0) {
            errorMessage.append("Luggage capacity must be zero or positive! ");
        }

        // Check seating capacity
        if (vehicle.getSeatingCapacity() < 1 || vehicle.getSeatingCapacity() > 100) {
            errorMessage.append("Seating capacity must be between 1 and 100! ");
        }

        // Check required driving category
        if (vehicle.getRequiredDrivingCategory() == null) {
            errorMessage.append("Required driving category cannot be null! ");
        }

        // Check if production year is no later than the current year
        int currentYear = LocalDate.now().getYear();
        if (vehicle.getProductionYear() > currentYear) {
            errorMessage.append("Production year must be no later than the current year! ");
        }

        // If there are any validation errors, throw an exception
        if (errorMessage.length() > 0) {
            throw new IllegalArgumentException("Validation failed for vehicle: " + errorMessage.toString());
        }
    }
}