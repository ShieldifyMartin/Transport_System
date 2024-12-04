package org.example.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.example.configuration.SessionFactoryUtil;
import org.example.dao.CompanyDAO;
import org.example.dto.StaffDTO;
import org.example.entity.Company;
import org.example.entity.SalaryPayment;
import org.example.entity.Staff;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class CompanyService {
    private CompanyDAO companyDAO;

    public CompanyService() {
        this.companyDAO = new CompanyDAO();
    }

    // Standart getter functions

    public Company getCompanyById(long id) {
        return companyDAO.getCompanyById(id);
    }

    public List<Company> getCompanies() {
        return companyDAO.getCompanies();
    }

    public List<Company> getAllCompanies() {
        return companyDAO.getAllCompanies();
    }

    public Set<Staff> getCompanyStaff(long companyId) {
        return companyDAO.getCompanyStaff(companyId);
    }

    public List<StaffDTO> getCompanyStaffDTO(long companyId) {
        return companyDAO.getCompanyStaffDTO(companyId);
    }

    public BigDecimal calculateTotalSalaryExpenses(long companyId) {
        Set<Staff> staffMembers = companyDAO.getCompanyStaff(companyId);

        BigDecimal totalSalaries = staffMembers.stream()
            .map(Staff::getSalary)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalSalaries;
    }

    // Sorting getter functions

    // Get all companies sorted by name
    public List<Company> getCompaniesSortedByName() {
        return companyDAO.getCompanies().stream()
                .sorted(Comparator.comparing(Company::getName))
                .collect(Collectors.toList());
    }

    // Get all companies sorted by total revenue (largest first)
    public List<Company> getCompaniesSortedByLargestRevenue() {
        return companyDAO.getCompanies().stream()
            .sorted(Comparator.comparing(Company::getTotalRevenue, Comparator.reverseOrder())) // Descending order
            .collect(Collectors.toList());
    }

    // Filter functions

    // Get companies filtered by name containing a keyword
    public List<Company> getCompaniesByNameKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new IllegalArgumentException("Keyword cannot be null or empty");
        }
        return companyDAO.getCompanies().stream()
            .filter(company -> company.getName().toLowerCase().contains(keyword.toLowerCase()))
            .collect(Collectors.toList());
    }

    public List<Company> filterCompanies(String name, 
        String address, 
        LocalDate foundationDateStart, 
        LocalDate foundationDateEnd, 
        String VAT, 
        String entityType, 
        BigDecimal initialCapitalMin, 
        BigDecimal initialCapitalMax, 
        BigDecimal totalRevenueMin, 
        BigDecimal totalRevenueMax, 
        BigDecimal totalExpensesMin, 
        BigDecimal totalExpensesMax, 
        Boolean isDeleted) {
    return companyDAO.getCompanies().stream()
        .filter(company -> name == null || company.getName().equalsIgnoreCase(name))
        .filter(company -> address == null || company.getAddress().contains(address))
        .filter(company -> foundationDateStart == null || !company.getFoundationDate().isBefore(foundationDateStart))
        .filter(company -> foundationDateEnd == null || !company.getFoundationDate().isAfter(foundationDateEnd))
        .filter(company -> VAT == null || company.getVAT().equalsIgnoreCase(VAT))
        .filter(company -> entityType == null || company.getEntityType().equalsIgnoreCase(entityType))
        .filter(company -> initialCapitalMin == null || company.getInitialCapital().compareTo(initialCapitalMin) >= 0)
        .filter(company -> initialCapitalMax == null || company.getInitialCapital().compareTo(initialCapitalMax) <= 0)
        .filter(company -> totalRevenueMin == null || company.getTotalRevenue().compareTo(totalRevenueMin) >= 0)
        .filter(company -> totalRevenueMax == null || company.getTotalRevenue().compareTo(totalRevenueMax) <= 0)
        .filter(company -> totalExpensesMin == null || company.getTotalExpenses().compareTo(totalExpensesMin) >= 0)
        .filter(company -> totalExpensesMax == null || company.getTotalExpenses().compareTo(totalExpensesMax) <= 0)
        .filter(company -> isDeleted == null || company.isDeleted() == isDeleted)
        .collect(Collectors.toList());
    }

    // Core logic

    public void saveCompany(Company company) {
        validateCompany(company);
        companyDAO.saveCompany(company);
    }

    public void updateCompany(Company company) {
        validateCompany(company);
        companyDAO.updateCompany(company);
    }

    // Soft delete company (mark as deleted)
    public void deleteCompany(long id) {
        if (getCompanyById(id) == null || id == 0) {
            throw new IllegalArgumentException("Invalid company id");
        }
        companyDAO.softDeleteCompanyById(id);
    }

    // Hard delete company (mark as deleted)
    public void hardDeleteCompany(long id) {
        if (getCompanyById(id) == null || id == 0) {
            throw new IllegalArgumentException("Invalid company");
        }
        companyDAO.hardDeleteCompanyById(id);
    }

    // Fetch update with the current total expenses
    public void updateCompanyExpenses(long companyId) {
        Company company = companyDAO.getCompanyById(companyId);
        BigDecimal salaryExpenses = calculateTotalSalaryExpenses(companyId);
    
        company.setTotalExpenses(
            company.getTotalExpenses().add(salaryExpenses)
        );
    
        companyDAO.updateCompany(company);
    }

    public Staff getStaffWithSalaryPayments(Long staffId) {
        Staff staff;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            staff = session.createQuery(
                    "SELECT s FROM Staff s LEFT JOIN FETCH s.salaryPayments WHERE s.id = :id AND s.isDeleted = false",
                    Staff.class
                )
                .setParameter("id", staffId)
                .getSingleResult();
            transaction.commit();
        }
        return staff;
    }    

    public void paySalaries(long companyId) {
        Set<Staff> staffList = getCompanyStaff(companyId);
        LocalDate currentMonth = LocalDate.now().withDayOfMonth(1);
    
        BigDecimal totalPaid = BigDecimal.ZERO;
    
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
    
            for (Staff staff : staffList) {
                // Use getStaffWithSalaryPayments to retrieve the staff with initialized salaryPayments
                Staff staffWithPayments = getStaffWithSalaryPayments(staff.getId());
    
                boolean alreadyPaid = staffWithPayments.getSalaryPayments().stream()
                    .anyMatch(payment -> payment.getPaymentDate().equals(currentMonth));
    
                if (!alreadyPaid) {
                    SalaryPayment payment = new SalaryPayment(
                        staffWithPayments, 
                        currentMonth, 
                        staffWithPayments.getSalary()
                    );
                    staffWithPayments.getSalaryPayments().add(payment);
                    session.persist(payment);
                    totalPaid = totalPaid.add(staffWithPayments.getSalary());
                }
            }
    
            transaction.commit();
        }
    
        // Update the company's total expenses with the new salaries paid
        CompanyDAO.addExpenses(companyId, totalPaid);
    }    

    // Helper validation function
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

        // Check if total revenue is null or negative
        if (company.getTotalRevenue() == null || company.getTotalRevenue().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Total revenue must be zero or greater and cannot be null");
        }

        // Check decimal scale of total revenue (max 2 decimals)
        if (company.getTotalRevenue().scale() > 2) {
            throw new IllegalArgumentException("Total revenue must be a valid monetary amount with up to 2 decimal places");
        }

        // Check if total expenses are null or negative
        if (company.getTotalExpenses() == null || company.getTotalExpenses().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Total expenses must be zero or greater and cannot be null");
        }

        // Check decimal scale of total expenses (max 2 decimals)
        if (company.getTotalExpenses().scale() > 2) {
            throw new IllegalArgumentException("Total expenses must be a valid monetary amount with up to 2 decimal places");
        }
    }
}
