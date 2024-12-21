package org.example.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;
import org.example.entity.Company;
import org.example.entity.Staff;
import org.example.entity.enums.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class StaffDAOTest {
    private static final Logger logger = Logger.getLogger(StaffDAOTest.class.getName());
    private StaffDAO staffDAO;
    private CompanyDAO companyDAO;
    private Company company;

    @BeforeEach
    public void setUp() {
        staffDAO = new StaffDAO();
        companyDAO = new CompanyDAO();
        
        // Create and save a new Company for the staff
        Company comp = new Company(
            "Test Company", 
            "123 Test St, Test City", 
            LocalDate.of(2020, 1, 1),
            generateRandomVAT(), 
            "LLC", 
            "John Doe, Jane Doe", 
            new BigDecimal("100000.00")
        );
        companyDAO.saveCompany(comp);
        company = companyDAO.getCompanyById(comp.getId());
    }

    private String generateRandomEmail() {
        int randomNum = (int) (Math.random() * 90000) + 10000;
        return "staff" + randomNum + "@example.com";
    }

    private String generateRandomVAT() {
        int randomNum = (int) (Math.random() * 900000000) + 100000000;
        return "VAT" + randomNum;
    }

    @Test
    public void testSaveStaff() {
        // Create a new Staff using the constructor
        Staff staff = new Staff(
                "John Staff",
                Position.DRIVER,
                35,
                new BigDecimal("2500.00"),
                10.0,
                LocalDate.of(2020, 1, 1),
                company,
                generateRandomEmail()
        );

        staffDAO.saveStaff(staff);
        assertNotNull(staff.getId());

        // Retrieve the saved staff and verify data
        Staff savedStaff = staffDAO.getStaffById(staff.getId());
        assertNotNull(savedStaff);
        assertEquals("John Staff", savedStaff.getName());
    }

    @Test
    public void testSoftDeleteStaff() {
        // Create and save a new staff
        Staff staff = new Staff(
                "John Staff",
                Position.DRIVER,
                35,
                new BigDecimal("2500.00"),
                10.0,
                LocalDate.of(2020, 1, 1),
                company,
                generateRandomEmail()
        );
        staffDAO.saveStaff(staff);

        // Perform soft delete
        staffDAO.softDeleteStaff(staff.getId());

        // Try to retrieve the staff after soft delete (should not be found)
        Staff deletedStaff = staffDAO.getStaffById(staff.getId());
        assertNull(deletedStaff, "Staff should not be found after soft delete.");
        
        // Optionally, you can verify if the soft delete flag is set without fetching the deleted staff
        staff = staffDAO.getStaffById(staff.getId());  // should still be null
        assertNull(staff, "Deleted staff should not appear in regular queries.");
    }

    @Test
    public void testHardDeleteStaff() {
        // Create and save a new staff
        Staff staff = new Staff(
                "John Staff",
                Position.DRIVER,
                35,
                new BigDecimal("2500.00"),
                10.0,
                LocalDate.of(2020, 1, 1),
                company,
                generateRandomEmail()
        );
        staffDAO.saveStaff(staff);

        // Perform hard delete
        staffDAO.hardDeleteStaff(staff);

        // Try to retrieve the staff after hard delete
        Staff hardDeletedStaff = staffDAO.getStaffById(staff.getId());
        assertNull(hardDeletedStaff, "Staff should be physically deleted and not retrievable.");
    }

    @Test
    public void testGetStaff() {
        // Save a couple of staff members
        Staff staff1 = new Staff(
                "Staff 1",
                Position.DRIVER,
                30,
                new BigDecimal("2500.00"),
                10.0,
                LocalDate.of(2020, 1, 1),
                company,
                generateRandomEmail()
        );
        staffDAO.saveStaff(staff1);

        Staff staff2 = new Staff(
                "Staff 2",
                Position.DRIVER,
                40,
                new BigDecimal("3000.00"),
                15.0,
                LocalDate.of(2020, 1, 1),
                company,
                generateRandomEmail()
        );
        staffDAO.saveStaff(staff2);

        // Retrieve all staff and verify
        List<Staff> staffList = staffDAO.getStaff();
        assertTrue(staffList.size() >= 2, "There should be at least two staff members in the list.");
    }

    @Test
    public void testGetStaffByPosition() {
        // Save staff members with different positions
        Staff staff1 = new Staff(
                "Staff 1",
                Position.DRIVER,
                30,
                new BigDecimal("2500.00"),
                10.0,
                LocalDate.of(2020, 1, 1),
                company,
                generateRandomEmail()
        );
        staffDAO.saveStaff(staff1);

        Staff staff2 = new Staff(
                "Staff 2",
                Position.MANAGER,
                40,
                new BigDecimal("3500.00"),
                15.0,
                LocalDate.of(2020, 1, 1),
                company,
                generateRandomEmail()
        );
        staffDAO.saveStaff(staff2);

        // Retrieve staff by position
        List<Staff> drivers = staffDAO.getStaffByPosition(Position.DRIVER);
        assertTrue(drivers.size() > 0, "There should be at least one driver in the list.");
    }
}