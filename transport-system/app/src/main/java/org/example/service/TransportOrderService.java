package org.example.service;

import java.math.BigDecimal;
import java.util.List;
import org.example.dao.TransportOrderDAO;
import org.example.entity.TransportOrder;

public class TransportOrderService {
    // Get Transport Order by ID
    public TransportOrder getTransportOrderById(long id) {
        return TransportOrderDAO.getTransportOrderById(id);
    }

    // Get list of active (non-deleted) transport orders
    public List<TransportOrder> getActiveTransportOrders() {
        return TransportOrderDAO.getTransportOrders();
    }

    // Save new transport order
    public void saveTransportOrder(TransportOrder transportOrder) {
        // Validate transport order
        validateTransportOrder(transportOrder);

        // Save the validated transport order
        TransportOrderDAO.saveTransportOrder(transportOrder);
    }

    // Update existing transport order
    public void updateTransportOrder(TransportOrder transportOrder) {
        // Validate transport order
        validateTransportOrder(transportOrder);

        // Update the validated transport order
        TransportOrderDAO.updateTransportOrder(transportOrder);
    }
    
    // Soft delete transport order (mark as deleted)
    public void deleteTransportOrder(TransportOrder transportOrder) {
        if (transportOrder == null || transportOrder.getId() == 0) {
            throw new IllegalArgumentException("Invalid transport order");
        }
        TransportOrderDAO.softDeleteTransportOrder(transportOrder);
    }

    // Hard delete transport order (mark as deleted)
    public void hardDeleteTransportOrder(TransportOrder transportOrder) {
        if (transportOrder == null || transportOrder.getId() == 0) {
            throw new IllegalArgumentException("Invalid transport order");
        }
        TransportOrderDAO.hardDeleteTransportOrder(transportOrder);
    }

    // Validate TransportOrder before saving or updating
    private void validateTransportOrder(TransportOrder transportOrder) {
        // Check if transport order is null
        if (transportOrder == null) {
            throw new IllegalArgumentException("Transport Order cannot be null");
        }

        // Validate company
        if (transportOrder.getCompany() == null) {
            throw new IllegalArgumentException("Company cannot be null");
        }

        // Validate vehicle
        if (transportOrder.getVehicle() == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }

        // Validate driver
        if (transportOrder.getDriver() == null) {
            throw new IllegalArgumentException("Driver cannot be null");
        }

        // Validate departure location
        if (transportOrder.getDepartureLocation() == null || transportOrder.getDepartureLocation().isEmpty()) {
            throw new IllegalArgumentException("Departure location cannot be blank");
        }
        if (transportOrder.getDepartureLocation().length() > 100) {
            throw new IllegalArgumentException("Departure location cannot exceed 100 characters");
        }

        // Validate destination location
        if (transportOrder.getDestinationLocation() == null || transportOrder.getDestinationLocation().isEmpty()) {
            throw new IllegalArgumentException("Destination location cannot be blank");
        }
        if (transportOrder.getDestinationLocation().length() > 100) {
            throw new IllegalArgumentException("Destination location cannot exceed 100 characters");
        }

        // Validate departure date
        if (transportOrder.getDepartureDate() == null) {
            throw new IllegalArgumentException("Departure date cannot be null");
        }

        // Validate arrival date
        if (transportOrder.getArrivalDate() == null) {
            throw new IllegalArgumentException("Arrival date cannot be null");
        }

        // Ensure that the arrival date is after the departure date
        if (transportOrder.getArrivalDate().isBefore(transportOrder.getDepartureDate())) {
            throw new IllegalArgumentException("Arrival date cannot be before departure date");
        }

        // Validate distance
        if (transportOrder.getDistance() == null || transportOrder.getDistance() < 0) {
            throw new IllegalArgumentException("Distance must be zero or positive");
        }

        // Validate transport type
        if (transportOrder.getTransportType() == null) {
            throw new IllegalArgumentException("Transport type cannot be null");
        }

        // Validate cargo weight
        if (transportOrder.getCargoWeightKg() == null || transportOrder.getCargoWeightKg() < 0) {
            throw new IllegalArgumentException("Cargo weight must be zero or positive");
        }

        // Validate amount
        if (transportOrder.getAmount() == null || transportOrder.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        // Validate payment status
        if (transportOrder.getIsPaid() == null) {
            throw new IllegalArgumentException("Payment status cannot be null");
        }
    }
}