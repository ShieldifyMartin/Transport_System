package org.example.dao;

import java.util.List;
import org.example.configuration.SessionFactoryUtil;
import org.example.entity.Vehicle;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class VehicleDAO {
    // Get Vehicle by ID where isDeleted is false
    public static Vehicle getVehicleById(long id) {
        Vehicle vehicle;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            vehicle = session.createQuery("SELECT v FROM Vehicle v WHERE v.id = :id AND v.isDeleted = false", Vehicle.class)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
        }
        return vehicle;
    }

    // Get list of vehicles where isDeleted is false
    public static List<Vehicle> getVehicles() {
        List<Vehicle> vehicles;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            vehicles = session
                    .createQuery("SELECT v FROM Vehicle v WHERE v.isDeleted = false", Vehicle.class)
                    .getResultList();
            transaction.commit();
        }
        return vehicles;
    }

    // Save vehicle
    public static void saveVehicle(Vehicle vehicle) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(vehicle);
            transaction.commit();
        }
    }

    // Update vehicle
    public static void updateVehicle(Vehicle vehicle) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(vehicle);
            transaction.commit();
        }
    }

    // Delete vehicle (physical delete)
    public static void hardDeleteVehicle(Vehicle vehicle) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(vehicle);
            transaction.commit();
        }
    }

    // Soft delete vehicle by setting isDeleted to true
    public static void softDeleteVehicle(Vehicle vehicle) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            // Mark the vehicle as deleted instead of removing it from the database
            vehicle.softDelete();
            session.merge(vehicle);
            transaction.commit();
        }
    }
}