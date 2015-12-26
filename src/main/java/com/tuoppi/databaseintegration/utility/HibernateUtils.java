package com.tuoppi.databaseintegration.utility;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Component;

/**
 *
 * @author Tuoppi
 * 
 * Obtain hibernate session from this class
 */
@Component
public class HibernateUtils {
    
    private final Configuration configuration;
    private final ServiceRegistry serviceRegistry;
    private final SessionFactory sessionFactory;
    
    
    public HibernateUtils() {
        
        configuration = new Configuration().configure();
        serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }
    
    public Session getSession() {
        
        return sessionFactory.openSession();
    }
    
}