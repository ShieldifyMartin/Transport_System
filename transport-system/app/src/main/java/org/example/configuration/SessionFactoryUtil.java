package org.example.configuration;

import org.example.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SessionFactoryUtil {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Company.class);
            configuration.addAnnotatedClass(Customer.class);
            configuration.addAnnotatedClass(Staff.class);
            configuration.addAnnotatedClass(Driver.class);
            configuration.addAnnotatedClass(TransportOrder.class);
            configuration.addAnnotatedClass(Vehicle.class);
            ServiceRegistry serviceRegistry
                    = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml").build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }
}
