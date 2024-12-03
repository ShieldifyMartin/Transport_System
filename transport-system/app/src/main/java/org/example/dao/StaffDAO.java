package org.example.dao;

import java.util.List;
import org.example.configuration.SessionFactoryUtil;
import org.example.entity.Staff;
import org.example.entity.enums.Position;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class StaffDAO {
    // Get Staff by ID where isDeleted is false
    public static Staff getStaffById(long id) {
        Staff staff;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            staff = session.createQuery("SELECT s FROM Staff s WHERE s.id = :id AND s.isDeleted = false", Staff.class)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
        }
        return staff;
    }

    // Get list of staff where isDeleted is false
    public static List<Staff> getStaff() {
        List<Staff> staff;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            staff = session
                    .createQuery("SELECT s FROM Staff s WHERE s.isDeleted = false", Staff.class)
                    .getResultList();
            transaction.commit();
        }
        return staff;
    }

    // Get list of staff by position where isDeleted is false
    public static List<Staff> getStaffByPosition(Position position) {
        List<Staff> staff;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            staff = session
                    .createQuery("SELECT s FROM Staff s WHERE s.position = :position AND s.isDeleted = false", Staff.class)
                    .setParameter("position", position)
                    .getResultList();
            transaction.commit();
        }
        return staff;
    }

    // Save staff
    public static void saveStaff(Staff staff) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(staff);
            transaction.commit();
        }
    }

    // Update staff
    public static void updateStaff(Staff staff) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(staff);
            transaction.commit();
        }
    }

    // Delete staff (physical delete)
    public static void hardDeleteStaff(Staff staff) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(staff);
            transaction.commit();
        }
    }

    // Soft delete staff by setting isDeleted to true
    public static void softDeleteStaff(Staff staff) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            // Mark the staff as deleted instead of removing it from the database
            staff.softDelete();
            session.merge(staff);
            transaction.commit();
        }
    }
}