package org.example.dao;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.example.configuration.SessionFactoryUtil;
import org.example.dto.StaffDTO;
import org.example.entity.Company;
import org.example.entity.Staff;
import org.hibernate.Session;
import org.hibernate.Transaction;

import jakarta.persistence.NoResultException;

public class CompanyDAO {
    public static Company getCompanyById(long id) {
        Company company;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            company = session.get(Company.class, id);
            transaction.commit();
        }
        return company;
    }

    public static List<Company> getCompanies() {
        List<Company> companies;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
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
                                    " where c.id = :id",
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
                                    "where c.id = :id",
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

    public static void deleteCompany(Company company) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(company);
            transaction.commit();
        }
    }
}
