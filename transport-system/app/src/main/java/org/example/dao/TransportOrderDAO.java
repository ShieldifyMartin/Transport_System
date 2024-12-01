package org.example.dao;

import java.util.List;

import org.example.configuration.SessionFactoryUtil;
import org.example.entity.TransportOrder;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TransportOrderDAO {
    public static TransportOrder getTransportOrderById(long id) {
        TransportOrder transportOrder;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            transportOrder = session.get(TransportOrder.class, id);
            transaction.commit();
        }
        return transportOrder;
    }

    public static List<TransportOrder> getTransportOrders() {
        List<TransportOrder> transportOrders;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            transportOrders = session
                    .createQuery("Select tr From TransportOrder tr", TransportOrder.class)
                    .getResultList();
            transaction.commit();
        }
        return transportOrders;
    }

    public static void saveTransportOrder(TransportOrder transportOrder) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(transportOrder);
            transaction.commit();
        }
    }

    public static void updateTransportOrder(TransportOrder transportOrder) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(transportOrder);
            transaction.commit();
        }
    }

    public static void deleteTransportOrder(TransportOrder transportOrder) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(transportOrder);
            transaction.commit();
        }
    }
}
