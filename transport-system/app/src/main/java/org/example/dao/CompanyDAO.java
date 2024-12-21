package org.example.dao;

import jakarta.persistence.NoResultException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.example.configuration.SessionFactoryUtil;
import org.example.dto.StaffDTO;
import org.example.entity.Company;
import org.example.entity.Staff;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CompanyDAO {
    public static Company getCompanyById(long id) {
        Company company = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                company = session.createQuery("Select c From Company c where c.id = :id and c.isDeleted = false", Company.class)
                        .setParameter("id", id)
                        .getSingleResult();
            } catch (NoResultException e) {
                company = null;
            }
            transaction.commit();
        }
        return company;
    }

    public static Company getCompanyByIdAdmin(long id) {
        Company company = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                company = session.createQuery("Select c From Company c where c.id = :id", Company.class)
                        .setParameter("id", id)
                        .getSingleResult();
            } catch (NoResultException e) {
                company = null;
            }
            transaction.commit();
        } catch (NoResultException e) {
            company = null;
        }
        return company;
    }

    public static List<Company> getCompanies() {
        List<Company> companies;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            companies = session
                    .createQuery("Select c From Company c where c.isDeleted = false", Company.class)
                    .getResultList();
            transaction.commit();
        } catch (NoResultException e) {
            companies = null;
        }
        return companies;
    }

    public static List<Company> getAllCompanies() {
        List<Company> companies;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            companies = session
                    .createQuery("Select c From Company c", Company.class)
                    .getResultList();
            transaction.commit();
        } catch (NoResultException e) {
            companies = null;
        }
        return companies;
    }

    public static Set<Staff> getCompanyStaff(long id) {
        Company company = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            company = session.createQuery(
                            "select c from Company c" +
                                    " join fetch c.staff" +
                                    " where c.id = :id and c.isDeleted = false",
                            Company.class)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
        }
        return company != null ? company.getStaff() : Collections.emptySet();
    }

    public static List<StaffDTO> getCompanyStaffDTO(long id) {
        List<StaffDTO> staff;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            staff = session.createQuery(
                            "select new org.example.dto.StaffDTO(e.id, e.name)" +
                                    " from Staff e" +
                                    " join e.company c " +
                                    "where c.id = :id and c.isDeleted = false",
                            StaffDTO.class)
                    .setParameter("id", id)
                    .getResultList();
            transaction.commit();
        }
        return staff;
    }

    public static void saveCompany(Company company) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(company);
            transaction.commit();
        }
    }

    public static void updateCompany(Company company) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(company);
            transaction.commit();
        }
    }

    public void hardDeleteCompanyById(long companyId) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
    
            // Check if the Company exists
            Company company = session.find(Company.class, companyId);
            if (company == null) {
                throw new IllegalArgumentException("Company with ID " + companyId + " does not exist.");
            }
    
            // If the company exists, delete it
            session.remove(company);
    
            transaction.commit();
        }
    }    
    
    public static void softDeleteCompanyById(long id) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
    
            Company company = session.get(Company.class, id);
            if (company == null) {
                throw new IllegalArgumentException("Company with ID " + id + " does not exist.");
            } 
            
            company.softDelete();
            session.merge(company);
            transaction.commit();
        }
    }

    public static void addExpenses(long companyId, BigDecimal additionalExpenses) {
        if (additionalExpenses == null || additionalExpenses.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Additional expenses must be a positive value.");
        }

        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            // Fetch the company
            Company company = session.get(Company.class, companyId);
            if (company == null) {
                transaction.rollback();
                throw new IllegalArgumentException("Company with ID " + companyId + " does not exist.");
            }

            // Update total expenses
            BigDecimal newTotalExpenses = company.getTotalExpenses().add(additionalExpenses);
            company.setTotalExpenses(newTotalExpenses);

            // Save the updated company
            session.merge(company);
            transaction.commit();
        }
    }
}
