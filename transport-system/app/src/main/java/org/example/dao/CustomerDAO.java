package org.example.dao;

import java.util.List;
import java.util.Set;
import org.example.configuration.SessionFactoryUtil;
import org.example.dto.StaffDTO;
import org.example.entity.Customer;
import org.example.entity.Staff;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CustomerDAO {
    public static Customer getCustomerById(long id) {
        Customer customer;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            customer = session.get(Customer.class, id);
            transaction.commit();
        }
        return customer;
    }

    public static List<Customer> getCustomers() {
        List<Customer> customers;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            customers = session
                    .createQuery("Select c From Customer c", Customer.class)
                    .getResultList();
            transaction.commit();
        }
        return customers;
    }

    public static void saveCustomer(Customer customer) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(customer);
            transaction.commit();
        }
    }

    public static void updateCustomer(Customer customer) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(customer);
            transaction.commit();
        }
    }

    public static void deleteCustomer(Customer customer) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(customer);
            transaction.commit();
        }
    }
}