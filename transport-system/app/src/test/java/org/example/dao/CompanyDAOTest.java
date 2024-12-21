package org.example.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.example.entity.Company;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CompanyDAOTest {

    private CompanyDAO companyDAO;
    long newlyAddedCompanyId = 1;

    @BeforeEach
    public void setUp() {
        companyDAO = new CompanyDAO();
    }

    private String generateRandomVAT() {
        int randomNum = (int) (Math.random() * 900000000) + 100000000;
        return "VAT" + randomNum;
    }

    @Test
    public void testSaveCompany() {
        Company company = new Company();
        company.setName("Test Company");
        company.setAddress("123 Test St, Test City");
        company.setFoundationDate(LocalDate.of(2020, 1, 1));
        company.setVAT(generateRandomVAT());
        company.setEntityType("LLC");
        company.setOwners("John Doe, Jane Doe");
        company.setInitialCapital(new BigDecimal("100000.00"));
        company.setTotalRevenue(new BigDecimal("500000.00"));
        company.setTotalExpenses(new BigDecimal("100000.00"));

        companyDAO.saveCompany(company);
        assertNotNull(company.getId());

        Company savedCompany = companyDAO.getCompanyById(company.getId());
        newlyAddedCompanyId = savedCompany.getId();
        assertNotNull(savedCompany);
        assertEquals("Test Company", savedCompany.getName());
        assertEquals("123 Test St, Test City", savedCompany.getAddress());
    }

    @Test
    public void testUpdateCompany() {
        Company company = new Company();
        company.setName("Test Company");
        company.setAddress("123 Test St, Test City");
        company.setFoundationDate(LocalDate.of(2020, 1, 1));
        company.setVAT(generateRandomVAT());
        company.setEntityType("LLC");
        company.setOwners("John Doe, Jane Doe");
        company.setInitialCapital(new BigDecimal("100000.00"));
        company.setTotalRevenue(new BigDecimal("500000.00"));
        company.setTotalExpenses(new BigDecimal("100000.00"));
        companyDAO.saveCompany(company);

        company.setName("Updated Company");
        companyDAO.updateCompany(company);

        Company updatedCompany = companyDAO.getCompanyById(company.getId());
        assertNotNull(updatedCompany);
        assertEquals("Updated Company", updatedCompany.getName());
    }

    @Test
    public void testSoftDeleteCompanyById() {
        assertNotNull(newlyAddedCompanyId, "Company should exist before delete.");
        companyDAO.softDeleteCompanyById(newlyAddedCompanyId);

        assertNull(companyDAO.getCompanyById(newlyAddedCompanyId));
    }

    @Test
    public void testHardDeleteCompanyById() {
        // Create a sample company
        Company company = new Company();
        company.setName("Sample Company");
        company.setAddress("456 Example Rd, Sample City");
        company.setFoundationDate(LocalDate.of(2022, 3, 15));
        company.setVAT(generateRandomVAT());
        company.setEntityType("Corp");
        company.setOwners("Alice Smith, Bob Brown");
        company.setInitialCapital(new BigDecimal("150000.00"));
        company.setTotalRevenue(new BigDecimal("750000.00"));
        company.setTotalExpenses(new BigDecimal("200000.00"));
        companyDAO.saveCompany(company);

        // Ensure the company was saved
        long companyId = company.getId();
        assertNotNull(companyDAO.getCompanyById(companyId));

        // Delete the company
        companyDAO.hardDeleteCompanyById(companyId);

        // Verify the company was deleted
        assertNull(companyDAO.getCompanyByIdAdmin(companyId));
    }

    @Test
    public void testGetAllCompanies() {
        Company company1 = new Company();
        company1.setName("Company 1");
        company1.setAddress("Address 1");
        company1.setFoundationDate(LocalDate.of(2020, 1, 1));
        company1.setVAT(generateRandomVAT());
        company1.setEntityType("LLC");
        company1.setOwners("John Doe");
        company1.setInitialCapital(new BigDecimal("100000.00"));
        company1.setTotalRevenue(new BigDecimal("500000.00"));
        company1.setTotalExpenses(new BigDecimal("100000.00"));
        companyDAO.saveCompany(company1);

        Company company2 = new Company();
        company2.setName("Company 2");
        company2.setAddress("Address 2");
        company2.setFoundationDate(LocalDate.of(2021, 5, 5));
        company2.setVAT(generateRandomVAT());
        company2.setEntityType("Ltd");
        company2.setOwners("Jane Doe");
        company2.setInitialCapital(new BigDecimal("200000.00"));
        company2.setTotalRevenue(new BigDecimal("600000.00"));
        company2.setTotalExpenses(new BigDecimal("120000.00"));
        companyDAO.saveCompany(company2);

        List<Company> companies = companyDAO.getAllCompanies();
        assertNotNull(companies);
        assertTrue(companies.size() >= 2);
    }
}
