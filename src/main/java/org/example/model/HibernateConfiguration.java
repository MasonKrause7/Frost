package org.example.model;

import jakarta.persistence.Entity;
import org.example.Main;
import org.frost.util.PackageScanner;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Properties;

public class HibernateConfiguration {
    private  static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        //configuration object used for session factory, when instatiated like this, hibernate will automatically
        //pick up any hibernate.properties files
        Configuration configuration = new Configuration();

        //object to scan packages in application
        PackageScanner packageScanner = new PackageScanner(Main.class);
        //package scanner returns all classes in application path with the @Entity annotation
        List<Class> entities = packageScanner.finAnnotatedClasses(Entity.class);

        //loop through entity list and add it to the configuration file
        for(Class entity : entities) {
            configuration.addAnnotatedClass(entity);
        }


        //standard operations to be performed when open and closing connections
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        //factory used to open sessions
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        System.out.println(sessionFactory == null);
        return sessionFactory;






    }
}
