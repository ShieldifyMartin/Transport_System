package org.example.dao;

import jakarta.persistence.NoResultException;
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
        Company company;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            company = session.createQuery("Select c From Company c where c.id = :id and c.isDeleted = false", Company.class)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
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
        }
        return companies;
    }

    public static Set<Staff> getCompanyStaff(long id) {
        Company company = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                company = session.createQuery(
                                "select c from Company c" +
                                        " join fetch c.staff" +
                                        " where c.id = :id and c.isDeleted = false",
                                Company.class)
                        .setParameter("id", id)
                        .getSingleResult();
                transaction.commit();
            } catch (NoResultException e) {
                transaction.rollback();
            }
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
                                    "where c.id = :id and c.isDeleted = false", // Filter added here
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

    public static void hardDeleteCompanyById(long id) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            
            Company company = session.get(Company.class, id);
            if (company == null) {
                throw new IllegalArgumentException("Company with ID " + id + " does not exist.");
            }
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
            
            // Mark the company as deleted instead of removing it from the database
            company.softDelete();
            session.merge(company);
            transaction.commit();
        }
    }
}
