package org.example.dao;

import java.util.List;

import org.example.configuration.SessionFactoryUtil;
import org.example.entity.Vehicle;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class VehicleDAO {
    public static Vehicle getVehicleById(long id) {
        Vehicle vehicle;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            vehicle = session.get(Vehicle.class, id);
            transaction.commit();
        }
        return vehicle;
    }

    public static List<Vehicle> getVehicles() {
        List<Vehicle> vehicles;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            vehicles = session
                    .createQuery("Select v From Vehicle v", Vehicle.class)
                    .getResultList();
            transaction.commit();
        }
        return vehicles;
    }

    public static void saveVehicle(Vehicle vehicle) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(vehicle);
            transaction.commit();
        }
    }

    public static void updateVehicle(Vehicle vehicle) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(vehicle);
            transaction.commit();
        }
    }

    public static void deleteVehicle(Vehicle vehicle) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(vehicle);
            transaction.commit();
        }
    }
}
