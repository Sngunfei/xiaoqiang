package com.syf.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Utils {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private Utils() {}

    static {
        System.out.println("111111111111");
        sessionFactory = new Configuration().configure().buildSessionFactory();
        System.out.println("22222222222");
    }
}
