package org.example.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import org.example.dao.CompanyDAO;
import org.example.dto.StaffDTO;
import org.example.entity.Company;
import org.example.entity.Staff;

public class CompanyService {
    private CompanyDAO companyDAO;

    public CompanyService() {
        this.companyDAO = new CompanyDAO();
    }

    public Company getCompanyById(long id) {
        return companyDAO.getCompanyById(id);
    }

    public List<Company> getCompanies() {
        return companyDAO.getCompanies();
    }

    public Set<Staff> getCompanyStaff(long companyId) {
        return companyDAO.getCompanyStaff(companyId);
    }

    public List<StaffDTO> getCompanyStaffDTO(long companyId) {
        return companyDAO.getCompanyStaffDTO(companyId);
    }

    public void saveCompany(Company company) {
        validateCompany(company);
        companyDAO.saveCompany(company);
    }

    public void updateCompany(Company company) {
        validateCompany(company);
        companyDAO.updateCompany(company);
    }

    // Soft delete company (mark as deleted)
    public void deleteCompany(Company company) {
        if (company == null || company.getId() == 0) {
            throw new IllegalArgumentException("Invalid company");
        }
        companyDAO.softDeleteCompany(company);
    }

    // Hard delete company (mark as deleted)
    public void hardDeleteCompany(Company company) {
        if (company == null || company.getId() == 0) {
            throw new IllegalArgumentException("Invalid company");
        }
        companyDAO.hardDeleteCompany(company);
    }

    private void validateCompany(Company company) {
        // Check if company is null
        if (company == null) {
            throw new IllegalArgumentException("Company cannot be null");
        }
        
        // Check if company name is empty
        if (company.getName() == null || company.getName().isEmpty()) {
            throw new IllegalArgumentException("Company name cannot be null or empty");
        }

        // Check name length (max 100 chars)
        if (company.getName().length() > 100) {
            throw new IllegalArgumentException("Company name must not exceed 100 characters");
        }

        // Check if address is empty
        if (company.getAddress() == null || company.getAddress().isEmpty()) {
            throw new IllegalArgumentException("Address cannot be null or empty");
        }
        
        // Check address length (max 255 chars)
        if (company.getAddress().length() > 255) {
            throw new IllegalArgumentException("Address must not exceed 255 characters");
        }

        // Check if foundation date is valid
        if (company.getFoundationDate() == null || company.getFoundationDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Foundation date cannot be in the future");
        }

        // Check if VAT number is alphanumeric and not empty
        if (company.getVAT() == null || company.getVAT().isEmpty()) {
            throw new IllegalArgumentException("VAT number cannot be null or empty");
        }
        
        if (!company.getVAT().matches("^[A-Za-z0-9]+$")) {
            throw new IllegalArgumentException("VAT number must be alphanumeric");
        }

        // Check if entity type is empty
        if (company.getEntityType() == null || company.getEntityType().isEmpty()) {
            throw new IllegalArgumentException("Entity type cannot be null or empty");
        }
        
        // Check entity type length (max 50 chars)
        if (company.getEntityType().length() > 50) {
            throw new IllegalArgumentException("Entity type must not exceed 50 characters");
        }

        // Check if owners list is empty
        if (company.getOwners() == null || company.getOwners().isEmpty()) {
            throw new IllegalArgumentException("Owners cannot be null or empty");
        }
        
        // Check owners length (max 1000 chars)
        if (company.getOwners().length() > 1000) {
            throw new IllegalArgumentException("Owners must not exceed 1000 characters");
        }

        // Check if initial capital is valid
        if (company.getInitialCapital() == null || company.getInitialCapital().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Initial capital must be greater than 0");
        }

        // Check decimal scale of initial capital (max 2 decimals)
        if (company.getInitialCapital().scale() > 2) {
            throw new IllegalArgumentException("Initial capital must be a valid monetary amount with up to 2 decimal places");
        }        
    }
}
