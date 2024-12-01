package org.example.dao;

import java.util.List;

import org.example.configuration.SessionFactoryUtil;
import org.example.entity.Driver;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DriverDAO {

    public static Driver getDriverById(long id) {
        Driver driver;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            driver = session.get(Driver.class, id);
            transaction.commit();
        }
        return driver;
    }

    public static List<Driver> getDrivers() {
        List<Driver> drivers;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            drivers = session
                    .createQuery("Select d From Driver d", Driver.class)
                    .getResultList();
            transaction.commit();
        }
        return drivers;
    }

    public static void saveDriver(Driver driver) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(driver);
            transaction.commit();
        }
    }

    public static void updateDriver(Driver driver) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(driver);
            transaction.commit();
        }
    }

    public static void deleteDriver(Driver driver) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(driver);
            transaction.commit();
        }
    }
}
