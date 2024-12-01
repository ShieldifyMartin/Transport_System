package org.example.dao;

import java.util.List;

import org.example.configuration.SessionFactoryUtil;
import org.example.entity.Staff;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class StaffDAO {
     public static Staff getStaffById(long id) {
        Staff staff;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            staff = session.get(Staff.class, id);
            transaction.commit();
        }
        return staff;
    }

    public static List<Staff> getStaff() {
        List<Staff> staff;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            staff = session
                    .createQuery("Select s From Staff s", Staff.class)
                    .getResultList();
            transaction.commit();
        }
        return staff;
    }

    public static void saveStaff(Staff staff) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(staff);
            transaction.commit();
        }
    }

    public static void updateStaff(Staff staff) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(staff);
            transaction.commit();
        }
    }

    public static void deleteStaff(Staff staff) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(staff);
            transaction.commit();
        }
    }
}
