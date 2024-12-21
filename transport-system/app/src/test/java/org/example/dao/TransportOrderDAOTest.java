package org.example.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;
import org.example.dao.utils.TestingObjUtils;
import org.example.entity.Company;
import org.example.entity.Driver;
import org.example.entity.TransportOrder;
import org.example.entity.Vehicle;
import org.example.entity.enums.TransportType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TransportOrderDAOTest {
    private static final Logger logger = Logger.getLogger(TransportOrderDAO.class.getName());
    private CompanyDAO companyDAO;
    private VehicleDAO vehicleDAO;
    private DriverDAO driverDAO;
    private TransportOrderDAO transportOrderDAO;
    private TestingObjUtils testingObjUtils;
    private Company company;
    private Vehicle vehicle;
    private Driver driver;

    @BeforeEach
    public void setUp() {
        companyDAO = new CompanyDAO();
        vehicleDAO = new VehicleDAO();
        driverDAO = new DriverDAO();
        transportOrderDAO = new TransportOrderDAO();
        testingObjUtils = new TestingObjUtils();

        // setup sub objects
        Company comp = testingObjUtils.createTestCompany();
        companyDAO.saveCompany(comp);
        company = comp;

        Vehicle v = testingObjUtils.createTestVehicle();
        v.setCompany(company);
        vehicleDAO.saveVehicle(v);
        vehicle = v;

        Driver d = testingObjUtils.createTestDriver();
        d.setCompany(company);
        driverDAO.saveDriver(d);
        driver = d;
    }

    private TransportOrder createSampleTransportOrder() {
        return new TransportOrder(
            company, vehicle, driver,
            "New York", "Los Angeles",
            LocalDate.of(2024, 1, 1),
            LocalDate.of(2024, 1, 10),
            2500.0, TransportType.CARGO_TRANSPORTATION,
            1000, new BigDecimal("1500.00"), true
        );
    }

    @Test
    public void testSaveTransportOrder() {
        // Create and save transport order
        TransportOrder transportOrder = createSampleTransportOrder();
        transportOrderDAO.saveTransportOrder(transportOrder);

        // Verify it has been saved and has an ID assigned
        assertNotNull(transportOrder.getId(), "TransportOrder ID should not be null after saving.");

        // Retrieve the saved transport order by ID
        TransportOrder savedOrder = transportOrderDAO.getTransportOrderById(transportOrder.getId());
        assertNotNull(savedOrder, "TransportOrder should be retrievable by ID.");
        assertEquals("New York", savedOrder.getDepartureLocation(), "Departure location should match.");
        assertEquals("Los Angeles", savedOrder.getDestinationLocation(), "Destination location should match.");
    }

    @Test
    public void testGetTransportOrders() {
        // Create and save a transport order
        TransportOrder transportOrder1 = createSampleTransportOrder();
        transportOrderDAO.saveTransportOrder(transportOrder1);

        // Create and save another transport order
        TransportOrder transportOrder2 = createSampleTransportOrder();
        transportOrderDAO.saveTransportOrder(transportOrder2);

        // Retrieve all transport orders and verify
        List<TransportOrder> orders = transportOrderDAO.getTransportOrders();

        logger.info(String.format("SIZE: %s", orders.size()));
        assertTrue(orders.size() >= 2, "There should be at least two transport orders in the list.");
    }

    @Test
    public void testGetTransportOrderById() {
        // Create and save a transport order
        TransportOrder transportOrder = createSampleTransportOrder();
        transportOrderDAO.saveTransportOrder(transportOrder);

        // Retrieve by ID and verify
        TransportOrder retrievedOrder = transportOrderDAO.getTransportOrderById(transportOrder.getId());

        assertNotNull(retrievedOrder, "TransportOrder should be retrievable by ID.");
        assertEquals("New York", retrievedOrder.getDepartureLocation(), "Departure location should match.");
        assertEquals("Los Angeles", retrievedOrder.getDestinationLocation(), "Destination location should match.");
        assertEquals(new BigDecimal("1500.00"), retrievedOrder.getAmount(), "Amount should match.");
        assertEquals(1000, retrievedOrder.getCargoWeightKg(), "Cargo weight should match.");
    }

    @Test
    public void testUpdateTransportOrder() {
        // Create and save a transport order
        TransportOrder transportOrder = createSampleTransportOrder();
        transportOrderDAO.saveTransportOrder(transportOrder);

        // Update some properties
        transportOrder.setDepartureLocation("Updated Location");
        transportOrder.setAmount(new BigDecimal("2000.00"));
        transportOrderDAO.updateTransportOrder(transportOrder);

        // Retrieve the updated order and verify changes
        TransportOrder updatedOrder = transportOrderDAO.getTransportOrderById(transportOrder.getId());
        assertNotNull(updatedOrder, "TransportOrder should be retrievable after update.");
        assertEquals("Updated Location", updatedOrder.getDepartureLocation(), "Departure location should be updated.");
        assertEquals(new BigDecimal("2000.00"), updatedOrder.getAmount(), "Amount should be updated.");
    }

    @Test
    public void testHardDeleteTransportOrder() {
        // Create and save a transport order
        TransportOrder transportOrder = createSampleTransportOrder();
        transportOrderDAO.saveTransportOrder(transportOrder);

        // Perform hard delete
        transportOrderDAO.hardDeleteTransportOrder(transportOrder);

        // Try to retrieve the transport order (should be null after deletion)
        TransportOrder deletedOrder = transportOrderDAO.getTransportOrderById(transportOrder.getId());
        assertNull(deletedOrder, "TransportOrder should be null after hard delete.");
    }

    @Test
    public void testSoftDeleteTransportOrder() {
        // Create and save a transport order
        TransportOrder transportOrder = createSampleTransportOrder();
        transportOrderDAO.saveTransportOrder(transportOrder);

        // Perform soft delete
        transportOrderDAO.softDeleteTransportOrder(transportOrder);

        // Try to retrieve the soft-deleted transport order (should be null if isDeleted is checked)
        TransportOrder softDeletedOrder = transportOrderDAO.getTransportOrderById(transportOrder.getId());
        assertNull(softDeletedOrder, "TransportOrder should be null after soft delete.");
    }
}