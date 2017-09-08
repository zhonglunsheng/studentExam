package com.lipop.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class HibernateUtil {
    private final static  SessionFactory SESSION_FACTORY = buildSessionFactory();
    private static SessionFactory buildSessionFactory(){
        Configuration configuration = new Configuration().configure();
        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(registry);
    }
    public static SessionFactory getSessionFactory(){
        return SESSION_FACTORY;
    }
}
