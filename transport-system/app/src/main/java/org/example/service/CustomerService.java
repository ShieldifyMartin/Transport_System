package org.example.service;

import java.math.BigDecimal;
import java.util.List;
import org.example.dao.CustomerDAO;
import org.example.entity.Customer;

public class CustomerService {
    // Method to fetch a customer by ID
    public Customer getCustomerById(long id) {
        Customer customer = CustomerDAO.getCustomerById(id);
        if (customer == null) {
            throw new IllegalArgumentException("Customer with ID " + id + " does not exist.");
        }
        return customer;
    }

    // Method to fetch all customers
    public List<Customer> getAllCustomers() {
        return CustomerDAO.getCustomers();
    }

    // Method to save a new customer
    public void saveCustomer(Customer customer) {
        validateCustomer(customer);
        CustomerDAO.saveCustomer(customer);
    }

    // Method to update an existing customer
    public void updateCustomer(Customer customer) {
        if (CustomerDAO.getCustomerById(customer.getId()) == null) {
            throw new IllegalArgumentException("Cannot update non-existent customer with ID " + customer.getId());
        }
        validateCustomer(customer);
        CustomerDAO.updateCustomer(customer);
    }

    // Method to soft delete a customer
    public void deleteCustomer(Customer customer) {
        if (customer == null || customer.getId() == 0) {
            throw new IllegalArgumentException("Invalid customer");
        }
        CustomerDAO.softDeleteCustomer(customer);
    }

    // Method to hard delete a customer
    public void hardDeleteCustomer(Customer customer) {
        if (customer == null || customer.getId() == 0) {
            throw new IllegalArgumentException("Invalid customer");
        }
        CustomerDAO.hardDeleteCustomer(customer);
    }

    // Private method to validate a customer object
    private void validateCustomer(Customer customer) {
    if (customer == null) {
        throw new IllegalArgumentException("Customer cannot be null.");
    }

    // Validate name
    if (customer.getName() == null || customer.getName().trim().isEmpty()) {
        throw new IllegalArgumentException("Name cannot be null or empty.");
    }
    if (customer.getName().length() < 1 || customer.getName().length() > 100) {
        throw new IllegalArgumentException("Name must be between 1 and 100 characters.");
    }

    // Validate description
    if (customer.getDescription() != null && customer.getDescription().length() > 500) {
        throw new IllegalArgumentException("Description cannot exceed 500 characters.");
    }

    // Validate customer since
    if (customer.getCustomer_since() == null) {
        throw new IllegalArgumentException("Customer since date cannot be null.");
    }

    // Validate loyalty status
    if (customer.getLoyalty_status() == null) {
        throw new IllegalArgumentException("Loyalty status cannot be null.");
    }

    // Validate money spent
    if (customer.getMoney_spent() != null) {
        if (customer.getMoney_spent().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Money spent must be greater than or equal to 0.");
        }
        String moneySpentStr = customer.getMoney_spent().toPlainString();
        if (moneySpentStr.contains(".") && moneySpentStr.split("\\.")[1].length() > 2) {
            throw new IllegalArgumentException("Money spent can have up to 2 decimal places.");
        }
    }

    // Validate phone
    if (customer.getPhone() != null && !customer.getPhone().matches("^[0-9]{10}$")) {
        throw new IllegalArgumentException("Phone must be a 10-digit number.");
    }

    // Validate address
    if (customer.getAddress() == null || customer.getAddress().trim().isEmpty()) {
        throw new IllegalArgumentException("Address cannot be null or empty.");
    }
    if (customer.getAddress().length() < 10 || customer.getAddress().length() > 255) {
        throw new IllegalArgumentException("Address must be between 10 and 255 characters.");
    }

    // Validate email
    if (customer.getEmail() == null || customer.getEmail().trim().isEmpty()) {
        throw new IllegalArgumentException("Email cannot be null or empty.");
    }
    if (!customer.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
        throw new IllegalArgumentException("Email must be valid.");
    }
    if (customer.getEmail().length() > 100) {
        throw new IllegalArgumentException("Email must not exceed 100 characters.");
    }
}

}
