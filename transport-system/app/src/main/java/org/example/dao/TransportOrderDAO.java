package org.example.dao;

import jakarta.persistence.NoResultException;
import java.util.List;
import org.example.configuration.SessionFactoryUtil;
import org.example.entity.TransportOrder;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TransportOrderDAO {
    // Get Transport Order by ID where isDeleted is false
    public static TransportOrder getTransportOrderById(long id) {
        TransportOrder transportOrder;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            transportOrder = session.createQuery("SELECT tr FROM TransportOrder tr WHERE tr.id = :id AND tr.isDeleted = false", TransportOrder.class)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
        } catch (NoResultException e) {
            transportOrder = null;
        }
        return transportOrder;
    }

    // Get list of transport orders where isDeleted is false
    public static List<TransportOrder> getTransportOrders() {
        List<TransportOrder> transportOrders;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            transportOrders = session
                    .createQuery("SELECT tr FROM TransportOrder tr WHERE tr.isDeleted = false", TransportOrder.class)
                    .getResultList();
            transaction.commit();
        } catch (NoResultException e) {
            transportOrders = null;
        }
        return transportOrders;
    }

    // Save transport order
    public static void saveTransportOrder(TransportOrder transportOrder) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(transportOrder);
            transaction.commit();
        }
    }

    // Update transport order
    public static void updateTransportOrder(TransportOrder transportOrder) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(transportOrder);
            transaction.commit();
        }
    }

    // Delete transport order (physical delete)
    public static void hardDeleteTransportOrder(TransportOrder transportOrder) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(transportOrder);
            transaction.commit();
        }
    }

    // Soft delete transport order by setting isDeleted to true
    public static void softDeleteTransportOrder(TransportOrder transportOrder) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            // Mark the transport order as deleted instead of removing it from the database
            transportOrder.softDelete();
            session.merge(transportOrder);
            transaction.commit();
        }
    }
}