package com.syf.dao;

import com.syf.bean.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Date;

public class test {

    public static void main(String[] args){

        //Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
        SessionFactory sf = DB.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            Task task = session.get(Task.class, 12);
            task.setFinishTime(new Date(System.currentTimeMillis()));
            System.out.println(task.toString());
            session.update(task);
            transaction.commit();
        }catch (HibernateException e){
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
            sf.close();
        }

    }
}
