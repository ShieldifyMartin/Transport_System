package org.example.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;
import org.example.dao.utils.TestingObjUtils;
import org.example.entity.Company;
import org.example.entity.Vehicle;
import org.example.entity.enums.DrivingCategory;
import org.example.entity.enums.VehicleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleDAOTest {
    private static final Logger logger = Logger.getLogger(VehicleDAOTest.class.getName());
    private CompanyDAO companyDAO;
    private VehicleDAO vehicleDAO;
    private TestingObjUtils testingObjUtils;
    private Company company;

    @BeforeEach
    public void setUp() {
        companyDAO = new CompanyDAO();
        vehicleDAO = new VehicleDAO();
        testingObjUtils = new TestingObjUtils();

        // setup sub objects
        Company comp = testingObjUtils.createTestCompany();
        companyDAO.saveCompany(comp);
        company = comp;
    }

    private Vehicle createTestVehicle() {
        return new Vehicle(
            company, 
            VehicleType.CAR, 
            "Toyota", 
            "Corolla", 
            "ABC123", 
            2020, 
            50000.0, 
            150, 
            new BigDecimal("1200.00"), 
            7.5, 
            450.0, 
            5, 
            DrivingCategory.B, 
            false
        );
    }

    @Test
    public void testSaveVehicle() {
        // Create and save vehicle
        Vehicle vehicle = createTestVehicle();
        vehicleDAO.saveVehicle(vehicle);

        // Verify it has been saved and has an ID assigned
        assertNotNull(vehicle.getId(), "Vehicle ID should not be null after saving.");

        // Retrieve the saved vehicle by ID
        Vehicle savedVehicle = vehicleDAO.getVehicleById(vehicle.getId());
        assertNotNull(savedVehicle, "Vehicle should be retrievable by ID.");
        assertEquals("Toyota", savedVehicle.getManifacture(), "Manufacturer should match.");
        assertEquals("Corolla", savedVehicle.getModel(), "Model should match.");
    }

    @Test
    public void testGetVehicles() {
        // Create and save a vehicle
        Vehicle vehicle1 = createTestVehicle();
        vehicleDAO.saveVehicle(vehicle1);

        // Create and save another vehicle
        Vehicle vehicle2 = createTestVehicle();
        vehicleDAO.saveVehicle(vehicle2);

        // Retrieve all vehicles and verify
        List<Vehicle> vehicles = vehicleDAO.getVehicles();
        assertTrue(vehicles.size() >= 2, "There should be at least two vehicles in the list.");
    }

    @Test
    public void testGetVehicleById() {
        // Create and save a vehicle
        Vehicle vehicle = createTestVehicle();
        vehicleDAO.saveVehicle(vehicle);

        // Retrieve by ID and verify
        Vehicle retrievedVehicle = vehicleDAO.getVehicleById(vehicle.getId());
        assertNotNull(retrievedVehicle, "Vehicle should be retrievable by ID.");
        assertEquals("Toyota", retrievedVehicle.getManifacture(), "Manufacturer should match.");
    }

    @Test
    public void testUpdateVehicle() {
        // Create and save a vehicle
        Vehicle vehicle = createTestVehicle();
        vehicleDAO.saveVehicle(vehicle);

        // Update some properties
        vehicle.setManifacture("Honda");
        vehicle.setModel("Civic");
        vehicle.setMileageKm(60000.0);
        vehicleDAO.updateVehicle(vehicle);

        // Retrieve the updated vehicle and verify changes
        Vehicle updatedVehicle = vehicleDAO.getVehicleById(vehicle.getId());
        assertNotNull(updatedVehicle, "Vehicle should be retrievable after update.");
        assertEquals("Honda", updatedVehicle.getManifacture(), "Manufacturer should be updated.");
        assertEquals("Civic", updatedVehicle.getModel(), "Model should be updated.");
        assertEquals(60000.0, updatedVehicle.getMileageKm(), "Mileage should be updated.");
    }

    @Test
    public void testHardDeleteVehicle() {
        // Create and save a vehicle
        Vehicle vehicle = createTestVehicle();
        vehicleDAO.saveVehicle(vehicle);

        // Perform hard delete
        vehicleDAO.hardDeleteVehicle(vehicle);

        // Try to retrieve the vehicle (should be null after deletion)
        Vehicle deletedVehicle = vehicleDAO.getVehicleById(vehicle.getId());
        assertNull(deletedVehicle, "Vehicle should be null after hard delete.");
    }

    @Test
    public void testSoftDeleteVehicle() {
        // Create and save a vehicle
        Vehicle vehicle = createTestVehicle();
        vehicleDAO.saveVehicle(vehicle);

        // Perform soft delete
        vehicleDAO.softDeleteVehicle(vehicle);

        // Try to retrieve the soft-deleted vehicle (should be null if isDeleted is checked)
        Vehicle softDeletedVehicle = vehicleDAO.getVehicleById(vehicle.getId());
        assertNull(softDeletedVehicle, "Vehicle should be null after soft delete.");
    }
}