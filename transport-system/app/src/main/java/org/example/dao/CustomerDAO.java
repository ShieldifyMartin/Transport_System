package org.example.dao;

import java.util.List;
import org.example.configuration.SessionFactoryUtil;
import org.example.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CustomerDAO {
    // Get Customer by ID where isDeleted is false
    public static Customer getCustomerById(long id) {
        Customer customer;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            customer = session.createQuery("SELECT c FROM Customer c WHERE c.id = :id AND c.isDeleted = false", Customer.class)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
        }
        return customer;
    }

    // Get list of customers where isDeleted is false
    public static List<Customer> getCustomers() {
        List<Customer> customers;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            customers = session
                    .createQuery("SELECT c FROM Customer c WHERE c.isDeleted = false", Customer.class)
                    .getResultList();
            transaction.commit();
        }
        return customers;
    }

    // Get list of all customers
    public static List<Customer> getAllCustomers() {
        List<Customer> customers;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            customers = session
                    .createQuery("SELECT c FROM Customer c", Customer.class)
                    .getResultList();
            transaction.commit();
        }
        return customers;
    }

    // Save customer
    public static void saveCustomer(Customer customer) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(customer);
            transaction.commit();
        }
    }

    // Update customer
    public static void updateCustomer(Customer customer) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(customer);
            transaction.commit();
        }
    }

    // Delete customer (physical delete)
    public static void hardDeleteCustomer(Customer customer) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(customer);
            transaction.commit();
        }
    }

    // Soft delete customer by setting isDeleted to true
    public static void softDeleteCustomer(Customer customer) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            // Mark the customer as deleted instead of removing it from the database
            customer.softDelete();
            session.merge(customer);
            transaction.commit();
        }
    }
}