package com.zakharenkov.shop.connection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Connection {

    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    public static Session getSession() {
        return FACTORY.openSession();
    }

    public static SessionFactory getFactory() {
        return FACTORY;
    }
}
