package org.example.dao;

import org.example.configuration.SessionFactoryUtil;
import org.example.entity.Driver;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DriverDAO {
    // Save driver
    public static void saveDriver(Driver driver) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(driver);
            transaction.commit();
        }
    }

    // Update driver
    public static void updateDriver(Driver driver) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(driver);
            transaction.commit();
        }
    }

    // Delete driver (physical delete)
    public static void hardDeleteDriver(Driver driver) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(driver);
            transaction.commit();
        }
    }
}