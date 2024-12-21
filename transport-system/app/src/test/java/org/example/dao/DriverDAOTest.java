package org.example.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.logging.Logger;
import org.example.dao.utils.TestingObjUtils;
import org.example.entity.Company;
import org.example.entity.Driver;
import org.example.entity.enums.DrivingCategory;
import org.example.entity.enums.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class DriverDAOTest {
    private static final Logger logger = Logger.getLogger(DriverDAOTest.class.getName());
    private DriverDAO driverDAO;
    private StaffDAO staffDAO;
    private CompanyDAO companyDAO;
    private TestingObjUtils testingObjUtils;
    Company company;

    private String generateRandomVAT() {
        int randomNum = (int) (Math.random() * 900000000) + 100000000;
        return "VAT" + randomNum;
    }
    private String generateRandomEmail() {
        int randomNum = (int) (Math.random() * 90000) + 10000;
        return "driver" + randomNum + "@example.com";
    }

    @BeforeEach
    public void setUp() {
        testingObjUtils = new TestingObjUtils();
        company = testingObjUtils.createTestCompany();
        companyDAO.saveCompany(company);
        driverDAO = new DriverDAO();
        staffDAO = new StaffDAO();
        companyDAO = new CompanyDAO();
    }

    @Test
    public void testSaveDriver() {
        // Using the Company constructor for initialization
        assertNotNull(company.getId(), "Company ID should not be null after saving.");
        
        // generate random email and then compare it
        String sampleEmail = generateRandomEmail();
        // Create and save a new driver
        Driver driver = new Driver(
            "John Driver",
            Position.DRIVER,
            32,
            new BigDecimal("10000.00"),
            10.0,
            LocalDate.of(2020, 4, 12),
            company,
            sampleEmail,
            DrivingCategory.D,
            150000L,
            new BigDecimal("900.00")
        );

        driverDAO.saveDriver(driver);
        assertNotNull(driver.getId(), "Driver ID should not be null after saving.");

        Driver savedDriver = (Driver) staffDAO.getStaffById(driver.getId());

        // Validating the saved Driver
        assertNotNull(savedDriver, "Saved driver should not be null.");
        assertEquals("John Driver", savedDriver.getName(), "Driver name should match.");
        assertEquals(DrivingCategory.D, savedDriver.getDrivingCategory(), "Driving category should match.");
        assertEquals(32, savedDriver.getAge(), "Driving category should match.");
        assertEquals(sampleEmail, savedDriver.getEmail(), "Driver email should match.");
    }

    @Test
    public void testUpdateDriver() {
        // Create and save a new driver
        Driver driver = new Driver(
            "John Driver",
            Position.DRIVER,
            35,
            new BigDecimal("2500.00"),
            10.0,
            LocalDate.of(2020, 1, 1),
            company,
            generateRandomEmail(),
            DrivingCategory.B,
            50000L,
            new BigDecimal("150.00")
        );
        driverDAO.saveDriver(driver);

       
        driver.setAge(88);
        driver.setName("John Lock");
        driver.setTotalKmDrivenForCompany(60000L);
        driverDAO.updateDriver(driver);

        Driver updatedDriver = (Driver) staffDAO.getStaffById(driver.getId());
        assertNotNull(updatedDriver);
        assertEquals("John Lock", updatedDriver.getName());
        assertEquals(88, updatedDriver.getAge());
        assertEquals(60000L, updatedDriver.getTotalKmDrivenForCompany());
    }

    @Test
    public void testSoftDeleteDriver() {
        // Create and save a new driver
        Driver driver = new Driver(
            "John Driver",
            Position.DRIVER,
            35,
            new BigDecimal("2500.00"),
            10.0,
            LocalDate.of(2020, 1, 1),
            company,
            generateRandomEmail(),
            DrivingCategory.B,
            50000L,
            new BigDecimal("150.00")
        );
        driverDAO.saveDriver(driver);
        
        Driver savedDriver = (Driver) staffDAO.getStaffById(driver.getId());
        assertNotNull(savedDriver, "Driver should be saved successfully.");

        // Perform soft delete
        long id = savedDriver.getId();
        staffDAO.softDeleteStaff(id);
    
        assertNull((Driver) staffDAO.getStaffById(savedDriver.getId()), "Driver should not be retrievable after soft delete.");
    }

    @Test
    public void testHardDeleteDriver() {
        // Create and save a new driver
        Driver driver = new Driver(
            "John Driver",
            Position.DRIVER,
            35,
            new BigDecimal("2500.00"),
            10.0,
            LocalDate.of(2020, 1, 1),
            company,
            generateRandomEmail(),
            DrivingCategory.B,
            50000L,
            new BigDecimal("150.00")
        );
        driverDAO.saveDriver(driver);

        // Perform hard delete
        driverDAO.hardDeleteDriver(driver);

        // Validate the hard deletion
        Driver hardDeletedDriver = (Driver) staffDAO.getStaffById(driver.getId());
        assertNull(hardDeletedDriver, "Driver should be physically deleted and not retrievable.");
    }
}