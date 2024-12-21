package org.example.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.example.entity.Customer;
import org.example.entity.enums.LoyaltyStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerDAOTest {

    private CustomerDAO customerDAO;

    @BeforeEach
    public void setUp() {
        customerDAO = new CustomerDAO();
    }

    private String generateRandomEmail() {
        int randomNum = (int) (Math.random() * 90000) + 10000;
        return "user" + randomNum + "@example.com";
    }

    @Test
    public void testSaveCustomer() {
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setDescription("Loyal customer");
        customer.setCustomer_since(LocalDate.of(2020, 1, 1));
        customer.setLoyalty_status(LoyaltyStatus.GOLD);
        customer.setMoney_spent(new BigDecimal("1500.00"));
        customer.setPhone("1234567890");
        customer.setAddress("123 Test Street, Test City");
        customer.setEmail(generateRandomEmail());

        customerDAO.saveCustomer(customer);
        assertNotNull(customer.getId());

        Customer savedCustomer = customerDAO.getCustomerById(customer.getId());
        assertNotNull(savedCustomer);
        assertEquals("John Doe", savedCustomer.getName());
        assertEquals("Loyal customer", savedCustomer.getDescription());
    }

    @Test
    public void testUpdateCustomer() {
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setDescription("Loyal customer");
        customer.setCustomer_since(LocalDate.of(2020, 1, 1));
        customer.setLoyalty_status(LoyaltyStatus.GOLD);
        customer.setMoney_spent(new BigDecimal("1500.00"));
        customer.setPhone("1234567890");
        customer.setAddress("123 Test Street, Test City");
        customer.setEmail(generateRandomEmail());
        customerDAO.saveCustomer(customer);

        customer.setName("Jane Doe");
        customer.setDescription("Updated description");
        customerDAO.updateCustomer(customer);

        Customer updatedCustomer = customerDAO.getCustomerById(customer.getId());
        assertNotNull(updatedCustomer);
        assertEquals("Jane Doe", updatedCustomer.getName());
        assertEquals("Updated description", updatedCustomer.getDescription());
    }

    @Test
    public void testSoftDeleteCustomer() {
        // Create and save a new customer
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setDescription("Loyal customer");
        customer.setCustomer_since(LocalDate.of(2020, 1, 1));
        customer.setLoyalty_status(LoyaltyStatus.GOLD);
        customer.setMoney_spent(new BigDecimal("1500.00"));
        customer.setPhone("1234567890");
        customer.setAddress("123 Test Street, Test City");
        customer.setEmail(generateRandomEmail());
        customerDAO.saveCustomer(customer);

        // Fetch the saved customer to ensure the ID is generated correctly
        Customer savedCustomer = customerDAO.getCustomerById(customer.getId());
        assertNotNull(savedCustomer, "Customer should be successfully saved.");
        assertNotNull(savedCustomer.getId(), "Saved customer should have a valid ID.");

        // Soft delete the customer using the retrieved ID
        customerDAO.softDeleteCustomer(savedCustomer.getId());

        // Verify that the customer is soft deleted
        assertNull(customerDAO.getCustomerById(savedCustomer.getId()), "Customer should not be retrievable after soft delete.");
    }

    @Test
    public void testHardDeleteCustomer() {
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setDescription("Loyal customer");
        customer.setCustomer_since(LocalDate.of(2020, 1, 1));
        customer.setLoyalty_status(LoyaltyStatus.GOLD);
        customer.setMoney_spent(new BigDecimal("1500.00"));
        customer.setPhone("1234567890");
        customer.setAddress("123 Test Street, Test City");
        customer.setEmail(generateRandomEmail());
        customerDAO.saveCustomer(customer);

        customerDAO.hardDeleteCustomer(customer);

        List<Customer> allCustomers = customerDAO.getAllCustomers();
        assertTrue(allCustomers.stream().noneMatch(c -> c.getId().equals(customer.getId())));
    }

    @Test
    public void testGetCustomers() {
        Customer customer1 = new Customer();
        customer1.setName("John Doe");
        customer1.setDescription("Loyal customer");
        customer1.setCustomer_since(LocalDate.of(2020, 1, 1));
        customer1.setLoyalty_status(LoyaltyStatus.GOLD);
        customer1.setMoney_spent(new BigDecimal("1500.00"));
        customer1.setPhone("1234567890");
        customer1.setAddress("123 Test Street, Test City");
        customer1.setEmail(generateRandomEmail());
        customerDAO.saveCustomer(customer1);

        Customer customer2 = new Customer();
        customer2.setName("Jane Doe");
        customer2.setDescription("New customer");
        customer2.setCustomer_since(LocalDate.of(2021, 5, 15));
        customer2.setLoyalty_status(LoyaltyStatus.SILVER);
        customer2.setMoney_spent(new BigDecimal("500.00"));
        customer2.setPhone("0987654321");
        customer2.setAddress("456 Another Street, Another City");
        customer2.setEmail(generateRandomEmail());
        customerDAO.saveCustomer(customer2);

        List<Customer> customers = customerDAO.getCustomers();
        assertNotNull(customers);
        assertTrue(customers.size() >= 2);
    }
}